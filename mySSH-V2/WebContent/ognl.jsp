<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%


String jsonStr2 = "[{\"address\": \"address2\",\"name\":\"zkk\",\"id\":2,\"email\":\"email2\"},"+
        "{\"address\":\"address\",\"name\":\"haha\",\"id\":1,\"email\":\"email\"}]";
String jsonStr1 = "{'address': 'address2','name':'zkk','id':2,'email':'email2'}";
String jsonNum = "{'12':'12212', '123':'123123'}";

Map<String, String> maper = new HashMap<String, String>();
maper.put("1", "11");
maper.put("2", "12");
String test = "{\"address\": \"address2\",\"name\":\"zkk\",\"id\":2,\"email\":\"email2\"}";
request.setAttribute("jsonStr2", jsonStr2);
request.setAttribute("jsonStr1", jsonStr1);
request.setAttribute("test", test);
request.setAttribute("maper", maper);
%>
<s:property value="#parameters.msg" /><br>
<s:property value="#jsonStr2[0][\"address\"]" /><br>
<s:property value="#request['test']" /><br>
str1---><s:property value="#request['jsonStr1']" /><br>
address---><s:property value="%{#request['jsonStr1']['address']}" /><br>
<body>
--><%=jsonStr1 %>
<br>
	<s:set name="foobar3" value="#{'foo1':'bar1', 'foo2':'bar2'}" />
    <p>The value of key "foo1" is <s:property value="#foobar3['foo1']" /></p>
    <p>不使用％：<s:url value="#foobar3['foo1']" /></p>
   <p>使用％：<s:url value="%{#foobar3['foo1']}" /></p>
   
	<s:set name="foobar2" value="# {'12':'12212', '123':'123123'}" />  
	add2-><s:property value="#foobar2['12']" />
	<br>
	<s:set name="foobar" value="# <%=jsonNum %>" />  
	add1-><s:property value="#foobar['12']" />
	<br>
	mapper-><s:property value="#request['mapper']" />
	mapper-><s:property value="#request['mapper']['1']" />
</body>
</html>