package org.cvs.utilities;

import com.github.javafaker.Faker;

public class GenerateTestData {

    private final static Faker FAKER = new Faker();

    public static String gitHubPath() {
        return FAKER.numerify("path###");
    }

    public static String gitHubFilePath() {
        return FAKER.numerify("file###.rb");
    }
}
