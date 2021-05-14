<%@page import="com.Buyer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/buyers.js"></script>
<title>Insert title here</title>
</head>


<body>

<div class="container"><div class="row"><div class="col-6">
<h1>Buyer Management </h1>

<form id="formBuyer" name="formBuyer" method="post" action="buyers.jsp">
 Buyer ID:
<input id="buyerID" name="buyerID" type="text"
 class="form-control form-control-sm">
<br> Name:
<input id="name" name="name" type="text"
 class="form-control form-control-sm">
<br> Address:
<input id="address" name="address" type="text"
 class="form-control form-control-sm">
<br> Phone:
<input id="phone" name="phone" type="text"
 class="form-control form-control-sm">
<br> Email:
<input id="email" name="email" type="text"
 class="form-control form-control-sm">
 <br> Project Name:
<input id="projectName" name="projectName" type="text"
 class="form-control form-control-sm">
 <br>
<input id="btnSave" name="btnSave" type="button" value="Save"
 class="btn btn-primary">
<input type="hidden" id="hidBnoSave" name="hidBnoSave" value="">
</form>

<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divBuyersGrid">
 <%
 Buyer buyerObj = new Buyer();
 out.print(buyerObj.readBuyers());
 %>
</div>
</div> </div> </div>

</body>
</html>