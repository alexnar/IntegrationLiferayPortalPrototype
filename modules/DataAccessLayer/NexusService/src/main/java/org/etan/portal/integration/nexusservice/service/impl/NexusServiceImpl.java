package org.etan.portal.integration.nexusservice.service.impl;

import org.etan.portal.integration.nexusservice.service.NexusService;
import org.osgi.service.component.annotations.Component;

import java.util.List;


@Component(
        immediate = true,
        property = {
                // TODO: enter required service properties
        },
        service = NexusService.class
)
public class NexusServiceImpl implements NexusService {

    // TODO implement methods

    @Override
    public String createRepository(String repositoryName) {
        return null;
    }

    @Override
    public void addUserToRepository(String userId, String repositoryId) {

    }

    @Override
    public void deleteUserFromRepository(String userId, String repositoryId) {

    }

    @Override
    public List<Object> getLastArtifacts(String repositoryId, int artifactsCount) {
        return null;
    }
}
