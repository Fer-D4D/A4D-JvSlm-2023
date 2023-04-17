package org.test.week_01;
import org.core.Core;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KeepItEasy {

    //test data
    private static final String BASE_URL = "https://duckduckgo.com/";
    private static final String DEFAULT_BROWSER = "Edge";
    private static final String TEXT_2_SEARCH = "Peaches, peaches";
    // Test Elements
    private static final String XPATH_SEARCH_INPUT_TEXT = "XPATH://input[@id='search_form_input_homepage']";
    private static final String SEARCH_INPUT_TEXT = "CSS:#search_form_input_homepage";
    private static final String SEARCH_BUTTON = "ID:search_button_homepage";
    private static final String XPATH_SEARCH_BUTTON = "XPATH://input[@id='search_button_homepage']";
    private static final String MENU_BUTTON = "CSS:.header__button--menu.js-side-menu-open";
    private static final String MENU_SECTION_HEADERS = "XPATH:";
    private static final String FIRST_RESULT_TITTLE_SPAN = "XPATH://div[@id='links']/child::div[1]//child::div[2]//span";
    private static final String FIRST_RESULT_SUMMARY_SPAN = "XPATH://div[@id='links']/child::div[1]//child::div[3]//child::span[%$%]";

    Core letsAutomate = new Core();
    @BeforeEach
    void setup() {
        letsAutomate.launchSite(DEFAULT_BROWSER, BASE_URL);
    }

    @Test
    void test() throws InterruptedException {
    letsAutomate.fillInputText(XPATH_SEARCH_INPUT_TEXT, TEXT_2_SEARCH);
    }
}
