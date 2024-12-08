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

    public static String branchName() {
        return FAKER.numerify("branch_##");
    }

    public static String gitCommitFileName() {
        return FAKER.numerify("commit###");
    }

    public static String gitPRName() {
        return FAKER.numerify("pr###");
    }
}
