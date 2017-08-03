package org.etan.portal.integration.prototype.projectmanage.service.impl;


import org.etan.portal.integration.prototype.projectmanage.service.ProjectManage;
import org.etan.portal.integration.prototype.projectmanage.service.context.ProjectManageContext;
import org.osgi.service.component.annotations.Component;

@Component(
        immediate = true,
        service = ProjectManage.class
)
public class ProjectManageImpl implements ProjectManage {

    // TODO implement methods

    @Override
    public void createProject(String projectName, ProjectManageContext projectManageContext) {

    }

    @Override
    public void assignUser(long userId, ProjectManageContext projectManageContext) {

    }

    @Override
    public void unassignUser(long userId, ProjectManageContext projectManageContext) {

    }
}
