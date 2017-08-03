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

package org.etan.portal.integration.prototype.projectservice.service.persistence;

import aQute.bnd.annotation.ProviderType;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import org.etan.portal.integration.prototype.projectservice.exception.NoSuchInfrastructureEntityProjectException;
import org.etan.portal.integration.prototype.projectservice.model.InfrastructureEntityProject;

/**
 * The persistence interface for the infrastructure entity project service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see org.etan.portal.integration.prototype.projectservice.service.persistence.impl.InfrastructureEntityProjectPersistenceImpl
 * @see InfrastructureEntityProjectUtil
 * @generated
 */
@ProviderType
public interface InfrastructureEntityProjectPersistence extends BasePersistence<InfrastructureEntityProject> {
    /*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link InfrastructureEntityProjectUtil} to access the infrastructure entity project persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

    /**
     * Returns all the infrastructure entity projects where organizationId = &#63;.
     *
     * @param organizationId the organization ID
     * @return the matching infrastructure entity projects
     */
    public java.util.List<InfrastructureEntityProject> findByOrganizationId(
            long organizationId);

    /**
     * Returns a range of all the infrastructure entity projects where organizationId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InfrastructureEntityProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param organizationId the organization ID
     * @param start the lower bound of the range of infrastructure entity projects
     * @param end the upper bound of the range of infrastructure entity projects (not inclusive)
     * @return the range of matching infrastructure entity projects
     */
    public java.util.List<InfrastructureEntityProject> findByOrganizationId(
            long organizationId, int start, int end);

    /**
     * Returns an ordered range of all the infrastructure entity projects where organizationId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InfrastructureEntityProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param organizationId the organization ID
     * @param start the lower bound of the range of infrastructure entity projects
     * @param end the upper bound of the range of infrastructure entity projects (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching infrastructure entity projects
     */
    public java.util.List<InfrastructureEntityProject> findByOrganizationId(
            long organizationId, int start, int end,
            com.liferay.portal.kernel.util.OrderByComparator<InfrastructureEntityProject> orderByComparator);

    /**
     * Returns an ordered range of all the infrastructure entity projects where organizationId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InfrastructureEntityProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param organizationId the organization ID
     * @param start the lower bound of the range of infrastructure entity projects
     * @param end the upper bound of the range of infrastructure entity projects (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @param retrieveFromCache whether to retrieve from the finder cache
     * @return the ordered range of matching infrastructure entity projects
     */
    public java.util.List<InfrastructureEntityProject> findByOrganizationId(
            long organizationId, int start, int end,
            com.liferay.portal.kernel.util.OrderByComparator<InfrastructureEntityProject> orderByComparator,
            boolean retrieveFromCache);

    /**
     * Returns the first infrastructure entity project in the ordered set where organizationId = &#63;.
     *
     * @param organizationId the organization ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching infrastructure entity project
     * @throws NoSuchInfrastructureEntityProjectException if a matching infrastructure entity project could not be found
     */
    public InfrastructureEntityProject findByOrganizationId_First(
            long organizationId,
            com.liferay.portal.kernel.util.OrderByComparator<InfrastructureEntityProject> orderByComparator)
            throws NoSuchInfrastructureEntityProjectException;

    /**
     * Returns the first infrastructure entity project in the ordered set where organizationId = &#63;.
     *
     * @param organizationId the organization ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching infrastructure entity project, or <code>null</code> if a matching infrastructure entity project could not be found
     */
    public InfrastructureEntityProject fetchByOrganizationId_First(
            long organizationId,
            com.liferay.portal.kernel.util.OrderByComparator<InfrastructureEntityProject> orderByComparator);

    /**
     * Returns the last infrastructure entity project in the ordered set where organizationId = &#63;.
     *
     * @param organizationId the organization ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching infrastructure entity project
     * @throws NoSuchInfrastructureEntityProjectException if a matching infrastructure entity project could not be found
     */
    public InfrastructureEntityProject findByOrganizationId_Last(
            long organizationId,
            com.liferay.portal.kernel.util.OrderByComparator<InfrastructureEntityProject> orderByComparator)
            throws NoSuchInfrastructureEntityProjectException;

    /**
     * Returns the last infrastructure entity project in the ordered set where organizationId = &#63;.
     *
     * @param organizationId the organization ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching infrastructure entity project, or <code>null</code> if a matching infrastructure entity project could not be found
     */
    public InfrastructureEntityProject fetchByOrganizationId_Last(
            long organizationId,
            com.liferay.portal.kernel.util.OrderByComparator<InfrastructureEntityProject> orderByComparator);

    /**
     * Returns the infrastructure entity projects before and after the current infrastructure entity project in the ordered set where organizationId = &#63;.
     *
     * @param infrastructureEntityProjectPKId the primary key of the current infrastructure entity project
     * @param organizationId the organization ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next infrastructure entity project
     * @throws NoSuchInfrastructureEntityProjectException if a infrastructure entity project with the primary key could not be found
     */
    public InfrastructureEntityProject[] findByOrganizationId_PrevAndNext(
            long infrastructureEntityProjectPKId, long organizationId,
            com.liferay.portal.kernel.util.OrderByComparator<InfrastructureEntityProject> orderByComparator)
            throws NoSuchInfrastructureEntityProjectException;

    /**
     * Removes all the infrastructure entity projects where organizationId = &#63; from the database.
     *
     * @param organizationId the organization ID
     */
    public void removeByOrganizationId(long organizationId);

    /**
     * Returns the number of infrastructure entity projects where organizationId = &#63;.
     *
     * @param organizationId the organization ID
     * @return the number of matching infrastructure entity projects
     */
    public int countByOrganizationId(long organizationId);

    /**
     * Caches the infrastructure entity project in the entity cache if it is enabled.
     *
     * @param infrastructureEntityProject the infrastructure entity project
     */
    public void cacheResult(
            InfrastructureEntityProject infrastructureEntityProject);

    /**
     * Caches the infrastructure entity projects in the entity cache if it is enabled.
     *
     * @param infrastructureEntityProjects the infrastructure entity projects
     */
    public void cacheResult(
            java.util.List<InfrastructureEntityProject> infrastructureEntityProjects);

    /**
     * Creates a new infrastructure entity project with the primary key. Does not add the infrastructure entity project to the database.
     *
     * @param infrastructureEntityProjectPKId the primary key for the new infrastructure entity project
     * @return the new infrastructure entity project
     */
    public InfrastructureEntityProject create(
            long infrastructureEntityProjectPKId);

    /**
     * Removes the infrastructure entity project with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param infrastructureEntityProjectPKId the primary key of the infrastructure entity project
     * @return the infrastructure entity project that was removed
     * @throws NoSuchInfrastructureEntityProjectException if a infrastructure entity project with the primary key could not be found
     */
    public InfrastructureEntityProject remove(
            long infrastructureEntityProjectPKId)
            throws NoSuchInfrastructureEntityProjectException;

    public InfrastructureEntityProject updateImpl(
            InfrastructureEntityProject infrastructureEntityProject);

    /**
     * Returns the infrastructure entity project with the primary key or throws a {@link NoSuchInfrastructureEntityProjectException} if it could not be found.
     *
     * @param infrastructureEntityProjectPKId the primary key of the infrastructure entity project
     * @return the infrastructure entity project
     * @throws NoSuchInfrastructureEntityProjectException if a infrastructure entity project with the primary key could not be found
     */
    public InfrastructureEntityProject findByPrimaryKey(
            long infrastructureEntityProjectPKId)
            throws NoSuchInfrastructureEntityProjectException;

    /**
     * Returns the infrastructure entity project with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param infrastructureEntityProjectPKId the primary key of the infrastructure entity project
     * @return the infrastructure entity project, or <code>null</code> if a infrastructure entity project with the primary key could not be found
     */
    public InfrastructureEntityProject fetchByPrimaryKey(
            long infrastructureEntityProjectPKId);

    @Override
    public java.util.Map<java.io.Serializable, InfrastructureEntityProject> fetchByPrimaryKeys(
            java.util.Set<java.io.Serializable> primaryKeys);

    /**
     * Returns all the infrastructure entity projects.
     *
     * @return the infrastructure entity projects
     */
    public java.util.List<InfrastructureEntityProject> findAll();

    /**
     * Returns a range of all the infrastructure entity projects.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InfrastructureEntityProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of infrastructure entity projects
     * @param end the upper bound of the range of infrastructure entity projects (not inclusive)
     * @return the range of infrastructure entity projects
     */
    public java.util.List<InfrastructureEntityProject> findAll(int start,
                                                               int end);

    /**
     * Returns an ordered range of all the infrastructure entity projects.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InfrastructureEntityProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of infrastructure entity projects
     * @param end the upper bound of the range of infrastructure entity projects (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of infrastructure entity projects
     */
    public java.util.List<InfrastructureEntityProject> findAll(int start,
                                                               int end,
                                                               com.liferay.portal.kernel.util.OrderByComparator<InfrastructureEntityProject> orderByComparator);

    /**
     * Returns an ordered range of all the infrastructure entity projects.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InfrastructureEntityProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of infrastructure entity projects
     * @param end the upper bound of the range of infrastructure entity projects (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @param retrieveFromCache whether to retrieve from the finder cache
     * @return the ordered range of infrastructure entity projects
     */
    public java.util.List<InfrastructureEntityProject> findAll(int start,
                                                               int end,
                                                               com.liferay.portal.kernel.util.OrderByComparator<InfrastructureEntityProject> orderByComparator,
                                                               boolean retrieveFromCache);

    /**
     * Removes all the infrastructure entity projects from the database.
     */
    public void removeAll();

    /**
     * Returns the number of infrastructure entity projects.
     *
     * @return the number of infrastructure entity projects
     */
    public int countAll();
}