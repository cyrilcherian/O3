<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script type="text/javascript">
$(document).ready(function() {
	$("#userForm").validate({
			rules: {confirmPassword:{
					equalTo:"#password"
				}
			},
			messages: {confirmPassword: {
				equalTo:"Enter the same as above"
			}
		}
	});
});
</script>

<form:form class="cmxform"  id="userForm" modelAttribute="user" action="../update/password" method="post">
	<fieldset>
		<legend>Please provide your details</legend>
			<p>
				<form:label	for="userName" path="userName">Username (required)</form:label> 
				<form:input path="userName"  disabled="true" class="required"  minlength="5" maxlength="150"/>
			</p>
			<p>
				<form:label	for="corporateID" path="corporateID">CorporateID (required)</form:label> 
				<form:input path="corporateID"  disabled="true" class="required"  minlength="5" maxlength="150"/>
			</p>
			<p>
				<form:label	for="password" path="password">Password (required)</form:label> 
				<form:password path="password" class="required" minlength="5" maxlength="150"/>
			</p>
			<p>
				<form:label	for="confirmPassword" path="confirmPassword">Confirm Password (required)</form:label> 
				<form:password path="confirmPassword" for="confirmPassword" class="required" minlength="5"  maxlength="150"/>
			</p>
			<input type="hidden" name="myKey" value="${user.myKey}" />
			<p>	
				<input class="submit" type="submit" />
			</p>
	</fieldset>
</form:form>
