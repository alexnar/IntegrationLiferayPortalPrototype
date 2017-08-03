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
import com.liferay.osgi.util.ServiceTrackerFactory;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import org.etan.portal.integration.prototype.projectservice.model.InfrastructureEntityProject;
import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the infrastructure entity project service. This utility wraps {@link org.etan.portal.integration.prototype.projectservice.service.persistence.impl.InfrastructureEntityProjectPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see InfrastructureEntityProjectPersistence
 * @see org.etan.portal.integration.prototype.projectservice.service.persistence.impl.InfrastructureEntityProjectPersistenceImpl
 * @generated
 */
@ProviderType
public class InfrastructureEntityProjectUtil {
    /*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

    private static ServiceTracker<InfrastructureEntityProjectPersistence, InfrastructureEntityProjectPersistence> _serviceTracker =
            ServiceTrackerFactory.open(InfrastructureEntityProjectPersistence.class);

    /**
     * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
     */
    public static void clearCache() {
        getPersistence().clearCache();
    }

    /**
     * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
     */
    public static void clearCache(
            InfrastructureEntityProject infrastructureEntityProject) {
        getPersistence().clearCache(infrastructureEntityProject);
    }

    /**
     * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
     */
    public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
        return getPersistence().countWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
     */
    public static List<InfrastructureEntityProject> findWithDynamicQuery(
            DynamicQuery dynamicQuery) {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<InfrastructureEntityProject> findWithDynamicQuery(
            DynamicQuery dynamicQuery, int start, int end) {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<InfrastructureEntityProject> findWithDynamicQuery(
            DynamicQuery dynamicQuery, int start, int end,
            OrderByComparator<InfrastructureEntityProject> orderByComparator) {
        return getPersistence()
                .findWithDynamicQuery(dynamicQuery, start, end,
                        orderByComparator);
    }

    /**
     * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
     */
    public static InfrastructureEntityProject update(
            InfrastructureEntityProject infrastructureEntityProject) {
        return getPersistence().update(infrastructureEntityProject);
    }

    /**
     * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
     */
    public static InfrastructureEntityProject update(
            InfrastructureEntityProject infrastructureEntityProject,
            ServiceContext serviceContext) {
        return getPersistence()
                .update(infrastructureEntityProject, serviceContext);
    }

    /**
     * Returns all the infrastructure entity projects where organizationId = &#63;.
     *
     * @param organizationId the organization ID
     * @return the matching infrastructure entity projects
     */
    public static List<InfrastructureEntityProject> findByOrganizationId(
            long organizationId) {
        return getPersistence().findByOrganizationId(organizationId);
    }

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
    public static List<InfrastructureEntityProject> findByOrganizationId(
            long organizationId, int start, int end) {
        return getPersistence().findByOrganizationId(organizationId, start, end);
    }

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
    public static List<InfrastructureEntityProject> findByOrganizationId(
            long organizationId, int start, int end,
            OrderByComparator<InfrastructureEntityProject> orderByComparator) {
        return getPersistence()
                .findByOrganizationId(organizationId, start, end,
                        orderByComparator);
    }

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
    public static List<InfrastructureEntityProject> findByOrganizationId(
            long organizationId, int start, int end,
            OrderByComparator<InfrastructureEntityProject> orderByComparator,
            boolean retrieveFromCache) {
        return getPersistence()
                .findByOrganizationId(organizationId, start, end,
                        orderByComparator, retrieveFromCache);
    }

    /**
     * Returns the first infrastructure entity project in the ordered set where organizationId = &#63;.
     *
     * @param organizationId the organization ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching infrastructure entity project
     * @throws NoSuchInfrastructureEntityProjectException if a matching infrastructure entity project could not be found
     */
    public static InfrastructureEntityProject findByOrganizationId_First(
            long organizationId,
            OrderByComparator<InfrastructureEntityProject> orderByComparator)
            throws org.etan.portal.integration.prototype.projectservice.exception.NoSuchInfrastructureEntityProjectException {
        return getPersistence()
                .findByOrganizationId_First(organizationId, orderByComparator);
    }

    /**
     * Returns the first infrastructure entity project in the ordered set where organizationId = &#63;.
     *
     * @param organizationId the organization ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching infrastructure entity project, or <code>null</code> if a matching infrastructure entity project could not be found
     */
    public static InfrastructureEntityProject fetchByOrganizationId_First(
            long organizationId,
            OrderByComparator<InfrastructureEntityProject> orderByComparator) {
        return getPersistence()
                .fetchByOrganizationId_First(organizationId,
                        orderByComparator);
    }

    /**
     * Returns the last infrastructure entity project in the ordered set where organizationId = &#63;.
     *
     * @param organizationId the organization ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching infrastructure entity project
     * @throws NoSuchInfrastructureEntityProjectException if a matching infrastructure entity project could not be found
     */
    public static InfrastructureEntityProject findByOrganizationId_Last(
            long organizationId,
            OrderByComparator<InfrastructureEntityProject> orderByComparator)
            throws org.etan.portal.integration.prototype.projectservice.exception.NoSuchInfrastructureEntityProjectException {
        return getPersistence()
                .findByOrganizationId_Last(organizationId, orderByComparator);
    }

    /**
     * Returns the last infrastructure entity project in the ordered set where organizationId = &#63;.
     *
     * @param organizationId the organization ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching infrastructure entity project, or <code>null</code> if a matching infrastructure entity project could not be found
     */
    public static InfrastructureEntityProject fetchByOrganizationId_Last(
            long organizationId,
            OrderByComparator<InfrastructureEntityProject> orderByComparator) {
        return getPersistence()
                .fetchByOrganizationId_Last(organizationId, orderByComparator);
    }

    /**
     * Returns the infrastructure entity projects before and after the current infrastructure entity project in the ordered set where organizationId = &#63;.
     *
     * @param infrastructureEntityProjectPKId the primary key of the current infrastructure entity project
     * @param organizationId the organization ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next infrastructure entity project
     * @throws NoSuchInfrastructureEntityProjectException if a infrastructure entity project with the primary key could not be found
     */
    public static InfrastructureEntityProject[] findByOrganizationId_PrevAndNext(
            long infrastructureEntityProjectPKId, long organizationId,
            OrderByComparator<InfrastructureEntityProject> orderByComparator)
            throws org.etan.portal.integration.prototype.projectservice.exception.NoSuchInfrastructureEntityProjectException {
        return getPersistence()
                .findByOrganizationId_PrevAndNext(infrastructureEntityProjectPKId,
                        organizationId, orderByComparator);
    }

    /**
     * Removes all the infrastructure entity projects where organizationId = &#63; from the database.
     *
     * @param organizationId the organization ID
     */
    public static void removeByOrganizationId(long organizationId) {
        getPersistence().removeByOrganizationId(organizationId);
    }

    /**
     * Returns the number of infrastructure entity projects where organizationId = &#63;.
     *
     * @param organizationId the organization ID
     * @return the number of matching infrastructure entity projects
     */
    public static int countByOrganizationId(long organizationId) {
        return getPersistence().countByOrganizationId(organizationId);
    }

    /**
     * Caches the infrastructure entity project in the entity cache if it is enabled.
     *
     * @param infrastructureEntityProject the infrastructure entity project
     */
    public static void cacheResult(
            InfrastructureEntityProject infrastructureEntityProject) {
        getPersistence().cacheResult(infrastructureEntityProject);
    }

    /**
     * Caches the infrastructure entity projects in the entity cache if it is enabled.
     *
     * @param infrastructureEntityProjects the infrastructure entity projects
     */
    public static void cacheResult(
            List<InfrastructureEntityProject> infrastructureEntityProjects) {
        getPersistence().cacheResult(infrastructureEntityProjects);
    }

    /**
     * Creates a new infrastructure entity project with the primary key. Does not add the infrastructure entity project to the database.
     *
     * @param infrastructureEntityProjectPKId the primary key for the new infrastructure entity project
     * @return the new infrastructure entity project
     */
    public static InfrastructureEntityProject create(
            long infrastructureEntityProjectPKId) {
        return getPersistence().create(infrastructureEntityProjectPKId);
    }

    /**
     * Removes the infrastructure entity project with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param infrastructureEntityProjectPKId the primary key of the infrastructure entity project
     * @return the infrastructure entity project that was removed
     * @throws NoSuchInfrastructureEntityProjectException if a infrastructure entity project with the primary key could not be found
     */
    public static InfrastructureEntityProject remove(
            long infrastructureEntityProjectPKId)
            throws org.etan.portal.integration.prototype.projectservice.exception.NoSuchInfrastructureEntityProjectException {
        return getPersistence().remove(infrastructureEntityProjectPKId);
    }

    public static InfrastructureEntityProject updateImpl(
            InfrastructureEntityProject infrastructureEntityProject) {
        return getPersistence().updateImpl(infrastructureEntityProject);
    }

    /**
     * Returns the infrastructure entity project with the primary key or throws a {@link NoSuchInfrastructureEntityProjectException} if it could not be found.
     *
     * @param infrastructureEntityProjectPKId the primary key of the infrastructure entity project
     * @return the infrastructure entity project
     * @throws NoSuchInfrastructureEntityProjectException if a infrastructure entity project with the primary key could not be found
     */
    public static InfrastructureEntityProject findByPrimaryKey(
            long infrastructureEntityProjectPKId)
            throws org.etan.portal.integration.prototype.projectservice.exception.NoSuchInfrastructureEntityProjectException {
        return getPersistence().findByPrimaryKey(infrastructureEntityProjectPKId);
    }

    /**
     * Returns the infrastructure entity project with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param infrastructureEntityProjectPKId the primary key of the infrastructure entity project
     * @return the infrastructure entity project, or <code>null</code> if a infrastructure entity project with the primary key could not be found
     */
    public static InfrastructureEntityProject fetchByPrimaryKey(
            long infrastructureEntityProjectPKId) {
        return getPersistence()
                .fetchByPrimaryKey(infrastructureEntityProjectPKId);
    }

    public static java.util.Map<java.io.Serializable, InfrastructureEntityProject> fetchByPrimaryKeys(
            java.util.Set<java.io.Serializable> primaryKeys) {
        return getPersistence().fetchByPrimaryKeys(primaryKeys);
    }

    /**
     * Returns all the infrastructure entity projects.
     *
     * @return the infrastructure entity projects
     */
    public static List<InfrastructureEntityProject> findAll() {
        return getPersistence().findAll();
    }

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
    public static List<InfrastructureEntityProject> findAll(int start, int end) {
        return getPersistence().findAll(start, end);
    }

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
    public static List<InfrastructureEntityProject> findAll(int start, int end,
                                                            OrderByComparator<InfrastructureEntityProject> orderByComparator) {
        return getPersistence().findAll(start, end, orderByComparator);
    }

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
    public static List<InfrastructureEntityProject> findAll(int start, int end,
                                                            OrderByComparator<InfrastructureEntityProject> orderByComparator,
                                                            boolean retrieveFromCache) {
        return getPersistence()
                .findAll(start, end, orderByComparator, retrieveFromCache);
    }

    /**
     * Removes all the infrastructure entity projects from the database.
     */
    public static void removeAll() {
        getPersistence().removeAll();
    }

    /**
     * Returns the number of infrastructure entity projects.
     *
     * @return the number of infrastructure entity projects
     */
    public static int countAll() {
        return getPersistence().countAll();
    }

    public static InfrastructureEntityProjectPersistence getPersistence() {
        return _serviceTracker.getService();
    }
}