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

	<div class="card-box m-2">
		<div class="d-flex">
			<div class="mr-auto">
				<h4 style="text-align: left;" class="font-weight-bold">
					Accessorics Indent <span class="badge badge-primary">Carton</span>
				</h4>
			</div>

			<div class="p-0">
				<div class="input-group">
					<div class="input-group-append">
						<input type="text"
							class="form-control mdb-autocomplete input-sm ml-1"
							placeholder="Search" aria-label="Search"><span
							style="height: 30px;" class="input-group-text" id="search"><i
							class="fa fa-cog" aria-hidden="true"></i></span>
					</div>
				</div>
			</div>
			<div class="p-0">
				<div class="col-sm-3 col-md-3 col-lg-3">
					<button style="height: 35px;" id="find" type="button"
						class="btn btn-primary btn-sm">Find</button>
				</div>
			</div>

		</div>
		<div class="row mt-1">
			<label style="width: 120px;" class="form-label ml-1">Purchase
				Order</label>
			<div class="col-sm-9 col-md-9 col-lg-3">
				<select class="form-control input-sm">
					<option>- Select Purchase Order -</option>
					<option>Option 1</option>
					<option>Option 2</option>
					<option>Option 3</option>
					<option>Option 4</option>
					<option>Option 5</option>
				</select>
			</div>

			<label style="width: 100px;" class="form-label ml-1">Style No</label>
			<div class="col-sm-9 col-md-9 col-lg-3">
				<select class="form-control input-sm">
					<option>- Select -</option>
					<option>Option 1</option>
					<option>Option 2</option>
					<option>Option 3</option>
					<option>Option 4</option>
					<option>Option 5</option>
				</select>
			</div>

			<label style="width: 30px;" class="form-label ml-1">Item</label>
			<div class="col-sm-9 col-md-9 col-lg-3">
				<select class="form-control input-sm">
					<option>- Select -</option>
					<option>Option 1</option>
					<option>Option 2</option>
					<option>Option 3</option>
					<option>Option 4</option>
					<option>Option 5</option>
				</select>
			</div>
		</div>

		<div class="row mt-1">
			<label style="width: 120px;" class="form-label ml-1">Accssories
				Item</label>
			<div class="col-sm-9 col-md-9 col-lg-3">
				<select class="form-control input-sm">
					<option>- Select -</option>
					<option>Option 1</option>
					<option>Option 2</option>
					<option>Option 3</option>
					<option>Option 4</option>
					<option>Option 5</option>
				</select>
			</div>

			<label style="width: 100px;" class="form-label ml-1">Shipping
				Mark</label>
			<div class="col-sm-9 col-md-9 col-lg-3">
				<select class="form-control input-sm">
					<option>- Select -</option>
					<option>Option 1</option>
					<option>Option 2</option>
					<option>Option 3</option>
					<option>Option 4</option>
					<option>Option 5</option>
				</select>
			</div>

			<label style="width: 30px;" class="form-label ml-1">Color</label>
			<div class="col-sm-9 col-md-9 col-lg-3">
				<select class="form-control input-sm">
					<option>- Select -</option>
					<option>Option 1</option>
					<option>Option 2</option>
					<option>Option 3</option>
					<option>Option 4</option>
					<option>Option 5</option>
				</select>
			</div>

		</div>

		<div class="row mt-1">

			<div style="width: 120px;" class="form-check ml-1">
				<input class="form-check-input" type="checkbox" value=""
					id="sizeRequired"> <label class="form-check-label"
					for="checkbox">Size Is Require</label>
			</div>
			<div class="col-sm-9 col-md-9 col-lg-3">
				<select class="form-control input-sm">
					<option>- Select -</option>
					<option>Option 1</option>
					<option>Option 2</option>
					<option>Option 3</option>
					<option>Option 4</option>
					<option>Option 5</option>
				</select>
			</div>

			<label style="width: 100px;" class="form-label ml-1">Order
				QTY</label>
			<div class="col-sm-9 col-md-9 col-lg-3">
				<input id="orderQty" type="text" class="form-control input-sm">
			</div>
		</div>

		<div class="row mt-2">
			<label style="width: 80px; margin-left: 43px;" class="form-label">Length</label>
			<div class="col-sm-2">
				<input id="length" type="text" class="form-control input-sm">
			</div>

			<label style="width: 40px;" class="form-label">Width</label>
			<div class="col-sm-2">
				<input id="width" type="text" class="form-control input-sm">
			</div>

			<label style="width: 40px; margin-left: 25px;" class="form-label">Height</label>
			<div class="col-sm-2">
				<input id="height" type="text" class="form-control input-sm">
			</div>

			<label style="width: 33px;" class="form-label">Add</label>
			<div class="col-sm-2">
				<input id="add" type="text" class="form-control input-sm">
			</div>
		</div>

		<div class="row mt-1">
			<label style="width: 80px; margin-left: 43px;" class="form-label">Length</label>
			<div class="col-sm-2">
				<input id="length" type="text" class="form-control input-sm">
			</div>

			<label style="width: 40px;" class="form-label">Width</label>
			<div class="col-sm-2">
				<input id="width" type="text" class="form-control input-sm">
			</div>

			<label style="width: 40px; margin-left: 25px;" class="form-label">Height</label>
			<div class="col-sm-2">
				<input id="height" type="text" class="form-control input-sm">
			</div>

			<label style="width: 33px;" class="form-label">Add</label>
			<div class="col-sm-2">
				<input id="add" type="text" class="form-control input-sm">
			</div>
		</div>

		<div class="row mt-1">
			<label style="width: 80px; margin-left: 43px;" class="form-label">Divide
				By</label>
			<div class="col-sm-2">
				<input id="devideBy" type="text" class="form-control input-sm">
			</div>
			<label style="width: 40px;" class="form-label">Ply</label>
			<div class="col-sm-2">
				<input id="ply" type="text" class="form-control input-sm">
			</div>
			<label style="width: 40px; margin-left: 25px;" class="form-label">QTY</label>
			<div class="col-sm-2">
				<input id="qty" type="text" class="form-control input-sm">
			</div>
		</div>

		<div class="row mt-1">

			<label style="width: 80px; margin-left: 43px;" class="form-label">Carton
				Size</label>
			<div class="col-sm-2">
				<input id="cartonSize" type="text" class="form-control input-sm">
			</div>

			<label style="width: 40px;" class="form-label">Unit</label>
			<div class="col-sm-2">
				<input id="unit" type="text" class="form-control input-sm">
			</div>
		</div>

		<div class="row mt-1">
			<div class="col-sm-12">
				<button style="background: green;" id="save"
					class="btn btn-primary btn-sm">Save</button>
				<button id="edit" class="btn btn-secondary btn-sm">Edit</button>
				<button id="refresh" class="btn btn-danger btn-sm">Refresh</button>
				<button id="pereview" class="btn btn-primary btn-sm">Preview</button>
			</div>
		</div>





		<div class="row mt-3">
			<div style="overflow: auto; max-height: 300px;" class="col-sm-12">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th style="width: 15px;">Sl#</th>
							<th>ID</th>
							<th>Style no</th>
							<th>Item Name</th>
							<th>Color Name</th>
							<th>Shipping Marks</th>
							<th>Accssories Name</th>
							<th>Size</th>
							<th>Total Required</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>

	</div>
</div>




<jsp:include page="../include/footer.jsp" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/Registration/FactroyCreate.js"></script>
