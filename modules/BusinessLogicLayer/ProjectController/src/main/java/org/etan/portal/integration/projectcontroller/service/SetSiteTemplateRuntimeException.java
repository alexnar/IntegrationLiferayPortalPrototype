package org.etan.portal.integration.projectcontroller.service;

import com.liferay.portal.kernel.model.Group;

/**
 * @author Efimov Timur
 * @version 1.0.1
 */
public class SetSiteTemplateRuntimeException extends RuntimeException {

    public SetSiteTemplateRuntimeException(Group group, String siteTemplateName, Exception e) {
        super("Problem when set template with name "
                        + "'" + siteTemplateName + "' "
                        + "to site with name "
                        + "'" + group.getName() + "\"",
                e);
    }
}
