package org.etan.portal.integration.projectcontroller.service.impl;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import org.etan.portal.integration.projectcontroller.service.ProjectController;
import org.etan.portal.integration.projectcontroller.service.dto.ProjectDto;
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
    public void addUser(long userId, ServiceContext context) {

    }

    @Override
    public void addUser(User user, ServiceContext context) {

    }

    @Override
    public void addUsers(long[] userIds, ServiceContext context) {

    }

    @Override
    public void addUsers(List<User> users, ServiceContext context) {

    }

    @Override
    public ProjectDto createProject(String projectName, Map<String, String> infrastructureEntityProjectIdMap, ServiceContext context) {
        return null;
    }

    @Override
    public ProjectDto createProject(ProjectDto projectDto, ServiceContext context) {
        return null;
    }

    @Override
    public void deleteUser(long userId, ServiceContext context) {

    }

    @Override
    public void deleteUser(User user, ServiceContext context) {

    }

    @Override
    public void deleteUsers(long[] userIds, ServiceContext context) {

    }

    @Override
    public void deleteUsers(List<User> users, ServiceContext context) {

    }


    @Override
    public ProjectDto getProject(ServiceContext context) {
        return null;
    }

    @Override
    public List<ProjectDto> getProjects(ServiceContext context) {
        return null;
    }


    // TODO enter required service methods
}