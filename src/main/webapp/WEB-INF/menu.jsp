<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div class="menubg flt">
	<ul class="menu flt">
		<li class=""><a href="/office-cube/officeholiday">Office Holiday</a>
		<li class=""><a href="/office-cube/weekholiday">Weekly Holiday</a>
		<li class=""><a href="/office-cube/leavepolicy">Leave Policy</a>
		<security:authorize access="hasRole('ROLE_SUPERVISOR')"> 
			<li class=""><a href="/office-cube/userleavepolicy">User Leave Policy</a>
		</security:authorize> 
		<li class=""><a href="/office-cube/user/active" title="Users">Users</a></li>
		<li class=""><a href="/office-cube/userleave">My Leaves</a>
		<li class=""><a href="/office-cube/project">Project</a>
		<li class=""><a href="/office-cube/task">Task</a>
		<li class=""><a href="/office-cube/team">Team</a>
		<li class=""><a href="/office-cube/work">Work</a>
	</ul>	
</div>
