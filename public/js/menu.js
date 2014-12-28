function MenuTab() {
	this.showMenu = function(event) {
		$('#menu').css({top: event.pageY, left:event.pageX});
		$('#menu').show("fast");
	};
	this.hideMenu = function() {
		$('#menu').hide("fast");
	};
	this.reloadFields = function() {
		if (linksTab.trip) {
			$('.menu-optional-fields').show();
		}
	};
	$('#menu-button').click(function(e) {
		menuTab.showMenu(e);
		e.stopPropagation(); 
	});
	$(document).click(function() {
		menuTab.hideMenu();
	});
	$("#menu").click(function(e) {
	    e.stopPropagation(); 
	});
	$("#tripListButton").click(function() {
		viewResolver.redirectToPage(tripsTab);
		menuTab.hideMenu();
	});
	$("#tripDraftButton").click(function() {
		viewResolver.redirectToPage(linksTab);
		menuTab.hideMenu();
	});
	$("#tripTimelineButton").click(function() {
		viewResolver.redirectToPage(timelineTab);
		menuTab.hideMenu();
	});
}