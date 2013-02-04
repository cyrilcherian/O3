<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>	
<script type="text/javascript" charset="utf-8">
	
	$(document).ready(function() {
		$('#teams').html( '<table cellpadding="0" cellspacing="0" border="0" class="display" id="example"></table>' );
		$('#example').dataTable( {
			"aaData":  ${teamJsonData},
			"bPaginate": false,
			"bFilter": true,
			"aoColumns": [
				{ "sTitle": "Name" , "mDataProp": "name",
				  "fnRender": function(obj) {
						var sReturn = obj.aData["name"];
						return sReturn;
					}
				},
				{ "sTitle": "Description" , "mDataProp": "description",
				  "fnRender": function(obj) {
						var sReturn = obj.aData["description"];
						return sReturn;
					}
				}
					,{ "sTitle": "Action", "mDataProp": "myKey",
					  "sClass": "center",
					  "fnRender": function(obj) {
							var sReturn = obj.aData["myKey"];
							var data = "";
							<security:authorize access="hasAnyRole('ROLE_SUPERVISOR', 'ROLE_TEAM_SUPERVISOR')">
								data = "<a href=\"/office-cube/team/admin/edit/"+sReturn+"\">Edit</a> / ";
							</security:authorize> 
							data += "<a href=\"/office-cube/team/show/"+sReturn+"\">Show</a>";
							return data;
						}
					}

			]
		} );	
	} );
</script>

<h1>Teams</h1>
<div id="teams"></div>

