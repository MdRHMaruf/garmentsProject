

var sizesListByGroup = JSON;
function loadData() {

  $.ajax({
    type: 'GET',
    dataType: 'json',
    url: './sizesLoadByGroup',
    data: {},
    success: function (obj) {
      sizesListByGroup = [];
      sizesListByGroup = obj.sizeList;
    }
  });

  $.ajax({
    type: 'GET',
    dataType: 'json',
    url: './getBuyerPOItems',
    data: {
      buyerPoNo : "0"
    },
    success: function (data) {
      if (data.result == "Something Wrong") {
        dangerAlert("Something went wrong");
      } else if (data.result == "duplicate") {
        dangerAlert("Duplicate Item Name..This Item Name Already Exist")
      } else {
         drawItemTable(data.itemList);
      }
    }
  });

}

window.onload = loadData;

function buyerWiseStyleLoad() {
  var buyerId = $("#buyerName").val();
  $.ajax({
    type: 'GET',
    dataType: 'json',
    url: './getBuyerWiseStylesItem',
    data: {
      buyerId: buyerId
    },
    success: function (data) {

      var styleList = data.styleList;
      var options = "<option id='styleNo' value='0' selected>Select Style</option>";
      var length = styleList.length;
      for (var i = 0; i < length; i++) {
        options += "<option id='styleNo' value='" + styleList[i].styleId + "'>" + styleList[i].styleNo + "</option>";
      };
      document.getElementById("styleNo").innerHTML = options;
      $('.selectpicker').selectpicker('refresh');
      $('#styleNo').val("0").change();
      $('#itemType').val("0").change();
    }
  });
}

function styleWiseItemLoad() {
  var styleId = $("#styleNo").val();
  $.ajax({
    type: 'GET',
    dataType: 'json',
    url: './getStyleWiseItem',
    data: {
      styleId: styleId
    },
    success: function (data) {

      var itemList = data.itemList;
      var options = "<option id='itemType' value='0' selected>Select Item Type</option>";
      var length = itemList.length;
      for (var i = 0; i < length; i++) {
        options += "<option id='itemType' value='" + itemList[i].itemId + "'>" + itemList[i].itemName + "</option>";
      };
      document.getElementById("itemType").innerHTML = options;
      $('.selectpicker').selectpicker('refresh');
      $('#itemType').val("0").change();
    }
  });
}

function itemSizeAdd() {

  var buyerPOId = $("#buyerPOId").val();
  var buyerId = $("#buyerName").val();
  var styleId = $("#styleNo").val();
  var itemId = $("#itemType").val();
  var factoryId = $("#factory").val();
  var colorId = $("#color").val();
  var sizeGroupId = $("#sizeGroup").val();
  var customerOrder = $("#customerOrder").val();
  var purchaseOrder = $("#purchaseOrder").val();
  var shippingMark = $("#shippingMark").val();
  var userId = $("#userId").val();
  var totalUnit = 0;


  if (buyerId != 0) {
    if (styleId != 0) {
      if (itemId != 0) {
        if (factoryId != 0) {
          if (colorId != 0) {
            if (sizeGroupId != 0) {
              var sizeListLength = $(".sizeValue").length;
              var sizeList = "";
              for (var i = 0; i < sizeListLength; i++) {
                var quantity = $("#sizeValue" + i).val().trim() == "" ? "0" : $("#sizeValue" + i).val().trim();
                var id = $("#sizeId" + i).val().trim();
                sizeList += "id=" + id + ",quantity=" + quantity + " ";
                totalUnit += Number(quantity);
              }


              $.ajax({
                type: 'POST',
                dataType: 'json',
                url: './addItemToBuyerPO',
                data: {
                  autoId: "0",
                  buyerPOId: buyerPOId,
                  buyerId: buyerId,
                  styleId: styleId,
                  itemId: itemId,
                  factoryId: factoryId,
                  colorId: colorId,
                  customerOrder: customerOrder,
                  purchaseOrder: purchaseOrder,
                  shippingMark: shippingMark,
                  sizeGroupId: sizeGroupId,
                  sizeListString: sizeList,
                  totalUnit: totalUnit,
                  unitCmt: 1,
                  totalPrice: 0,
                  unitFob: 1,
                  totalAmount: 1,
                  userId: userId
                },
                success: function (data) {
                  if (data.result == "Something Wrong") {
                    dangerAlert("Something went wrong");
                  } else if (data.result == "duplicate") {
                    dangerAlert("Duplicate Item Name..This Item Name Allreary Exist")
                  } else {

                    console.log(data.itemList);
                    drawItemTable(data.itemList);
                  }
                }
              });

            } else {
              warningAlert("Size Group not selected ... Please Select Size group");
              $("#sizeGroup").focus();
            }
          } else {
            warningAlert("Color Not Selected... Please Select Color");
            $("#color").focus();
          }
        } else {
          warningAlert("Factory not selected... Please Select Factory Name");
          $("#factoryId").focus();
        }
      } else {
        warningAlert("Item Type not selected... Please Select Item Type");
        $("#itemType").focus();
      }
    } else {
      warningAlert("Style No not selected... Please Select Style No");
      $("#styleNo").focus();
    }
  } else {
    warningAlert("Buyer Name not selected... Please Select Buyer Name");
    $("#buyerName").focus();
  }

}



function submitAction() {
  var buyerId = $("#buyerName").val();
  var paymentTerm = $("#paymentTerm").val();
  var currency = $("#currency").val();
  var totalUnit = 0;
  var unitCmt = 0;
  var totalPrice = 0;
  var unitFob = 0;
  var totalAmount = 0;
  var note = $("#note").val();
  var remarks = $("#remarks").val();
  var userId = $("#userId").val();



  if (buyerId != 0) {
    
    $.ajax({
      type: 'POST',
      dataType: 'json',
      url: './submitBuyerPO',
      data: {
        buyerPoId: "0",
        buyerId: buyerId,
        paymentTerm: paymentTerm,
        currency: currency,
        totalUnit: totalUnit,
        unitCmt: unitCmt,
        totalPrice: totalPrice,
        unitFob: unitFob,
        totalAmount: totalAmount,
        note: note,
        remarks: remarks,
        userId: userId
      },
      success: function (data) {
        if (data.result == "Something Wrong") {
          dangerAlert("Something went wrong");
        } else if (data.result == "duplicate") {
          dangerAlert("Duplicate Buyer Name..This Unit Name Already Exist")
        } else {
          successAlert("Buyer Purchase Order Save Successfully");
        }
      }
    });
  } else {
    warningAlert("Buyer Name not selected... Please Select Buyer Name");
    $("#buyerName").focus();
  }
}


function editAction() {
  var unitId = $("#unitId").val();
  var unitName = $("#unitName").val().trim();
  var unitValue = $("#unitValue").val().trim();
  var userId = $("#userId").val();

  if (unitName != '') {
    $.ajax({
      type: 'POST',
      dataType: 'json',
      url: './editUnit',
      data: {
        unitId: unitId,
        unitName: unitName,
        unitValue: unitValue,
        userId: userId
      },
      success: function (data) {
        if (data.result == "Something Wrong") {
          dangerAlert("Something went wrong");
        } else if (data.result == "duplicate") {
          dangerAlert("Duplicate Unit Name..This Unit Name Allreary Exist")
        } else {
          successAlert("Unit Name Edit Successfully");

          $("#dataList").empty();
          $("#dataList").append(drawDataTable(data.result));

        }
      }
    });
  } else {
    warningAlert("Empty Unit Name... Please Enter Unit Name");
  }
}

function searchBuyerPO(buyerPoNo){
  
    $.ajax({
      type: 'GET',
      dataType: 'json',
      url: './getBuyerPO',
      data: {
        buyerPoNo : buyerPoNo
      },
      success: function (data) {
        if (data.result == "Something Wrong") {
          dangerAlert("Something went wrong");
        } else if (data.result == "duplicate") {
          dangerAlert("Duplicate Unit Name..This Unit Name Allreary Exist")
        } else {
            console.log(data.buyerPO);
            var buyerPo = data.buyerPO;
            $("#buyerName").val(buyerPo.buyerId).change();
            $("#paymentTerm").val(buyerPo.paymentTerm).change();
            $("#currency").val(buyerPo.currency).change();
            $("#note").val(buyerPo.note);
            $("#remarks").val(buyerPo.remarks);

            drawItemTable(buyerPo.itemList);
            $('.modal').modal('hide')
        }
      }
    });
}

function reset() {
  var element = $(".alert");
  element.hide();
  $("#sizeGroup").val("0").change();
}

function refreshAction() {
  location.reload();
  /*var element = $(".alert");
  element.hide();
  document.getElementById("unitId").value = "0";
  document.getElementById("unitName").value = "";
  document.getElementById("unitValue").value = "";
  document.getElementById("btnSave").disabled = false;
  document.getElementById("btnEdit").disabled = true;*/
}

function sizeLoadByGroup() {

  var groupId = $("#sizeGroup").val().trim();

  var listGroup = document.getElementById("listGroup");
  var child = "";
  if (groupId != "0") {
    var length = sizesListByGroup['groupId' + groupId].length;
    for (var i = 0; i < length; i++) {
      //child += " <div class=\"list-group-item pt-0 pb-0 sizeNameList\"> <div class=\"form-group row mb-0\"><label for=\"sizeId" + sizesListByGroup['groupId' + groupId][i].sizeId + "\" class=\"col-md-6 col-form-label-sm pb-0 mb-0\" style=\"height:25px;\">" + sizesListByGroup['groupId' + groupId][i].sizeName + "</label><input type=\"number\" class=\"form-control-sm col-md-6\" id=\"sizeValue" + sizesListByGroup['groupId' + groupId][i].sizeId + "\" style=\"height:25px;\"></div></div>";
      child += " <div class=\"list-group-item pt-0 pb-0\"> <div class=\"form-group row mb-0\"><label for=\"sizeValue" + i + "\" class=\"col-md-6 col-form-label-sm pb-0 mb-0\" style=\"height:25px;\">" + sizesListByGroup['groupId' + groupId][i].sizeName + "</label><input type=\"number\" class=\"form-control-sm col-md-6 sizeValue\" id=\"sizeValue" + i + "\" style=\"height:25px;\"> <input type=\"hidden\" id=\"sizeId" + i + "\" value=" + sizesListByGroup['groupId' + groupId][i].sizeId + "></div></div>";
    }
  }
  listGroup.innerHTML = child;
}
function setData(unitId) {


  document.getElementById("unitId").value = unitId;
  document.getElementById("unitName").value = document.getElementById("unitName" + unitId).innerHTML;
  document.getElementById("unitValue").value = document.getElementById("unitValue" + unitId).innerHTML;
  document.getElementById("btnSave").disabled = true;
  document.getElementById("btnEdit").disabled = false;

}

function drawItemTable(dataList){
  var length = dataList.length;   
        sizeGroupId = "";
        var tables = "";
        var isClosingNeed = false;
        for (var i = 0; i < length; i++) {
          var item = dataList[i];
          console.log(item);
          if (sizeGroupId != item.sizeGroupId) {
            if(isClosingNeed){
              tables += "</tbody></table> </div></div>";
            }
            sizeGroupId = item.sizeGroupId;
            tables += `<div class="row">
                        <div class="col-md-12 table-responsive" >
              <table class="table table-hover table-bordered table-sm mb-0 min-width-100">
              <thead class="no-wrap-text bg-light">
                <tr>
                  <th scope="col">Style</th>
                  <th scope="col">Item Name</th>
                  <th scope="col">Color</th>
                  <th scope="col">Customer Order</th>
                  <th scope="col">Purchase Order</th>
                  <th scope="col">Shipping Mark</th>
                  <th scope="col">Sizes Reg-Tall-N/A</th>`
                  var sizeListLength = sizesListByGroup['groupId' + sizeGroupId].length;
                  for (var j = 0; j < sizeListLength; j++) {
                    tables += "<th class=\"min-width-100 mx-auto\"scope=\"col\">"+sizesListByGroup['groupId' + sizeGroupId][j].sizeName+"</th>";
                  }
                  tables+=`<th scope="col">Total Units</th>
                  <th scope="col">Unit CMT</th>
                  <th scope="col">Total Price</th>
                  <th scope="col">Unit FOB</th>
                  <th scope="col">Total Price</th>
                  <th scope="col"><i class="fa fa-edit"></i></th>
                  <th scope="col"><i class="fa fa-trash"></i></th>
                </tr>
              </thead>
              <tbody id="dataList">`
              isClosingNeed = true;
          }
          tables += "<tr><td>"+item.styleId+"</td><td>"+item.itemId+"</td><td>"+item.colorId+"</td><td>"+item.customerOrder+"</td><td>"+item.purchaseOrder+"</td><td>"+item.shippingMark+"</td><td>"+item.sizeReg+"</td>"
              var sizeList = item.sizeList;
              var sizeListLength = sizeList.length;
              for(var j=0;j<sizeListLength;j++){
                tables += "<td>"+sizeList[j].sizeQuantity+"</td>"
              }
          tables += "<td class='totalUnit' id='totalUnit"+item.autoId+"'>"+item.totalUnit+"</td><td class='unitCmt' id='unitCmt"+item.autoId+"'>"+item.unitCmt+"</td><td class='totalPrice' id='totalPrice"+item.autoId+"'>"+item.totalPrice+"</td><td class='unitFob' id='unitFob"+item.autoId+"'>"+item.unitFob+"</td><td class='totalAmount' id='totalAmount"+item.autoId+"'>"+item.totalAmount+"</td><td><i class='fa fa-edit' onclick='setData()'> </i></td><td><i class='fa fa-trash' onclick='setData()'> </i></td></tr>";

        }
        tables += "</tbody></table> </div></div>";

        document.getElementById("tableList").innerHTML = tables;
}

function drawDataTable(data) {
  var rows = [];
  var length = data.length;

  for (var i = 0; i < length; i++) {
    rows.push(drawRowDataTable(data[i], i));
  }

  return rows;
}

function drawRowDataTable(rowData, c) {

  var row = $("<tr />")
  row.append($("<td>" + rowData.unitId + "</td>"));
  row.append($("<td id='unitName" + rowData.unitId + "'>" + rowData.unitName + "</td>"));
  row.append($("<td id='unitValue" + rowData.unitId + "'>" + rowData.unitValue + "</td>"));
  row.append($("<td ><i class='fa fa-edit' onclick=\"setData(" + rowData.unitId + ")\"> </i></td>"));

  return row;
}

function successAlert(message) {
  var element = $(".alert");
  element.hide();
  element = $(".alert-success");
  document.getElementById("successAlert").innerHTML = "<strong>Success!</strong> " + message + "...";
  element.show();
}

function warningAlert(message) {
  var element = $(".alert");
  element.hide();
  element = $(".alert-warning");
  document.getElementById("warningAlert").innerHTML = "<strong>Warning!</strong> " + message + "..";
  element.show();
}

function dangerAlert(message) {
  var element = $(".alert");
  element.hide();
  element = $(".alert-danger");
  document.getElementById("dangerAlert").innerHTML = "<strong>Duplicate!</strong> " + message + "..";
  element.show();
}

$(document).ready(function () {
  $("input:text").focus(function () { $(this).select(); });
});

$(document).ready(function () {
  $("#search").on("keyup", function () {
    var value = $(this).val().toLowerCase();
    $("#poList tr").filter(function () {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});



