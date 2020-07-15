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
			<p id="successAlert" class="mb-0"><strong>Success!</strong> Unit Name Save Successfully..</p>
		</div>
		<div class="alert alert-warning alert-dismissible fade show"
			style="display: none;">
			<p id="warningAlert" class="mb-0"><strong>Warning!</strong> Unit Name Empty.Please Enter Unit Name...</p>
		</div>
		<div class="alert alert-danger alert-dismissible fade show"
			style="display: none;">
			<p id="dangerAlert" class="mb-0"><strong>Wrong!</strong> Something Wrong...</p>
		</div>
		<input type="hidden" id="userId" value="<%=lg.get(0).getId()%>">
		<input type="hidden" id="unitId" value="0">
	 	<input type="hidden" id="itemDescriptionId" value="0">
	 	<input type="hidden" id="buyerid" value="0">

		<div class="row">
			<div class="col-sm-12 col-md-12 col-lg-12">
				<div class="card-box">
					<div class="row">
						<div class="col-sm-6 col-md-6 col-lg-6">

							<div class="row ">
								<h2>
									<b>Style Create</b>
								</h2>
							</div>
							<hr>
								<div class="input-group">
									<input type="text" class="form-control" id="buyername"
										placeholder="Select Buyer Name"
										aria-label="Recipient's username"
										aria-describedby="button-addon2">
									<div class="input-group-append">
										<button class="btn btn-outline-secondary" type="button"
											id="groupSettingButton">
											<i class="fa fa-gear"></i>
										</button>
									</div>
								</div>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label for="unitValue">Style No:</label> 
										<input type="text" class="form-control" id="itemdescription" name="text">
									</div>							
								</div>							
								<div class="col-md-6">
									<div class="form-group">
										<label for="unitValue">Date:</label> 									
										<div class="input-group date" data-provide="datepicker">
												    <input type="date" class="form-control">
										</div>
									</div>						
								</div>									
							</div>	

							<div class="form-group">
								<label for="sizeGroupName">Item Description:</label>
								<div class="input-group">
									<input id="itemDescription" type="text" class="form-control"
										placeholder="Select Item Name" onfocus="setSizeGroupId('0')">
									<div class="input-group-append">
										<button class="btn btn-outline-secondary" type="button"
											id="groupSettingButton" data-toggle="modal"
											data-target="#exampleModal">
											<i class="fa fa-gear"></i>
										</button>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label for="unitValue">Front Image</label> 
										<input type="submit" class="form-control text-left" id="fontimage" value="Browse">
										 <a href="index.php"><img src="assets/images/user.jpg" alt="Preadmin"></a>
									</div>								
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label for="unitValue">Back Image</label> 
										<input  type="submit" class="form-control text-left" id="backimage" value="Browse">
										  <a href="index.php"><img src="assets/images/user.jpg" alt="Preadmin"></a>
									</div>								
								</div>
							</div>												
							<button type="button" id="btnSave" class="btn btn-primary btn-sm"
								onclick="saveAction()">Save</button>

							<button type="button" id="btnEdit" class="btn btn-primary btn-sm" onclick="editAction()"
								disabled>Edit</button>
							<button type="button" id="btnRefresh"
								class="btn btn-primary btn-sm" onclick="refreshAction()">Refresh</button>

						</div>
						<div class="col-sm-6 col-md-6 col-lg-6 shadow ">
							<div class="input-group my-2">
								<input type="text" class="form-control"
									placeholder="Search Style" aria-describedby="findButton"
									id="search" name="search">
								<div class="input-group-append">
									<button class="btn btn-primary" type="button" id="findButton">
										<i class="fa fa-search"></i>
									</button>
								</div>
							</div>
							<hr>
							<div class="row" >
								<div class="col-sm-12 col-md-12 col-lg-12" style="overflow: auto; max-height: 600px;">
								<table class="table table-hover table-bordered table-sm" >
								<thead>
									<tr>
										<th scope="col">#</th>
										<th scope="col">Style No</th>
										<th scope="col">Item Description</th>
										<th scope="col">Edit</th>
									</tr>
								</thead>
								<tbody id="dataList">
									<c:forEach items="${unitList}" var="unit"
													varStatus="counter">
										<tr>
											<td>${unit.unitId}</td>
											<td id='unitName${unit.unitId}'>${unit.unitName}</td>
											<td id='unitValue${unit.unitId}'>${unit.unitValue}</td>
											<td><i class="fa fa-edit" onclick="setData(${unit.unitId})"> </i></td>
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

			<script>
			$('.datepicker').datepicker({
			    format: 'mm/dd/yyyy',
			    startDate: '-3d'
			});
			</script>
<script
	src="${pageContext.request.contextPath}/assets/js/order/style-create.js"></script>