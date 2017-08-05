package org.etan.portal.integration.prototype.gitlabservice.service;

/**
 * Service used for get aces to some methods of GitLab server
 *
 * @author Efimov Timur
 * @version 1.0.1
 */
public interface GitLabService {

    /**
     * Creates repository and return its id
     *
     * @return id of created repository
     */
    long createRepository();

    /**
     * Gives the user access to the repository
     *
     * @param userId       id of gitLab user
     * @param repositoryId id of gitLab repository
     */
    void addUserToRepository(long userId, long repositoryId);

    /**
     * Takes the user from the repository. Does not do anything
     * if the user does not have access
     *
     * @param userId       id of gitLab user
     * @param repositoryId id of gitLab repository
     */
    void deleteUserFromRepository(long userId, long repositoryId);
}
