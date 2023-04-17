package org.test.week_01;
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
    private static final String MENU_USER_ICON_LOCATOR = "ID:menuUser";
    private static final String XPATH_USERNAME_INPUT_SING_IN_LOCATOR = "//*/input[@name='username']";
    private static final String XPATH_PASSWORD_INPUT_SING_IN_LOCATOR = "//*/input[@name='password']";
    private static final String CREATE_NEW_ACCOUNT_LINK_SING_IN_LOCATOR = "XPATH://*/a[contains(., 'CREATE NEW ACCOUNT')]";
    private static final String CSS_USERNAME_INPUT_CREATE_ACCOUNT_LOCATOR = "input[name='usernameRegisterPage']";
    private static final String EMAIL_INPUT_CREATE_ACCOUNT_LOCATOR = "CSS:input[name='emailRegisterPage']";
    private static final String XPATH_PASSWORD_INPUT_CREATE_ACCOUNT_LOCATOR = "//*/input[@name='passwordRegisterPage']";
    private static final String XPATH_CONFIRM_PASSWORD_INPUT_CREATE_ACCOUNT_LOCATOR = "//*/input[@name='confirm_passwordRegisterPage']";
    private static final String CSS_I_AGREE_CONDITIONS_CREATE_ACCOUNT_LOCATOR = "input[name='i_agree']";
    private static final String CSS_REGISTER_BUTTON_CREATE_ACCOUNT_LOCATOR = "#register_btnundefined";

    private static final String CSS_ALREADY_REGISTERED_MSG_CREATE_ACCOUNT_LOCATOR = "CSS:label[class='invalid center block smollMargin']";
    private static final String CSS_ALREADY_REGISTERED_HIDDEN_MSG_CREATE_ACCOUNT_LOCATOR = "label[class='center block smollMargin']";


    WebDriver driver;
    Core letsAutomate = new Core();
    @BeforeEach
    void setup() {
        driver = letsAutomate.launchSite(DEFAULT_BROWSER, BASE_URL);
    }

    @Test
    void test() throws InterruptedException {
        letsAutomate.doClick(MENU_USER_ICON_LOCATOR);
        letsAutomate.doClick(CREATE_NEW_ACCOUNT_LINK_SING_IN_LOCATOR);

        letsAutomate.fillInputText(driver, "CSS", CSS_USERNAME_INPUT_CREATE_ACCOUNT_LOCATOR, TEST_USER_NAME);

        letsAutomate.fillInputText(EMAIL_INPUT_CREATE_ACCOUNT_LOCATOR, TEST_EMAIL);

        letsAutomate.fillInputText(driver, "xpath", XPATH_PASSWORD_INPUT_CREATE_ACCOUNT_LOCATOR, GENERIC_PASSWORD);
        letsAutomate.fillInputText(driver, "xpath", XPATH_CONFIRM_PASSWORD_INPUT_CREATE_ACCOUNT_LOCATOR, GENERIC_PASSWORD);
        letsAutomate.doClick("CSS", CSS_I_AGREE_CONDITIONS_CREATE_ACCOUNT_LOCATOR);
        letsAutomate.doClick("CSS", CSS_REGISTER_BUTTON_CREATE_ACCOUNT_LOCATOR);
        letsAutomate.wasteSomeTime(1);
        System.out.println(letsAutomate.getTextFromElement( CSS_ALREADY_REGISTERED_MSG_CREATE_ACCOUNT_LOCATOR));

    }
}
