package org.cvs.steps.ui;

import org.openqa.selenium.Cookie;

public interface UISteps {

    Cookie logIn();

    void createBranch();

    void addCommit();

    void createMergeRequest();

    void mergeMergeRequest();
}
