package com.selenium.datafromexcel.modules.account;

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

public class AccountCreation extends AccountTestSuiteBase{
	
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
		if(!TestUtil.isTestCaseRunnable(suite_create_account_xls, this.getClass().getSimpleName()))
		{
			APP_LOGS.info(this.getClass().getSimpleName() + " is set to NO . So Skiipping the Test Case");
			throw new SkipException(this.getClass().getSimpleName() + " is set to NO . So Skiipping the Test Case");
		}
		
		runmodes=TestUtil.getDataSetRunmodes(suite_create_account_xls, this.getClass().getSimpleName());
		// runmodes[0] = Y , runmodes[1] = N , runmodes[2] = N
		
		openBrowser();		
		Utilities.driver = Base.driver;	
		
	}
	
	@AfterTest
	public void releaseResources()
	{
		closeBrowser();
	}
	
	@BeforeMethod
	public void beforeMethod()
	{
		expctedText = "do you already have a Target.com account?";
		accountExpectedText = "account info 123";
		
	    actualText =  getElementText(getORValue("actualText"));
		
		if(expctedText.equals(actualText))
			System.out.println("TEXT MATCH ..");
		else
			System.out.println("TEXT UNMATCH");
	
		driver.findElement(By.cssSelector(getORValue("Button_No"))).click();
		
		accountActualText =  getElementText(getORValue("accountActualText"));
		
	}

	
	
	@Test(dataProvider="getAccountData")
	public void createAccount(String firstName , String lastName , String mail , String password){
		
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			System.out.println(runmodes[count]);
			
			throw new SkipException("Runmode for test set data set to no "+count);
		}
		
		APP_LOGS.debug("count " +count);
		
		accountFirstName = firstName;
		
		APP_LOGS.debug("Executing createAccountValidCredentials");
		APP_LOGS.debug(firstName +" -- "+lastName +" -- "+mail +" -- "+password);
		
		
		
		// Assert.assertEquals(accountExpectedText, accountActualText);// Program terminates on Failure
	
		writeInput(getORValue("firstname") , firstName); // Address, Data
		writeInput(getORValue("lastname") , lastName);
		writeInput(getORValue("emailId") , mail);
		writeInput(getORValue("password") , password);
		
		driver.findElement(By.xpath("//button[contains(text(),'next')]")).click();	
		
		pause(15);	
		
			
		
	}
	
	@AfterMethod
     public void runAfterMethod()  {
		
	//	navigateToURL();
		driver.findElement(By.cssSelector("a[title=accountFirstName]")).click();
		
		driver.findElement(By.id("signOut")).click();
		pause(10);
		navigateToURL();
		pause(10);
		
	}
	
	
	@DataProvider
	public Object[][] getAccountData()
	{
		return TestUtil.getData(suite_create_account_xls, this.getClass().getSimpleName());
	}
	
	
	

}
