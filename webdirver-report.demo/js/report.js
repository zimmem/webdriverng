function load_detail(hash) {
	$('#detail_frame').attr('src', hash.substring(1));
}

$(document).ready(function() {

	$('body').layout({
		applyDefaultStyles : true,
		resizable : true,
		slidable : true,
		west__size : 250
	});

	$('#show-all').button().click(function() {
		$('.testng-suit-ok').show();
		$('.testng-test-ok').show();
	});

	$('#only-failed').button().click(function() {
		$('.testng-suit-ok').hide();
		$('.testng-test-ok').hide();
	});

	$('.testng-node > span').click(function() {
		$(this).next('ul').toggle();
	});

	$(window).bind('hashchange', function(event) {
		load_detail(window.location.hash);
	});
	load_detail(window.location.hash);
});
