package com.sample.test.demo.pages;
import com.aventstack.extentreports.Status;
import com.sample.test.demo.base.TestBasePage;
import com.sample.test.demo.base.LocalDriver;
import com.sample.test.demo.constants.CustomWarnings;
import com.sample.test.demo.utils.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class HomePage extends TestBasePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = LocalDriver.getInstance().getDriver();
        PageFactory.initElements(LocalDriver.getInstance().getDriver(), this);
    }

    Helper helper = new Helper();
    CustomWarnings warnings = new CustomWarnings();

    @FindBy(how = How.NAME, using = "stockType")
    public WebElement stockType;
    @FindBy(how = How.NAME, using = "makeId")
    public WebElement makeid;
    @FindBy(how = How.NAME, using = "modelId")
    public WebElement modelid;
    @FindBy(how = How.NAME, using = "radius")
    public WebElement radius;
    @FindBy(how = How.NAME, using = "priceMax")
    public WebElement pricemax;
    @FindBy(how = How.NAME, using = "zip")
    public WebElement zip;
    @FindBy(how = How.XPATH, using = "//input[@type='submit']")
    public WebElement search;

    public ResultPage SelectTheCar(List<String> searchParameters, String zipcode) {
        //Selects the car based on the Search Parameters and click search button to go to Result page
        helper.selectFromDropdownMenuByText(searchParameters.get(0),stockType);
        test.get().log(Status.INFO,searchParameters.get(0)+" is selected as the condition.");
        helper.selectFromDropdownMenuByText(searchParameters.get(1),makeid);
        test.get().log(Status.INFO,searchParameters.get(1)+"  is selected as the make.");
        helper.selectFromDropdownMenuByText(searchParameters.get(2),modelid);
        test.get().log(Status.INFO,searchParameters.get(2)+"  is selected as the model.");
        helper.selectFromDropdownMenuByValue(searchParameters.get(3),pricemax);
        test.get().log(Status.INFO,"$"+searchParameters.get(3)+"  is selected as the maximum price.");
        helper.selectFromDropdownMenuByValue(searchParameters.get(4),radius);
        test.get().log(Status.INFO,searchParameters.get(4)+" miles is selected as the radius.");
        zip.clear();
        zip.sendKeys(zipcode);
        test.get().log(Status.INFO,zipcode +"  is entered as the zipcode.");
        search.click();
        test.get().log(Status.INFO,"Search button is clicked.");
        return new ResultPage(driver);
    }
}












