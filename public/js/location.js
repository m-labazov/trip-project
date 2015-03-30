function LocationTab() {
	this.name = "location-list";
	this.init = function() {
		this.paint = function() {
			serviceContext.locationService.loadLocationTable(tripsTab);
			history.pushState({}, 'Title', '');
			$("#location-list-layer").show();
			history.pushState({}, 'Title', 'trip?id='+informationTab.trip.id);
		}
		this.hide = function() {
			$("#location-list-layer").hide();
		};
		this.back = function() {
			this.hide();
		};
		$("#addLocationButton").click(function() {
			viewResolver.moveTo(locationCreateTab);
			$("#locationForm #tripId").val(locationTab.trip.id);
		});
		this.initLocationTable = function() {
			$('#locationTable').dataTable( {
				"processing": false,
				"columns": [
		            { "data": "id" },
		            { "data": "name" }
	            ],
	            "columnDefs": [
                       {
                    	   "visible" : false,
                    	   "searchable" : false,
                    	   "targets" : [0]
                       }, {
	                	   "targets" : [ 1 ],
	                	   "render" : function(data, type, row) {
	                		   return "<div class='button locationCell'>" + data + "</div>";
	                	   }
                	   }, {
                    	   "targets" : [ 2 ],
                    	   "render" : function(data, type, row) {
                    		   return "Draw dates here.";
                    	   }
                       }
               ],
               "drawCallback": function() {
            	   $('.locationCell').click( function () {
            		   var location = locationTab.table.row( $(this).closest('tr') ).data();
            		   informationTab.location = location;
            		   viewResolver.moveTo(informationTab);
            	   } );
               }
			} );
		}
		this.initLocationTable();
		this.table = $('#locationTable').DataTable();
	};
}

function LocationCreateTab() {
	this.name = "location-create";
	this.init = function() {
		this.paint = function() {
			clearForm($("#locationForm"));
			if (location) {
				applyObjectToRow(location, $("#locationForm"));
			}
			$("#location-create-layer").show();
		}
		this.hide = function() {
			$("#location-create-layer").hide();
		};
		this.back = function() {
			this.hide();
		};
		$("#location-submit").click(function(){
			if($("#locationForm")[0].checkValidity()) {
				serviceContext.locationService.save($("#locationForm"), location ? "put" : "post");
			}else console.log("invalid form");
		});
		$("#location-cancel-submit").click(function(){
			viewResolver.goBack();
		});
		$(".date-field").datetimepicker({
			timepicker:false,
			format:'d-m-Y' });
	};
}

function LocationService() {
	this.save = function(locationForm, method) {
		$.ajax({
			url : "action/location",
			data : JSON.stringify(createObjectFromRow(locationForm)),
			type : method,
			contentType : 'application/json',
			success : function(data) {
				if (data.httpStatus === '400') {
					showErrors(data.data, $("#locationForm"));
				} else {
					viewResolver.goBack();
				}
			}
		});
	};
	this.loadLocationTable = function() {
		locationTab.table.clear();
		locationTab.table.ajax.url("action/location/list/" + locationTab.trip.id).load();
	};
	this.load = function(id) {
		
	}
}