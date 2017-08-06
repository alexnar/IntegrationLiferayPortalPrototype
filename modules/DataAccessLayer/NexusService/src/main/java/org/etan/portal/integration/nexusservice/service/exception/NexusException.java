package org.etan.portal.integration.nexusservice.service.exception;

/**
 * Signals if something went wrong
 * while communication with
 * Nexus server.
 *
 * @author Naryzhny Alex
 */
public class NexusException extends Exception{
    public NexusException(String message) {
        super(message);
    }

    public NexusException(String message, Throwable cause) {
        super(message, cause);
    }
}
