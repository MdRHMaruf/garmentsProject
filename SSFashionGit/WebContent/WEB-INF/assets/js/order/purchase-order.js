var styleIdForSet = 0;
var itemIdForSet = 0;
var indentIdForSet = 0;
function poWiseStyleLoad() {
  var purchaseOrder = $("#purchaseOrder").val();

  if (purchaseOrder != "0") {
    $.ajax({
      type: 'GET',
      dataType: 'json',
      url: './getPOWiseStyleLoad',
      data: {
        purchaseOrder: purchaseOrder
      },
      success: function (data) {

        var styleList = data.styleList;
        var options = "<option id='styleNo' value='0' selected>Select Style No</option>";
        var length = styleList.length;
        for (var i = 0; i < length; i++) {
          options += "<option id='styleNo' value='" + styleList[i].styleId + "'>" + styleList[i].styleNo + "</option>";
        };
        document.getElementById("styleNo").innerHTML = options;
        $('#styleNo').selectpicker('refresh');
        $('#styleNo').val(styleIdForSet).change();
        styleIdForSet = 0;
      }
    });
  } else {
    var options = "<option id='styleNo' value='0' selected>Select Style No</option>";
    $("#styleNo").html(options);
    $('#styleNo').selectpicker('refresh');
    $('#styleNo').val(0).change();
    styleIdForSet = 0;
  }
}

function typeWiseIndentItemLoad(){
  var type = $("#type").val();
  var purchaseOrder = $("#purchaseOrder").val();
  var styleId = $("#styleNo").val();
  if(type != "0"){ 
    if(type == "3"){

    }else{
      $.ajax({
        type: 'GET',
        dataType: 'json',
        url: './getTypeWiseIndentItems',
        data: {
          purchaseOrder : purchaseOrder,
          styleId : styleId,
          type : type
        },
        success: function (data) {
  
          var itemList = data.itemList;
          var options = "<option id='indentItem' value='0' selected>--Select Indent Item--</option>";
          var length = itemList.length;
          for (var i = 0; i < length; i++) {
            options += "<option id='indentItem' value='" + itemList[i].accessoriesItemId + "'>" + itemList[i].accessoriesItemName + "</option>";
          };
          document.getElementById("indentItem").innerHTML = options;
          $('#indentItem').selectpicker('refresh');
          $('#indentItem').val(indentIdForSet).change();
          indentIdForSet = 0;
        }
      });
    }
    
  }else{
    var options = "<option id='indentItem' value='0' selected>--Select Indent Item--</option>";
    $("#indentItem").html(options);
    $('#indentItem').selectpicker('refresh');
    $('#indentItem').val(0).change();
    indentIdForSet = 0;
  }
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
                  totalPrice: 1,
                  unitFob: 1,
                  totalAmount: 1,
                  userId: userId
                },
                success: function (data) {
                  if (data.result == "Something Wrong") {
                    dangerAlert("Something went wrong");
                  } else if (data.result == "duplicate") {
                    dangerAlert("Duplicate Item Name..This Item Name Already Exist")
                  } else {
                    drawItemTable(data.result);
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

function itemSizeEdit() {

  var buyerPOId = $("#buyerPOId").val();
  var itemAutoId = $("#itemAutoId").val();
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
                url: './editBuyerPoItem',
                data: {
                  autoId: itemAutoId,
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
                  totalPrice: 1,
                  unitFob: 1,
                  totalAmount: 1,
                  userId: userId
                },
                success: function (data) {
                  if (data.result == "Something Wrong") {
                    dangerAlert("Something went wrong");
                  } else if (data.result == "duplicate") {
                    dangerAlert("Duplicate Item Name..This Item Name Already Exist")
                  } else {
                    drawItemTable(data.result);
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
  var buyerPoId = $("#buyerPOId").val();
  var buyerId = $("#buyerName").val();
  var paymentTerm = $("#paymentTerm").val();
  var currency = $("#currency").val();

  var totalRow = $(".totalUnit");

  var totalUnit = 0;
  var unitCmt = 0;
  var totalPrice = 0;
  var unitFob = 0;
  var totalAmount = 0;

  for(var i = 0;i<totalRow.length;i++){
      var autoId =  $('#itemRow'+i).data('id');
      totalUnit += Number($("#totalUnit"+autoId).text());
      unitCmt += Number($("#unitCmt"+autoId).text());
      totalPrice += Number($("#totalPrice"+autoId).text());
      unitFob += Number($("#unitFob"+autoId).text());
      totalAmount += Number($("#totalAmount"+autoId).text());
  }

  var note = $("#note").val();
  var remarks = $("#remarks").val();
  var userId = $("#userId").val();



  if (buyerId != 0) {

    $.ajax({
      type: 'POST',
      dataType: 'json',
      url: './submitBuyerPO',
      data: {
        buyerPoId: buyerPoId,
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


function buyerPoEditAction() {

  var buyerPoId = $("#buyerPOId").val();
  var buyerId = $("#buyerName").val();
  var paymentTerm = $("#paymentTerm").val();
  var currency = $("#currency").val();
  var totalRow = $(".totalUnit");

  var totalUnit = 0;
  var unitCmt = 0;
  var totalPrice = 0;
  var unitFob = 0;
  var totalAmount = 0;
  for(var i = 0;i<totalRow.length;i++){
    var autoId =  $('#itemRow'+i).data('id');
    totalUnit += Number($("#totalUnit"+autoId).text());
    unitCmt += Number($("#unitCmt"+autoId).text());
    totalPrice += Number($("#totalPrice"+autoId).text());
    unitFob += Number($("#unitFob"+autoId).text());
    totalAmount += Number($("#totalAmount"+autoId).text());
}

  var note = $("#note").val();
  var remarks = $("#remarks").val();
  var userId = $("#userId").val();

  console.log("Edit function call");
  if (buyerPoId != "0") {
    if (buyerId != 0) {

      $.ajax({
        type: 'POST',
        dataType: 'json',
        url: './editBuyerPO',
        data: {
          buyerPoId: buyerPoId,
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
            successAlert("Buyer Purchase Order Edit Successfully");
          }
        }
      });
    } else {
      warningAlert("Buyer Name not selected... Please Select Buyer Name");
      $("#buyerName").focus();
    }
  } else {
    warningAlert("Something Wrong... Buyer Purchase Order Id not found");
    $("#buyerName").focus();
  }

}

function searchBuyerPO(buyerPoNo) {

  $.ajax({
    type: 'GET',
    dataType: 'json',
    url: './getBuyerPO',
    data: {
      buyerPoNo: buyerPoNo
    },
    success: function (data) {
      if (data.buyerPO == "Something Wrong") {
        dangerAlert("Something went wrong");
      } else if (data.buyerPO == "duplicate") {
        dangerAlert("Duplicate Unit Name..This Unit Name Already Exist")
      } else {
        
        var buyerPo = data.buyerPO;
        console.log(buyerPo);
        $("#buyerPOId").val(buyerPo.buyerPoId);
        $("#buyerName").val(buyerPo.buyerId).change();
        $("#paymentTerm").val(buyerPo.paymentTerm).change();
        $("#currency").val(buyerPo.currency).change();
        $("#note").val(buyerPo.note);
        $("#remarks").val(buyerPo.remarks);

        drawItemTable(buyerPo.itemList);
        $('.modal').modal('hide')
        $("#btnPOSubmit").prop("disabled", true);
        $("#btnPOEdit").prop("disabled", false);
        $("#btnPreview").prop("disabled", false);
      }
    }
  });
}

function setBuyerPoItemDataForEdit(itemAutoId) {
  $.ajax({
    type: 'GET',
    dataType: 'json',
    url: './getBuyerPOItem',
    data: {
      itemAutoId: itemAutoId
    },
    success: function (data) {
      if (data.result == "Something Wrong") {
        dangerAlert("Something went wrong");
      } else if (data.result == "duplicate") {
        dangerAlert("Duplicate Unit Name..This Unit Name Already Exist")
      } else {
        
        var item = data.poItem;
        console.log(item);
        $("#itemAutoId").val(item.autoId);
        $("#customerOrder").val(item.customerOrder);
        $("#purchaseOrder").val(item.purchaseOrder);
        $("#shippingMark").val(item.shippingMark);
        $("#factory").val(item.factoryId).change();
        $("#color").val(item.colorId).change();

        sizeValueListForSet = item.sizeList;
        $("#sizeGroup").val(item.sizeGroupId).change();

        $("#itemAutoId").val(itemAutoId);
        $("#btnAdd").prop("disabled", true);
        $("#btnEdit").prop("disabled", false);

        styleIdForSet = item.styleId;
        itemIdForSet = item.itemId;
        $("#buyerName").val(item.buyerId).change();

        
      }
    }
  });
  
}

function deleteBuyerPoItem(itemAutoId) {

  var buyerPoId = $("#buyerPOId").val();
  if (confirm("Are you sure to Delete this item")) {
    $.ajax({
      type: 'GET',
      dataType: 'json',
      url: './deleteBuyerPoItem',
      data: {
        buyerPoId: buyerPoId,
        itemAutoId: itemAutoId

      },
      success: function (data) {
        if (data.result == "Something Wrong") {
          dangerAlert("Something went wrong");
        } else if (data.result == "duplicate") {
          dangerAlert("Duplicate Unit Name..This Unit Name Already Exist")
        } else {
          drawItemTable(data.result);
          $('.modal').modal('hide');
          $("#btnPOSubmit").prop("disabled", true);
          $("#btnPOEdit").prop("disabled", false);
          $("#btnPreview").prop("disabled", false);
        }
      }
    });
  }

}

function reset() {
  var element = $(".alert");
  element.hide();
  $("#sizeGroup").val("0").change();
  $("#itemAutoId").val("0");
  $("#btnAdd").prop("disabled", false);
  $("#btnEdit").prop("disabled", true);
}

function refreshAction() {
  location.reload();
  
}

function sizeLoadByGroup() {

  var groupId = $("#sizeGroup").val().trim();
  var child = "";
  var length = 0;
  if (groupId != "0") {
    length = sizesListByGroup['groupId' + groupId].length;
    for (var i = 0; i < length; i++) {
      //child += " <div class=\"list-group-item pt-0 pb-0 sizeNameList\"> <div class=\"form-group row mb-0\"><label for=\"sizeId" + sizesListByGroup['groupId' + groupId][i].sizeId + "\" class=\"col-md-6 col-form-label-sm pb-0 mb-0\" style=\"height:25px;\">" + sizesListByGroup['groupId' + groupId][i].sizeName + "</label><input type=\"number\" class=\"form-control-sm col-md-6\" id=\"sizeValue" + sizesListByGroup['groupId' + groupId][i].sizeId + "\" style=\"height:25px;\"></div></div>";
      child += " <div class=\"list-group-item pt-0 pb-0\"> <div class=\"form-group row mb-0\"><label for=\"sizeValue" + i + "\" class=\"col-md-6 col-form-label-sm pb-0 mb-0\" style=\"height:25px;\">" + sizesListByGroup['groupId' + groupId][i].sizeName + "</label><input type=\"number\" class=\"form-control-sm col-md-6 sizeValue\" id=\"sizeValue" + i + "\" style=\"height:25px;\"> <input type=\"hidden\" id=\"sizeId" + i + "\" value=" + sizesListByGroup['groupId' + groupId][i].sizeId + "></div></div>";
    }

  }
  $("#listGroup").html(child);
  if(sizeValueListForSet.length>0){
    for(var i =0 ;i<length;i++){
      $("#sizeValue"+i).val(sizeValueListForSet[i].sizeQuantity);
    }
    sizeValueListForSet = [];
  }
  
}
function setData(unitId) {


  document.getElementById("unitId").value = unitId;
  document.getElementById("unitName").value = document.getElementById("unitName" + unitId).innerHTML;
  document.getElementById("unitValue").value = document.getElementById("unitValue" + unitId).innerHTML;
  document.getElementById("btnSave").disabled = true;
  document.getElementById("btnEdit").disabled = false;

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
  $("input").focus(function () { $(this).select(); });
});
$(document).ready(function () {
  $("#search").on("keyup", function () {
    var value = $(this).val().toLowerCase();
    $("#poList tr").filter(function () {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});



