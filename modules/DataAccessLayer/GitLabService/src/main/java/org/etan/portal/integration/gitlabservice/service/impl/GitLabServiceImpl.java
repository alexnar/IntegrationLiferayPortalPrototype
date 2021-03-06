package org.etan.portal.integration.gitlabservice.service.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import org.etan.portal.integration.gitlabservice.service.GitLabService;
import org.etan.portal.integration.gitlabservice.service.GitLabServiceException;
import org.gitlab.api.GitlabAPI;
import org.gitlab.api.Pagination;
import org.gitlab.api.models.GitlabAccessLevel;
import org.gitlab.api.models.GitlabCommit;
import org.gitlab.api.models.GitlabGroup;
import org.gitlab.api.models.GitlabProject;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Service used for get aces to some methods of GitLab server.
 * <br/>
 * I use external lib:
 * <a href="https://github.com/timols/java-gitlab-api">
 * https://github.com/timols/java-gitlab-api
 * </a>
 * <br/>
 *
 * @author Efimov Timur
 * @version 1.0.1
 */
@Component(
        immediate = true,
        property = {
                // TODO enter required service properties
        },
        service = GitLabService.class
)
public class GitLabServiceImpl implements GitLabService {

    private static final Log log = LogFactoryUtil.getLog(GitLabServiceImpl.class);

    //todo make configurable
    private static final String SERVER_URL = "https://192.168.43.89";
    private static final String API_KEY = "9Q3hhL6QaJvEDXK3TQt_";

    private static final String PROJECTS_CATALOG_GITLAB_GROUP_PATH = "GlobalProjects";

    private static final int DEFAULT_GITLAB_ROOT_USER_ID = 1;
    private static final int DEFAULT_GITLAB_ADMINISTRATOR_USER_ID =
            DEFAULT_GITLAB_ROOT_USER_ID;

    private static final int GITLAB_ADMIN_ID = DEFAULT_GITLAB_ADMINISTRATOR_USER_ID;


    /* constants */
    private static final String DESCRIPTION
            = "This is an automatically created project by integration system.";
    private static final String PRIVATE_VISIBILITY = "private";
    private static final GitlabAccessLevel DEVELOPER_ACCESS =
            GitlabAccessLevel.Developer;


    /* error messages */
    private static final String CREATE_PROJECT_ERROR
            = "There were problems creating the project.";
    private static final String ADD_USER_TO_PROJECT_ERROR
            = "There were problems when adding the user to the project.";
    private static final String DELETE_USER_FROM_PROJECT_ERROR
            = "There were problems when deleting the user from the project.";
    private static final String GET_LAST_COMMITS_ERROR
            = "There were problems when getting last commits.";
    @Reference
    private volatile OrganizationLocalService organizationLocalService;

    /**
     * Creates repository and return its id
     *
     * @param repositoryName name of new repository
     * @return id of created repository
     * @throws GitLabServiceException if any problems occurs
     */
    public int createRepository(String repositoryName) throws GitLabServiceException {

        GitlabAPI api = getApiClient();

        try {
            GitlabGroup group = api.getGroup(PROJECTS_CATALOG_GITLAB_GROUP_PATH);
            GitlabProject project = api.createProjectForGroup(
                    repositoryName, group, DESCRIPTION, PRIVATE_VISIBILITY);


            return project.getId();

        } catch (IOException e) {
            throw handle(CREATE_PROJECT_ERROR, e);
        }
    }

    /**
     * Gives the user access to the repository
     *
     * @param userId       id of gitLab user
     * @param repositoryId id of gitLab repository
     * @throws GitLabServiceException if any problems occurs
     */
    public void addUserToRepository(int userId, int repositoryId)
            throws GitLabServiceException {

        GitlabAPI api = getApiClient();

        try {
            api.addProjectMember(repositoryId, userId, DEVELOPER_ACCESS);

        } catch (IOException e) {
            throw handle(ADD_USER_TO_PROJECT_ERROR, e);
        }
    }

    /**
     * Takes the user from the repository. Does not do anything
     * if the user does not have access
     *
     * @param userId       id of gitLab user
     * @param repositoryId id of gitLab repository
     * @throws GitLabServiceException if any problems occurs
     */
    public void deleteUserFromRepository(int userId, int repositoryId)
            throws GitLabServiceException {

        GitlabAPI api = getApiClient();

        try {
            api.deleteProjectMember(repositoryId, userId);

        } catch (IOException e) {
            throw handle(DELETE_USER_FROM_PROJECT_ERROR, e);
        }

    }

    /**
     * Returns up to 20 last commits.
     * Returns less if there are no more commits at all.
     *
     * @param repositoryId id of gitLab repository
     * @throws GitLabServiceException if any problems occurs
     */
    @Override
    public List<GitlabCommit> getLastCommits(int repositoryId) throws GitLabServiceException {
        List<GitlabCommit> someLastCommits;

        GitlabAPI api = getApiClient();

        try {
            someLastCommits = api.getLastCommits(repositoryId);
        } catch (IOException e) {
            throw handle(GET_LAST_COMMITS_ERROR, e);
        }

        return someLastCommits;
    }

    /**
     * Returns the specified number of commits.
     * Returns less if there are no more commits at all.
     *
     * @param repositoryId id of gitLab repository
     * @param number       specified number of commits
     * @throws GitLabServiceException if any problems occurs
     */
    @Override
    public List<GitlabCommit> getLastCommits(int repositoryId, int number) throws GitLabServiceException {
        List<GitlabCommit> lastCommits;

        GitlabAPI api = getApiClient();

        Pagination pagination = new Pagination().withPerPage(number);

        try {
            lastCommits = api.getCommits(repositoryId, pagination, null);
        } catch (IOException e) {
            throw handle(GET_LAST_COMMITS_ERROR, e);
        }

        return lastCommits;
    }

    /**
     * Check if repository with name exists.
     *
     * @param repositoryName name of checking repository
     * @return true, if exists
     * @throws GitLabServiceException if any problems occurs
     */
    public boolean checkIfRepositoryWithNameExists(String repositoryName)
            throws GitLabServiceException {

        boolean exists;

        GitlabAPI api = getApiClient();
        try {
            GitlabProject project = api.getProject(repositoryName);
            exists = (project != null);
        } catch (IOException e) {
            boolean instanceofFileNotFoundException = (e instanceof FileNotFoundException);
            if (instanceofFileNotFoundException) {
                exists = false;
            } else {
                throw handle(e.getMessage(), e);
            }
        }

        return exists;
    }


    @Activate
    protected void activate() {

        GitlabAPI api = getApiClient();

        try {
            GitlabGroup group = api.getGroup(PROJECTS_CATALOG_GITLAB_GROUP_PATH);
        } catch (IOException e) {
            if (e instanceof FileNotFoundException) {
                GitlabGroup group = null;
                try {
                    group = api.createGroup(PROJECTS_CATALOG_GITLAB_GROUP_PATH);
                } catch (IOException e1) {
                    handle(e.getMessage(), e1);
                }
                log.info("GitlabGroup created: \n" +
                        "group.getId(): " + group.getId() + "; " +
                        "group.getPath(): " + group.getPath() + "; " +
                        "group.getWebUrl(): " + group.getWebUrl() + ". ");
            } else {
                handle(e.getMessage(), e);
            }
        }
    }

    private GitlabAPI getApiClient() {//todo config here
        GitlabAPI api = GitlabAPI.connect(SERVER_URL, API_KEY);
        api.ignoreCertificateErrors(true);
        return api;
    }

    private GitLabServiceException handle(String createProjectError, Exception e) {
        log.error(createProjectError, e);
        return new GitLabServiceException(createProjectError, e);
    }

}