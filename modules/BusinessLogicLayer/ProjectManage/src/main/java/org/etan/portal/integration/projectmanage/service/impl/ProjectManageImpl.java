package org.etan.portal.integration.projectmanage.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import org.etan.portal.integration.infrastructureentityapi.service.InfrastructureEntity;
import org.etan.portal.integration.infrastructureentityapi.service.exception.InfrastructureEntityException;
import org.etan.portal.integration.projectcontroller.service.ProjectController;
import org.etan.portal.integration.projectcontroller.service.dto.ProjectDto;
import org.etan.portal.integration.projectmanage.service.ProjectManage;
import org.etan.portal.integration.projectmanage.service.dto.ProjectManageSummary;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Component(
        immediate = true,
        service = ProjectManage.class
)
public class ProjectManageImpl implements ProjectManage {

    private static final String ASSIGN_USER_ERROR = "Error occurred while assign user";
    private static final String UNASSIGN_USER_ERROR = "Error occurred while unassign user";
    private static final String CREATE_PROJECT_ERROR = "Error occurred while creating project";
    private static final Log logger = LogFactoryUtil.getLog(ProjectManageImpl.class);
    @Reference
    UserLocalService userLocalService;
    @Reference(cardinality = ReferenceCardinality.MULTIPLE, bind = "bind", unbind = "unbind",
            service = InfrastructureEntity.class, policy = ReferencePolicy.DYNAMIC)
    private volatile Collection<InfrastructureEntity> infrastructureEntities;
    @Reference
    private ProjectController projectController;

    @Override
    public ProjectDto createProject(String projectName, ServiceContext serviceContext) {
        Map<String, String> infrastructureEntityProjectIdMap = new HashMap<>();
        long userId = serviceContext.getUserId();
        User user = null;
        try {
            user = userLocalService.getUser(userId);
        } catch (PortalException e) {
            logger.info(e.getMessage(), e);
        }
        for (InfrastructureEntity infrastructureEntity : infrastructureEntities) {
            String infrastructureEntityName = infrastructureEntity.getName();
            try {
                String infrastructureEntityProjectId =
                        infrastructureEntity.createInfrastructureEntityProject(projectName);
                infrastructureEntityProjectIdMap.put(infrastructureEntityName, infrastructureEntityProjectId);
                infrastructureEntity.assignUser(user, infrastructureEntityProjectId);
            } catch (InfrastructureEntityException e) {
                logger.info(CREATE_PROJECT_ERROR, e);
            }
        }
        ProjectDto project = null;
        try {
            project = projectController.
                    createProject(projectName, infrastructureEntityProjectIdMap, serviceContext);
        } catch (PortalException e) {
            logger.error(e.getMessage(), e);
        }
        return project;
    }

    @Override
    public void assignUser(User user, ServiceContext serviceContext) {
        ProjectDto projectDto = projectController.getProject(serviceContext);
        long projectId = projectDto.getProjectId();
        Map<String, String> infrastructureEntityProjectIdMap = projectDto.getInfrastructureEntityProjectIdMap();
        for (InfrastructureEntity infrastructureEntity : infrastructureEntities) {
            String infrastructureEntityName = infrastructureEntity.getName();
            String infrastructureEntityProjectId = infrastructureEntityProjectIdMap.get(infrastructureEntityName);

            try {
                infrastructureEntity.assignUser(user, infrastructureEntityProjectId);
            } catch (InfrastructureEntityException e) {
                logger.info(ASSIGN_USER_ERROR, e);
            }
        }
        projectController.addUser(user, serviceContext);
    }

    @Override
    public void unassignUser(User user, ServiceContext serviceContext) {
        ProjectDto projectDto = projectController.getProject(serviceContext);
        long projectId = projectDto.getProjectId();
        Map<String, String> infrastructureEntityProjectIdMap = projectDto.getInfrastructureEntityProjectIdMap();
        for (InfrastructureEntity infrastructureEntity : infrastructureEntities) {
            String infrastructureEntityName = infrastructureEntity.getName();
            String infrastructureEntityProjectId = infrastructureEntityProjectIdMap.get(infrastructureEntityName);

            try {
                infrastructureEntity.unassignUser(user, infrastructureEntityProjectId);
            } catch (InfrastructureEntityException e) {
                logger.info(UNASSIGN_USER_ERROR, e);
            }
        }

        projectController.deleteUser(user, serviceContext);
    }

    @Override
    public ProjectManageSummary checkCreateProjectOpportunity(String projectName, ServiceContext serviceContext) {
        Map<String, Boolean> summaryMap = new HashMap<>();
        for (InfrastructureEntity infrastructureEntity : infrastructureEntities) {
            String infrastructureEntityName = infrastructureEntity.getName();
            boolean hasOpportunity = infrastructureEntity.
                    checkCreateInfrastructureEntityProjectOpportunity(projectName);
            summaryMap.put(infrastructureEntityName, hasOpportunity);

        }
        ProjectManageSummary projectManageSummary = new ProjectManageSummary(summaryMap);
        return projectManageSummary;
    }

    /**
     * Bind service.
     * Add service in infrastructureEntities.
     *
     * @param infrastructureEntity - service to bind
     */
    protected void bind(InfrastructureEntity infrastructureEntity) {
        if (infrastructureEntities == null) {
            infrastructureEntities = new HashSet<>();
        }
        infrastructureEntities.add(infrastructureEntity);
    }

    /**
     * Unbind service.
     * Remove service from infrastructureEntities
     *
     * @param infrastructureEntityRemoved - service to remove
     */
    protected void unbind(InfrastructureEntity infrastructureEntityRemoved) {
        InfrastructureEntity forDelete = null;
        for (InfrastructureEntity infrastructureEntity : infrastructureEntities) {
            if (infrastructureEntityRemoved.getName()
                    .equals(infrastructureEntity.getName())) {
                forDelete = infrastructureEntity;
                break;
            }
        }
        infrastructureEntities.remove(forDelete);
    }
}
