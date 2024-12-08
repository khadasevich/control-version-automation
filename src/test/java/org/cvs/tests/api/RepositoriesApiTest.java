package org.cvs.tests.api;

import io.qameta.allure.*;
import org.cvs.entities.repositories.Repository;
import org.cvs.entities.repositories.github.GithubRepository;
import org.cvs.steps.api.github.GithubApiSteps;
import org.cvs.utilities.GenerateTestData;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.cvs.utilities.AllureUtilities.API_ALLURE_EPIC;

@Epic(API_ALLURE_EPIC)
@Feature("REPOSITORIES")
public class RepositoriesApiTest extends BaseApiTest {

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Description("Create new Repository and validate")
    void createNewRepositoryTest() {
        String newRepositoryName = GenerateTestData.gitRepositoryName();
        GithubRepository requestBody = GithubRepository.builder().name(newRepositoryName).build();
        Repository repository = restApiSteps.createRepo(requestBody);
        if (repository instanceof GithubRepository) {
            assertThat(((GithubRepository) repository).getName()).isEqualTo(newRepositoryName);
            List<GithubRepository> repositories = restApiSteps.getListOfRepositories();
            assertThat(repositories).size().isGreaterThanOrEqualTo(1);;
            assertThat(repositories).anyMatch(repo -> repo.getName().equals(newRepositoryName));
        }
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @Description("Delete Repository and validate")
    void deleteRepositoryTest() {
        String newRepositoryName = GenerateTestData.gitRepositoryName();
        restApiSteps.createRepo(GithubRepository.builder().name(newRepositoryName).build());
        restApiSteps.deleteRepo(newRepositoryName);
        if (restApiSteps instanceof GithubApiSteps) {
            List<GithubRepository> repositories = restApiSteps.getListOfRepositories();
            assertThat(repositories).noneMatch(repo -> repo.getName().equals(newRepositoryName));
        }
    }
}
