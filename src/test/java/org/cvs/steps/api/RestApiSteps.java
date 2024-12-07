package org.cvs.steps.api;

import io.restassured.builder.RequestSpecBuilder;
import org.cvs.entities.branch.AbstractBranch;
import org.cvs.entities.commit.AbstractCommit;
import org.cvs.entities.mr.AbstractMergeRequest;
import org.cvs.entities.repositories.Repository;

import java.util.List;

public interface RestApiSteps {

    String AUTHORIZATION = "Authorization";

    RequestSpecBuilder getBaseRequestSpecification();

    <T extends Repository> List<T> getListOfRepositories();

    Repository createRepo(Repository repository);

    List<AbstractMergeRequest> getListOfMergeRequests();

    void closeMergeRequest(AbstractMergeRequest mergeRequest);

    List<AbstractBranch> getListOfBranches();

    void deleteBranch(AbstractBranch branch);

    void createBranch(AbstractBranch branch);

    void addCommit(AbstractCommit commit);

    void createMergeRequest(AbstractMergeRequest mergeRequest);
}
