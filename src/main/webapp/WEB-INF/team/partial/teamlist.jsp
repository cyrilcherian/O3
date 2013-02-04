<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$('#teamstable').dataTable( {
			"bPaginate": false,
			"bFilter": true,
			"aaData":  ${teamJsonData},
			"bSearchable": false,
			"aoColumns": [
				{ "sTitle": "Name", "mDataProp": "name" },
				{ "sTitle": "Description" , "mDataProp": "description"}
			] } );
		} );
</script>
<table cellpadding="0" cellspacing="0" border="0" class="display" id="teamstable"></table>

