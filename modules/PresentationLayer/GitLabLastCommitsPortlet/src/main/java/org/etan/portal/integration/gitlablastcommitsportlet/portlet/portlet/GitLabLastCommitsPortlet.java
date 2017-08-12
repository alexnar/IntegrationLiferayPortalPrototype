package org.etan.portal.integration.gitlablastcommitsportlet.portlet.portlet;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import org.etan.portal.integration.gitlablastcommitsportlet.portlet.constants.GitLabLastCommitsPortletKeys;
import org.etan.portal.integration.gitlabservice.service.GitLabService;
import org.etan.portal.integration.gitlabservice.service.GitLabServiceException;
import org.etan.portal.integration.projectcontroller.service.ProjectController;
import org.etan.portal.integration.projectcontroller.service.dto.ProjectDto;
import org.etan.portal.integration.projectmanage.service.ProjectManage;
import org.gitlab.api.models.GitlabCommit;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.IOException;
import java.util.ArrayList;
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
                "javax.portlet.display-name=GitLabLastCommitsPortlet Portlet",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/view.jsp",
                "javax.portlet.name=" + GitLabLastCommitsPortletKeys.GitLabLastCommits,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class GitLabLastCommitsPortlet extends MVCPortlet {


    private static final Log logger = LogFactoryUtil.getLog(GitLabLastCommitsPortlet.class);

    @Reference
    private GitLabService gitLabService;

    @Reference
    private ProjectManage projectManage;

    @Reference
    private ProjectController projectController;


    @Override
    public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
            throws IOException, PortletException {


        ServiceContext serviceContext = null;
        long organizationId;
        List<GitlabCommit> lastCommits = new ArrayList<>();
        try {
            serviceContext = ServiceContextFactory.getInstance(renderRequest);

            ProjectDto projectDto = projectController.getProject(serviceContext);
            Map<String, String> map = projectDto.getInfrastructureEntityProjectIdMap();
            String gitlabProjectId = map.get("GITLAB");
            int projectId = Integer.valueOf(gitlabProjectId);

            lastCommits = gitLabService.getLastCommits(projectId, 10);
        } catch (PortalException e) {
            throw new RuntimeException("neozhidanno PortalException");
        } catch (GitLabServiceException e) {
            throw new RuntimeException("neozhidanno GitLabServiceException");
        }


        renderRequest.setAttribute("lastCommits", lastCommits);

//		GitlabCommit gitlabCommit = lastCommits.get(0);
//		gitlabCommit.getTitle();
//		gitlabCommit.getMessage();
//		gitlabCommit.getAuthorName();
//		gitlabCommit.getCommittedDate();

        super.doView(renderRequest, renderResponse);
    }
}