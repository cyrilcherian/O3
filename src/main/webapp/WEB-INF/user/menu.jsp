<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div class="menubg flt">
	<ul class="menu flt">
		<security:authorize access="hasRole('ROLE_SUPERVISOR')">
			<li class=""><a href="/office-cube/user/admin/new">New User</a>
		</security:authorize> 
		<li class=""><a href="/office-cube/user/edit/password">Reset Password</a>
		<li class=""><a href="/office-cube/user/edit">Edit</a>
		<li class=""><a href="/office-cube/user/active">Active Users</a>
		<li class=""><a href="/office-cube/user/inactive">Inactive Users</a>
		<li class=""><a href="/office-cube">Main</a>
	</ul>	
</div>
