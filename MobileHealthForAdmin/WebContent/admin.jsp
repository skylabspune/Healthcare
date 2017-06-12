<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Page</title>
<script language="javascript">

function validatePatientForm()
{
	var username=document.getElementById("username").value;
	var password=document.getElementById("password").value;
	var cpassword=document.getElementById("cpassword").value;
	var firstname=document.getElementById("firstname").value;
	var lastname=document.getElementById("lastname").value;
	var regex=/^[a-zA-Z]+$/;
 
	if(username=="" || password=="" || cpassword=="" || firstname=="" || lastname=="")
	{
		alert("All fields are mandetory");
		return false;
	}
	
	if(password!=cpassword) {
		document.addPatientForm.cpassword.focus();
		document.getElementById("diverror").innerHTML="Password do not match, please enter correct password.";
		return false;
	}

	if(!firstname.match(/^[a-zA-Z]+$/)) {
		document.addPatientForm.firstname.focus();
		document.getElementById("diverror").innerHTML="First name should contain only alphabates.";
		return false;
	}
	
	if(!lastname.match(/^[a-zA-Z]+$/)) {
		document.addPatientForm.lastname.focus();
		document.getElementById("diverror").innerHTML="Last name should contain only alphabates.";
		return false;
	}
	
	document.getElementById("diverror").style.display= 'none';
	return true;
}

</script>

</head>
<body>

<div class="error" id="diverror"></div>
<br>
<div class="error" id="error">${requestScope.error}</div>
<br>
<div class="success" id="message">${requestScope.message}</div>
<br>
Enter Patient's details to add<br><br>
<form name="addPatientForm" method="post" action="<%= request.getContextPath() %>/AddPatientServlet" onsubmit="return validatePatientForm()">
Patient's first name : <input type="text" name="firstname" id="firstname" maxlength="10">
<br><br>
Patient's last name : <input type="text" name="lastname" id="lastname" maxlength="10">
<br><br>
Enter Username : <input type="text" value="" name="username" id="username" maxlength="50">
<br><br>
Enter Password : <input type="password" value="" name="password" id="password" maxlength="16">
<br><br>
Confirm Password : <input type="password" name="cpassword" id="cpassword" maxlength="16">
<br><br>
<input type="submit" value="Add Patient">
</form>
</body>
</html>