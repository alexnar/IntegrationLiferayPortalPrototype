package org.etan.portal.integration.infrastructureentityapi.service.exception;

/**
 * Exception throws if some
 * problem occurred while executing
 * InfrastructureEntity methods.
 *
 * @author Naryzhny Alex
 */
public class InfrastructureEntityException extends Exception {
    public InfrastructureEntityException(String message) {
        super(message);
    }

    public InfrastructureEntityException(String message, Throwable cause) {
        super(message, cause);
    }
}
