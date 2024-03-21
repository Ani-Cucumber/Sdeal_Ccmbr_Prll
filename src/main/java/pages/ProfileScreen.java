package pages;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import wrpr.Wrap;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ProfileScreen extends Wrap {

    @AndroidFindBy(xpath="//*[@text='Login/Sign up']")
    @iOSXCUITFindBy()
    protected WebElement signUp;

    @AndroidFindBy(className="android.widget.AutoCompleteTextView")
    @iOSXCUITFindBy()
    protected WebElement mobileNumber;

   @AndroidFindBy(id="continuebtn")
   @iOSXCUITFindBy()
   protected WebElement continueButton;

    @AndroidFindBy(id="edtName")
    @iOSXCUITFindBy()
    protected WebElement name;

    @AndroidFindBy(id="txtAutoFill")
    @iOSXCUITFindBy()
    protected WebElement autoFill;

    @AndroidFindBy(xpath="//*[@text='LEGAL POLICIES']")
    @iOSXCUITFindBy()
    protected WebElement legalPolicies;

    @AndroidFindBy(xpath="//android.widget.TextView[@text='TERMS OF USE']")
    @iOSXCUITFindBy()
    protected WebElement TOS;

    @AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'ABUSE')]")
    @iOSXCUITFindBy()
    protected WebElement RAT;

    public ProfileScreen() {
    PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    @And("taps on Signup button")
    public void userTapsOnFilter() {
        click(signUp);
    }

    @Then("user is asked for mobileNumber")
    public void UserIsAskedForMobileNumber() {
        waitForVisibility(mobileNumber);
        Assert.assertTrue(eleIsDisplayed(mobileNumber));
    }

    @When("user enters number {string}")
    public void userEntersNumber(String arg0) {
        click(mobileNumber);
        enterValue(mobileNumber, arg0);
      //  pressEnter();
    }

    @And("taps on Continue button")
    public void TapsOnContinueButton() {
        click(continueButton);
    }

    @Then("user is asked for name")
    public void UserIsAskedForName() {
        waitForVisibility(name);
        Assert.assertTrue(eleIsDisplayed(name));
    }

    @When("user enters {string}")
    public void userEntersName(String arg0) {
        waitForVisibility(name);
        click(name);
        enterValue(name, arg0);
        //pressEnter();
    }

    @Then("user is moved to Verify OTP screen")
    public void userIsMovedToOTPScreen() {
        waitForVisibility(autoFill);
        Assert.assertTrue(eleIsDisplayed(autoFill));
    }

    @Then("user is not asked for name")
    public void UserIsNotAskedForName() {
        //waitForVisibility(name);
        Assert.assertFalse(eleIsDisplayed(name));
    }

    @Then("Terms button is not visible")
    public void TermsButtonIsNotVisible() {
        //waitForVisibility(name);
        Assert.assertFalse(eleIsDisplayed(TOS));
    }

    @Then("Report button is not visible")
    public void ReportButtonIsNotVisible() {
        //waitForVisibility(name);
        Assert.assertFalse(eleIsDisplayed(RAT));
    }

    @When("user taps on Legal button")
    public void TapsOnLegalButton() {
        click(legalPolicies);
    }

    @Then("Terms button is visible")
    public void TermsButtonIsVisible() {
        //waitForVisibility(name);
        Assert.assertTrue(eleIsDisplayed(TOS));
    }

    @And("Report button is visible")
    public void ReportButtonIsVisible() {
        //waitForVisibility(name);
        Assert.assertTrue(eleIsDisplayed(RAT));
    }

}
