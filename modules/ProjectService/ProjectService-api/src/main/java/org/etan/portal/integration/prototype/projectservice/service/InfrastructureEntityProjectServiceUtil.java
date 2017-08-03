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

package org.etan.portal.integration.prototype.projectservice.service;

import aQute.bnd.annotation.ProviderType;
import com.liferay.osgi.util.ServiceTrackerFactory;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for InfrastructureEntityProject. This utility wraps
 * {@link org.etan.portal.integration.prototype.projectservice.service.impl.InfrastructureEntityProjectServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see InfrastructureEntityProjectService
 * @see org.etan.portal.integration.prototype.projectservice.service.base.InfrastructureEntityProjectServiceBaseImpl
 * @see org.etan.portal.integration.prototype.projectservice.service.impl.InfrastructureEntityProjectServiceImpl
 * @generated
 */
@ProviderType
public class InfrastructureEntityProjectServiceUtil {
    /*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.etan.portal.integration.prototype.projectservice.service.impl.InfrastructureEntityProjectServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

    private static ServiceTracker<InfrastructureEntityProjectService, InfrastructureEntityProjectService> _serviceTracker =
            ServiceTrackerFactory.open(InfrastructureEntityProjectService.class);

    /**
     * Returns the OSGi service identifier.
     *
     * @return the OSGi service identifier
     */
    public static java.lang.String getOSGiServiceIdentifier() {
        return getService().getOSGiServiceIdentifier();
    }

    public static InfrastructureEntityProjectService getService() {
        return _serviceTracker.getService();
    }
}