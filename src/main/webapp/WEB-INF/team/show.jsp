<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script type="text/javascript">
/* Get the rows which are currently selected */

$(document).ready(function() {
	$('#users').dataTable( {
		"bPaginate": false,
		"bFilter": true,
		"bSearchable": false,
		"sScrollY": "150px",
		"sScrollX": "100%",
		"aaData":  ${users},
		"aoColumns": [
			{ "sTitle": "Corporate ID", "mDataProp": "corporateID" },
			{ "sTitle": "Username" , "mDataProp": "userName"},
			
		]
	} );
	$('#teams').dataTable( {
		"bPaginate": false,
		"bFilter": true,
		"bSearchable": false,
		"sScrollY": "150px",
		"sScrollX": "100%",
		"aaData":  ${teams},
		"aoColumns": [
			{ "sTitle": "Name", "mDataProp": "name" },
			{ "sTitle": "Action", "mDataProp": "myKey",
			  "sClass": "center",
			  "fnRender": function(obj) {
					var sReturn = obj.aData["myKey"];
					data = "<a href=\"/office-cube/team/show/"+sReturn+"\">Show</a>";
					return data;
				}
			}
			
		]
	} );

});
</script>
<form:form class="cmxform"  id="teamForm" modelAttribute="team"  action="/office-cube/team/update" method="post">
	<fieldset>
		<legend>Please provide team details</legend>
			<p>
				<form:label	for="name" path="name">Name (required)</form:label> 
				<form:input path="name" class="required" disabled = "true" minlength="5" maxlength="150"/>
			</p>
			<p>
				<form:label	for="description" path="description">Description</form:label> 
				<form:input path="description" disabled = "true" maxlength="150"/>
			</p>
			<legend>Please select team members: </legend>
			<table cellpadding="0" cellspacing="0" border="0" class="display" id="users"></table>
			<legend>Please select teams:</legend> 
			<table cellpadding="0" cellspacing="0" border="0" class="display" id="teams"></table>
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