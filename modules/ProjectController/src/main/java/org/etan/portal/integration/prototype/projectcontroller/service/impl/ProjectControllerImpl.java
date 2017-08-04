package org.etan.portal.integration.prototype.projectcontroller.service.impl;

import com.liferay.portal.kernel.model.User;
import org.etan.portal.integration.prototype.projectcontroller.service.ProjectController;
import org.etan.portal.integration.prototype.projectcontroller.service.dto.ProjectDto;
import org.etan.portal.integration.prototype.projectmanage.service.context.ProjectManageContext;
import org.osgi.service.component.annotations.Component;

import java.util.List;
import java.util.Map;

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
    @Override
    public void addUser(long userId, ProjectManageContext context) {

    }

    @Override
    public void addUser(User user, ProjectManageContext context) {

    }

    @Override
    public void addUsers(long[] userIds, ProjectManageContext context) {

    }

    @Override
    public void addUsers(List<User> users, ProjectManageContext context) {

    }

    @Override
    public ProjectDto createProject(String projectName, Map<String, String> infrastructureEntityProjectIdMap,
                                    ProjectManageContext context) {
        return null;
    }

    @Override
    public ProjectDto createProject(ProjectDto projectDto, ProjectManageContext context) {
        return null;
    }

    @Override
    public void deleteUser(long userId, ProjectManageContext context) {

    }

    @Override
    public void deleteUser(User user, ProjectManageContext context) {

    }

    @Override
    public void deleteUsers(long[] userIds, ProjectManageContext context) {

    }

    @Override
    public void deleteUsers(List<User> users, ProjectManageContext context) {

    }

    @Override
    public String getInfrastructureEntityProjectId(long projectId, String infrastructureEntityName) {
        return null;
    }

    @Override
    public List<ProjectDto> getProjects(ProjectManageContext context) {
        return null;
    }

    // TODO enter required service methods
}