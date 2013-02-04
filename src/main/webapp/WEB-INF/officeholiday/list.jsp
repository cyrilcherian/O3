<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>	
	
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$('#officeholidays').html( '<table cellpadding="0" cellspacing="0" border="0" class="display" id="example"></table>' );
		$('#example').dataTable( {
		"aaData":  ${officeHolidayJsonData},
			"bPaginate": false,
   			"bFilter": true,
			"aoColumns": [
				{ "sTitle": "Date (yyyy-mm-dd)", "mDataProp": "date" },
				{ "sTitle": "Description", "mDataProp": "description" }
				<security:authorize access="hasRole('ROLE_SUPERVISOR')">
				,{ "sTitle": "Action", "mDataProp": "myKey",
			  	"sClass": "center",
				  "fnRender": function(obj) {
						var sReturn = obj.aData["myKey"];
						data = "<a href=\"/office-cube/officeholiday/admin/edit/"+sReturn+"\">Edit</a>";
						return data;
					}
				}
				</security:authorize> 
			]
		} );
	} );
</script>
<h1>Office Holidays</h1>
<div id="officeholidays"></div>

