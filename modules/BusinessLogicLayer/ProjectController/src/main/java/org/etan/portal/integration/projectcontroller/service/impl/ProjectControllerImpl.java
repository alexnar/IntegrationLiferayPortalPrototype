package org.etan.portal.integration.projectcontroller.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.*;
import com.liferay.portal.kernel.service.*;
import org.etan.portal.integration.projectcontroller.service.ProjectController;
import org.etan.portal.integration.projectcontroller.service.dto.ProjectDto;
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
    private static final String PROJECT_SITE_TEMPLATE = "Project Template";///todo mb bad

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

    private Organization getOrganization(ServiceContext context) {
        Group group;
        long organizationId;
        Organization organization;

        try {
            group = context.getScopeGroup();//here PortalException
            if (!group.isOrganization()) {
                throw new RuntimeException("is not organization");//todo my own
                //todo discus
            }
            organizationId = group.getOrganizationId();
            organization = organizationLocalService.getOrganization(organizationId);//here PortalException
        } catch (PortalException e) {
            log.error(e, e);//todo some hero method or etc. or not
            throw new RuntimeException("Can not get Organization from ServiceContext", e);//todo my own
            //todo discus
        }
        return organization;
    }

    private Organization getProjectOrganization(ServiceContext context) {
        Organization organization = getOrganization(context);

        if (!organization.getType().equals(ORGANIZATION_TYPE__PROJECT)) {
            throw new RuntimeException("organization type must be " + ORGANIZATION_TYPE__PROJECT);//todo my own
            //todo discus
        }
        return organization;
    }

    private Organization getProjectsCatalogOrganization(ServiceContext context) {
        Organization organization = getOrganization(context);

        if (!organization.getType().equals(ORGANIZATION_TYPE__PROJECTS_CATALOG)) {
            throw new RuntimeException("organization type must be " + ORGANIZATION_TYPE__PROJECTS_CATALOG);//todo my own
            //todo discus
        }
        return organization;
    }

    /**
     * Add user to project organization.
     * Do nothing, if user is already in.
     *
     * @param user    was added to project organization
     * @param context context of action, uses for get owner userId, organizationId
     */
    public void addUser(User user, ServiceContext context) {
        Organization projectOrganization = getProjectOrganization(context);
        organizationLocalService.addUserOrganization(user.getUserId(), projectOrganization);//todo reindex if problem occurs
    }

    /**
     * Add users to project organization .
     * Do nothing with user, if it is already in.
     *
     * @param users   who was added to project organization
     * @param context context of action, uses for get owner userId, organizationId
     */
    public void addUsers(List<User> users, ServiceContext context) {
        Organization projectOrganization = getProjectOrganization(context);
        for (User user : users) {
            organizationLocalService.addUserOrganization(user.getUserId(), projectOrganization);//todo reindex if problem occurs
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
            throw new RuntimeException("can not find User owner of new project: : " + projectName, e);//todo my own
            // Should not happen
        }

        try {
            newProjectOrganization = organizationLocalService.addOrganization(
                    ownerUserId,
                    projectsCatalogOrganization.getOrganizationId(),
                    projectName,
                    true
            );
        } catch (PortalException e) {
            log.error(e, e);
            throw new RuntimeException("can not create new organization for project: " + projectName, e);//todo my own
            //todo discus
        }

        assignProjectSiteTemplate(newProjectOrganization);

        organizationLocalService.addUserOrganization(ownerUserId, newProjectOrganization);//todo reindex if problem occurs

        projectDto = new ProjectDto.Builder()
                .setProjectName(projectName)
                .setInfrastructureEntityProjectIdMap(infrastructureEntityProjectIdMap)
                .setProjectId(newProjectOrganization.getOrganizationId())
                .setMembers(members)
                .build();

        //todo Save - save me now! Save by ProjectService now!
//        infrastructureEntityProjectLocalService.add();

        return projectDto;
    }

    private void assignProjectSiteTemplate(Organization newProjectOrganization) {
        LayoutSetPrototype layoutSetPrototype = null;// site template
        LayoutSet layoutSet = null;// site

        List<LayoutSetPrototype> layoutSetPrototypes =
                layoutSetPrototypeLocalService.getLayoutSetPrototypes(newProjectOrganization.getCompanyId());
        for (LayoutSetPrototype prototype : layoutSetPrototypes) {
            if (prototype.getName(Locale.ENGLISH).equalsIgnoreCase(PROJECT_SITE_TEMPLATE)) {
                layoutSetPrototype = prototype;
                break;
            }
        }

        if (layoutSetPrototype == null) {
            throw new RuntimeException("can not find : " + PROJECT_SITE_TEMPLATE);//todo my own
            //todo discus
        }

        try {
            layoutSet = layoutSetLocalService.getLayoutSet(newProjectOrganization.getGroupId(), true);

            layoutSet.setLayoutSetPrototypeLinkEnabled(true);
            layoutSet.setLayoutSetPrototypeUuid(layoutSetPrototype.getUuid());
            layoutSetLocalService.updateLayoutSet(layoutSet);

            sitesUtil.mergeLayoutSetPrototypeLayouts(newProjectOrganization.getGroup(), layoutSet);//throws Exception
        } catch (PortalException e) {
            log.error(e, e);
            throw new RuntimeException(
                    "can not get site of new organization of project: "
                            + newProjectOrganization.getName(),
                    e);//todo my own
            //todo discus
        } catch (Exception e) {
            log.error(e, e);
            throw new RuntimeException(
                    "can not set site template to site of new organization of project: "
                            + newProjectOrganization.getName(),
                    e);//todo my own
            //todo discus
        }
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
     */

    public void deleteUser(User user, ServiceContext context) {
        Organization projectOrganization = getProjectOrganization(context);
        organizationLocalService.deleteUserOrganization(user.getUserId(), projectOrganization);//todo reindex if problem occurs

    }

    /**
     * Delete user from project organization .
     * Do nothing with user, if it is already in.
     *
     * @param users   who was deleted from project organization
     * @param context context of action, used for get owner userId, organizationId
     */
    public void deleteUsers(List<User> users, ServiceContext context) {
        Organization projectOrganization = getProjectOrganization(context);
        for (User user : users) {
            organizationLocalService.deleteUserOrganization(user.getUserId(), projectOrganization);//todo reindex if problem occurs
        }
    }

    /**
     * Get a project related to the current Project organization
     *
     * @param context - context of action, used for get current organizationId
     * @return - ProjectDto
     */
    //todo mb runtime, when invoked not in Project organization or etc.
    public ProjectDto getProject(ServiceContext context) {
        Organization projectOrganization = getProjectOrganization(context);
        return getProject(projectOrganization);
    }

    private ProjectDto getProject(Organization projectOrganization) {
        ProjectDto projectDto;

        long id = projectOrganization.getOrganizationId();
        String name = projectOrganization.getName();
        List<User> members = userLocalService.getOrganizationUsers(projectOrganization.getOrganizationId());
        Map<String, String> infrastructureEntityProjectIdMap = new HashMap<>();//todo get from ProjectService

        projectDto = new ProjectDto.Builder()
                .setProjectId(id)
                .setProjectName(name)
                .setMembers(members)
                .setInfrastructureEntityProjectIdMap(infrastructureEntityProjectIdMap)
                .build();

        return projectDto;
    }

    /**
     * Gives a list of available for user projects in current ProjectsCatalog organization.
     *
     * @param context context of action, used for get owner userId, organizationId
     * @return list of available for projects or empty list, if no projects found
     */
    //todo mb runtime, when invoked not in ProjectsCatalog organization or etc.
    public List<ProjectDto> getProjects(ServiceContext context) {
        List<ProjectDto> projectDtoList = new ArrayList<>();
        long userId = context.getUserId();
        User user;
        List<Organization> userOrganizations;
        try {
            user = userLocalService.getUser(userId);
        } catch (PortalException e) {
            log.error(e, e);
            throw new RuntimeException("can not get User, which try to get his projects list", e);//todo my own
            //todo discus
        }

        Organization projectsCatalogOrganization = getProjectsCatalogOrganization(context);
        List<Organization> projectsCatalogOrganizationSuborganizations = projectsCatalogOrganization.getSuborganizations();
        userOrganizations = organizationLocalService.getUserOrganizations(user.getUserId());

        userOrganizations.retainAll(projectsCatalogOrganizationSuborganizations);

        if (projectsCatalogOrganizationSuborganizations.size() != 0 && userOrganizations.size() != 0) {
            for (Organization userOrganization : userOrganizations) {
                ProjectDto projectDto = getProject(userOrganization);
                projectDtoList.add(projectDto);
            }
        }

        return projectDtoList;
    }
}