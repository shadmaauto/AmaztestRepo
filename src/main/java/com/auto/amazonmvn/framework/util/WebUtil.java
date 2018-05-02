package com.auto.amazonmvn.framework.util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
	//import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.auto.amazonmvn.framework.pages.functions.myAccount.SignInPage;
import com.auto.amazonmvn.framework.util.PropertiesUtil;
import com.auto.amazonmvn.framework.util.TestDataUtil;

	public class WebUtil {
		private static  WebDriver DRIVER;
		//this for handling run of multiple threads	
	    public static ThreadLocal<WebDriver> DriverPool=null;
	    public static EventFiringWebDriver edriver;
	    public static WebEventListner eventListener;
	    
		public static WebDriver getDriver(){
			return DRIVER;
			
		}
		
	
		public static  void browserLaunch(String brName) {
			// TODO Auto-generated method stub
			//WebDriver driver=null;
			if(brName.equalsIgnoreCase("FF")){
				System.setProperty("webDriver.gecko.driver", "drivers//geckoDriverPool.get().exe");
				DRIVER=new FirefoxDriver();
				
			}else if(brName.equalsIgnoreCase("CH")){
				System.setProperty("webDriver.chrome.driver","drivers//chromeDriverPool.get().exe" );
				DRIVER=new ChromeDriver();
			}else if(brName.equalsIgnoreCase("CH")){
				System.setProperty("webDriver.ie.driver", "drivers//IEDriverServer.exe");
				DRIVER=new InternetExplorerDriver();
			}
			DRIVER.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			DRIVER.manage().window().maximize();
			
			//DRIVER=driver;
		}
		
		
		//to handle multiple threads
		public static  void browserLaunchThreadLocal(String brName) {
			// TODO Auto-generated method stub
			//WebDriver driver=null;
			if(brName.equalsIgnoreCase("FF")){
				//System.out.println("here1");
				System.setProperty("webdriver.gecko.driver", "drivers//geckoDriver.exe");
				DRIVER=new FirefoxDriver();
				DriverPool=new ThreadLocal<WebDriver>();
				DriverPool.set(DRIVER);
				System.out.println(DriverPool.get().getTitle());
				//System.out.println("here2");
								
			}else if(brName.equalsIgnoreCase("CH")){
				System.setProperty("webDriver.chrome.driver","drivers//chromeDriver.exe" );
				DRIVER=new ChromeDriver();
				DriverPool=new ThreadLocal<WebDriver>();
				DriverPool.set(DRIVER );
				
			}else if(brName.equalsIgnoreCase("IE")){
				System.setProperty("webDriver.ie.driver", "drivers//IEDriverServer.exe");
				DRIVER=new InternetExplorerDriver();
				DriverPool=new ThreadLocal<WebDriver>();
				DriverPool.set(DRIVER);
			}
			
			edriver=new EventFiringWebDriver(DriverPool.get());
			eventListener=new WebEventListner();
			edriver.register(eventListener);
			DriverPool.set(edriver);
			
			DriverPool.get().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			DriverPool.get().manage().window().maximize();
			
			
			
		}
		
		//grid usage when hub decides on which node to run based on the browser,os and available nodes with these configurations
		public static  void browserLaunch(String brName,String osName) throws MalformedURLException {
			DesiredCapabilities dc=null;
			
			if(brName.equalsIgnoreCase("FF") && osName.equalsIgnoreCase("win")){
				dc=dc.firefox();
				dc.setPlatform(Platform.WINDOWS);
				
			}else if(brName.equalsIgnoreCase("FF") && osName.equalsIgnoreCase("mac")){
				dc=dc.firefox();
				dc.setPlatform(Platform.MAC);
			}else if(brName.equalsIgnoreCase("FF") && osName.equalsIgnoreCase("lin")){
				dc=dc.firefox();
				dc.setPlatform(Platform.LINUX);
			}else if(brName.equalsIgnoreCase("CH") && osName.equalsIgnoreCase("win")){
				dc=dc.chrome();
				dc.setPlatform(Platform.WINDOWS);
			}else if(brName.equalsIgnoreCase("CH") && osName.equalsIgnoreCase("mac")){
				dc=dc.chrome();
				dc.setPlatform(Platform.MAC);
			}else if(brName.equalsIgnoreCase("CH") && osName.equalsIgnoreCase("lin")){
				dc=dc.chrome();
				dc.setPlatform(Platform.LINUX);
			}
			java.net.URL hubUrlObj=new java.net.URL("http://192.168.0.8:7777/wd/hub");
			WebDriver DRIVER=new RemoteWebDriver(hubUrlObj, dc);
			DriverPool.set(DRIVER);		
			DriverPool.get().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			DriverPool.get().manage().window().maximize();
			
			//DRIVER=driver;
		}
		
	public static void openUrl(String url){
		DriverPool.get().get(url);
		//DriverPool.get().get(url);
	}


	public static void openApp(String browserName,String url){
		browserLaunchThreadLocal(browserName);
		//browserLaunch(browserName);
		openUrl(url);
		//SignInPage signInObj=PageFactory.initElements(WebUtil.getDriver(), SignInPage.class);
		//return signInObj;
		
	}
	
	public static void openApp(String browserName,String url,String osName) throws MalformedURLException{
		browserLaunch(browserName, osName);
		openUrl(url);
		//SignInPage signInObj=PageFactory.initElements(WebUtil.getDriver(), SignInPage.class);
		//return signInObj;
	}
	public static void input(String loc,String testDatafieldName) throws IOException{
		WebElement weObj=getWebElement(loc);
		String inputVal=TestDataUtil.DataMap.get(testDatafieldName);
		//System.out.println(inputVal);
		input(weObj,inputVal);
	}
	
	public static void input(WebElement webObj,String inputVal){
		if(checkWebElementForAction(webObj)==true){
		webObj.clear();
		webObj.sendKeys(inputVal);
		}
	}

	public static void click(String loc) throws IOException, InterruptedException{
		try{
			WebElement weObj=getWebElement(loc);
			click(weObj);
		}catch(StaleElementReferenceException se){
			Thread.sleep(3000);
			WebElement weObj=getWebElement(loc);
			click(weObj);
		}catch(UnhandledAlertException uhae){
			Alert alert=DriverPool.get().switchTo().alert();//yaha
			alert.accept();
			WebElement weObj=getWebElement(loc);
			click(weObj);
		}catch(NoSuchWindowException nwe){
			
		}
		
	}
	
	public static void click(WebElement weObj){
		if(checkWebElementForAction(weObj)==true){
		weObj.click();
		}
	}
	

	public void selectByText(String loc,String optionText) throws IOException{
		WebElement weObj=getWebElement(loc);
		if(checkWebElementForAction(weObj)==true){
		Select selObj=new Select(weObj);
		selObj.selectByVisibleText(optionText);
		}
	}

	public void selectByIndex(String loc,int optionIndex) throws IOException{
		WebElement weObj=getWebElement(loc);
		if(checkWebElementForAction(weObj)==true){
		Select selObj=new Select(weObj);
		selObj.selectByIndex(optionIndex);
		}
	}

	public void selectByValue(String loc,String val) throws IOException{
		WebElement weObj=getWebElement(loc);
		if(checkWebElementForAction(weObj)==true){
		Select selObj=new Select(weObj);
		selObj.deselectByValue(val);
		}
	}

	public void selectByPartiaText(String loc,String partialVal) throws IOException{
		WebElement weObj=getWebElement(loc);
		if(checkWebElementForAction(weObj)==true){
		Select selObj=new Select(weObj);
		List<WebElement> liWe=selObj.getOptions();
		for(int i=0;i<=liWe.size()-1;i++){
			WebElement we=liWe.get(i);
			String txt=we.getText();
			if(txt.toLowerCase().contains(partialVal.toLowerCase())){
				selObj.deselectByIndex(i);
				break;
			}
			}

		}
	}

	public void checkCheckBox(String loc) throws IOException{
		WebElement weObj=getWebElement(loc);
		if(checkWebElementForAction(weObj)==true){
			if(weObj.isSelected()==false){
				weObj.click();
			}
		}
	}


	public void uncheckCheckBox(String loc) throws IOException{
		WebElement weObj=getWebElement(loc);
		if(checkWebElementForAction(weObj)==true){
			if(weObj.isSelected()==true){
				weObj.click();
			}
		}
	}


	public static boolean checkWebElementForAction(WebElement we){
		if(we.isDisplayed()==true){
			if(we.isEnabled()==true){
				return true;
			}else{
				System.out.println("web Element not enabled");
				return false;
			}
			}else{
				System.out.println("web Element not visible");
		}
		return false;
	}

	public static WebElement getWebElement(String loc) throws IOException{
		String locValfull=PropertiesUtil.getLocator(loc);
		String[] locArr=locValfull.split("##");
		String locType=locArr[1];
		String locValue=locArr[0];
		WebElement we=null;
		if(locType.equalsIgnoreCase("XP")){
		we=DriverPool.get().findElement(By.xpath(locValue));//yaha
		System.out.println(we.getText());
		}else if(locType.equalsIgnoreCase("NM")){
		we=DriverPool.get().findElement(By.name(locValue));	//yaha
		}else if(locType.equalsIgnoreCase("ID")){
		we=DriverPool.get().findElement(By.id(locValue));//yaha
		System.out.println("found by id using or");
		}else if(locType.equalsIgnoreCase("CN")){
		we=DriverPool.get().findElement(By.className(locValue));	}//yaha
		else if(locType.equalsIgnoreCase("LK")){
		we=DriverPool.get().findElement(By.linkText(locValue));}//yaha
		else if(locType.equalsIgnoreCase("PLK")){
		we=DriverPool.get().findElement(By.partialLinkText(locValue));	}//yaha
		else{
			System.out.println(locType +"invalid value" );
			
		}
		return we;
		
	}

		
	public  void setFocusByTitle(String expTitle){
			
			Set<String> handleSet = DriverPool.get().getWindowHandles();
			Iterator<String> it=handleSet.iterator();
			while(it.hasNext()==true){
				DriverPool.get().switchTo().window(it.next());
				String title = DriverPool.get().getTitle();
				if(title.equalsIgnoreCase(expTitle)){
					break;
				}
			}
			
		 
			
		}
		
	public void setFocusByPartialTitle(String expPartTitle){
			String lowerCaseExpPartTitle = expPartTitle.toLowerCase();
			Set<String> handleSet = DriverPool.get().getWindowHandles();
			Iterator<String> it=handleSet.iterator();
			while(it.hasNext()==true){
				DriverPool.get().switchTo().window(it.next());
				String title = DriverPool.get().getTitle().toLowerCase();
				if(title.contains(lowerCaseExpPartTitle)){
					break;
				}
			}
			
				
		}


	public void setFocusByUrl(String expUrl){
		
		Set<String> handleSet = DriverPool.get().getWindowHandles();
		Iterator<String> it=handleSet.iterator();
		while(it.hasNext()==true){
			DriverPool.get().switchTo().window(it.next());
			String url=DriverPool.get().getCurrentUrl();
			if(url.equalsIgnoreCase(expUrl)){
				break;
			}
		}
		
			
	}


	public void setFocusByPartialUrl(String expPartUrl){
		String lowerCaseExpPartUrl = expPartUrl.toLowerCase();
		Set<String> handleSet = DriverPool.get().getWindowHandles();
		Iterator<String> it=handleSet.iterator();
		while(it.hasNext()==true){
			DriverPool.get().switchTo().window(it.next());
			String url = DriverPool.get().getCurrentUrl().toLowerCase();
			if(url.contains(lowerCaseExpPartUrl)){
				break;
			}
		}
		

		
	}
 

	public void setFocusByWebElement(By byObj){
		Set<String> handleSet = DriverPool.get().getWindowHandles();
		Iterator<String> it=handleSet.iterator();
		while(it.hasNext()==true){
			DriverPool.get().switchTo().window(it.next());
		    try{
		    	DriverPool.get().findElement(byObj);
		    	break;
		    }catch(NoSuchElementException ne){
		    	
		    }
			}
	}

	public void getSnapshot(String destFilePath) throws IOException{
		TakesScreenshot tss=(TakesScreenshot)DriverPool.get();
		File fileObj=tss.getScreenshotAs(OutputType.FILE);
		//File destFile=new File("Results/Screenshots/myImg.png");
		File destFile=new File(destFilePath+System.currentTimeMillis()+".png");
		FileHandler.copy(fileObj,destFile);
	}

	public static class JSUtil{
		private static JavascriptExecutor JS;
		
		public static void  jsClick(String locatorName) throws IOException{
			if(JS==null){
				JS=(JavascriptExecutor) WebUtil.getDriver();
				}
			WebElement we = WebUtil.getWebElement(locatorName);
			JS.executeScript("arguments[0].click()", we);
		}
		
		public static void  jsClick(WebElement we){
			if(JS==null){
				JS=(JavascriptExecutor) WebUtil.getDriver();
				}
			//WebElement we = WebUtil.getUtilObject().getWebElement(locator);
			JS.executeScript("arguments[0].click()", we);
		}
		
		
		public void jsInput(String locator,String inputVal) throws IOException{
			if(JS==null){
				JS=(JavascriptExecutor) WebUtil.getDriver();
				}
			WebElement we = WebUtil.getWebElement(locator);
			JS.executeScript("arguments[0].setAttribute('value'+'"+inputVal+"')", we);
		}
		
		
		public void jsMouseHover(String locator) throws IOException{
			if(JS==null){
				JS=(JavascriptExecutor) WebUtil.getDriver();
				}
			WebElement we = WebUtil.getWebElement(locator);
			String jsScript="if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover',true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
			JS.executeScript(jsScript, we);
		}
	}
	
	public static class ActionsUtil{
		public static void mouseHover(WebElement weObj){
			if(checkWebElementForAction(weObj)==true){
				new Actions(DRIVER).moveToElement(weObj).build().perform();
			}
		}	
		public static void mouseHover(String locatorName) throws IOException{
			WebElement weObj=getWebElement(locatorName);
			mouseHover(weObj);
		}
		public static void click(WebElement weObj){
			if(checkWebElementForAction(weObj)==true){
				new Actions(DRIVER).click(weObj);
			}
		}	
		public static void click(String locatorName) throws IOException{
			WebElement weObj=getWebElement(locatorName);
			click(weObj);
		}
	}
	
	
	public static class Validate{
		public static void validateText(String locatorName,String validationName) throws IOException{
			WebElement we=getWebElement(locatorName);
			String expValue=TestDataUtil.DataMap.get(validationName);
			System.out.println("value from excel is:"+expValue);
			validateText(we, expValue);
		}

		public static void validateText(WebElement we,String expValue){
			String actValue=we.getText();
			System.out.println("value from we is:"+actValue);
			if(actValue.equalsIgnoreCase(expValue)==true){
				System.out.println("Passed");
			}else{
				System.out.println("Fail");
			}
			Assert.assertEquals(expValue, actValue);
		}
		
		public static void validateTextContains(){
			
		}
		public static void validateEnabled(){
			
		}
		
	
	
	}
	}


