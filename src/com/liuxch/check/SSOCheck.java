package com.liuxch.check;

public class SSOCheck {
	
	//用户名
	public static final String USER_NAME = "test";
	
	//密码
	public static final String USER_PASSWORD = "123456";
	
	public static boolean checkUser(String username,String userpassword){
		boolean result = false;
		
		if(USER_NAME.equals(username)&&USER_PASSWORD.equals(userpassword)){
			result = true;
		}
		return result;		
	}
	
	public static boolean checkCookie(String cookieName,String cookieValue){
		boolean result = false;
		if("ssocookie".equals(cookieName) && "sso".equals(cookieValue)){
			result = true;
		}
		return result;	
	}

}
