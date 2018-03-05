package com.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BayernPortalMain
{
   public static WebDriver driver;

   @BeforeClass(alwaysRun = true)
   public void setUp() throws Exception
   {
      System.setProperty("webdriver.chrome.driver", "lib//chromedriver.exe");
      driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      driver.manage().window().maximize();
   }

   @Test
   public void register()
   {
      RegistrationSuite suite = new RegistrationSuite();
      suite.init();
      suite.fillRegisterInfoAndSubmit(AppData.get("data1"));
      suite.fillRegisterInfoAndSubmit(AppData.get("data2"));
      suite.fillRegisterInfoAndSubmit(AppData.get("data3"));
      suite.fillRegisterInfoAndSubmit(AppData.get("data4"));
      suite.fillRegisterInfoAndSubmit(AppData.get("data5"));
      suite.fillRegisterInfoAndSubmit(AppData.get("data6"));
      suite.fillRegisterInfoAndSubmit(AppData.get("data7"));
      suite.fillRegisterInfoAndSubmit(AppData.get("data8")); // real data
      
      
   }
   
   @AfterClass(alwaysRun = true)
   public void tearDown()
   {
//      driver.quit();
   }

}