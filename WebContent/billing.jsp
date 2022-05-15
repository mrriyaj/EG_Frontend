<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="servermodel.Billing"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Billing Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/billing.js"></script>
</head>
<body>
	<div align="center">
		<br>
		<br>
		<h2 class="mb-3">Create a Bill Record</h2>
		<form id="formBilling" name="formBilling"
			class="justify-content-center" style="width: 60%">

			<div id="alertSuccess" class="alert alert-success"></div>
			<div id="alertError" class="alert alert-danger"></div>

			<div class="form-row">
				<div class="form-group col-md-6">
					<label class="form-label">Billing ID </label> 
					<input type="text" class="form-control" id="billing_id" name="billing_id" placeholder="Enter a Billing ID">
				</div>
				<div class="form-group col-md-6">
					<label class="form-label">Meter ID</label> 
					<input type="text" class="form-control" id="mc_id" name="mc_id" placeholder="Enter a Meter ID">
				</div>
			</div>

			<div class="form-row">
				<div class="form-group col-md-6">
					<label class="form-label">User ID</label> 
					<input type="text" class="form-control" id="user_id" name="user_id" placeholder="Enter User ID">
				</div>
				<div class="form-group col-md-6">
					<label class="form-label">Usage</label> 
					<input class="form-control" type="number" step="usege" id="usege" name="usege"  min=1>
				</div>
			</div>

			<div class="form-row">
				<div class="form-group col-md-6">
					<label class="form-label">Total</label> 
					<input type="number" class="form-control" id="total" name="total">
				</div>
				<div class="form-group col-md-6">
					<label class="form-label">Status</label> 
					<input type="text" class="form-control" id="status" name="status" placeholder="Select a status">
				</div>
			</div>


			<br>
			<div>
				<button class="btn btn-primary mr-3" id="btnSave" name="btnSave" type="button" style="width: 30%; height: 100%">Save Billing</button>
				<button class="btn btn-info" id="clear" type="button" style="width: 30%; height: 100%">Clear</button>
				<input type="hidden" class="form-control" id="hidBillIDSave" name="hidBillIDSave" value="">
			</div>
		</form>
		<br> <br>
		<div class="ml-5">
			<h2 class="mb-3">All Billing Records</h2>
		</div>


		<br>
		<div id="divBillGrid"
			class="col-12 mb-5 table table-responsive container-fluid table-striped row justify-content-center">
			<%
			Billing billObj = new Billing();
			out.print(billObj.readBilling());
			%>
		</div>

	</div>

</body>
</html>