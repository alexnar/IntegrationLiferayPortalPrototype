package org.etan.portal.integration.infrastructureentityapi.service.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.Validator;
import org.etan.portal.integration.gitlabservice.service.GitLabService;
import org.etan.portal.integration.gitlabservice.service.GitLabServiceException;
import org.etan.portal.integration.infrastructureentityapi.service.InfrastructureEntity;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.Serializable;

/**
 * Infrastructure entity implementation.
 * Delegates all methods to GitLabService.
 * Preliminary translation of 'id' arguments into int primitives.
 *
 * @author Efimov Timur
 */
@Component(
        immediate = true,
        property = {
                // TODO enter required service properties
        },
        service = InfrastructureEntity.class
)
public class GitLabInfrastructureEntity implements InfrastructureEntity {

    private static final Log log =
            LogFactoryUtil.getLog(GitLabInfrastructureEntity.class);

    private static final String GITLAB_USER_ID_FIELD = "gitlabUserId";
    private static final String INFRASTRUCTURE_ENTITY_NAME = "GITLAB";

    @Reference
    private GitLabService gitLabService;

    /**
     * Create project in GitLab.
     *
     * @param projectName - name of project to create.
     * @return - String Id of project in GitLab.
     * @throws GitLabInfrastructureEntityException - throws if some exception
     *                                             happened while creating project
     */
    public String createInfrastructureEntityProject(
            String projectName) throws GitLabInfrastructureEntityException {
        String projectIdString;
        try {
            int projectId = gitLabService.createRepository(projectName);
            projectIdString = String.valueOf(projectId);
        } catch (GitLabServiceException e) {
            String msg = "Could not create project with name: " + projectName;
            log.error(msg, e);
            throw new GitLabInfrastructureEntityException(msg, e);
        }
        return projectIdString;
    }

    /**
     * Assign user to GitLab.
     *
     * @param user                          -  user to assign
     * @param infrastructureEntityProjectId - String id of GitLab project
     *                                      where user would be assigned.
     * @throws GitLabInfrastructureEntityException - throws if some exception
     *                                             happened while assign user
     */
    public void assignUser(User user, String infrastructureEntityProjectId)
            throws GitLabInfrastructureEntityException {

        int gitlabUserId = getGitlabUserId(user);
        int gitlabProjectId = getGitlabProjectId(infrastructureEntityProjectId);

        try {
            gitLabService.addUserToRepository(gitlabUserId, gitlabProjectId);
        } catch (GitLabServiceException e) {
            String msg = "Could not assign user to project. "
                    + "{ "
                    + "gitlabUserId: " + gitlabUserId + ", "
                    + "gitlabProjectId" + gitlabProjectId
                    + " }";
            log.error(msg, e);
            throw new GitLabInfrastructureEntityException(msg, e);
        }
    }

    /**
     * Unassign user from GitLab.
     *
     * @param user                          - user to unassign
     * @param infrastructureEntityProjectId - String id of GitLab project
     *                                      where user would be unassigned.
     * @throws GitLabInfrastructureEntityException - throws if some exception
     *                                             happened while unassign user
     */
    public void unassignUser(User user, String infrastructureEntityProjectId)
            throws GitLabInfrastructureEntityException {

        int gitlabUserId = getGitlabUserId(user);
        int gitlabProjectId = getGitlabProjectId(infrastructureEntityProjectId);

        try {
            gitLabService.deleteUserFromRepository(gitlabUserId, gitlabProjectId);
        } catch (GitLabServiceException e) {
            String msg = "Could not unassign user from project. "
                    + "{"
                    + "gitlabUserId: " + gitlabUserId + ", "
                    + "gitlabProjectId" + gitlabProjectId
                    + " }";
            log.error(msg, e);
            throw new GitLabInfrastructureEntityException(msg, e);
        }
    }

    @Override
    public boolean checkCreateInfrastructureEntityProjectOpportunity(String projectName) {
        return false;
    }

    /**
     * Get GitLab name.
     *
     * @return - GitLab name
     */
    public String getName() {
        return INFRASTRUCTURE_ENTITY_NAME;
    }

    private int getGitlabProjectId(String infrastructureEntityProjectId)
            throws GitLabInfrastructureEntityException {

        int gitlabProjectId = -1;

        if (!isInteger(infrastructureEntityProjectId)) {
            gitlabProjectId = Integer.valueOf(infrastructureEntityProjectId);
        }

        if (gitlabProjectId <= 0) {
            throw new GitLabInfrastructureEntityException("invalid gitlab project id");
        }

        return gitlabProjectId;
    }

    private int getGitlabUserId(User user) throws GitLabInfrastructureEntityException {
        int gitlabUserId = -1;

        ExpandoBridge userExpandoBridge = user.getExpandoBridge();
        Serializable userGitlabIdSerializable =
                userExpandoBridge.getAttribute(GITLAB_USER_ID_FIELD);

        if (!(userGitlabIdSerializable instanceof String)) {
            throw new GitLabInfrastructureEntityException(
                    "custom user id: gitlabUserId - is not a string");
        }

        String userGitlabIdString = (String) userGitlabIdSerializable;

        if (!isInteger(userGitlabIdString)) {
            gitlabUserId = Integer.valueOf(userGitlabIdString);
        }

        if (gitlabUserId <= 0) {
            throw new GitLabInfrastructureEntityException("invalid gitlab user id");
        }

        return gitlabUserId;
    }

    private boolean isInteger(String string) {
        int length = string.length();
//        int a = 999999999; // Explanation to figure 18 which is lower
        return 0 < length && length <= 9 && Validator.isNumber(string);
    }
}