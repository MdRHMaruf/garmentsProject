
var itemDescription = [];
function loadData() {

	
  $.ajax({
    type: 'GET',
    dataType: 'json',
    url: './itemDescriptionLoad',
    data: {},
    success: function (obj) {
    	itemDescription = [];
    	itemDescription = obj.result;
      $(function () {
        $("#itemDescription").autocomplete({
          source: itemDescription,
          select: function (event, ui) {
            document.getElementById("itemDescriptionId").value = ui.item.id;
          }
        });
      });
    }
  });
  
  //Buyer name
  var buyerList = [];
  $.ajax({
	    type: 'GET',
	    dataType: 'json',
	    url: './buyerLoad',
	    data: {},
	    success: function (obj) {
	    	buyerList = [];
	    	buyerList = obj.result;
	      $(function () {
	        $("#buyername").autocomplete({
	          source: itemDescription,
	          select: function (event, ui) {
	            document.getElementById("buyerid").value = ui.item.id;
	          }
	        });
	      });
	    }
	  });

}

window.onload = loadData;

