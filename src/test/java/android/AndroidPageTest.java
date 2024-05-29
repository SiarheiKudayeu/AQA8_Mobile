package android;

import driver_init.AppiumDriverInit;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.AndroidPage;

public class AndroidPageTest {
    AppiumDriver driver;
    AndroidPage androidPage;
    @BeforeClass
    public void setUp(){
        driver = new AppiumDriverInit().getDriver();
        androidPage = new AndroidPage(driver);
    }
    @AfterClass
    public void close(){
        driver.quit();
    }
    @Test
    public void checkTextClock() throws InterruptedException {
        androidPage.clickOnViews()
                .swipeUntilElementFoundTextClock(AndroidPage.Directions.DOWN)
                .clickOnTextClock()
                .checkClockFunction(6);
    }

}
