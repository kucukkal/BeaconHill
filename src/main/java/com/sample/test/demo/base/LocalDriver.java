package com.sample.test.demo.base;
import org.openqa.selenium.WebDriver;
import com.sample.test.demo.configuration.Configuration;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

public class LocalDriver {
    //Creates the driver for each Thread
    private Configuration config;
    private LocalDriver(){}

    private static LocalDriver instance = new LocalDriver();

    public static LocalDriver getInstance()
    {
        return instance;
    }
    ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>(){
        @Override
        protected WebDriver initialValue()
        {
            config = new Configuration();
            if (config.getBrowser().equalsIgnoreCase("chrome")) {
                if (config.getPlatform().equalsIgnoreCase("mac")) {
                    System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver/mac/chromedriver");
                } else {
                    System.setProperty("webdriver.chrome.driver",
                            "src/test/resources/chromedriver/windows/chromedriver.exe");
                }
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--Start-maximized");
                chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
                chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                return new ChromeDriver(chromeOptions); // can be replaced with other browser drivers
            }
            else {
                Assert.fail("Unsupported browser " + config.getBrowser());
                return null;
            }
        }

    };

    public WebDriver getDriver()
    {
        return driver.get();
    }
    public void removeDriver()
    {
        driver.get().quit();
        driver.remove();
    }
}

