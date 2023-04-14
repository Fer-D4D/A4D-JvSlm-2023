package org.core;

import com.beust.ah.A;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Core {

    public static WebDriver driver;

    private static final double DEFAULT_TIME_2_WASTE = 2;
    private static final long DEFAULT_TIME_2_WAIT = 4;
    private static final long DEFAULT_IMPLICIT_WAIT = 10;
    public WebDriver getDriver(String desiredBrowser) {
        if(desiredBrowser.equalsIgnoreCase("edge")){
            driver = WebDriverManager.edgedriver().create();
            return driver;
        }
        if(desiredBrowser.equalsIgnoreCase("firefox"))
            return WebDriverManager.firefoxdriver().create();
        return WebDriverManager.chromedriver().create();
    }

    public WebDriver launchSite(String desiredBrowser, String targetSite) {
        WebDriver driver = this.getDriver(desiredBrowser);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(DEFAULT_IMPLICIT_WAIT));
        driver.get(targetSite);
        driver.manage().window().maximize();
        return driver;
    }

    public WebElement getElement(WebDriver driver, String locatorType, String locatorDefinition){
        WebElement element;
        if (locatorType.equalsIgnoreCase("css")){
            element = driver.findElement(By.cssSelector(locatorDefinition));
            move2TargetElement(element);
            return element;
        }
        element = driver.findElement(By.xpath(locatorDefinition));
        move2TargetElement(element);
        return element;
    }

    public void move2TargetElement(WebElement element){
        Actions actions = new Actions(driver);
        actions.scrollToElement(element);
    }


    public boolean fillInputText(WebDriver driver, String locatorType, String locatorDefinition, String text2fill){
        WebElement webElement = this.getElement(driver, locatorType, locatorDefinition);
        if(this.elementReady2BeClickable(driver,webElement)){
            webElement.sendKeys(text2fill);
            return true;
        }
        return false;
    }

    public void fillInputText(WebElement element,  String text2fill){
       element.sendKeys(text2fill);
    }

    public boolean doClick( String locatorType, String locatorDefinition) throws InterruptedException {
        WebElement webElement = this.getElement(driver, locatorType, locatorDefinition);
        wasteSomeTime();
        if(this.elementReady2BeClickable(driver,webElement)){
            webElement.click();
            return true;
        }
        return false;
    }

    public void wasteSomeTime() throws InterruptedException {
        Thread.sleep((long) (DEFAULT_TIME_2_WASTE*1000));
    }

    public void wasteSomeTime(double wastedTime) throws InterruptedException {
        Thread.sleep((long) (wastedTime*1000));
    }

    public boolean elementReady2BeClickable(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIME_2_WAIT));
        try{
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        }catch (TimeoutException e){
            return false;
        }
    }


    public String getTextFromElement(String locatorType, String locatorDefinition) throws InterruptedException {
        wasteSomeTime(.5);
        return this.getElement(driver, locatorType, locatorDefinition).getText();
    }

}
