/**
 * 
 */
package de.hd.gmbh;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.test.Util;

/**
 * @author Liridon
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
   
   /**
    * An unexpected pop up is coming recently on the website
    * 
    * @throws Exception
    */
   public void removePopup() throws Exception {
      Util.scrollWindow(driver);
      driver.findElement(By.linkText("Registrieren")).click();
      Thread.sleep(1000);
   }
   
   public void openRegisterPage(WebDriver driver) {
      try
      {
         try {
            removePopup();
         } catch(Exception e){
            
         }
         Util.scrollWindow(driver);
         WebElement registerForm = Util.fluentWait(By.className("RegisterPage"), driver, 30, 5); //parent of checkbox
         registerForm.findElement(By.xpath("form/input[2]")).click(); // clicking on checkbox
         Thread.sleep(1000); //wait
         
         WebElement submitForm = driver.findElement(By.className("RegisterSubmit"));
         submitForm.findElement(By.xpath("input[3]")).click(); //clicking Registrieren mit Authega  button
         
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
            registerFormTmp.findElement(By.xpath("form/dl/dt/span"));
            Assert.assertTrue(true);
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

}
