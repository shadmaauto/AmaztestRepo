package com.auto.amazonmvn.framework.testcases.myAccount;

import java.io.IOException;
import java.util.List;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.auto.amazonmvn.framework.pages.functions.home.myhome.MyHomePage;
import com.auto.amazonmvn.framework.pages.functions.myAccount.LoginnSecurityPage;
import com.auto.amazonmvn.framework.pages.functions.myAccount.MyAccountHomepage;
import com.auto.amazonmvn.framework.pages.functions.myAccount.SignInPage;
import com.auto.amazonmvn.framework.util.TestDataUtil;
import com.auto.amazonmvn.framework.util.WebUtil;

public class MyAccountLinksVerificationCases {

	/*public static void main(String[] args) throws IOException, InterruptedException{
		validatePrimeMembershipLink();
	}*/
	
	
	
	private static void navigatontoMyAccountpage() throws IOException, InterruptedException{
		WebUtil.openApp("FF", "https://www.amazon.com/ap/signin?_encoding=UTF8&ignoreAuthState=1&openid.assoc_handle=usflex&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.mode=checkid_setup&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&openid.ns.pape=http%3A%2F%2Fspecs.openid.net%2Fextensions%2Fpape%2F1.0&openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.com%2F%3Fref_%3Dnav_signin&switch_account=");
		SignInPage.validLogin();
		MyHomePage.navigateToMyAccountPage();
	}
	
	public void validYourOrdersLink(){
		
	}
	
	@Test
	public static void validatePrimeMembershipLink() throws IOException, InterruptedException{
				
		List<Integer> liData=TestDataUtil.getAllMatchedRowNumberList("TC_001");
		System.out.println("no. of matching rows are:"+liData.size());
		for(int i=0;i<liData.size();i++){
			System.out.println("Run no.:"+i);
			TestDataUtil.getTestCaseDatapoi(liData.get(i));
			navigatontoMyAccountpage();
			MyAccountHomepage.navigateToPrimeMembershipPage();
			WebUtil.Validate.validateText("PrimeLndgText", "MyPrimeMemLndngPgTxt");
			WebUtil.Validate.validateText("MyPrimeMemEnjoyheadingTxt","PrimeMemEnjoyHeadingTxt");
			
		}
		
		
		
		
	}
	
}
