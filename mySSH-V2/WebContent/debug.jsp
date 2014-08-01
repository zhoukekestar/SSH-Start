<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/conf.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
	<h1>Debug.jsp API.</h1>
	<h2>actions</h2>
	<ul>
		<li><a href="${base}actions/login.action?name=res_1">/actions/login.action?name=res_1</a></li>
		<li><a href="${base}actions/login.action?name=res_2">/actions/login.action?name=res_2</a></li>
		<li><a href="${base}actions/login.action?name=err">/actions/login.action?name=err</a></li>
	</ul>
	<h2>methods.action</h2>
	<ul>
		<li><a href="${base}actions/methods_res_1.action">/methods_res_1</a></li>
		<li><a href="${base}actions/methods_res_2.action">/methods_res_2</a></li>
		<li><a href="${base}actions/methods_error.action">/methods_error</a></li>
	</ul>
	<h2>i18n</h2>
	<ul>
		<li><a href="${base}i18n.action">/i18n</a></li>
	</ul>
	<h1>Servlet</h1>
	<ul>
		<li><a href="${base}servlet/SayHello">sayHello</a></li>
		<li><a href="${base}servlet/ShowRequest?name=name1&sex=man">showRequest</a></li>
		<li><a href="${base}servlet/TestChinese?country=%E4%B8%AD%E5%9B%BD">testChinese</a></li>
		<li><a href="${base}servlet/async/AsyncRequest?name=abc">asyncRequest</a></li>
	</ul>
</body>
</html>