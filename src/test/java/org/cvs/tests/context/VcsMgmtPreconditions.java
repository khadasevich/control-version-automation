package org.cvs.tests.context;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.cvs.core.config.Config;
import org.cvs.entities.branch.GithubBranch;
import org.cvs.entities.commit.GithubCommit;
import org.cvs.entities.pr.GithubPoolRequest;
import org.cvs.entities.repositories.Repository;
import org.cvs.entities.repositories.github.GithubRepository;
import org.cvs.steps.api.RestApiSteps;
import org.cvs.steps.api.github.GithubApiSteps;
import org.testng.annotations.BeforeSuite;

import java.util.List;
import java.util.stream.IntStream;

import static org.cvs.core.config.Config.*;

@Log4j2
public class VcsMgmtPreconditions extends BaseTest {

    protected Repository repository;
    protected String repositoryName;
    protected String repoSHA;

    @BeforeSuite
    public void prepareSystem() {
        RestApiSteps apiSteps = Config.getRestApiSteps();
        prepareRepository(apiSteps);
        prepareBranches(apiSteps);
        addCommitsToBranches(apiSteps);
        createMergeRequest(apiSteps);
    }

    @Step
    private void prepareRepository(RestApiSteps apiSteps) {
        if (apiSteps instanceof GithubApiSteps) {
            List<GithubRepository> repositoryList = apiSteps.getListOfRepositories();
            repositoryList = repositoryList
                    .stream().filter(repo -> repo.getName().contains(DEFAULT_REPOSITORY_NAME)).toList();
            if (!repositoryList.isEmpty()) {
                apiSteps.deleteRepo();
            }
            GithubRepository requestBody = GithubRepository.builder().name(DEFAULT_REPOSITORY_NAME).build();
            repository = apiSteps.createRepo(requestBody);
            ((GithubApiSteps) apiSteps).addContentToRepo();
            repositoryName = ((GithubRepository) repository).getName();
            log.info("Repository Created: {}", repositoryName);
        }
    }

    @Step
    private void prepareBranches(RestApiSteps apiSteps) {
        if (apiSteps instanceof GithubApiSteps) {
            repoSHA = ((GithubApiSteps) apiSteps).getRepoSHA();
            IntStream.range(0, BRANCHES.size()).forEach(i -> {
                String branchName = BRANCHES.get(i);
                GithubBranch branch = GithubBranch.builder()
                        .repoName(repositoryName)
                        .branchName(branchName)
                        .sha(repoSHA)
                        .build();
                apiSteps.createBranch(branch);
                log.info("Branch Created: {}", branchName);
            });
        }
    }

    @Step
    private void addCommitsToBranches(RestApiSteps apiSteps) {
        if (apiSteps instanceof GithubApiSteps) {
            String blobSHA = ((GithubApiSteps) apiSteps).createBlob();
            IntStream.range(0, BRANCHES_WITH_COMMITS.size()).forEach(i -> {
                String branchName = BRANCHES_WITH_COMMITS.get(i);
                String treeSHA = ((GithubApiSteps) apiSteps).createTree(blobSHA, repoSHA);
                List<String> parents = List.of(repoSHA);
                GithubCommit commit = GithubCommit.builder().tree(treeSHA).parents(parents).build();
                String commitSHA = apiSteps.addCommit(commit);
                ((GithubApiSteps) apiSteps).updateBranchReference(commitSHA, branchName);
                log.info("Commit {} added to branch {}", commitSHA, branchName);
            });
        }
    }

    @Step
    private void createMergeRequest(RestApiSteps apiSteps) {
        if (apiSteps instanceof GithubApiSteps) {
            GithubPoolRequest mergeRequest = GithubPoolRequest.builder()
                    .base(VCS_MAIN_BRANCH)
                    .head(BRANCH_WITH_MR_NAME)
                    .build();
            apiSteps.createPullRequest(mergeRequest);
            log.info("Merge Request created for branch {}", BRANCH_WITH_MR_NAME);
        }
    }
}
