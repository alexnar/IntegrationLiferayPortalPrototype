package nexuslastartifactsportlet.portlet;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import org.etan.portal.integration.nexusservice.service.NexusService;
import org.etan.portal.integration.nexusservice.service.dto.NexusComponentDto;
import org.etan.portal.integration.nexusservice.service.exception.NexusException;
import org.etan.portal.integration.projectcontroller.service.ProjectController;
import org.etan.portal.integration.projectcontroller.service.dto.ProjectDto;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author nai
 */
@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=Integration",
                "com.liferay.portlet.instanceable=true",
                "javax.portlet.display-name=Nexus last artifacts portlet",
                "javax.portlet.init-param.template-path=/html/",
                "javax.portlet.init-param.view-template=/html/view.jsp",
                "com.liferay.portlet.header-portlet-css=/css/main.css",
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class NexusLastArtifactsPortlet extends MVCPortlet {
    private static final String NEXUS_REPOSITORY_NAME = "NEXUS REPOSITORY";
    private static final String PORTAL_EXCEPTION_MESSAGE = "Error while getting context";
    private static final String NEXUS_EXCEPTION_MESSAGE = "Cannot get last artifacts error";

    protected String incorrectPortletPlacementTemplate =
            "/html/incorrectPortletPlacement.jsp";
    protected String unexpectedErrorTemplate =
            "/html/unexpected_error.jsp";
    protected String gitlabErrorTemplate =
            "/html/gitlab_error.jsp";

    private static final Log logger = LogFactoryUtil.getLog(NexusLastArtifactsPortlet.class);


    @Reference
    private NexusService nexusService;

    @Reference
    private ProjectController projectController;


    @Override
    public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
        ServiceContext serviceContext = getServiceContext(renderRequest);
        if (serviceContext != null && isCorrectPortletPlacement(serviceContext)) {
            try {
                includeLastArtifactsAttribute(renderRequest, serviceContext);
                super.doView(renderRequest, renderResponse);
            } catch (NexusException e) {
                logger.error(e.getMessage(), e);
                include(gitlabErrorTemplate, renderRequest, renderResponse);
            } catch (PortalException e) {
                logger.error(e.getMessage(), e);
                include(unexpectedErrorTemplate, renderRequest, renderResponse);
            }
        } else {
            include(incorrectPortletPlacementTemplate, renderRequest, renderResponse);
        }
    }

    private void includeLastArtifactsAttribute(RenderRequest renderRequest,
                                             ServiceContext serviceContext)
            throws NexusException, PortalException {

        ProjectDto projectDto = projectController.getProject(serviceContext);
        Map<String, String> map = projectDto.getInfrastructureEntityProjectIdMap();
        String nexusProjectId = map.get(NEXUS_REPOSITORY_NAME);


        List<NexusComponentDto> lastArtifacts = nexusService.getLastArtifacts(nexusProjectId, 5);
        renderRequest.setAttribute("lastArtifacts", lastArtifacts);
    }

    private boolean isCorrectPortletPlacement(ServiceContext serviceContext) {
        return projectController.isProjectOrganizationSite(serviceContext)
                || projectController.isProjectsCatalogOrganizationSite(serviceContext);
    }

    private ServiceContext getServiceContext(RenderRequest renderRequest) {
        ServiceContext serviceContext = null;
        try {
            serviceContext = ServiceContextFactory.getInstance(renderRequest);
        } catch (PortalException e) {
            logger.error(e.getMessage(), e);
        }
        return serviceContext;
    }
}