

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
}

window.onload = loadData;

function buyerWiseStyleLoad() {
  var buyerId = $("#buyerName").val();
  $.ajax({
    type: 'GET',
    dataType: 'json',
    url: './getBuyerWiseStylesItem',
    data:{
      buyerId : buyerId
    },
    success: function(data){
      
      var styleList = data.styleList;
      var options = "<option id='styleNo' value='0' selected>Select Style</option>";
      var length = styleList.length;
      for(var i=0;i<length;i++) {
          options += "<option id='styleNo' value='"+styleList[i].styleId+"'>"+styleList[i].styleNo+"</option>";
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
    data:{
      styleId : styleId
    },
    success: function(data){
      
      var itemList = data.itemList;
      var options = "<option id='itemType' value='0' selected>Select Item Type</option>";
      var length = itemList.length;
      for(var i=0;i<length;i++) {
          options += "<option id='itemType' value='"+itemList[i].itemId+"'>"+itemList[i].itemName+"</option>";
      };
      document.getElementById("itemType").innerHTML = options;
      $('.selectpicker').selectpicker('refresh');
      $('#itemType').val("0").change();
    }
  });
}

function itemSizeAdd(){
    var buyerId = $("#buyerName").val();
    var styleId = $("#styleNo").val();
    var itemId = $("#itemType").val();
    var factoryId = $("#factory").val();
    var colorId = $("#color").val();
    var sizeGroup = $("#sizeGroup").val();

    if(buyerId != 0){
      if(styleId != 0){
        if(itemId != 0){
          if(factoryId != 0){
            if(colorId != 0){
              if(sizeGroup != 0){

              }else{
                warningAlert("Size Group not selected ... Please Select Size group");
                $("#sizeGroup").focus();
              }
            }else{
              warningAlert("Color Not Selected... Please Select Color");
              $("#color").focus();
            }
          }else{
            warningAlert("Factory not selected... Please Select Factory Name");
            $("#factoryId").focus();
          }
        }else{
          warningAlert("Item Type not selected... Please Select Item Type");
          $("#itemType").focus();
        }
      }else{
        warningAlert("Style No not selected... Please Select Style No");
        $("#styleNo").focus();
      }
    }else{
      warningAlert("Buyer Name not selecte... Please Select Buyer Name");
      $("#buyerName").focus();
    }
    
}

function saveAction() {
  var unitName = $("#unitName").val().trim();
  var unitValue = $("#unitValue").val().trim();
  var userId = $("#userId").val();

  if (unitName != '') {
    $.ajax({
      type: 'POST',
      dataType: 'json',
      url: './saveUnit',
      data: {
        unitId: "0",
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
          successAlert("Unit Name Save Successfully");

          $("#dataList").empty();
          $("#dataList").append(drawDataTable(data.result));

        }
      }
    });
  } else {
    warningAlert("Empty Unit Name... Please Enter Unit Name");
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

function reset(){
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
      child += " <div class=\"list-group-item pt-0 pb-0\"> <div class=\"form-group row mb-0\"><label for=\"sizeId" + sizesListByGroup['groupId' + groupId][i].sizeId + "\" class=\"col-md-6 col-form-label-sm pb-0 mb-0\" style=\"height:25px;\">" + sizesListByGroup['groupId' + groupId][i].sizeName + "</label><input type=\"number\" class=\"form-control-sm col-md-6\" id=\"sizeId" + sizesListByGroup['groupId' + groupId][i].sizeId + "\" style=\"height:25px;\"></div></div>";
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
    $("#dataList tr").filter(function () {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});



