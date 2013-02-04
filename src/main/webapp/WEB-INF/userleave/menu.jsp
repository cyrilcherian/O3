<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div class="menubg flt">
	<ul class="menu flt">
	
		<li class=""><a href="/office-cube//userleave/applylist">Apply Leave</a>
		<li class=""><a href="/office-cube/userleave">My Leaves</a>
		<security:authorize access="hasRole('ROLE_SUPERVISOR')"> 
			<li class=""><a href="/office-cube/userleave/admin/approvelist">Approve Leaves</a>
		</security:authorize> 
		
		<li class=""><a href="/office-cube">Main</a>
	</ul>	
</div>
