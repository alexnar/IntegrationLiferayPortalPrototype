package org.etan.portal.integration.projectcontroller.service;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Efimov Timur
 * @version 1.0.1
 */
public class CouldNotCreateOrganizationRuntimeException extends RuntimeException {

    public CouldNotCreateOrganizationRuntimeException(long ownerUserId,
                                                      long organizationId,
                                                      String projectName,
                                                      boolean site,
                                                      PortalException e) {
        super("ownerUserId: " + ownerUserId
                        + "\n organizationId: " + organizationId
                        + "\n projectName\\organizationName: " + projectName
                        + "\n site: " + site,
                e
        );
    }
}
