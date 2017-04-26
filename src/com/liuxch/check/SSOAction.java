package com.liuxch.check;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SSOAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SSOAction() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");		
		if (SSOCheck.checkUser(username, password)) {
			// 存储用户登录标志到父域中
			Cookie cookie = new Cookie("ssocookie", "sso");
			cookie.setDomain(".x.com");
			cookie.setPath("/");
			response.addCookie(cookie);            
			response.sendRedirect(getgotoUrl(request));
		} else {
			response.sendRedirect("http://check.x.com:8080/sso_same_father/login.jsp");
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	public void init() throws ServletException {

	}
	
	public String getgotoUrl(HttpServletRequest request){
		//默认主页
		String gotourl =  "";
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			for(Cookie cookie: cookies){
				if(cookie.getName().equals("gotourl")){
					gotourl = cookie.getValue();
				}
			}
		}
		return gotourl;
	}

}
