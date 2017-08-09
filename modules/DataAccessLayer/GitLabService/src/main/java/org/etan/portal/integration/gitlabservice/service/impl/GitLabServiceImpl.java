package org.etan.portal.integration.gitlabservice.service.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.etan.portal.integration.gitlabservice.service.GitLabService;
import org.etan.portal.integration.gitlabservice.service.GitLabServiceException;
import org.gitlab.api.GitlabAPI;
import org.gitlab.api.models.GitlabAccessLevel;
import org.gitlab.api.models.GitlabGroup;
import org.gitlab.api.models.GitlabProject;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.util.Random;

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
    private static final String SERVER_URL = "https://192.168.0.69";
    private static final String API_KEY = "th-roADVLPo5VbX4Hk2u";

    //todo if we want one more?
    private static final String PROJECTS_CATALOG_GITLAB_GROUP_PATH = "MyNewGroup2";

    //todo better
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
            //todo make group creation or etc. configurable
//            GitlabProject project = api.createUserProject(
//                    GITLAB_ADMIN_ID, repositoryName);

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
            throw handle(CREATE_PROJECT_ERROR, e);
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
            throw handle(CREATE_PROJECT_ERROR, e);
        }

    }

    @Activate
    protected void activate() throws IOException, GitLabServiceException {

        GitlabAPI api = getApiClient();

        GitlabGroup group = api.createGroup(PROJECTS_CATALOG_GITLAB_GROUP_PATH);
        log.info("GitlabGroup created: \n" +
                "group.getId(): " + group.getId() + "; " +
                "group.getPath(): " + group.getPath() + "; " +
                "group.getWebUrl(): " + group.getWebUrl() + ". ");

        test();
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


    //    @Activate
    protected void test() throws IOException, GitLabServiceException {
        Random random = new Random(System.currentTimeMillis());
        int r = random.nextInt(100);

        String randName = "EtaProject" + r;

        createRepository(randName);
//        createRepository(randName);

//
//
//        GitlabAPI api = getApiClient();
//
//        GitlabGroup group = api.getGroup(PROJECTS_CATALOG_GITLAB_GROUP_PATH);
//        System.out.println("group.getId()"+group.getId());
//        System.out.println("group.getPath()"+group.getPath());
//        System.out.println("group.getWebUrl()"+group.getWebUrl());
//
//        GitlabProject project = api.getProject(23);
//        System.out.println("project.getId()"+project.getId());
//        System.out.println("project.getPath()"+project.getPath());
//        System.out.println("project.getWebUrl()"+project.getWebUrl());
//        System.out.println("project.getHttpUrl()"+project.getHttpUrl());
//        System.out.println("project.getNameWithNamespace()"+project.getNameWithNamespace());
//        System.out.println("project.getPathWithNamespace()"+project.getPathWithNamespace());
//        System.out.println("project.getNamespace().getName()"+project.getNamespace().getName());
//        System.out.println("project.getNamespace().getPath()"+project.getNamespace().getPath());
//
//        GitlabProject project2 = api.getProject(group.getPath(),"EtaProject64");
//        System.out.println("project2.getId()"+project2.getId());
//        System.out.println("project2.getPath()"+project2.getPath());
//        System.out.println("project2.getWebUrl()"+project2.getWebUrl());
//        System.out.println("project2.getHttpUrl()"+project2.getHttpUrl());
//        System.out.println("project2.getNameWithNamespace()"+project2.getNameWithNamespace());
//        System.out.println("project2.getPathWithNamespace()"+project2.getPathWithNamespace());
//        System.out.println("project2.getNamespace().getName()"+project2.getNamespace().getName());
//        System.out.println("project2.getNamespace().getPath()"+project2.getNamespace().getPath());

    }

}