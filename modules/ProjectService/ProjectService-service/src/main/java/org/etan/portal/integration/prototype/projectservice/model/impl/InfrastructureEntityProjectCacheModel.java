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

package org.etan.portal.integration.prototype.projectservice.model.impl;

import aQute.bnd.annotation.ProviderType;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import org.etan.portal.integration.prototype.projectservice.model.InfrastructureEntityProject;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing InfrastructureEntityProject in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see InfrastructureEntityProject
 * @generated
 */
@ProviderType
public class InfrastructureEntityProjectCacheModel implements CacheModel<InfrastructureEntityProject>,
        Externalizable {
    public long infrastructureEntityProjectPKId;
    public long organizationId;
    public String infrastructureEntityName;
    public String infrastructureEntityProjectId;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof InfrastructureEntityProjectCacheModel)) {
            return false;
        }

        InfrastructureEntityProjectCacheModel infrastructureEntityProjectCacheModel =
                (InfrastructureEntityProjectCacheModel) obj;

        if (infrastructureEntityProjectPKId == infrastructureEntityProjectCacheModel.infrastructureEntityProjectPKId) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return HashUtil.hash(0, infrastructureEntityProjectPKId);
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(9);

        sb.append("{infrastructureEntityProjectPKId=");
        sb.append(infrastructureEntityProjectPKId);
        sb.append(", organizationId=");
        sb.append(organizationId);
        sb.append(", infrastructureEntityName=");
        sb.append(infrastructureEntityName);
        sb.append(", infrastructureEntityProjectId=");
        sb.append(infrastructureEntityProjectId);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public InfrastructureEntityProject toEntityModel() {
        InfrastructureEntityProjectImpl infrastructureEntityProjectImpl = new InfrastructureEntityProjectImpl();

        infrastructureEntityProjectImpl.setInfrastructureEntityProjectPKId(infrastructureEntityProjectPKId);
        infrastructureEntityProjectImpl.setOrganizationId(organizationId);

        if (infrastructureEntityName == null) {
            infrastructureEntityProjectImpl.setInfrastructureEntityName(StringPool.BLANK);
        } else {
            infrastructureEntityProjectImpl.setInfrastructureEntityName(infrastructureEntityName);
        }

        if (infrastructureEntityProjectId == null) {
            infrastructureEntityProjectImpl.setInfrastructureEntityProjectId(StringPool.BLANK);
        } else {
            infrastructureEntityProjectImpl.setInfrastructureEntityProjectId(infrastructureEntityProjectId);
        }

        infrastructureEntityProjectImpl.resetOriginalValues();

        return infrastructureEntityProjectImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        infrastructureEntityProjectPKId = objectInput.readLong();

        organizationId = objectInput.readLong();
        infrastructureEntityName = objectInput.readUTF();
        infrastructureEntityProjectId = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
            throws IOException {
        objectOutput.writeLong(infrastructureEntityProjectPKId);

        objectOutput.writeLong(organizationId);

        if (infrastructureEntityName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(infrastructureEntityName);
        }

        if (infrastructureEntityProjectId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(infrastructureEntityProjectId);
        }
    }
}