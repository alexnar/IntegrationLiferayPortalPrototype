package org.etan.portal.integration.projectcontroller.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import org.etan.portal.integration.projectcontroller.service.dto.ProjectDto;

import java.util.List;
import java.util.Map;

/**
 * Responsible for creating an organization by organization type "Project"
 * and its site, adding / removing users to it, getting
 * a list of "projects" of current "ProjectCatalog" organization.
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
     * @throws IllegalServiceContextException if could not get organization
     *                                        by serviceContext;
     *                                        if organization type name
     *                                        not equals "Project";
     */
    void addUser(User user, ServiceContext context);

    /**
     * Add users to project organization.
     * Do nothing with user, if it is already in.
     *
     * @param users   who was added to project organization
     * @param context context of action, uses for get owner userId, organizationId
     * @throws IllegalServiceContextException if could not get organization
     *                                        by serviceContext;
     *                                        if organization type name
     *                                        not equals "Project";
     */
    void addUsers(List<User> users, ServiceContext context);

    /**
     * Check opportunity for creating
     * project in portal.
     *
     * @param projectName    - name of project
     * @param serviceContext context of action, uses for get userId, organizationId
     * @return true, if there is opportunity to create
     */
    boolean checkCreateProjectOpportunity(String projectName, ServiceContext serviceContext);

    /**
     * Check ServiceContext.
     * It must have not zero(null) field userId, Group, OrganizationId...
     *
     * @param context ServiceContext
     * @return true, if it is right ServiceContext
     */
    boolean checkServiceContext(ServiceContext context);

    /**
     * Creates organization of organization type Project. Also creates site
     * by template Project Template and assign it to created organization.
     * The creator of the project will be the only member in the organization.
     * Saves in database list of "infrastructure entity project id" with mapping on
     * created organization.
     *
     * @param projectName                      name of project, will match
     *                                         the name of organization
     * @param infrastructureEntityProjectIdMap key = infrastructure entity name,
     *                                         value = infrastructure entity project id;
     *                                         may be empty or null.
     * @param serviceContext                   uses for get userId,
     *                                         organizationId
     * @return dto of created "project"
     * @throws PortalException                if could not get user by userId from context
     *                                        or any problem with organization creation
     * @throws IllegalServiceContextException if serviceContext.getUserId equals 0;
     *                                        if could not get organization by
     *                                        serviceContext;
     *                                        if organization type name not equals
     *                                        "ProjectsCatalog";
     * @throws IllegalArgumentException       if projectName == null
     * @throws SiteTemplateNotFoundException  if Project site template not found
     */
    ProjectDto createProject(
            String projectName,
            Map<String, String> infrastructureEntityProjectIdMap,
            ServiceContext serviceContext) throws PortalException;

    /**
     * Creates organization by delegation data
     * from projectDto to overload method
     *
     * @param projectDto only projectName and infrastructureEntityProjectIdMap uses
     * @param context    context of action, used for get owner userId
     * @return dto of created "project"
     * @see #createProject(String, Map, ServiceContext)
     */
    ProjectDto createProject(
            ProjectDto projectDto, ServiceContext context) throws PortalException;

    /**
     * Delete user from project organization.
     * Do nothing, if user is not in.
     *
     * @param user    who was deleted from project organization
     * @param context context of action, used for get organizationId
     */
    void deleteUser(User user, ServiceContext context);

    /**
     * Delete user from project organization.
     * Do nothing with user, if it is already in.
     *
     * @param users   who was deleted from project organization
     * @param context context of action, used for get organizationId
     */
    void deleteUsers(List<User> users, ServiceContext context);

    /**
     * Get a project related to the current Project organization
     *
     * @param context - context of action, used for get current organization
     * @return - ProjectDto
     */
    ProjectDto getProject(ServiceContext context);

    /**
     * Gives a list of available for user projects in current ProjectsCatalog
     * organization.
     *
     * @param context context of action, used for get owner userId, organizationId
     * @return list of available for projects or empty list, if no projects found
     */
    List<ProjectDto> getProjects(ServiceContext context);
}
