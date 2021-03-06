package Steps;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

/**
 * Package: PACKAGE_NAME @FindBy(css="") private WebElement webElement; Generated by:
 * Hoang.Son.Nguyen Generated at: 21.10.2018 2018
 */

// @RunWith(Cucumber.class)

@CucumberOptions(
    features = "src//test//java//features//tutor",
    glue = {"Steps"},
    plugin = {
      "pretty",
      "json:target/cucumber-reports/Cucumber.json",
      "junit:target/cucumber-reports/Cucumber.xml",
      "html:target/cucumber-reports"
    },
    tags = ("@chrome, @firefox"),
    monochrome = true,
    dryRun = false)
public class runAsNGTest extends AbstractTestNGCucumberTests {}
