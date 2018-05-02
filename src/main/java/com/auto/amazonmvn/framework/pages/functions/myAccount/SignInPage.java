package com.auto.amazonmvn.framework.pages.functions.myAccount;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.auto.amazonmvn.framework.pages.functions.home.myhome.MyHomePage;
import com.auto.amazonmvn.framework.util.WebUtil;

public class SignInPage {

	/*@FindBy(id="ap_email")
	public WebElement UserNameED;
	
	@FindBy(id="continue")
	public WebElement ContinueBT;
	
	@FindBy(name="password")
	public WebElement PasswordED;
	
	@FindBy(id="signInSubmit")
	public WebElement SigninBT;
	*/
	public static void validLogin() throws IOException, InterruptedException{
		//below code webelements locator value read from or.properties file	,for that changes done in getWebElement method called through input method of WebUtil class	
		WebUtil.input("SIgnInUserNameED", "SIgnInUserNameTextBox");
		WebUtil.click("SIgnInContinueBT");
		WebUtil.input("SIgnInPasswordED", "SIgnInPasswordTextBox");
		WebUtil.click("SIgnInSigninButton");
		System.out.println("submit clicked");
		//if want to use pagefactory approach uncomment the FindBy code above and use same name in above input,click etc. methods 
		//MyHomePage myhmpgObj=PageFactory.initElements(WebUtil.getDriver(), MyHomePage.class);
		//return myhmpgObj;
	}
	
	public void invalidLogin(){
		
	}
}
