package org.etan.portal.integration.projectcontroller.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Organization;

/**
 * @author Efimov Timur
 * @version 1.0.1
 */
public class CouldNotGetOrganizationSiteRuntimeException extends RuntimeException {

    public CouldNotGetOrganizationSiteRuntimeException(Organization organization, PortalException e) {
        super("can not get site of organization with name: " + organization.getName(), e);
    }
}
