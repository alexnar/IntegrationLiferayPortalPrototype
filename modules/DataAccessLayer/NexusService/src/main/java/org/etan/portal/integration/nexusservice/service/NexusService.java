package org.etan.portal.integration.nexusservice.service;

import org.etan.portal.integration.nexusservice.service.exception.NexusException;

import java.util.List;

/**
 * Nexus service interface define methods for
 * for accessing and working with Nexus API.
 * <p>
 * Note: in nexus repository id of any entities
 * have a String type.
 *
 * @author Naryzhny Alex
 */
public interface NexusService {

    /**
     * Create Nexus repository
     *
     * @param repositoryName - name of repository to create
     * @return - - String id of Nexus repository,
     * it is equal to repositoryName
     * @throws NexusException - if something went wrong while
     *                        communication with Nexus server.
     */
    String createMavenRepository(String repositoryName) throws NexusException;

    /**
     * Gives the user access to the repository
     *
     * @param userId       - String id of Nexus user
     * @param repositoryId - String id of Nexus repository
     * @throws NexusException - if something went wrong while
     *                        communication with Nexus server.
     */
    void assignUserToRepository(String userId, String repositoryId) throws NexusException;

    /**
     * Delete user from the repository. Do nothing if
     * user is missing or user does not have access.
     *
     * @param userId       - String id of Nexus user
     * @param repositoryId - String id of Nexus repository
     * @throws NexusException - if something went wrong while
     *                        communication with Nexus server.
     */
    void unassignUserFromRepository(String userId, String repositoryId) throws NexusException;

    /**
     * Get last Nexus artifacts
     *
     * @param repositoryId   - String id of Nexus repository
     * @param artifactsCount - count of artifacts to return
     * @return - TODO: do return type, as ArtifactDto
     * @throws NexusException - if something went wrong while
     *                        communication with Nexus server.
     */
    List<Object> getLastArtifacts(String repositoryId, int artifactsCount) throws NexusException;
}