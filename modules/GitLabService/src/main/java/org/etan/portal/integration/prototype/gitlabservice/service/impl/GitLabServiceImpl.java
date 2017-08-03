package org.etan.portal.integration.prototype.gitlabservice.service.impl;

import org.etan.portal.integration.prototype.gitlabservice.service.GitLabService;
import org.osgi.service.component.annotations.Component;

/**
 * @author eta
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
    public long createRepository() {
        return 0;
    }

    @Override
    public void addUserToRepository(long userId, long repositoryId) {

    }

    @Override
    public void deleteUserFromRepository(long userId, long repositoryId) {

    }


}