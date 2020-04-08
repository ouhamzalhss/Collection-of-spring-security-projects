<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
      <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home page</title>
</head>
<body>

	
		<form:form style="" action="${pageContext.request.contextPath}/logout" 
							   method="POST" class="form-horizontal">
				<button type="submit" class="btn btn-success">logout</button>	   
		</form:form>
		
		

		
		<h3>OUHAMZA Company home page</h3>
		<hr>
		<h4>welcome to the OUHAMZA Company home page</h4>

		<hr>
		
		<p> User: <security:authentication property="principal.username"/> </p>
		<p> Roles: <security:authentication property="principal.authorities"/> </p>
		
		
		<security:authorize access="hasRole('MANAGER')">
		   <hr>
		   <p>
		    	 <a href="${pageContext.request.contextPath }/leaders">Leadership meeting </a>
		    	 (Only for MANAGER people)
		   </p>
		</security:authorize>
		
		
		
		<security:authorize access="hasRole('ADMIN')">
		   <hr>
		   <p>
		    	 <a href="${pageContext.request.contextPath }/systems">IT admin meeting </a>
		    	 (Only for ADMIN people)
		   </p>
		</security:authorize>

</body>
</html>