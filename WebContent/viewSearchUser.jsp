<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
a {
  background-color: blue;
  color: white;
  padding: 1em 1.5em;
  text-decoration: none;
  text-transform: uppercase;
}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Details</title>
</head>
<body>
<h1>User Details</h1>
<body>
   <table border="1" class="myTable"> 
			<tr class="myTableRow"> 
				<th class="myTableHead" style=" text-align: center">Username</th>
				<th class="myTableHead" style=" text-align: center">UTA ID</th>
				<th class="myTableHead" style="padding-right: 10px; text-align: center">First Name</th> 
				<th class="myTableHead" style="padding-right: 10px; text-align: center">Last Name</th> 
				<th class="myTableHead" style="padding-right: 10px; text-align: center">Email</th>
				<th class="myTableHead" style="padding-right: 10px; text-align: center">Phone</th>
				<th class="myTableHead" style="padding-right: 10px; text-align: center">Street Address</th>
				<th class="myTableHead" style="padding-right: 10px; text-align: center">City</th>
				<th class="myTableHead" style="padding-right: 10px; text-align: center">State</th>
				<th class="myTableHead" style="padding-right: 10px; text-align: center">Zip-code</th>
				<th class="myTableHead" style="padding-right: 10px; text-align: center">Role</th>
				
				
			</tr>
			
			
			<tr class="myTableRow">
			<td class="myTableCell" style="padding-right: 10px; text-align: center""><c:out value="${user.username}" /></td>
			<td class="myTableCell" style="padding-right: 10px; text-align: center""><c:out value="${user.id}" /></td>
			<td class="myTableCell" style="padding-right: 10px; text-align: center""><c:out value="${user.firstname}" /></td>
			<td class="myTableCell" style="padding-right: 10px; text-align: center""><c:out value="${user.lastname}" /></td>
			<td class="myTableCell" style="padding-right: 10px; text-align: center""><c:out value="${user.email}" /></td>
			<td class="myTableCell" style="padding-right: 10px; text-align: center""><c:out value="${user.phone}" /></td>
			<td class="myTableCell" style="padding-right: 10px; text-align: center""><c:out value="${user.address}" /></td>
			<td class="myTableCell" style="padding-right: 10px; text-align: center""><c:out value="${user.city}" /></td>
			<td class="myTableCell" style="padding-right: 10px; text-align: center""><c:out value="${user.state}" /></td>
			<td class="myTableCell" style="padding-right: 10px; text-align: center""><c:out value="${user.zipcode}" /></td>
			<td class="myTableCell" style="padding-right: 10px; text-align: center""><c:out value="${user.role}" /></td>
			
						
		</c>

 		
 </table>
 
 <br/>
 <br/>
 <br/>
 <a href="adminHome.jsp">Home</a>
 
  
</body>
</html>