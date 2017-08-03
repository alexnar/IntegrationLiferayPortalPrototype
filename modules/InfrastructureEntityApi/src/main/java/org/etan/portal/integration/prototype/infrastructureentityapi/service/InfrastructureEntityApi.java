package org.etan.portal.integration.prototype.infrastructureentityapi.service;

/**
 * Infrastructure entity api.
 * Provide methods, which are characteristic to
 * any infrastructure entity (For example: in any
 * infrastructure entity we can  create project in
 * infrastructure entity or assign user to it).
 *
 * Infrastructure entity - is the system that is
 * integrated in our portal.
 *
 * Every Infrastructure entity must implement
 * this interface.
 *
 * @author Naryzhny Alex
 */
public interface InfrastructureEntityApi {

    /**
     * Create project in infrastructure entity.
     *
     * @param projectName - name of project to create.
     *
     * @return - String Id of project in infrastructure entity.
     */
    String createInfrastructureEntityProject(String projectName);

    /**
     * Assign user to infrastructure entity.
     *
     * @param userId - id of user to assign
     * @param infrastructureEntityProjectId - String id of infrastructure entity project
     *                                        where user would be assigned.
     */
    void assignUser(long userId, String infrastructureEntityProjectId);

    /**
     * Unassign user from infrastructure entity.
     *
     * @param userId - id of user to unassign
     * @param infrastructureEntityProjectId - String id of infrastructure entity project
     *                                        where user would be unassigned.
     */
    void unassignUser(long userId, String infrastructureEntityProjectId);
}