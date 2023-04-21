package org.test.week_01;

import org.core.Common;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KeepItEasyIITest {

    public static final String TEST_URL = "http://advantageonlineshopping.com/";
    public static final String BROWSER = "edge";


    // Test Elements
    private static final String CONTACT_US_MENU = "LINK TEXT:CONTACT US";
    private static final String CONTACT_US_MAIN_SELECT = "NAME:categoryListboxContactUs";
    private static final String CONTACT_US_SUB_SELECT = "NAME:productListboxContactUs";
    Common letsAutomate  = new Common(BROWSER, true);

    @BeforeEach
    void setup() {
        letsAutomate.launchSite(TEST_URL);
    }

    @Test
    void test() throws InterruptedException {
        letsAutomate.doClick(CONTACT_US_MENU);
        letsAutomate.selectDropdownOption(CONTACT_US_MAIN_SELECT, "Mice");
        letsAutomate.selectDropdownOption(CONTACT_US_SUB_SELECT, "Kensington Orbit 72337 Trackball with Scroll Ring");
    }

    @AfterEach
    void tearDown() throws InterruptedException {
        letsAutomate.wasteSometime(2);
    }
}
