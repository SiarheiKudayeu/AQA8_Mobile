package page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class AndroidPage {
    AppiumDriver driver;
    WebDriverWait waiters;

    public AndroidPage(AppiumDriver driver) {
        this.driver = driver;
        waiters = new WebDriverWait(driver, 10);
    }

    private static final class Locators {
        private static final By views = MobileBy.AccessibilityId("Views");
        private static final By textClock = MobileBy.AccessibilityId("TextClock");
        private static final By time = MobileBy.xpath("//android.widget.TextView[4]");
    }

    public AndroidPage clickOnViews() {
        waiters.until(ExpectedConditions.visibilityOfElementLocated(Locators.views));
        driver.findElement(Locators.views).click();
        return this;
    }

    public AndroidPage clickOnTextClock() {
        waiters.until(ExpectedConditions.visibilityOfElementLocated(Locators.textClock));
        driver.findElement(Locators.textClock).click();
        return this;
    }

    public enum Directions {UP, DOWN}

    private void swipe(int startX, int startY, int finishX, int finishY) {
        TouchAction action = new TouchAction<>(driver);
        action
                .press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(finishX, finishY))
                .release().perform();
    }

    public AndroidPage swipe(Directions directions) {
        int startX = 0;
        int startY = 0;
        int finishX = 0;
        int finishY = 0;
        Dimension screenSize = driver.manage().window().getSize();
        switch (directions) {
            case DOWN -> {
                startX = screenSize.width / 2;
                finishX = screenSize.width / 2;
                startY = (int) (screenSize.height * 0.8);
                finishY = (int) (screenSize.height * 0.2);
            }
            case UP -> {
                startX = screenSize.width / 2;
                finishX = screenSize.width / 2;
                startY = (int) (screenSize.height * 0.2);
                finishY = (int) (screenSize.height * 0.8);
            }
        }
        swipe(startX, startY, finishX, finishY);
        return this;
    }

    public AndroidPage swipeUntilElementFoundTextClock(Directions directions) {
        while (driver.findElements(Locators.textClock).isEmpty()) {
            swipe(directions);
        }
        return this;
    }

    public AndroidPage checkClockFunction(int timeToWait) {
        waiters.until(ExpectedConditions.visibilityOfElementLocated(Locators.time));
        int startSeconds =
                Integer.parseInt(driver.findElement(Locators.time).getText().split("\\s")[0].split(":")[2]);
        try {
            Thread.sleep(timeToWait * 1000);
        } catch (InterruptedException e) {
        }
        int finishSeconds =
                Integer.parseInt(driver.findElement(Locators.time).getText().split("\\s")[0].split(":")[2]);
        int resultSeconds = finishSeconds - startSeconds;
        Assert.assertEquals(timeToWait, resultSeconds);
        return this;
    }


}
