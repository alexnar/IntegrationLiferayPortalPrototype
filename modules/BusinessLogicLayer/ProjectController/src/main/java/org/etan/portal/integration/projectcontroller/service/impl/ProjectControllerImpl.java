package org.etan.portal.integration.projectcontroller.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.*;
import com.liferay.portal.kernel.service.*;
import org.etan.portal.integration.projectcontroller.service.*;
import org.etan.portal.integration.projectcontroller.service.dto.ProjectDto;
import org.etan.portal.integration.projectservice.model.InfrastructureEntityProject;
import org.etan.portal.integration.projectservice.service.InfrastructureEntityProjectLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.*;

/**
 * @author eta
 */
@Component(
        immediate = true,
        property = {
                // TODO enter required service properties
        },
        service = ProjectController.class
)
public class ProjectControllerImpl implements ProjectController {
    private static final Log log = LogFactoryUtil.getLog(ProjectControllerImpl.class);

    private static final String ORGANIZATION_TYPE__PROJECT = "Project";
    private static final String ORGANIZATION_TYPE__PROJECTS_CATALOG = "ProjectsCatalog";
    private static final String ORGANIZATION_COMMENT = "Project organization uses for project's manipulations";
    private static final String PROJECT_SITE_TEMPLATE_NAME = "Project Template";///todo mb bad

    @Reference
    private volatile UserLocalService userLocalService;
    @Reference
    private volatile OrganizationLocalService organizationLocalService;
    @Reference
    private volatile GroupLocalService groupLocalService;
    @Reference
    private volatile LayoutLocalService layoutLocalService;
    @Reference
    private volatile LayoutPrototypeLocalService layoutPrototypeLocalService;
    @Reference
    private volatile LayoutSetPrototypeLocalService layoutSetPrototypeLocalService;
    @Reference
    private volatile LayoutSetLocalService layoutSetLocalService;
    @Reference
    private volatile com.liferay.sites.kernel.util.Sites sitesUtil;
    @Reference
    private volatile InfrastructureEntityProjectLocalService infrastructureEntityProjectLocalService;

    /**
     * Add user to project organization.
     * Do nothing, if user is already in.
     *
     * @param user    was added to project organization
     * @param context context of action, uses for get owner userId, organizationId
     * @see #getProjectOrganization(ServiceContext)
     */
    public void addUser(User user, ServiceContext context) {
        Organization projectOrganization = getProjectOrganization(context);
        organizationLocalService.addUserOrganization(user.getUserId(), projectOrganization);
        //todo reindex if problem occurs
    }

    /**
     * Add users to project organization .
     * Do nothing with user, if it is already in.
     *
     * @param users   who was added to project organization
     * @param context context of action, uses for get owner userId, organizationId
     * @see #getProjectOrganization(ServiceContext)
     */
    public void addUsers(List<User> users, ServiceContext context) {
        Organization projectOrganization = getProjectOrganization(context);
        for (User user : users) {
            organizationLocalService.addUserOrganization(user.getUserId(), projectOrganization);
            //todo reindex if problem occurs
        }
    }

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
     * @throws CouldNotGetUserFromServiceContextRuntimeException if could not get user from context
     * @throws CouldNotCreateOrganizationRuntimeException        if any problem with organization creation
     * @see #getProjectsCatalogOrganization(ServiceContext)
     * @see #assignProjectSiteTemplate(Organization)
     */
    //todo mb delete
    public ProjectDto createProject(String projectName, Map<String, String> infrastructureEntityProjectIdMap,
                                    ServiceContext context) {
        ProjectDto projectDto = null;
        Organization projectsCatalogOrganization = getProjectsCatalogOrganization(context);
        long ownerUserId = context.getUserId();
        Organization newProjectOrganization;
        List<User> members = new ArrayList<>();

        try {
            User owner = userLocalService.getUser(ownerUserId);
            members.add(owner);
        } catch (PortalException e) {
            log.error(e, e);
            throw new CouldNotGetUserFromServiceContextRuntimeException(ownerUserId, e);
            //todo discus runtime
        }

        try {
            newProjectOrganization = organizationLocalService.addOrganization(
                    ownerUserId,
                    projectsCatalogOrganization.getOrganizationId(),
                    projectName,
                    ORGANIZATION_TYPE__PROJECT,
                    0,
                    0,
                    ListTypeConstants.ORGANIZATION_STATUS_DEFAULT,
                    ORGANIZATION_COMMENT,
                    true,
                    new ServiceContext()//seems like bad, but worked when test it
            );
        } catch (PortalException e) {
            log.error(e, e);
            throw new CouldNotCreateOrganizationRuntimeException(
                    ownerUserId,
                    projectsCatalogOrganization.getOrganizationId(),
                    projectName,
                    true,
                    e);
            //todo discus runtime
        }

        assignProjectSiteTemplate(newProjectOrganization);

        organizationLocalService.addUserOrganization(ownerUserId, newProjectOrganization);
        //todo reindex if problem occurs

        projectDto = new ProjectDto.Builder()
                .setProjectName(projectName)
                .setInfrastructureEntityProjectIdMap(infrastructureEntityProjectIdMap)
                .setProjectId(newProjectOrganization.getOrganizationId())
                .setMembers(members)
                .build();

        infrastructureEntityProjectLocalService.addAll(
                projectDto.getInfrastructureEntityProjectIdMap(),
                newProjectOrganization.getOrganizationId()
        );

        return projectDto;
    }

    /**
     * Creates organization of organization type Project. Also creates site
     * by template Project Template and assign to created organization.
     * Saves in database list of "infrastructure entity project id" with mapping on
     * created organization.
     *
     * @param projectDto only projectName and infrastructureEntityProjectIdMap uses
     * @param context    context of action, used for get owner userId
     * @return dto of created "project" or null, If there is a problem
     * @see #createProject(String, Map, ServiceContext)
     */
    public ProjectDto createProject(ProjectDto projectDto, ServiceContext context) {
        Map<String, String> infrastructureEntityProjectIdMap = projectDto.getInfrastructureEntityProjectIdMap();
        String projectName = projectDto.getProjectName();
        return createProject(projectName, infrastructureEntityProjectIdMap, context);
    }

    /**
     * Delete user from project organization .
     * Do nothing, if user is not in.
     *
     * @param user    who was deleted from project organization
     * @param context context of action, used for get owner userId, organizationId
     * @see #getProjectOrganization(ServiceContext)
     */

    public void deleteUser(User user, ServiceContext context) {
        Organization projectOrganization = getProjectOrganization(context);
        organizationLocalService.deleteUserOrganization(user.getUserId(), projectOrganization);
        //todo reindex if problem occurs

    }

    /**
     * Delete user from project organization .
     * Do nothing with user, if it is already in.
     *
     * @param users   who was deleted from project organization
     * @param context context of action, used for get owner userId, organizationId
     * @see #getProjectOrganization(ServiceContext)
     */
    public void deleteUsers(List<User> users, ServiceContext context) {
        Organization projectOrganization = getProjectOrganization(context);
        for (User user : users) {
            organizationLocalService.deleteUserOrganization(user.getUserId(), projectOrganization);
            //todo reindex if problem occurs
        }
    }

    /**
     * Get a project related to the current Project organization
     *
     * @param context - context of action, used for get current organization
     * @return - ProjectDto
     * @see #getProjectOrganization(ServiceContext)
     * @see #getProject(Organization)
     */
    public ProjectDto getProject(ServiceContext context) {
        Organization projectOrganization = getProjectOrganization(context);
        return getProject(projectOrganization);
    }

    /**
     * Gives a list of available for user projects in current ProjectsCatalog organization.
     *
     * @param context context of action, used for get owner userId, organizationId
     * @return list of available for projects or empty list, if no projects found
     * @see #getProjectsCatalogOrganization(ServiceContext)
     */
    public List<ProjectDto> getProjects(ServiceContext context) {
        List<ProjectDto> projectDtoList = new ArrayList<>();
        long userId = context.getUserId();

        if (userId == 0) {
            log.warn("try to get ProjectDto list for ServiceContext where userId == 0");
            return projectDtoList;
        }

        Organization projectsCatalogOrganization = getProjectsCatalogOrganization(context);
        List<Organization> projectsCatalogOrganizationSuborganizations = projectsCatalogOrganization.getSuborganizations();
        List<Organization> userOrganizations = organizationLocalService.getUserOrganizations(userId);

        userOrganizations.retainAll(projectsCatalogOrganizationSuborganizations);

        if (projectsCatalogOrganizationSuborganizations.size() != 0 && userOrganizations.size() != 0) {
            for (Organization userOrganization : userOrganizations) {
                ProjectDto projectDto = getProject(userOrganization);
                projectDtoList.add(projectDto);
            }
        }

        return projectDtoList;
    }

    /**
     * Assign Project Site Template to site bound new Project organization.
     * Site of this organization must be empty.(or not important?)
     *
     * @param newProjectOrganization new Project organization with empty site
     */
    private void assignProjectSiteTemplate(Organization newProjectOrganization) {
        LayoutSetPrototype layoutSetPrototype = null;// site template
        LayoutSet layoutSet = null;// site

        List<LayoutSetPrototype> layoutSetPrototypes =
                layoutSetPrototypeLocalService.getLayoutSetPrototypes(newProjectOrganization.getCompanyId());
        for (LayoutSetPrototype prototype : layoutSetPrototypes) {
            if (prototype.getName(Locale.ENGLISH).equalsIgnoreCase(PROJECT_SITE_TEMPLATE_NAME)) {
                layoutSetPrototype = prototype;
                break;
            }
        }

        if (layoutSetPrototype == null) {
            throw new SiteTemplateNotFoundRuntimeException(PROJECT_SITE_TEMPLATE_NAME);
            //todo discus runtime
            //todo nm that not runtime...
        }

        try {
            layoutSet = layoutSetLocalService.getLayoutSet(newProjectOrganization.getGroupId(), true);

            layoutSet.setLayoutSetPrototypeLinkEnabled(true);
            layoutSet.setLayoutSetPrototypeUuid(layoutSetPrototype.getUuid());
            layoutSetLocalService.updateLayoutSet(layoutSet);

            sitesUtil.mergeLayoutSetPrototypeLayouts(newProjectOrganization.getGroup(), layoutSet);//throws Exception
        } catch (PortalException e) {
            log.error(e, e);
            throw new CouldNotGetOrganizationSiteRuntimeException(newProjectOrganization, e);
            //todo discus runtime
        } catch (Exception e) {
            log.error(e, e);
            throw new SetSiteTemplateRuntimeException(newProjectOrganization.getGroup(), PROJECT_SITE_TEMPLATE_NAME, e);
            //todo discus runtime
        }
    }

    /**
     * Get map of infrastructure entity project id, that bound to organization, where
     * key = infrastructure entity name,
     * value = infrastructure entity project id.
     *
     * @param organizationId id of organization
     * @return map of infrastructure entity project id
     */
    private Map<String, String> getInfrastructureEntityProjectIdMap(long organizationId) {
        Map<String, String> infrastructureEntityProjectIdMap = new HashMap<>();
        List<InfrastructureEntityProject> infrastructureEntityProjects =
                infrastructureEntityProjectLocalService.get(organizationId);
        for (InfrastructureEntityProject infrastructureEntityProject : infrastructureEntityProjects) {
            String infrastructureEntityName = infrastructureEntityProject.getInfrastructureEntityName();
            String infrastructureEntityProjectId = infrastructureEntityProject.getInfrastructureEntityProjectId();
            infrastructureEntityProjectIdMap.put(infrastructureEntityName, infrastructureEntityProjectId);
        }

        return infrastructureEntityProjectIdMap;
    }

    /**
     * Get organization from ServiceContext.
     *
     * @param context context of action
     * @return current for context organization
     * @throws IsNotOrganizationRuntimeException                         if site not bound to organization
     * @throws CouldNotGetOrganizationFromServiceContextRuntimeException if other problem
     *                                                                   with get organization from context occurs
     */
    private Organization getOrganization(ServiceContext context) {
        Organization organization;

        try {
            Group group = context.getScopeGroup();//here PortalException

            if (!group.isOrganization()) {
                throw new IsNotOrganizationRuntimeException(group);
                //todo discus runtime
            }
            long organizationId = group.getOrganizationId();

            /* Not achievable PortalException below, I think */
            organization = organizationLocalService.getOrganization(organizationId);
        } catch (PortalException e) {
            throw new CouldNotGetOrganizationFromServiceContextRuntimeException(e);
            //todo discus runtime
        }
        return organization;
    }

    /**
     * Get ProjectDto bound to projectOrganization.</br>
     * ProjectDto fields: id, name, members - gets from projectOrganization;
     * infrastructureEntityProjectIdMap field got from InfrastructureEntityProjectLocalService
     *
     * @param projectOrganization need to get project fields: id, name, members
     * @return ProjectsCatalog organization
     * @throws OrganizationTypeRuntimeException if organization type name not equals "ProjectsCatalog"
     * @see #getInfrastructureEntityProjectIdMap(long)
     * @see InfrastructureEntityProjectLocalService
     */
    private ProjectDto getProject(Organization projectOrganization) {
        ProjectDto projectDto;

        long organizationId = projectOrganization.getOrganizationId();
        String projectOrganizationName = projectOrganization.getName();
        List<User> members = userLocalService.getOrganizationUsers(projectOrganization.getOrganizationId());
        Map<String, String> infrastructureEntityProjectIdMap = getInfrastructureEntityProjectIdMap(organizationId);

        projectDto = new ProjectDto.Builder()
                .setProjectId(organizationId)
                .setProjectName(projectOrganizationName)
                .setMembers(members)
                .setInfrastructureEntityProjectIdMap(infrastructureEntityProjectIdMap)
                .build();

        return projectDto;
    }

    /**
     * Get ProjectsCatalog organization
     *
     * @param context need to get organization from context
     * @return ProjectsCatalog organization
     * @throws OrganizationTypeRuntimeException if organization type name not equals "ProjectsCatalog"
     * @see #getOrganization(ServiceContext)
     */
    private Organization getProjectsCatalogOrganization(ServiceContext context) {
        Organization organization = getOrganization(context);

        if (!organization.getType().equals(ORGANIZATION_TYPE__PROJECTS_CATALOG)) {
            throw new OrganizationTypeRuntimeException(organization.getType(), ORGANIZATION_TYPE__PROJECTS_CATALOG);
            //todo discus runtime
        }
        return organization;
    }

    /**
     * Get Project organization
     *
     * @param context need to get organization from context
     * @return Project organization
     * @throws OrganizationTypeRuntimeException if organization type name not equals "Project"
     * @see #getOrganization(ServiceContext)
     */
    private Organization getProjectOrganization(ServiceContext context) {
        Organization organization = getOrganization(context);

        if (!organization.getType().equals(ORGANIZATION_TYPE__PROJECT)) {
            throw new OrganizationTypeRuntimeException(organization.getType(), ORGANIZATION_TYPE__PROJECT);
            //todo discus runtime
        }
        return organization;
    }
}