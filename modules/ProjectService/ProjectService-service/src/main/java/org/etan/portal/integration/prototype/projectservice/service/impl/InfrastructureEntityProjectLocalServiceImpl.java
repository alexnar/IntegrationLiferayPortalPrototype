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

package org.etan.portal.integration.prototype.projectservice.service.impl;

import aQute.bnd.annotation.ProviderType;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.transaction.Transactional;
import org.etan.portal.integration.prototype.projectcontroller.service.dto.ProjectDto;
import org.etan.portal.integration.prototype.projectcontroller.service.dto.factory.ProjectDtoFactory;
import org.etan.portal.integration.prototype.projectservice.model.InfrastructureEntityProject;
import org.etan.portal.integration.prototype.projectservice.service.base.InfrastructureEntityProjectLocalServiceBaseImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The implementation of the infrastructure entity project local service.
 * <p>
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.etan.portal.integration.prototype.projectservice.service.InfrastructureEntityProjectLocalService} interface.
 * <p>
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see InfrastructureEntityProjectLocalServiceBaseImpl
 * @see org.etan.portal.integration.prototype.projectservice.service.InfrastructureEntityProjectLocalServiceUtil
 */
@ProviderType
public class InfrastructureEntityProjectLocalServiceImpl
        extends InfrastructureEntityProjectLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.etan.portal.integration.prototype.projectservice.service.InfrastructureEntityProjectLocalServiceUtil} to access the infrastructure entity project local service.
	 */

    /**
     * Get InfrastructureEntityProject by organizationId
     *
     * @param organizationId id of organization
     * @return InfrastructureEntityProject
     */
    public List<InfrastructureEntityProject> findByOrganizationId(long organizationId) {
        return infrastructureEntityProjectPersistence.findByOrganizationId(organizationId);
    }

    /**
     * Add all InfrastructureEntityProjects from ProjectDto.
     */
    @Indexable(type = IndexableType.REINDEX)
    @Transactional //todo не уверен, что это работает
    public void saveAllInfrastructureEntityProjects(ProjectDto projectDto) {
        ProjectDto newProjectDto = new ProjectDtoFactory().getInstance();
        newProjectDto.setProjectId(projectDto.getProjectId());

        Map<String, String> addedFromInfrastructureEntityProjectIdMap = new HashMap<>();

        for (Map.Entry<String, String> entry : projectDto.getInfrastructureEntityProjectIdMap().entrySet()) {

            long i = counterLocalService.increment();

            InfrastructureEntityProject infrastructureEntityProject = createInfrastructureEntityProject(i);

            infrastructureEntityProject.setOrganizationId(projectDto.getProjectId());
            infrastructureEntityProject.setInfrastructureEntityName(entry.getKey());
            infrastructureEntityProject.setInfrastructureEntityProjectId(entry.getValue());


            addedFromInfrastructureEntityProjectIdMap.put(entry.getKey(), entry.getValue());
        }
    }
}