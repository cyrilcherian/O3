<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script type="text/javascript">
$(document).ready(function() {
	$("#userHourLeaveForm").validate();
	$.validator.addMethod("o3date", function(value) {
			if (/^([1-9]|[012][1-9]|10|[3][01]+)-([1-9]{0,1}|[1][120]|[0][1-9])-([1-9][\d]{3})$/.test(value)){
				return true;
			} else {
				return false;
			}
		}, "Incorrect Date");
	
	$( "#hourlyStartDate" ).datepicker( {dateFormat: 'd-m-yy'});
});
</script>
<form:form class="cmxform"  id="userDayLeaveForm" modelAttribute="userLeave" method="post">
	<fieldset>
		<legend>Please provide your details</legend>
		<p>
			<form:label	path="userLeavePolicy.user.userName">Username</form:label>
			<form:input readOnly="true" id="userLeavePolicy.user.userName" path="userLeavePolicy.user.userName"/>
		</p>
		<p>
			<form:label	path="userLeavePolicy.user.corporateID">CorporateID</form:label>
			<form:input readOnly="true" id="userLeavePolicy.user.corporateID" path="userLeavePolicy.user.corporateID"/>
		</p>
		<p>
			<form:label	path="userLeavePolicy.leavePolicy.leaveType">Leave Type</form:label>
			<form:input readOnly="true" id="userLeavePolicy.leavePolicy" path="userLeavePolicy.leavePolicy.leaveType"/>
		</p>
		
		<p>
			<form:label	path="userLeavePolicy.daysLeaveAllowed">Days Leave Allowed</form:label>
			<form:input readOnly="true" id="userLeavePolicy.daysLeaveAllowed" path="userLeavePolicy.daysLeaveAllowed"/>
		</p>
		
		<p>
			<form:label	for="startDate" path="startDate">On</form:label>
			<form:input id="hourlyStartDate" path="startDate" class="required o3date"/>
		</p>
		<p>
			<form:label	for="hour" path="hour">Hours</form:label>
			<form:select path="hour" class="required">
				<form:option value="" label="--Please Select--"/>
				<form:options items="${hours}" itemValue="code" itemLabel="name"/>`
			</form:select>
		</p>
		<p>
			<form:label	for="description" path="description">Description</form:label> 
			<form:textarea path="description" class="required" cols="22" rows="8" maxlength="255"/>
		</p>
		<p>	
			<button type="submit" id="proceed" name="_eventId_proceed">Proceed</button>
			<button type="submit" class="cancel" name="_eventId_cancel" >Cancel</button>
		</p>
	</fieldset>
</form:form>

