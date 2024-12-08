package org.cvs.entities.pr;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubPoolRequest extends PoolRequest {

    private String head;
    @Builder.Default
    private String title = "Automated Pool Request";
    @Builder.Default
    private String body = "This is a automated merge request";
    private String base;
}
