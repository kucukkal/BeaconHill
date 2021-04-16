package com.sample.test.demo.pages;
import com.aventstack.extentreports.Status;
import com.sample.test.demo.base.TestBasePage;
import com.sample.test.demo.base.LocalDriver;
import com.sample.test.demo.constants.CustomWarnings;
import com.sample.test.demo.utils.Helper;
import org.apache.tools.ant.util.LinkedHashtable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.testng.Assert;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultPage extends TestBasePage {
        WebDriver driver;


    public ResultPage(WebDriver driver) {
            this.driver = LocalDriver.getInstance().getDriver();
            PageFactory.initElements(LocalDriver.getInstance().getDriver(), this);
        }
        CustomWarnings customValues=new CustomWarnings();
        @FindBy(how = How.CSS, using = "h1.srp-header")
        public WebElement header;
        @FindBy(how=How.XPATH, using = "//li[contains(@class,'breadcrumb')]/div/following-sibling::label")
        public List<WebElement> searchParameters;
        @FindBy(how = How.XPATH, using = "//input[@id='stkTypId-28880']/following-sibling::label")
        public WebElement newradiobutton;
        @FindBy(how = How.XPATH, using = "//div[@class='select']/select[@class='select-sort-options']")
        public WebElement sortby;
        @FindBy(how = How.XPATH, using = "//li[@class='breadcrumb facet' and contains(@data-value,'28880')]/div/following-sibling::label")
        public List<WebElement> newparameter;
        @FindBy(how = How.XPATH, using = "//li[@class='breadcrumb facet' and contains(@data-value,'28881')]/div/following-sibling::label")
        public List<WebElement> usedparameter;
        @FindBy(how = How.XPATH, using="//ul[@class='refinements']/li/input[contains(@id,'trId-')]")
        public List<WebElement> trimOptions;
        @FindBy(how = How.XPATH, using="//li[@class='breadcrumb facet' and @data-dimension-id='trId']/label")
        public List<WebElement> searchParameterWithTrimId;
        @FindBy(how = How.CSS, using = "li#trId")
        public WebElement trimHeader;
        @FindBy(how = How.XPATH, using="//div[contains(@class,'shop-srp-listings') and contains(@class,'listing-container')]/a")
        public List<WebElement> vehicleList;
        @FindBy(how = How.NAME, using="submit")
        public WebElement checkAvailabilityButton;
        @FindBy(how = How.NAME, using="fname")
        public WebElement firstName;
        @FindBy(how = How.NAME, using="lname")
        public WebElement lastName;
        @FindBy(how = How.NAME, using="email")
        public WebElement emailbox;
        @FindBy(how = How.CSS, using="a.cui-button")
        public WebElement contactSeller;
        @FindBy(how = How.XPATH, using="//h2[contains(@class,'page-section') and contains(@class,'sub cui-heading-2 calculator-title')]")
        public WebElement paymentCalculator;
        Helper helper = new Helper();


        public List<String> searchParams(){
            //Get the text of Webelements for the search parameters in the result page and returns as a list
            List<String> list=searchParameters.stream().map(x->x.getText().trim()).collect(Collectors.toList());
            List<String>  newlist=list.stream().filter(x->!x.isEmpty()).collect(Collectors.toList());
            return newlist;
        }
        public void verifyTheSearchParameters(List<String> expectedResults){
            //Verifies the search parameters are displayed in the ResultPage
            List<String> newlist=searchParams();
            Assert.assertTrue(newlist.retainAll(expectedResults));
        }
        public void checkForNewandOldValuesInParam(String previousValue, String currentValue) throws InterruptedException {
           //Verifies if the "New" option is displayed and "Used" option is not displayed in the Result page
            helper.waitForElementToBeDisplayed(driver,newparameter.get(0),5);
            if(previousValue!=null) {
                List<String> previousParameterList=usedparameter.stream().map(x->x.getText().trim()).collect(Collectors.toList());
                Assert.assertFalse(previousParameterList.contains(previousValue), previousValue + " still presents in search parameters");
            }
              if(currentValue!=null) {
                  List<String> currentParameterList=newparameter.stream().map(x->x.getText().trim()).collect(Collectors.toList());
                  Assert.assertTrue(currentParameterList.contains(currentValue), currentValue + " is missing in the search parameters");
              }

        }
        public void chooseTrimOption(String text){
            //Scrolls down to Trim options and clicks the trim option given as a string (text)
            helper.jsExecutor(driver,"scroll",trimHeader);
            for(WebElement element:trimOptions)
            {
                if(element.getAttribute("data-dtm").contains(text))
                {
                    helper.jsExecutor(driver,"scroll",element);
                    helper.jsExecutor(driver,"click",element);
                }
            }
        }
           public void verifyTrimChoice(String text) {
            //Verifies the chose trip option is displayed in the ResultPage as Search Parameter
            List<String> list=searchParameterWithTrimId.stream().map(x-> x.getText().trim()).collect(Collectors.toList());
            List<String> listWithNoNullElement=list.stream().filter(x->!x.isEmpty()).collect(Collectors.toList());;
            Assert.assertTrue(listWithNoNullElement.contains(text));
        }
        public void selectNthVehicle(int n){
            //Selects the Nth car in the search results shown in ResultPage
            vehicleList.get(n-1).click();
        }
        public void verifyForDetailsOfTheNthCar(){
            //Verifies the details of the Nth car
            helper. waitForUrlToContain(driver,"overview",5);
            Assert.assertTrue(driver.getTitle().contains(customValues.hondaPilotForSale));
            Assert.assertTrue(checkAvailabilityButton.isDisplayed());
        }
        public void enterValueToFirstName(String FirstName)
        {
            //Enters the first name
            firstName.clear();
            firstName.sendKeys(FirstName);
            test.get().log(Status.INFO,FirstName+" has been entered as the First Name.");
        }
        public void enterValueToLastName(String LastName)
        {
            //Enters the last name
            lastName.clear();
            lastName.sendKeys(LastName);
            test.get().log(Status.INFO,LastName+" has been entered as the Last Name.");
        }
        public void enterValueToEmail(String Email)
        {
            //Enters the email
            emailbox.clear();
            emailbox.sendKeys(Email);
            test.get().log(Status.INFO,Email+" has been entered as the Email.");
        }
        public void enterTheCustomerDetails(String FirstName, String LastName, String Email){
           //Entering first name, last name and email
            enterValueToFirstName(FirstName);
            enterValueToLastName(LastName);
            enterValueToEmail(Email);
        }
        public void getScreenshotAfterScrollingDown()
        {
            //scrolls down to contact Seller button (to get Payment Calculator in the screen) and gets screenshot
            helper.jsExecutor(driver,"scroll",contactSeller);
            takeScreenShot();
        }

}
