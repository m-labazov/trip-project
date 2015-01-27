function SerciceContext () {
	this.tripService = new TripService();
	this.linkService = new LinkService();
	this.mapService = new MapService();
	this.eventService = new EventService();
}

var serviceContext;
var tripsTab;
var linksTab;
var menuTab;
var timelineTab;
var membersTab;
var viewResolver;

$(document).ready(function() {
	serviceContext = new SerciceContext();
	tripsTab = new TripsTab();
	linksTab = new LinksTab();
	menuTab = new MenuTab();
	membersTab = new MemberTab();
	timelineTab = new TimelineTab();
	
	if ($("#initParams #startTab").html() === "trip") {
		var tripId = $("#initParams #id").html();
		serviceContext.tripService.loadTrip(tripId, function(trip) {
			linksTab.trip = trip;
			viewResolver = new ViewResolver(linksTab);
		});
	} else if($("#initParams #startTab").html() === "tripList") {
		viewResolver = new ViewResolver(tripsTab);
	} else {
		viewResolver = new ViewResolver(tripsTab);
    }
	$("#logout-button").click(function() {
		$("#logoutForm").submit();
	});
	$(".date-field").datetimepicker({
		timepicker:false,
		format:'d-m-Y' });
	$(".date-time-field").datetimepicker({format: "d-m-Y H:i"});
});

function ViewResolver(startTab) {
	this.views = [];
	this.views.push(startTab);
	startTab.paint();
	
	this.rerender = function() {
		this.views[this.views.length-1].paint();
	};
	this.moveTo = function(tab) {
		this.views[this.views.length-1].hide();
		this.views.push(tab);
		tab.paint();
	};
	this.goBack = function() {
		this.views[this.views.length-1].back();
		this.views.pop(); 
		this.views[this.views.length-1].paint();
	}
	this.redirectToPage = function(tab) {
		this.views[this.views.length-1].hide();
		this.views = [];
		this.views.push(tab);
		tab.paint();
	}
};

var mapHandler;

function initMap() {
	$('#map-canvas').show();
	mapHandler.loadMarkers(linksHandler.trip.links);
}

