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
function removeTeam(evt) {
	var destTable = $('#teams').dataTable();
    var nTr = evt.target.parentNode.parentNode;
	destTable.fnDeleteRow(nTr);
}
function addSelectedTeams() {
	var srcTable = $('#teamstable').dataTable();
	var destTable = $('#teams').dataTable();
    var aTrs = srcTable.fnGetNodes();
	$.each(aTrs, function(index, value) {
	    if ( $(value).hasClass('row_selected') )
        {
			var addElement = srcTable.fnGetData(value);
        	destTable.fnAddData(addElement);
        }
	});
}

$(document).ready(function() {
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
	$('#dialog_team').dialog({
		autoOpen: false,
		height: 500,
		width: 500,
		buttons: {
			"Ok": function() {
			 	addSelectedTeams();
				$('#dialog_team').dialog("close"); 
			}, 
			"Cancel": function() { 
				$('#dialog_team').dialog("close"); 
			} 
		}
	});
	
	// Dialog Link
	$('#team_dialog_link').click(function(){
		$.ajax({
				url: '/office-cube/team/partial',
				type: 'GET',
				success: function(data) {
					$('#teamselectdiv').html(data);
					$('#teamstable tr').click( function() {
				        if ( $(this).hasClass('row_selected') )
				            $(this).removeClass('row_selected');
				        else
				            $(this).addClass('row_selected');
		    		} );
				}
		});
		$('#dialog_team').dialog('open');
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
	$('#teams').dataTable( {
		"bPaginate": false,
		"bFilter": true,
		"bSearchable": false,
		"sScrollY": "150px",
		"sScrollX": "100%",
		"aoColumns": [
			{ "sTitle": "Name", "mDataProp": "name" },
			{ "sTitle": "Action", "mDataProp": "myKey",
			  "sClass": "center",
			  "fnRender": function(obj) {
					var sReturn = obj.aData["myKey"];
					data = "<a href=\"#\" onclick='removeTeam(evt)' id=\"team_delete\">Delete</a> <input type=\"hidden\" name=\"teams\" value=\"" + sReturn + "\">"
					return data;
				}
			}
			
		]
	} );

	$("#teamForm").validate({
				rules: {
					name:{
						remote:"/office-cube/team/validateT"
					}
			},
			messages: {name:{
				remote: jQuery.format("Team name is already in use.")
			}
		}
	});
});
</script>
<form:form class="cmxform"  id="teamForm" modelAttribute="teamBean" action="create" method="post">
	<fieldset>
		<legend>Please provide team details</legend>
			<p>
				<form:label	for="name" path="name">Name (required)</form:label> 
				<form:input path="name" class="required" minlength="5" maxlength="150"/>
			</p>
			<p>
				<form:label	for="description" path="description">Description</form:label> 
				<form:input path="description" maxlength="150"/>
			</p>
			<legend>Please select team members: </legend>	<button id="user_dialog_link">Add User</button>
			<table cellpadding="0" cellspacing="0" border="0" class="display" id="users"></table>
			<legend>Please select teams:</legend> <button id="team_dialog_link">Add Other Team</button>
			<table cellpadding="0" cellspacing="0" border="0" class="display" id="teams"></table>
			<p>	
				<input class="submit" type="submit" />
			</p>
	</fieldset>
	<div id="dialog_user" title="Users">
		<div id="userselectdiv">
		</div>
	</div>
	<div id="dialog_team" title="Teams">
		<div id="teamselectdiv">
		</div>
	</div>
	
</form:form>
