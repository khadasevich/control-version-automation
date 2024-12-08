package org.cvs.steps.ui;

import org.openqa.selenium.Cookie;

public interface UISteps {

    Cookie logIn();

    void openRepositoryDetails();

    void createBranch(String branchName);

    void validateBranchCreation(String branchName);

    void addCommit();

    void createMergeRequest();

    void mergeMergeRequest();
}
