package org.cvs.steps.api.azure;

import io.restassured.builder.RequestSpecBuilder;
import org.cvs.entities.branch.AbstractBranch;
import org.cvs.entities.commit.AbstractCommit;
import org.cvs.entities.mr.AbstractMergeRequest;
import org.cvs.entities.repositories.Repository;
import org.cvs.entities.repositories.azure.AzureRepository;
import org.cvs.steps.api.RestApiSteps;

import java.util.List;

public class AzureApiSteps implements RestApiSteps {
    @Override
    public RequestSpecBuilder getBaseRequestSpecification() {
        return null;
    }

    @Override
    public List<AzureRepository> getListOfRepositories() {
        return List.of();
    }

    @Override
    public AzureRepository createRepo(Repository repository) {
        return null;
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
