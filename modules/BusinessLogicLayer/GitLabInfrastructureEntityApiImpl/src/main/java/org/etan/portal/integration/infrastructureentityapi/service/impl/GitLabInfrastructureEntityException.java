package org.etan.portal.integration.infrastructureentityapi.service.impl;

import org.etan.portal.integration.infrastructureentityapi.service.exception.InfrastructureEntityException;

/**
 * @author Efimov Timur
 * @version 1.0.1
 */
public class GitLabInfrastructureEntityException extends InfrastructureEntityException {
    public GitLabInfrastructureEntityException(String message) {
        super(message);
    }

    public GitLabInfrastructureEntityException(String message, Throwable cause) {
        super(message, cause);
    }
}
