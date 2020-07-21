
function selectStyle(v){
	//var itemList = [];
	var styleId=v;
	
    $.ajax({  
    	url: './getStyleWiseItem/'+styleId,
    	method:"GET",  
    	dataType: 'json',
        success: function (data) {
        	
        	  $('#itemname').empty();
        	
        	  var itemList = data.result;
              var options = "<option id='itemname' value='0' selected>Select Item Type</option>";
              var length = itemList.length;
              for(var i=0;i<length;i++) {
                  options += "<option id='itemname' value='"+itemList[i].id+"'>"+itemList[i].value+"</option>";
              };
              document.getElementById("itemname").innerHTML = options;
              $('.selectpicker').selectpicker('refresh');
              $('#itemname').val("0").change();
              
/*        	  $('#itemname').append($('<option>Select Name</option>'));
        	  $.each(data.result, function (i, p) {
        		  $('#itemname').append($('<option></option>').val(p.id).html(p.value));
        		});
        	  */
        	//  $('.selectpicker').selectpicker('refresh');
        	  
        	//itemList = [];
           // itemList = data.result;
/*            $("#itemname").autocomplete({
                  source: itemList,
                  select: function (event, ui) {
                    //document.getElementById("sizeGroupId").value = ui.item.id;
                  }
            });*/
    
            
        
           // $('#itemname').append($("<option id="'+result.id+'">'+ result.value +'</option>')");

        },
	    error: function (e) {
	        alert("ERROR : ", e);
	    }
   });  
    
}


