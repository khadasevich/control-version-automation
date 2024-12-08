package org.cvs.tests.ui.context;

import lombok.extern.log4j.Log4j2;
import org.cvs.steps.ui.UISteps;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.Cookie;

import static org.cvs.core.config.Config.getUISteps;

@Log4j2
public class UIBaseLoginContext extends UIBaseTest {

    protected static Cookie userSession;
    protected static final ThreadLocal<UISteps> uiSteps = new ThreadLocal<>();

    @BeforeMethod
    public void loginAndGetCookie() {
        uiSteps.set(getUISteps());
        userSession = uiSteps.get().successfulLogIn();
    }
}
