function InformationTab() {
	this.name = "information-list";
	this.init = function() {
		this.paint = function() {
			serviceContext.informationService.loadInformationTable(locationTab);
			history.pushState({}, 'Title', '');
			$("#information-list-layer").show();
		}
		this.hide = function() {
			$("#information-list-layer").hide();
		};
		this.back = function() {
			this.hide();
		};
		$("#addInformationButton").click(function() {
			viewResolver.moveTo(informationCreateTab);
			$("#informationForm #locationId").val(informationTab.location.id);
		});
		this.initInformationTable = function() {
			$('#informationTable').dataTable( {
				"processing": false,
				"columns": [
				            { "data": "id" },
				            { "data": "name" },
				            { "data": "type" },
				            { "data": "url" }
	            ],
	            "columnDefs": [
                       {
                    	   "visible" : false,
                    	   "searchable" : false,
                    	   "targets" : [0]
                       }, {
	                	   "targets" : [ 2 ],
	                	   "render" : function(data, type, row) {
	                		   return linkTypes[row.type];
	                	   }
                	   }, {
                    	   "targets" : [ 3 ],
                    	   "render" : function(data, type, row) {
                    		   return row.url ? "<a href='" + row.url + "'>Link</a>" : '&nbsp;';
                    	   }
                       }
               ],
               "drawCallback": function() {
            	   $('.informationCell').click( function () {
            		   var location = locationTab.table.row( $(this).closest('tr') ).data();
            		   informationTab.location = location;
            		   viewResolver.moveTo(informationTab);
            	   } );
               }
			} );
		}
		this.initInformationTable();
		this.table = $('#informationTable').DataTable();
	};
}

function InformationCreateTab() {
	this.name = "information-create";
	this.init = function() {
		this.paint = function() {
			clearForm($("#informationForm"));
			if (location) {
				applyObjectToRow(location, $("#informationForm"));
			}
			$("#information-create-layer").show();
		}
		this.hide = function() {
			$("#information-create-layer").hide();
		};
		this.back = function() {
			this.hide();
		};
		$("#information-submit").click(function(){
			if($("#informationForm")[0].checkValidity()) {
				serviceContext.informationService.save($("#informationForm"));
			}else console.log("invalid form");
		});
		$("#information-cancel-submit").click(function(){
			viewResolver.goBack();
		});
	};
}

function InformationService() {
	this.loadInformationTable = function() {
		informationTab.table.clear();
		informationTab.table.ajax.url("action/information/list/" + informationTab.location.id).load();
	};
	this.save = function(row) {
		var json = createObjectFromRow(row);
		var jsonData = JSON.stringify(json);
		$.ajax({
			url : "action/information",
			data : jsonData,
			type : 'put',
			contentType : 'application/json',
			success : function(data) {
				if (data.httpStatus === '400') {
					showErrors(data.data, $("#informationForm"));
				} else {
					viewResolver.goBack();
				}
			}
		});
	};
}