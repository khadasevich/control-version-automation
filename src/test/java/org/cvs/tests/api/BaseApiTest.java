package org.cvs.tests.api;

import org.cvs.core.config.Config;
import org.cvs.core.listeners.TestListener;
import org.cvs.steps.api.RestApiSteps;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners({TestListener.class})
public class BaseApiTest {

    protected RestApiSteps restApiSteps;

    @BeforeSuite
    public void setUp() {
        restApiSteps = Config.getRestApiSteps();
    }
}
