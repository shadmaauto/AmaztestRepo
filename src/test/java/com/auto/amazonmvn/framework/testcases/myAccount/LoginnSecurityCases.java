package com.auto.amazonmvn.framework.testcases.myAccount;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.auto.amazonmvn.framework.pages.functions.home.myhome.MyHomePage;
import com.auto.amazonmvn.framework.pages.functions.myAccount.LoginnSecurityPage;
import com.auto.amazonmvn.framework.pages.functions.myAccount.MyAccountHomepage;
import com.auto.amazonmvn.framework.pages.functions.myAccount.SignInPage;
import com.auto.amazonmvn.framework.util.TestDataUtil;
import com.auto.amazonmvn.framework.util.Web;
import com.auto.amazonmvn.framework.util.WebUtil;

import Exception.FilloException;

public class LoginnSecurityCases {

	public static void main(String[] args) throws IOException, FilloException, InterruptedException{
		//tc_editName();
		tc_editNameusingpoitodataread();
	}
	
	@AfterMethod
	public static void closeBrowser(){
		WebUtil.DriverPool.get().quit();
	}
	
	//common navigation method that can be used in test cases
	private static void navigationToLoginnSecurityPage() throws IOException, InterruptedException{
		WebUtil.openApp("FF", "https://www.amazon.com/ap/signin?_encoding=UTF8&ignoreAuthState=1&openid.assoc_handle=usflex&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.mode=checkid_setup&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&openid.ns.pape=http%3A%2F%2Fspecs.openid.net%2Fextensions%2Fpape%2F1.0&openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.com%2F%3Fref_%3Dnav_signin&switch_account=");
		SignInPage.validLogin();
		//WebUtil.Validate.validateText("SuccesMessageText", "ValSuccessmessage");
		MyHomePage.navigateToMyAccountPage();
		MyAccountHomepage.navigateToLoginnSecurityPage();
	}
	//@Parameters({"Browser","url"}) =>code used when 2 parameters provided through testng
	//@Parameters({"Browser"})
	//@Test
/*	public static void tc_editName(String b) throws IOException, FilloException, InterruptedException{
		SignInPage signInObj=WebUtil.openApp(b, "https://www.amazon.com/ap/signin?_encoding=UTF8&ignoreAuthState=1&openid.assoc_handle=usflex&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.mode=checkid_setup&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&openid.ns.pape=http%3A%2F%2Fspecs.openid.net%2Fextensions%2Fpape%2F1.0&openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.com%2F%3Fref_%3Dnav_signin&switch_account=");
		//below line to read test data from xlsx
		TestDataUtil.getTestCaseData("TC_001");
		MyHomePage myhmpgObj = signInObj.validLogin();
		//WebUtil.Validate.validateText("SuccesMessageText", "ValSuccessmessage");
		MyAccountHomepage myAcHmPgObj=myhmpgObj.navigateToMyAccountPage();
		LoginnSecurityPage lgSecPgObj= myAcHmPgObj.navigateToLoginnSecurityPage();
		lgSecPgObj.editName();
		System.out.println("TC PASS");
			
	}*/
	
	//when poi is used and multiple rows of data for testing a test case used
	@Test
	public static void tc_editNameusingpoitodataread() throws IOException, FilloException, InterruptedException{
		
		List<Integer> liData=TestDataUtil.getAllMatchedRowNumberList("TC_001");
		System.out.println("no. of matching rows are:"+liData.size());
		for(int i=0;i<liData.size();i++){
			System.out.println("Run no.:"+i);
			TestDataUtil.getTestCaseDatapoi(liData.get(i));
			navigationToLoginnSecurityPage();
			LoginnSecurityPage.editName();
			System.out.println("TC PASS");
		}
		
		
	}
	
}
