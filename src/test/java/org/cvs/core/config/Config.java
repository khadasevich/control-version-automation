package org.cvs.core.config;

import org.cvs.entities.VCS;
import org.cvs.steps.api.RestApiSteps;
import org.cvs.steps.api.azure.AzureApiSteps;
import org.cvs.steps.api.github.GithubApiSteps;
import org.cvs.steps.api.gitlab.GitlabApiSteps;

public class Config {

    public static final VCS SYSTEM_TYPE = VCS.getVcsByName(PropertiesReader.getEnvOrConfigVariable("vcs.type"));
    public static final String USERNAME = PropertiesReader.getEnvOrConfigVariable("vcs.username");
    public static final String PASSWORD = PropertiesReader.getEnvOrConfigVariable("vcs.password");
    public static final String HOST = PropertiesReader.getEnvOrConfigVariable("vcs.host");
    public static final String API_HOST = PropertiesReader.getEnvOrConfigVariable("vcs.api.host");
    public static final String TOKEN = PropertiesReader.getEnvOrConfigVariable("vcs.token");
    public static final String DEFAULT_REPOSITORY_NAME = "repository_under_test";

    public static RestApiSteps getRestApiSteps() {
        assert SYSTEM_TYPE != null;
        return switch (SYSTEM_TYPE) {
            case AZURE -> new AzureApiSteps();
            case GITLAB -> new GitlabApiSteps();
            default -> new GithubApiSteps();
        };
    }
}
