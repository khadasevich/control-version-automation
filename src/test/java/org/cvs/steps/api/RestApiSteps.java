package org.cvs.steps.api;

import io.restassured.builder.RequestSpecBuilder;
import org.cvs.entities.branch.Branch;
import org.cvs.entities.commit.Commit;
import org.cvs.entities.mr.MergeRequest;
import org.cvs.entities.repositories.Repository;

import java.util.List;

public interface RestApiSteps {

    String AUTHORIZATION = "Authorization";

    RequestSpecBuilder getBaseRequestSpecification();

    <T extends Repository> List<T> getListOfRepositories();

    Repository createRepo(Repository repository);

    void deleteRepo();

    void createBranch(Branch branch);

    String addCommit(Commit commit);

    void createMergeRequest(MergeRequest mergeRequest);
}
