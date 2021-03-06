function SerciceContext () {
	this.tripService = new TripService();
	this.linkService = new LinkService();
	this.mapService = new MapService();
	this.eventService = new EventService();
	this.locationService = new LocationService();
	this.informationService = new InformationService();
}

var serviceContext;
var viewResolver;

// Tab handlers
var tripsTab;
var linksTab;
var createLinkTab;
var createEventTab;
var menuTab;
var timelineTab;
var addMembersTab;
var tripMembersTab;
var tripMembersTab;
var locationTab;
var locationCreateTab;
var informationTab;
var informationCreateTab;

$(document).ready(function() {
	serviceContext = new SerciceContext();
	tripsTab = new TripsTab();
	linksTab = new LinksTab();
	menuTab = new MenuTab();
	addMembersTab = new AddMemberTab();
	tripMembersTab = new TripMembersTab();
	timelineTab = new TimelineTab();
	createLinkTab = new CreateLinkTab();
	createEventTab = new CreateEventTab();
	locationCreateTab = new LocationCreateTab();
	locationTab = new LocationTab();
	informationCreateTab = new InformationCreateTab();
	informationTab = new InformationTab();
	
	if ($("#initParams #startTab").html() === "trip") {
		var tripId = $("#initParams #id").html();
		serviceContext.tripService.loadTrip(tripId, function(trip) {
			locationTab.trip = trip;
			viewResolver = new ViewResolver(locationTab);
		});
	} else if($("#initParams #startTab").html() === "tripList") {
		viewResolver = new ViewResolver(tripsTab);
	} else {
		viewResolver = new ViewResolver(tripsTab);
    }
	$("#logout-button").click(function() {
		$("#logoutForm").submit();
	});
});

function ViewResolver(startTab) {
	this.loadTab = function(tab) {
		var section = $("section#" + tab.name);
		if(section.is(":empty")) {
			var tabSrc = $.ajax({
				url: section.attr("src"), 
				async:false,
				method: "get"
			}).responseText;
			section.append(tabSrc);
		}
		if (!tab.initialized) {
			tab.init();
			tab.initialized = true;
		}
	};
	this.views = [];
	this.views.push(startTab);
	this.loadTab(startTab);
	startTab.paint();
	
	this.rerender = function() {
		this.views[this.views.length-1].paint();
	};
	this.moveTo = function(tab) {
		this.views[this.views.length-1].hide();
		this.loadTab(tab);
		this.views.push(tab);
		tab.paint();
	};
	this.goBack = function() {
		this.views[this.views.length-1].back();
		this.views.pop(); 
		this.views[this.views.length-1].paint();
	};
	this.redirectToPage = function(tab) {
		this.views[this.views.length-1].hide();
		this.views = [];
		this.loadTab(tab);
		this.views.push(tab);
		tab.paint();
	};
};

var mapHandler;

function initMap() {
	$('#map-canvas').show();
	mapHandler.loadMarkers(linksHandler.trip.links);
}

