package org.cvs.steps.api;

import io.restassured.builder.RequestSpecBuilder;
import org.cvs.entities.branch.Branch;
import org.cvs.entities.commit.Commit;
import org.cvs.entities.pr.PoolRequest;
import org.cvs.entities.repositories.Repository;

import java.util.List;

public interface RestApiSteps {

    String AUTHORIZATION = "Authorization";

    RequestSpecBuilder getBaseRequestSpecification();

    <T extends Repository> List<T> getListOfRepositories();

    Repository createRepo(Repository repository);

    void deleteRepo();

    void deleteRepo(String repoName);

    void createBranch(Branch branch);

    <T extends Branch> List<T> getListOfBranches();

    void deleteBranch(String branchName);

    String addCommit(Commit commit);

    void createPullRequest(PoolRequest poolRequest);
}
