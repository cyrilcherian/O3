<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<script type="text/javascript" charset="utf-8">
	/* Data set - can contain whatever information you want */
	
	$(document).ready(function() {
		
		//portlet style
   		 $( ".portlet" ).addClass( "ui-widget ui-widget-content ui-helper-clearfix ui-corner-all" )
     					.find( ".portlet-header" )
        				.addClass( "ui-widget-header ui-corner-all" )
        				.prepend( "<span class='ui-icon ui-icon-minusthick'></span>")
        				.end()
      					.find( ".portlet-content" );
 
    	$( ".portlet-header .ui-icon" ).click(function() {
      		$( this ).toggleClass( "ui-icon-minusthick" ).toggleClass( "ui-icon-plusthick" );
      		$( this ).parents( ".portlet:first" ).find( ".portlet-content" ).toggle();
   		});
	//portlet style end	
		
		$('#users').html( '<table cellpadding="0" cellspacing="0" reddborder="0" class="display" id="example"></table>' );
		$('#example').dataTable( {
			"bServerSide": false,
    		"sAjaxSource": "listJoinee",
			"aoColumns": [
				{ "sTitle": "Corporate ID", "mDataProp": "corporateID" },
				{ "sTitle": "Username" , "mDataProp": "userName"},
				{ "sTitle": "Firstname" , "mDataProp": "firstName"},
				{ "sTitle": "Lastname" , "mDataProp": "lastName"}
			],
			"sPaginationType": "full_numbers"
		} );
		
		$('#projects').html( '<table cellpadding="0" cellspacing="0" reddborder="0" class="display" id="project"></table>' );
		$('#project').dataTable( {
			"bServerSide": false,
    		"sAjaxSource": "listNewProject",
			"aoColumns": [
				{ "sTitle": "Name" , "mDataProp": "name"},
				{ "sTitle": "Description" , "mDataProp": "description"}
			],
			"sPaginationType": "full_numbers"
		} );
			
		$('#notifications').html( '<table cellpadding="0" cellspacing="0" reddborder="0" class="display" id="notification"></table>' );
		$('#notification').dataTable( {
			"bServerSide": false,
    		"sAjaxSource": "listNotifications",
			"aoColumns": [
				{ "sTitle": "Description", "mDataProp": "description" }
			],
			"sPaginationType": "full_numbers"
		} );
		
		$('#leaves').html( '<table cellpadding="0" cellspacing="0" reddborder="0" class="display" id="leave"></table>' );
		$('#leave').dataTable( {
			"bServerSide": false,
    		"sAjaxSource": "listLeaveApproval",
			"aoColumns": [
				{ "sTitle": "Description", "mDataProp": "description" },
				{ "sTitle": "Status" , "mDataProp": "status"},
				{ "sTitle": "Days" , "mDataProp": "days"},
				{ "sTitle": "StartDate" , "mDataProp": "startDate"},
				{ "sTitle": "EndDate" , "mDataProp": "endDate"}
			],
			"sPaginationType": "full_numbers"
		} );
		
	} );
</script>
<h1>Welcome to Office Cube!</h1>

	<div class="columns">
		<div class="portlet">
		    <div class="portlet-header">New Joinee Users</div>
		    <div class="portlet-content" id="users" style="display: none;"></div>
		 </div>
		 <div class="portlet">
		    <div class="portlet-header">New Projects</div>
		    <div class="portlet-content" id="projects" style="display: none;"></div>
		 </div>
	</div>
	<div class="columns">
		<div class="portlet">
		    <div class="portlet-header">Notifications</div>
		    <div class="portlet-content" id="notifications" style="display: none;">Notifications goes here</div>
		 </div>
		  <div class="portlet">
		    <div class="portlet-header">Leave Approvals</div>
		    <div class="portlet-content" id="leaves" style="display: none;"></div>
		 </div>
	</div>