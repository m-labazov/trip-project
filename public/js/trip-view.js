function TripsTab() {
	this.name = "trip-list";
	this.init = function() {
		this.addTripButton = $("#addTripButton"); 
		this.paint = function() {
			serviceContext.tripService.loadTripsTable(tripsTab);
			history.pushState({}, 'Title', '');
			$("#trip-layer").show();
		}
		this.hide = function() {
			$("#trip-layer").hide();
		};
		this.back = function() {
			window.history.back();
			this.hide();
		};
		this.addTripButton.click(function() {
			viewResolver.moveTo(new CreateTripTab());
		});
		
		this.initTripsTable = function() {
			$('#tripTable').dataTable( {
				"processing": false,
				"columns": [
				            { "data": "id" },
				            { "data": "name" },
				            { "data": "startDate" },
				            { "data": "endDate" }
				            ],
				            "columnDefs": [
				                           {
				                        	   "targets" : [ 0 ],
				                        	   "visible" : false,
				                        	   "searchable" : false
				                           }, {
				                        	   "targets" : [ 1 ],
				                        	   "render" : function(data, type, row) {
				                        		   return "<div class='button tripCell'>" + data + "</div>";
				                        	   }
				                           } ],
				                           "drawCallback": function() {
				                        	   $('.tripCell').click( function () {
				                        		   var trip = tripsTab.table.row( $(this).closest('tr') ).data();
				                        		   linksTab.trip = trip;
				                        		   viewResolver.moveTo(linksTab);
				                        	   } );
				                           }
			} );
			this.table = $('#tripTable').DataTable();
			
		};
		this.initTripsTable();
	
	}
}

function CreateTripTab(trip) {
	this.name = "trip-create";
	this.init = function() {
		this.paint = function() {
			clearForm($("#tripForm"));
			if (trip) {
				applyObjectToRow(trip, $("#trip-create-layer"));
			}
			$("#trip-create-layer").show();
		}
		this.hide = function() {
			linksTab.trip = createObjectFromRow($("#trip-create-layer"));
			$("#trip-create-layer").hide();
			$("#trip-submit").off();
			$("#trip-cancel-submit").off();
		};
		this.back = function() {
			this.hide();
		};
		
		$("#trip-submit").click(function(){
			if($("#tripForm")[0].checkValidity()) {
				serviceContext.tripService.saveTripRow($("#tripForm"), trip ? "put" : "post");
			}else console.log("invalid form");
		});
		$("#trip-cancel-submit").click(function(){
			viewResolver.goBack();
		});
		$(".date-field").datetimepicker({
			timepicker:false,
			format:'d-m-Y' });
	}
}

function TripService() {
	this.loadTripsTable = function(tripTab) {
		tripsTab.table.clear();
		tripsTab.table.ajax.url("action/trip/list").load();
	};
	this.loadTrip = function(id, displayFunction) {
		$.ajax({
			url : "action/trip/" + id,
			type : "GET",
			contentType : 'application/json',
			success : function(data) {
				displayFunction(getResponseBody(data));
			}
		});
	};
	this.saveTripRow = function(row, method) {
		$.ajax({
			url : "action/trip",
			data : JSON.stringify(createObjectFromRow(row)),
			type : method,
			contentType : 'application/json',
			success : function(data) {
				if (data.httpStatus === '400') {
					showErrors(data.data, $("#tripForm"));
				} else {
					viewResolver.goBack();
				}
			}
		});
	};
	this.deleteTrip = function(id) {
		$.ajax({
			url : "action/trip/" + id,
			type : 'delete',
			success : function(data) {
				viewResolver.goBack();
			}
		});
	};
	this.loadTripMembers = function(id) {
		$.ajax({
			url : "action/trip/" + id + "/members",
			type : 'get',
			success : function(data) {
				linksTab.drawMembers(getResponseBody(data));
			}
		});
	};
	this.addTripMember = function(id, member) {
		$.ajax({
			url : "action/trip/" + id + "/member/" + member.id,
			type : 'put',
			success : function(data) {
				serviceContext.tripService.loadTripMembers(id);
			    viewResolver.goBack();
			}
		});
	};
}
