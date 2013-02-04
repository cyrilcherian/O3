<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>	
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$('#weekholidays').html( '<table cellpadding="0" cellspacing="0" border="0" class="display" id="weekholiday"></table>' );
		$('#weekholiday').dataTable( {
			"aaData":  ${weekHolidayJsonData},
			"bPaginate": false,
   			"bFilter": false,
			"aoColumns": [
				{ "sTitle": "Description", "mDataProp": "day", "sClass": "right",
				  "fnRender": function(obj) {
						var sReturn = obj.aData["myKey"];
						data = "Leave on " +  obj.aData["frequency"] + " " + obj.aData["day"] + " of a week.";
						return data;
					}
				}
				<security:authorize access="hasRole('ROLE_SUPERVISOR')">
					,{ "sTitle": "Action", "mDataProp": "myKey",
					  "sClass": "center",
					  "fnRender": function(obj) {
							var sReturn = obj.aData["myKey"];
							data = "<a href=\"/office-cube/weekholiday/admin/edit/"+sReturn+"\">Edit</a>";
							return data;
						}
					}
				</security:authorize>
			]
		} );	
	} );
</script>

<h1>Week Holidays</h1>
<div id="weekholidays"></div>

