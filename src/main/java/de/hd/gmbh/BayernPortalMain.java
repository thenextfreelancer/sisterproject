package de.hd.gmbh;

import java.util.Scanner;
/**
 * @author Liridon
 */
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.Util;
import com.test.gmail.GmailLogin;

public class BayernPortalMain
{
   public static WebDriver driver;
   
   public static String chromeIdentifier = "--chrome=";
   
   public static String mozIdentifier = "--firefox=";
   
   @BeforeClass(alwaysRun = true)
   public void setUp() throws Exception
   {
      System.out.println("Please choose browser type:");
      System.out.println("1 - Chrome");
      System.out.println("2 - Firefox");
      System.out.println("3 - Safari");
      Util.giveSpaceInLogs(2);
      System.out.print("Please input option:");
      Scanner browserTypeScan = new Scanner(System.in);
      String browserType= browserTypeScan.nextLine();
      
      switch(browserType) {
      case "1":
         System.out.println("App will run on Chrome.");
         System.setProperty("webdriver.chrome.driver", "lib//chromedriver.exe");
         driver = new ChromeDriver();
         break;
      case "2":
         System.out.println("App will run on Firefox.");
         System.setProperty("webdriver.gecko.driver", "lib//geckodriver.exe");
         driver = new FirefoxDriver();
         break;
      default:
         System.out.println("This option is not supported. System will exit.");
         throw new Exception("Invalid input.");
      }
      
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      driver.manage().window().maximize();
   }
   
   public static void main(String args[]) throws Exception {
      
      if(args.length == 0) {
         System.out.println("Error: Driver type is not defined.");
         System.out.println("The command must have the driver type e.g. --chrome=/path/to/chromedriver  or --firefox=/path/to/geckodriver");
         Util.giveSpaceInLogs(2);
         System.out.println("System will exit.");
         throw new Exception("Error: Driver type is not defined.");
      }
      
      if(args.length > 1) {
         System.out.println("Error: Command not found.");
         System.out.println("The command must have the driver type e.g. --chrome=/path/to/chromedriver  or --firefox=/path/to/geckodriver");
         Util.giveSpaceInLogs(2);
         System.out.println("System will exit.");
         throw new Exception("Error: Command not found.");
      }
      
      if(args[0].contains(chromeIdentifier)) {
         System.setProperty("webdriver.chrome.driver", args[0].replace(chromeIdentifier, ""));
         driver = new ChromeDriver();
      } else if(args[0].contains(mozIdentifier)) {
         System.setProperty("webdriver.gecko.driver", args[0].replace(mozIdentifier, ""));
         driver = new FirefoxDriver();
      } else {
         System.out.println("Error: Driver type is not defined. System will exit.");
         throw new Exception("Error: Driver type is not defined.");
      }
      
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      driver.manage().window().maximize();
      register();
   }

   @Test
   public static void register()
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
      
      GmailLogin gmail = new GmailLogin();
      gmail.setUp();
      gmail.login();
   }
   
   @AfterClass(alwaysRun = true)
   public void tearDown()
   {
//      driver.quit();
   }

}