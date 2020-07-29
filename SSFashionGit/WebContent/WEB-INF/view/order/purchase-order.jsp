<%@page import="pg.share.Currency"%>
<%@page import="pg.share.PaymentType"%>
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
	<input type="hidden" id="poAutoId" value="0">

	<div class="card-box">
		<header class="d-flex justify-content-between">
			<h5 class="text-center" style="display: inline;">Purchase Order</h5>
			<button type="button" class="btn btn-outline-dark btn-sm"
				data-toggle="modal" data-target="#exampleModal">
				<i class="fa fa-search"></i>
			</button>
		</header>
		<hr class="my-1">
		<div class="row">
			<div class="col-md-4">
				<div class="form-group mb-0  row">
					<label for="orderDate"
						class="col-md-4 col-form-label-sm pr-0 mb-1 pb-1">Order
						Date</label> <input id="orderDate" type="date"
						class="col-md-8 form-control-sm">

				</div>
				<div class="form-group mb-0  row">
					<label for="deliveryDate"
						class="col-md-4 col-form-label-sm pr-0 mb-1 pb-1">Delivery
						Date</label> <input id="deliveryDate" type="date"
						class="col-md-8 form-control-sm">

				</div>
				<div class="form-group mb-0  row">
					<label for="purchaseOrder"
						class="col-md-4 col-form-label-sm pr-0 mb-1 pb-1">Purchase
						Order</label> <select id="purchaseOrder" onchange="poWiseStyleLoad()"
						class="selectpicker col-md-8 px-0" data-live-search="true"
						data-style="btn-light btn-sm border-light-gray">
						<option id="purchaseOrder" value="0">Select Purchase
							Order</option>
						<c:forEach items="${poList}" var="po">
							<option id="purchaseOrder" value="${po}">${po}</option>
						</c:forEach>
					</select>

				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group mb-0  row">
					<label for="deliveryTo"
						class="col-md-3 col-form-label-sm pr-0 mb-1 pb-1">Delivery
						To</label> <select id="deliveryTo"
						class="selectpicker col-md-9 px-0" data-live-search="true"
						data-style="btn-light btn-sm border-light-gray">
						<option id="deliveryTo" value="0">--- Select --- </option>
						<c:forEach items="${factoryList}" var="factory">
							<option id="deliveryTo" value="${factory.factoryId}">${factory.factoryName}</option>
						</c:forEach>
					</select>

				</div>
				<div class="form-group mb-0  row">
					<label for="orderBy"
						class="col-md-3 col-form-label-sm pr-0 mb-1 pb-1">Order By</label>
					<select id="orderBy"
						class="selectpicker col-md-9 px-0" data-live-search="true"
						data-style="btn-light btn-sm border-light-gray">
						<option id="orderBy" value="0">--- Select --- </option>
						<c:forEach items="${merchendiserList}" var="merchandiser">
							<option id="deliveryTo" value="${merchandiser.merchendiserId}">${merchandiser.name}</option>
						</c:forEach>
					</select>

				</div>
				<div class="form-group mb-0  row">
					<label for="billTo"
						class="col-md-3 col-form-label-sm pr-0 mb-1 pb-1">Bill To</label>
					<select id="billTo"
						class="selectpicker col-md-9 px-0" data-live-search="true"
						data-style="btn-light btn-sm border-light-gray">
						<option id="billTo" value="0">--- Select --- </option>
						<c:forEach items="${factoryList}" var="factory">
							<option id="deliveryTo" value="${factory.factoryId}">${factory.factoryName}</option>
						</c:forEach>
					</select>

				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group mb-0  row">
					<label for="manualPO"
						class="col-md-4 col-form-label-sm pr-0 mb-1 pb-1">Manual
						PO</label> <input id="manualPO" type="text"
						class="col-md-8 form-control-sm">

				</div>
				<div class="form-group mb-0  row">
					<label for="paymentType"
						class="col-md-4 col-form-label-sm pr-0 mb-1 pb-1">Payment
						Type</label> 
						<select id="paymentType" 
						class="form-control-sm col-md-8 px-0">
						<option id="paymentType" value="0">Select Purchase Order</option>
						<% 		
							int length = PaymentType.values().length;
							for(int i=0;i<length;i++){			
						%>
							<option id="paymentType" value="<%=PaymentType.values()[i].getType() %>"><%=PaymentType.values()[i].name()%></option>
						<% }%>
					</select>

				</div>
				<div class="form-group mb-0  row">
					<label for="currency"
						class="col-md-4 col-form-label-sm pr-0 mb-1 pb-1">Currency</label>
					<select id="currency" class="form-control-sm col-md-8 px-0">
						<option id="currency" value="0">Select Purchase Order</option>
						<% 
							length = Currency.values().length;
							for(int i=0;i<length;i++){			
						%>
							<option id="currency" value="<%=Currency.values()[i].getType() %>"><%=Currency.values()[i].name()%></option>
						<% }%>
					</select>

				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-2 pr-0 pl-1">
				<label for="styleNo" class="col-form-label-sm my-0 py-0">Style
					No</label> <select id="styleNo"
					class="selectpicker col-md-12 px-0" data-live-search="true"
					data-style="btn-light btn-sm border-light-gray">
					<option id="styleNo" value="0">Select Style</option>
				</select>
			</div>

			<div class="col-md-1 pr-0 pl-1">
				<label for="type" class="col-form-label-sm my-0 py-0">Type</label> <select
					id="type" onchange="typeWiseIndentItemLoad()"
					class="form-control-sm col-md-12 px-0">
					<option id="type" value="0">Select Type</option>
					<option id="type" value="1">Fabrics</option>
					<option id="type" value="2">Accessories</option>
					<option id="type" value="3">Curton</option>
				</select>
			</div>

			<div class="col-md-3 pr-0 pl-1">
				<label for="indentItem" class="col-form-label-sm my-0 py-0">Indent
					Item</label> <select id="indentItem" 
					class="selectpicker col-md-12 px-0" data-live-search="true"
					data-style="btn-light btn-sm border-light-gray">
					<option id="indentItem" value="0">--Select Indent Item--</option>
				</select>
			</div>

			<div class="form-group col-md-3 mb-1  pr-0 pl-1">
				<label for="supplierName" class="col-form-label-sm my-0 py-0">Supplier
					Name</label>
				<div class="row">
					<select id="supplierName" class="selectpicker col-md-12"
						data-live-search="true"
						data-style="btn-light btn-sm border-light-gray"
						onchange="unitChangeAction()">
						<option id="supplierName" value="0">--Select SupplierName--</option>
						<c:forEach items="${supplierList}" var="supplier">
							<option id="supplierName" value="${supplier.supplierid}">${supplier.suppliername}</option>
						</c:forEach>
					</select>
				</div>

			</div>

			<div class="col-md-3 pr-0">
				<div class="row">
					<div class="col-md-4 pr-0 pl-1">
						<label for="rate" class="col-form-label-sm my-0 py-0">Rate</label>
						<input id="rate" type="number" class="form-control-sm pr-0 pl-1">
					</div>
					<div class="col-md-4 pr-0 pl-1">
						<label for="dollar" class="col-form-label-sm my-0 py-0">Dollar</label>
						<input id="dollar" type="number" class="form-control-sm pr-0 pl-1">
					</div>
					<div class="col-md-4 pr-0 pl-1">

						<button id="btnAdd" type="button" style="margin-top:1.3rem;" class="btn btn-primary btn-sm"
							onclick="indentItemAdd()">
							<i class="fa fa-plus-circle"></i> Add
						</button>
					</div>

				</div>

			</div>

		</div>
		<hr class="my-1">
		<div class="row mt-1">
			<div style="overflow: auto; max-height: 300px;"
				class="col-sm-12 px-1 table-responsive">
				<table
					class="table table-hover table-bordered table-sm mb-0 small-font">
					<thead class="no-wrap-text">
						<tr>

							<th>P.O.</th>
							<th>Style</th>
							<th>Item Name</th>
							<th>Color Name</th>
							<th>Fabrices Item</th>
							<th>Dozen</th>
							<th>Consumption</th>
							<th>%QTY</th>
							<th>Total QTY</th>
							<th>Unit</th>
							<th><i class="fa fa-info-circle"></i></th>
						</tr>
					</thead>
					<tbody id="dataList">
						<c:forEach items="${fabricsIndentList}" var="indent"
							varStatus="counter">
							<tr>
								<td>${indent.purchaseOrder}</td>
								<td>${indent.styleName}</td>
								<td>${indent.itemName}</td>
								<td>${indent.itemColorName}</td>
								<td>${indent.fabricsName}</td>
								<td>${indent.dozenQty}</td>
								<td>${indent.consumption}</td>
								<td>${indent.percentQty}</td>
								<td>${indent.totalQty}</td>
								<td>${indent.unit}</td>
								<td><i class="fa fa-info-circle"
									onclick="viewFabricsIndent(${indent.autoId})"
									style="cursor: pointer;"></i></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>


		<div class="row mt-1">
			<div class="col-md-6">
				<div class="input-group input-group-sm mb-1">
					<div class="input-group-prepend">
						<span class="input-group-text" id="inputGroup-sizing-sm">Note</span>
					</div>
					<input id="note" type="text" class="form-control"
						aria-label="Sizing example input"
						aria-describedby="inputGroup-sizing-sm">
				</div>
			</div>
			<div class="col-md-6">
				<div class="input-group input-group-sm mb-1">
					<div class="input-group-prepend">
						<span class="input-group-text" id="inputGroup-sizing-sm">Subject</span>
					</div>
					<input id="subject" type="text" class="form-control"
						aria-label="Sizing example input"
						aria-describedby="inputGroup-sizing-sm">
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 d-flex justify-content-end">
				<button id="btnPOSubmit" type="button"
					class="btn btn-primary btn-sm" onclick="submitAction()">
					<i class="fas fa-save"></i> Submit
				</button>
				<button id="btnPOEdit" type="button"
					class="btn btn-primary btn-sm ml-1" onclick="buyerPoEditAction()"
					disabled>
					<i class="fa fa-pencil-square"></i> Edit
				</button>
				<button id="btnRefresh" type="button"
					class="btn btn-primary btn-sm ml-1" onclick="refreshAction()">
					<i class="fa fa-refresh"></i> Refresh
				</button>
				<button id="btnPreview" type="button"
					class="btn btn-primary btn-sm ml-1" disabled>
					<i class="fa fa-print"></i> Preview
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<div class="input-group">
					<input id="search" type="text" class="form-control"
						placeholder="Search Buyer Purchase Order"
						aria-label="Recipient's username" aria-describedby="basic-addon2">
					<div class="input-group-append">
						<span class="input-group-text"><i class="fa fa-search"></i></span>
					</div>
				</div>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<table class="table table-hover table-bordered table-sm mb-0">
					<thead>
						<tr>
							<th>PO Id</th>
							<th>Buyer Name</th>
							<th>Date</th>
							<th><span><i class="fa fa-search"></i></span></th>
						</tr>
					</thead>
					<tbody id="poList">
						<c:forEach items="${buyerPoList}" var="po" varStatus="counter">
							<tr>
								<td>${po.buyerPoId}</td>
								<td id='buyerName${po.buyerPoId}'>${po.buyerName}</td>
								<td>${po.date}</td>
								<td><i class="fa fa-search"
									onclick="searchBuyerPO(${po.buyerPoId})"> </i></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

		</div>
	</div>
</div>

<jsp:include page="../include/footer.jsp" />

<script
	src="${pageContext.request.contextPath}/assets/js/order/purchase-order.js"></script>
