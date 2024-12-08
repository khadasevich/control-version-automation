package org.cvs.entities.commit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import org.cvs.utilities.GenerateTestData;

@Data
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class TreeItem {

    @Builder.Default
    private String mode = "100644";
    @Builder.Default
    private String path = GenerateTestData.gitHubFilePath();
    @Builder.Default
    private String type = "blob";
    private String sha;
}