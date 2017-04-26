<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <center>
        <h1>请登录</h1>
        <form action="http://check.x.com:8080/sso_same_father/ssologin" method="post">
          <span>用户名：</span><input type="text" name="username"/><br/>
           <span>密码：</span><input type="password" name="password"/><br/>
           <input type="hidden" value="${gotourl}" name="gotourl"/>
           <input type="submit" value="登录">   <input type="reset" value="重置">
        </form>
    </center>
  </body>
</html>
