package org.etan.portal.integration.prototype.nexusservice.service;

import java.util.List;

/**
 * Nexus service interface define methods for
 * for accessing and working with Nexus API.
 *
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
     *             it is equal to repositoryName
     */
    String createRepository(String repositoryName);

    /**
     * Gives the user access to the repository
     *
     * @param userId - String id of Nexus user
     * @param repositoryId - String id of Nexus repository
     */
    void addUserToRepository(String userId, String repositoryId);

    /**
     * Delete user from the repository. Do nothing if
     * user is missing or user does not have access.
     *
     * @param userId - String id of Nexus user
     * @param repositoryId - String id of Nexus repository
     */
    void deleteUserFromRepository(String userId, String repositoryId);

    /**
     * Get last Nexus artifacts
     *
     * @param repositoryId - Str
     * @param artifactsCount
     * @return - TODO: do return type, as ArtifactDto
     */
    List<Object> getLastArtifacts(String repositoryId, int artifactsCount);
}