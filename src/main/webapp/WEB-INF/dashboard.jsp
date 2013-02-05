<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<script type="text/javascript" charset="utf-8">
	/* Data set - can contain whatever information you want */
	
	$(document).ready(function() {
		$('#users').html( '<table cellpadding="0" cellspacing="0" reddborder="0" class="display" id="example"></table>' );
		$('#example').dataTable( {
			"aaData":  ${userJsonData},
			"bFilter": true,
			"sPaginationType": "full_numbers",
			"aoColumns": [
				{ "sTitle": "Corporate ID", "mDataProp": "corporateID" },
				{ "sTitle": "Username" , "mDataProp": "userName"},
				{ "sTitle": "Firstname" , "mDataProp": "firstName"},
				{ "sTitle": "Lastname" , "mDataProp": "lastName"}
			]
		} );	
	} );
</script>
<h1>Welcome to Office Cube!</h1>

<h3 class="ui-widget-header ui-corner-all toggle">New Joinee Users</h3>
<div id="users"></div>