package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.Browser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);
    protected static final ApplicationManager app =
            new ApplicationManager(System.getProperty("browser", Browser.CHROME.browserName()));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws IOException {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

    @BeforeMethod
    public void logTestStart(Method method, Object[] parameters) {
        logger.info("Start test " + method.getName() + " with parameters " + Arrays.asList(parameters));
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method method, Object[] parameters) {
        logger.info("Stop test " + method.getName() + " with parameters " + Arrays.asList(parameters));
    }

}
