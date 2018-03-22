/**
 * 
 */
package com.test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

/**
 * @author Moksh
 *
 */
public class RegistrationSuite implements Module
{
   
   public WebDriver driver = null;
   
   public final String registerLink = "https://infra-pre.buergerserviceportal.de/bayern/freistaat/register";
   
   public void init()
   {
      driver = BayernPortalMain.driver;
      driver.get(registerLink);
      openRegisterPage(driver);
   }
   
   public void openRegisterPage(WebDriver driver) {
      try
      {
         Util.scrollWindow(driver);
         WebElement registerForm = driver.findElement(By.className("RegisterPage"));
         registerForm.findElement(By.xpath("form/input[2]")).click();
         Thread.sleep(1000);
         
         
         driver.findElement(By.id("registersubmitID")).click();
         
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }
   
   public void fillRegisterInfoAndSubmit(RegistrationEntity data) {
      try
      {
         WebElement registerForm = Util.fluentWait(By.className("RegisterPage"), driver, 30, 5);
         List<WebElement> checkboxList = registerForm.findElement(By.className("RadioInputContainer")).findElements(By.tagName("input"));
         if("male".equals(data.getGender()))
            checkboxList.get(0).click();
         else 
            checkboxList.get(1).click();
         
         //for Familienname
         WebElement e1 = registerForm.findElement(By.xpath("form/input[2]"));
         e1.clear();
         e1.sendKeys(data.getFamilienname());
         
         //for Vorname
         WebElement e2 = registerForm.findElement(By.xpath("form/input[3]"));
         e2.clear();
         e2.sendKeys(data.getVorname());
         
         //for Geburtsname
         WebElement e3 = registerForm.findElement(By.xpath("form/input[4]"));
         e3.clear();
         e3.sendKeys(data.getGeburtsname());
         
         //for Geburtsdatum
         WebElement e4 = registerForm.findElement(By.xpath("form/input[5]"));
         e4.clear();
         e4.sendKeys(data.getGeburtsdatum());
         
         //for Geburtsort
         WebElement e5 = registerForm.findElement(By.xpath("form/input[6]"));
         e5.clear();
         e5.sendKeys(data.getGeburtsort());
         
         //for E-Mail-Adresse
         WebElement e6 = registerForm.findElement(By.xpath("form/input[7]"));
         e6.clear();
         e6.sendKeys(data.getEmail());
         
         //for E-Mail-Adresse wiederholen
         WebElement e7 = registerForm.findElement(By.xpath("form/input[8]"));
         e7.clear();
         e7.sendKeys(data.getEmailCopy());
         
         // Sleep for entering captcha
         Thread.sleep(10000);
         
         WebElement footer = driver.findElement(By.className("PortletFooter"));
         
         Util.scrollWindow(driver);
         footer.findElements(By.tagName("input")).get(0).click();
         
         try {
            WebElement registerFormTmp = Util.fluentWait(By.className("RegisterPage"), driver, 30, 5);
            String error = printErrorLog(registerFormTmp);
            if(!"".equals(error)) {
               System.out.println("Error Message on Web Page: "+error);
               noCaptchaErrorMsgHandler(registerFormTmp);
               Assert.assertTrue(true);
            } else {
                 // No Error Message
            }
         } catch(NoSuchElementException e) {
            throw e;
         } catch(Exception e) {
            throw e;
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }
   
   private String printErrorLog(WebElement registerForm) {
      List<WebElement> errorLabels = registerForm.findElements(By.className("rich-message-label"));
      for(WebElement errorEl: errorLabels) {
         String errorMsg = errorEl.getText();
         if(!"".equals(errorMsg)) {
            return errorMsg;
         }
      }
      return "";
   }
   
   private void noCaptchaErrorMsgHandler(WebElement registerForm) {
      List<WebElement> errorLabels = registerForm.findElements(By.className("ErrorMessageBlock"));
      for(WebElement errorEl: errorLabels) {
         String errorMsg = errorEl.getText();
         if(errorMsg != null && errorMsg.contains("Bild ein")) {
            System.out.println("Please enter Captcha as well.");
         }
      }
   }

}
