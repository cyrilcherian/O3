<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script type="text/javascript">
$(document).ready(function() {
	$("#userForm").validate({
			rules: {corporateID:{
					remote:"/office-cube/user/validateCID"
				},userName:{
					remote:"/office-cube/user/${user.myKey}/validateUN"
				}
			},
			messages: {corporateID:{
				remote: jQuery.format("CorporateID is already in use")
			},userName:{
				remote: jQuery.format("UserName is already in use")
			}
		}
	});
});
</script>

<form:form class="cmxform"  id="userForm" modelAttribute="user" action="/office-cube/user/update" method="post">
	<fieldset>
		<legend>Please provide your details</legend>
			<p>
				<form:label	for="userName" path="userName">Username (required)</form:label> 
				<form:input path="userName" disabled="true" class="required"  minlength="2" maxlength="150"/>
				<form:hidden path="userName"/>
			</p>
			<p>
				<form:label	for="corporateID" path="corporateID">CorporateID (required)</form:label> 
				<form:input path="corporateID"  disabled="true" class="required"  minlength="2" maxlength="150"/>
				<form:hidden path="corporateID"/>
			</p>
			<p>
				<form:label	for="firstName" path="firstName">FirstName (required)</form:label> 
				<form:input path="firstName" class="required"  minlength="2" maxlength="150"/>
			</p>
			<p>
				<form:label	for="lastName" path="lastName">LastName (required)</form:label> 
				<form:input path="lastName" class="required"  minlength="1" maxlength="150"/>
			</p>
			<p>
				<form:label	for="email" path="email">Email (required)</form:label> 
				<form:input path="email" class="required email"  minlength="5" maxlength="150"/>
			</p>
			<c:if test="${roleTypes !=null}">
				<legend>Please provide role details</legend>
				<p>
					<c:forEach var="role" items="${roleTypes}" >
						<c:choose>
							<c:when test="${role.checked}">
								<form:checkbox path="roles" label="${role.name}" value ="${role.code}" checked="true"/><br />
							</c:when>
							<c:otherwise>
								<form:checkbox path="roles" label="${role.name}" value ="${role.code}"/><br />
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</p>
			</c:if>
			<legend>Please provide home address details</legend>
			<p>
				<form:label	for="homeAddress.houseDets" path="homeAddress.houseDets">Block Details</form:label> 
				<form:input path="homeAddress.houseDets" maxlength="255"/>
			</p>
			<p>
				<form:label	for="homeAddress.street" path="homeAddress.street">Street</form:label> 
				<form:input path="homeAddress.street" maxlength="255"/>
			</p>
			<p>
				<form:label	for="homeAddress.city" path="homeAddress.city">City</form:label> 
				<form:input path="homeAddress.city" maxlength="255"/>
			</p>
			<p>
				<form:label	for="homeAddress.country" path="homeAddress.country">Country</form:label> 
				<form:input path="homeAddress.country" maxlength="100"/>
			</p>
			<p>
				<form:label	for="homeAddress.pin" path="homeAddress.pin">Pin</form:label> 
				<form:input path="homeAddress.pin" maxlength="50"/>
			</p>
			<input type="hidden" name="myKey" value="${user.myKey}" />
			<input type="hidden" name="homeAddress.myKey" value="${user.homeAddress.myKey}" />
			<p>	
				<input class="submit" type="submit" />
			</p>
	</fieldset>
</form:form>
