package com.liuxch.demo2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liuxch.util.HttpUtils;

public class demo2 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public demo2() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		String url = "http://check.x.com:8080/sso_same_father/ssocookie";
		Cookie[] cookies = request.getCookies();

		String result = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("ssocookie".equals(cookie.getName())) {
					try {
						result = HttpUtils.sendHttpRequest(url,
								cookie.getName(), cookie.getValue());
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (result == "1") {
						response.sendRedirect("http://demo1.x.com:8080/sso_same_father/demo2.jsp");
						break;
					}
				}
			}
		}
		// session无法跨域，gotourl无法传递
		// HttpSession session = request.getSession();
		// session.setAttribute("gotourl",
		// "http://demo1.x.com:8080/sso_same_father/demo1.jsp");

		Cookie urlcookie = new Cookie("gotourl",
				"http://demo2.x.com:8080/sso_same_father/demo2.jsp");
		urlcookie.setDomain(".x.com");
		urlcookie.setPath("/");
		response.addCookie(urlcookie);
		response.sendRedirect("http://check.x.com:8080/sso_same_father/login.jsp");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
