<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>		
<script type="text/javascript" charset="utf-8">
	/* Data set - can contain whatever information you want */
	
	$(document).ready(function() {
		$('#leavePolicies').html( '<table cellpadding="0" cellspacing="0" border="0" class="display" id="example"></table>' );
		$('#example').dataTable( {
			"aaData":  ${leavePolicyJsonData},
			"bPaginate": false,
			"bFilter": true,
			"aoColumns": [
				{ "sTitle": "Leave Type", "mDataProp": "leaveType" },
				{ "sTitle": "Description", "mDataProp": "description" }
				<security:authorize access="hasRole('ROLE_SUPERVISOR')">
				,{ "sTitle": "Action", "mDataProp": "myKey",
				  "sClass": "center",
				  "fnRender": function(obj) {
						var sReturn = obj.aData["myKey"];
							data = "<a href=\"/office-cube/leavepolicy/admin/edit/"+sReturn+"\">Edit</a>";
						
						return data;
					}
				}
				</security:authorize> 

			]
		} );	
	} );
</script>

<h1>Office Leave Policies</h1>
<div id="leavePolicies"></div>

