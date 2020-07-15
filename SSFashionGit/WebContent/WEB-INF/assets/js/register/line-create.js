
var departmentsByFactoryId  = JSON;
function loadData(){
  
    $.ajax({
        type: 'GET',
        dataType: 'json',
        url: './departmentLoadByFactory',
        data: {},
        success: function (obj) {
          departmentsByFactoryId = [];
          departmentsByFactoryId = obj.departmentList;
        }
    });
}

window.onload = loadData;
function saveAction() {
  var factoryId = $("#factoryName").val().trim();
  var departmentId = $("#departmentName").val().trim();
  var lineName = $("#lineName").val().trim();
  var userId = $("#userId").val();

  if (factoryId != '0') {
    if (departmentId != '0') {
      if (lineName != '') {
        $.ajax({
          type: 'POST',
          dataType: 'json',
          url: './saveLine',
          data: {
            lineId: "0",
            factoryId: factoryId,
            departmentId: departmentId,
            lineName: lineName,
            userId: userId
          },
          success: function (data) {
            if (data.result == "Something Wrong") {
              dangerAlert("Something went wrong");
            } else if (data.result == "duplicate") {
              dangerAlert("Duplicate Line Name..This Line Name Allreary Exist")
            } else {
              successAlert("Line Name Save Successfully");

              $("#dataList").empty();
              $("#dataList").append(drawDataTable(data.result));

            }
          }
        });
      } else {
        warningAlert("Empty Line Name... Please Enter Line Name");
      }
    } else {
      warningAlert("Empty Department Name... Please Select a Department Name");
    }
  } else {
    warningAlert("Empty Factory Name... Please Select a Factory Name");
  }
}


function editAction() {
  var lineId = $("#lineId").val().trim();
  var factoryId = $("#factoryName").val().trim();
  var departmentId = $("#departmentName").val().trim();
  var lineName = $("#lineName").val().trim();
  var userId = $("#userId").val();

  if (factoryId != '0') {
    if (departmentId != '0') {
      if (lineName != '') {

        $.ajax({
          type: 'POST',
          dataType: 'json',
          url: './editLine',
          data: {
            lineId: lineId,
            factoryId: factoryId,
            departmentId: departmentId,
            lineName: lineName,
            userId: userId
          },
          success: function (data) {
            if (data.result == "Something Wrong") {
              dangerAlert("Something went wrong");
            } else if (data.result == "duplicate") {
              dangerAlert("Duplicate Line Name..This Line Name Allreary Exist")
            } else {
              successAlert("Line Name Edit Successfully");

              $("#dataList").empty();
              $("#dataList").append(drawDataTable(data.result));

            }
          }
        });
      } else {
        warningAlert("Empty Line Name... Please Enter Line Name");
      }
    } else {
      warningAlert("Empty Department Name... Please Select a Department Name");
    }
  } else {
    warningAlert("Empty Factory Name... Please Select a Factory Name");
  }
}


function refreshAction() {
  location.reload();
  /*var element = $(".alert");
  element.hide();
  document.getElementById("lineId").value = "0";
  document.getElementById("factoryName").value = "";
  document.getElementById("lineName").value = "";
  document.getElementById("btnSave").disabled = false;
  document.getElementById("btnEdit").disabled = true;*/
}

function loadDepartmentByFactory() {
  var factoryId = $("#factoryName").val().trim();
  
  var length= departmentsByFactoryId['factId'+factoryId].length;
  var options = "<option value='0'>Select Department</option>";
  
  for(var i=0;i<length;i++){
    options += "<option value='"+departmentsByFactoryId['factId'+factoryId][i].departmentId+"'>"+departmentsByFactoryId['factId'+factoryId][i].departmentName+"</option>"
  }
  
  document.getElementById("departmentName").innerHTML = options;
}

function setData(factoryId, departmentId, lineId) {


  document.getElementById("lineId").value = lineId;
  document.getElementById("factoryName").value = factoryId;
  document.getElementById("departmentName").value = departmentId;
  document.getElementById("lineName").value = document.getElementById("lineName" + lineId).innerHTML;
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
  row.append($("<td>" + rowData.lineId + "</td>"));
  row.append($("<td id='factoryName" + rowData.lineId + "'>" + rowData.factoryName + "</td>"));
  row.append($("<td id='departmentName" + rowData.lineId + "'>" + rowData.departmentName + "</td>"));
  row.append($("<td id='lineName" + rowData.lineId + "'>" + rowData.lineName + "</td>"));
  row.append($("<td ><i class='fa fa-edit' onclick=\"setData(" + rowData.factoryId + "," + rowData.departmentId + "," + rowData.lineId + ")\"> </i></td>"));

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

