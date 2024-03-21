package runner;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.OutputType;
import org.testng.annotations.*;
import wrpr.Wrap;

@CucumberOptions(features={"src/test/resources/featureFiles"},
                 glue = {"pages"},
      //           tags="@ProfileScreen",
                 monochrome = true,
                 dryRun = false,
        plugin = {"pretty",
                  "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})
public class TestRunner extends Wrap {
    @BeforeSuite
    public void bs()
    {
        System.out.println("here");
        startAppiumServer();
    }

    @Parameters({"platformName", "deviceName", "udid", "appPackage", "appActivity", "automationName",
            "chromeDriverPort", "systemPort", "xcodeOrgId", "xcodeSigningId", "bundleId", "app", "mjpegServerPort",
            "wdaLocalPort"})
    @BeforeMethod
    public void bm(String platformName, String deviceName, @Optional("") String udid, @Optional("") String appPackage,
                   @Optional("") String appActivity, @Optional("") String automationName,
                   @Optional("") String chromeDriverPort, @Optional("") String systemPort, @Optional("") String xcodeOrgId,
                   @Optional("") String xcodeSigningId, @Optional("") String bundleId, @Optional("") String app,
                   @Optional("") String mjpegServerPort, @Optional("") String wdaLocalPort)
    {
        System.out.println("BM");
        launchApp(platformName, deviceName, udid, appPackage, appActivity, automationName, chromeDriverPort, systemPort,
                xcodeOrgId, xcodeSigningId, bundleId, app, mjpegServerPort, wdaLocalPort);
        System.out.println("AM");
    }


    @AfterStep
    public void afterEachStep(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = driver.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getId());
        }
    }

    @AfterMethod(alwaysRun = true)
    public void am() {
        closeApp();
    }

    @AfterSuite
    public void as() {
        stopAppiumServer();
    }


}
