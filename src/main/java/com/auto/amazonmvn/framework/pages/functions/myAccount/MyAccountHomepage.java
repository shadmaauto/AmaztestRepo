package com.auto.amazonmvn.framework.pages.functions.myAccount;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.auto.amazonmvn.framework.util.JSUtil;
import com.auto.amazonmvn.framework.util.WebUtil;

public class MyAccountHomepage {
	
	//@FindBy(xpath="//div[@class='a-column a-span9 a-span-last']//h3[text()='Login & security']")
	//public WebElement LoginnSecurityLink; 
	
	//  @FindBy(xpath="//div[@class='a-column a-span9 a-span-last']/h3[text()='Your Orders']")
	 // public WebElement LoginnSecurityLink;
	
	//@FindBy(xpath="//img[@alt='Login & security']")
	 //public static WebElement LoginnSecurityLink;
	
	
	
 public static void navigateToLoginnSecurityPage() throws IOException{
	 //WebUtil.getUtilObject().click(LoginnSecurityLink);
	WebUtil.JSUtil.jsClick("LoginnSecurityLink");
	 //LoginnSecurityPage lginnSecPgObj=PageFactory.initElements(WebUtil.getDriver(), LoginnSecurityPage.class);
	 System.out.println("navigated successfully to log n sec");
	 //return lginnSecPgObj;
 }
 
 public static void navigateToPrimeMembershipPage() throws IOException{
	 //WebUtil.getUtilObject().click(LoginnSecurityLink);
	WebUtil.JSUtil.jsClick("PrimePageLink");
	 //LoginnSecurityPage lginnSecPgObj=PageFactory.initElements(WebUtil.getDriver(), LoginnSecurityPage.class);
	 System.out.println("navigated successfully to prime membership page");
	 //return lginnSecPgObj;
 }
 
}
