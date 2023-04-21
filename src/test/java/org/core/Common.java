package org.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Common {

    public static final long DEFAULT_WAITING_TIME = 2;
    public static String browser = "chrome";
    public static boolean viewer_mode = false;
    public static WebDriver driver;
    public Common(String browser, boolean viewer_mode){
        Common.browser = browser.toLowerCase();
        Common.viewer_mode = viewer_mode;
    }

    public Common(String browser){
        Common.browser = browser.toLowerCase();
    }

    public WebDriver setWebdriver(){
        if(Common.browser.equalsIgnoreCase("edge"))
            return WebDriverManager.edgedriver().create();
        if(Common.browser.equalsIgnoreCase("firefox"))
            return WebDriverManager.firefoxdriver().create();
        return WebDriverManager.chromedriver().create();
    }

    public void launchSite(String targetURL, String key_locator_definition){
        Common.driver = setWebdriver();
        Common.driver.get(targetURL);
        Common.driver.manage().window().maximize();
    }

    public void launchSite(String targetURL){
        Common.driver = setWebdriver();
        Common.driver.get(targetURL);
        Common.driver.manage().window().maximize();
    }

    public void wasteSometime(long waitingTime) throws InterruptedException {
        Thread.sleep(waitingTime*1000);
    }

    public WebElement getElement(String locatorDefinition) throws InterruptedException {
        if(Common.viewer_mode)
            wasteSometime(DEFAULT_WAITING_TIME);
        if (locatorDefinition.toLowerCase().contains("css:")) {
           return Common.driver.findElement(By.cssSelector(StringUtils.replaceIgnoreCase(locatorDefinition, "CSS:", "")));
        }
        if (locatorDefinition.toLowerCase().contains("xpath:")) {
            return Common.driver.findElement(By.xpath(StringUtils.replaceIgnoreCase(locatorDefinition, "XPATH:", "")));
        }
        if (locatorDefinition.toLowerCase().contains("id:")) {
            return Common.driver.findElement(By.cssSelector(StringUtils.replaceIgnoreCase(locatorDefinition, "ID:", "#")));
        }
        if (locatorDefinition.toLowerCase().contains("name:")) {
            return Common.driver.findElement(By.xpath(StringUtils.replaceIgnoreCase(locatorDefinition, "NAME:", "//*[@name='") + "']"));
        }
        if (locatorDefinition.toLowerCase().contains("link text:")) {
            return Common.driver.findElement(By.xpath(StringUtils.replaceIgnoreCase(locatorDefinition, "LINK TEXT:", "//a[contains(text(),'") + "')]"));
        }
        return null;
    }

    public void doClick(String locatorDefinition) throws InterruptedException {

        WebElement element = this.getElement(locatorDefinition);
        move2TargetElement(element);
        element.click();
    }

    public void fillInputText(String locatorDefinition, String text) throws InterruptedException {
        WebElement element = this.getElement(locatorDefinition);
        move2TargetElement(element);
        element.sendKeys(text);
    }

    public void selectDropdownOption( String locatorDefinition, String optionValue) throws InterruptedException {
        new Select(this.getElement(locatorDefinition)).selectByVisibleText(optionValue);
    }

    public void move2TargetElement(WebElement element){
        Actions actions = new Actions(Common.driver);
        actions.scrollToElement(element);
    }
}
