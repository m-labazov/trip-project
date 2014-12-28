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
var viewResolver;

$(document).ready(function() {
	serviceContext = new SerciceContext();
	tripsTab = new TripsTab();
	linksTab = new LinksTab();
	menuTab = new MenuTab();
	timelineTab = new TimelineTab()
	viewResolver = new ViewResolver(tripsTab);
	$("#logout-button").click(function() {
		$("#logoutForm").submit();
	});
});

function ViewResolver(startTab) {
	this.views = [];
	this.views.push(startTab);
	
	this.rerender = function() {
		this.views[this.views.length-1].paint();
	};
	this.moveTo = function(tab) {
		this.views[this.views.length-1].hide();
		this.views.push(tab);
		tab.paint();
	};
	this.goBack = function() {
		this.views[this.views.length-1].hide();
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

