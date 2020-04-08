<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
      <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>leaders page</title>
</head>
<body>

	
		<form:form style="" action="${pageContext.request.contextPath}/logout" 
							   method="POST" class="form-horizontal">
				<button type="submit" class="btn btn-success">logout</button>	   
		</form:form>
		
		
		User: <security:authentication property="principal.username"/>
		Roles: <security:authentication property="principal.authorities"/>
		
		<h3>OUHAMZA Company leaders page</h3>
		<hr>
		<h4>welcome to the OUHAMZA Company leaders page</h4>


	    <hr>
		   <p>
		    	 <a href="${pageContext.request.contextPath }/">Back to home page </a>
		   </p>
		<hr>
</body>
</html>