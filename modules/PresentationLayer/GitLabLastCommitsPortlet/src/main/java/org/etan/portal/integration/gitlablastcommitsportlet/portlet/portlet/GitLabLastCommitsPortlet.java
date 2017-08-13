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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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
    private static final String GITLAB = "GITLAB";
    /* attribute names */
    private static final String LAST_COMMITS_ATTRIBUTE_NAME = "lastCommits";
    private static final String DATE_FORMAT_ATTRIBUTE_NAME = "dateFormat";
    private static final String COMMIT_PROJECT_NAMES_MAP_ATTRIBUTE_NAME =
            "commitProjectNameMap";
    private static final String IS_PROJECT_ATTRIBUTE_NAME = "isProject";
    private static final int LAST_COMMITS_NUMBER = 10;

    /* dateFormat */
    private final DateFormat dateFormat = new SimpleDateFormat();
//    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /* paths */
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

        boolean isProject = false;
        boolean isProjectsCatalog = false;

        ServiceContext serviceContext = getServiceContext(renderRequest);
        if (serviceContext != null) {
            isProject = isProject(serviceContext);
            isProjectsCatalog = isProjectsCatalog(serviceContext);
        }

        if (isProject || isProjectsCatalog) {
            try {
                setLastCommitsAttribute(isProject, renderRequest,
                        renderResponse, serviceContext);
                renderRequest.setAttribute(DATE_FORMAT_ATTRIBUTE_NAME, dateFormat);
                renderRequest.setAttribute(IS_PROJECT_ATTRIBUTE_NAME, isProject);
                super.doView(renderRequest, renderResponse);
            } catch (GitLabServiceException e) {
                logger.error(e, e);
                include(gitlabErrorTemplate, renderRequest, renderResponse);
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

    private void setLastCommitsAttribute(boolean isProject,
                                         RenderRequest renderRequest,
                                         RenderResponse renderResponse,
                                         ServiceContext serviceContext)
            throws IOException, PortletException, GitLabServiceException {

        if (isProject) {
            setProjectLastCommitsAttribute(renderRequest, serviceContext);
        } else {
            setProjectsCatalogLastCommitsAttribute(renderRequest, serviceContext);
        }
    }

    private void setProjectLastCommitsAttribute(RenderRequest renderRequest,
                                                ServiceContext serviceContext)
            throws GitLabServiceException {

        ProjectDto projectDto = projectController.getProject(serviceContext);
        Map<String, String> map = projectDto.getInfrastructureEntityProjectIdMap();
        String gitlabProjectId = map.get(GITLAB);
        int projectId = Integer.valueOf(gitlabProjectId);

        List<GitlabCommit> lastCommits = gitLabService.getLastCommits(projectId, LAST_COMMITS_NUMBER);
        renderRequest.setAttribute(LAST_COMMITS_ATTRIBUTE_NAME, lastCommits);
    }

    private void setProjectsCatalogLastCommitsAttribute(RenderRequest renderRequest,
                                                        ServiceContext serviceContext)
            throws GitLabServiceException {

        List<GitlabCommit> tenLastCommits = new ArrayList<>();
        Map<String, String> commitProjectName = new HashMap<>();

        List<ProjectDto> projectDtoList = projectController.getProjects(serviceContext);
        ArrayList<GitlabCommit> allLastCommits = new ArrayList<>();
        for (ProjectDto projectDto : projectDtoList) {
            String projectName = projectDto.getProjectName();
            Map<String, String> map = projectDto.getInfrastructureEntityProjectIdMap();
            String gitlabProjectId = map.get(GITLAB);
            if (gitlabProjectId == null) {
                continue;
            }
            int projectId = Integer.valueOf(gitlabProjectId);
            List<GitlabCommit> projectLastCommits = gitLabService.getLastCommits(projectId, LAST_COMMITS_NUMBER);
            for (GitlabCommit lastCommit : projectLastCommits) {
                //todo make better after test
                commitProjectName.put(lastCommit.getId(), projectName);
            }
            allLastCommits.addAll(projectLastCommits);
        }

        allLastCommits.sort(Comparator.comparing(GitlabCommit::getCommittedDate).reversed());

        for (int i = 0; i < LAST_COMMITS_NUMBER; i++) {
            tenLastCommits.add(allLastCommits.get(i));
        }

        renderRequest.setAttribute(LAST_COMMITS_ATTRIBUTE_NAME, tenLastCommits);
        renderRequest.setAttribute(COMMIT_PROJECT_NAMES_MAP_ATTRIBUTE_NAME, commitProjectName);
    }

    private boolean isProject(ServiceContext serviceContext) {
        return projectController.isProjectOrganizationSite(serviceContext);
    }

    private boolean isProjectsCatalog(ServiceContext serviceContext) {
        return projectController.isProjectsCatalogOrganizationSite(serviceContext);
    }
}