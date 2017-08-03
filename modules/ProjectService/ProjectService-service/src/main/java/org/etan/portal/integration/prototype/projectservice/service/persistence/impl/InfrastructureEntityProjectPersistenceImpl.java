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

package org.etan.portal.integration.prototype.projectservice.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;
import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.spring.extender.service.ServiceReference;
import org.etan.portal.integration.prototype.projectservice.exception.NoSuchInfrastructureEntityProjectException;
import org.etan.portal.integration.prototype.projectservice.model.InfrastructureEntityProject;
import org.etan.portal.integration.prototype.projectservice.model.impl.InfrastructureEntityProjectImpl;
import org.etan.portal.integration.prototype.projectservice.model.impl.InfrastructureEntityProjectModelImpl;
import org.etan.portal.integration.prototype.projectservice.service.persistence.InfrastructureEntityProjectPersistence;

import java.io.Serializable;
import java.util.*;

/**
 * The persistence implementation for the infrastructure entity project service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see InfrastructureEntityProjectPersistence
 * @see org.etan.portal.integration.prototype.projectservice.service.persistence.InfrastructureEntityProjectUtil
 * @generated
 */
@ProviderType
public class InfrastructureEntityProjectPersistenceImpl
        extends BasePersistenceImpl<InfrastructureEntityProject>
        implements InfrastructureEntityProjectPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link InfrastructureEntityProjectUtil} to access the infrastructure entity project persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = InfrastructureEntityProjectImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
            ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
            ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(InfrastructureEntityProjectModelImpl.ENTITY_CACHE_ENABLED,
            InfrastructureEntityProjectModelImpl.FINDER_CACHE_ENABLED,
            InfrastructureEntityProjectImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(InfrastructureEntityProjectModelImpl.ENTITY_CACHE_ENABLED,
            InfrastructureEntityProjectModelImpl.FINDER_CACHE_ENABLED,
            InfrastructureEntityProjectImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(InfrastructureEntityProjectModelImpl.ENTITY_CACHE_ENABLED,
            InfrastructureEntityProjectModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
            new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ORGANIZATIONID =
            new FinderPath(InfrastructureEntityProjectModelImpl.ENTITY_CACHE_ENABLED,
                    InfrastructureEntityProjectModelImpl.FINDER_CACHE_ENABLED,
                    InfrastructureEntityProjectImpl.class,
                    FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByOrganizationId",
                    new String[]{
                            Long.class.getName(),

                            Integer.class.getName(), Integer.class.getName(),
                            OrderByComparator.class.getName()
                    });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORGANIZATIONID =
            new FinderPath(InfrastructureEntityProjectModelImpl.ENTITY_CACHE_ENABLED,
                    InfrastructureEntityProjectModelImpl.FINDER_CACHE_ENABLED,
                    InfrastructureEntityProjectImpl.class,
                    FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByOrganizationId",
                    new String[]{Long.class.getName()},
                    InfrastructureEntityProjectModelImpl.ORGANIZATIONID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ORGANIZATIONID = new FinderPath(InfrastructureEntityProjectModelImpl.ENTITY_CACHE_ENABLED,
            InfrastructureEntityProjectModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByOrganizationId", new String[]{Long.class.getName()});
    private static final String _FINDER_COLUMN_ORGANIZATIONID_ORGANIZATIONID_2 = "infrastructureEntityProject.organizationId = ?";
    private static final String _SQL_SELECT_INFRASTRUCTUREENTITYPROJECT = "SELECT infrastructureEntityProject FROM InfrastructureEntityProject infrastructureEntityProject";
    private static final String _SQL_SELECT_INFRASTRUCTUREENTITYPROJECT_WHERE_PKS_IN =
            "SELECT infrastructureEntityProject FROM InfrastructureEntityProject infrastructureEntityProject WHERE infrastructureEntityProjectPKId IN (";
    private static final String _SQL_SELECT_INFRASTRUCTUREENTITYPROJECT_WHERE = "SELECT infrastructureEntityProject FROM InfrastructureEntityProject infrastructureEntityProject WHERE ";
    private static final String _SQL_COUNT_INFRASTRUCTUREENTITYPROJECT = "SELECT COUNT(infrastructureEntityProject) FROM InfrastructureEntityProject infrastructureEntityProject";
    private static final String _SQL_COUNT_INFRASTRUCTUREENTITYPROJECT_WHERE = "SELECT COUNT(infrastructureEntityProject) FROM InfrastructureEntityProject infrastructureEntityProject WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "infrastructureEntityProject.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No InfrastructureEntityProject exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No InfrastructureEntityProject exists with the key {";
    private static final Log _log = LogFactoryUtil.getLog(InfrastructureEntityProjectPersistenceImpl.class);
    @ServiceReference(type = EntityCache.class)
    protected EntityCache entityCache;
    @ServiceReference(type = FinderCache.class)
    protected FinderCache finderCache;

    public InfrastructureEntityProjectPersistenceImpl() {
        setModelClass(InfrastructureEntityProject.class);
    }

    /**
     * Returns all the infrastructure entity projects where organizationId = &#63;.
     *
     * @param organizationId the organization ID
     * @return the matching infrastructure entity projects
     */
    @Override
    public List<InfrastructureEntityProject> findByOrganizationId(
            long organizationId) {
        return findByOrganizationId(organizationId, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null);
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
    @Override
    public List<InfrastructureEntityProject> findByOrganizationId(
            long organizationId, int start, int end) {
        return findByOrganizationId(organizationId, start, end, null);
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
    @Override
    public List<InfrastructureEntityProject> findByOrganizationId(
            long organizationId, int start, int end,
            OrderByComparator<InfrastructureEntityProject> orderByComparator) {
        return findByOrganizationId(organizationId, start, end,
                orderByComparator, true);
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
    @Override
    public List<InfrastructureEntityProject> findByOrganizationId(
            long organizationId, int start, int end,
            OrderByComparator<InfrastructureEntityProject> orderByComparator,
            boolean retrieveFromCache) {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORGANIZATIONID;
            finderArgs = new Object[]{organizationId};
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ORGANIZATIONID;
            finderArgs = new Object[]{
                    organizationId,

                    start, end, orderByComparator
            };
        }

        List<InfrastructureEntityProject> list = null;

        if (retrieveFromCache) {
            list = (List<InfrastructureEntityProject>) finderCache.getResult(finderPath,
                    finderArgs, this);

            if ((list != null) && !list.isEmpty()) {
                for (InfrastructureEntityProject infrastructureEntityProject : list) {
                    if ((organizationId != infrastructureEntityProject.getOrganizationId())) {
                        list = null;

                        break;
                    }
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 2));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_INFRASTRUCTUREENTITYPROJECT_WHERE);

            query.append(_FINDER_COLUMN_ORGANIZATIONID_ORGANIZATIONID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                        orderByComparator);
            } else if (pagination) {
                query.append(InfrastructureEntityProjectModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(organizationId);

                if (!pagination) {
                    list = (List<InfrastructureEntityProject>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = Collections.unmodifiableList(list);
                } else {
                    list = (List<InfrastructureEntityProject>) QueryUtil.list(q,
                            getDialect(), start, end);
                }

                cacheResult(list);

                finderCache.putResult(finderPath, finderArgs, list);
            } catch (Exception e) {
                finderCache.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Returns the first infrastructure entity project in the ordered set where organizationId = &#63;.
     *
     * @param organizationId the organization ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching infrastructure entity project
     * @throws NoSuchInfrastructureEntityProjectException if a matching infrastructure entity project could not be found
     */
    @Override
    public InfrastructureEntityProject findByOrganizationId_First(
            long organizationId,
            OrderByComparator<InfrastructureEntityProject> orderByComparator)
            throws NoSuchInfrastructureEntityProjectException {
        InfrastructureEntityProject infrastructureEntityProject = fetchByOrganizationId_First(organizationId,
                orderByComparator);

        if (infrastructureEntityProject != null) {
            return infrastructureEntityProject;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("organizationId=");
        msg.append(organizationId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchInfrastructureEntityProjectException(msg.toString());
    }

    /**
     * Returns the first infrastructure entity project in the ordered set where organizationId = &#63;.
     *
     * @param organizationId the organization ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching infrastructure entity project, or <code>null</code> if a matching infrastructure entity project could not be found
     */
    @Override
    public InfrastructureEntityProject fetchByOrganizationId_First(
            long organizationId,
            OrderByComparator<InfrastructureEntityProject> orderByComparator) {
        List<InfrastructureEntityProject> list = findByOrganizationId(organizationId,
                0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last infrastructure entity project in the ordered set where organizationId = &#63;.
     *
     * @param organizationId the organization ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching infrastructure entity project
     * @throws NoSuchInfrastructureEntityProjectException if a matching infrastructure entity project could not be found
     */
    @Override
    public InfrastructureEntityProject findByOrganizationId_Last(
            long organizationId,
            OrderByComparator<InfrastructureEntityProject> orderByComparator)
            throws NoSuchInfrastructureEntityProjectException {
        InfrastructureEntityProject infrastructureEntityProject = fetchByOrganizationId_Last(organizationId,
                orderByComparator);

        if (infrastructureEntityProject != null) {
            return infrastructureEntityProject;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("organizationId=");
        msg.append(organizationId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchInfrastructureEntityProjectException(msg.toString());
    }

    /**
     * Returns the last infrastructure entity project in the ordered set where organizationId = &#63;.
     *
     * @param organizationId the organization ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching infrastructure entity project, or <code>null</code> if a matching infrastructure entity project could not be found
     */
    @Override
    public InfrastructureEntityProject fetchByOrganizationId_Last(
            long organizationId,
            OrderByComparator<InfrastructureEntityProject> orderByComparator) {
        int count = countByOrganizationId(organizationId);

        if (count == 0) {
            return null;
        }

        List<InfrastructureEntityProject> list = findByOrganizationId(organizationId,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
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
    @Override
    public InfrastructureEntityProject[] findByOrganizationId_PrevAndNext(
            long infrastructureEntityProjectPKId, long organizationId,
            OrderByComparator<InfrastructureEntityProject> orderByComparator)
            throws NoSuchInfrastructureEntityProjectException {
        InfrastructureEntityProject infrastructureEntityProject = findByPrimaryKey(infrastructureEntityProjectPKId);

        Session session = null;

        try {
            session = openSession();

            InfrastructureEntityProject[] array = new InfrastructureEntityProjectImpl[3];

            array[0] = getByOrganizationId_PrevAndNext(session,
                    infrastructureEntityProject, organizationId,
                    orderByComparator, true);

            array[1] = infrastructureEntityProject;

            array[2] = getByOrganizationId_PrevAndNext(session,
                    infrastructureEntityProject, organizationId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected InfrastructureEntityProject getByOrganizationId_PrevAndNext(
            Session session,
            InfrastructureEntityProject infrastructureEntityProject,
            long organizationId,
            OrderByComparator<InfrastructureEntityProject> orderByComparator,
            boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(4 +
                    (orderByComparator.getOrderByConditionFields().length * 3) +
                    (orderByComparator.getOrderByFields().length * 3));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_INFRASTRUCTUREENTITYPROJECT_WHERE);

        query.append(_FINDER_COLUMN_ORGANIZATIONID_ORGANIZATIONID_2);

        if (orderByComparator != null) {
            String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

            if (orderByConditionFields.length > 0) {
                query.append(WHERE_AND);
            }

            for (int i = 0; i < orderByConditionFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByConditionFields[i]);

                if ((i + 1) < orderByConditionFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN_HAS_NEXT);
                    } else {
                        query.append(WHERE_LESSER_THAN_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN);
                    } else {
                        query.append(WHERE_LESSER_THAN);
                    }
                }
            }

            query.append(ORDER_BY_CLAUSE);

            String[] orderByFields = orderByComparator.getOrderByFields();

            for (int i = 0; i < orderByFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByFields[i]);

                if ((i + 1) < orderByFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC_HAS_NEXT);
                    } else {
                        query.append(ORDER_BY_DESC_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC);
                    } else {
                        query.append(ORDER_BY_DESC);
                    }
                }
            }
        } else {
            query.append(InfrastructureEntityProjectModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(organizationId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(infrastructureEntityProject);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<InfrastructureEntityProject> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the infrastructure entity projects where organizationId = &#63; from the database.
     *
     * @param organizationId the organization ID
     */
    @Override
    public void removeByOrganizationId(long organizationId) {
        for (InfrastructureEntityProject infrastructureEntityProject : findByOrganizationId(
                organizationId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(infrastructureEntityProject);
        }
    }

    /**
     * Returns the number of infrastructure entity projects where organizationId = &#63;.
     *
     * @param organizationId the organization ID
     * @return the number of matching infrastructure entity projects
     */
    @Override
    public int countByOrganizationId(long organizationId) {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ORGANIZATIONID;

        Object[] finderArgs = new Object[]{organizationId};

        Long count = (Long) finderCache.getResult(finderPath, finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_INFRASTRUCTUREENTITYPROJECT_WHERE);

            query.append(_FINDER_COLUMN_ORGANIZATIONID_ORGANIZATIONID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(organizationId);

                count = (Long) q.uniqueResult();

                finderCache.putResult(finderPath, finderArgs, count);
            } catch (Exception e) {
                finderCache.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Caches the infrastructure entity project in the entity cache if it is enabled.
     *
     * @param infrastructureEntityProject the infrastructure entity project
     */
    @Override
    public void cacheResult(
            InfrastructureEntityProject infrastructureEntityProject) {
        entityCache.putResult(InfrastructureEntityProjectModelImpl.ENTITY_CACHE_ENABLED,
                InfrastructureEntityProjectImpl.class,
                infrastructureEntityProject.getPrimaryKey(),
                infrastructureEntityProject);

        infrastructureEntityProject.resetOriginalValues();
    }

    /**
     * Caches the infrastructure entity projects in the entity cache if it is enabled.
     *
     * @param infrastructureEntityProjects the infrastructure entity projects
     */
    @Override
    public void cacheResult(
            List<InfrastructureEntityProject> infrastructureEntityProjects) {
        for (InfrastructureEntityProject infrastructureEntityProject : infrastructureEntityProjects) {
            if (entityCache.getResult(
                    InfrastructureEntityProjectModelImpl.ENTITY_CACHE_ENABLED,
                    InfrastructureEntityProjectImpl.class,
                    infrastructureEntityProject.getPrimaryKey()) == null) {
                cacheResult(infrastructureEntityProject);
            } else {
                infrastructureEntityProject.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all infrastructure entity projects.
     *
     * <p>
     * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        entityCache.clearCache(InfrastructureEntityProjectImpl.class);

        finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
        finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the infrastructure entity project.
     *
     * <p>
     * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(
            InfrastructureEntityProject infrastructureEntityProject) {
        entityCache.removeResult(InfrastructureEntityProjectModelImpl.ENTITY_CACHE_ENABLED,
                InfrastructureEntityProjectImpl.class,
                infrastructureEntityProject.getPrimaryKey());

        finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
            List<InfrastructureEntityProject> infrastructureEntityProjects) {
        finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (InfrastructureEntityProject infrastructureEntityProject : infrastructureEntityProjects) {
            entityCache.removeResult(InfrastructureEntityProjectModelImpl.ENTITY_CACHE_ENABLED,
                    InfrastructureEntityProjectImpl.class,
                    infrastructureEntityProject.getPrimaryKey());
        }
    }

    /**
     * Creates a new infrastructure entity project with the primary key. Does not add the infrastructure entity project to the database.
     *
     * @param infrastructureEntityProjectPKId the primary key for the new infrastructure entity project
     * @return the new infrastructure entity project
     */
    @Override
    public InfrastructureEntityProject create(
            long infrastructureEntityProjectPKId) {
        InfrastructureEntityProject infrastructureEntityProject = new InfrastructureEntityProjectImpl();

        infrastructureEntityProject.setNew(true);
        infrastructureEntityProject.setPrimaryKey(infrastructureEntityProjectPKId);

        return infrastructureEntityProject;
    }

    /**
     * Removes the infrastructure entity project with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param infrastructureEntityProjectPKId the primary key of the infrastructure entity project
     * @return the infrastructure entity project that was removed
     * @throws NoSuchInfrastructureEntityProjectException if a infrastructure entity project with the primary key could not be found
     */
    @Override
    public InfrastructureEntityProject remove(
            long infrastructureEntityProjectPKId)
            throws NoSuchInfrastructureEntityProjectException {
        return remove((Serializable) infrastructureEntityProjectPKId);
    }

    /**
     * Removes the infrastructure entity project with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the infrastructure entity project
     * @return the infrastructure entity project that was removed
     * @throws NoSuchInfrastructureEntityProjectException if a infrastructure entity project with the primary key could not be found
     */
    @Override
    public InfrastructureEntityProject remove(Serializable primaryKey)
            throws NoSuchInfrastructureEntityProjectException {
        Session session = null;

        try {
            session = openSession();

            InfrastructureEntityProject infrastructureEntityProject = (InfrastructureEntityProject) session.get(InfrastructureEntityProjectImpl.class,
                    primaryKey);

            if (infrastructureEntityProject == null) {
                if (_log.isDebugEnabled()) {
                    _log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchInfrastructureEntityProjectException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                        primaryKey);
            }

            return remove(infrastructureEntityProject);
        } catch (NoSuchInfrastructureEntityProjectException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected InfrastructureEntityProject removeImpl(
            InfrastructureEntityProject infrastructureEntityProject) {
        infrastructureEntityProject = toUnwrappedModel(infrastructureEntityProject);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(infrastructureEntityProject)) {
                infrastructureEntityProject = (InfrastructureEntityProject) session.get(InfrastructureEntityProjectImpl.class,
                        infrastructureEntityProject.getPrimaryKeyObj());
            }

            if (infrastructureEntityProject != null) {
                session.delete(infrastructureEntityProject);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (infrastructureEntityProject != null) {
            clearCache(infrastructureEntityProject);
        }

        return infrastructureEntityProject;
    }

    @Override
    public InfrastructureEntityProject updateImpl(
            InfrastructureEntityProject infrastructureEntityProject) {
        infrastructureEntityProject = toUnwrappedModel(infrastructureEntityProject);

        boolean isNew = infrastructureEntityProject.isNew();

        InfrastructureEntityProjectModelImpl infrastructureEntityProjectModelImpl =
                (InfrastructureEntityProjectModelImpl) infrastructureEntityProject;

        Session session = null;

        try {
            session = openSession();

            if (infrastructureEntityProject.isNew()) {
                session.save(infrastructureEntityProject);

                infrastructureEntityProject.setNew(false);
            } else {
                infrastructureEntityProject = (InfrastructureEntityProject) session.merge(infrastructureEntityProject);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew ||
                !InfrastructureEntityProjectModelImpl.COLUMN_BITMASK_ENABLED) {
            finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        } else {
            if ((infrastructureEntityProjectModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORGANIZATIONID.getColumnBitmask()) != 0) {
                Object[] args = new Object[]{
                        infrastructureEntityProjectModelImpl.getOriginalOrganizationId()
                };

                finderCache.removeResult(FINDER_PATH_COUNT_BY_ORGANIZATIONID,
                        args);
                finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORGANIZATIONID,
                        args);

                args = new Object[]{
                        infrastructureEntityProjectModelImpl.getOrganizationId()
                };

                finderCache.removeResult(FINDER_PATH_COUNT_BY_ORGANIZATIONID,
                        args);
                finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORGANIZATIONID,
                        args);
            }
        }

        entityCache.putResult(InfrastructureEntityProjectModelImpl.ENTITY_CACHE_ENABLED,
                InfrastructureEntityProjectImpl.class,
                infrastructureEntityProject.getPrimaryKey(),
                infrastructureEntityProject, false);

        infrastructureEntityProject.resetOriginalValues();

        return infrastructureEntityProject;
    }

    protected InfrastructureEntityProject toUnwrappedModel(
            InfrastructureEntityProject infrastructureEntityProject) {
        if (infrastructureEntityProject instanceof InfrastructureEntityProjectImpl) {
            return infrastructureEntityProject;
        }

        InfrastructureEntityProjectImpl infrastructureEntityProjectImpl = new InfrastructureEntityProjectImpl();

        infrastructureEntityProjectImpl.setNew(infrastructureEntityProject.isNew());
        infrastructureEntityProjectImpl.setPrimaryKey(infrastructureEntityProject.getPrimaryKey());

        infrastructureEntityProjectImpl.setInfrastructureEntityProjectPKId(infrastructureEntityProject.getInfrastructureEntityProjectPKId());
        infrastructureEntityProjectImpl.setOrganizationId(infrastructureEntityProject.getOrganizationId());
        infrastructureEntityProjectImpl.setInfrastructureEntityName(infrastructureEntityProject.getInfrastructureEntityName());
        infrastructureEntityProjectImpl.setInfrastructureEntityProjectId(infrastructureEntityProject.getInfrastructureEntityProjectId());

        return infrastructureEntityProjectImpl;
    }

    /**
     * Returns the infrastructure entity project with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the infrastructure entity project
     * @return the infrastructure entity project
     * @throws NoSuchInfrastructureEntityProjectException if a infrastructure entity project with the primary key could not be found
     */
    @Override
    public InfrastructureEntityProject findByPrimaryKey(Serializable primaryKey)
            throws NoSuchInfrastructureEntityProjectException {
        InfrastructureEntityProject infrastructureEntityProject = fetchByPrimaryKey(primaryKey);

        if (infrastructureEntityProject == null) {
            if (_log.isDebugEnabled()) {
                _log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchInfrastructureEntityProjectException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
        }

        return infrastructureEntityProject;
    }

    /**
     * Returns the infrastructure entity project with the primary key or throws a {@link NoSuchInfrastructureEntityProjectException} if it could not be found.
     *
     * @param infrastructureEntityProjectPKId the primary key of the infrastructure entity project
     * @return the infrastructure entity project
     * @throws NoSuchInfrastructureEntityProjectException if a infrastructure entity project with the primary key could not be found
     */
    @Override
    public InfrastructureEntityProject findByPrimaryKey(
            long infrastructureEntityProjectPKId)
            throws NoSuchInfrastructureEntityProjectException {
        return findByPrimaryKey((Serializable) infrastructureEntityProjectPKId);
    }

    /**
     * Returns the infrastructure entity project with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the infrastructure entity project
     * @return the infrastructure entity project, or <code>null</code> if a infrastructure entity project with the primary key could not be found
     */
    @Override
    public InfrastructureEntityProject fetchByPrimaryKey(
            Serializable primaryKey) {
        Serializable serializable = entityCache.getResult(InfrastructureEntityProjectModelImpl.ENTITY_CACHE_ENABLED,
                InfrastructureEntityProjectImpl.class, primaryKey);

        if (serializable == nullModel) {
            return null;
        }

        InfrastructureEntityProject infrastructureEntityProject = (InfrastructureEntityProject) serializable;

        if (infrastructureEntityProject == null) {
            Session session = null;

            try {
                session = openSession();

                infrastructureEntityProject = (InfrastructureEntityProject) session.get(InfrastructureEntityProjectImpl.class,
                        primaryKey);

                if (infrastructureEntityProject != null) {
                    cacheResult(infrastructureEntityProject);
                } else {
                    entityCache.putResult(InfrastructureEntityProjectModelImpl.ENTITY_CACHE_ENABLED,
                            InfrastructureEntityProjectImpl.class, primaryKey,
                            nullModel);
                }
            } catch (Exception e) {
                entityCache.removeResult(InfrastructureEntityProjectModelImpl.ENTITY_CACHE_ENABLED,
                        InfrastructureEntityProjectImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return infrastructureEntityProject;
    }

    /**
     * Returns the infrastructure entity project with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param infrastructureEntityProjectPKId the primary key of the infrastructure entity project
     * @return the infrastructure entity project, or <code>null</code> if a infrastructure entity project with the primary key could not be found
     */
    @Override
    public InfrastructureEntityProject fetchByPrimaryKey(
            long infrastructureEntityProjectPKId) {
        return fetchByPrimaryKey((Serializable) infrastructureEntityProjectPKId);
    }

    @Override
    public Map<Serializable, InfrastructureEntityProject> fetchByPrimaryKeys(
            Set<Serializable> primaryKeys) {
        if (primaryKeys.isEmpty()) {
            return Collections.emptyMap();
        }

        Map<Serializable, InfrastructureEntityProject> map = new HashMap<Serializable, InfrastructureEntityProject>();

        if (primaryKeys.size() == 1) {
            Iterator<Serializable> iterator = primaryKeys.iterator();

            Serializable primaryKey = iterator.next();

            InfrastructureEntityProject infrastructureEntityProject = fetchByPrimaryKey(primaryKey);

            if (infrastructureEntityProject != null) {
                map.put(primaryKey, infrastructureEntityProject);
            }

            return map;
        }

        Set<Serializable> uncachedPrimaryKeys = null;

        for (Serializable primaryKey : primaryKeys) {
            Serializable serializable = entityCache.getResult(InfrastructureEntityProjectModelImpl.ENTITY_CACHE_ENABLED,
                    InfrastructureEntityProjectImpl.class, primaryKey);

            if (serializable != nullModel) {
                if (serializable == null) {
                    if (uncachedPrimaryKeys == null) {
                        uncachedPrimaryKeys = new HashSet<Serializable>();
                    }

                    uncachedPrimaryKeys.add(primaryKey);
                } else {
                    map.put(primaryKey,
                            (InfrastructureEntityProject) serializable);
                }
            }
        }

        if (uncachedPrimaryKeys == null) {
            return map;
        }

        StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
                1);

        query.append(_SQL_SELECT_INFRASTRUCTUREENTITYPROJECT_WHERE_PKS_IN);

        for (Serializable primaryKey : uncachedPrimaryKeys) {
            query.append(String.valueOf(primaryKey));

            query.append(StringPool.COMMA);
        }

        query.setIndex(query.index() - 1);

        query.append(StringPool.CLOSE_PARENTHESIS);

        String sql = query.toString();

        Session session = null;

        try {
            session = openSession();

            Query q = session.createQuery(sql);

            for (InfrastructureEntityProject infrastructureEntityProject : (List<InfrastructureEntityProject>) q.list()) {
                map.put(infrastructureEntityProject.getPrimaryKeyObj(),
                        infrastructureEntityProject);

                cacheResult(infrastructureEntityProject);

                uncachedPrimaryKeys.remove(infrastructureEntityProject.getPrimaryKeyObj());
            }

            for (Serializable primaryKey : uncachedPrimaryKeys) {
                entityCache.putResult(InfrastructureEntityProjectModelImpl.ENTITY_CACHE_ENABLED,
                        InfrastructureEntityProjectImpl.class, primaryKey, nullModel);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        return map;
    }

    /**
     * Returns all the infrastructure entity projects.
     *
     * @return the infrastructure entity projects
     */
    @Override
    public List<InfrastructureEntityProject> findAll() {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
    @Override
    public List<InfrastructureEntityProject> findAll(int start, int end) {
        return findAll(start, end, null);
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
    @Override
    public List<InfrastructureEntityProject> findAll(int start, int end,
                                                     OrderByComparator<InfrastructureEntityProject> orderByComparator) {
        return findAll(start, end, orderByComparator, true);
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
    @Override
    public List<InfrastructureEntityProject> findAll(int start, int end,
                                                     OrderByComparator<InfrastructureEntityProject> orderByComparator,
                                                     boolean retrieveFromCache) {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
            finderArgs = FINDER_ARGS_EMPTY;
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
            finderArgs = new Object[]{start, end, orderByComparator};
        }

        List<InfrastructureEntityProject> list = null;

        if (retrieveFromCache) {
            list = (List<InfrastructureEntityProject>) finderCache.getResult(finderPath,
                    finderArgs, this);
        }

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 2));

                query.append(_SQL_SELECT_INFRASTRUCTUREENTITYPROJECT);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                        orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_INFRASTRUCTUREENTITYPROJECT;

                if (pagination) {
                    sql = sql.concat(InfrastructureEntityProjectModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<InfrastructureEntityProject>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = Collections.unmodifiableList(list);
                } else {
                    list = (List<InfrastructureEntityProject>) QueryUtil.list(q,
                            getDialect(), start, end);
                }

                cacheResult(list);

                finderCache.putResult(finderPath, finderArgs, list);
            } catch (Exception e) {
                finderCache.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Removes all the infrastructure entity projects from the database.
     *
     */
    @Override
    public void removeAll() {
        for (InfrastructureEntityProject infrastructureEntityProject : findAll()) {
            remove(infrastructureEntityProject);
        }
    }

    /**
     * Returns the number of infrastructure entity projects.
     *
     * @return the number of infrastructure entity projects
     */
    @Override
    public int countAll() {
        Long count = (Long) finderCache.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_INFRASTRUCTUREENTITYPROJECT);

                count = (Long) q.uniqueResult();

                finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
                        count);
            } catch (Exception e) {
                finderCache.removeResult(FINDER_PATH_COUNT_ALL,
                        FINDER_ARGS_EMPTY);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    @Override
    protected Map<String, Integer> getTableColumnsMap() {
        return InfrastructureEntityProjectModelImpl.TABLE_COLUMNS_MAP;
    }

    /**
     * Initializes the infrastructure entity project persistence.
     */
    public void afterPropertiesSet() {
    }

    public void destroy() {
        entityCache.removeCache(InfrastructureEntityProjectImpl.class.getName());
        finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
        finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}