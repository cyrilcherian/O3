<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div class="menubg flt">
	<ul class="menu flt">
		<security:authorize access="hasRole('ROLE_SUPERVISOR')"> 
			<li class=""><a href="/office-cube/weekholiday/admin/new">New Weekly Holiday</a>
		</security:authorize> 
	
		<li class=""><a href="/office-cube/weekholiday">Weekly Holiday</a>
		<li class=""><a href="/office-cube">Main</a>
	</ul>	
</div>

