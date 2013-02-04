<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script type="text/javascript">
$(document).ready(function() {
	$("#taskForm").validate({
				rules: {
					name:{
						remote:"/office-cube/task/validateTN"
					}
			},
			messages: {name:{
				remote: jQuery.format("Task is already in use")
			}
		}
	});
});
</script>


<form:form class="cmxform"  id="taskForm" modelAttribute="task" action="create" method="post">
	<fieldset>
		<legend>Please provide task details</legend>
			<p>
				<form:label	for="name" path="name">Task Name (required)</form:label> 
				<form:input path="name" class="required" minlength="2" maxlength="150"/>
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
