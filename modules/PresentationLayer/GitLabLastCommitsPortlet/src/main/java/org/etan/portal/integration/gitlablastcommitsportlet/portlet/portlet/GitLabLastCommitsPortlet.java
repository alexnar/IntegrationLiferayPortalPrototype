package org.etan.portal.integration.gitlablastcommitsportlet.portlet.portlet;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import org.etan.portal.integration.gitlablastcommitsportlet.portlet.constants.GitLabLastCommitsPortletKeys;
import org.etan.portal.integration.gitlabservice.service.GitLabService;
import org.etan.portal.integration.gitlabservice.service.GitLabServiceException;
import org.etan.portal.integration.projectcontroller.service.ProjectController;
import org.etan.portal.integration.projectcontroller.service.dto.ProjectDto;
import org.gitlab.api.models.GitlabCommit;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author eta
 */
@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=Integration",
                "com.liferay.portlet.instanceable=true",
                "javax.portlet.display-name=GitLab last commits",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/view.jsp",
                "javax.portlet.name=" + GitLabLastCommitsPortletKeys.GitLabLastCommits,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class GitLabLastCommitsPortlet extends MVCPortlet {

    private static final Log logger =
            LogFactoryUtil.getLog(GitLabLastCommitsPortlet.class);
    protected String incorrectPortletPlacementTemplate =
            "/incorrectPortletPlacement.jsp";
    protected String unexpectedErrorTemplate =
            "/unexpected_error.jsp";
    protected String gitlabErrorTemplate =
            "/gitlab_error.jsp";

    @Reference
    private GitLabService gitLabService;

    @Reference
    private ProjectController projectController;

    @Reference
    private OrganizationLocalService organizationLocalService;


    @Override
    public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
            throws IOException, PortletException {

        ServiceContext serviceContext = getServiceContext(renderRequest);
        if (serviceContext != null && isCorrectPortletPlacement(serviceContext)) {
            try {
                includeLastCommitsAttribute(renderRequest, serviceContext);
                super.doView(renderRequest, renderResponse);
            } catch (GitLabServiceException e) {
                logger.error(e, e);
                include(gitlabErrorTemplate, renderRequest, renderResponse);
            } catch (PortalException e) {
                logger.error(e, e);//unreachable, i think
                include(unexpectedErrorTemplate, renderRequest, renderResponse);
            }
        } else {
            include(incorrectPortletPlacementTemplate, renderRequest, renderResponse);
        }
    }

    private ServiceContext getServiceContext(RenderRequest renderRequest) {
        ServiceContext serviceContext = null;
        try {
            serviceContext = ServiceContextFactory.getInstance(renderRequest);
        } catch (PortalException e) {
            logger.error(e, e);//unreachable, i think
        }
        return serviceContext;
    }

    private void includeLastCommitsAttribute(RenderRequest renderRequest,
                                             ServiceContext serviceContext)
            throws GitLabServiceException, PortalException {

        ProjectDto projectDto = projectController.getProject(serviceContext);
        Map<String, String> map = projectDto.getInfrastructureEntityProjectIdMap();
        String gitlabProjectId = map.get("GITLAB");
        int projectId = Integer.valueOf(gitlabProjectId);

        List<GitlabCommit> lastCommits = gitLabService.getLastCommits(projectId, 10);
        renderRequest.setAttribute("lastCommits", lastCommits);
    }

    private boolean isCorrectPortletPlacement(ServiceContext serviceContext) {
        return projectController.isProjectOrganizationSite(serviceContext)
                || projectController.isProjectsCatalogOrganizationSite(serviceContext);
    }
}