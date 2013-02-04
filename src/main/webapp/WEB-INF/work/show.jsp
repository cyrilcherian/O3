<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script type="text/javascript">
$(document).ready(function() {
	$('#dialog-form').dialog({
		autoOpen: false,
		height: 500,
		width: 500,
		modal: true,
		buttons: {
			"Submit": function() {
					if ($('#work-create-form').validate().form()){
						$.ajax({
							  type: "POST",
							  url: "/office-cube/work/create",
							  data: $('#work-create-form').serialize(),
							  success: function(data) {
							  	$('#calendar').fullCalendar('renderEvent', data, true);
							  },
							  failure: function(data) {
							    alert('Event Creation Failed.');
							  }
							});
						$('#dialog-form').dialog("close"); 
					}
			},
			"Cancel": function() { 
					$('#dialog-form').dialog("close"); 
				} 
			}
	});
	$('#edit-dialog-form').dialog({
		autoOpen: false,
		height: 500,
		width: 500,
		modal: true,
		buttons: {
			"Submit": function() {
					if ($('#work-edit-form').validate().form()){
						$.ajax({
							  type: "POST",
							  url: "/office-cube/work/update",
							  data: $('#work-edit-form').serialize(),
							  success: function(data) {
							  	$('#calendar').fullCalendar( 'removeEvents', data.id );
							  	$('#calendar').fullCalendar( 'renderEvent', data, true );
							  },
							  failure: function(data) {
							    alert('Event Updation Failed.');
							  }
							});
						$('#edit-dialog-form').dialog("close"); 
					}
			},
			"Delete": function() { 
				$.ajax({
					  type: "POST",
					  url: "/office-cube/work/delete/" ,
					  data: $('#work-edit-form').serialize(),
					  success: function(data) {
					  	$('#calendar').fullCalendar( 'removeEvents', data.id );
					  },
					  failure: function(data) {
					    alert('Event Updation Failed.');
					  }
					});
				$('#edit-dialog-form').dialog("close"); 
			}, 
			"Cancel": function() { 
					$('#edit-dialog-form').dialog("close"); 
			} 
		}
	});

	
	var calendar = $('#calendar').fullCalendar({
		header: {
			left: 'prev,next today',
			center: 'title'
		},
		selectable: true,
		selectHelper: true,
		events: {
	        url: '/office-cube/work/show',
	        cache: false,
	        success: function() {
	        	$('#calendar').fullCalendar( 'removeEvents' );
	        }
    	},
		editable: false,
	    eventClick: function(calEvent, jsEvent, view) {
	    			if (calEvent.id.toLowerCase().indexOf("holiday") >= 0){
	    				return false;
	    			}
					$.ajax({
						url: '/office-cube/work/edit/' + calEvent.id,
						type: 'GET',
						success: function(data) {
								$('#editworkdiv').html(data);
						}
					});
					$('#edit-dialog-form').dialog('open');
					return false;
				}
	});
	$('#work_dialog_link').click(function(){
		$.ajax({
		url: '/office-cube/work/new',
		type: 'GET',
		success: function(data) {
					$('#createworkdiv').html(data);
				}
			});
		
		$('#dialog-form').dialog('open');
		return false;
	});
	
	$('#export_work').click(function(){
		$.download('/office-cube/work/xls/' + $('#calendar').fullCalendar('getDate').valueOf(),'filename=mySpreadsheet&format=xls', 'GET' );
	});
});
</script>
<div class= "external-events" id='external-events'>
	<button id="work_dialog_link">Add Work</button>
	<button id="export_work">Export</button>
</div>
<div class ="calendar" id='calendar'></div>
<div id="dialog-form" title="Create new work log">
	<div id="createworkdiv"></div>
</div>
<div id="edit-dialog-form" title="Edit work log">
	<div id="editworkdiv"></div>
</div>