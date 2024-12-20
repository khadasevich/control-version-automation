package org.cvs.entities.commit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubCommit extends Commit {

    @Builder.Default
    private String message = "Commit message";
    private String tree;
    private List<String> parents;
}
