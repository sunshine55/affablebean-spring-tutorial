$(document).ready(function() {

	/* Customer checkout tab */
	$('#checkoutTab a').click(function(e) {
		e.preventDefault();
		$(this).tab('show');
	});
	
	/* AJAX for validating checkout member */
	$('#email').focusout(function() {
	    $.ajax({
	        url: $('#validateMemberUrl').val(),
	        data: "email=" + $('#email').val(),
	        type: "POST",
	        success: function(response) {
	        	var content = '';
	        	if (response === 'false')
	        		content += '<p class="text-danger text-right">This email invalid. You are guest.</p>';
	        	else
	        		content += '<p class="text-success text-right">This email is valid. You are already member.</p>';
	            $("#isValidMember").html(content);
	        }
	    }); 
	});
	
});