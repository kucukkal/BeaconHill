package com.sample.test.demo.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.util.Iterator;
import java.util.List;


public class Helper {
    public void waitForElementToBeDisplayed(WebDriver driver,WebElement element, int n)
    {
        //explicit wait for an element to be displayed for n seconds
        WebDriverWait wait= new WebDriverWait(driver, n);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void selectFromDropdownMenuByText(String text, WebElement element){
        //selecting an option from a dropdown menu using value
        Select menu= new Select(element);
        menu.selectByVisibleText(text);

    }
    public void selectFromDropdownMenuByValue(String value, WebElement element){
        //selecting an option from a dropdown menu using value
        Select menu= new Select(element);
        menu.selectByValue(String.valueOf(value));

    }
    public String defaultDropdownMenu(WebElement element){
        //selecting the default option from a dropdown menu using value
        Select menu= new Select(element);
        return menu.getFirstSelectedOption().getText();

    }
    public void waitForUrlToContain(WebDriver driver,String s,int n){
        WebDriverWait wait= new WebDriverWait(driver, n);
        wait.until(ExpectedConditions.urlContains(s));
    }
    public  void jsExecutor(WebDriver driver, String action,WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        String javaScript = "var evObj = document.createEvent('MouseEvents');" +
                "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
                "arguments[0].dispatchEvent(evObj);";
        switch (action) {
            case "hover":
                executor.executeScript(javaScript, element);
                break;
            case "click":
                executor.executeScript("arguments[0].click();", element);
                break;
            case "scroll":
                executor.executeScript("arguments[0].scrollIntoView(true);", element);
                break;
            }
    }
    public List<String> noEmptyElement(List<String> list){
            Iterator<String> iterator=list.iterator();
            while(iterator.hasNext()) {
                String s=iterator.next();
                if(s.isEmpty())
                    iterator.remove();
            }
            return list;
    }




}
