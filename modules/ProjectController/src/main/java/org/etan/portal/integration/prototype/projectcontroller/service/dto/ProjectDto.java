package org.etan.portal.integration.prototype.projectcontroller.service.dto;


import com.liferay.portal.kernel.model.User;

import java.util.List;
import java.util.Map;

/**
 * Project dto
 *
 * @author Efimov Timur
 * @version 1.0.1
 */
public interface ProjectDto {

    /**
     * Gives map, where
     * key = infrastructure entity name,
     * value = infrastructure entity project id
     *
     * @return map pf external systems projects ids
     */
    Map<String, String> getInfrastructureEntityProjectIdMap();

    /**
     * Set map, where
     * key = infrastructure entity name,
     * value = infrastructure entity project id
     *
     * @param infrastructureEntityProjectIdMap map of infrastructure entity project id
     */
    void setInfrastructureEntityProjectIdMap(Map<String, String> infrastructureEntityProjectIdMap);

    /**
     * Gives project id
     *
     * @return project id
     */
    long getProjectId();

    /**
     * Set project id
     *
     * @param projectId id of project
     */
    void setProjectId(long projectId);

    /**
     * Gives project name
     *
     * @return project name
     */
    String getProjectName();

    /**
     * Set project name
     *
     * @param projectName name of project
     */
    void setProjectName(String projectName);

    /**
     * Gives project members
     *
     * @return project members
     */
    List<User> getMembers();

    /**
     * Set project members.
     *
     * @param members project members
     */
    void setMembers(List<User> members);
}
