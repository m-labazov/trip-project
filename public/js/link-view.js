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
	this.name = "link-list";
	this.init = function() {
		$('#linkTable').dataTable( {
			"processing": true,
			"columns": [
			            { "data": "linkId" },
			            { "data": "name" },
			            { "data": "location" },
			            { "data": "type" },
			            { "data": "url" }
			            ],
			            "columnDefs": [
			                           {
			                        	   "render" : function(data, type, row) {
			                        		   return row.url ? "<a href='" + row.url + "'>Link</a>" : null;
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
			createLinkTab.link = null;
			viewResolver.moveTo(createLinkTab);
		});
		$("#tripDelete").click(function() {
			serviceContext.tripService.deleteTrip(linksTab.trip.id);
		});
		$("#tripEdit").click(function() {
			viewResolver.moveTo(new CreateTripTab(linksTab.trip));
		});
		$("#linkDelete").click(function() {
			serviceContext.linkService.deleteLink($(this).parent().find("#linkId").text());
		});
		$("#linkEdit").click(function() {
			createLinkTab.link = linksTab.table.row($(this).closest('tr').prev()).data();
			viewResolver.moveTo(createLinkTab);
		});
		$("#linkAddToTimeline").click(function() {
			createEventTab.link = linksTab.table.row($(this).closest('tr').prev()).data();
			viewResolver.moveTo(createEventTab);
		});
	}
}

function CreateLinkTab() {
	this.name = "link-create";
	this.init = function () {
		this.paint = function() {
			clearForm($("#linkForm"));
			if (this.link) {
				applyObjectToRow(this.link, $("#link-create-layer"));
			}
			$('#link-create-layer').show();
			$('#link-submit').show();
			$('.eventField').hide();
		};
		this.hide = function() {
			$("#link-create-layer").hide();
		}
		this.back = function() {
			this.hide();
		};
		$("#link-submit").click(function(){
			if($("#linkForm")[0].checkValidity()) {
				serviceContext.linkService.saveLink($("#linkForm"));
			}else console.log("invalid form");
		});
		$("#link-cancel-submit").click(function(){
			viewResolver.goBack();
		});
	}
}

function getLinkDetails(link) {
	var details = $("#linkDetails").clone(true,true);
	details.show();
	details[0].id = "usedLinkDetails";
	details.find("#description").append(link.description);
	if (link.startTime !== null) {
		details.find("#linkAddToTimeline").hide();
	}
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
