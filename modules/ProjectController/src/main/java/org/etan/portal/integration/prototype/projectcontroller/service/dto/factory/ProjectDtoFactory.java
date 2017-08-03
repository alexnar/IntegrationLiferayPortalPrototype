package org.etan.portal.integration.prototype.projectcontroller.service.dto.factory;

import org.etan.portal.integration.prototype.projectcontroller.service.dto.ProjectDto;
import org.etan.portal.integration.prototype.projectcontroller.service.dto.impl.ProjectDtoImpl;

/**
 * Project dto factory
 *
 * @author Efimov Timur
 * @version 1.0.1
 */
public class ProjectDtoFactory {

    /**
     * Get simple empty dto
     *
     * @return simple empty dto
     */
    public ProjectDto getInstance() {
        return new ProjectDtoImpl();
    }
}
