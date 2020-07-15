<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="pg.model.wareinfo"%>
<%@page import="pg.model.module"%>
<%@page import="pg.model.login"%>
<%@page import="java.util.List"%>
<jsp:include page="../include/header.jsp" />
<%
	List<login> lg = (List<login>) session.getAttribute("pg_admin");
%>
<div class="page-wrapper">
	<div class="content container-fluid">
		<div class="alert alert-success alert-dismissible fade show"
			style="display: none;">
			<p id="successAlert" class="mb-0">
				<strong>Success!</strong> Line Name Save Successfully..
			</p>
		</div>
		<div class="alert alert-warning alert-dismissible fade show"
			style="display: none;">
			<p id="warningAlert" class="mb-0">
				<strong>Warning!</strong> Line Name Empty.Please Enter
				Line Name...
			</p>
		</div>
		<div class="alert alert-danger alert-dismissible fade show"
			style="display: none;">
			<p id="dangerAlert" class="mb-0">
				<strong>Wrong!</strong> Something Wrong...
			</p>
		</div>
		<input type="hidden" id="userId" value="<%=lg.get(0).getId()%>">
		<input type="hidden" id="lineId" value="0">

		<div class="row">
			<div class="col-sm-12 col-md-12 col-lg-12">
				<div class="card-box">
					<div class="row">
						<div class="col-sm-5 col-md-5 col-lg-5">

							<div class="row ">
								<h2>
									<b>Line Create</b>
								</h2>
							</div>
							<hr>

							<div class="form-group">
								<label for="factoryName">Factory Name:</label> <select
									class="form-control" id="factoryName" onchange="loadDepartmentByFactory()">
									<option value="0">Select Factory</option>
									<c:forEach items="${factoryList}" var="factory"
										varStatus="counter">
										<option id='factoryName' value="${factory.factoryId}">${factory.factoryName}</option>
									</c:forEach>


								</select>
							</div>
							<div class="form-group">
								<label for="departmentName">Department Name:</label> <select
									class="form-control" id="departmentName">
									<option value="0">Select Department</option>
									<c:forEach items="${departmentLis}" var="department"
										varStatus="counter">
										<option id='factoryName' value="${department.departmentId}">${department.departmentName}</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label for="lineName">Line Name:</label> <input
									type="text" class="form-control" id="lineName"
									name="text">
							</div>
							<button type="button" id="btnSave" class="btn btn-primary btn-sm"
								onclick="saveAction()">Save</button>

							<button type="button" id="btnEdit" class="btn btn-primary btn-sm"
								onclick="editAction()" disabled>Edit</button>
							<button type="button" id="btnRefresh"
								class="btn btn-primary btn-sm" onclick="refreshAction()">Refresh</button>

						</div>
						<div class="col-sm-7 col-md-7 col-lg-7 shadow ">
							<div class="input-group my-2">
								<input type="text" class="form-control"
									placeholder="Search Factory/Department/Line" aria-describedby="findButton"
									id="search" name="search">
								<div class="input-group-append">
									<button class="btn btn-primary" type="button" id="findButton">
										<i class="fa fa-search"></i>
									</button>
								</div>
							</div>
							<hr>
							<div class="row">
								<div class="col-sm-12 col-md-12 col-lg-12"
									style="overflow: auto; max-height: 600px;">
									<table class="table table-hover table-bordered table-sm">
										<thead>
											<tr>
												<th scope="col">#</th>
												<th scope="col">Factory Name</th>
												<th scope="col">Department Name</th>
												<th scope="col">Line Name</th>
												<th scope="col">edit</th>
											</tr>
										</thead>
										<tbody id="dataList">
											<c:forEach items="${lineList}" var="line"
												varStatus="counter">
												<tr>
													<td>${line.lineId}</td>
													<td id='factoryName${line.lineId}'>${line.factoryName}</td>
													<td id='departmentName${line.lineId}'>${line.departmentName}</td>
													<td id='lineName${line.lineId}'>${line.lineName}</td>
													<td><i class="fa fa-edit"
														onclick="setData(${line.factoryId},${line.departmentId},${line.lineId})"> </i></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>

							</div>

						</div>
					</div>



				</div>

			</div>


		</div>
	</div>
</div>
<jsp:include page="../include/footer.jsp" />

<script
	src="${pageContext.request.contextPath}/assets/js/register/line-create.js"></script>