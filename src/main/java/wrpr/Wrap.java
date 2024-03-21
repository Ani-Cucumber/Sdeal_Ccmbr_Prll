package wrpr;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.StartsActivity;
import io.appium.java_client.android.connection.ConnectionStateBuilder;
import io.appium.java_client.android.connection.HasNetworkConnection;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Wrap extends AbstractTestNGCucumberTests {

    public static ThreadLocal<AppiumDriver> driver = new ThreadLocal<AppiumDriver>();
    public static final int MAX_SCROLL = 10;
    public AppiumDriverLocalService service;
    public AppiumServiceBuilder builder;
    public String serverURL;
    public String appserver;

    public static synchronized AppiumDriver getDriver() {
        return driver.get();
    }


    public void startAppiumServer() {
        builder = new AppiumServiceBuilder();
        builder.usingAnyFreePort();
//      TODO: update with correct path of your machine
//      TODO: uncomment the below lines for windows nachine
//      builder.usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"));
//      builder.withAppiumJS(new File("C:\\Users\\lokthy\\AppData\\Roaming\\npm\\node_modules\\appium"));
//      TODO: uncomment the below lines for MAC nachine
        HashMap<String, String> environment = new HashMap();
        environment.put("PATH", "/usr/local/bin:" + System.getenv("PATH"));
        builder.withEnvironment(environment);
        service = AppiumDriverLocalService.buildService(builder);
        service.start();
        serverURL = String.valueOf(service.getUrl());
        System.out.println("server should be up");
    }

    public void stopAppiumServer() {
        service.stop();
    }

    public boolean launchApp(String platformName, String deviceName, String udid, String appPackage, String appActivity,
                             String automationName, String chromeDriverPort, String systemPort, String xcodeOrgId, String xcodeSigningId,
                             String bundleId, String app, String mjpegServerPort, String wdaLocalPort) {
        try {
            DesiredCapabilities dc = new DesiredCapabilities();
            // To pas   s the Unique Device Identifier
            if (!udid.equals(""))
                dc.setCapability("udid", udid);
            // To pass the absolute path of the application
            if (!app.equals("")) {
                appserver = "/Snapdeal.apk";
                dc.setCapability("app", System.getProperty("user.dir") + appserver);
              //      dc.setCapability("app", "Users/a1707/Documents/Apps/Snapdeal.apk");
            }
            // Android
            if (!appPackage.equals(""))
                dc.setCapability("appPackage", appPackage);
            if (!appActivity.equals(""))
                dc.setCapability("appActivity", appActivity);
            // For hybrid app parallel testing
            if (!chromeDriverPort.equals(""))
                dc.setCapability("chromedriverPort", chromeDriverPort);
            // For native app parallel testing
            if (!mjpegServerPort.equals(""))
                dc.setCapability("mjpegServerPort", mjpegServerPort);
            // For hybrid/native app parallel testing
            if (!systemPort.equals(""))
                dc.setCapability("systemPort", systemPort);
            // iOS
            // For hybrid/native app parallel testing
            if (!wdaLocalPort.equals(""))
                dc.setCapability("wdaLocalPort", wdaLocalPort);
            // To pass the Xcode Org ID if the application and WDA are built with different developer certificates
            if (!xcodeOrgId.equals(""))
                dc.setCapability("xcodeOrgId", xcodeOrgId);
            // To pass the Xcode Signing ID if the application and WDA are built with different developer certificates
            if (!xcodeSigningId.equals(""))
                dc.setCapability("xcodeSigningId", xcodeSigningId);
            if (!bundleId.equals(""))
                dc.setCapability("bundleId", bundleId);
            // Mandatory desired capabilities
            // To set the device name
            dc.setCapability("deviceName", deviceName);
            // To pass the VDM
            dc.setCapability("automationName", automationName);
            System.out.println("--168--");

            // Comment the below line based on need
            if (platformName.equalsIgnoreCase("Android")) {
                // Comment the below line based on need
                dc.setCapability("autoGrantPermissions", true);
        /*        URL url= new URL("http://127.0.0.1:4723/wd/hub");
                driver= new AndroidDriver(url,dc);
        */      driver.set(new AndroidDriver(new URI(serverURL).toURL(),dc));
            } else if (platformName.equalsIgnoreCase("iOS")) {
                // Comment the below line based on need
                dc.setCapability("autoAcceptAlerts", true);
          //      URL url= new URL("http://127.0.0.1:4723/wd/hub");
                //driver=(new IOSDriver(new URI(serverURL).toURL(), dc));
           //     driver= new IOSDriver(url,dc);
                driver.set(new IOSDriver(new URI(serverURL).toURL(), dc));
            }

            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));


        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public WebElement getWebElement(String locator, String locValue) {
        try {
            switch (locator) {
                case "id":
//                    return getDriver().findElement(AppiumBy.id(locValue));
                    return getDriver().findElement(AppiumBy.xpath("//*[@resource-id='" + locValue + "' or @id='" + locValue + "']"));
                case "name":
//					return getDriver().findElement(AppiumBy.name(locValue));
                    return getDriver().findElement(AppiumBy.xpath("//*[@name='" + locValue + "']"));
                case "className":
                    return getDriver().findElement(AppiumBy.className(locValue));
                case "link":
                    return getDriver().findElement(AppiumBy.linkText(locValue));
                case "partialLink":
                    return getDriver().findElement(AppiumBy.partialLinkText(locValue));
                case "tag":
                    return getDriver().findElement(AppiumBy.tagName(locValue));
                case "css":
                    return getDriver().findElement(AppiumBy.cssSelector(locValue));
                case "xpath":
                    return getDriver().findElement(AppiumBy.xpath(locValue));
                case "accessibilityId":
                    return getDriver().findElement(AppiumBy.accessibilityId(locValue));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean eleIsDisplayed(WebElement ele) {
        try {
            return ele.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifyText(WebElement ele, String Expected) {
        try {
            return ele.getText().equals(Expected);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // To scroll in application
    private boolean scroll(int startX, int startY, int endX, int endY) {
        try {
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence sequence = new Sequence(finger, 1);
            sequence.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
            sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            sequence.addAction(finger.createPointerMove(Duration.ofSeconds(2), PointerInput.Origin.viewport(), endX, endY));
            sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            getDriver().perform(Collections.singletonList(sequence));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // To double tap in application
    public void doubleTap(int x, int y) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence doubleTap = new Sequence(finger, 1);
        doubleTap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
        doubleTap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        doubleTap.addAction(new Pause(finger, Duration.ofMillis(100)));
        doubleTap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        doubleTap.addAction(new Pause(finger, Duration.ofMillis(100)));
        doubleTap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        doubleTap.addAction(new Pause(finger, Duration.ofMillis(100)));
        doubleTap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        getDriver().perform(Collections.singletonList(doubleTap));
    }

    // To long press in application
    public void longPress(int x, int y) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence longPress = new Sequence(finger, 1);
        longPress.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
        longPress.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        longPress.addAction(new Pause(finger, Duration.ofMillis(2000)));
        longPress.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        getDriver().perform(Collections.singletonList(longPress));
        System.out.println();
    }

    // To pinch in application
    public void pinchInApp() {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        int maxY = getDriver().manage().window().getSize().getHeight();
        int maxX = getDriver().manage().window().getSize().getWidth();
        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence a = new Sequence(finger1, 1);
        a.addAction(finger1.createPointerMove(Duration.ofSeconds(0), PointerInput.Origin.viewport(), (int) (maxX * 0.75),
                (int) (maxY * 0.25)));
        a.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        a.addAction(finger1.createPointerMove(Duration.ofSeconds(1), PointerInput.Origin.viewport(), (int) (maxX * 0.5),
                (int) (maxY * 0.5)));
        a.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");
        Sequence b = new Sequence(finger2, 1);
        b.addAction(finger2.createPointerMove(Duration.ofSeconds(0), PointerInput.Origin.viewport(), (int) (maxX * 0.25),
                (int) (maxY * 0.75)));
        b.addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        b.addAction(finger2.createPointerMove(Duration.ofSeconds(1), PointerInput.Origin.viewport(), (int) (maxX * 0.5),
                (int) (maxY * 0.5)));
        b.addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        getDriver().perform(Arrays.asList(a, b));
    }

    // To zoom in application
    public void zoomInApp() {
        int maxY = getDriver().manage().window().getSize().getHeight();
        int maxX = getDriver().manage().window().getSize().getWidth();
        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "lokesh-finger1");
        Sequence a = new Sequence(finger1, 1);
        a.addAction(finger1.createPointerMove(Duration.ofSeconds(0), PointerInput.Origin.viewport(), (int) (maxX * 0.5),
                (int) (maxY * 0.5)));
        a.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        a.addAction(finger1.createPointerMove(Duration.ofSeconds(1), PointerInput.Origin.viewport(), (int) (maxX * 0.75),
                (int) (maxY * 0.25)));
        a.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "lokesh-finger2");
        Sequence b = new Sequence(finger2, 1);
        b.addAction(finger2.createPointerMove(Duration.ofSeconds(0), PointerInput.Origin.viewport(), (int) (maxX * 0.5),
                (int) (maxY * 0.5)));
        b.addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        b.addAction(finger2.createPointerMove(Duration.ofSeconds(1), PointerInput.Origin.viewport(), (int) (maxX * 0.25),
                (int) (maxY * 0.75)));
        b.addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        getDriver().perform(Arrays.asList(a, b));
    }

    private boolean swipeUpInApp() {
        Dimension size = getDriver().manage().window().getSize();
        int startX = (int) (size.getWidth() * 0.5);
        int startY = (int) (size.getHeight() * 0.8);
        int endX = (int) (size.getWidth() * 0.5);
        int endY = (int) (size.getHeight() * 0.2);
        return scroll(startX, startY, endX, endY);
    }

    // To scroll down in application
    private boolean swipeDownInApp() {
        Dimension size = getDriver().manage().window().getSize();
        int startX = (int) (size.getWidth() * 0.5);
        int startY = (int) (size.getHeight() * 0.2);
        int endX = (int) (size.getWidth() * 0.5);
        int endY = (int) (size.getHeight() * 0.8);
        return scroll(startX, startY, endX, endY);
    }

    // To scroll left in application
    private boolean swipeLeftInApp() {
        Dimension size = getDriver().manage().window().getSize();
        int startX = (int) (size.getWidth() * 0.8);
        int startY = (int) (size.getHeight() * 0.5);
        int endX = (int) (size.getWidth() * 0.2);
        int endY = (int) (size.getHeight() * 0.5);
        return scroll(startX, startY, endX, endY);
    }

    // To scroll right in application
    private boolean swipeRightInApp() {
        Dimension size = getDriver().manage().window().getSize();
        int startX = (int) (size.getWidth() * 0.2);
        int startY = (int) (size.getHeight() * 0.5);
        int endX = (int) (size.getWidth() * 0.8);
        int endY = (int) (size.getHeight() * 0.5);
        return scroll(startX, startY, endX, endY);
    }

    private boolean swipeDownInAppWithWebElement(WebElement ele) {
        Rectangle rect = ele.getRect();
        int startX = (int) (((rect.getWidth()) * 0.5) + rect.getX());
        int startY = (int) (((rect.getHeight()) * 0.2) + rect.getY());
        int endX = (int) (((rect.getWidth()) * 0.5) + rect.getX());
        int endY = (int) (((rect.getHeight()) * 0.8) + rect.getY());
        return scroll(startX, startY, endX, endY);
    }

    // To scroll up within web element in application
    private boolean swipeUpInAppWithWebElement(WebElement ele) {
        Rectangle rect = ele.getRect();
        int startX = (int) (((rect.getWidth()) * 0.5) + rect.getX());
        int startY = (int) (((rect.getHeight()) * 0.8) + rect.getY());
        int endX = (int) (((rect.getWidth()) * 0.5) + rect.getX());
        int endY = (int) (((rect.getHeight()) * 0.2) + rect.getY());
        return scroll(startX, startY, endX, endY);
    }

    // To scroll right within web element in application
    private boolean swipeRightInAppWithWebElement(WebElement ele) {
        Rectangle rect = ele.getRect();
        int startX = (int) (((rect.getWidth()) * 0.2) + rect.getX());
        int startY = (int) (((rect.getHeight()) * 0.5) + rect.getY());
        int endX = (int) (((rect.getWidth()) * 0.8) + rect.getX());
        int endY = (int) (((rect.getHeight()) * 0.5) + rect.getY());
        return scroll(startX, startY, endX, endY);
    }

    // To scroll left within web element in application
    private boolean swipeLeftInAppWithWebElement(WebElement ele) {
        Rectangle rect = ele.getRect();
        int startX = (int) (((rect.getWidth()) * 0.8) + rect.getX());
        int startY = (int) (((rect.getHeight()) * 0.5) + rect.getY());
        int endX = (int) (((rect.getWidth()) * 0.2) + rect.getX());
        int endY = (int) (((rect.getHeight()) * 0.5) + rect.getY());
        return scroll(startX, startY, endX, endY);
    }

    // To scroll up until web element is displayed in application
    public boolean swipeUpInAppUntilElementIsVisible(String locator, String locValue) {
        int i = 1;
        while (!eleIsDisplayed(getWebElement(locator, locValue))) {
            swipeUpInApp();
            i++;
            if (i == MAX_SCROLL)
                break;
        }
        return true;
    }

    // To scroll down until web element is displayed in application
    public boolean swipeDownInAppUntilElementIsVisible(String locator, String locValue) {
        int i = 1;
        while (!eleIsDisplayed(getWebElement(locator, locValue))) {
            swipeDownInApp();
            i++;
            if (i == MAX_SCROLL)
                break;
        }
        return true;
    }

    // To scroll left until web element is displayed in application
    public boolean swipeLeftInAppUntilElementIsVisible(String locator, String locValue) {
        int i = 1;
        while (!eleIsDisplayed(getWebElement(locator, locValue))) {
            swipeLeftInApp();
            i++;
            if (i == MAX_SCROLL)
                break;
        }
        return true;
    }

    // To scroll right until web element is displayed in application
    public boolean swipeRightInAppUntilElementIsVisible(String locator, String locValue) {
        int i = 1;
        while (!eleIsDisplayed(getWebElement(locator, locValue))) {
            swipeRightInApp();
            i++;
            if (i == MAX_SCROLL)
                break;
        }
        return true;
    }

    // To close all the application opened in this session
    public void closeApp() {
        if (driver != null) {
            try {
                getDriver().quit();
            } catch (Exception ignored) {
            }
        }
    }


    // To enter data in web element
    public boolean enterValue(WebElement ele, String data) {
        ele.clear();
        ele.sendKeys(data);
        return true;
    }

    // To click in web element
    public boolean click(WebElement ele) {
        try {
            ele.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // To get text in web element
    public String getText(WebElement ele) {
        return ele.getText();
    }

    public boolean pressEnter() {
        ((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
        return true;
    }

    public boolean pressBack() {
        ((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.BACK));
        return true;
    }

    public void showNotificationMenu() {
        ((AndroidDriver) getDriver()).openNotifications();
    }

    public void hideNotificationMenu() {
        pressBack();
    }

    public boolean dataOffInAndroid() {
        ((HasNetworkConnection) driver).setConnection(new ConnectionStateBuilder().withWiFiDisabled().build());
        ((HasNetworkConnection) driver).setConnection(new ConnectionStateBuilder().withDataDisabled().build());
        return true;
    }

    public boolean dataOnInAndroid() {
        ((HasNetworkConnection) driver).setConnection(new ConnectionStateBuilder().withWiFiEnabled().build());
        ((HasNetworkConnection) driver).setConnection(new ConnectionStateBuilder().withDataEnabled().build());
        return true;
    }

    public String getCurrentActivity() {
        return ((StartsActivity) driver).currentActivity();
    }

    public String getCurrentAppPackage() {
        return ((StartsActivity) driver).getCurrentPackage();
    }

    public boolean deleteChromeCookies() {
        getDriver().manage().deleteAllCookies();
        return true;
    }


    public void waitForVisibility(WebElement e)
    {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(70));
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public void waitForExplicitVisibility(WebElement e){
        Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver())
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOf(e));
    }
}
