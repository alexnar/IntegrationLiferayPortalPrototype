package org.etan.portal.integration.gitlabservice.service;

/**
 * If there are problems when interacting with GitLab api by http - json.
 *
 * @author Efimov Timur
 * @version 1.0.1
 */
public class GitLabServiceException extends Exception {

    public GitLabServiceException(String s) {
        super();
    }

    public GitLabServiceException() {
    }
}
