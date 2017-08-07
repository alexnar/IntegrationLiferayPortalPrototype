package org.etan.portal.integration.nexusservice.service.impl;

import org.etan.portal.integration.nexusservice.service.NexusService;
import org.etan.portal.integration.nexusservice.service.dto.NexusScriptDto;
import org.etan.portal.integration.nexusservice.service.exception.NexusException;
import org.etan.portal.integration.nexusservice.service.script.NexusRemoteScriptManager;
import org.etan.portal.integration.nexusservice.service.script.NexusScriptAction;
import org.etan.portal.integration.nexusservice.service.script.NexusScripts;
import org.osgi.service.component.annotations.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component(
        immediate = true,
        property = {
                // TODO: enter required service properties
        },
        service = NexusService.class
)
public class NexusServiceImpl implements NexusService {

    private static final String REPOSITORY_NAME_FIELD = "repositoryName";

    private NexusScripts nexusScripts = new NexusScripts();
    private NexusRemoteScriptManager nexusRemoteScriptManager = new NexusRemoteScriptManager();

    @Override
    public String createMavenHostedRepository(String repositoryName) throws NexusException {
        NexusScriptDto createRepositoryScript = nexusScripts.getCreateMavenRepositoryScript(repositoryName);
        Map<String, String> parameters = new HashMap<>();
        parameters.put(REPOSITORY_NAME_FIELD, repositoryName);
        nexusRemoteScriptManager.executeScript(
                createRepositoryScript, NexusScriptAction.CREATE_MAVEN_HOSTED, parameters);
        return repositoryName;
    }

    @Override
    public void assignUserToRepository(String userId, String repositoryId) throws NexusException {
        NexusScriptDto assignUserToRepositoryScript =
                nexusScripts.getAssignUserToRepositoryScript(userId, repositoryId);
        nexusRemoteScriptManager.executeScript(assignUserToRepositoryScript, NexusScriptAction.ASSIGN_USER, null);
    }

    @Override
    public void unassignUserFromRepository(String userId, String repositoryId) throws NexusException {
        NexusScriptDto unassignUserFromRepositoryScript =
                nexusScripts.getUnassignUserToRepositoryScript(userId, repositoryId);
        nexusRemoteScriptManager.executeScript(unassignUserFromRepositoryScript, NexusScriptAction.UNASSIGN_USER, null);
    }

    @Override
    public List<Object> getLastArtifacts(String repositoryId, int artifactsCount) throws NexusException {
        NexusScriptDto lastArtifactsScript =
                nexusScripts.getLastArtifactsScript(repositoryId, artifactsCount);
        String executionResponse = nexusRemoteScriptManager.executeScript(
                lastArtifactsScript, NexusScriptAction.LAST_ARTIFACTS, null);
        // TODO: get list of artifact from executionResponse
        return null;
    }

}
