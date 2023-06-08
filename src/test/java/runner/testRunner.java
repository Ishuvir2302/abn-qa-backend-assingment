package runner;

import java.io.File;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "feature" }, glue = { "stepdefinitions" }, plugin = {
        "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber_report.html",
        "html:output/html-report" }, dryRun = false, tags = { "@E2E" }, monochrome = true

)
public class testRunner {
    @AfterClass
    public static void writeExtentReport() {
        Reporter.loadXMLConfig(new File(System.getProperty("user.dir") + "/configurations/extent-config.xml"));
        Reporter.setSystemInfo("user", System.getProperty("user.name"));
        Reporter.setSystemInfo("os", "Mac OS");
        Reporter.setTestRunnerOutput("Sample test runner output message");
    }
}
