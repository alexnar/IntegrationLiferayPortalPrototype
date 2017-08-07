package org.etan.portal.integration.projectcontroller.service;


import com.liferay.portal.kernel.model.Group;

/**
 * @author Efimov Timur
 * @version 1.0.1
 */
public class IsNotOrganizationRuntimeException extends RuntimeException {

    public IsNotOrganizationRuntimeException(Group group) {
        super("Site with name: " + group.getName() + "is not assign to organization");
    }
}
