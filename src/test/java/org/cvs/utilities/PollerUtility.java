package org.cvs.utilities;

import org.hamcrest.Matcher;

import java.util.concurrent.Callable;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.with;

public class PollerUtility {

    public static <T> T waiter(Callable<T> callable, Matcher<? super T> matcher) {
        return with()
                .pollInSameThread()
                .pollInterval(500, MILLISECONDS)
                .pollDelay(1, SECONDS)
                .atMost(30, SECONDS)
                .until(callable, matcher);
    }
}
