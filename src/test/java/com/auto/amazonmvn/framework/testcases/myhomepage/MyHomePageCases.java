/*
 * Author=SI
 */

package com.auto.amazonmvn.framework.testcases.myhomepage;

import java.io.IOException;
import java.sql.Driver;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.auto.amazonmvn.framework.pages.functions.home.myhome.MyHomePage;
import com.auto.amazonmvn.framework.util.TestDataUtil;
import com.auto.amazonmvn.framework.util.WebUtil;


public class MyHomePageCases {
	MyHomePage myHomepgObj;
	Logger log=Logger.getLogger(MyHomePageCases.class);
	
	@BeforeMethod
	public void setUp(){
		myHomepgObj=new MyHomePage();
		log.info("creating object of homepage class");
	}
	
	@Test
	public void verifyMyHomePageTitle() throws IOException, InterruptedException{
		log.info("******************************starting  tests**********");
		log.info("****************************** homepage tests**********");
		log.info("******************************LOGO tests**********");
		
		
		List<Integer> liData=TestDataUtil.getAllMatchedRowNumberList("TC_001");
		System.out.println("no. of matching rows are:"+liData.size());
		for(int i=0;i<liData.size();i++){
			System.out.println("Run no.:"+i);
			TestDataUtil.getTestCaseDatapoi(liData.get(i));
			String title=myHomepgObj.validateTitleOfHomePage();
			Assert.assertEquals(title,"Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more");
		}
		
		
	}
	
	@Test
	public void verifyMyHomePageIcon() throws IOException, InterruptedException{
		List<Integer> liData=TestDataUtil.getAllMatchedRowNumberList("TC_001");
		System.out.println("no. of matching rows are:"+liData.size());
		for(int i=0;i<liData.size();i++){
			System.out.println("Run no.:"+i);
			TestDataUtil.getTestCaseDatapoi(liData.get(i));
			Boolean flag=myHomepgObj.validateLogoOfHomePage();
			Assert.assertTrue(flag, "Home page icon Verification");
			}
			
	}
	
	@AfterMethod
	public static void closeBrowser(){
		WebUtil.DriverPool.get().quit();
	}
	
}
