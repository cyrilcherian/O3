<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>	

<script type="text/javascript" charset="utf-8">
	/* Data set - can contain whatever information you want */
	
	$(document).ready(function() {
		$('#tasks').html( '<table cellpadding="0" cellspacing="0" border="0" class="display" id="example"></table>' );
		$('#example').dataTable( {
			"aaData":  ${taskJsonData},
			"bPaginate": false,
			"bFilter": true,
			"aoColumns": [
				{ "sTitle": "Task Name", "mDataProp": "name" },
				{ "sTitle": "Description", "mDataProp": "description" }
				<security:authorize access="hasAnyRole('ROLE_SUPERVISOR', 'ROLE_TEAM_SUPERVISOR')">
				
					,{ "sTitle": "Action", "mDataProp": "myKey",
					  "sClass": "center",
					  "fnRender": function(obj) {
							var sReturn = obj.aData["myKey"];
							data = "<a href=\"/office-cube/task/admin/edit/"+sReturn+"\">Edit</a>";
							return data;
						}
					}
				</security:authorize> 
			]
		} );	
	} );
</script>

<h1>Office Tasks</h1>
<div id="tasks"></div>

