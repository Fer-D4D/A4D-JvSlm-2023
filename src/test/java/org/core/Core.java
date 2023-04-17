package org.core;

import com.beust.ah.A;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
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

    public WebElement getElement(WebDriver driver, String locatorDefinition){
        try {
            WebElement element;
            if (locatorDefinition.toLowerCase().contains("css:")) {
                element = driver.findElement(By.cssSelector(StringUtils.replaceIgnoreCase(locatorDefinition, "CSS:", "")));
                move2TargetElement(element);
                return element;
            }
            if (locatorDefinition.toLowerCase().contains("id:")) {
                element = driver.findElement(By.cssSelector(StringUtils.replaceIgnoreCase(locatorDefinition, "ID:", "#")));
                move2TargetElement(element);
                return element;
            }
            element = driver.findElement(By.xpath(StringUtils.replaceIgnoreCase(locatorDefinition, "XPATH:", "")));
            move2TargetElement(element);
            return element;
        }catch (NoSuchElementException e){
            return null;
        }
    }

    public void move2TargetElement(WebElement element){
        Actions actions = new Actions(driver);
        actions.scrollToElement(element);
    }


    public boolean fillInputText(WebDriver driver, String locatorType, String locatorDefinition, String text2fill){
        WebElement webElement = this.getElement(driver, locatorType, locatorDefinition);
        if(this.elementReady2BeClickable(webElement)){
            webElement.sendKeys(text2fill);
            return true;
        }
        return false;
    }

    public boolean fillInputText(String locatorDefinition,  String text2fill){
        WebElement webElement = this.getElement(driver, locatorDefinition);
        if(this.elementReady2BeClickable(webElement)){
            webElement.sendKeys(text2fill);
            return true;
        }
        return false;
    }

    public boolean doClick( String locatorType, String locatorDefinition) throws InterruptedException {
        WebElement webElement = this.getElement(driver, locatorType, locatorDefinition);
        wasteSomeTime();
        if(this.elementReady2BeClickable(webElement)){
            webElement.click();
            return true;
        }
        return false;
    }

    public boolean doClick( String locatorDefinition) throws InterruptedException {
        WebElement webElement = this.getElement(driver, locatorDefinition);
        //wasteSomeTime();
        if(this.elementReady2BeClickable(webElement)){
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

    public boolean elementReady2BeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIME_2_WAIT));
        try{
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        }catch (TimeoutException e){
            return false;
        }
    }


    public String getTextFromElement( String locatorDefinition) throws InterruptedException {
        wasteSomeTime(.5);
        WebElement element = this.getElement(driver, locatorDefinition);
        if(element != null)
            return element.getText();
        return "Element not found, nothing to show here :P";
    }

}
