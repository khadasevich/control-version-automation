package org.cvs.core.config;

import org.cvs.entities.VCS;
import org.cvs.steps.api.RestApiSteps;
import org.cvs.steps.api.github.GithubApiSteps;
import org.cvs.steps.ui.UISteps;
import org.cvs.steps.ui.github.GitHubUISteps;

import java.util.List;

public class Config {

    public static final VCS SYSTEM_TYPE = VCS.getVcsByName(PropertiesReader.getEnvOrConfigVariable("vcs.type"));
    public static final String USERNAME = PropertiesReader.getEnvOrConfigVariable("vcs.username");
    public static final String PASSWORD = PropertiesReader.getEnvOrConfigVariable("vcs.userpwd");
    public static final String WEB_HOST = PropertiesReader.getEnvOrConfigVariable("vcs.host");
    public static final String API_HOST = PropertiesReader.getEnvOrConfigVariable("vcs.api.host");
    public static final String TOKEN = PropertiesReader.getEnvOrConfigVariable("vcs.token");
    public static final String VCS_MAIN_BRANCH = PropertiesReader.getEnvOrConfigVariable("vcs.main.branch");
    public static final String DEFAULT_REPOSITORY_NAME = "repository_under_test";
    public static final String BRANCH_NAME = "branch";
    public static final String BRANCH_WITH_COMMIT_NAME = "branch_with_commit";
    public static final String BRANCH_WITH_PR_NAME = "branch_with_pr";
    public static final String AUTOMATED_PR_TITLE = "automated_pr_title";
    public static final List<String> BRANCHES = List.of(BRANCH_NAME, BRANCH_WITH_COMMIT_NAME, BRANCH_WITH_PR_NAME);
    public static final List<String> BRANCHES_WITH_COMMITS = List.of(BRANCH_WITH_COMMIT_NAME, BRANCH_WITH_PR_NAME);

    public static RestApiSteps getRestApiSteps() {
        assert SYSTEM_TYPE != null;
        return switch (SYSTEM_TYPE) {
            case AZURE -> null; //ToDo implement
            case GITLAB -> null; //ToDo implement
            default -> new GithubApiSteps();
        };
    }

    public static UISteps getUISteps() {
        assert SYSTEM_TYPE != null;
        return switch (SYSTEM_TYPE) {
            case AZURE -> null; //ToDo implement
            case GITLAB -> null; //ToDo implement
            default -> new GitHubUISteps();
        };
    }
}
