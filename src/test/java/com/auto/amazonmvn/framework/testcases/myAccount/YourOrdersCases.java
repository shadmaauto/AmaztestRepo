package com.auto.amazonmvn.framework.testcases.myAccount;



import java.io.IOException;

import com.auto.amazonmvn.framework.pages.functions.home.myhome.MyHomePage;
import com.auto.amazonmvn.framework.pages.functions.myAccount.MyAccountHomepage;
import com.auto.amazonmvn.framework.pages.functions.myAccount.SignInPage;
import com.auto.amazonmvn.framework.util.WebUtil;

public class YourOrdersCases {
	public static void main(String[] args) throws IOException, InterruptedException{
		tc_searchOrdersByText();
	}

	private static void tc_searchOrdersByText() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		WebUtil.openApp("FF", "");
		SignInPage.validLogin();
		MyHomePage.navigateToMyAccountPage();
	}
}
