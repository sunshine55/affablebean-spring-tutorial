$(document).ready(function() {

	/* Customer checkout tab */
	$('#checkoutTab a').click(function(e) {
		e.preventDefault();
		$(this).tab('show');
	});
	
});