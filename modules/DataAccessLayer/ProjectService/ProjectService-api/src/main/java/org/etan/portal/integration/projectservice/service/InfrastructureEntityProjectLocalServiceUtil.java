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

package org.etan.portal.integration.projectservice.service;

import aQute.bnd.annotation.ProviderType;
import com.liferay.osgi.util.ServiceTrackerFactory;
import org.etan.portal.integration.projectcontroller.service.dto.ProjectDto;
import org.etan.portal.integration.projectservice.model.InfrastructureEntityProject;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for InfrastructureEntityProject. This utility wraps
 * {@link org.etan.portal.integration.projectservice.service.impl.InfrastructureEntityProjectLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see InfrastructureEntityProjectLocalService
 * @see org.etan.portal.integration.projectservice.service.base.InfrastructureEntityProjectLocalServiceBaseImpl
 * @see org.etan.portal.integration.projectservice.service.impl.InfrastructureEntityProjectLocalServiceImpl
 * @generated
 */
@ProviderType
public class InfrastructureEntityProjectLocalServiceUtil {
    private static ServiceTracker<InfrastructureEntityProjectLocalService, InfrastructureEntityProjectLocalService> _serviceTracker =
            ServiceTrackerFactory.open(InfrastructureEntityProjectLocalService.class);

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link org.etan.portal.integration.projectservice.service.impl.InfrastructureEntityProjectLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
        return getService().getActionableDynamicQuery();
    }

    public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return getService().dynamicQuery();
    }

    public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
        return getService().getIndexableActionableDynamicQuery();
    }

    /**
     * @throws PortalException
     */
    public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
            com.liferay.portal.kernel.model.PersistedModel persistedModel)
            throws com.liferay.portal.kernel.exception.PortalException {
        return getService().deletePersistedModel(persistedModel);
    }

    public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
            java.io.Serializable primaryKeyObj)
            throws com.liferay.portal.kernel.exception.PortalException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
     * Returns the number of infrastructure entity projects.
     *
     * @return the number of infrastructure entity projects
     */
    public static int getInfrastructureEntityProjectsCount() {
        return getService().getInfrastructureEntityProjectsCount();
    }

    /**
     * Returns the OSGi service identifier.
     *
     * @return the OSGi service identifier
     */
    public static java.lang.String getOSGiServiceIdentifier() {
        return getService().getOSGiServiceIdentifier();
    }

    /**
     * Performs a dynamic query on the database and returns the matching rows.
     *
     * @param dynamicQuery the dynamic query
     * @return the matching rows
     */
    public static <T> java.util.List<T> dynamicQuery(
            com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
        return getService().dynamicQuery(dynamicQuery);
    }

    /**
     * Performs a dynamic query on the database and returns a range of the matching rows.
     * <p>
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.etan.portal.integration.projectservice.model.impl.InfrastructureEntityProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param dynamicQuery the dynamic query
     * @param start        the lower bound of the range of model instances
     * @param end          the upper bound of the range of model instances (not inclusive)
     * @return the range of matching rows
     */
    public static <T> java.util.List<T> dynamicQuery(
            com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
            int end) {
        return getService().dynamicQuery(dynamicQuery, start, end);
    }

    /**
     * Performs a dynamic query on the database and returns an ordered range of the matching rows.
     * <p>
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.etan.portal.integration.projectservice.model.impl.InfrastructureEntityProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param dynamicQuery      the dynamic query
     * @param start             the lower bound of the range of model instances
     * @param end               the upper bound of the range of model instances (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching rows
     */
    public static <T> java.util.List<T> dynamicQuery(
            com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
            int end,
            com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
        return getService()
                .dynamicQuery(dynamicQuery, start, end, orderByComparator);
    }

    /**
     * Get InfrastructureEntityProject by organizationId
     *
     * @param organizationId id of organization
     * @return InfrastructureEntityProject
     */
    public static java.util.List<InfrastructureEntityProject> findByOrganizationId(
            long organizationId) {
        return getService().findByOrganizationId(organizationId);
    }

    /**
     * Returns a range of all the infrastructure entity projects.
     * <p>
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.etan.portal.integration.projectservice.model.impl.InfrastructureEntityProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of infrastructure entity projects
     * @param end   the upper bound of the range of infrastructure entity projects (not inclusive)
     * @return the range of infrastructure entity projects
     */
    public static java.util.List<InfrastructureEntityProject> getInfrastructureEntityProjects(
            int start, int end) {
        return getService().getInfrastructureEntityProjects(start, end);
    }

    /**
     * Returns the number of rows matching the dynamic query.
     *
     * @param dynamicQuery the dynamic query
     * @return the number of rows matching the dynamic query
     */
    public static long dynamicQueryCount(
            com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
        return getService().dynamicQueryCount(dynamicQuery);
    }

    /**
     * Returns the number of rows matching the dynamic query.
     *
     * @param dynamicQuery the dynamic query
     * @param projection the projection to apply to the query
     * @return the number of rows matching the dynamic query
     */
    public static long dynamicQueryCount(
            com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
            com.liferay.portal.kernel.dao.orm.Projection projection) {
        return getService().dynamicQueryCount(dynamicQuery, projection);
    }

    /**
     * Adds the infrastructure entity project to the database. Also notifies the appropriate model listeners.
     *
     * @param infrastructureEntityProject the infrastructure entity project
     * @return the infrastructure entity project that was added
     */
    public static InfrastructureEntityProject addInfrastructureEntityProject(
            InfrastructureEntityProject infrastructureEntityProject) {
        return getService()
                .addInfrastructureEntityProject(infrastructureEntityProject);
    }

    /**
     * Creates a new infrastructure entity project with the primary key. Does not add the infrastructure entity project to the database.
     *
     * @param infrastructureEntityProjectPKId the primary key for the new infrastructure entity project
     * @return the new infrastructure entity project
     */
    public static InfrastructureEntityProject createInfrastructureEntityProject(
            long infrastructureEntityProjectPKId) {
        return getService()
                .createInfrastructureEntityProject(infrastructureEntityProjectPKId);
    }

    /**
     * Deletes the infrastructure entity project with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param infrastructureEntityProjectPKId the primary key of the infrastructure entity project
     * @return the infrastructure entity project that was removed
     * @throws PortalException if a infrastructure entity project with the primary key could not be found
     */
    public static InfrastructureEntityProject deleteInfrastructureEntityProject(
            long infrastructureEntityProjectPKId)
            throws com.liferay.portal.kernel.exception.PortalException {
        return getService()
                .deleteInfrastructureEntityProject(infrastructureEntityProjectPKId);
    }

    /**
     * Deletes the infrastructure entity project from the database. Also notifies the appropriate model listeners.
     *
     * @param infrastructureEntityProject the infrastructure entity project
     * @return the infrastructure entity project that was removed
     */
    public static InfrastructureEntityProject deleteInfrastructureEntityProject(
            InfrastructureEntityProject infrastructureEntityProject) {
        return getService()
                .deleteInfrastructureEntityProject(infrastructureEntityProject);
    }

    public static InfrastructureEntityProject fetchInfrastructureEntityProject(
            long infrastructureEntityProjectPKId) {
        return getService()
                .fetchInfrastructureEntityProject(infrastructureEntityProjectPKId);
    }

    /**
     * Returns the infrastructure entity project with the primary key.
     *
     * @param infrastructureEntityProjectPKId the primary key of the infrastructure entity project
     * @return the infrastructure entity project
     * @throws PortalException if a infrastructure entity project with the primary key could not be found
     */
    public static InfrastructureEntityProject getInfrastructureEntityProject(
            long infrastructureEntityProjectPKId)
            throws com.liferay.portal.kernel.exception.PortalException {
        return getService()
                .getInfrastructureEntityProject(infrastructureEntityProjectPKId);
    }

    /**
     * Updates the infrastructure entity project in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param infrastructureEntityProject the infrastructure entity project
     * @return the infrastructure entity project that was updated
     */
    public static InfrastructureEntityProject updateInfrastructureEntityProject(
            InfrastructureEntityProject infrastructureEntityProject) {
        return getService()
                .updateInfrastructureEntityProject(infrastructureEntityProject);
    }

    /**
     * Add all InfrastructureEntityProjects from ProjectDto.
     */
	public static void saveAllInfrastructureEntityProjects(
		ProjectDto projectDto) {
		getService().saveAllInfrastructureEntityProjects(projectDto);
	}

	public static InfrastructureEntityProjectLocalService getService() {
		return _serviceTracker.getService();
	}
}