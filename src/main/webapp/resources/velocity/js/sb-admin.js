$(function() {

    $('#side-menu').metisMenu();
    $('#dataTables-example').dataTable();
    $('#f_logout').click(function() {
    	$('#f_logout').submit();
    });
});

//Loads the correct sidebar on window load
$(function() {

    $(window).bind("load", function() {
        if ($(this).width() < 768) {
            $('div.sidebar-collapse').addClass('collapse');
        } else {
            $('div.sidebar-collapse').removeClass('collapse');
        }
    });
});

//Collapses the sidebar on window resize
$(function() {

    $(window).bind("resize", function() {
        if ($(this).width() < 768) {
            $('div.sidebar-collapse').addClass('collapse');
        } else {
            $('div.sidebar-collapse').removeClass('collapse');
        }
    });
});