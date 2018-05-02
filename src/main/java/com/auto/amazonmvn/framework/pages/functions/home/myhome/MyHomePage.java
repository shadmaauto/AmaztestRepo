package com.auto.amazonmvn.framework.pages.functions.home.myhome;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//import com.gargoylesoftware.htmlunit.protocol.javascript.JavaScriptURLConnection;
import com.auto.amazonmvn.framework.pages.functions.myAccount.MyAccountHomepage;
import com.auto.amazonmvn.framework.pages.functions.myAccount.SignInPage;
import com.auto.amazonmvn.framework.pages.functions.myAccount.YourOrdersPage;
import com.auto.amazonmvn.framework.util.WebUtil;

public class MyHomePage {

	WebElement weAmazonLogo;
	private static void navigatontoMyHomepage() throws IOException, InterruptedException{
		WebUtil.openApp("FF", "https://www.amazon.com/ap/signin?_encoding=UTF8&ignoreAuthState=1&openid.assoc_handle=usflex&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.mode=checkid_setup&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&openid.ns.pape=http%3A%2F%2Fspecs.openid.net%2Fextensions%2Fpape%2F1.0&openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.com%2F%3Fref_%3Dnav_signin&switch_account=");
		SignInPage.validLogin();
		
	}
	
	public boolean validateLogoOfHomePage() throws IOException, InterruptedException{
		
		navigatontoMyHomepage();
		weAmazonLogo=WebUtil.getWebElement("AmazonLogo");
		return WebUtil.checkWebElementForAction(weAmazonLogo);
	}
	
	public String validateTitleOfHomePage() throws IOException, InterruptedException{
		navigatontoMyHomepage();
		return WebUtil.DriverPool.get().getTitle();
	}
	
	
	
	public static void navigateToMyAccountPage() throws IOException{
		//WebUtil.ActionsUtil.mouseHover(MyAccountMenu);
		WebUtil.ActionsUtil.mouseHover("MyAccountMenu");
		System.out.println("mousehover done");
		
		//WebUtil.getUtilObject().click(OrdersLink);
		//String locator="//a[@class='nav-link nav-item']/span[text()='Your Account']##XP";
		//new JSUtil().jsClick(locator);
		
		WebUtil.JSUtil.jsClick("YourAccountLink");
		//WebUtil.ActionsUtil.click("YourAccountLink");
		System.out.println("my acct link clicked");
		//MyAccountHomepage myAcctHmpgObj=PageFactory.initElements(WebUtil.getDriver(), MyAccountHomepage.class);
		//return myAcctHmpgObj;
	}
	
	
	/*public YourOrdersPage navigateToYourOrdersPage(){
		
		
		WebUtil.click(OrdersLink);
		YourOrdersPage yourOrdersPgObj=PageFactory.initElements(WebUtil.getDriver(), YourOrdersPage.class);
		return yourOrdersPgObj;
	}*/
	
}
