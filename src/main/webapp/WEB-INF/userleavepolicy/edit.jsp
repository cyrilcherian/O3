<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script type="text/javascript">
$(document).ready(function() {
	$("#userLeavePolicyForm").validate();
});
</script>


<form:form class="cmxform"  id="userLeavePolicyForm" modelAttribute="userLeavePolicy" action="../update" method="post">
	<fieldset>
		<legend>Please provide leave policy details</legend>
			<p>
				<form:label path="user">UserName</form:label> 
				<form:label path="user">${userLeavePolicy.user.userName}</form:label>
			</p>
			<p>
				<form:label path="user">CorporateID</form:label> 
				<form:label path="user">${userLeavePolicy.user.corporateID}</form:label>
			</p>
			<p>
				<form:label path="leavePolicy">LeaveType</form:label> 
				<form:label path="leavePolicy">${userLeavePolicy.leavePolicy.leaveType}</form:label>
			</p>
			
			<p>
				<form:label	for="daysLeaveAllowed" path="daysLeaveAllowed">Days Leave Allowed</form:label> 
				<form:input path="daysLeaveAllowed" class="required number" minlength="1"/>
			</p>
			<input type="hidden" name="myKey" value="${userLeavePolicy.myKey}" />
			<p>	
				<input class="submit" type="submit" />
			</p>
	</fieldset>
</form:form>