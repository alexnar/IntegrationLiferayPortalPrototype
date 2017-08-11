package org.etan.portal.integration.createprojectportlet.portlet;

import com.google.gson.Gson;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import org.etan.portal.integration.projectcontroller.service.dto.ProjectDto;
import org.etan.portal.integration.projectmanage.service.ProjectManage;
import org.etan.portal.integration.projectmanage.service.dto.ProjectManageSummary;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Create project portlet.
 *
 * @author nai
 */
@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=Integration",
                "com.liferay.portlet.instanceable=true",
                "javax.portlet.display-name=Create project",
                "javax.portlet.init-param.template-path=/html/",
                "javax.portlet.init-param.view-template=/html/view.jsp",
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user",
                "com.liferay.portlet.footer-portlet-javascript=/js/main.js",

        },
        service = Portlet.class
)
public class CreateProjectPortlet extends MVCPortlet {

    private static final Log logger = LogFactoryUtil.getLog(CreateProjectPortlet.class);

    private final static String CHECK_STATUS = "CHECK";
    private final static String CREATE_STATUS = "CREATE";

    @Reference
    private ProjectManage projectManage;


    @Override
    public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
        renderRequest.setAttribute("projectManage", projectManage);
        super.doView(renderRequest, renderResponse);
    }


    @ProcessAction(name = "createProject")
    public void createProject(ActionRequest actionRequest, ActionResponse actionResponse) {
        String projectName = actionRequest.getParameter("projectName");
        ServiceContext serviceContext = null;
        try {
            serviceContext = ServiceContextFactory.getInstance(actionRequest);
        } catch (PortalException e) {
            logger.warn(e);
        }
        ProjectDto project = projectManage.createProject(projectName, serviceContext);
    }

    @Override
    public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException, PortletException {
        String projectName = resourceRequest.getParameter("projectName");

        PrintWriter out = resourceResponse.getWriter();
        ProjectManageSummary projectManageSummary =
                projectManage.checkCreateProjectOpportunity(projectName, null);
        Map<String, Boolean> summaryMap = projectManageSummary.getSummaryMap();
        Gson gson = new Gson();
        String response = gson.toJson(summaryMap);

        out.println(response);
        out.flush();
        super.serveResource(resourceRequest, resourceResponse);
    }

    @ProcessAction(name = "setProjectName")
    public void setProjectName(ActionRequest actionRequest, ActionResponse actionResponse) {
        String projectName = null;
        try {
            projectName = actionRequest.getReader().readLine();
        } catch (IOException e) {
            logger.warn(e);
        }
        actionRequest.setAttribute("projectName", projectName);


    }

}