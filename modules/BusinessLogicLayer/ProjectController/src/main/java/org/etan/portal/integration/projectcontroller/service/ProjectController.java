package org.etan.portal.integration.projectcontroller.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import org.etan.portal.integration.projectcontroller.service.dto.ProjectDto;

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
//todo mb rename class name
public interface ProjectController {

    /**
     * Add user to project organization.
     * Do nothing, if user is already in.
     *
     * @param user    was added to project organization
     * @param context context of action, uses for get owner userId, organizationId
     */
    void addUser(User user, ServiceContext context);

    /**
     * Add users to project organization .
     * Do nothing with user, if it is already in.
     *
     * @param users   who was added to project organization
     * @param context context of action, uses for get owner userId, organizationId
     */
    void addUsers(List<User> users, ServiceContext context);

    /**
     * Check ServiceContext. It must have not zero(null) field userId, Group, OrganizationId...
     *
     * @param context ServiceContext
     * @return true, if it is right ServiceContext
     */
    boolean checkServiceContext(ServiceContext context);

    /**
     * Creates organization of organization type Project. Also creates site
     * by template Project Template and assign to created organization.
     * Saves in database list of "infrastructure entity project id" with mapping on
     * created organization.
     *
     * @param projectName                      name of project, will match the name of organization
     * @param infrastructureEntityProjectIdMap "infrastructure entity project id" mapped to "infrastructure entity name"
     * @param context                          context of action, used for get owner userId
     * @return dto of created "project" or null, If there is a problem
     */
    //todo mb delete
    ProjectDto createProject(String projectName, Map<String, String> infrastructureEntityProjectIdMap,
                             ServiceContext context) throws PortalException;

    /**
     * Creates organization of organization type Project. Also creates site
     * by template Project Template and assign to created organization.
     * Saves in database list of "infrastructure entity project id" with mapping on
     * created organization.
     *
     * @param projectDto only projectName and map with "infrastructure entity project id" uses
     * @param context    context of action, used for get owner userId
     * @return dto of created "project" or null, If there is a problem
     */
    ProjectDto createProject(ProjectDto projectDto, ServiceContext context) throws PortalException;

    /**
     * Delete user from project organization .
     * Do nothing, if user is not in.
     *
     * @param user    who was deleted from project organization
     * @param context context of action, used for get owner userId, organizationId
     */
    void deleteUser(User user, ServiceContext context);

    /**
     * Delete user from project organization .
     * Do nothing with user, if it is already in.
     *
     * @param users   who was deleted from project organization
     * @param context context of action, used for get owner userId, organizationId
     */
    void deleteUsers(List<User> users, ServiceContext context);

    /**
     * Get a project related to the current Project organization
     *
     * @param context - context of action, used for get owner userId, current organizationId
     * @return - ProjectDto
     */
    //todo mb runtime, when invoked not in Project organization or etc.
    ProjectDto getProject(ServiceContext context);

    /**
     * Gives a list of available for user projects in current ProjectsCatalog organization.
     *
     *
     * @param context context of action, used for get owner userId, organizationId
     * @return list of available for projects
     */
    //todo mb runtime, when invoked not in ProjectsCatalog organization or etc.
    List<ProjectDto> getProjects(ServiceContext context);
}
