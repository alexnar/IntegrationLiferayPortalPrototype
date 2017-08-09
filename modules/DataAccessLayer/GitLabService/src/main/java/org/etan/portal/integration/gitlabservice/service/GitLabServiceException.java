package org.etan.portal.integration.gitlabservice.service;

/**
 * If there are problems when interacting with GitLab api.
 *
 * @author Efimov Timur
 * @version 1.0.1
 */
public class GitLabServiceException extends Exception {

    public GitLabServiceException(String s) {
        super(s);
    }

    public GitLabServiceException() {
    }

    public GitLabServiceException(String createProjectError, Exception e) {
        super(createProjectError, e);
    }
}
