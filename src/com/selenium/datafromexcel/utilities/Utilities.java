package com.selenium.datafromexcel.utilities;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.selenium.datafromexcel.base.Base;

public class Utilities {
	
	public static Utilities utilitiesObj = null;
	public static Properties configProp = null;
	public static WebDriver driver = null;
	
	
	static
	{
		driver = Base.getDriver();
	}
	
	
	
	public static Utilities getUtilitiesInstance()
	{
		
		if(utilitiesObj == null)
		{
			utilitiesObj = new Utilities();
		}
		
		else
		{
			System.out.println("Base Object is already Created .. ..");
		}
		return utilitiesObj;
	}
	
	
	
	public void writeInput(String path , String data)
	{
		try
		{
		driver.findElement(By.id(path)).sendKeys(data);
		}

		catch(NoSuchElementException e)
		{
			System.out.println(path + "is not Valid");
			System.out.println(e.getLocalizedMessage());
			
			File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(file ,new File(Base.getCONFIGValue("screenShotPath") + "//" + path + "_Failure.jpg"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}
	
	public void navigateToURL()
	{
		Utilities.configProp = Base.configProp;
		try
		{
		driver.navigate().to(configProp.getProperty("prodURL"));
		}

		catch(Exception e)
		{
			System.out.println("Unable to Navigate to the URL " +e.getLocalizedMessage());
			
			File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(file ,new File(Base.getCONFIGValue("screenShotPath") + "//" + "_Failure.jpg"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}
	
	
	public String getElementText(String path)
	{
		String text = null;
		try
		{
		text = driver.findElement(By.cssSelector(path)).getText();
		}

		catch(NoSuchElementException e)
		{
			System.out.println(path + "is not Valid");
			System.out.println(e.getLocalizedMessage());
			
			File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(file ,new File(Base.getCONFIGValue("screenShotPath") + "//" + path + "_Failure.jpg"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		return text;
		
	}
	
	

   
   public void closeBrowser(){
	   driver.close();
	   
	   
   }
   
	public void pause(long sec)
	{
		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 


}
