package com.auto.amazonmvn.framework.pages.functions.myAccount;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.auto.amazonmvn.framework.util.WebUtil;

public class LoginnSecurityPage {

	public static void editName() throws IOException, InterruptedException{
		WebUtil.click("EditNameBT");
		WebUtil.input("NewNametxtBox", "EditNameTextBox");
		WebUtil.click("saveChngsBT");
		System.out.println("Name edited successfully");
	}
	
}
