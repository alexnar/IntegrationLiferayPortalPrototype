package org.etan.portal.integration.nexusservice.service.impl;

import org.etan.portal.integration.nexusservice.service.dto.NexusScriptDto;
import org.etan.portal.integration.nexusservice.service.exception.NexusException;

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

    /**
     * Execute script.
     * If script with such name already
     * exists than execute existing script,
     * else add script and execute it.
     *
     * @param nexusScriptDto - DTO of script to execute
     * @return - response of script execution
     * @throws NexusException - if something went wrong while
     *                        communication with Nexus server.
     */
    public String executeScript(NexusScriptDto nexusScriptDto) throws NexusException {
        String scriptName = nexusScriptDto.getScriptName();
        boolean scriptExists = checkIfScriptExists(scriptName);
        String executionResponse;
        if (scriptExists) {
            executionResponse = executeExistingScript(scriptName);
        } else {
            addScript(nexusScriptDto);
            executionResponse = executeExistingScript(scriptName);
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
    private void addScript(NexusScriptDto nexusScriptDto) throws NexusException {
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
     * @param scriptName - name of script ot execute
     * @return - response of script execution
     * @throws NexusException - if something went wrong while
     *                        communication with Nexus server.
     */
    private String executeExistingScript(String scriptName) throws NexusException {
        return null;
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
        return true;
    }
}
