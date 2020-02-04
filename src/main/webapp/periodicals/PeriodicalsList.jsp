<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Periodicals Web Application</title>
</head>
<body>
	<center>
		<h1>Periodicals Management</h1>
        <h2>
        	<a href="pnew">Add New Periodical</a>
        	&nbsp;&nbsp;&nbsp;
        	<a href="plist">List All Periodicals</a>
        	
        </h2>
	</center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Periodicals</h2></caption>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Price</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="periodicals" items="${listPeriodicals}">
                <tr>
                    <td><c:out value="${periodicals.pid}" /></td>
                    <td><c:out value="${periodicals.title}" /></td>
                    <td><c:out value="${periodicals.price}" /></td>
                    <td>
                    	<a href="/pedit?pid=<c:out value='${periodicals.pid}' />">Edit</a>
                    	&nbsp;&nbsp;&nbsp;&nbsp;
                    	<a href="/pdelete?pid=<c:out value='${periodicals.pid}' />">Delete</a>                    	
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>	
</body>
</html>
