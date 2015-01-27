function TimelineTab() {
	this.events;
	this.paint = function() {
		this.events = serviceContext.eventService.loadEvents();
	};
	this.hide = function() {
		$("#trip-timeline").hide();
	};
	this.back = function() {
		this.hide();
	};
	$("#event-submit").click(function(){
	    if($("#linkForm")[0].checkValidity()) {
	        serviceContext.eventService.saveEvent($("#linkForm"));
	        viewResolver.redirectToPage(timelineTab);
	    } else console.log("invalid form");
	});
	this.repaintTimelineTable = function() {
		var container = $("#timelineTable");
		container.empty();
		timelineTab.events.forEach(function(event) {
			var template = $("#eventTemplate").clone(true, true);
			template.css({visibility:"visible"});
			applyObjectToRow(event, template);
			container.append(template);
		});
	};
}

function CreateEventTab(link) {
	this.paint = function() {
		clearForm($("#linkForm"));
		if (link) {
			applyObjectToRow(link, $("#link-create-layer"));
		}
		$('#link-create-layer').show();
		$('#link-submit').hide();
		$('.eventField').show();
	}
	this.hide = function() {
		$("#link-create-layer").hide();
	}
}

function EventService() {
	this.saveEvent = function(row) {
		var json = createObjectFromRow(row);
		delete json.linkId;
		var jsonData = JSON.stringify(json);
		$.ajax({
			url : "action/event/" + linksTab.trip.id,
			data : jsonData,
			type : 'put',
			contentType : 'application/json',
			success : function(data) {
				getResponseBody(data);
				serviceContext.eventService.loadEvents();
			}
		});
	};
	this.loadEvents = function() {
		$.ajax({
			url : "action/event/" + linksTab.trip.id,
			type : 'get',
			contentType : 'application/json',
			success : function(data) {
				timelineTab.events = getResponseBody(data); 
				timelineTab.repaintTimelineTable();
				$("#trip-timeline").show();
			}
		});
	}
}