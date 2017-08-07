package org.etan.portal.integration.projectcontroller.service;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Efimov Timur
 * @version 1.0.1
 */
public class CouldNotGetUserFromServiceContextRuntimeException extends RuntimeException {

    public CouldNotGetUserFromServiceContextRuntimeException(long userId, PortalException e) {
        super("user not found with id: " + userId, e);
    }
}
