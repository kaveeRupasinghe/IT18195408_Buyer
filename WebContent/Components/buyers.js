$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-------------------
var status = validateBuyerForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "BuyersAPI",
 type : type,
 data : $("#formbUYER").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onBuyerSaveComplete(response.responseText, status);
 
}
 });
});

function onBuyerSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divBuyersGrid").html(resultSet.data);
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
 $("#hidItemIDSave").val("");
 $("#formBuyer")[0].reset();
}

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
$("#hidItemIDSave").val($(this).data("bno")); 
 $("#buyerID").val($(this).closest("tr").find('td:eq(0)').text());
 $("#name").val($(this).closest("tr").find('td:eq(3)').text());
 $("#address").val($(this).closest("tr").find('td:eq(1)').text());
 $("#phone").val($(this).closest("tr").find('td:eq(2)').text());
 $("#email").val($(this).closest("tr").find('td:eq(3)').text());
 $("#projectName").val($(this).closest("tr").find('td:eq(3)').text());

});

//DELETE==========================================
$(document).on("click", ".btnRemove", function(event)
{
 $.ajax(
 {
 url : "BuyersAPI",
 type : "DELETE",
 data : "bno=" + $(this).data("bno"),
 dataType : "text",
 complete : function(response, status)
 {
 onItemDeleteComplete(response.responseText, status);
 }
 });
});

function onBuyerDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divBuyersGrid").html(resultSet.data);
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

// CLIENT-MODEL================================================================
function validateBuyerForm()
{
// CODE
if ($("#buyerID").val().trim() == "")
 {
 return "Insert Buyer ID.";
 }
// NAME
if ($("#name").val().trim() == "")
 {
 return "Insert Name.";
 }

// ADDRESS-------------------------------
if ($("#address").val().trim() == "")
 {
 return "Insert Address.";
 }
// phone-------------------------------
if ($("#phone").val().trim() == "")
 {
 return "Insert phone number.";
 }
// email-------------------------------
if ($("#email").val().trim() == "")
 {
 return "Insert email.";
 }
// projectName------------------------
if ($("#projectName").val().trim() == "")
 {
 return "Insert project Name.";
 }
return true;
}