<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Office Cube Application</title>
	<link rel="stylesheet" href="<c:url value="/resources/styles/chosen.css" />" type="text/css" />
	<link rel="stylesheet" href="<c:url value="/resources/styles/booking.css" />" type="text/css" media="screen" />
	<link rel="stylesheet" href="<c:url value="/resources/styles/layout-default-latest.css" />" type="text/css" media="screen" />
	<link rel="stylesheet" href="<c:url value="/resources/styles/data_table.css" />" type="text/css" media="screen" />
	<link rel="stylesheet" href="<c:url value="/resources/styles/data_table_page.css" />" type="text/css" media="screen" />
	<link rel="stylesheet" href="<c:url value="/resources/styles/jquery-ui-1.8.16.custom.css" />" type="text/css" media="screen" />
	<link rel="stylesheet" href="<c:url value="/resources/styles/jquery-ui.css" />" type="text/css" media="screen" />
	<link rel="stylesheet" href="<c:url value="/resources/styles/screen.css" />" type="text/css" media="screen" />
	<link rel="stylesheet" href="<c:url value="/resources/styles/fullcalendar.css" />" type="text/css" />
	<link rel="stylesheet" href="<c:url value="/resources/styles/fullcalendar.print.css" />" type="text/css" media="print" />
	
    <script type="text/javascript" src="<c:url value="/resources/javascript/jquery-1.7.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/javascript/jquery-ui-1.10.1.custom.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/javascript/jquery.layout-latest.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/javascript/jquery.dataTables.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/javascript/jquery.validate.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/javascript/fullcalendar.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/javascript/jQuery.download.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/javascript/chosen.jquery.min.js" />"></script>
	
	<SCRIPT type="text/javascript">$(document).ready(function () {	$('body').layout({ 
		north__scrolable: false,
		north__closable: false,
		north__resizable: false,
		south__scrolable: false,
		south__closable: false,
		south__resizable: false
		
	});});</SCRIPT>
</head>
	<BODY>
		<DIV class="ui-layout-center" >
			<tiles:insertAttribute name="center" />
		</DIV>
		<DIV class="ui-layout-north">
			<h1 class="banner-label">Office Cube</h1>
			<p class="welcome-label">
				<security:authorize ifAllGranted="ROLE_USER">
					<c:if test="${pageContext.request.userPrincipal != null}">
						Welcome, ${pageContext.request.userPrincipal.name} |
					</c:if>
					<a href="<c:url value="/office-cube/j_spring_security_logout" />">Logout</a>
				</security:authorize>
			</p>
		</DIV>
		<DIV class="ui-layout-south"></DIV>
		<DIV class="ui-layout-west">
			<tiles:insertAttribute name="menu" />
		</DIV>
	</BODY>
</html>