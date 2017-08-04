package org.etan.portal.integration.prototype.projectmanage.service.context;

import javax.portlet.RenderRequest;

/**
 * Context which contain information
 * about who and where initiate action.
 *
 * @author Naryzhny Alex
 */
public class ProjectManageContext {

    private long projectId;
    private long userId;

    public ProjectManageContext(RenderRequest renderRequest) {

    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
