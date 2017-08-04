package org.etan.portal.integration.prototype.projectmanage.service.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import org.etan.portal.integration.prototype.infrastructureentityapi.service.InfrastructureEntity;
import org.etan.portal.integration.prototype.infrastructureentityapi.service.exception.InfrastructureEntityException;
import org.etan.portal.integration.prototype.projectcontroller.service.ProjectController;
import org.etan.portal.integration.prototype.projectcontroller.service.dto.ProjectDto;
import org.etan.portal.integration.prototype.projectmanage.service.ProjectManage;
import org.etan.portal.integration.prototype.projectmanage.service.context.ProjectManageContext;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(
        immediate = true,
        service = ProjectManage.class
)
public class ProjectManageImpl implements ProjectManage {

    // TODO implement methods
    public static final String ASSIGN_USER_ERROR = "Error occurred while assign user";
    public static final String UNASSIGN_USER_ERROR = "Error occurred while unassign user";
    public static final String CREATE_PROJECT_ERROR = "Error occurred while creating project";
    private static final Log logger = LogFactoryUtil.getLog(ProjectManageImpl.class);


    @Reference(cardinality = ReferenceCardinality.MULTIPLE, bind = "bind", unbind = "unbind",
            service = InfrastructureEntity.class, policy = ReferencePolicy.DYNAMIC)
    private List<InfrastructureEntity> infrastructureEntities;

    @Reference
    private ProjectController projectController;

    @Override
    public ProjectDto createProject(String projectName, ProjectManageContext projectManageContext) {
        Map<String, String> infrastructureEntityProjectIdMap = new HashMap<>();
        for (InfrastructureEntity infrastructureEntity : infrastructureEntities) {
            String infrastructureEntityName = infrastructureEntity.getName();
            try {
                String infrastructureEntityProjectId =
                        infrastructureEntity.createInfrastructureEntityProject(projectName);
                infrastructureEntityProjectIdMap.put(infrastructureEntityName, infrastructureEntityProjectId);
            } catch (InfrastructureEntityException e) {
                logger.info(CREATE_PROJECT_ERROR, e);
                // TODO: Rollback changes. DISCUSS IT! (Delete project can also be failed)
            }
        }
        ProjectDto project = projectController.
                createProject(projectName, infrastructureEntityProjectIdMap, projectManageContext);
        return project;
    }

    @Override
    public void assignUser(User user, ProjectManageContext projectManageContext) {
        for (InfrastructureEntity infrastructureEntity : infrastructureEntities) {
            long projectId = projectManageContext.getProjectId();
            String infrastructureEntityName = infrastructureEntity.getName();
            String infrastructureEntityProjectId = projectController.
                    getInfrastructureEntityProjectId(projectId, infrastructureEntityName);

            try {
                infrastructureEntity.assignUser(user, infrastructureEntityProjectId);
            } catch (InfrastructureEntityException e) {
                logger.info(ASSIGN_USER_ERROR, e);
                // TODO: Rollback changes. DISCUSS IT! (Unassign can also be failed)
            }
        }
    }

    @Override
    public void unassignUser(User user, ProjectManageContext projectManageContext) {
        for (InfrastructureEntity infrastructureEntity : infrastructureEntities) {
            long projectId = projectManageContext.getProjectId();
            String infrastructureEntityName = infrastructureEntity.getName();
            String infrastructureEntityProjectId = projectController.
                    getInfrastructureEntityProjectId(projectId, infrastructureEntityName);

            try {
                infrastructureEntity.assignUser(user, infrastructureEntityProjectId);
            } catch (InfrastructureEntityException e) {
                logger.info(UNASSIGN_USER_ERROR, e);
                // TODO: Rollback changes. DISCUSS IT! (Assign can also be failed)
            }
        }
    }

    /**
     * Bind service.
     * Add service in infrastructureEntities.
     *
     * @param infrastructureEntity - service to bind
     */
    protected void bind(InfrastructureEntity infrastructureEntity) {
        if (infrastructureEntities == null) {
            infrastructureEntities = new ArrayList<>();
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
        for (int i = 0; i < infrastructureEntities.size(); i++) {
            InfrastructureEntity infrastructureEntity = infrastructureEntities.get(i);
            String infrastructureEntityName = infrastructureEntity.getName();
            String infrastructureEntityRemovedName = infrastructureEntityRemoved.getName();
            if (infrastructureEntityName.equals(infrastructureEntityRemovedName)) {
                infrastructureEntities.remove(i);
            }
        }
    }
}
