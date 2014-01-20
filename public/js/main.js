var map;
var markers = [];
function initialize() {
	initMap();
	initLinks();
}

var context = {};

function initLinks() {
	$.ajax({
		url : "action/link/types",
		type : 'get',
		success : function(data) {
			var jsonData = $.parseJSON(data);
			var types = jsonData['body'];
			context['linkTypes'] = types;
			for (row in types) {
				var option = "<option value=\'" + row + "\'>" + types[row]
						+ "</option>";
				$('#createLinkLayer #linkType').append(option);
			}
		}
	});

	$("#trip-create").click(function() {
		$.ajax({
			url : "action/trip",
			data : JSON.stringify({
				'name' : $('#trip-add-name').val()
			}),
			type : 'post',
			contentType : 'application/json',
			success : function(data) {
				alert(data);
			}
		});
	});
	$("#trip-search").click(function() {
		$.ajax({
			url : "action/trip?tripName=" + $("#trip-search-name").val(),
			type : 'get',
			success : function(data) {
				$('#linkList').children($("[id*='linkRow']")).remove();

				var jsonData = $.parseJSON(data);
				context['trip'] = jsonData['body'];

				for (row in jsonData['body']['links']) {
					var link = jsonData['body']['links'][row];
					processLinkRow(link);
				}
			}
		});
	});
	$("#addLinkButton").click(function() {
		showAddLinkLayer();
	});
	$(".link-edit").click(function() {
		editRow($(this).parent());
	});
	$(".link-delete").click(
			function() {
				var row = $(this).parent();
				$.ajax({
					url : "action/link/" + context['trip']['id'] + "/"
							+ row.find("#linkName").text(),
					type : 'delete',
					success : function(data) {
						row.remove();
					}
				});
			});
	$("#okLinkButton").click(function() {
		var row = $(this).parent();
		console.log(row);
		var json = {
			"name" : row.find("#linkName").val(),
			"description" : row.find("#linkDescription").val(),
			"url" : row.find("#linkUrl").val(),
			"type" : row.find('#linkType').val(),
			"linkId" : row.find('#linkId').val()
		};
		var jsonData = JSON.stringify(json);
		$.ajax({
			url : "action/link/" + context['trip']['id'],
			data : jsonData,
			type : 'put',
			contentType : 'application/json',
			success : function(data) {
				processLinkRow(json, row);
			}
		});

	});

}

function collectDataFromLinkRow(id) {
	return $.map(context['trip']['links'], function (obj) {
		if (obj.linkId === id) {
			return obj;
		}
	})[0];
}

function setEditData(row, link) {
	row.find("#linkName").val(link['name']);
	row.find("#linkUrl").val(link['url']);
	row.find("#linkType").val(link['type']);
	row.find("#linkId").val(link['linkId']);
}

function editRow(row) {
	row.hide();
	var link = collectDataFromLinkRow(row.find("#linkId").val());
	var editLinkLayer = cleanAddLinkLayer($("#createLinkLayer").clone(true,
			true));
	editLinkLayer.attr('id', 'edit' + row.attr('id'));
	setEditData(editLinkLayer, link);
	editLinkLayer.insertBefore(row);
}

function initMap() {
	var mapOptions = {
		center : new google.maps.LatLng(-34.397, 150.644),
		zoom : 8
	};
	map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);
	google.maps.event.addListener(map, 'click', function(event) {
		placeMarker(event.latLng);
	});
	loadMarkers();
}

function cleanAddLinkLayer(addLinkLayer) {
	addLinkLayer.find("#linkName").val("");
	addLinkLayer.find("#linkUrl").val("");
	addLinkLayer.show();
	return addLinkLayer;
}

function showAddLinkLayer() {
	var newLink = $("#createLinkLayer");
	cleanAddLinkLayer(newLink);
	return newLink;
}

function processLinkRow(data, inputRow) {
	if (inputRow && data['linkId']) {
		var linkRowId = inputRow.attr('id').replace('edit', '');
		var linkRow = $('#' + linkRowId);
		setRowData(data, linkRow);
		updateContext(data, linkRowId);
		inputRow.remove();
	} else {
		var newLinkRow = $("#createLinkContainer").clone(true, true);
		var id = 'linkRow' + $("[id*='linkRow']").length;
		newLinkRow.attr('id', id);
		setRowData(data, newLinkRow);
		$('#linkList').append(newLinkRow);
		if (inputRow) {
			inputRow.hide();
		}
	}
}

function updateContext (data, linkRowId) {
	var index = linkRowId.replace('linkRow', '');
	debugger;
	context['trip']['links'][index] = data;
}

function setRowData(link, newLink) {
	newLink.show();
	newLink.find("#linkId").val(link['linkId']);
	newLink.find("#linkName").html(link['name']);
	newLink.find("#linkType").html(context['linkTypes'][link['type']]);
	newLink.find(".trip-link").attr('href', link['url']);
}

function loadMarkers() {
	$
			.ajax({
				url : "action/marker/get/1",
				type : 'get',
				success : function(data) {
					if (data) {
						var jsonData = $.parseJSON(data);
						for ( var index in jsonData['locations']) {
							var location = jsonData['locations'][index];
							placeMarker(new google.maps.LatLng(location.nb,
									location.ob));
						}
					}
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
		var marker = new google.maps.Marker({
			map : map,
			position : position,
			title : results[0].formatted_address
		});
	});
}

function placeMarker(location) {
	var marker = new google.maps.Marker({
		position : location,
		map : map
	});
	markers[markers.length] = {
		nb : location.b,
		ob : location.d,
		title : location.title
	};
}

function storeMarkers() {
	$.ajax({
		url : "action/marker/store",
		data : JSON.stringify({
			'locations' : markers
		}),
		type : 'post',
		contentType : 'application/json',
		success : function(data) {
			alert(data);
		}
	});
}

google.maps.event.addDomListener(window, 'load', initialize);
