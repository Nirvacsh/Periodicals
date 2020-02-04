<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Periodicals Web Application</title>
</head>
<body>
	<center>
		<h1>Periodical Management</h1>
        <h2>
        	<a href="/periodicals/new">Add New Periodical</a>
        	&nbsp;&nbsp;&nbsp;
        	<a href="/periodicals/plist">List All Periodicals</a>
        	
        </h2>
	</center>
    <div align="center">
		<c:if test="${periodicals != null}">
			<form action="pupdate" method="post">
        </c:if>
        <c:if test="${periodicals == null}">
			<form action="pinsert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
            	<h2>
            		<c:if test="${periodicals != null}">
            			Edit Periodical
            		</c:if>
            		<c:if test="${periodicals == null}">
            			Add New Periodical
            		</c:if>
            	</h2>
            </caption>
        		<c:if test="${periodicals != null}">
        			<input type="hidden" name="pid" value="<c:out value='${periodicals.pid}' />" />
        		</c:if>            
            <tr>
                <th>Name: </th>
                <td>
                	<input type="text" name="title" size="45"
                			value="<c:out value='${periodicals.title}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>Password: </th>
                <td>
                	<input type="text" name="price" size="45"
                			value="<c:out value='${periodicals.price}' />"
                	/>
                </td>
            </tr>
                       <tr>
            	<td colspan="2" align="center">
            		<input type="submit" value="Save" />
            	</td>
            </tr>
        </table>
        </form>
    </div>	
</body>
</html>
