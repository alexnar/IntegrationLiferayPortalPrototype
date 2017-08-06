package org.etan.portal.integration.nexusservice.service.impl;

import org.etan.portal.integration.nexusservice.service.NexusService;
import org.etan.portal.integration.nexusservice.service.dto.NexusScriptDto;
import org.etan.portal.integration.nexusservice.service.exception.NexusException;
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

    private NexusScripts nexusScripts = new NexusScripts();
    private NexusRemoteScriptManager nexusRemoteScriptManager = new NexusRemoteScriptManager();

    @Override
    public String createRepository(String repositoryName) throws NexusException {
        NexusScriptDto createRepositoryScript = nexusScripts.getCreateRepositoryScript(repositoryName);
        nexusRemoteScriptManager.executeScript(createRepositoryScript);
        return repositoryName;
    }

    @Override
    public void assignUserToRepository(String userId, String repositoryId) throws NexusException {
        NexusScriptDto assignUserToRepositoryScript =
                nexusScripts.getAssignUserToRepositoryScript(userId, repositoryId);
        nexusRemoteScriptManager.executeScript(assignUserToRepositoryScript);
    }

    @Override
    public void unassignUserFromRepository(String userId, String repositoryId) throws NexusException {
        NexusScriptDto unassignUserFromRepositoryScript =
                nexusScripts.getUnassignUserToRepositoryScript(userId, repositoryId);
        nexusRemoteScriptManager.executeScript(unassignUserFromRepositoryScript);
    }

    @Override
    public List<Object> getLastArtifacts(String repositoryId, int artifactsCount) throws NexusException {
        NexusScriptDto getLastArtifactsScript =
                nexusScripts.getLastArtifactsScript(repositoryId, artifactsCount);
        String executionResponse = nexusRemoteScriptManager.executeScript(getLastArtifactsScript);
        // TODO: get list of artifact from executionResponse
        return null;
    }
}
