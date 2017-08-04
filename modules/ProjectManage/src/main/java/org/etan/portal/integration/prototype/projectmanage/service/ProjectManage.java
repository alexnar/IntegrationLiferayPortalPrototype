package org.etan.portal.integration.prototype.projectmanage.service;

import org.etan.portal.integration.prototype.projectmanage.service.context.ProjectManageContext;

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
     * @param projectName          - Name of project.
     * @param projectManageContext - Context which contain information
     *                             about who and where initiate calling
     *                             of this method.
     */
    void createProject(String projectName, ProjectManageContext projectManageContext);


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
     * @param userId               - id of user to assign
     * @param projectManageContext - Context which contain information
     *                             about who and where initiate calling
     *                             of this method.
     */
    void assignUser(long userId, ProjectManageContext projectManageContext);

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
     * @param userId               - id of user to unassign
     * @param projectManageContext - Context which contain information
     *                             about who and where initiate calling
     *                             of this method.
     */
    void unassignUser(long userId, ProjectManageContext projectManageContext);
}