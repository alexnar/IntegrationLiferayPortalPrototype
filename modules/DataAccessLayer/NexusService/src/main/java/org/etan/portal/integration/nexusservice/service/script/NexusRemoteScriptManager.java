package org.etan.portal.integration.nexusservice.service.script;

import com.google.gson.*;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.etan.portal.integration.datarequester.service.DataRequesterService;
import org.etan.portal.integration.datarequester.service.exception.DataRequestException;
import org.etan.portal.integration.datarequester.service.impl.DataRequesterServiceImpl;
import org.etan.portal.integration.nexusservice.service.dto.NexusScriptDto;
import org.etan.portal.integration.nexusservice.service.exception.NexusException;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for managing scripts on
 * Nexus remote server.
 * <p>
 * There should be methods that
 * respond for any type of communication
 * with scripts on Nexus remote server.
 *
 * @author Naryzhny Alex
 */
public class NexusRemoteScriptManager {

    // TODO: Make configurable. IMPORTANT: URL must end with "/" !!!
    private static final String SCRIPT_URL = "http://localhost:8081/service/siesta/rest/v1/script/";
    private static final String SCRIPT_URL_EXECUTION_POSTFIX = "/run";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin123";

    private static final Log logger = LogFactoryUtil.getLog(DataRequesterServiceImpl.class);

    @Reference
    private DataRequesterService dataRequesterService;


    /**
     * Execute script.
     * If script with such name already
     * exists than execute existing script,
     * else add script and execute it.
     *
     * @param nexusScriptDto - DTO of script to execute
     * @param action         - script action
     * @param parameters     - parameters map, could be null, if no
     *                       parameters needed
     * @return - response of script execution
     * @throws NexusException - if something went wrong while
     *                        communication with Nexus server.
     */
    public String executeScript(NexusScriptDto nexusScriptDto, NexusScriptAction action, Map<String, String> parameters)
            throws NexusException {
        if (parameters == null) {
            parameters = new HashMap<>();
        }
        String scriptName = nexusScriptDto.getScriptName();
        boolean scriptExists = checkIfScriptExists(scriptName);
        String executionResponse;
        if (scriptExists) {
            executionResponse = executeExistingScript(action, parameters);
        } else {
            addScript(nexusScriptDto, action);
            executionResponse = executeExistingScript(action, parameters);
        }
        return executionResponse;
    }

    /**
     * Add script to Nexus remote
     * script server.
     *
     * @param nexusScriptDto - DTO of script to add
     * @throws NexusException - if something went wrong while
     *                        communication with Nexus server.
     */
    private void addScript(NexusScriptDto nexusScriptDto, NexusScriptAction action) throws NexusException {
        Gson gson = new Gson();
        String scriptJson = gson.toJson(nexusScriptDto);
        String runScriptUrl = SCRIPT_URL + action.getAction() + SCRIPT_URL_EXECUTION_POSTFIX;
        try {
            dataRequesterService.postJsonToUrlWithAuthorization(runScriptUrl, USERNAME, PASSWORD, scriptJson);
        } catch (DataRequestException e) {
            throw new NexusException(e.getMessage(), e);
        }
    }


    /**
     * Execute script, if it already exists.
     * Recommended to call this method after
     * checking if script exists by calling
     * {@link #checkIfScriptExists(String)}
     * <p>
     * Check response, if it is empty throws
     * {@link NexusException}.
     *
     * @param action - name of script ot execute
     * @return - response of script execution
     * @throws NexusException - if something went wrong while
     *                        communication with Nexus server.
     */
    private String executeExistingScript(NexusScriptAction action,
                                         Map<String, String> parameters) throws NexusException {
        StringBuilder response;
        try {
            response = dataRequesterService.postParametersToUrlWithAuthorization(
                    SCRIPT_URL, USERNAME, PASSWORD, parameters);
        } catch (DataRequestException e) {
            throw new NexusException(e.getMessage(), e);
        }
        return response.toString();
    }

    /**
     * Check if script already exists in
     * Nexus script server.
     *
     * @param scriptName - name of script to check
     * @return - true if script exists,
     * false if script does not exists
     * @throws NexusException - if something went wrong while
     *                        communication with Nexus server.
     */
    private boolean checkIfScriptExists(String scriptName) throws NexusException {
        StringBuilder jsonStringBuilder;
        try {
            jsonStringBuilder =
                    dataRequesterService.getDataFromUrlWithAuthorization(SCRIPT_URL, USERNAME, PASSWORD);
        } catch (DataRequestException e) {
            throw new NexusException(e.getMessage(), e);
        }
        String json = jsonStringBuilder.toString();
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = jsonParser.parse(json).getAsJsonArray();
        for (JsonElement jsonElement : jsonArray) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            JsonElement scriptNameOnServer = jsonObject.get("name");
            if (scriptNameOnServer == null) {
                continue;
            }
            String scriptNameOnServerValue = scriptNameOnServer.getAsString();
            if (scriptNameOnServerValue.equals(scriptName)) {
                return true;
            }
        }
        return false;
    }

}
