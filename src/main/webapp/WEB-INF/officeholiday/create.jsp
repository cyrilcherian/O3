<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script type="text/javascript">
$(document).ready(function() {
	$("#officeHolidayForm").validate();
	$( "#date" ).datepicker( {dateFormat: 'd-m-yy'});
	$.validator.addMethod("o3date", function(value) {
			if (/^([1-9]|[012][1-9]|10|[3][01]+)-([1-9]{0,1}|[1][120]|[0][1-9])-([1-9][\d]{3})$/.test(value)){
				return true;
			} else {
				return false;
			}

	}, "Incorrect Date");
});
</script>
<form:form class="cmxform"  id="officeHolidayForm" modelAttribute="officeHoliday" action="../admin/create" method="post">
	<fieldset>
		<legend>Please provide office holiday details</legend>
			<p>
				<form:label	for="date" path="date">Date</form:label>
				<form:input id="date" path="date" class="required o3date"/>
			</p>
			<p>
				<form:label	for="description" path="description">Description</form:label> 
				<form:textarea path="description" class="required" cols="22" rows="8" maxlength="255"/>
			</p>
			<p>	
				<input class="submit" type="submit" />
			</p>
	</fieldset>
</form:form>

