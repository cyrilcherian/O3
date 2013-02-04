<script type="text/javascript" charset="utf-8">
	/* Data set - can contain whatever information you want */
	function approveForm(key){
		document.approveForm.action = document.approveForm.action + key;
		document.approveForm.submit();
	}
	function disApproveForm(key){
		document.disApproveForm.action = document.disApproveForm.action + key;
		document.disApproveForm.submit();
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
				{ "sTitle": "Admin Action", "mDataProp": "myKey",
				  "sClass": "center",
				  "fnRender": function(obj) {
						var sReturn = obj.aData["myKey"];
						data = "<a href=\"javascript: approveForm("+sReturn+")\">Approve</a>";
						data += " | ";
						data += "<a href=\"javascript: disApproveForm("+sReturn+")\">Disapprove</a>";
						return data;
					}
				}
			]
		} );	
	} );
</script>

<h1>Approve Leaves</h1>
<div id="userleaves"></div>
<form name="approveForm" action="/office-cube/userleave/admin/approve/" method="post">
</form>
<form name="disApproveForm" action="/office-cube/userleave/admin/disapprove/" method="post">
</form>