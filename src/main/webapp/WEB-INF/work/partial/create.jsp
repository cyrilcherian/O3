<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript">
$(function() {
	$( "#create-date" ).datepicker( {dateFormat: 'd-m-yy'});
	$.validator.addMethod("o3date", function(value) {
			if (/^([1-9]|[012][1-9]|10|[3][01]+)-([1-9]{0,1}|[1][120]|[0][1-9])-([1-9][\d]{3})$/.test(value)){
				return true;
			} else {
				return false;
			}
		}, "Incorrect Date");

});
</script>
<div id="dialog-form" title="Create new work log">
	<form:form class="cmxform"  id="work-create-form" modelAttribute="work" action="" method="post">
		<fieldset>
			<legend>Please provide work details</legend>
				<p>
					<form:label	for="project" path="project">Project</form:label>
					<form:select path="project" class="required">
						<form:option value="" label="--Please Select--"/>
						<form:options items="${projects}" itemValue="myKey" itemLabel="name"/>
					</form:select>
				</p>
				<p>
					<form:label	for="task" path="task">Task</form:label>
					<form:select path="task" class="required">
						<form:option value="" label="--Please Select--"/>
						<form:options items="${tasks}" itemValue="myKey" itemLabel="name"/>
					</form:select>
				</p>
				<p>
					<form:label	for="hours" path="hours">Hours</form:label> 
					<form:input path="hours" class="required number" min="0" max="24" />
				</p>
				<p>
					<form:label	for="date" path="date">Date</form:label>
					<form:input id="create-date" path="date" class="required o3date"/>
				</p>
				<p>
					<form:label	for="issues" path="issues">Issues</form:label> 
					<form:input path="issues" maxlength="150"/>
				</p>
				<p>
					<form:label	for="description" path="description">Description</form:label> 
					<form:textarea path="description" cols="22" rows="8" maxlength="255"/>
				</p>
				
		</fieldset>
	</form:form>
</div>