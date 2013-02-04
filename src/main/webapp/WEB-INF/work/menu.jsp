<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div class="menubg flt">
	<ul class="menu flt">
		<li class=""><a href="/office-cube/work">My Work</a></li>
		<security:authorize access="hasAnyRole('ROLE_SUPERVISOR', 'ROLE_TEAM_SUPERVISOR')"> 
			<li class=""><a href="/office-cube/work/admin/report/new">Create Report</a></li>
		</security:authorize> 
			
		<li class=""><a href="/office-cube">Main</a>
	</ul>	
</div>
