<script type="text/javascript" charset="utf-8">
			
	$(document).ready(function() {
		$('#userstable').dataTable( {
			"bPaginate": false,
			"bFilter": true,
			"aaData":  ${userJsonData},
			"bSearchable": false,
			"aoColumns": [
				{ "sTitle": "Corporate ID", "mDataProp": "corporateID" },
				{ "sTitle": "Username" , "mDataProp": "userName"}
			] } );
		} );
</script>
<table cellpadding="0" cellspacing="0" border="0" class="display" id="userstable"></table>

