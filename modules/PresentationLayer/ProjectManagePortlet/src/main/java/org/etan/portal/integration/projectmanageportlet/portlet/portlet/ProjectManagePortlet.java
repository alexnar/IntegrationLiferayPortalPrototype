package org.etan.portal.integration.projectmanageportlet.portlet.portlet;

import com.liferay.item.selector.ItemSelector;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.Validator;
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
                "javax.portlet.display-name=ProjectManage Portlet",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/view.jsp",
                "javax.portlet.name=" + ProjectManagePortletKeys.ProjectManage,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class ProjectManagePortlet extends MVCPortlet {

    private static final Log logger = LogFactoryUtil.getLog(ProjectManagePortlet.class);

    @Reference
    private ItemSelector _itemSelector;

    @Reference
    private ProjectManage projectManage;


    @Reference
    private volatile UserLocalService userLocalService;


    @Override
    public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
            throws IOException, PortletException {


        ServiceContext serviceContext = null;
        long organizationId;
        try {
            serviceContext = ServiceContextFactory.getInstance(renderRequest);
            organizationId = serviceContext.getScopeGroup().getOrganizationId();
        } catch (PortalException e) {
            throw new RuntimeException("neozhidanno");
        }


        List<User> userList = userLocalService.getUsers(-1, -1);
        List<Boolean> isMemberList = new ArrayList<>();

        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            boolean isMember = userLocalService.hasOrganizationUser(organizationId, user.getUserId());
            isMemberList.add(isMember);
        }

        renderRequest.setAttribute("userList", userList);
        renderRequest.setAttribute("isMemberList", isMemberList);


        super.doView(renderRequest, renderResponse);
    }

    @ProcessAction(name = "changeUsersMembership")
    public void changeUsersMembership(ActionRequest actionRequest, ActionResponse actionResponse) {

        ServiceContext serviceContext = null;
        try {
            serviceContext = ServiceContextFactory.getInstance(actionRequest);
        } catch (PortalException e) {
            throw new RuntimeException("neozhidanno");
        }

        List<User> addedUsers = new ArrayList<>();
        List<User> deletedUsers = new ArrayList<>();

        String forAssign[] = actionRequest.getParameterValues("forAssign");
        if (forAssign != null && forAssign.length != 0) {
            logger.info("~~~~~~You have selected forAssign: ");
            for (String aForAssign : forAssign) {
                if (aForAssign == null || !Validator.isNumber(aForAssign)) {
                    continue;
                }
                int userId = Integer.valueOf(aForAssign);
                try {
                    User user = userLocalService.getUser(userId);
                    logger.info("~~~~~~~~~~~~" + userId + " user name" + user.getFullName());
                    addedUsers.add(user);
                } catch (PortalException e) {
                    logger.warn(e, e);
                }
            }
        }

        String forUnassign[] = actionRequest.getParameterValues("forUnassign");
        if (forUnassign != null && forUnassign.length != 0) {
            logger.info("~~~~~~You have selected forUnassign: ");
            for (String aForUnassign : forUnassign) {
                if (aForUnassign == null || !Validator.isNumber(aForUnassign)) {
                    continue;
                }
                int userId = Integer.valueOf(aForUnassign);
                try {
                    User user = userLocalService.getUser(userId);
                    logger.info("~~~~~~~~~~~~" + userId + " user name" + user.getFullName());
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

}