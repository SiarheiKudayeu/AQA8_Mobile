package driver_init;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumDriverInit {
    AppiumDriver driver;

    public AppiumDriver getDriver(){
        try {
            driver = initialDriver();
        }catch (MalformedURLException e){
        }
        return this.driver;
    }


    private AppiumDriver initialDriver() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android11");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        desiredCapabilities.setCapability("appPackage", "io.appium.android.apis");
        desiredCapabilities.setCapability("appActivity", "io.appium.android.apis.ApiDemos");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        driver =new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
        return driver;
    }
}
