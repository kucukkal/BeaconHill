package com.sample.test.demo.tests;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.sample.test.demo.base.LocalDriver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.sample.test.demo.base.TestBasePage;
import com.sample.test.demo.pages.HomePage;
import com.sample.test.demo.pages.ResultPage;
import com.sample.test.demo.utils.Helper;
import com.sample.test.demo.constants.CustomWarnings;

import javax.xml.transform.Result;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class DemoTest extends TestBasePage {

    Helper helper=new Helper();
    CustomWarnings customValues=new CustomWarnings();
    @Test(groups ={"Smoke"})
    public void SuccefulSearchForHondaPilot8_Passenger()  {

        try {
            WebDriver driver=LocalDriver.getInstance().getDriver();
            HomePage homepage=new HomePage(driver);
            helper.waitForElementToBeDisplayed(driver,homepage.search,5);
            test.get().log(Status.INFO, driver.getCurrentUrl()+" is displayed.");
            List<String> searchParameters=new ArrayList<String>(){{add("Used Cars");add("Honda");add("Pilot");add("50000");add("100");}};
            ResultPage resultpage=homepage.SelectTheCar(searchParameters,"22033");
            helper.waitForElementToBeDisplayed(driver,resultpage.header,10);
            test.get().log(Status.INFO,"Results have been populated.");
            resultpage.verifyTheSearchParameters(searchParameters);
            test.get().log(Status.INFO,"Search parameters have been verified.");
            helper.jsExecutor(driver,"scroll",resultpage.newradiobutton);
            resultpage.newradiobutton.click();
            test.get().log(Status.INFO,"New radio button is clicked");
            helper. waitForUrlToContain(driver,customValues.UrlforNew,5);
            helper.jsExecutor(driver,"scroll",resultpage.sortby);
            resultpage.checkForNewandOldValuesInParam("Used","New");
            test.get().log(Status.INFO,"Search parameters are verified for the presence of New.");
            resultpage.chooseTrimOption("Touring 8-Passenger");
            test.get().log(Status.INFO,"Touring 8-Passenger has been selected as trim option.");
            helper. waitForUrlToContain(driver,"trId=36434822",5);
            resultpage.verifyTrimChoice("Touring 8-Passenger");
            test.get().log(Status.INFO,"Touring 8-Passenger has been verified in the search parameters.");
            resultpage.selectNthVehicle(2);
            test.get().log(Status.INFO,"2nd vehicle has been selected from the list.");
            resultpage.verifyForDetailsOfTheNthCar();
            test.get().log(Status.INFO,"2nd vehicle's details are verified.");
            resultpage.enterTheCustomerDetails("Car","Owner","carowner@yahoo.com");
            helper.jsExecutor(driver,"scroll",resultpage.paymentCalculator);
            test.get().log(Status.INFO,"Scrolled down to Payment Calculator.");
            resultpage.getScreenshotAfterScrollingDown();
            test.get().log(Status.INFO,"Screenshot is taken.");
        } catch (Exception e) {
            Assert.fail("Exception caught" + e);
        }

    }

}
