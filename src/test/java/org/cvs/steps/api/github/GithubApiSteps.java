package org.cvs.steps.api.github;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.cvs.entities.branch.Branch;
import org.cvs.entities.branch.GitHubRef;
import org.cvs.entities.branch.GithubBranch;
import org.cvs.entities.commit.*;
import org.cvs.entities.pr.PoolRequest;
import org.cvs.entities.repositories.Repository;
import org.cvs.entities.repositories.github.GitHubRepoContent;
import org.cvs.entities.repositories.github.GithubRepository;
import org.cvs.steps.api.RestApiSteps;
import org.cvs.steps.api.common.HTTPRequestSteps;
import org.cvs.utilities.GenerateTestData;

import java.util.List;

import static org.cvs.core.config.Config.*;

public class GithubApiSteps implements RestApiSteps {

    private final String API_VERSION_HEADER = "X-GitHub-Api-Version";
    private final String API_VERSION = "2022-11-28";
    private final String CONTENT_TYPE = "application/vnd.github+json";

    @Step
    @Override
    public RequestSpecBuilder getBaseRequestSpecification() {
        RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
        reqBuilder.setBaseUri(API_HOST);
        reqBuilder.addHeader(AUTHORIZATION, "Bearer " + TOKEN);
        reqBuilder.addHeader(API_VERSION_HEADER, API_VERSION);
        reqBuilder.setContentType(CONTENT_TYPE);
        return reqBuilder;
    }

    @Step
    @Override
    public List<GithubRepository> getListOfRepositories() {
        RequestSpecification requestSpecification = getBaseRequestSpecification().setBasePath("/user/repos").build();
        return HTTPRequestSteps.get(requestSpecification)
                .statusCode(200).extract().jsonPath().getList("", GithubRepository.class);
    }

    @Step
    @Override
    public GithubRepository createRepo(Repository repository) {
        RequestSpecification requestSpecification = getBaseRequestSpecification()
                .setBasePath("/user/repos").setBody(repository).build();
        return HTTPRequestSteps.post(requestSpecification)
                .statusCode(201).extract().jsonPath().getObject("", GithubRepository.class);
    }

    @Step
    @Override
    public List<GithubBranch> getListOfBranches() {
        String basePath = String.format("/repos/%s/%s/branches", USERNAME, DEFAULT_REPOSITORY_NAME);
        RequestSpecification requestSpecification = getBaseRequestSpecification().setBasePath(basePath).build();
        return HTTPRequestSteps.get(requestSpecification)
                .statusCode(200).extract().jsonPath().getList("", GithubBranch.class);
    }

    @Step
    @Override
    public void deleteRepo() {
        String basePath = String.format("/repos/%s/%s", USERNAME, DEFAULT_REPOSITORY_NAME);
        RequestSpecification requestSpecification = getBaseRequestSpecification()
                .setBasePath(basePath).build();
        HTTPRequestSteps.delete(requestSpecification).statusCode(204);
    }

    @Step
    @Override
    public void deleteRepo(String repoName) {
        String basePath = String.format("/repos/%s/%s", USERNAME, repoName);
        RequestSpecification requestSpecification = getBaseRequestSpecification()
                .setBasePath(basePath).build();
        HTTPRequestSteps.delete(requestSpecification).statusCode(204);
    }

    @Step
    public void addContentToRepo() {
        String basePath = String.format("/repos/%s/%s/contents/%s", USERNAME, DEFAULT_REPOSITORY_NAME, GenerateTestData.gitHubPath());
        RequestSpecification requestSpecification = getBaseRequestSpecification()
                .setBasePath(basePath).setBody(GitHubRepoContent.builder().build()).build();
        HTTPRequestSteps.put(requestSpecification).statusCode(201);
    }

    @Step
    public String getRepoSHA() {
        String basePath = String.format("/repos/%s/%s/git/refs/heads", USERNAME, DEFAULT_REPOSITORY_NAME);
        RequestSpecification requestSpecification = getBaseRequestSpecification()
                .setBasePath(basePath).build();
        return HTTPRequestSteps.get(requestSpecification).statusCode(200)
                .extract().jsonPath().getString("object.sha").replaceAll("[^a-zA-Z0-9]", "");
    }

    @Step
    @Override
    public void createBranch(Branch branch) {
        String repoName = ((GithubBranch) branch).getRepoName();
        String basePath = "/repos/" + USERNAME + "/" + repoName + "/git/refs";
        String refName = "refs/heads/" + ((GithubBranch) branch).getBranchName();
        String branchSha = ((GithubBranch) branch).getSha();
        GitHubRef body = GitHubRef.builder().ref(refName).sha(branchSha).build();
        RequestSpecification requestSpecification = getBaseRequestSpecification()
                .setBasePath(basePath).setBody(body).build();
        HTTPRequestSteps.post(requestSpecification).statusCode(201);
    }

    @Step
    @Override
    public void deleteBranch(String branchName) {
        String basePath = String.format("/repos/%s/%s/git/refs/heads/%s", USERNAME, DEFAULT_REPOSITORY_NAME, branchName);
        RequestSpecification requestSpecification = getBaseRequestSpecification()
                .setBasePath(basePath).build();
        HTTPRequestSteps.delete(requestSpecification).statusCode(204);

    }

    @Step
    public String createBlob() {
        String basePath = String.format("/repos/%s/%s/git/blobs", USERNAME, DEFAULT_REPOSITORY_NAME);
        RequestSpecification requestSpecification = getBaseRequestSpecification()
                .setBasePath(basePath).setBody(GitHubBlob.builder().build()).build();
        return HTTPRequestSteps.post(requestSpecification).statusCode(201).extract().jsonPath().get("sha");
    }

    @Step
    public String createTree(String blobSHA, String branchSHA) {
        String basePath = String.format("/repos/%s/%s/git/trees", USERNAME, DEFAULT_REPOSITORY_NAME);
        TreeItem treeItem = TreeItem.builder().sha(blobSHA).build();
        GitHubTree gitHubTree = GitHubTree.builder().baseTree(branchSHA).tree(List.of(treeItem)).build();
        RequestSpecification requestSpecification = getBaseRequestSpecification()
                .setBasePath(basePath).setBody(gitHubTree).build();
        return HTTPRequestSteps.post(requestSpecification).statusCode(201).extract().jsonPath().get("sha");
    }

    @Step
    @Override
    public String addCommit(Commit commit) {
        String basePath = String.format("/repos/%s/%s/git/commits", USERNAME, DEFAULT_REPOSITORY_NAME);
        RequestSpecification requestSpecification = getBaseRequestSpecification()
                .setBasePath(basePath).setBody(commit).build();
        return HTTPRequestSteps.post(requestSpecification).statusCode(201).extract().jsonPath().get("sha");
    }

    @Step
    public void updateBranchReference(String commitSHA, String branchName) {
        String basePath = String.format("/repos/%s/%s/git/refs/heads/%s", USERNAME, DEFAULT_REPOSITORY_NAME, branchName);
        LinkCommitToBranch body = LinkCommitToBranch.builder().sha(commitSHA).build();
        RequestSpecification requestSpecification = getBaseRequestSpecification()
                .setBasePath(basePath).setBody(body).build();
        HTTPRequestSteps.patch(requestSpecification).statusCode(200);
    }

    @Override
    public void createPullRequest(PoolRequest poolRequest) {
        String basePath = String.format("/repos/%s/%s/pulls", USERNAME, DEFAULT_REPOSITORY_NAME);
        RequestSpecification requestSpecification = getBaseRequestSpecification()
                .setBasePath(basePath).setBody(poolRequest).build();
        HTTPRequestSteps.post(requestSpecification).statusCode(201);
    }
}
