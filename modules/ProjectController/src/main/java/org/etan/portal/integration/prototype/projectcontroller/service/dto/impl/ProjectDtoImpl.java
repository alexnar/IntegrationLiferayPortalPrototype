package org.etan.portal.integration.prototype.projectcontroller.service.dto.impl;

import com.liferay.portal.kernel.model.User;
import org.etan.portal.integration.prototype.projectcontroller.service.dto.ProjectDto;

import java.util.List;
import java.util.Map;

/**
 * @author Efimov Timur
 * @version 1.0.1
 */
public class ProjectDtoImpl implements ProjectDto {
    private Map<String, Long> infrastructureEntityProjectIdMap;
    private String projectName;
    private List<User> members;


    @Override
    public Map<String, Long> getInfrastructureEntityProjectIdMap() {
        return infrastructureEntityProjectIdMap;
    }

    @Override
    public void setInfrastructureEntityProjectIdMap(Map<String, Long> infrastructureEntityProjectIdMap) {
        this.infrastructureEntityProjectIdMap = infrastructureEntityProjectIdMap;
    }

    @Override
    public String getProjectName() {
        return projectName;
    }

    @Override
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }
}
