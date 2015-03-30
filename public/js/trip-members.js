function TripMembersTab() {
	this.name = "trip-members";
	this.init = function() {
		$('#membersTable').dataTable( {
			"processing": true,
			"columns": [
			            { "data": "id" },
			            { "data": "name" }
			            ],
			            "columnDefs": [
	                           {
	                        	   "render" : function(data, type, row) {
	                        		   return "<img src='action/user/profileImage?userId=" + row.id + "'/>";
	                        	   },
	                        	   "targets" : 0
	                           },{
	                        	   "render" : function(data, type, row) {
	                        		   return "<div id='expelButton' class='button expelButton' userId='" + row.id + "'>Expel</div>";
	                        	   },
	                        	   "targets" : 2
	                           }],
                       "drawCallback": function() {
	                        	   $('.expelButton').click( function () {
	                        		   serviceContext.tripService.expelMember(linksTab.trip.id, $(this).attr("userId"));
	                        		   tripMembersTab.paint();
	                        	   } );
	                           }
		} );
		this.table = $('#membersTable').DataTable();
		this.paint = function() {
			this.table.ajax.url("action/trip/" + linksTab.trip.id + "/members").load();
			$("#trip-members-layer").show();
		};
		this.hide = function() {
			$("#trip-members-layer").hide();
		};
		this.back = function() {
			this.hide();
		};
		$("#tripAddMember").click(function() {
			addMembersTab.trip = linksTab.trip;
			viewResolver.moveTo(addMembersTab);
		});
	}
}

function AddMemberTab() {
	this.name = "trip-member-add";
	this.init = function() {
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
			var member = addMembersTab.table.row( this ).data();
			serviceContext.tripService.addTripMember(addMembersTab.trip.id, member);
		} );
		this.paint = function() {
			addMembersTab.table.ajax.url("action/loadNewMembersList/" + addMembersTab.trip.id).load();
			$('#add-member-layer').show();
		};
		this.back = function() {
			this.hide();
		};
		
		this.hide = function() {
			$("#add-member-layer").hide();
		};
		$("#add-member-cancel-submit").click(function(){
			viewResolver.goBack();
		});
	}
}