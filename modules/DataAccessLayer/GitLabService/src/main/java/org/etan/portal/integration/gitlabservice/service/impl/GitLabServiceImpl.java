package org.etan.portal.integration.gitlabservice.service.impl;

import org.etan.portal.integration.gitlabservice.service.GitLabService;
import org.etan.portal.integration.gitlabservice.service.GitLabServiceException;
import org.osgi.service.component.annotations.Component;

/**
 *
 * @author Efimov Timur
 * @version 0.0.1
 */
@Component(
        immediate = true,
        property = {
                // TODO enter required service properties
        },
        service = GitLabService.class
)
public class GitLabServiceImpl implements GitLabService {

    // TODO enter realization of methods


    @Override
    public long createRepository(String repositoryName) throws GitLabServiceException {
        return 0;
    }

    @Override
    public void addUserToRepository(long userId, long repositoryId) {

    }

    @Override
    public void deleteUserFromRepository(long userId, long repositoryId) {

    }


}