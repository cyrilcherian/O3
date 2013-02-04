<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<script type="text/javascript" charset="utf-8">
	/* Data set - can contain whatever information you want */
	
	$(document).ready(function() {
		$('#users').html( '<table cellpadding="0" cellspacing="0" border="0" class="display" id="example"></table>' );
		$('#example').dataTable( {
			"aaData":  ${userJsonData},
			"bFilter": true,
			"sPaginationType": "full_numbers",
			"aoColumns": [
				{ "sTitle": "Corporate ID", "mDataProp": "corporateID" },
				{ "sTitle": "Username" , "mDataProp": "userName"},
				{ "sTitle": "Firstname" , "mDataProp": "firstName"},
				{ "sTitle": "Lastname" , "mDataProp": "lastName"}
				<security:authorize access="hasRole('ROLE_SUPERVISOR')">
					,{ "sTitle": "Action", "mDataProp": "myKey",
					  "sClass": "right",
					  "sWidth": "30%",
					  "fnRender": function(obj) {
							var sReturn = obj.aData["myKey"];
							var flag = obj.aData["active"];
							data = "<a href=\"/office-cube/user/admin/edit/"+sReturn+"\">Edit</a>";
							if(flag){
								data += " / <a href=\"/office-cube/user/admin/deactivate/"+sReturn+"\">Deactivate</a>";
							} else {
								data += " / <a href=\"/office-cube/user/admin/activate/"+sReturn+"\">Activate</a>";
							}
								data += " / <a href=\"/office-cube/user/edit/password?id="+sReturn+"\">ChangePassword</a>";
							return data;
						}
					}
				</security:authorize> 
				
			]
		} );	
	} );
</script>

<h1>Office Users</h1>
<div id="users"></div>

