package org.etan.portal.integration.projectcontroller.service;

/**
 * @author Efimov Timur
 * @version 1.0.1
 */
public class OrganizationTypeRuntimeException extends RuntimeException {

    public OrganizationTypeRuntimeException(String found, String expected) {
        super("found: " + found + ", expected: " + expected);
    }
}
