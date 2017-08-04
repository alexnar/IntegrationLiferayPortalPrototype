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
public class ProjectDto {
    private Map<String, String> infrastructureEntityProjectIdMap;
    private String projectName;
    private long projectId;
    private List<User> members;


    private ProjectDto(Builder builder) {

    }

    /**
     * Gives map, where
     * key = infrastructure entity name,
     * value = infrastructure entity project id
     *
     * @return map pf external systems projects ids
     */
    public Map<String, String> getInfrastructureEntityProjectIdMap() {
        return infrastructureEntityProjectIdMap;
    }

    /**
     * Gives project id
     *
     * @return project id
     */
    public long getProjectId() {
        return projectId;
    }

    /**
     * Gives project name
     *
     * @return project name
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Gives project members
     *
     * @return project members
     */
    public List<User> getMembers() {
        return members;
    }

    public static class Builder {
        private Map<String, String> infrastructureEntityProjectIdMap;
        private String projectName;
        private long projectId;
        private List<User> members;

        /**
         * Set map, where
         * key = infrastructure entity name,
         * value = infrastructure entity project id
         *
         * @param infrastructureEntityProjectIdMap map of infrastructure entity project id
         */
        public void setInfrastructureEntityProjectIdMap(Map<String, String> infrastructureEntityProjectIdMap) {
            this.infrastructureEntityProjectIdMap = infrastructureEntityProjectIdMap;
        }

        /**
         * Set project id
         *
         * @param projectId id of project
         */
        public void setProjectId(long projectId) {
            this.projectId = projectId;
        }

        /**
         * Set project name
         *
         * @param projectName name of project
         */
        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        /**
         * Set project members.
         *
         * @param members project members
         */
        public void setMembers(List<User> members) {
            this.members = members;
        }

        public ProjectDto build() {
            return new ProjectDto(this);
        }

    }
}
