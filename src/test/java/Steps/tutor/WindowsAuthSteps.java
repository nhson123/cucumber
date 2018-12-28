package Steps.tutor;

import Steps.tutor.pageFactory.WindowAutPageFactory;
import Steps.wiener.PageFactory.PFHelper;
import base.BaseUtil;
import cucumber.api.java8.En;

import java.io.IOException;

/** Package: Steps.tutor Generated by: Hoang.Son.Nguyen Generated at: 18.11.2018 2018 */
public class WindowsAuthSteps extends BaseUtil implements En {
  BaseUtil base;
  WindowAutPageFactory windowAutPageFactory;

  public WindowsAuthSteps(BaseUtil base) {
    this.base = base;
    Given(
        "^ich navigiere zu engprod$",
        () -> {
          base.webDriver.navigate().to("https://www.engprod-charter.net");
          PFHelper.threatSleep(2000);
          try {
            Runtime.getRuntime()
                .exec(
                    "C:\\Users\\Hoang.Son.Nguyen\\OneDrive\\Public\\Cucumber\\src\\test\\java\\Steps\\tutor\\windowAuth.exe");

            System.out.println("Donegggg");
          } catch (IOException e) {
            e.printStackTrace();
          }
        });
    When("^ich sende die logindaten und enter$", () -> {});
    Then("^das login erfolgt$", () -> {});
  }
}
