package org.etan.portal.integration.projectcontroller.service;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Efimov Timur
 * @version 1.0.1
 */
public class IllegalServiceContextException extends RuntimeException {

    public IllegalServiceContextException(String msg) {
        super(msg);
    }

    public IllegalServiceContextException(String msg, PortalException e) {
        super(msg, e);
    }
}
