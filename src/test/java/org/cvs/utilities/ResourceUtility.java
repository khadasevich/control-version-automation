package org.cvs.utilities;

import java.io.InputStream;

public class ResourceUtility {

    private static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    public static InputStream getResourceStreamViaName(String resourceName) {
        return getClassLoader().getResourceAsStream(resourceName);
    }
}
