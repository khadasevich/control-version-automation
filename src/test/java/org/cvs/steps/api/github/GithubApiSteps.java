package org.cvs.steps.api.github;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.cvs.entities.branch.AbstractBranch;
import org.cvs.entities.commit.AbstractCommit;
import org.cvs.entities.mr.AbstractMergeRequest;
import org.cvs.entities.repositories.Repository;
import org.cvs.entities.repositories.github.GithubRepository;
import org.cvs.steps.api.RestApiSteps;
import org.cvs.steps.api.common.HTTPRequestSteps;

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
        return HTTPRequestSteps.get(requestSpecification).extract().jsonPath().getList("", GithubRepository.class);
    }

    @Step
    @Override
    public GithubRepository createRepo(Repository repository) {
        RequestSpecification requestSpecification = getBaseRequestSpecification()
                .setBasePath("/user/repos").setBody(repository).build();
        return HTTPRequestSteps.post(requestSpecification).extract().jsonPath().getObject("", GithubRepository.class);
    }

    @Override
    public List<AbstractMergeRequest> getListOfMergeRequests() {
        return List.of();
    }

    @Override
    public void closeMergeRequest(AbstractMergeRequest mergeRequest) {

    }

    @Override
    public List<AbstractBranch> getListOfBranches() {
        return List.of();
    }

    @Override
    public void deleteBranch(AbstractBranch branch) {

    }

    @Override
    public void createBranch(AbstractBranch branch) {

    }

    @Override
    public void addCommit(AbstractCommit commit) {

    }

    @Override
    public void createMergeRequest(AbstractMergeRequest mergeRequest) {

    }
}
