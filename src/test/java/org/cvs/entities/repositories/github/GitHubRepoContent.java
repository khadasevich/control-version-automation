package org.cvs.entities.repositories.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubRepoContent {

    @Builder.Default
    private String message = "Content message";
    @Builder.Default
    private String content = "dGhpcyB0YXNrIGlzIHRvbyBiaWc=";
}
