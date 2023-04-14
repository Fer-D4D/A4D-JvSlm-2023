package org.test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.core.Core;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class BasicFrameWorkTest {

    //test data
    private static final String BASE_URL = "http://advantageonlineshopping.com/";
    private static final String DEFAULT_BROWSER = "Edge";
    private static final String TEST_USER_NAME = "A4DUser-FER";
    private static final String TEST_EMAIL = "fer@a4d.tv";
    private static final String GENERIC_PASSWORD = "aaBB22";

    //Test Web Elements
    private static final String CSS_MENU_USER_ICON_LOCATOR = "#menuUser";
    private static final String XPATH_USERNAME_INPUT_SING_IN_LOCATOR = "//*/input[@name='username']";
    private static final String XPATH_PASSWORD_INPUT_SING_IN_LOCATOR = "//*/input[@name='password']";
    private static final String XPATH_CREATE_NEW_ACCOUNT_LINK_SING_IN_LOCATOR = "//*/a[contains(., 'CREATE NEW ACCOUNT')]";
    private static final String CSS_USERNAME_INPUT_CREATE_ACCOUNT_LOCATOR = "input[name='usernameRegisterPage']";
    private static final String CSS_EMAIL_INPUT_CREATE_ACCOUNT_LOCATOR = "input[name='emailRegisterPage']";
    private static final String XPATH_PASSWORD_INPUT_CREATE_ACCOUNT_LOCATOR = "//*/input[@name='passwordRegisterPage']";
    private static final String XPATH_CONFIRM_PASSWORD_INPUT_CREATE_ACCOUNT_LOCATOR = "//*/input[@name='confirm_passwordRegisterPage']";
    private static final String CSS_I_AGREE_CONDITIONS_CREATE_ACCOUNT_LOCATOR = "input[name='i_agree']";
    private static final String CSS_REGISTER_BUTTON_CREATE_ACCOUNT_LOCATOR = "#register_btnundefined";

    private static final String CSS_ALREADY_REGISTERED_MSG_CREATE_ACCOUNT_LOCATOR = "label[class='center block smollMargin invalid']";
    private static final String CSS_ALREADY_REGISTERED_HIDDEN_MSG_CREATE_ACCOUNT_LOCATOR = "label[class='center block smollMargin']";


    WebDriver driver;
    Core core = new Core();
    @BeforeEach
    void setup() {
        driver = core.launchSite(DEFAULT_BROWSER, BASE_URL);
    }

    @Test
    void test() throws InterruptedException {
        core.doClick("CSS", CSS_MENU_USER_ICON_LOCATOR);
        core.doClick("xpath", XPATH_CREATE_NEW_ACCOUNT_LINK_SING_IN_LOCATOR);
        core.fillInputText(driver, "CSS", CSS_USERNAME_INPUT_CREATE_ACCOUNT_LOCATOR, TEST_USER_NAME);
        core.fillInputText(driver, "CSS", CSS_EMAIL_INPUT_CREATE_ACCOUNT_LOCATOR, TEST_EMAIL);
        core.fillInputText(driver, "xpath", XPATH_PASSWORD_INPUT_CREATE_ACCOUNT_LOCATOR, GENERIC_PASSWORD);
        core.fillInputText(driver, "xpath", XPATH_CONFIRM_PASSWORD_INPUT_CREATE_ACCOUNT_LOCATOR, GENERIC_PASSWORD);
        core.doClick("CSS", CSS_I_AGREE_CONDITIONS_CREATE_ACCOUNT_LOCATOR);
        core.doClick("CSS", CSS_REGISTER_BUTTON_CREATE_ACCOUNT_LOCATOR);
        core.wasteSomeTime(2);
        core.doClick("CSS", CSS_REGISTER_BUTTON_CREATE_ACCOUNT_LOCATOR);
        core.wasteSomeTime(.5);
        System.out.println(core.getTextFromElement("CSS", CSS_ALREADY_REGISTERED_MSG_CREATE_ACCOUNT_LOCATOR));

    }
}
