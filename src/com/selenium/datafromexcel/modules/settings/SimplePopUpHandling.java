package com.selenium.datafromexcel.modules.settings;

import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import com.selenium.datafromexcel.base.Base;
import com.selenium.datafromexcel.utilities.Utilities;

public class SimplePopUpHandling extends Base{
	
	public static void main(String args[])
	{
		new SimplePopUpHandling().popupHandling();
		
		
	}
	public void popupHandling()
	{
		intilize();
		openBrowser();
		Utilities.driver = Base.driver;	
		
        driver.findElement(By.xpath(getORValue("Try_it_Button"))).click(); // Click the "Try It" Button
		
        // Alert Handling 
        
		Alert alert = driver.switchTo().alert();		
		String alertText = alert.getText();
		
		System.out.println("Alert Text  " + alertText);
		
		pause(10);
		
		alert.dismiss();
		
		pause(5);
	
		
		
		
	}

}
