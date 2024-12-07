package org.cvs.entities;

public enum VCS {
    GITHUB("github"),
    AZURE("azure"),
    GITLAB("gitlab");

    private String VCSName;

    VCS(String VCSName) {
        this.VCSName = VCSName;
    }

    public String getVCSName() {
        return VCSName;
    }

    public static VCS getVcsByName(String VCSName) {
        for (VCS v : VCS.values()) {
            if (v.VCSName.equals(VCSName)) {
                return v;
            }
        }
        return null;
    }
}
