<%@page import="org.apache.catalina.User"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%
request.setAttribute("req", "req - scope");
request.setAttribute("id", "2");

//-------------------------------------------------list
ArrayList<String> list = new ArrayList<String>();
list.add("aa");
list.add("bb");
request.setAttribute("list", list);

//--------------------------------------------------map
Map<String, Object> map = new HashMap<String, Object>();
map.put("a", "map-a");
map.put("b", "map-b");
request.setAttribute("map", map);


//---------------------------------------------------usermap
class User
{
	public String name = "";
	public String sex = "";
	
	public User()
	{
		name = "unname";
		sex = "unsex";
	}
	public User(String name, String sex)
	{
		this.name = name;
		this.sex = sex;
	}
	
	public String toString()
	{
		return "name: " + this.name + " sex: " + this.sex;
	}
}
Map<String, User> userMap = new HashMap<String,User>();
userMap.put("a", new User("zkk", "man"));
userMap.put("b", new User("abc","woman"));
request.setAttribute("userMap", userMap);


//---------------------------------------------------
%>
</head>
<body>
<table>
	<tr>
		<td>req</td>
		<td>${req }</td>
	</tr>
	<tr>
		<td>name</td>
		<td>${param.name }</td>
	</tr>
	<tr>
		<td>id</td>
		<td>
			<c:choose>
			<c:when test="${id==1 }">
				id is 1	
			</c:when>
			<c:otherwise>
				!!!!@
			</c:otherwise>
			</c:choose>
		</td>
	</tr>
	<tr style=""><td>-----------list</td></tr>
	<c:forEach var="item" items="${list }" varStatus="i">
		<tr>
			<td>${i.index + 1 }</td>
			<td>${item }</td>
		</tr>
	</c:forEach>
	<tr style=""><td>-----------map</td></tr>
	<tr>
		<td>${map.a }</td>
		<td>${map.b }</td>
	</tr>
	<tr>
		<td>${userMap.a }</td>
		<td>${userMap.b }</td>
	</tr>
</table>



</body>
</html>