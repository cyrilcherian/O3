<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
</script>

<form:form class="cmxform" id="userForm" modelAttribute="user"
	action="../admin/create" method="post">
	<fieldset>
		<legend>Please fill notification details</legend>
		<p>
			<form:label for="notifiers" path="notifiers">Notifiers (required)</form:label>
			<form:input path="userName" class="required" minlength="2"
				maxlength="150" />
		</p>
		<p>
			<form:label for="summary" path="summary">Summary</form:label>
			<form:input path="summary" maxlength="150" />
		</p>
		<p>
			<form:label for="description" path="description">Description</form:label>
			<form:textarea path="description" cols="22" rows="8" maxlength="255" />
		</p>
		<p>
			<input class="submit" type="submit" />
		</p>
	</fieldset>
</form:form>
