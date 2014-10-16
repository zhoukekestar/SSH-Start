<%@page import="com.alibaba.fastjson.JSON"%>
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
//---------------------------------------------------userList

ArrayList<Map<?,?>> userList = new ArrayList<Map<?,?>>();
Map<String, String> map1 = new HashMap<String, String>();
map1.put("name", "zkk");
map1.put("sex", "man");
userList.add(map1);
Map<String, String> map2 = new HashMap<String, String>();
map2.put("name", "abc");
map2.put("sex", "woman");
userList.add(map2);
request.setAttribute("userList", userList);

//----------------------------------------------------complex
ArrayList<Map<String, Object>> complexList = new ArrayList<Map<String, Object>>();

Map<String,Object> objMap1 = new HashMap<String, Object>();
objMap1.put("aa", "abc");
objMap1.put("bb", userList);

complexList.add(objMap1);


Map<String,Object> objMap2 = new HashMap<String, Object>();
objMap2.put("aa", "abcabc");
objMap2.put("bb", userList);

complexList.add(objMap2);
request.setAttribute("complexList", complexList);

//------------------------------------------------------
String json = "{'name':'zkk','sex': 'man','age':3,'address': [{'name': '浙江', 'time': '小时候'}, {'name': '北京', 'time': '工作' }]}";
json.replaceAll("'", "\"");
System.out.print(json);
Map<String, Object> fastJson = new HashMap<String, Object>();
fastJson = JSON.parseObject(json);
request.setAttribute("fastJson", fastJson);

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
	<tr style=""><td>-----------userList</td></tr>
	<c:forEach var="item" items="${userList }" varStatus="i">
	<tr>
		<td>${item.name}</td>
		<td>${item.sex }</td>
	</tr>
	</c:forEach>
	
	<tr style=""><td>-----------complexList</td></tr>
	<c:forEach var="item" items="${complexList }" varStatus="i">
	<tr>
		<td>${item.aa}</td>
		<td>
			<c:forEach var="item2" items="${item.bb}" >
				name:${item2.name } sex:${item2.sex };
			</c:forEach>
		</td>
	</tr>
	<tr>
	</tr>
	</c:forEach>
	<tr style=""><td>-----------fastJson</td></tr>
	<tr>
		<td>${fastJson.name }</td>;
		<td>${fastJson.sex }</td>
	</tr>
	<tr>
		<td>${fastJson.age }</td>;
		<td>
			<c:forEach var="item" items="${fastJson.address }">
				${item.name } ${item.time } <br>
			</c:forEach>
		</td>
	</tr>
</table>



</body>
</html>