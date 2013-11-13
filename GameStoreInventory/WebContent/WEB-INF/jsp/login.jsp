<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
<p align="center"> <u>LOGIN</u> </p>

  <form method="POST" action="login">
   <table>
    <tr><td align="center">Username</td>
    	<td align="center"><input type="text" name="username" size="20" maxlength="16"/></td>
    </tr> 
	<tr><td align="center">Password</td>
		<td align="center"><input type="password" name="password" size="20" maxlength="16"/></td>
	</tr> 
	<tr><td></td>
		<td><div align="center"><input type="submit" value="LOGIN" /></div></td>
	</tr>
   </table>
  </form>
   
  <p><c:if test="${not empty msg}">
    <h4>${msg}</h4>
	</c:if>
  </p>
	
</body>
</html>