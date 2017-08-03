package org.etan.portal.integration.prototype.projectmanage.service.context;

import javax.portlet.RenderRequest;

/**
 * Context which contain information
 * about who and where initiate action.
 *
 * @author Naryzhny Alex
 */
public class ProjectManageContext {

    private long organizationId;
    private long userId;

    public ProjectManageContext(RenderRequest renderRequest) {

    }

    public long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(long organizationId) {
        this.organizationId = organizationId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


}
