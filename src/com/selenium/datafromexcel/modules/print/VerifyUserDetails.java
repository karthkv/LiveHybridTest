package com.selenium.datafromexcel.modules.print;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.selenium.datafromexcel.base.Base;
import com.selenium.datafromexcel.utilities.TestUtil;
import com.selenium.datafromexcel.utilities.Utilities;

public class VerifyUserDetails extends PrintTestSuiteBase{
	
	String runmodes[] = null;
	static int count = -1;
	
	String expctedText = null;
	String accountExpectedText = null;
	String actualText = null;
	String accountActualText = null;
	
	String accountFirstName = null;
	
	
	@BeforeTest // Ensuring the RunMode of the Test case
	public void testRunCheckup()
	{
		if(!TestUtil.isTestCaseRunnable(suite_print_xls, this.getClass().getSimpleName()))
		{
			APP_LOGS.info(this.getClass().getSimpleName() + " is set to NO . So Skiipping the Test Case");
			throw new SkipException(this.getClass().getSimpleName() + " is set to NO . So Skiipping the Test Case");
		}
		
		runmodes=TestUtil.getDataSetRunmodes(suite_print_xls, this.getClass().getSimpleName());
		// runmodes[0] = Y , runmodes[1] = N , runmodes[2] = N
		
		openBrowser();		
		Utilities.driver = Base.driver;	
		
	}
	
	@AfterTest
	public void releaseResources()
	{
		closeBrowser();
	}
	
	@Test(dataProvider="getUserData")
	public void performSign(String email, String password)
	{
		
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			System.out.println(runmodes[count]);
			
			throw new SkipException("Runmode for test set data set to no "+count);
		}
		
		writeInput(getORValue("emailId"), email);
		writeInput(getORValue("password"), password);
		
		driver.findElement(By.cssSelector(getORValue("signin"))).click();
		pause(10);
		
	}
	
	@Test
	public void createAccount(){
		
		driver.findElement(By.id(getORValue("print"))).click();
		
		driver.findElement(By.xpath(getORValue("selectoption"))).click();
		
		driver.findElement(By.id(getORValue("submit"))).click();
		
	}
	
	@AfterMethod
     public void runAfterMethod()  {
		
	//	navigateToURL();
		
		
	}
	
	
	@DataProvider
	public Object[][] getUserData()
	{
		return TestUtil.getData(suite_print_xls, this.getClass().getSimpleName());
	}
	
	
	

}
