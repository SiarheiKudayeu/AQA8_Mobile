package waiters;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.function.Function;

public class CustomWaiters {
    private final WebDriver driver;

    public CustomWaiters(WebDriver driver) {
        this.driver = driver;
    }

    private FluentWait<WebDriver> fluentWait() {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotInteractableException.class)
                .ignoring(InvalidElementStateException.class)
                .ignoring(NoAlertPresentException.class)
                .ignoring(NoSuchFrameException.class);
    }

    private void waitForFunction(Function function) {
        FluentWait<WebDriver> wait = fluentWait();
        wait.until(function);
    }
    //visibilityOfElementLocated
    public WebElement waitForVisibility(By locator){
        return fluentWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForVisibility(WebElement element){
        return fluentWait().until(ExpectedConditions.visibilityOf(element));
    }
    //frameToBeAvailableAndSwitchToIt
    public void switchToFrame(By locator){
        waitForFunction(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
    }
    //alertIsPresent
    public Alert switchToAlert(){
        return fluentWait().until(ExpectedConditions.alertIsPresent());
    }
    //elementToBeClickable
    public WebElement clickableStateOfElement(By locator){
        return fluentWait().until(ExpectedConditions.elementToBeClickable(locator));
    }
    //elementToBeSelected
    public void waitForElementSelection(By locator){
        waitForFunction(ExpectedConditions.elementToBeSelected(locator));
    }
    //elementSelectionStateToBe
    public void waitForElementDeSelection(By locator){
        waitForFunction(ExpectedConditions.elementSelectionStateToBe(locator, false));
    }



}
