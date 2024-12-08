package org.cvs.tests.context;

import java.time.Duration;

public class WebTimeouts {

    public static final Duration EXPLICIT_TIMEOUT = Duration.ofSeconds(30);
    public static final int IMPLICIT_TIMEOUT = 20000;
    public static final int PAGE_LOAD_TIMEOUT = 90000;
}
