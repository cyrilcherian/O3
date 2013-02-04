<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div class="menubg flt">
	<ul class="menu flt">
		<security:authorize access="hasAnyRole('ROLE_SUPERVISOR', 'ROLE_TEAM_SUPERVISOR')"> 
			<li class=""><a href="/office-cube/team/admin/new">Create Team</a></li>
		</security:authorize> 
	
		<li class=""><a href="/office-cube/team">Teams</a>
		<li class=""><a href="/office-cube">Main</a>
	</ul>	
</div>
