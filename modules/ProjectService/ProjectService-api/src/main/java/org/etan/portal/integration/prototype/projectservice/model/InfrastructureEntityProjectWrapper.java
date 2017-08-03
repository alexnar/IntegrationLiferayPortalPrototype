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

package org.etan.portal.integration.prototype.projectservice.model;

import aQute.bnd.annotation.ProviderType;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link InfrastructureEntityProject}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see InfrastructureEntityProject
 * @generated
 */
@ProviderType
public class InfrastructureEntityProjectWrapper
        implements InfrastructureEntityProject,
        ModelWrapper<InfrastructureEntityProject> {
    private final InfrastructureEntityProject _infrastructureEntityProject;

    public InfrastructureEntityProjectWrapper(
            InfrastructureEntityProject infrastructureEntityProject) {
        _infrastructureEntityProject = infrastructureEntityProject;
    }

    @Override
    public Class<?> getModelClass() {
        return InfrastructureEntityProject.class;
    }

    @Override
    public String getModelClassName() {
        return InfrastructureEntityProject.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("infrastructureEntityProjectPKId",
                getInfrastructureEntityProjectPKId());
        attributes.put("organizationId", getOrganizationId());
        attributes.put("infrastructureEntityName", getInfrastructureEntityName());
        attributes.put("infrastructureEntityProjectId",
                getInfrastructureEntityProjectId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long infrastructureEntityProjectPKId = (Long) attributes.get(
                "infrastructureEntityProjectPKId");

        if (infrastructureEntityProjectPKId != null) {
            setInfrastructureEntityProjectPKId(infrastructureEntityProjectPKId);
        }

        Long organizationId = (Long) attributes.get("organizationId");

        if (organizationId != null) {
            setOrganizationId(organizationId);
        }

        String infrastructureEntityName = (String) attributes.get(
                "infrastructureEntityName");

        if (infrastructureEntityName != null) {
            setInfrastructureEntityName(infrastructureEntityName);
        }

        String infrastructureEntityProjectId = (String) attributes.get(
                "infrastructureEntityProjectId");

        if (infrastructureEntityProjectId != null) {
            setInfrastructureEntityProjectId(infrastructureEntityProjectId);
        }
    }

    @Override
    public InfrastructureEntityProject toEscapedModel() {
        return new InfrastructureEntityProjectWrapper(_infrastructureEntityProject.toEscapedModel());
    }

    @Override
    public InfrastructureEntityProject toUnescapedModel() {
        return new InfrastructureEntityProjectWrapper(_infrastructureEntityProject.toUnescapedModel());
    }

    @Override
    public boolean isCachedModel() {
        return _infrastructureEntityProject.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _infrastructureEntityProject.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _infrastructureEntityProject.isEscapedModel();
    }

    @Override
    public boolean isNew() {
        return _infrastructureEntityProject.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _infrastructureEntityProject.setNew(n);
    }

    @Override
    public ExpandoBridge getExpandoBridge() {
        return _infrastructureEntityProject.getExpandoBridge();
    }

    @Override
    public com.liferay.portal.kernel.model.CacheModel<InfrastructureEntityProject> toCacheModel() {
        return _infrastructureEntityProject.toCacheModel();
    }

    @Override
    public int compareTo(
            InfrastructureEntityProject infrastructureEntityProject) {
        return _infrastructureEntityProject.compareTo(infrastructureEntityProject);
    }

    @Override
    public int hashCode() {
        return _infrastructureEntityProject.hashCode();
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _infrastructureEntityProject.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        _infrastructureEntityProject.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public java.lang.Object clone() {
        return new InfrastructureEntityProjectWrapper((InfrastructureEntityProject) _infrastructureEntityProject.clone());
    }

    /**
     * Returns the infrastructure entity name of this infrastructure entity project.
     *
     * @return the infrastructure entity name of this infrastructure entity project
     */
    @Override
    public java.lang.String getInfrastructureEntityName() {
        return _infrastructureEntityProject.getInfrastructureEntityName();
    }

    /**
     * Sets the infrastructure entity name of this infrastructure entity project.
     *
     * @param infrastructureEntityName the infrastructure entity name of this infrastructure entity project
     */
    @Override
    public void setInfrastructureEntityName(
            java.lang.String infrastructureEntityName) {
        _infrastructureEntityProject.setInfrastructureEntityName(infrastructureEntityName);
    }

    /**
     * Returns the infrastructure entity project ID of this infrastructure entity project.
     *
     * @return the infrastructure entity project ID of this infrastructure entity project
     */
    @Override
    public java.lang.String getInfrastructureEntityProjectId() {
        return _infrastructureEntityProject.getInfrastructureEntityProjectId();
    }

    /**
     * Sets the infrastructure entity project ID of this infrastructure entity project.
     *
     * @param infrastructureEntityProjectId the infrastructure entity project ID of this infrastructure entity project
     */
    @Override
    public void setInfrastructureEntityProjectId(
            java.lang.String infrastructureEntityProjectId) {
        _infrastructureEntityProject.setInfrastructureEntityProjectId(infrastructureEntityProjectId);
    }

    @Override
    public java.lang.String toString() {
        return _infrastructureEntityProject.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _infrastructureEntityProject.toXmlString();
    }

    /**
     * Returns the infrastructure entity project pk ID of this infrastructure entity project.
     *
     * @return the infrastructure entity project pk ID of this infrastructure entity project
     */
    @Override
    public long getInfrastructureEntityProjectPKId() {
        return _infrastructureEntityProject.getInfrastructureEntityProjectPKId();
    }

    /**
     * Sets the infrastructure entity project pk ID of this infrastructure entity project.
     *
     * @param infrastructureEntityProjectPKId the infrastructure entity project pk ID of this infrastructure entity project
     */
    @Override
    public void setInfrastructureEntityProjectPKId(
            long infrastructureEntityProjectPKId) {
        _infrastructureEntityProject.setInfrastructureEntityProjectPKId(infrastructureEntityProjectPKId);
    }

    /**
     * Returns the organization ID of this infrastructure entity project.
     *
     * @return the organization ID of this infrastructure entity project
     */
    @Override
    public long getOrganizationId() {
        return _infrastructureEntityProject.getOrganizationId();
    }

    /**
     * Sets the organization ID of this infrastructure entity project.
     *
     * @param organizationId the organization ID of this infrastructure entity project
     */
    @Override
    public void setOrganizationId(long organizationId) {
        _infrastructureEntityProject.setOrganizationId(organizationId);
    }

    /**
     * Returns the primary key of this infrastructure entity project.
     *
     * @return the primary key of this infrastructure entity project
     */
    @Override
    public long getPrimaryKey() {
        return _infrastructureEntityProject.getPrimaryKey();
    }

    /**
     * Sets the primary key of this infrastructure entity project.
     *
     * @param primaryKey the primary key of this infrastructure entity project
     */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _infrastructureEntityProject.setPrimaryKey(primaryKey);
    }

    @Override
    public void persist() {
        _infrastructureEntityProject.persist();
    }

    @Override
    public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
        _infrastructureEntityProject.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
            com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
        _infrastructureEntityProject.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
        _infrastructureEntityProject.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof InfrastructureEntityProjectWrapper)) {
            return false;
        }

        InfrastructureEntityProjectWrapper infrastructureEntityProjectWrapper = (InfrastructureEntityProjectWrapper) obj;

        if (Objects.equals(_infrastructureEntityProject,
                infrastructureEntityProjectWrapper._infrastructureEntityProject)) {
            return true;
        }

        return false;
    }

    @Override
    public InfrastructureEntityProject getWrappedModel() {
        return _infrastructureEntityProject;
    }

    @Override
    public boolean isEntityCacheEnabled() {
        return _infrastructureEntityProject.isEntityCacheEnabled();
    }

    @Override
    public boolean isFinderCacheEnabled() {
        return _infrastructureEntityProject.isFinderCacheEnabled();
    }

    @Override
    public void resetOriginalValues() {
        _infrastructureEntityProject.resetOriginalValues();
    }
}