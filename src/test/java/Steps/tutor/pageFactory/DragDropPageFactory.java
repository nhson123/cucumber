package Steps.tutor.pageFactory;

import Steps.wiener.PageFactory.PFHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.picocontainer.classname.ClassName;

import java.util.logging.Logger;

import static org.testng.Assert.assertTrue;

/** Package: Steps.tutor.pageFactory Generated by: Hoang.Son.Nguyen Generated at: 23.11.2018 2018 */
public class DragDropPageFactory {
  private WebDriver driver;
  private static final Logger LOGGER = Logger.getLogger(ClassName.class.getName());

  public DragDropPageFactory(WebDriver driver) {
    this.driver = driver;
  }

  @CacheLookup
  @FindBy(how = How.XPATH, using = "//img[@src='img/logo.png']")
  WebElement guruLogo;

  public void guruLogo_check() {
    assertTrue(guruLogo.isDisplayed());
  }

  @FindBy(how = How.XPATH, using = "//a[contains(text(),'BANK')]")
  @CacheLookup
  WebElement bankButton;

  @FindBy(how = How.XPATH, using = "//ol[@id='amt7']//li[@class='placeholder']")
  @CacheLookup
  WebElement debitAmount;

  @FindBy(how = How.XPATH, using = "//ol[@id='bank']//li[@class='placeholder']")
  @CacheLookup
  WebElement debitAccount;

  @FindBy(how = How.XPATH, using = "//section[@id='g-container-main']//ul//li[2]")
  @CacheLookup
  WebElement debit5000;

  public void dragDropBank() {
    PFHelper.dragAndDrop(driver, bankButton, debitAccount);
    PFHelper.threatSleep(1000);
  }

  public void dragDrop5000() {
    PFHelper.dragAndDrop(driver, debit5000, debitAmount);
    PFHelper.threatSleep(1000);
  }
}
