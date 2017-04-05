package com.selenium.datafromexcel.modules.print;

import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.selenium.datafromexcel.base.Base;
import com.selenium.datafromexcel.utilities.TestUtil;
import com.selenium.datafromexcel.utilities.Xls_Reader;


public class PrintTestSuiteBase extends Base{
    // check if the suite ex has to be skiped
	@BeforeSuite
	public void checkSuiteSkip() throws Exception{
		
		intilize();
		// We will Verify whether TestSuite is Runnable OR NOT
		
		APP_LOGS.debug("Checking Runmode of Print");
		if(!TestUtil.isSuiteRunnable(suiteXls, "Print")){
			APP_LOGS.debug("Skipped Create Account Suite as the runmode was set to NO");
			throw new SkipException("RUnmode of Print Account Suite set to no. So Skipping all tests in Create Account Suite");
		
		}
	}
	@AfterSuite
	public void releaseResource()
	{
		suite_create_account_xls = null;
		suite_sign_in_xls = null;
		suite_create_registry_xls = null;
		suiteXls = null;
	}

}
