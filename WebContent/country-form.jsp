<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Covid 19 Tracker</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: grey">
			<div>
				<a  class="navbar-brand"> Covid 19 Tracker </a>
			</div>
			

		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${user != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${user == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2 class="text-center">
						<c:if test="${user != null}">
            			Edit Country
            		</c:if>
						<c:if test="${user == null}">
            			Add New Country
            		</c:if>
					</h2>
					<hr>
			
					<br>	
				</caption>

				<c:if test="${user != null}">
					<input type="hidden" name="country" value="<c:out value='${user.country}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Country</label> <input type="text"
						value="<c:out value='${user.country}' />" class="form-control"
						name="country" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Total Case</label> <input type="number"
						value="<c:out value='${user.total_case}' />" class="form-control"
						name="total_case">
				</fieldset>

				<fieldset class="form-group">
					<label>Total Death</label> <input type="number"
						value="<c:out value='${user.total_death}' />" class="form-control"
						name="total_death">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Total Recovered</label> <input type="number"
						value="<c:out value='${user.total_recovered}' />" class="form-control"
						name="total_recovered" required="required">
				</fieldset>

				<button type="submit" class="btn btn-secondary btn-block">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>