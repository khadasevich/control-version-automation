package org.cvs.entities.branch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubBranch extends Branch {

    private String repoName;
    private String branchName;
    private String sha;
}
