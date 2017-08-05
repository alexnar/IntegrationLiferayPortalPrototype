/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package org.etan.portal.integration.prototype.projectservice.service;

import aQute.bnd.annotation.ProviderType;
import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link InfrastructureEntityProjectService}.
 *
 * @author Brian Wing Shun Chan
 * @see InfrastructureEntityProjectService
 * @generated
 */
@ProviderType
public class InfrastructureEntityProjectServiceWrapper
        implements InfrastructureEntityProjectService,
        ServiceWrapper<InfrastructureEntityProjectService> {
    private InfrastructureEntityProjectService _infrastructureEntityProjectService;

    public InfrastructureEntityProjectServiceWrapper(
            InfrastructureEntityProjectService infrastructureEntityProjectService) {
        _infrastructureEntityProjectService = infrastructureEntityProjectService;
    }

    /**
     * Returns the OSGi service identifier.
     *
     * @return the OSGi service identifier
     */
    @Override
    public java.lang.String getOSGiServiceIdentifier() {
        return _infrastructureEntityProjectService.getOSGiServiceIdentifier();
    }

    @Override
    public InfrastructureEntityProjectService getWrappedService() {
        return _infrastructureEntityProjectService;
    }

    @Override
    public void setWrappedService(
            InfrastructureEntityProjectService infrastructureEntityProjectService) {
        _infrastructureEntityProjectService = infrastructureEntityProjectService;
    }
}