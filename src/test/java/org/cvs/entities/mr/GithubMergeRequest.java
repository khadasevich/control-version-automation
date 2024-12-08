package org.cvs.entities.mr;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubMergeRequest extends MergeRequest {

    private String head;
    @Builder.Default
    private String title = "Automated Merge Request";
    @Builder.Default
    private String body = "This is a automated merge request";
    private String base;
}
