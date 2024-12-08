package org.cvs.tests.api;

import io.qameta.allure.*;
import org.cvs.core.config.Config;
import org.cvs.entities.branch.GithubBranch;
import org.cvs.entities.repositories.Repository;
import org.cvs.entities.repositories.github.GithubRepository;
import org.cvs.steps.api.github.GithubApiSteps;
import org.cvs.utilities.GenerateTestData;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.cvs.utilities.AllureUtilities.API_ALLURE_EPIC;

@Epic(API_ALLURE_EPIC)
@Feature("BRANCHES")
public class BranchesApiTest extends BaseApiTest {

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Description("Create new branch")
    void createNewBranchTest() {
        String branchName = GenerateTestData.gitBranchName();
        if (restApiSteps instanceof GithubApiSteps) {
            GithubBranch branch = GithubBranch.builder()
                    .repoName(Config.DEFAULT_REPOSITORY_NAME)
                    .branchName(branchName)
                    .sha(((GithubApiSteps) restApiSteps).getRepoSHA())
                    .build();
            restApiSteps.createBranch(branch);
            List<GithubBranch> branches = restApiSteps.getListOfBranches();
            assertThat(branches.size()).isGreaterThanOrEqualTo(1);
            assertThat(branches).anyMatch(br -> br.getBranchName().equals(branchName));
        }
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @Description("Delete branch")
    void deleteBranchTest() {
        String branchName = GenerateTestData.gitBranchName();
        if (restApiSteps instanceof GithubApiSteps) {
            restApiSteps.createBranch(GithubBranch.builder()
                    .repoName(Config.DEFAULT_REPOSITORY_NAME)
                    .branchName(branchName)
                    .sha(((GithubApiSteps) restApiSteps).getRepoSHA())
                    .build());
            restApiSteps.deleteBranch(branchName);
            List<GithubBranch> branches = restApiSteps.getListOfBranches();
            assertThat(branches).noneMatch(br -> br.getBranchName().equals(branchName));
        }
    }
}
