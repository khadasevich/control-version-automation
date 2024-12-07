package org.cvs.entities;

public enum VCS {
    GITHUB("Github"),
    AZURE("Azure Devops"),
    GITLAB("Gitlab");

    private String VCSName;

    VCS(String VCSName) {
        this.VCSName = VCSName;
    }

    public String getVCSName() {
        return VCSName;
    }
}
