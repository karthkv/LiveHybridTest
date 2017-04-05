package com.selenium.datafromexcel.modules.iebrowserhandling;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import com.selenium.datafromexcel.base.Base;
import com.selenium.datafromexcel.utilities.Utilities;

public class IEBrowserHandling extends Base{
	
	public static void main(String args[])
	{
		new IEBrowserHandling().ieHandling();
		
		
	}
	public void ieHandling()
	{
		intilize();
		openBrowser();
		Utilities.driver = Base.driver;	
		
      driver.findElement(By.id(getORValue("user_id"))).sendKeys("aaa");
      driver.findElement(By.id(getORValue("password"))).sendKeys("bbb");
        
     //  driver.findElement(By.id(getORValue("sign_in_button"))).click();
        
      //JavascriptExecutor executor = (JavascriptExecutor)driver;
	  //executor.executeScript("arguments[0].click();", WebElement);
        
    JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript( "arguments[0].click();"   , driver.findElement(By.id(getORValue("sign_in_button"))));
        
	}

}
