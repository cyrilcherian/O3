<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div class="menubg flt">
	<ul class="menu flt">
		<security:authorize access="hasRole('ROLE_SUPERVISOR')"> 
			<li class=""><a href="/office-cube/leavepolicy/admin/new">New Leave Policy</a></li>
		</security:authorize> 
		<li class=""><a href="/office-cube/leavepolicy">Leave Policy</a>
		<li class=""><a href="/office-cube">Main</a>
	</ul>	
</div>
