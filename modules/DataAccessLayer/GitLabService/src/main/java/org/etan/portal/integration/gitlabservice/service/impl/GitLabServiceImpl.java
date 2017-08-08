package org.etan.portal.integration.gitlabservice.service.impl;

import org.etan.portal.integration.datarequester.service.DataRequesterService;
import org.etan.portal.integration.gitlabservice.service.GitLabService;
import org.etan.portal.integration.gitlabservice.service.GitLabServiceException;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Service used for get aces to some methods of GitLab server
 *
 * @author Efimov Timur
 * @version 1.0.1
 */
@Component(
        immediate = true,
        property = {
                // TODO enter required service properties
        },
        service = GitLabService.class
)
public class GitLabServiceImpl implements GitLabService {


    @Reference
    private DataRequesterService dataRequesterService;

    /**
     * Creates repository and return its id
     *
     * @param repositoryName name of new repository
     * @return id of created repository
     * @throws GitLabServiceException if any problems occurs
     */
    public long createRepository(String repositoryName) throws GitLabServiceException {
        return 0;
    }

    /**
     * Gives the user access to the repository
     *
     * @param userId       id of gitLab user
     * @param repositoryId id of gitLab repository
     * @throws GitLabServiceException if any problems occurs
     */
    public void addUserToRepository(long userId, long repositoryId)
            throws GitLabServiceException {

    }

    /**
     * Takes the user from the repository. Does not do anything
     * if the user does not have access
     *
     * @param userId       id of gitLab user
     * @param repositoryId id of gitLab repository
     * @throws GitLabServiceException if any problems occurs
     */
    public void deleteUserFromRepository(long userId, long repositoryId)
            throws GitLabServiceException {

    }


}