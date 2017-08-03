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

package org.etan.portal.integration.prototype.projectservice.model;

import aQute.bnd.annotation.ProviderType;
import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the InfrastructureEntityProject service. Represents a row in the &quot;ProjectService_InfrastructureEntityProject&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see InfrastructureEntityProjectModel
 * @see org.etan.portal.integration.prototype.projectservice.model.impl.InfrastructureEntityProjectImpl
 * @see org.etan.portal.integration.prototype.projectservice.model.impl.InfrastructureEntityProjectModelImpl
 * @generated
 */
@ImplementationClassName("org.etan.portal.integration.prototype.projectservice.model.impl.InfrastructureEntityProjectImpl")
@ProviderType
public interface InfrastructureEntityProject
        extends InfrastructureEntityProjectModel, PersistedModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this interface directly. Add methods to {@link org.etan.portal.integration.prototype.projectservice.model.impl.InfrastructureEntityProjectImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
    public static final Accessor<InfrastructureEntityProject, Long> INFRASTRUCTURE_ENTITY_PROJECT_PK_ID_ACCESSOR =
            new Accessor<InfrastructureEntityProject, Long>() {
                @Override
                public Long get(
                        InfrastructureEntityProject infrastructureEntityProject) {
                    return infrastructureEntityProject.getInfrastructureEntityProjectPKId();
                }

                @Override
                public Class<Long> getAttributeClass() {
                    return Long.class;
                }

                @Override
                public Class<InfrastructureEntityProject> getTypeClass() {
                    return InfrastructureEntityProject.class;
                }
            };
}