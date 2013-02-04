<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script type="text/javascript">
$(document).ready(function() {
	$("#weekHolidayForm").validate();
});
</script>


<form:form class="cmxform"  id="weekHolidayForm" modelAttribute="weekHoliday" action="../update" method="post">
	<fieldset>
		<legend>Please provide week holiday details</legend>
			<p>
				<form:select path="day" class="required">
					<form:option value="" label="--Please Select--"/>
					<form:options items="${days}" itemValue="code" itemLabel="name"/>
				</form:select>
			</p>
			<p>
				<form:select path="frequency" class="required">
					<form:option value="" label="--Please Select--"/>
					<form:options items="${frequencies}" itemValue="code" itemLabel="name"/>
				</form:select>
			</p>
			
			<input type="hidden" name="myKey" value="${weekHoliday.myKey}" />
			<p>	
				<input class="submit" type="submit" />
			</p>
	</fieldset>
</form:form>
