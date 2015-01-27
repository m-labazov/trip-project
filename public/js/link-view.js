function LinkService() {
	this.deleteLink = function(linkId) {
		$.ajax({
			url : "action/link/" + linksTab.trip.id + "/"
					+ linkId,
			type : 'delete',
			success : function(data) {
				linksTab.repaintLinksTable();
			}
		});
	};
	this.saveLink = function(row) {
		var json = createObjectFromRow(row);
		delete json.startTime;
		delete json.endTime;
		var jsonData = JSON.stringify(json);
		$.ajax({
			url : "action/link/" + linksTab.trip.id,
			data : jsonData,
			type : 'put',
			contentType : 'application/json',
			success : function(data) {
				if (data.httpStatus === '400') {
					showErrors(data.data, $("#linkForm"));
				} else {
					linksTab.repaintLinksTable();
					viewResolver.goBack();
				}
			}
		});
	};
}

function LinksTab() {
	$('#linkTable').dataTable( {
		"processing": true,
		"columns": [
		            { "data": "linkId" },
		            { "data": "name" },
		            { "data": "location" },
		            { "data": "type" },
		            { "data": "url" },
		            ],
        "columnDefs": [
 					{
						"render" : function(data, type, row) {
							return "<a href='" + row.url + "'>Link</a>";
						},
						"targets" : 4
					}, {
						"render" : function(data, type, row) {
							return linkTypes[row.type];
						},
						"targets" : 3
					}, {
						"targets" : [ 0 ],
						"visible" : false,
						"searchable" : false
					} ]
	} );
	$('#linkTable tbody').on( 'click', 'tr[role="row"]', function () {
		var tr = $(this).closest('tr');
		var row = linksTab.table.row( tr );
		var link = row.data();
 
        if ( row.child.isShown() ) {
            row.child.hide();
            tr.removeClass('shown');
        }
        else {
            row.child( getLinkDetails(link) ).show();
            tr.addClass('shown');
        }
	} );
	this.table = $('#linkTable').DataTable();
	this.paint = function() {
		this.repaintLinksTable();
		$('#links-layer').show();
		$("#tripLabel").empty();
		$("#tripDescription").empty();
		$("#tripLabel").append(linksTab.trip.name + " (from " + linksTab.trip.startDate + " to " + linksTab.trip.endDate + ")");
		$("#tripDescription").append(linksTab.trip.comment);
		history.pushState({}, 'Title', 'trip?id='+linksTab.trip.id);
		menuTab.reloadFields();
		serviceContext.tripService.loadTripMembers(linksTab.trip.id);
	};
	this.back = function() {
		window.history.back();
		this.hide();
	};
	this.hide = function() {
		$('#links-layer').hide();
	};
	this.repaintLinksTable = function() {
		this.table.ajax.url("action/link/" + this.trip.id).load();
	}
	this.filterLinkTypes = $('#filterLinkType');
	this.addLinkButton = $('#addLinkButton');
	this.addLinkButton.click(function() {
		viewResolver.moveTo(new CreateLinkTab());
	});
	$("#link-submit").click(function(){
	    if($("#linkForm")[0].checkValidity()) {
	        serviceContext.linkService.saveLink($("#linkForm"));
	    }else console.log("invalid form");
	});
	$("#link-cancel-submit").click(function(){
		viewResolver.goBack();
	});
	$("#add-member-cancel-submit").click(function(){
		viewResolver.goBack();
	});
	$("#tripDelete").click(function() {
		serviceContext.tripService.deleteTrip(linksTab.trip.id);
	});
	$("#tripEdit").click(function() {
		viewResolver.moveTo(new CreateTripTab(linksTab.trip));
	});
	$("#tripAddMember").click(function() {
		membersTab.trip = linksTab.trip;
		viewResolver.moveTo(membersTab);
	});
	$("#linkDelete").click(function() {
		serviceContext.linkService.deleteLink($(this).parent().find("#linkId").text());
	});
	$("#linkEdit").click(function() {
		viewResolver.moveTo(new CreateLinkTab(linksTab.table.row($(this).closest('tr').prev()).data()));
	});
	$("#linkAddToTimeline").click(function() {
		viewResolver.moveTo(new CreateEventTab(linksTab.table.row($(this).closest('tr').prev()).data()));
	});
	this.drawMembers = function(members) {
		var membersContainer = $("#tripMembers");
		membersContainer.empty();
		membersContainer.append(members.toString());
	};
}

function CreateLinkTab(link) {
	this.paint = function() {
		clearForm($("#linkForm"));
		if (link) {
			applyObjectToRow(link, $("#link-create-layer"));
		}
		$('#link-create-layer').show();
		$('#link-submit').show();
		$('.eventField').hide();
	}
	this.hide = function() {
		$("#link-create-layer").hide();
	}
	this.back = function() {
		this.hide();
	};

}

function MemberTab(trip) {
	$('#friendTable').dataTable( {
		"processing": true,
		"columns": [
		            { "data": "id" },
		            { "data": "name" }
		            ],
        "columnDefs": [
 					 {
				"targets" : [ 0 ],
				"visible" : false,
				"searchable" : false
			} ]
	} );
	this.table = $('#friendTable').DataTable();
	$('#friendTable tbody').on( 'click', 'tr', function () {
		  var member = membersTab.table.row( this ).data();
		  serviceContext.tripService.addTripMember(membersTab.trip.id, member);
	} );
	this.paint = function() {
		membersTab.table.ajax.url("action/loadNewMembersList/" + membersTab.trip.id).load();
		$('#add-member-layer').show();
	};
	this.back = function() {
		this.hide();
	};

	this.hide = function() {
		$("#add-member-layer").hide();
	}
}

function getLinkDetails(link) {
	var details = $("#linkDetails").clone(true,true);
	details.show();
	details.find("#description").append(link.description);
	details.find("#linkId").append(link.linkId);
	return details;
}

function initControlPanel() {
	var controlPanel=context['controlPanel'];
	controlPanel.find("#link-add-marker").click(function() {
		google.maps.event.addListener(mapHandler.map, 'click', onPlaceMarkerClick);
	});
	controlPanel.find("#link-edit-marker").click(function() {
		google.maps.event.addListener(mapHandler.map, 'click', onPlaceMarkerClick);
	});
	controlPanel.find("#link-show-marker").click(function() {
		var link = getLinkByRow(context['chosedRow']);
		mapHandler.showMarker(link);
	});
	controlPanel.find("#link-delete-marker").click(function() {
		var row = context['chosedRow'];
		var link = getLinkByRow(row);
		var linkId = link['linkId'];
		mapHandler.deleteMarker(linkId, linksHandler.trip.id);
	});
	$("#createLinkLayer").hide();
	$("#link-delete").click(serviceContext.linkService.deleteLink);
	$("#okLinkButton").click(function() {
		var row = $(this).parent();
		serviceContext.linkService.saveLinkRow(row);
	});
	$("#cancelLinkButton").click(function() {
		$("#addLinkButton").show();
		$("#createLinkLayer").hide();
	});
	$("#filterLinkType").change(function () {contextService.linkSerice.loadLinksTable(context.trip);});

}
