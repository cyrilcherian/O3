<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script type="text/javascript">
$(document).ready(function() {
	$("#weekDaysHolidayForm").validate();
});
</script>
<form:form class="cmxform"  id="weekDaysHolidayForm" modelAttribute="weekHoliday" action="create" method="post">
	<fieldset>
		<legend>Please provide office week holiday details</legend>
			<p>
				<form:label	path="day">Day</form:label>
				<form:select path="day" class="required">
					<form:option value="" label="--Please Select--"/>
					<form:options items="${days}" itemValue="code" itemLabel="name"/>
				</form:select>
			</p>
			<p>
				<form:label	path="frequency">Frequency</form:label>
				<form:select path="frequency" class="required">
					<form:option value="" label="--Please Select--"/>
					<form:options items="${frequencies}" itemValue="code" itemLabel="name"/>`
				</form:select>
			</p>
			<p>	
				<input class="submit" type="submit" />
			</p>
	</fieldset>
</form:form>
