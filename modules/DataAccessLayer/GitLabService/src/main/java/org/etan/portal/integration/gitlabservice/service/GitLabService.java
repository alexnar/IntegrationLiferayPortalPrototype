package org.etan.portal.integration.gitlabservice.service;

/**
 * Service used for get aces to some methods of GitLab server.
 *
 * @author Efimov Timur
 * @version 1.0.1
 */
public interface GitLabService {

    /**
     * Creates repository and return its id
     *
     * @param repositoryName name of new repository
     * @return id of created repository
     * @throws GitLabServiceException if any problems occurs
     */
    int createRepository(String repositoryName) throws GitLabServiceException;

    /**
     * Gives the user access to the repository
     *
     * @param userId       id of gitLab user
     * @param repositoryId id of gitLab repository
     * @throws GitLabServiceException if any problems occurs
     */
    void addUserToRepository(int userId, int repositoryId)
            throws GitLabServiceException;

    /**
     * Takes the user from the repository. Does not do anything
     * if the user does not have access
     *
     * @param userId       id of gitLab user
     * @param repositoryId id of gitLab repository
     * @throws GitLabServiceException if any problems occurs
     */
    void deleteUserFromRepository(int userId, int repositoryId)
            throws GitLabServiceException;

    /**
     * Check if repository with name exists.
     *
     * @param repositoryName name of checking repository
     * @return true, if exists
     * @throws GitLabServiceException if any problems occurs
     */
    boolean checkIfRepositoryWithNameExists(String repositoryName)
            throws GitLabServiceException;
}
