function MapService() {
	
}

function MapHandler(linksHandler) {
	var mapOptions = {
			center : new google.maps.LatLng(37.7699298, -122.4469157),
			zoom : 12
		};
	this.map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);
	this.linksHandler = linksHandler;
	this.markers = {};
	this.addLink = function() {
		
	};
	
	this.deleteMarker = function(linkId, tripId) {
		var marker = markers[linkId];
		$.ajax({
			url : "action/marker/" + tripId + '/' + linkId,
			type : 'delete',
			success : function(data) {
				marker.setMap(null);
				link['marker'] = null;
				linksHandler.displayMarkerButtons(false);
			}
		});
	};
	this.loadMarkers = function(links) {
		var firstMarker = true;
		for ( var index in links) {
			var link = links[index];
			var location = link['marker'];
			if (location) {
				var latLang = new google.maps.LatLng(location.y, location.x);
				var marker = drawMarker(latLang, link['name']);
				if (firstMarker) {
					map.setCenter(latLang);
					firstMarker = false;
				}
				markers[link['linkId']] = marker;
			}
		}
	};
	this.showMarker = function(link) {
		map.setCenter(markers[link['linkId']].getPosition());
	};
}

google.maps.event.addDomListener(window, 'load', MapHandler);


function drawMarker(location, title) {
	return new google.maps.Marker({
		position : location,
		map : this.map,
		title : title
	});
}


function onPlaceMarkerClick(event) {
	placeMarker(event.latLng, context['chosedRow']);
	google.maps.event.clearListeners(map, 'click');
	linksHandler.displayMarkerButtons(true);
}

function placeMarker(location, row) {
	var trip = context['trip'];
	var link = getLinkByRow(row);
	var linkId = link['linkId'];
	$.ajax({
		url : "action/marker/" + trip['id'] + '/' + linkId,
		data : JSON.stringify({
			'y' : location.k,
			'x' : location.A
		}),
		type : 'put',
		contentType : 'application/json',
		success : function(data) {
			var prevMarker = context['markers'][linkId];
			if (prevMarker) {
				prevMarker.setMap(null);
			}
			var marker = drawMarker(location, link['name']);
			context['markers'][linkId] = marker;
		}
	});
}

function search() {
	var geocoder = new google.maps.Geocoder();
	var address = document.getElementById('location').value;
	geocoder.geocode({
		'address' : address
	}, function(results, status) {
		var position = results[0].geometry.location;
		map.setCenter(position, 12);
		new google.maps.Marker({
			map : map,
			position : position,
			title : results[0].formatted_address
		});
	});
}

