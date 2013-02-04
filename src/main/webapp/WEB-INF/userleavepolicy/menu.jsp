<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<security:authorize access="hasRole('ROLE_SUPERVISOR')"> 
	<div class="menubg flt">
		<ul class="menu flt">
			<li class=""><a href="/office-cube/userleavepolicy/admin/new">Add User Leave Policy</a></li>
			<li class=""><a href="/office-cube/userleavepolicy">User Leave Policy</a>
			<li class=""><a href="/office-cube">Main</a>
		</ul>	
	</div>
</security:authorize> 
