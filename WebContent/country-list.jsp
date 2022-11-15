<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Covid 19 tracker</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: grey">
			
			<div style="text-align:center" >
				<a  class="navbar-brand" class="text-center" style="color:white; font-size:25px"  > Covid 19 tracker</a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link"></a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center" >List of Countries</h3>
			<hr>
			
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Country</th>
						<th>Total Case</th>
						<th>Total Death</th>
						<th>Total recovered</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="user" items="${listUser}">

						<tr>
							<td><c:out value="${user.country}" /></td>
							<td><c:out value="${user.total_case}" /></td>
							<td><c:out value="${user.total_death}" /></td>
							<td><c:out value="${user.total_recovered}" /></td>
							<td><a class="btn btn-secondary"   href="edit?country=<c:out value='${user.country}' />">Update</a> 
								&nbsp;&nbsp;&nbsp;&nbsp; <a class="btn btn-secondary"
								href="delete?country=<c:out value='${user.country}'  />">Delete</a></td>
						</tr>
					</c:forEach>
		
				</tbody>

			</table>
			
			<div class="container text-center">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-secondary btn-block">Add
					New Country</a>
			</div>
		</div>
	</div>
</body>
</html>