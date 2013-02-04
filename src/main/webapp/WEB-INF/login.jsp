<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script type="text/javascript">
$(document).ready(function() {
	$("#signupform").validate();
});
</script>
<div id="signupbox">
 	<div id="signuptab">
		<ul>
			<li id="signupcurrent"><a>Office Cube Signup</a></li>
		</ul>
	</div>
	<div id="signupwrap">
		<h1 id="login-title">Office Cube Login</h1>
		</br>
		<form id="signupform" autocomplete="off" method="post" action="/office-cube/j_spring_security_check">
			<c:if test="${not empty param.authValid}">
			    <span id="infomessage" class="errormessage" >
			    Login failed due to: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
			    </span>
			</c:if>
			<table> 
				<tr>
					<td><label id="login-label" for="username">Username</label></td>
					<td><input id="username" name="j_username" type="text" class="required" maxlength="50" /></td>
					<td class="status"></td>
				</tr>
				<tr>
					<td><label  id="login-label" for="password">Password</label></td>
					<td class="field"><input id="password" name="j_password" class="required" type="password" maxlength="50" /></td>
					<td class="status"></td>
				</tr>
				<tr>
					<td class="label">&nbsp;</td>
					<td class="field" colspan="2">
						<div id="termswrap">
							<input id="terms" type="checkbox" name="_spring_security_remember_me" />
							<label id="lterms" for="terms">Remember me for 2 weeks.</label>
		            	</div> 
	  				</td>
	  		  	</tr>
	  		  	<tr>
					<td class="label"><label id="lsignupsubmit" for="signupsubmit">Signup</label></td>
	  				<td class="field" colspan="2">
	            	<input id="signupsubmit" name="login" type="submit" value="Login" /></td>
	  			</tr>
	  		</table>
		</form>
	</div>
</div>
