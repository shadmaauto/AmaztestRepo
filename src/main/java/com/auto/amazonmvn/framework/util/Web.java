package com.auto.amazonmvn.framework.util;

public class Web {

	private static WebUtil webUtilObj;
	private static JSUtil jsUtilObj;
	private static ActionsUtil actionsUtilObj;
	
	public static WebUtil webDriverWay(){
		if(webUtilObj==null){
			webUtilObj=new WebUtil();
		}
		return webUtilObj;
	}
	
	public static JSUtil jSWay(){
		if(jsUtilObj==null){
			jsUtilObj=new JSUtil();
		}
		return jsUtilObj;
	}
	
	public static ActionsUtil actionaWay(){
		if(actionsUtilObj==null){
			actionsUtilObj=new ActionsUtil();
		}
		return actionsUtilObj;
	}
	
}
