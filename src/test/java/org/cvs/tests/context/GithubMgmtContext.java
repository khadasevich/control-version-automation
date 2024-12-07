package org.cvs.tests.context;

import org.cvs.core.config.Config;
import org.cvs.entities.repositories.Repository;
import org.cvs.entities.repositories.github.GithubRepository;
import org.cvs.steps.api.RestApiSteps;
import org.cvs.steps.api.github.GithubApiSteps;
import org.testng.annotations.BeforeSuite;

import java.util.List;

import static org.cvs.core.config.Config.DEFAULT_REPOSITORY_NAME;

public class GithubMgmtContext extends CommonTestContext {

    protected Repository repository;

    @BeforeSuite
    public void prepareSystem() {
        RestApiSteps apiSteps = Config.getRestApiSteps();
        prepareRepository(apiSteps);
    }

    private void prepareRepository(RestApiSteps apiSteps) {
        if (apiSteps instanceof GithubApiSteps) {
            List<GithubRepository> repositoryList = apiSteps.getListOfRepositories();
            repositoryList = repositoryList
                    .stream().filter(repo -> repo.getName().equals(DEFAULT_REPOSITORY_NAME)).toList();
            if (repositoryList.isEmpty()) {
                GithubRepository requestBody = GithubRepository.builder().name(DEFAULT_REPOSITORY_NAME).build();
                repository = apiSteps.createRepo(requestBody);

            } else {
                repository = repositoryList.getFirst();
            }
        }
    }
}
