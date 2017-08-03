package org.etan.portal.integration.prototype.projectcontroller.service;

import com.liferay.portal.kernel.model.User;
import org.etan.portal.integration.prototype.projectcontroller.service.dto.ProjectDto;
import org.etan.portal.integration.prototype.projectmanage.context.ProjectManageContext;

import java.util.List;
import java.util.Map;

/**
 * Responsible for creating an organization by organization type "Project",
 * adding / removing users to it, getting a list of "projects" of
 * current "ProjectCatalog" organization
 *
 * @author Efimov Timur
 * @version 1.0.1
 */
public interface ProjectController {

    /**
     * Add user to project organization .
     * Do nothing, if user is already in.
     *
     * @param userId  id of user, who was added to project organization
     * @param context context of action, used for get owner userId, organizationId
     */
    void addUser(long userId, ProjectManageContext context);

    /**
     * Add user to project organization .
     * Do nothing, if user is already in.
     *
     * @param user    was added to project organization
     * @param context context of action, used for get owner userId, organizationId
     */
    void addUser(User user, ProjectManageContext context);

    /**
     * Add users to project organization .
     * Do nothing with user, if it is already in.
     *
     * @param userIds ids of users, who was added to project organization
     * @param context context of action, used for get owner userId, organizationId
     */
    void addUsers(long[] userIds, ProjectManageContext context);

    /**
     * Add users to project organization .
     * Do nothing with user, if it is already in.
     *
     * @param users   who was added to project organization
     * @param context context of action, used for get owner userId, organizationId
     */
    void addUsers(List<User> users, ProjectManageContext context);

    /**
     * Creates organization of organization type Project. Also creates site
     * by template Project Template and assign to created organization.
     * Saves in database list of "external system project id" with mapping on
     * created organization.
     *
     * @param projectName           name of project, will match the name of organization
     * @param internalProjectsIdMap "external system project id" mapped to "external system name"
     * @param context               context of action, used for get owner userId
     * @return dto of created "project" or null, If there is a problem
     */
    ProjectDto createProject(String projectName, Map<String, Long> internalProjectsIdMap, ProjectManageContext context);

    /**
     * Creates organization of organization type Project. Also creates site
     * by template Project Template and assign to created organization.
     * Saves in database list of "external system project id" with mapping on
     * created organization.
     *
     * @param projectDto only projectName and map with "external system project id" uses
     * @param context    context of action, used for get owner userId
     * @return dto of created "project" or null, If there is a problem
     */
    ProjectDto createProject(ProjectDto projectDto, ProjectManageContext context);

    /**
     * Delete user from project organization .
     * Do nothing, if user is not in.
     *
     * @param userId  id of user, who was deleted from project organization
     * @param context context of action, used for get owner userId, organizationId
     */
    void deleteUser(long userId, ProjectManageContext context);

    /**
     * Delete user from project organization .
     * Do nothing, if user is not in.
     *
     * @param user    who was deleted from project organization
     * @param context context of action, used for get owner userId, organizationId
     */
    void deleteUser(User user, ProjectManageContext context);

    /**
     * Delete user from project organization .
     * Do nothing with user, if it is already in.
     *
     * @param userIds ids of users, who was deleted from project organization
     * @param context context of action, used for get owner userId, organizationId
     */
    void deleteUsers(long[] userIds, ProjectManageContext context);

    /**
     * Delete user from project organization .
     * Do nothing with user, if it is already in.
     *
     * @param users   who was deleted from project organization
     * @param context context of action, used for get owner userId, organizationId
     */
    void deleteUsers(List<User> users, ProjectManageContext context);

    /**
     * Gives a list of available for user projects in current ProjectsCatalog organization
     *
     * @param context context of action, used for get owner userId, organizationId
     * @return list of available for projects
     */
    List<ProjectDto> getProjects(ProjectManageContext context);
}
