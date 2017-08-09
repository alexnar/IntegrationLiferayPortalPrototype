package org.etan.portal.integration.projectcontroller.service.impl;

import com.liferay.portal.kernel.exception.NoSuchOrganizationException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.*;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.*;
import org.etan.portal.integration.projectcontroller.service.IllegalServiceContextException;
import org.etan.portal.integration.projectcontroller.service.ProjectController;
import org.etan.portal.integration.projectcontroller.service.SiteTemplateNotFoundException;
import org.etan.portal.integration.projectcontroller.service.dto.ProjectDto;
import org.etan.portal.integration.projectservice.model.InfrastructureEntityProject;
import org.etan.portal.integration.projectservice.service.InfrastructureEntityProjectLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.*;

/**
 * Responsible for creating an organization by organization type "Project"
 * and its site, adding / removing users to it, getting
 * a list of "projects" of current "ProjectCatalog" organization.
 *
 * @author Efimov Timur
 * @version 1.0.1
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

    private static final String ORGANIZATION_COMMENT =
            "Project organization uses for project's manipulations";
    private static final String ORGANIZATION_TYPE__PROJECT = "Project";
    private static final String ORGANIZATION_TYPE__PROJECTS_CATALOG = "ProjectsCatalog";
    private static final String PROJECT_SITE_TEMPLATE_PRIVATE =
            "Project Template - private";
//TODO - make osgi config for site template name and etc.

    @Reference
    private volatile GroupLocalService groupLocalService;
    @Reference
    private volatile InfrastructureEntityProjectLocalService
            infrastructureEntityProjectLocalService;
    @Reference
    private volatile LayoutLocalService layoutLocalService;
    @Reference
    private volatile LayoutPrototypeLocalService layoutPrototypeLocalService;
    @Reference
    private volatile LayoutSetPrototypeLocalService layoutSetPrototypeLocalService;
    @Reference
    private volatile LayoutSetLocalService layoutSetLocalService;
    @Reference
    private volatile OrganizationLocalService organizationLocalService;
    @Reference
    private volatile com.liferay.sites.kernel.util.Sites sitesUtil;
    @Reference
    private volatile UserLocalService userLocalService;


    /**
     * Check opportunity for creating
     * project in portal.
     *
     * @param projectName    - name of project
     * @param serviceContext context of action, uses for get userId, organizationId
     * @return true, if there is opportunity to create
     */
    public boolean checkCreateProjectOpportunity(String projectName, ServiceContext serviceContext) {
        boolean hasOpportunity;

        try {
            Organization o = organizationLocalService.getOrganization(serviceContext.getCompanyId(), projectName);
            hasOpportunity = (o == null);
        } catch (PortalException e) {
            boolean instanceofNoSuchOrganizationException = (e instanceof NoSuchOrganizationException);
            if (!instanceofNoSuchOrganizationException) {
                log.error(e, e);
            }
            hasOpportunity = false;
        }

        return hasOpportunity;
    }

    /**
     * Check ServiceContext.
     * It must have not zero(null) field userId, Group, OrganizationId...
     *
     * @param context ServiceContext
     * @return true, if it is right ServiceContext
     */
    public boolean checkServiceContext(ServiceContext context) {
        boolean checkFailed = false;

        try {
            checkFailed = context == null
                    || context.getUserId() <= 0
                    || context.getScopeGroup() == null
                    || !context.getScopeGroup().isOrganization()
                    || context.getScopeGroup().getOrganizationId() == 0;
        } catch (PortalException e) {
            checkFailed = true;
            log.warn(e.getMessage());
        }

        return checkFailed;
    }

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
     * @see #getProjectOrganization(ServiceContext)
     */
    public void addUser(User user, ServiceContext context) {
        Organization projectOrganization = getProjectOrganization(context);
        organizationLocalService.addUserOrganization(
                user.getUserId(), projectOrganization);
        reindex(user);
    }

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
     * @see #getProjectOrganization(ServiceContext)
     */
    public void addUsers(List<User> users, ServiceContext context) {
        Organization projectOrganization = getProjectOrganization(context);
        for (User user : users) {
            organizationLocalService.addUserOrganization(
                    user.getUserId(), projectOrganization);
            reindex(user);//todo some performance issue? )
        }
    }


    /**
     * Creates organization of organization type Project. Also creates site
     * by template Project Template and assign it to created organization.
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
     * @see #getProjectsCatalogOrganization(ServiceContext)
     * @see #assignProjectSiteTemplate(Organization)
     */
    public ProjectDto createProject(
            String projectName,
            Map<String, String> infrastructureEntityProjectIdMap,
            ServiceContext serviceContext) throws PortalException {
        ProjectDto projectDto;
        Organization newProjectOrganization;
        List<User> members = new ArrayList<>();

        if (projectName == null) {
            throw new IllegalArgumentException("projectName can not be null");
            //todo todo discus runtime - this is temporary solution
        }
        if (serviceContext == null) {
            throw new IllegalServiceContextException("serviceContext can not be null");
            //todo todo discus runtime - this is temporary solution
        }

        Organization projectsCatalogOrganization =
                getProjectsCatalogOrganization(serviceContext);
        long parentOrganizationId = projectsCatalogOrganization.getOrganizationId();
        long ownerUserId = serviceContext.getUserId();

        if (ownerUserId == 0) {
            throw new IllegalServiceContextException(
                    "userId from ServiceContext equals 0");
            //todo todo discus runtime - this is temporary solution
        }

        User owner = userLocalService.getUser(ownerUserId);
        members.add(owner);

        newProjectOrganization = organizationLocalService.addOrganization(
                ownerUserId,
                parentOrganizationId,
                projectName,
                ORGANIZATION_TYPE__PROJECT,
                0,
                0,
                ListTypeConstants.ORGANIZATION_STATUS_DEFAULT,
                ORGANIZATION_COMMENT,
                true,
                null
        );

        assignProjectSiteTemplate(newProjectOrganization);

        organizationLocalService.addUserOrganization(
                ownerUserId, newProjectOrganization);
        reindex(owner);

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
     * Creates organization by delegation data
     * from projectDto to overload method
     *
     * @param projectDto only projectName and infrastructureEntityProjectIdMap uses
     * @param context    context of action, used for get owner userId
     * @return dto of created "project"
     * @see #createProject(String, Map, ServiceContext)
     */
    public ProjectDto createProject(ProjectDto projectDto,
                                    ServiceContext context) throws PortalException {
        Map<String, String> infrastructureEntityProjectIdMap =
                projectDto.getInfrastructureEntityProjectIdMap();
        String projectName = projectDto.getProjectName();
        return createProject(projectName, infrastructureEntityProjectIdMap, context);
    }

    /**
     * Delete user from project organization.
     * Do nothing, if user is not in.
     *
     * @param user    who was deleted from project organization
     * @param context context of action, used for get organizationId
     * @see #getProjectOrganization(ServiceContext)
     */
    public void deleteUser(User user, ServiceContext context) {
        Organization projectOrganization = getProjectOrganization(context);
        organizationLocalService.deleteUserOrganization(
                user.getUserId(), projectOrganization);
        reindex(user);

    }

    /**
     * Delete user from project organization.
     * Do nothing with user, if it is already in.
     *
     * @param users   who was deleted from project organization
     * @param context context of action, used for get organizationId
     * @see #getProjectOrganization(ServiceContext)
     */
    public void deleteUsers(List<User> users, ServiceContext context) {
        Organization projectOrganization = getProjectOrganization(context);
        for (User user : users) {
            organizationLocalService.deleteUserOrganization(
                    user.getUserId(), projectOrganization);
            reindex(user);//todo some performance issue? )
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
     * Gives a list of available for user projects in current ProjectsCatalog
     * organization.
     *
     * @param context context of action, used for get owner userId, organizationId
     * @return list of available for projects or empty list, if no projects found
     * @see #getProjectsCatalogOrganization(ServiceContext)
     */
    public List<ProjectDto> getProjects(ServiceContext context) {
        List<ProjectDto> projectDtoList = new ArrayList<>();
        long userId = context.getUserId();

        if (userId == 0) {
            String msg =
                    "try to get ProjectDto list for ServiceContext where userId == 0";
            log.warn(msg);
            return projectDtoList;
        }

        Organization projectsCatalogOrganization =
                getProjectsCatalogOrganization(context);
        List<Organization> projectsCatalogOrganizationSuborganizations =
                projectsCatalogOrganization.getSuborganizations();
        List<Organization> userOrganizations =
                organizationLocalService.getUserOrganizations(userId);

        userOrganizations.retainAll(projectsCatalogOrganizationSuborganizations);

        if (projectsCatalogOrganizationSuborganizations.size() != 0
                && userOrganizations.size() != 0) {
            for (Organization userOrganization : userOrganizations) {
                ProjectDto projectDto = getProject(userOrganization);
                projectDtoList.add(projectDto);
            }
        }

        return projectDtoList;
    }

    /**
     * Assign Project Site Template to site bound new Project organization.
     * Organization must have site. Site of this organization must be empty.
     * (or not important?)
     *
     * @param newProjectOrganization new Project organization with empty site
     * @throws SiteTemplateNotFoundException if Project site template not found
     */
    private void assignProjectSiteTemplate(
            Organization newProjectOrganization) throws PortalException {
        LayoutSetPrototype layoutSetPrototype = null;// site template
        LayoutSet layoutSet = null;// site

        List<LayoutSetPrototype> layoutSetPrototypes =
                layoutSetPrototypeLocalService.getLayoutSetPrototypes(
                        newProjectOrganization.getCompanyId());
        for (LayoutSetPrototype prototype : layoutSetPrototypes) {
            if (prototype.getName(Locale.ENGLISH)
                    .equalsIgnoreCase(PROJECT_SITE_TEMPLATE_PRIVATE)) {
                layoutSetPrototype = prototype;
                break;
            }
        }

        if (layoutSetPrototype == null) {
            throw new SiteTemplateNotFoundException(PROJECT_SITE_TEMPLATE_PRIVATE);
            //todo todo discus runtime - this is temporary solution
            //todo nm that not runtime...
        }

        layoutSet = layoutSetLocalService.getLayoutSet(
                newProjectOrganization.getGroupId(), true);

        layoutSet.setLayoutSetPrototypeLinkEnabled(true);
        layoutSet.setLayoutSetPrototypeUuid(layoutSetPrototype.getUuid());
        layoutSetLocalService.updateLayoutSet(layoutSet);

        try {
            sitesUtil.mergeLayoutSetPrototypeLayouts(
                    newProjectOrganization.getGroup(), layoutSet);
        } catch (Exception e) {
            String msg = "While bind "
                    + PROJECT_SITE_TEMPLATE_PRIVATE + " to "
                    + newProjectOrganization.getGroup();
            throw new PortalException(msg, e);
        }
    }

    /**
     * Get map of infrastructure entity project id, that bound to organization:
     * key = infrastructure entity name,
     * value = infrastructure entity project id.
     *
     * @param organizationId id of organization
     * @return map of infrastructure entity project id
     */
    private Map<String, String> getInfrastructureEntityProjectIdMap(
            long organizationId) {
        Map<String, String> infrastructureEntityProjectIdMap = new HashMap<>();
        List<InfrastructureEntityProject> infrastructureEntityProjects =
                infrastructureEntityProjectLocalService.get(organizationId);
        for (InfrastructureEntityProject infrastructureEntityProject
                : infrastructureEntityProjects) {
            String infrastructureEntityName =
                    infrastructureEntityProject.getInfrastructureEntityName();
            String infrastructureEntityProjectId =
                    infrastructureEntityProject.getInfrastructureEntityProjectId();
            infrastructureEntityProjectIdMap.put(
                    infrastructureEntityName, infrastructureEntityProjectId);
        }

        return infrastructureEntityProjectIdMap;
    }

    /**
     * Get organization from ServiceContext.
     *
     * @param serviceContext context of action
     * @return current for context organization
     * @throws IllegalServiceContextException if site from serviceContext
     *                                        not assign to organization or other
     *                                        problem with get organization
     *                                        from serviceContext occurs
     */
    private Organization getOrganization(ServiceContext serviceContext) {
        Organization organization;

        try {
            Group group = serviceContext.getScopeGroup();//here PortalException

            if (!group.isOrganization()) {
                throw new IllegalServiceContextException(
                        "Site with name: " + group.getName()
                                + "is not assign to organization");
                //todo todo discus runtime - this is temporary solution
            }
            long organizationId = group.getOrganizationId();

            /* Not achievable PortalException below, I think */
            organization = organizationLocalService.getOrganization(organizationId);
        } catch (PortalException e) {
            throw new IllegalServiceContextException(
                    "Could not get organization from ServiceContext", e);
            //todo todo discus runtime - this is temporary solution
        }
        return organization;
    }

    /**
     * Get ProjectDto bound to projectOrganization.</br>
     * ProjectDto fields: id, name, members - gets from projectOrganization;
     * infrastructureEntityProjectIdMap field gets by
     * InfrastructureEntityProjectLocalService
     *
     * @param projectOrganization need to get project fields: id, name, members
     * @return ProjectsCatalog organization
     * @see #getInfrastructureEntityProjectIdMap(long)
     * @see InfrastructureEntityProjectLocalService
     */
    private ProjectDto getProject(Organization projectOrganization) {
        ProjectDto projectDto;

        long organizationId = projectOrganization.getOrganizationId();
        String projectOrganizationName = projectOrganization.getName();
        List<User> members = userLocalService.getOrganizationUsers(
                projectOrganization.getOrganizationId());
        Map<String, String> infrastructureEntityProjectIdMap =
                getInfrastructureEntityProjectIdMap(organizationId);

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
     * @throws IllegalServiceContextException if organization type name
     *                                        not equals "ProjectsCatalog"
     * @see #getOrganization(ServiceContext)
     */
    private Organization getProjectsCatalogOrganization(ServiceContext context) {
        Organization organization = getOrganization(context);

        if (!organization.getType().equals(ORGANIZATION_TYPE__PROJECTS_CATALOG)) {
            throw new IllegalServiceContextException(
                    "invalid organization type, found: " + organization.getType()
                            + ", expected: " + ORGANIZATION_TYPE__PROJECTS_CATALOG);
            //todo todo discus runtime - this is temporary solution
        }
        return organization;
    }

    /**
     * Get Project organization
     *
     * @param context need to get organization from context
     * @return Project organization
     * @throws IllegalServiceContextException if organization type name
     *                                        not equals "Project"
     * @see #getOrganization(ServiceContext)
     */
    private Organization getProjectOrganization(ServiceContext context) {
        Organization organization = getOrganization(context);

        if (!organization.getType().equals(ORGANIZATION_TYPE__PROJECT)) {
            throw new IllegalServiceContextException(
                    "invalid organization type, found: " + organization.getType()
                            + ", expected: " + ORGANIZATION_TYPE__PROJECT);
            //todo todo discus runtime - this is temporary solution
        }
        return organization;
    }

    /**
     * Used to ensure that information about the user
     * is updated in all places immediately
     *
     * @param user user to reindex
     */
    private void reindex(User user) {
        Indexer indexer = IndexerRegistryUtil.getIndexer(User.class);
        try {
            indexer.reindex(user);// not important
        } catch (SearchException e) {
            log.error(e, e);//unreachable, i think
        }
    }
}