function TripsTab() {
	this.addTripButton = $("#addTripButton"); 
	this.paint = function() {
		serviceContext.tripService.loadTripsTable(tripsTab);
		$("#trip-layer").show();
	}
	this.hide = function() {
		$("#trip-layer").hide();
	}
	this.addTripButton.click(function() {
		viewResolver.moveTo(new CreateTripTab());
	});
	
	this.initTripsTable = function() {
		$('#tripTable').dataTable( {
			"processing": true,
	        "ajax": "action/trip/list",
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
			} ]
	    } );
		this.table = $('#tripTable').DataTable();
		$('#tripTable tbody').on( 'click', 'tr', function () {
			  var trip = tripsTab.table.row( this ).data();
			  linksTab.trip = trip;
			  viewResolver.moveTo(linksTab);
			  menuTab.reloadFields();
		} );
	};
	this.initTripsTable();
}

function CreateTripTab(trip) {
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
	}
	$("#trip-submit").click(function(){
		if($("#tripForm")[0].checkValidity()) {
			serviceContext.tripService.saveTripRow($("#tripForm"), trip ? "put" : "post");
		}else console.log("invalid form");
	});
	$("#trip-cancel-submit").click(function(){
		viewResolver.goBack();
	});
}

function TripService() {
	this.loadTripsTable = function(tripTab) {
		tripsTab.table.clear();
		tripsTab.table.ajax.reload();
	};
	this.saveTripRow = function(row, method) {
		$.ajax({
			url : "action/trip",
			data : JSON.stringify(createObjectFromRow(row)),
			type : method,
			contentType : 'application/json',
			success : function(data) {
				viewResolver.goBack();
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
	}
}
