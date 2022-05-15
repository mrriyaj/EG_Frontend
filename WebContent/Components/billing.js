$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	{
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});
		
$(document).on("click", "#clear", function(event) {

	$("#formBilling")[0].reset();
	$("#alertError").hide();
});

//SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation-------------------
	var status = validateItemForm();
	if (status != true)
	{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------
	//==============================DC-Engine FOR INSERT AND UPDATE AJAX COMMUNICATION==========================================
	var type = ($("#hidBillIDSave").val() == "") ? "POST" : "PUT";
	$.ajax(
			{
				url : "BillingAPI",
				type : type,
				data : $("#formBilling").serialize(),
				dataType : "text",
				complete : function(response, status)
				{
					if(type == "POST"){
						onItemSaveComplete(response.responseText, status);
					}
					
					else{
						onItemUpdateComplete(response.responseText, status);
					}
					
				}
			});
	
		});
		
	
function onItemSaveComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success")
		{
			
			$("#alertSuccess").text("INSERTED A BILLING RECORD SUCCESSFULLY");
			$("#alertSuccess").show();
			$("#divBillGrid").html(resultSet.data);
			
								
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error")
	{
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else
	{
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidBillIDSave").val("");
}

//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidBillIDSave").val($(this).data("billID"));
	$("#billing_id").val($(this).closest("tr").find('td:eq(0)').text());
	$("#mc_id").val($(this).closest("tr").find('td:eq(1)').text());
	$("#user_id").val($(this).closest("tr").find('td:eq(2)').text());
	$("#usege").val($(this).closest("tr").find('td:eq(3)').text());
	$("#total").val($(this).closest("tr").find('td:eq(4)').text());
	$("#status").val($(this).closest("tr").find('td:eq(5)').text());
});

function onItemUpdateComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("UPDATED A BILLING RECORD SUCCESSFULLY");
			$("#alertSuccess").show();
			$("#divBillGrid").html(resultSet.data);
						
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error")
	{
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else
	{
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidBillIDSave").val("");
}


//DELETE============================================
$(document).on("click", ".btnRemove", function(event)
//==============================DC-Engine FOR DELETE AJAX COMMUNICATION==========================================
		{
	$.ajax(
			{
				url : "BillingAPI",
				type : "DELETE",
				data : "billID=" + $(this).data("billID"),
				dataType : "text",
				complete : function(response, status)
				{
					onItemDeleteComplete(response.responseText, status);
				}
			});
		});

function onItemDeleteComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("DELETED A BILLING RECORD SUCCESSFULLY");
			$("#alertSuccess").show();
			$("#divBillGrid").html(resultSet.data);
			
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
		
	} else if (status == "error")
	{
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
		
	} else
	{
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

//CLIENT-MODEL================================================================
function validateItemForm()
{

	if ($("#billing_id").val().trim() == "")
	{
		return "Insert Billing ID.";
	}

	if ($("#mc_id").val().trim() == "")
	{
		return "Insert Meter ID.";
	}


	if ($("#user_id").val().trim() == "")
	{
		return "Insert User ID.";
	}

	var tmpusege = $("#usege").val().trim();
	if (!$.isNumeric(tmpusege))
	{
		return "Insert a numerical value for usage.";
	}

	

	var tmptotal = $("#total").val().trim();
	if (!$.isNumeric(tmptotal))
	{
		return "Insert a numerical value for Total.";
	}
	
	if ($("#status").val().trim() == "")
	{
		return "Insert a stutas.";
	}
	return true;
}



