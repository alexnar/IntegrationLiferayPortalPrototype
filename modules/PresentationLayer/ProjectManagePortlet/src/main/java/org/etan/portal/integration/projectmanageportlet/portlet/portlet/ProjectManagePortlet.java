package org.etan.portal.integration.projectmanageportlet.portlet.portlet;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.Validator;
import org.etan.portal.integration.projectcontroller.service.ProjectController;
import org.etan.portal.integration.projectmanage.service.ProjectManage;
import org.etan.portal.integration.projectmanageportlet.portlet.constants.ProjectManagePortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author eta
 */
@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=Integration",
                "com.liferay.portlet.instanceable=true",
                "javax.portlet.display-name=Project Manage",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/view.jsp",
                "javax.portlet.name=" + ProjectManagePortletKeys.ProjectManage,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class ProjectManagePortlet extends MVCPortlet {

    private static final Log logger =
            LogFactoryUtil.getLog(ProjectManagePortlet.class);
    protected String incorrectPortletPlacementTemplate =
            "/incorrectPortletPlacement.jsp";
    protected String unexpectedErrorTemplate =
            "/unexpected_error.jsp";


    @Reference
    private ProjectManage projectManage;

    @Reference
    private ProjectController projectController;

    @Reference
    private volatile UserLocalService userLocalService;


    @Override
    public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
            throws IOException, PortletException {

        ServiceContext serviceContext = getServiceContext(renderRequest);
        if (serviceContext != null && isCorrectPortletPlacement(serviceContext)) {
            try {
                includeUserListAttributes(renderRequest, serviceContext);
                super.doView(renderRequest, renderResponse);
            } catch (PortalException e) {
                logger.error(e, e);
                include(unexpectedErrorTemplate, renderRequest, renderResponse);
            }
        } else {
            include(incorrectPortletPlacementTemplate, renderRequest, renderResponse);
        }
    }

    private void includeUserListAttributes(RenderRequest renderRequest,
                                           ServiceContext serviceContext)
            throws PortalException {

        long organizationId = serviceContext.getScopeGroup().getOrganizationId();

        List<User> userList = userLocalService.getUsers(-1, -1);
        List<Boolean> isMemberList = new ArrayList<>();
        ArrayList<Object> userListNew = new ArrayList<>();


        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            if (user.getFullName().equals("") || user.getFullName().equals(" ")) {
                continue;
            }
            userListNew.add(user);
            boolean isMember = userLocalService.hasOrganizationUser(organizationId, user.getUserId());
            isMemberList.add(isMember);
        }


        renderRequest.setAttribute("userList", userListNew);
        renderRequest.setAttribute("isMemberList", isMemberList);
    }

    @ProcessAction(name = "changeUsersMembership")
    public void changeUsersMembership(ActionRequest actionRequest, ActionResponse actionResponse) {

        ServiceContext serviceContext = getServiceContext(actionRequest);
        if (serviceContext == null) return;//unreachable, i think

        List<User> addedUsers = new ArrayList<>();
        List<User> deletedUsers = new ArrayList<>();

        String forAssign[] = actionRequest.getParameterValues("forAssign");
        if (forAssign != null && forAssign.length != 0) {
            for (String aForAssign : forAssign) {
                if (aForAssign == null || !Validator.isNumber(aForAssign)) {
                    continue;
                }
                int userId = Integer.valueOf(aForAssign);
                try {
                    User user = userLocalService.getUser(userId);
                    addedUsers.add(user);
                } catch (PortalException e) {
                    logger.warn(e, e);
                }
            }
        }

        String forUnassign[] = actionRequest.getParameterValues("forUnassign");
        if (forUnassign != null && forUnassign.length != 0) {
            for (String aForUnassign : forUnassign) {
                if (aForUnassign == null || !Validator.isNumber(aForUnassign)) {
                    continue;
                }
                int userId = Integer.valueOf(aForUnassign);
                try {
                    User user = userLocalService.getUser(userId);
                    deletedUsers.add(user);
                } catch (PortalException e) {
                    logger.warn(e, e);
                }
            }
        }

        for (User addedUser : addedUsers) {
            projectManage.assignUser(addedUser, serviceContext);
        }
        for (User deletedUser : deletedUsers) {
            projectManage.unassignUser(deletedUser, serviceContext);
        }
    }

    private ServiceContext getServiceContext(PortletRequest portletRequest) {
        ServiceContext serviceContext = null;
        try {
            serviceContext = ServiceContextFactory.getInstance(portletRequest);
        } catch (PortalException e) {
            logger.error(e, e);//unreachable, i think
        }
        return serviceContext;
    }

    private boolean isCorrectPortletPlacement(ServiceContext serviceContext) {
        return projectController.isProjectOrganizationSite(serviceContext);
    }

}