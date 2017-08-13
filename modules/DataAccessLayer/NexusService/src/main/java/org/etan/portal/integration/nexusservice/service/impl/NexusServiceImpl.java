package org.etan.portal.integration.nexusservice.service.impl;

import com.google.gson.*;
import org.etan.portal.integration.nexusservice.service.NexusService;
import org.etan.portal.integration.nexusservice.service.dto.NexusComponentDto;
import org.etan.portal.integration.nexusservice.service.dto.NexusScriptDto;
import org.etan.portal.integration.nexusservice.service.exception.NexusException;
import org.etan.portal.integration.nexusservice.service.script.NexusRemoteScriptManager;
import org.etan.portal.integration.nexusservice.service.script.NexusScriptAction;
import org.etan.portal.integration.nexusservice.service.script.NexusScripts;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.*;


@Component(
        immediate = true,
        property = {
                "osgi.command.scope=test",
                "osgi.command.function=getLastArtifacts"
        },
        service = NexusService.class
)
public class NexusServiceImpl implements NexusService {

    private static final String REPOSITORY_NAME_FIELD = "repositoryName";
    private static final String USER_ID_FIELD = "userId";
    private static final String PRIVILEGE_NAME_FIELD = "privilegeName";
    private static final String ROLE_NAME_FIELD = "roleName";
    private static final String ROLE_ID_POSTFIX = "-user-privileges-role";
    private static final String RESPONSE_RESULT_FIELD = "result";
    private static final String REPOSITORY_EXISTS_RESULT = "REPOSITORY_EXISTS";
    private static final String REPOSITORY_NOT_EXISTS_RESULT = "REPOSITORY_NOT_EXISTS";
    private static final int START_ARRAY_INDEX = 0;
    private static final String NEXUS_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";


    private NexusScripts nexusScripts = new NexusScripts();

    @Reference
    private NexusRemoteScriptManager nexusRemoteScriptManager;

    @Override
    public String createMavenHostedRepository(String repositoryName) throws NexusException {
        NexusScriptDto createRepositoryScript = nexusScripts.getCreateMavenRepositoryScript();
        Map<String, String> parameters = new HashMap<>();
        parameters.put(REPOSITORY_NAME_FIELD, repositoryName);
        nexusRemoteScriptManager.executeScript(
                createRepositoryScript, NexusScriptAction.CREATE_MAVEN_HOSTED, parameters);
        return repositoryName;
    }

    @Override
    public void assignUserToRepository(String userId, String repositoryId) throws NexusException {
        NexusScriptDto assignUserToRepositoryScript =
                nexusScripts.getAssignUserToRepositoryScript();
        Map<String, String> parameters = new HashMap<>();
        //TODO: repository could be not only maven
        String privileges = "nx-repository-view-maven2-" + repositoryId + "-*";
        String roleName = repositoryId + ROLE_ID_POSTFIX;
        parameters.put(USER_ID_FIELD, userId);
        parameters.put(PRIVILEGE_NAME_FIELD, privileges);
        parameters.put(ROLE_NAME_FIELD, roleName);
        nexusRemoteScriptManager.executeScript(assignUserToRepositoryScript, NexusScriptAction.ASSIGN_USER, parameters);
    }

    @Override
    public void unassignUserFromRepository(String userId, String repositoryId) throws NexusException {
        NexusScriptDto unassignUserFromRepositoryScript =
                nexusScripts.getUnassignUserToRepositoryScript();
        Map<String, String> parameters = new HashMap<>();
        String roleName = repositoryId + ROLE_ID_POSTFIX;
        parameters.put(USER_ID_FIELD, userId);
        parameters.put(ROLE_NAME_FIELD, roleName);
        nexusRemoteScriptManager.executeScript(unassignUserFromRepositoryScript, NexusScriptAction.UNASSIGN_USER,
                parameters);
    }

    @Override
    public boolean checkCreateRepositoryOpportunity(String repositoryName) {
        NexusScriptDto checkCreateRepositoryOpportunityScript =
                nexusScripts.getCheckCreateRepositoryOpportunityScript();
        Map<String, String> parameters = new HashMap<>();
        parameters.put(REPOSITORY_NAME_FIELD, repositoryName);
        String response;
        try {
            response = nexusRemoteScriptManager.executeScript(checkCreateRepositoryOpportunityScript,
                    NexusScriptAction.CHECK_CREATE_REPOSITORY_OPPORTUNITY, parameters);
        } catch (NexusException e) {
            return false;
        }
        JsonParser jsonParser = new JsonParser();
        JsonObject responseJson = jsonParser.parse(response).getAsJsonObject();
        String responseResult = responseJson.get(RESPONSE_RESULT_FIELD).getAsString();
        boolean hasOpportunity = responseResult.equals(REPOSITORY_NOT_EXISTS_RESULT);
        return hasOpportunity;
    }

    @Override
    public List<NexusComponentDto> getLastArtifacts(String repositoryId, int artifactsCount) throws NexusException {
        NexusScriptDto lastArtifactsScript =
                nexusScripts.getLastArtifactsScript();
        Map<String, String> parameters = new HashMap<>();
        parameters.put(REPOSITORY_NAME_FIELD, repositoryId);
        String executionResponse = nexusRemoteScriptManager.executeScript(
                lastArtifactsScript, NexusScriptAction.LAST_ARTIFACTS, parameters);

        List<NexusComponentDto> nexusComponentList = parseResponseToNexusComponents(executionResponse);
        Collections.sort(nexusComponentList);
        int componentListSize = nexusComponentList.size();
        if (artifactsCount > componentListSize) {
            artifactsCount = componentListSize;
        }
        List<NexusComponentDto> lastArtifacts = nexusComponentList.subList(START_ARRAY_INDEX, artifactsCount);
        return lastArtifacts;
    }

    private List<NexusComponentDto> parseResponseToNexusComponents(String response) {
        JsonParser jsonParser = new JsonParser();
        JsonElement responseElement = jsonParser.parse(response);
        JsonElement resultElement = responseElement.getAsJsonObject().get("result");
        String result = resultElement.getAsString();
        Gson gson = new GsonBuilder().setDateFormat(NEXUS_DATE_FORMAT).create();
        NexusComponentDto[] nexusComponents = gson.fromJson(result, NexusComponentDto[].class);
        List<NexusComponentDto> nexusComponentList = Arrays.asList(nexusComponents);
        return nexusComponentList;
    }

}
