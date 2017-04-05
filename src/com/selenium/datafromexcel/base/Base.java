package com.selenium.datafromexcel.base;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.selenium.datafromexcel.utilities.Utilities;
import com.selenium.datafromexcel.utilities.Xls_Reader;

public class Base extends Utilities {	
	
	public static WebDriver driver;
	public static Base baseObj = null;
	String screenShotPath = System.getProperty("user.dir") +  "//Screenshots//";
	static String userDir = System.getProperty("user.dir");
	
	static protected Properties orProp = null;
	static public Properties configProp = null;
	static protected InputStream isOR = null;
	static  protected InputStream isCONFIG = null;
	
	public static boolean intializationStatus = false;
	public static boolean isBrowserOpened = false;
	
	
	public static Logger APP_LOGS=null;
	public static Properties CONFIG=null;
	public static Properties OR=null;
	
	
	public static Xls_Reader suiteXls=null;
	public static Xls_Reader suite_create_account_xls = null;
	public static Xls_Reader suite_sign_in_xls = null;
	public static Xls_Reader suite_create_registry_xls = null;
	public static Xls_Reader suite_print_xls = null;
	public static Hashtable<String,String> sessionData = new Hashtable<String,String>();
	
	public static final String currentDir = System.getProperty("user.dir"); // Getting the Current Directory
	public static final String chromeDriverPath = currentDir + "//drivers//chrome//chromedriver.exe"; // Getting the Chrome Browser Driver Path
	public static final String ieDriverPath = currentDir + "//drivers//ie//IEDriverServer.exe"; //Getting the IE Browser Driver Path
	
	// Keep intilization stuff
	
	public static WebDriver getDriver()
	{
		System.out.println("getDriver success .." + Base.driver);
		return Base.driver;
	}
	
	public static Base getBaseInstance()
	{
		
		if(baseObj == null)
		{
			baseObj = new Base();
		}
		
		else
		{
			System.out.println("Base Object is already Created .. ..");
		}
		return baseObj;
	}
	
	
	public void intilize()
	{
		if(!intializationStatus) {
			
			APP_LOGS = Logger.getLogger("devpinoyLogger");
		
		System.out.println("intilize");
		orProp  = new Properties();
		configProp = new Properties();
		
		try {
			isOR = new FileInputStream(userDir + "//OR.properties");
			orProp.load(isOR);
			
			System.out.println("orProp :: " +orProp);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			isCONFIG = new FileInputStream(userDir + "//config.properties");
			configProp.load(isCONFIG);
			System.out.println("configProp :: " +configProp);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		APP_LOGS.debug("Loaded Property files successfully");
		APP_LOGS.debug("Loading XLS Files");

		// xls file
		suite_create_account_xls = new Xls_Reader(System.getProperty("user.dir")+"//src//com//selenium//datafromexcel//xls//Create Account.xlsx");
		suite_sign_in_xls = new Xls_Reader(System.getProperty("user.dir")+"//src//com//selenium//datafromexcel//xls//Sign-in.xlsx");
		suite_create_registry_xls = new Xls_Reader(System.getProperty("user.dir")+"//src//com//selenium//datafromexcel//xls//Create Registry.xlsx");
		suite_print_xls = new Xls_Reader(System.getProperty("user.dir")+"//src//com//selenium//datafromexcel//xls//Print.xlsx");
		suiteXls = new Xls_Reader(System.getProperty("user.dir")+"//src//com//selenium//datafromexcel//xls//Suite.xlsx");
		
		APP_LOGS.debug("Loaded XLS Files successfully");
		
		
		intializationStatus = true;
	}
		else
		{
			System.out.println("Intialization is Already Done");
		}
				
		
	}
	
	public void openBrowser(){
	
		
			if(configProp.getProperty("browserType").equals("FIREFOX"))
			{
			driver = new FirefoxDriver();
				
			}
			     
			else if (configProp.getProperty("browserType").equals("CHROME"))
			{
				System.setProperty("webdriver.chrome.driver", chromeDriverPath);
				driver = new ChromeDriver();
				
				
			}
				 
			else if (configProp.getProperty("browserType").equals("IE"))
			{
				System.setProperty("webdriver.ie.driver", ieDriverPath);
				
				DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
				
				caps.setCapability(
				    InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
				    true);
				caps.setCapability(
					    InternetExplorerDriver.IGNORE_ZOOM_SETTING,
					    true);
				
				
				driver = new InternetExplorerDriver(caps);
			
			}
				 
			else
			{
				System.out.println("Please Mention the Proper Browser Type");
			}
			
			driver.manage().window().maximize(); // Maximizing the opened Browser
			
			isBrowserOpened=true;
	
			driver.manage().timeouts().pageLoadTimeout(stringToLong(getCONFIGValue("pageLoadTimeOut")), TimeUnit.SECONDS); // Set the Page Load Timeout
			// Another way of handling  driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.MINUTES);
		 
		 System.out.println(configProp.getProperty("prodURL"));
		 driver.get(configProp.getProperty("prodURL")); // Wait for the MAX 30 Sec for the WebElement Load
		 driver.manage().timeouts().implicitlyWait(stringToLong(getCONFIGValue("implicitTimeout")), TimeUnit.SECONDS); // Setting the MAX Time out for any WebElement
		

}
	
	
	public void releaseResources()
	{
		System.out.println("releaseResources");
		orProp = null;
		configProp = null;
		isOR = null;
		isCONFIG = null;
		
	}
	
	public String getORValue(String key)
	{
		System.out.println("orProp --->  " + orProp);
		
		String value = orProp.getProperty(key);
		return value;
	}
	
	public static String getCONFIGValue(String key)
	{
		System.out.println("configProp ---> " + configProp);
		
		String value = configProp.getProperty(key);
		return value;
	}
	
	   
	   public long stringToLong(String str)
	   {
		  long value =  Long.parseLong(str);
		  return value;
	   }
	

	
}
