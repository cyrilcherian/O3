<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script type="text/javascript">
$(document).ready(function() {
	$("#userLeavePolicyForm").validate();
	$('#userstable').html( '<table cellpadding="0" cellspacing="0" border="0" class="display" id="example"></table>' );
	$('#example').dataTable( {
		"bPaginate": false,
		"bFilter": true,
		"bSearchable": false,
		"aoColumns": [
			{ "sTitle": "Corporate ID", "mDataProp": "corporateID" },
			{ "sTitle": "Username" , "mDataProp": "userName"},
			{ "sTitle": "First Name" , "mDataProp": "firstName"},
			{ "sTitle": "Last Name" , "mDataProp": "lastName"},
			{ "sTitle": "Action", "mDataProp": "myKey",
			  "sClass": "center",
			  "fnRender": function(obj) {
					var sReturn = obj.aData["myKey"];
					data = "<input type=\"checkbox\" name=\"users\" value=\"" + sReturn + "\">"
					return data;
				}
			}
			
		]
	} );
	$('#example').dataTable().fnUpdate(${userJsonData});	
});
</script>
<form:form class="cmxform"  id="userLeavePolicyForm" modelAttribute="userLeavePolicy" action="create" method="post">
	<fieldset>
		<legend>Please provide leave policy details</legend>
			<p>
				<form:label	for="daysLeaveAllowed" path="daysLeaveAllowed">Days Leave Allowed</form:label> 
				<form:input path="daysLeaveAllowed" class="required number" minlength="1"/>
			</p>
			<p>
				<form:label	for="leavePolicy" path="leavePolicy">Leave Policies</form:label>
				<form:select path="leavePolicy" class="required">
					<form:option value="" label="--Please Select--"/>
					<form:options items="${leavePolicies}" itemValue="myKey" itemLabel="leaveType"/>`
				</form:select>
			</p>
			<div id="userstable"></div>
			<p>	
				<input class="submit" type="submit" />
			</p>
	</fieldset>
</form:form>
