<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script type="text/javascript">
$(document).ready(function() {
	$("#leavePolicyForm").validate();
});
</script>


<form:form class="cmxform"  id="leavePolicyForm" modelAttribute="leavePolicy" action="../admin/create" method="post">
	<fieldset>
		<legend>Please provide leave details</legend>
			<p>
				<form:label	for="leaveType" path="leaveType">Leave Type (required)</form:label> 
				<form:input path="leaveType" class="required" minlength="2" maxlength="150"/>
			</p>
			<p>
				<form:label	for="description" path="description">Description</form:label> 
				<form:textarea path="description" cols="22" rows="8" maxlength="255"/>
			</p>
			<p>	
				<input class="submit" type="submit" />
			</p>
	</fieldset>
</form:form>
