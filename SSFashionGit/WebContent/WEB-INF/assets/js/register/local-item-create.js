



function saveAction() {
  var localItemName = $("#localItemName").val().trim();
  var localItemCode = $("#localItemCode").val().trim();
  var userId = $("#userId").val();

  if (localItemName != '') {
    $.ajax({
      type: 'POST',
      dataType: 'json',
      url: './saveLocalItem',
      data: {
        localItemId: "0",
        localItemName: localItemName,
        localItemCode: localItemCode,
        userId: userId
      },
      success: function (data) {
        if (data.result == "Something Wrong") {
          dangerAlert("Something went wrong");
        } else if (data.result == "duplicate") {
          dangerAlert("Duplicate Loca lItem Name..This Local Item Name Allreary Exist")
        } else {
          successAlert("Local Item Name Save Successfully");

          $("#dataList").empty();
          $("#dataList").append(drawDataTable(data.result));

        }
      }
    });
  } else {
    warningAlert("Empty Local Item Name... Please Enter Local Item Name");
  }
}


function editAction() {
  var localItemId = $("#localItemId").val();
  var localItemName = $("#localItemName").val().trim();
  var localItemCode = $("#localItemCode").val().trim();
  var userId = $("#userId").val();

  if (localItemName != '') {
    $.ajax({
      type: 'POST',
      dataType: 'json',
      url: './editLocalItem',
      data: {
        localItemId: localItemId,
        localItemName: localItemName,
        localItemCode: localItemCode,
        userId: userId
      },
      success: function (data) {
        if (data.result == "Something Wrong") {
          dangerAlert("Something went wrong");
        } else if (data.result == "duplicate") {
          dangerAlert("Duplicate Local Item Name..This Local Item Name Allreary Exist")
        } else {
          successAlert("Local Item Name Edit Successfully");

          $("#dataList").empty();
          $("#dataList").append(drawDataTable(data.result));

        }
      }
    });
  } else {
    warningAlert("Empty Local Item Name... Please Enter Local Item Name");
  }
}


function refreshAction() {
  location.reload();
  /*var element = $(".alert");
  element.hide();
  document.getElementById("localItemId").value = "0";
  document.getElementById("localItemName").value = "";
  document.getElementById("localItemCode").value = "";
  document.getElementById("btnSave").disabled = false;
  document.getElementById("btnEdit").disabled = true;*/
}


function setData(localItemId) {


  document.getElementById("localItemId").value = localItemId;
  document.getElementById("localItemName").value = document.getElementById("localItemName" + localItemId).innerHTML;
  document.getElementById("localItemCode").value = document.getElementById("localItemCode" + localItemId).innerHTML;
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
  row.append($("<td>" + rowData.localItemId + "</td>"));
  row.append($("<td id='localItemName" + rowData.localItemId + "'>" + rowData.localItemName + "</td>"));
  row.append($("<td id='localItemCode" + rowData.localItemId + "'>" + rowData.localItemCode + "</td>"));
  row.append($("<td ><i class='fa fa-edit' onclick=\"setData(" + rowData.localItemId + ")\"> </i></td>"));

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
  document.getElementById("warningAlert").innerHTML = "<strong>Warning!</strong> "+message+"..";
  element.show();
}

function dangerAlert(message) {
  var element = $(".alert");
  element.hide();
  element = $(".alert-danger");
  document.getElementById("dangerAlert").innerHTML = "<strong>Duplicate!</strong> "+message+"..";
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

