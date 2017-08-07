package org.etan.portal.integration.projectcontroller.service;

/**
 * @author Efimov Timur
 * @version 1.0.1
 */
public class SiteTemplateNotFoundException extends RuntimeException {

    public SiteTemplateNotFoundException(String projectSiteTemplateName) {
        super("This site template not found: " + projectSiteTemplateName);
    }
}
