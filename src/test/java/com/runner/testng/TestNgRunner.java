package com.runner.testng;
import java.util.List;

import com.api.utilities.Constants;
import org.testng.TestNG;

import com.beust.jcommander.internal.Lists;

public class TestNgRunner {
    public static void main(String[] args) {
        DynamicTestNg.main(args);
        TestNG testng = new TestNG();
        List < String > suites = Lists.newArrayList();
        suites.add(System.getProperty("user.dir") + Constants.FILE_SEPARATOR_KEY + DynamicTestNg.testNgXml);
        testng.setTestSuites(suites);
        testng.run();
    }
}