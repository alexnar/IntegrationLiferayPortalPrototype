package org.etan.portal.integration.projectcontroller.service;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Efimov Timur
 * @version 1.0.1
 */
public class CouldNotGetOrganizationFromServiceContextRuntimeException extends RuntimeException {

    public CouldNotGetOrganizationFromServiceContextRuntimeException(PortalException e) {
        super(e);
    }
}
