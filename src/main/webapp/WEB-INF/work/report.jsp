<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script type="text/javascript">
/* Get the rows which are currently selected */
function addSelectedUsers() {
	var srcTable = $('#userstable').dataTable();
	var destTable = $('#users').dataTable();
    var aTrs = srcTable.fnGetNodes();
	$.each(aTrs, function(index, value) {
	    if ( $(value).hasClass('row_selected') )
        {
			var addElement = srcTable.fnGetData(value);
        	destTable.fnAddData(addElement);
        }
	});
}
function removeUser(evt) {
	var destTable = $('#users').dataTable();
    var nTr = evt.target.parentNode.parentNode;
	destTable.fnDeleteRow(nTr);
}

$(document).ready(function() {
	 var dates = $( "#from, #to" ).datepicker({
			changeMonth: true,
			numberOfMonths: 2,
			dateFormat: 'dd-mm-yy',
			onSelect: function( selectedDate ) {
				var option = this.id == "from" ? "minDate" : "maxDate",
					instance = $( this ).data( "datepicker" ),
					date = $.datepicker.parseDate(
										instance.settings.dateFormat ||
										$.datepicker._defaults.dateFormat,
										selectedDate, instance.settings );
					dates.not( this ).datepicker( "option", option, date );
				}
	});
			
	// Dialog			
	$('#dialog_user').dialog({
		autoOpen: false,
		height: 500,
		width: 500,
		buttons: {
			"Ok": function() {
			 	addSelectedUsers();
				$('#dialog_user').dialog("close"); 
			}, 
			"Cancel": function() { 
				$('#dialog_user').dialog("close"); 
			} 
		}
	});
	// Dialog Link
	$('#user_dialog_link').click(function(){
		$.ajax({
				url: '/office-cube/team/user',
				type: 'GET',
				success: function(data) {
					$('#userselectdiv').html(data);
					$('#userstable tr').click( function() {
				        if ( $(this).hasClass('row_selected') )
				            $(this).removeClass('row_selected');
				        else
				            $(this).addClass('row_selected');
		    		} );
				}
		});
		$('#dialog_user').dialog('open');
		return false;
	});
	$('#users').dataTable( {
		"bPaginate": false,
		"bFilter": true,
		"bSearchable": false,
		"sScrollY": "150px",
		"sScrollX": "100%",
		"aoColumns": [
			{ "sTitle": "Corporate ID", "mDataProp": "corporateID" },
			{ "sTitle": "Username" , "mDataProp": "userName"},
			{ "sTitle": "Action", "mDataProp": "myKey",
			  "sClass": "center",
			  "fnRender": function(obj) {
					var sReturn = obj.aData["myKey"];
					data = "<a href=\"#\" onclick='removeUser(evt)' id=\"user_delete\">Delete</a> <input type=\"hidden\" name=\"users\" value=\"" + sReturn + "\">"
					return data;
				}
			}
			
		]
	} );
	$('#submit').click(function(){
		$.download('/office-cube/work/report/create', $('#reportForm').serialize(), 'GET' );
	});
});
</script>
<form:form class="cmxform"  id="reportForm" modelAttribute="reportFormBean" action="" method="get">
	<fieldset>
		<legend>Please provide team details</legend>
			<p>
				<form:label	for="from" path="from">From (required)</form:label> 
				<form:input path="from" class="required date" />
			</p>
			<p>
				<form:label	for="to" path="to">To (required)</form:label> 
				<form:input path="to" class="required date" />
			</p>
			<legend>Please select team members: </legend>	<button id="user_dialog_link">Add User</button>
			<table cellpadding="0" cellspacing="0" border="0" class="display" id="users"></table>
	</fieldset>
	
	<div id="dialog_user" title="Users">
		<div id="userselectdiv">
		</div>
	</div>
	
</form:form>
			<p>	
				<button id="submit">Submit</button>
			</p>
