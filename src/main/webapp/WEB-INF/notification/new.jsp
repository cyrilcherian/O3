<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$(".chzn-select").chosen();
		$("#notificationForm").validate({
			rules: {
				summary: "required",
				description: "required",
			},
			messages: {
				summary: "Please provide the summary",
				description: "Please provide the description",
			}
		});
	});
</script>

<form:form class="cmxform" id="notificationForm"
	modelAttribute="notificationBean" action="../notification/creates1"
	method="post">
	<fieldset>
		<legend>Please fill notification details</legend>
		<p>
			<form:label for="notifiers" path="notifiers">Notifiers (required)</form:label>
			<select style="width: 150px;" name="users" id="users"
				data-placeholder="Notify users" multiple
				class="required chzn-select">
				<option value=""></option>
				<option>All</option>
				<option>Cyril</option>
				<option>Admin</option>
			</select>
		</p>
		<p>
			<form:label for="summary" path="summary">Summary</form:label>
			<form:input path="summary" maxlength="150" class="required" />
		</p>
		<p>
			<form:label for="description" path="description">Description</form:label>
			<form:textarea path="description" cols="22" rows="8" maxlength="255"
				class="required" />
		</p>
		<p>
			<input class="submit" type="submit" />
		</p>
	</fieldset>
</form:form>
