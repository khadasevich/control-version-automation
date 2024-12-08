package org.cvs.entities.commit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubBlob {

    @Builder.Default
    private String content = "Content of the blob";
    @Builder.Default
    private String encoding = "utf-8";
}
