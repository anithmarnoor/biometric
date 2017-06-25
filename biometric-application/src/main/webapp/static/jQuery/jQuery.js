$('[class^=is]').hide();//Hide all the elements whose className starts with "is"

$("#roles").change(function(){          
    var value = $("#roles option:selected").val();
    var theDiv = $(".is" + value);
    
    theDiv.slideDown();
    theDiv.siblings('[class^=is]').slideUp();
});

$(function() {
	$("#dob").datepicker();
});

$(function() {
	$("#doj").datepicker();
});

$(function() {
	$("#startDate").datepicker();
});

$(function() {
	$("#endDate").datepicker();
});
