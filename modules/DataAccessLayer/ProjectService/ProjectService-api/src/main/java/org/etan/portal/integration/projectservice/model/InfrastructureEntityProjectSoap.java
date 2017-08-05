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

package org.etan.portal.integration.projectservice.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link org.etan.portal.integration.projectservice.service.http.InfrastructureEntityProjectServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see org.etan.portal.integration.projectservice.service.http.InfrastructureEntityProjectServiceSoap
 * @generated
 */
@ProviderType
public class InfrastructureEntityProjectSoap implements Serializable {
    private long _infrastructureEntityProjectPKId;
    private long _organizationId;
    private String _infrastructureEntityName;
    private String _infrastructureEntityProjectId;

    public InfrastructureEntityProjectSoap() {
    }

    public static InfrastructureEntityProjectSoap toSoapModel(
            InfrastructureEntityProject model) {
        InfrastructureEntityProjectSoap soapModel = new InfrastructureEntityProjectSoap();

        soapModel.setInfrastructureEntityProjectPKId(model.getInfrastructureEntityProjectPKId());
        soapModel.setOrganizationId(model.getOrganizationId());
        soapModel.setInfrastructureEntityName(model.getInfrastructureEntityName());
        soapModel.setInfrastructureEntityProjectId(model.getInfrastructureEntityProjectId());

        return soapModel;
    }

    public static InfrastructureEntityProjectSoap[] toSoapModels(
            InfrastructureEntityProject[] models) {
        InfrastructureEntityProjectSoap[] soapModels = new InfrastructureEntityProjectSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static InfrastructureEntityProjectSoap[][] toSoapModels(
            InfrastructureEntityProject[][] models) {
        InfrastructureEntityProjectSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new InfrastructureEntityProjectSoap[models.length][models[0].length];
        } else {
            soapModels = new InfrastructureEntityProjectSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static InfrastructureEntityProjectSoap[] toSoapModels(
            List<InfrastructureEntityProject> models) {
        List<InfrastructureEntityProjectSoap> soapModels = new ArrayList<InfrastructureEntityProjectSoap>(models.size());

        for (InfrastructureEntityProject model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new InfrastructureEntityProjectSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _infrastructureEntityProjectPKId;
    }

    public void setPrimaryKey(long pk) {
        setInfrastructureEntityProjectPKId(pk);
    }

    public long getInfrastructureEntityProjectPKId() {
        return _infrastructureEntityProjectPKId;
    }

    public void setInfrastructureEntityProjectPKId(
            long infrastructureEntityProjectPKId) {
        _infrastructureEntityProjectPKId = infrastructureEntityProjectPKId;
    }

    public long getOrganizationId() {
        return _organizationId;
    }

    public void setOrganizationId(long organizationId) {
        _organizationId = organizationId;
    }

    public String getInfrastructureEntityName() {
        return _infrastructureEntityName;
    }

    public void setInfrastructureEntityName(String infrastructureEntityName) {
        _infrastructureEntityName = infrastructureEntityName;
    }

    public String getInfrastructureEntityProjectId() {
        return _infrastructureEntityProjectId;
    }

    public void setInfrastructureEntityProjectId(
            String infrastructureEntityProjectId) {
        _infrastructureEntityProjectId = infrastructureEntityProjectId;
    }
}