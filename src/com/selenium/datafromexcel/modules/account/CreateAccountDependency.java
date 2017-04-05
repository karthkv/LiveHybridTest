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

public class CreateAccountDependency extends AccountTestSuiteBase {
	
	static Utilities obj = null;
	CreateAccountDependency obj1 = null;
	
	String expctedText = null;
	String accountExpectedText = null;
	String actualText = null;
	String accountActualText = null;
	
	String runmodes[]=null;
	static int count=-1;
	
	
	@BeforeTest
	public void testCaseCheck() {
		System.out.println("@BeforeTest");
		
		
		if(!TestUtil.isTestCaseRunnable(suite_create_account_xls,this.getClass().getSimpleName())){
			APP_LOGS.debug("Skipping Test Case  "+this.getClass().getSimpleName()+" as runmode set to NO");//logs
			throw new SkipException("Skipping Test Case  "+this.getClass().getSimpleName()+" as runmode set to NO");//reports
		}
		
		runmodes=TestUtil.getDataSetRunmodes(suite_create_account_xls, this.getClass().getSimpleName());
		
	//Check the Tets Case Run

		openBrowser();		
		Utilities.driver = Base.driver;
	}

	@AfterTest
	public void releaseResources() {
		System.out.println("@AfterTest");
		closeBrowser();
			
	}

	@BeforeMethod
	public void runBeforeMethod() {
		System.out.println("@BeforeMethod");
		
		
	}

	@AfterMethod
	public void runAfterMethod()  {
		
		System.out.println("@AfterMethod");
		
	}
	
	@Test
	public void prepareAccountCreation()
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

	@Test(dataProvider="getAccountCreateData" ,dependsOnMethods = "prepareAccountCreation", alwaysRun = true)
	public void createAccountValidCredentials(String firstName,String lastName,String eMail,String password) {
		
		 count++;
			if(!runmodes[count].equalsIgnoreCase("Y")){
				throw new SkipException("Runmode for test set data set to no "+count);
			}
		
		APP_LOGS.debug("Executing createAccountValidCredentials");
		APP_LOGS.debug(firstName +" -- "+lastName +" -- "+eMail +" -- "+password);
		
		
		
		// Assert.assertEquals(accountExpectedText, accountActualText);// Program terminates on Failure
	
		writeInput(getORValue("firstname") , firstName); // Address, Data
		writeInput(getORValue("lastname") , lastName);
		writeInput(getORValue("emailId") , eMail);
		writeInput(getORValue("password") , password);
		
		//driver.findElement(By.cssSelector("button[value='next']")).click();	
		
	}

	@DataProvider
	public Object[][] getAccountCreateData(){
		return TestUtil.getData(suite_create_account_xls, this.getClass().getSimpleName()) ;
	}

}
