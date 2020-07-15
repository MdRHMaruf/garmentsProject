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
				<strong>Success!</strong> Unit Name Save Successfully..
			</p>
		</div>
		<div class="alert alert-warning alert-dismissible fade show"
			style="display: none;">
			<p id="warningAlert" class="mb-0">
				<strong>Warning!</strong> Unit Name Empty.Please Enter Unit Name...
			</p>
		</div>
		<div class="alert alert-danger alert-dismissible fade show"
			style="display: none;">
			<p id="dangerAlert" class="mb-0">
				<strong>Wrong!</strong> Something Wrong...
			</p>
		</div>
		<input type="hidden" id="userId" value="<%=lg.get(0).getId()%>">
		<input type="hidden" id="unitId" value="0">

		<div class="card-box">
			<header class="d-flex justify-content-between">
				<h5 class="text-center" style="display: inline;">Buyer Purchase
					Order</h5>
				<button type="button" class="btn btn-outline-dark btn-sm">
					<i class="fa fa-search"></i>
				</button>
			</header>
			<hr>
			<div class="row">
				<div class="col-md-9 mb-1 pr-1">
					<div class="row">
						<div class="form-group col-md-4 mb-1" style="padding-right: 1px;">
							<label for="buyerName" class="col-form-label-sm mb-0 pb-0">Buyer
								Name:</label>
							<div class="row">
								<select id="buyerName" class="selectpicker col-md-12"
									onchange="buyerWiseStyleLoad()" data-live-search="true"
									data-style="btn-light btn-sm border-secondary">
									<option value="0">Select Buyer</option>
									<c:forEach items="${buyerList}" var="buyer">
										<option id="buyerName" value="${buyer.buyerid}">${buyer.buyername}</option>
									</c:forEach>
								</select>
							</div>


						</div>
						<div class="form-group col-md-4 mb-1"
							style="padding-left: 1px; padding-right: 1px;">
							<label for="styleNo" class="col-form-label-sm mb-0 pb-0">Style
								No:</label>
							<div class="row">
								<select id="styleNo" class="selectpicker col-md-12"
									onchange="styleWiseItemLoad()" data-live-search="true"
									data-style="btn-light btn-sm border-secondary">
									<option id="styleNo" value="0">Select Style</option>
								</select>
							</div>

						</div>
						<div class="form-group col-md-4 mb-1" style="padding-left: 1px;">
							<label for="itemType" class="col-form-label-sm mb-0 pb-0">Item
								Type:</label>
							<div class="row">
								<select id="itemType" class="selectpicker col-md-12"
									data-live-search="true"
									data-style="btn-light btn-sm border-secondary">
									<option id="itemType" value="0">Select Item Type</option>
								</select>
							</div>

						</div>
					</div>
					<div class="row">
						<div class="col-md-7">
							<div class="form-group mb-0  row">
								<label for="factory" class="col-md-2 col-form-label-sm pr-0">factory</label>

								<select id="factory" class="selectpicker col-sm-10"
									data-live-search="true"
									data-style="btn-light btn-sm border-secondary">

									<option id="factory" value="0">Select Factory</option>
									<c:forEach items="${factoryList}" var="factory">
										<option id="factory" value="${factory.factoryid}">${factory.factoryname}</option>
									</c:forEach>
								</select>

							</div>
						</div>

						<div class="col-md-5">

							<div class="form-group mb-0 row">
								<label for="color" class="col-md-2 col-form-label-sm pr-0">Color</label>

								<select id="color" class="selectpicker col-md-10"
									data-live-search="true"
									data-style="btn-light btn-sm border-secondary">

									<option id="color" value="0">Select Color</option>
									<c:forEach items="${colorList}" var="color">
										<option id="color" value="${color.colorId}">${color.colorName}</option>
									</c:forEach>
								</select>

							</div>


						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group mb-0  row">
								<label for="customerOrder "
									class="col-md-4 col-form-label-sm pr-0">Customer Order</label>
								<div class="col-sm-8">
									<input type="text" class="form-control-sm" id="customerOrder">
								</div>
							</div>
						</div>

						<div class="col-md-6">

							<div class="form-group mb-0 row">
								<label for="purchaseOrder"
									class="col-md-4 col-form-label-sm pr-0">Purchase Order</label>
								<div class="col-md-8">
									<input type="text" class="form-control-sm" id="purchaseOrder">
								</div>
							</div>


						</div>
					</div>

					<div class="form-group row mb-0">
						<label for="shippingMark" class="col-sm-2 col-form-label-sm pr-0">Shipping
							mark</label>
						<div class="col-sm-10">
							<input type="text" class="form-control-sm" id="shippingMark">
						</div>
					</div>

					<div class="row ">
						<div class="col-md-12 d-flex justify-content-end">
							<button id="btnAdd" type="button" class="btn btn-primary btn-sm" onclick="itemSizeAdd()">Add</button>
							<button id="btnEdit" type="button" class="btn btn-primary btn-sm ml-1" disabled>Edit</button>
							<button id="btnReset" type="button" class="btn btn-primary btn-sm ml-1" onclick="reset()">Reset</button>
						</div>
					</div>

				</div>
				<div class="col-md-3 mb-1 pl-1">
					<div class="row">
						<div class="col-md-12">
							<select id="sizeGroup" class="form-control-sm border-secondary"
								style="width: 100%;" onchange="sizeLoadByGroup()">
								<option id="sizeGroup" value="0">Select Size Group</option>
								<c:forEach items="${groupList}" var="group" varStatus="counter">
									<option id="sizeGroup" value="${group.groupId}">${group.groupName}</option>
								</c:forEach>
							</select>
						</div>

					</div>
					<div class="row ">
						<div class="col-md-12">
							<div class="list-group" id="listGroup"></div>
						</div>
					</div>


				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<table class="table table-hover table-bordered table-sm" >
								<thead>
									<tr>
										<th scope="col">Style</th>
										<th scope="col">Item Name</th>
										<th scope="col">Color</th>
										<th scope="col">Customer Order</th>
										<th scope="col">Purchase Order</th>
										<th scope="col">Shipping Mark</th>
										<th scope="col">Sizes Reg-Tall-N/A</th>
										<th scope="col">Total Units</th>
										<th scope="col">Unit CMT</th>
										<th scope="col">Total Price</th>
										<th scope="col">Unit FOB</th>
										<th scope="col">Total Price</th>
										
									</tr>
								</thead>
								<tbody id="dataList">
									<c:forEach items="${itemList}" var="item"
													varStatus="counter">
										
									</c:forEach>
								</tbody>
							</table>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="input-group input-group-sm mb-1">
						<div class="input-group-prepend">
							<span class="input-group-text" id="inputGroup-sizing-sm">Note</span>
						</div>
						<input type="text" class="form-control"
							aria-label="Sizing example input"
							aria-describedby="inputGroup-sizing-sm">
					</div>
				</div>
				<div class="col-md-6">
					<div class="input-group input-group-sm mb-1">
						<div class="input-group-prepend">
							<span class="input-group-text" id="inputGroup-sizing-sm">Remarks</span>
						</div>
						<input type="text" class="form-control"
							aria-label="Sizing example input"
							aria-describedby="inputGroup-sizing-sm">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<button id="btnPOSubmit" type="button" class="btn btn-primary btn-sm">Submit</button>
					<button id="btnPOEdit" type="button" class="btn btn-primary btn-sm ml-1">Edit</button>
					<button id="btnRefresh" type="button" class="btn btn-primary btn-sm ml-1">Refresh</button>
					<button id="btnPreview" type="button" class="btn btn-primary btn-sm ml-1">Preview</button>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../include/footer.jsp" />

<script
	src="${pageContext.request.contextPath}/assets/js/order/buyer-purchase-order.js"></script>
