package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import wrpr.Wrap;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class SearchScreen extends Wrap {

    @AndroidFindBy(id="mFilterClickView")
    @iOSXCUITFindBy()
    protected WebElement filterButton;

    @AndroidFindBy(xpath="//*[contains(@text,'Rating')]")
    @iOSXCUITFindBy()
    protected WebElement customerRating;

    @AndroidFindBy(xpath="//*[contains(@text,'4.0')]")
    @iOSXCUITFindBy()
    protected WebElement ratingAbove4;

    @AndroidFindBy(xpath="//*[contains(@text,'Discount')]")
    @iOSXCUITFindBy()
    protected WebElement discount;

    @AndroidFindBy(xpath="//*[contains(@text,'0 - 10')]")
    @iOSXCUITFindBy()
    protected WebElement discountUpto10;

    @AndroidFindBy(id="applyFilterButton")
    @iOSXCUITFindBy()
    protected WebElement applyFilter;

    @AndroidFindBy(xpath="//*[contains(@text,'Showing results for')]")
    @iOSXCUITFindBy()
    protected WebElement showingResultsLabel;

    @AndroidFindBy(xpath="(//*[contains(@text,'novels')])[1]")
    @iOSXCUITFindBy()
    protected WebElement thrillerNovelsLabel;

    @AndroidFindBy(xpath="(//android.widget.TextView[@resource-id='com.snapdeal.main:id/guide_name'])[7]")
    @iOSXCUITFindBy()
    protected WebElement size40;

    @AndroidFindBy(xpath="//*[contains(@text,'Sort')]")
    @iOSXCUITFindBy()
    protected WebElement sortButton;

    @AndroidFindBy(xpath="//*[contains(@text,'Discount')]")
    @iOSXCUITFindBy()
    protected WebElement sortByDiscount;

    @AndroidFindBy(xpath="(//*[@resource-id='com.snapdeal.main:id/productDisplayPrice'])[1]")
    @iOSXCUITFindBy()
    protected WebElement discountedPrice;

    @AndroidFindBy(xpath="(//android.widget.TextView[@resource-id='com.snapdeal.main:id/productOldPrice'])[1]")
    @iOSXCUITFindBy()
    protected WebElement originalPrice;

    @AndroidFindBy(xpath="(//android.widget.TextView[@resource-id='com.snapdeal.main:id/productDiscount'])[1]")
    @iOSXCUITFindBy()
    protected WebElement discountPercent;

    public SearchScreen()
    {
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()),this);
    }

    @And("user taps on Filter")
    public void userTapsOnFilter() {
        click(filterButton);
    }

    @And("selects Customer rating of {int} and above")
    public void selectsCustomerRatingOfAndAbove(int arg0) {
        waitForVisibility(customerRating);
        click(customerRating);
        click(ratingAbove4);
    }

    @And("discount of {string}")
    public void discountOf(String arg0) {
    }

    @And("taps om Apply Filter")
    public void tapsOmApplyFilter() {
        click(applyFilter);
    }

    @Then("user is moved to result screen")
    public void userIsMovedToResultScreen() {
        waitForVisibility(filterButton);
        Assert.assertTrue(eleIsDisplayed(filterButton));
    }

    @And("screen has matching labels")
    public void screenHasMatchingLabels() {
        Assert.assertTrue(eleIsDisplayed(showingResultsLabel));
     //   Assert.assertTrue(eleIsDisplayed(thrillerNovelsLabel));
    }

    @And("selects size of {string}")
    public void selectsSizeOf(String arg0) {
        waitForVisibility(size40);
        click(size40);
    }

    @When("user applies Sort by discount")
    public void userAppliesSortByDiscount() {
        click(sortButton);
        waitForVisibility(sortByDiscount);
        click(sortByDiscount);
    }

    @Then("user is shown sorted list")
    public void userIsShownSortedList() {
    }

    @And("user can see scratched price and new price")
    public void userCanSeeScratchedPriceAndNewPrice() {
        waitForVisibility(discountedPrice);
        Assert.assertTrue(eleIsDisplayed(discountedPrice));
        Assert.assertTrue(eleIsDisplayed(originalPrice));
        Assert.assertTrue(eleIsDisplayed(discountPercent));
    }

}
