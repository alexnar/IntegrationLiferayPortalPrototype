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
     * key = name of external system,
     * value = external system project id
     *
     * @return map pf external systems projects ids
     */
    Map<String, Long> getInternalProjectsIdMap();

    /**
     * Set map, where
     * key = name of external system,
     * value = external system project id
     *
     * @param internalProjectsIdMap map pf external systems projects ids
     */
    void setInternalProjectsIdMap(Map<String, Long> internalProjectsIdMap);

    /**
     * Gives project name
     *
     * @return project name
     */
    String getProjectName();

    /**
     * set project name
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
