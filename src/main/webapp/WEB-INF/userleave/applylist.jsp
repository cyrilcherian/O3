		<script type="text/javascript" charset="utf-8">
			/* Data set - can contain whatever information you want */
			
			$(document).ready(function() {
				$('#userleave').html( '<table cellpadding="0" cellspacing="0" border="0" class="display" id="example"></table>' );
				$('#example').dataTable( {
					"aaData":  ${userLeavePolicyJsonData},
					"bPaginate": false,
        			"bFilter": true,
					"aoColumns": [
						{ "sTitle": "Leave Type" , "mDataProp": "leaveType",
						  "fnRender": function(obj) {
								var sReturn = obj.aData["leavePolicy"]["leaveType"];
								return sReturn;
							}
						},
						{ "sTitle": "Corporate ID" , "mDataProp": "corporateID",
						  "fnRender": function(obj) {
								var sReturn = obj.aData["user"]["corporateID"];
								return sReturn;
							}
						},
						{ "sTitle": "Name" , "mDataProp": "Name",
						  "fnRender": function(obj) {
								var sReturn = obj.aData["user"]["lastName"] + ", " + obj.aData["user"]["firstName"];
								return sReturn;
							}
						},
						{ "sTitle": "Days Leave Allowed" , "mDataProp": "daysLeaveAllowed",
						  "fnRender": function(obj) {
								var sReturn = obj.aData["daysLeaveAllowed"];
								return sReturn;
							}
						},
						{ "sTitle": "Apply Leave Action", "mDataProp": "myKey",
						  "sClass": "center",
						  "fnRender": function(obj) {
								var sReturn = obj.aData["myKey"];
								data = "<a href=\"/office-cube/userleave/apply?userLeavePolicyId="+sReturn+"\">Day Leave</a>";
								data += " | "
								data += "<a href=\"/office-cube/userleave/hourlyapply?userLeavePolicyId="+sReturn+"\">Hourly Leave</a>";
								return data;
							}
						}

					]
				} );	
			} );
		</script>

<h1>Apply Leave</h1>
<div id="userleave"></div>

