package org.cvs.steps.api.gitlab;

import io.restassured.builder.RequestSpecBuilder;
import org.cvs.entities.branch.Branch;
import org.cvs.entities.commit.Commit;
import org.cvs.entities.mr.MergeRequest;
import org.cvs.entities.repositories.Repository;
import org.cvs.entities.repositories.gitlab.GitlabRepository;
import org.cvs.steps.api.RestApiSteps;

import java.util.List;

public class GitlabApiSteps implements RestApiSteps {
    @Override
    public RequestSpecBuilder getBaseRequestSpecification() {
        return null;
    }

    @Override
    public List<GitlabRepository> getListOfRepositories() {
        return List.of();
    }

    @Override
    public GitlabRepository createRepo(Repository repository) {
        return null;
    }

    @Override
    public void deleteRepo() {

    }

    @Override
    public void createBranch(Branch branch) {

    }

    @Override
    public String addCommit(Commit commit) {

        return null;
    }

    @Override
    public void createMergeRequest(MergeRequest mergeRequest) {

    }
}
