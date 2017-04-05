package com.selenium.datafromexcel.modules.print;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.selenium.datafromexcel.base.Base;
import com.selenium.datafromexcel.utilities.Utilities;

public class SimpleWindowHandling extends Base{
	
	public static void main(String args[])
	{
		new SimpleWindowHandling().windowHandling();
		
		
	}
	public void windowHandling()
	{
		intilize();
		openBrowser();
		Utilities.driver = Base.driver;	
		
		writeInput(getORValue("emailId"), "sudheerprod10@target.com");
		writeInput(getORValue("password"), "Abcd1234");
		
		driver.findElement(By.cssSelector(getORValue("signin"))).click();
		pause(10);
		
        driver.findElement(By.id(getORValue("print"))).click();
		
		driver.findElement(By.xpath(getORValue("selectoption"))).click();
		
		driver.findElement(By.id(getORValue("submit"))).click();// New window will be opened
		
		Set<String> windows = driver.getWindowHandles();
		String firstWindow = null;
		
		while(windows.iterator().hasNext())
		{
			firstWindow = windows.iterator().next();
			break;
		}
      
		for (String window : windows) {
             driver.switchTo().window(window);
        }
		WebElement element = driver.findElement(By.cssSelector(getORValue("readInfo")));
		List<WebElement> list = element.findElements(By.tagName("span"));
		
		String registryType = list.get(0).getText();
		String userName     = list.get(1).getText();
		String collegeName = list.get(2).getText();
		
		System.out.println("registryType : " +registryType);
		System.out.println("userName : " +userName);
		System.out.println("collegeName : " +collegeName);
		
		driver.close();
		driver.switchTo().window(firstWindow);
				
		Set<String> windows1 = driver.getWindowHandles();
		System.out.println(windows1.size());
		
				String welcomeuserName = driver.findElement(By.id("OpenLogedinPopup")).getText();
				System.out.println(welcomeuserName);
		
		
	}

}
