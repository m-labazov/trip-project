function TimelineTab() {
	this.name = "timeline";
	this.init = function() {
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
}

function CreateEventTab() {
	this.name = "link-create";
	this.init = function() {
		this.paint = function() {
			clearForm($("#linkForm"));
			if (this.link) {
				applyObjectToRow(this.link, $("#link-create-layer"));
			}
			$('#link-create-layer').show();
			$('#link-submit').hide();
			$('.eventField').show();
		}
		this.hide = function() {
			$("#link-create-layer").hide();
		};
		$("#event-submit").click(function(){
			if($("#linkForm")[0].checkValidity()) {
				serviceContext.eventService.saveEvent($("#linkForm"));
				viewResolver.redirectToPage(timelineTab);
			} else console.log("invalid form");
		});
		$(".date-time-field").datetimepicker({format: "d-m-Y H:i"});
	}
}

function EventService() {
	this.saveEvent = function(row) {
		var json = createObjectFromRow(row);
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