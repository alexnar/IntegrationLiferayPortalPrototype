package org.etan.portal.integration.prototype.projectmanage.service.impl;


import org.etan.portal.integration.prototype.infrastructureentityapi.service.InfrastructureEntity;
import org.etan.portal.integration.prototype.projectmanage.service.ProjectManage;
import org.etan.portal.integration.prototype.projectmanage.service.context.ProjectManageContext;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

import java.util.ArrayList;
import java.util.List;

@Component(
        immediate = true,
        service = ProjectManage.class
)
public class ProjectManageImpl implements ProjectManage {

    // TODO implement methods

    @Reference(cardinality = ReferenceCardinality.MULTIPLE, bind = "bind", unbind = "unbind",
            service = InfrastructureEntity.class, policy = ReferencePolicy.DYNAMIC)
    private List<InfrastructureEntity> infrastructureEntities;

    @Override
    public void createProject(String projectName, ProjectManageContext projectManageContext) {

    }

    @Override
    public void assignUser(long userId, ProjectManageContext projectManageContext) {

    }

    @Override
    public void unassignUser(long userId, ProjectManageContext projectManageContext) {

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
