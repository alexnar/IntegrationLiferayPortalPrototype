/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 * <p>
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * <p>
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package org.etan.portal.integration.projectservice.service.impl;

import aQute.bnd.annotation.ProviderType;
import org.etan.portal.integration.projectservice.model.InfrastructureEntityProject;
import org.etan.portal.integration.projectservice.service.InfrastructureEntityProjectLocalService;
import org.etan.portal.integration.projectservice.service.InfrastructureEntityProjectLocalServiceUtil;
import org.etan.portal.integration.projectservice.service.base.InfrastructureEntityProjectLocalServiceBaseImpl;

import java.util.List;
import java.util.Map;

/**
 * The implementation of the infrastructure entity project local service.
 * <p>
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link InfrastructureEntityProjectLocalService} interface.
 * <p>
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see InfrastructureEntityProjectLocalServiceBaseImpl
 * @see InfrastructureEntityProjectLocalServiceUtil
 */
@ProviderType
public class InfrastructureEntityProjectLocalServiceImpl
        extends InfrastructureEntityProjectLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link InfrastructureEntityProjectLocalServiceUtil} to access the infrastructure entity project local service.
	 */


    /**
     * Get list of InfrastructureEntityProject by organizationId.
     *
     * @param organizationId id of organization
     * @return InfrastructureEntityProject list
     */
    public List<InfrastructureEntityProject> get(long organizationId) {
        return infrastructureEntityProjectPersistence.findByOrganizationId(organizationId);
    }

    /**
     * Add all InfrastructureEntityProjects from ProjectDto.
     */
    public void addAll(Map<String, String> infrastructureEntityProjectIdMap, long organizationId) {
        for (Map.Entry<String, String> entry : infrastructureEntityProjectIdMap.entrySet()) {
            long i = counterLocalService.increment();

            InfrastructureEntityProject infrastructureEntityProject = createInfrastructureEntityProject(i);

            infrastructureEntityProject.setOrganizationId(organizationId);
            infrastructureEntityProject.setInfrastructureEntityName(entry.getKey());
            infrastructureEntityProject.setInfrastructureEntityProjectId(entry.getValue());
        }
    }
}