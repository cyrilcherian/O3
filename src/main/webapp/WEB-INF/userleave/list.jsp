<script type="text/javascript" charset="utf-8">
	/* Data set - can contain whatever information you want */
	function deleteForm(key){
		document.deleteForm.action = document.deleteForm.action + key;
		document.deleteForm.submit();
	}
	
	$(document).ready(function() {
		$('#userleaves').html( '<table cellpadding="0" cellspacing="0" border="0" class="display" id="example"></table>' );
		$('#example').dataTable( {
			"aaData":  ${userLeaveJsonData},
			"bPaginate": false,
		"bFilter": true,
			"aoColumns": [
				{ "sTitle": "User Name" , "mDataProp": "userName",
				  "fnRender": function(obj) {
						var sReturn = obj.aData["userLeavePolicy"]["user"]["userName"];
						return sReturn;
					}
				},
				{ "sTitle": "Start Date" , "mDataProp": "startDate",
				  "fnRender": function(obj) {
						var sReturn = obj.aData["startDate"];
						return sReturn;
					}
				},
				{ "sTitle": "End Date" , "mDataProp": "endDate",
				  "fnRender": function(obj) {
						var sReturn = obj.aData["endDate"];
						return sReturn;
					}
				},
				
				{ "sTitle": "Status" , "mDataProp": "Status",
				  "fnRender": function(obj) {
						var sReturn = obj.aData["status"];
						return sReturn;
					}
				},
				{ "sTitle": "Days" , "mDataProp": "days",
				  "fnRender": function(obj) {
						var sReturn = obj.aData["days"];
						return sReturn;
					}
				},
				{ "sTitle": "LeaveType" , "mDataProp": "leaveType",
				  "fnRender": function(obj) {
						var sReturn = obj.aData["userLeavePolicy"]["leavePolicy"]["leaveType"];
						return sReturn;
					}
				},
				{ "sTitle": "Delete Action", "mDataProp": "myKey",
					  "sClass": "center",
					  "fnRender": function(obj) {
							var sReturn = obj.aData["myKey"];
							data = "-";
							if (obj.aData["status"] != "APPROVED") {
								data = "<a href=\"javascript: deleteForm("+sReturn+")\">Delete</a>";
							}
							return data;
						}
				}

			]
		} );	
	} );
</script>

<h1>User Leave Status</h1>
<div id="userleaves"></div>
<form name="deleteForm" action="/office-cube/userleave/delete/" method="post">
</form>

