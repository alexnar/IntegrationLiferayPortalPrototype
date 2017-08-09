package org.etan.portal.integration.projectmanage.service;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import org.etan.portal.integration.projectcontroller.service.dto.ProjectDto;
import org.etan.portal.integration.projectmanage.service.dto.ManageProjectSummary;

/**
 * Interface provide methods for
 * managing project. Calling of this
 * methods would execute corresponding
 * method of every infrastructure entity.
 *
 * @author Naryzhny Alex
 */
public interface ProjectManage {

    /**
     * Create project. This method will create
     * project in all registered infrastructure
     * entities, and create project organization
     * in portal.
     * <p>
     * UserId of current user and current organization
     * can be received from projectManageContext.
     *
     * @param projectName    - Name of project.
     * @param serviceContext - Context which contain information
     *                       about who and where initiate calling
     *                       of this method.
     * @return - ProjectDto of created project
     */
    ProjectDto createProject(String projectName, ServiceContext serviceContext);


    /**
     * Assign user to project. This method will assign
     * user in all registered infrastructure
     * entities, and assign user to organization.
     * <p>
     * UserId of current user and current organization
     * can be received from projectManageContext.
     * <p>
     * If user already exists in some infrastructure
     * entity project, then just skip assign to it.
     *
     * @param user           -  user to assign
     * @param serviceContext - Context which contain information
     *                       about who and where initiate calling
     *                       of this method.
     */
    void assignUser(User user, ServiceContext serviceContext);

    /**
     * Unassign user from project. This method will
     * unassign user in all registered infrastructure
     * entities, and unassign user to organization.
     * <p>
     * UserId of current user and current organization
     * can be received from projectManageContext.
     * <p>
     * If there is not such User in infrastructure
     * entity project, then just skip unassign to it.
     *
     * @param user           - id of user to unassign
     * @param serviceContext - Context which contain information
     *                       about who and where initiate calling
     *                       of this method.
     */
    void unassignUser(User user, ServiceContext serviceContext);

    /**
     * Check opportunity for creating
     * project.
     *
     * @param projectName    - name of project
     * @param serviceContext - Context which contain information
     *                       about who and where initiate calling
     *                       of this method.
     * @return - ManageProjectSummary, which contain information
     * about opportunity to create project.
     */
    ManageProjectSummary checkCreateProjectOpportunity(String projectName, ServiceContext serviceContext);
}