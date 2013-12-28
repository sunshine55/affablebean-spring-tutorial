(function ($) {

	/* Navbar: shopping cart items */
	$.ajax({
        url: $('#getCartSize').val(),
        success: function(response) {
            $("#nav-cart-size").html(response);
        }
    });
	
	/* Customer checkout tab */
	$('#checkout-tab a').click(function(e) {
		e.preventDefault();
		$(this).tab('show');
	});
	
	/* Forms validation */
	$("#form-guest").validate({
        rules: {
            name: "required",
            email: {
                required: true,
                email: true
            },
            phone: {
                required: true,
                number: true,
                minlength: 9
            },
            address: {
                required: true
            },
            ccNumber: {
                required: true,
                creditcard: true
            }
        },
		submitHandler: function() {
			$('#form-guest').submit(function(e) {
				e.preventDefault();
				$.ajax({
					url: $('#validateUrl').val(),
					data: "email=" + $('#form-guest').find('#email').val(),
					type: "POST",
					success: function(response) {
						var content = '';
						if (response === 'true') {
							content += '<div class="alert alert-danger">The provided email was already used. You can purchase as our member.</div>';
							$('#valid-guest').html(content);
						} else {
							$('#form-guest').unbind().submit();
						}
					}
				});
			});
		}
    });
	
	$("#form-member").validate({
        rules: {
            email: {
                required: true,
                email: true
            }
        },
        submitHandler: function() {
        	$('#form-member').submit(function(e) {
        		e.preventDefault();
        		$.ajax({
        			url: $('#validateUrl').val(),
        			data: "email=" + $('#form-member').find('#email').val(),
        			type: "POST",
        			success: function(response) {
        				var content = '';
        				if (response === 'false') {
        					content = '<div class="alert alert-danger">The provided email is invalid. You have to register.</div>';
        					$('#valid-member').html(content);
        				} else {
        					$('#form-guest').unbind().submit();
        				}
        			}
        		});
        	});
        }
    });
	
})(jQuery);