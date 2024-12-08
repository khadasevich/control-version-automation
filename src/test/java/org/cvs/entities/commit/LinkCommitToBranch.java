package org.cvs.entities.commit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class LinkCommitToBranch {

    @Builder.Default
    private boolean force = true;
    private String sha;
}