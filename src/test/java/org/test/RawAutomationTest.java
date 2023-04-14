package org.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class RawAutomationTest {
    WebDriver driver;
    @BeforeEach
    void setup() {
        driver = WebDriverManager.chromedriver().create();
    }
    @AfterEach
    void teardown() {
        driver.quit();
    }
    @Test
    void test(){
        driver.get("https://duckduckgo.com/");
        driver.manage().window().maximize();
    }
}
