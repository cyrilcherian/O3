<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script type="text/javascript">
</script>
<h1>Leave Details</h1>
<form:form class="cmxform"  id="userDayLeaveForm" modelAttribute="userLeave" method="post">
		<p>
			<label>Username: </label>
			<c:out value="${userLeave.userLeavePolicy.user.userName}"/><br>
		</p>
		<p>
			<label>Name: </label>
			<c:out value="${userLeave.userLeavePolicy.user.displayName}"/><br>
		</p>
		
		<p>
			<label>CorporateID: </label>
			<c:out value="${userLeave.userLeavePolicy.user.corporateID}"/><br>
		</p>
		<p>
			<label>Leave Type: </label>
			<c:out value="${userLeave.userLeavePolicy.leavePolicy.leaveType}"/><br>
		</p>
		<p>
			<label>Days Leave Allowed: </label>
			<c:out value="${userLeave.userLeavePolicy.daysLeaveAllowed}"/><br>
		</p>
		<p>
			<label>From: </label>
			<c:out value="${userLeave.startDate}"/><br>
		</p>
		<p>
			<label>To: </label>
			<c:out value="${userLeave.endDate}"/><br>
		</p>
		
		<p>
			<c:forEach items="${weekHolidays}" var="holiday">
				<label>Week Holidays: </label>
				<c:out value="${holiday}"/><br>
			</c:forEach>
		</p>
		<p>
			<c:forEach items="${officeHolidays}" var="holiday">
				<label>Office Holidays: </label>
				<c:out value="${holiday}"/><br>
			</c:forEach>
		</p>
		<p>
			<label>Total Days: </label>
			<c:out value="${days}"/><br>
		</p>
		<p>
			<label>Effective Leave Days: </label>
			<c:out value="${userLeave.days}"/><br>
		</p>
		<p>	
			<button type="submit" id="confirm" name="_eventId_confirm">Confirm</button>
			<button type="submit" id="revise" name="_eventId_revise">Revise</button>
			<button type="submit" name="_eventId_cancel" >Cancel</button>
		</p>
		
</form:form>