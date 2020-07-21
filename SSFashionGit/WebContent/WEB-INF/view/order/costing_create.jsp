<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="pg.model.wareinfo"%>
<%@page import="pg.model.module"%>
<%@page import="pg.model.login"%>
<%@page import="java.util.List"%>
<jsp:include page="../include/header.jsp" />


<div class="page-wrapper">
	<div class="m-2">
		<div class="row">
			<div class="col-md-12">
				<div class="card-box pt-1">
					<form
						class="form-inline d-flex justify-content-center md-form form-sm active-cyan-2">
						<div class="input-group-append">
							<input type="text" class="form-control mdb-autocomplete input-sm"
							placeholder="Search" aria-label="Search">
							<span class="input-group-text" id="search"><i
								class="fas fa-search" aria-hidden="true"></i></span>
						</div>

						<div class="col-sm-3 col-md-3 col-lg-3">
							<button style="height: 38px;" type="button"
								class="btn btn-primary" data-toggle="modal"
								data-target="#allcostingstyle">All Costing Style</button>
						</div>
					</form>

					<div class="modal fade" id="allcostingstyle" tabindex="-1"
						role="dialog" aria-hidden="true">
						<div class="modal-dialog modal-md" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLongTitle">All
										Costing Style</h5>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>


								<div class="modal-body">
									<div class="row">
										<div style="overflow: auto; max-height: 300px;"
											class="col-sm-12">
											<table class="table table-bordered">
												<thead>
													<tr>
														<th style="width: 15px;">Sl#</th>
														<th>Style</th>
														<th>Check</th>
													</tr>
												</thead>
											</table>
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-dismiss="modal">Close</button>
								</div>
							</div>
						</div>
					</div>


					<div class="card-box card-margin-bottom-ten mt-1 pt-1 pb-1">
						<div class="row">
							<label style="text-align: center; width: 107px;" class="form-level">Style
								No</label>
							<div class="col-sm-10 col-md-9 col-lg-3">
								<select id="styleNo" onchange="selectStyle(this.value)" name="styleNo" class="selectpicker col-md-12"  data-live-search="true" data-style="btn-light border-secondary">
										<option name="styleNo" id="styleNo" value="0">Select Style No</option>
		 								<c:forEach items="${styleList}" var="slist" varStatus="counter">
											<option name="styleNo"  id="styleNo" value="${slist.styleId}">${slist.styleNo}</option>
										</c:forEach>
									</select>
							</div>

							<label style="text-align: center; width: 107px;" class="form-level">Item
								Name</label>
							<div class="col-sm-10 col-md-9 col-lg-3">
								<select id="itemName"  name="itemName" class="selectpicker col-md-12"  data-live-search="true" data-style="btn-light border-secondary">
										<option name="itemName" id="itemName" value="0">Select Style No</option>
										
									</select>
							</div>

							<button style="height: 35px; width: 120px;"
								class="btn btn-danger" type="button">Install From</button>

						</div>
					</div>

					<div class="card-box card-margin-bottom-ten pt-1 pb-1">

						<div class="row">
							<label style="text-align: center; width: 107px;" class="form-label">Style
								Name</label>
							<div class="col-sm-10 col-md-9 col-lg-3">
								<input type="search" id="stylename"
									class="form-control mdb-autocomplete input-sm">
							</div>

							<label style="text-align: center; width: 107px;" class="form-label">Item
								Name</label>
							<div class="col-sm-10 col-md-9 col-lg-3">
								<input type="search" id="itemname"
									class="form-control mdb-autocomplete input-sm">
							</div>

							<label style="text-align: center; width: 107px;" class="form-label">Date</label>
							<div class="col-sm-10 col-md-9 col-lg-2">
								<input type="date" id="date" class="form-control input-sm">
							</div>

						</div>

						<div class="row mt-1">
							<label style="text-align: center; width: 107px;" class="form-label">Group</label>
							<div class="col-sm-10 col-md-9 col-lg-3">
								<select class="form-control input-sm">
									<option>-- Select --</option>
									<option>Option 1</option>
									<option>Option 2</option>
									<option>Option 3</option>
									<option>Option 4</option>
									<option>Option 5</option>
								</select>
							</div>

							<label style="text-align: center; width: 107px;" class="form-label">Comission</label>
							<div class="col-sm-10 col-md-9 col-lg-3">
								<input type="text" id="comission" placeholder=".00%"
									class="form-control input-sm">
							</div>

						</div>

						<div class="row mt-1">
							<label style="text-align: center; width: 107px;" class="form-label">Particular
								Name</label>
							<div class="col-sm-10 col-md-9 col-lg-4">
								<input type="text" id="particularname" class="form-control input-sm">
							</div>

							<label style="text-align: right; width: 58px;" class="form-label">Unit</label>
							<div class="col-sm-10 col-md-9 col-lg-3">
								<select class="form-control input-sm col-sm-10">
									<option>-- Select Unit --</option>
									<option>kg</option>
									<option>Option 2</option>
									<option>Option 3</option>
									<option>Option 4</option>
									<option>Option 5</option>
								</select>
							</div>

						</div>

						<div class="row mt-2">
							<label style="text-align: center; width: 105px;" class="form-label">Width</label>
							<div class="col-sm-10 col-md-9 col-lg-1">
								<input type="text" id="width" class="form-control input-sm">
							</div>

							<label style="text-align: center; width: 80px;" class="form-label">Yard</label>
							<div class="col-sm-10 col-md-9 col-lg-1">
								<input type="text" id="yard" class="form-control input-sm">
							</div>

							<label style="text-align: center; width: 80px;" class="form-label">GSM</label>
							<div class="col-sm-10 col-md-9 col-lg-1">
								<input type="text" id="gsm" class="form-control input-sm">
							</div>

							<label style="text-align: center;" class="form-label">Consumption</label>
							<div class="col-sm-10 col-md-9 col-lg-1">
								<input type="text" id="consumption" class="form-control input-sm">
							</div>

							<label style="text-align: center; width: 80px;" class="form-label">Unit
								Price</label>
							<div class="col-sm-10 col-md-9 col-lg-1">
								<input type="text" id="unit" class="form-control input-sm">
							</div>

							<button style="height: 35px; width: 80px; background:green;" class="btn btn-primary"
								type="button">Save</button>

						</div>

					</div>

					
						<div class="row">
							<div style="overflow: auto; max-height: 300px;" class="col-sm-12">
								<table class="table table-bordered">
									<thead>
										<tr>
											<th style="width: 15px;">Sl#</th>
											<th>ID</th>
											<th>Style</th>
											<th>Fabrication</th>
											<th>Size</th>
											<th>Width</th>
											<th>Yard</th>
											<th>GSM</th>
											<th>CONS/DOZEN</th>
											<th>Rate</th>
											<th>Amount</th>
											<th>Edit</th>
											<th>Delete</th>
										</tr>
									</thead>
								</table>
							</div>
						</div>
					
				</div>
			</div>
		</div>
	</div>
</div>


<jsp:include page="../include/footer.jsp" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/order/costing_create.js"></script>
