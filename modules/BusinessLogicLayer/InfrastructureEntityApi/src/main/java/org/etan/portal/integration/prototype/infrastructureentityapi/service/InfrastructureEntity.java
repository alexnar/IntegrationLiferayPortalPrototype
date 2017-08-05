package org.etan.portal.integration.prototype.infrastructureentityapi.service;

import com.liferay.portal.kernel.model.User;
import org.etan.portal.integration.prototype.infrastructureentityapi.service.exception.InfrastructureEntityException;

/**
 * Infrastructure entity api.
 * Provide methods, which are characteristic to
 * any infrastructure entity (For example: in any
 * infrastructure entity we can  create project in
 * infrastructure entity or assign user to it).
 * <p>
 * Infrastructure entity - is the system that is
 * integrated in our portal.
 * <p>
 * Every Infrastructure entity must implement
 * this interface.
 *
 * @author Naryzhny Alex
 */
public interface InfrastructureEntity {

    /**
     * Create project in infrastructure entity.
     *
     * @param projectName - name of project to create.
     * @return - String Id of project in infrastructure entity.
     * @throws InfrastructureEntityException - throws if some exception happened while
     *                                       creating project
     */
    String createInfrastructureEntityProject(String projectName) throws InfrastructureEntityException;

    /**
     * Assign user to infrastructure entity.
     *
     * @param user                          -  user to assign
     * @param infrastructureEntityProjectId - String id of infrastructure entity project
     *                                      where user would be assigned.
     * @throws InfrastructureEntityException - throws if some exception happened while
     *                                       assign user
     */
    void assignUser(User user, String infrastructureEntityProjectId) throws InfrastructureEntityException;

    /**
     * Unassign user from infrastructure entity.
     *
     * @param user                          - user to unassign
     * @param infrastructureEntityProjectId - String id of infrastructure entity project
     *                                      where user would be unassigned.
     * @throws InfrastructureEntityException - throws if some exception happened while
     *                                       unassign user
     */
    void unassignUser(User user, String infrastructureEntityProjectId) throws InfrastructureEntityException;

    /**
     * Get name of infrastructure entity.
     *
     * @return - name of infrastructure entity.
     */
    String getName();
}