package org.cvs.steps.ui;

import org.openqa.selenium.Cookie;

public interface UISteps {

    Cookie successfulLogIn();

    void openRepositoryDetails();

    void createBranch(String branchName);

    void validateBranchCreation(String branchName);

    void addCommit(String branchName, String fileName);

    void validateCommitCreation(String branchName, String fileName);

    void createPoolRequest(String branchName, String prName);

    void validatePoolRequestCreation(String prName);

    void mergePoolRequest(String prName);

    void validateMerge(String prName);
}
