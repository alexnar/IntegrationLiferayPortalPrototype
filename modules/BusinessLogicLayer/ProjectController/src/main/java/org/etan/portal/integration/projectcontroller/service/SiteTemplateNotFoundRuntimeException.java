package org.etan.portal.integration.projectcontroller.service;

/**
 * @author Efimov Timur
 * @version 1.0.1
 */
public class SiteTemplateNotFoundRuntimeException extends RuntimeException {

    public SiteTemplateNotFoundRuntimeException(String projectSiteTemplateName) {
        super("This site template not found: " + projectSiteTemplateName);
    }
}
