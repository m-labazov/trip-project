<%@page import="ua.home.trip.enums.ELinkType"%>
<html>
<head>
<title>Travel planner</title>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/jquery.datetimepicker.css">
<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.4/css/jquery.dataTables.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
<script type="text/javascript"
	src="http://maps.googleapis.com/maps/api/js?libraries=drawing&sensor=false"></script>
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/jquery.datetimepicker.js"></script>
<script type="text/javascript"
	src="//cdn.datatables.net/1.10.4/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="js/maps-handler.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<script type="text/javascript" src="js/trip-view.js"></script>
<script type="text/javascript" src="js/link-view.js"></script>
<script type="text/javascript" src="js/common-functions.js"></script>
<script type="text/javascript" src="js/menu.js"></script>
<script type="text/javascript" src="js/timeline.js"></script>
</head>
<body>

	<div id="initParams" hidden="true">
		<div id="startTab">${tab}</div>
		<div id="id">${id}</div>
	</div>
	<div id="menu" class="menu">
		<div id="tripListButton" class="button">Trips list</div>
		<div id="tripDraftButton" class="menu-optional-fields button">Trip's draft</div>
		<div id="tripTimelineButton" class="menu-optional-fields button">Trip's timeline</div>
		<div id="tripMembersButton" class="menu-optional-fields button">Members</div>
	</div>
	<div id="map-canvas" hidden="true">
		<input type="text" id="location" />
		<div id="goEvent" onclick="search()">Go!</div>
	</div>
	<div id="main-layer">
		<div id="menu-layer" class="menu-layer">
			<div id="menu-button" class="button">Menu</div>
			<div id="message-button">Messages</div>
			<div id="logout-button" class="button"><form method="post" id="logoutForm" action="logout">Logout</form></div>
		</div>
		<div id="trip-layer" hidden="true">
			<div id="addTripButton" class="button">New Trip</div>
			<table id="tripTable" class="display" cellspacing="0" width="100%">
				<thead>
					<tr>
						<th>Id</th>
						<th>Name</th>
						<th>Start date</th>
						<th>End date</th>
					</tr>
				</thead>
			</table>
		</div>
		<div id="trip-create-layer" hidden="true">
			<form id="tripForm" onsubmit="return false;">
				<div>
					<input type="hidden" id="id"/>
				</div>
				<div>
					<label for="name">Name:</label> 
					<input type="text" name="name"
						id="name" required placeholder="Name" />
				</div>
				<div>
					<label for="startDate">Start date:</label> 
					<input type="text"
						name="startDate" id="startDate" class="trip-start-date date-field" required
						placeholder="DD-MM-YYYY" />
				</div>
				<div>
					<label for="endDate">End date:</label> 
					<input type="text"
						name="endDate" id="endDate" class="trip-end-date date-field" required
						placeholder="DD-MM-YYYY" />
				</div>
				<div>
					<label for="comment">Comment:</label>
					<textarea name="comment" id="comment"></textarea>
				</div>
				<div>
					<div id="trip-submit" class="button">Save</div>
					<div id="trip-cancel-submit" class="button">Cancel</div>
				</div>
			</form>
		</div>
		<div id="link-create-layer" hidden="true">
			<form id="linkForm" onsubmit="return false;">
				<div>
					<input type="hidden" id="linkId"/>
				</div>
				<div>
					<label for="name">Name:</label> 
					<input type="text" name="name"
						id="name" required placeholder="Name" />
				</div>
				<div>
					<label for="url">URL:</label> 
					<input type="text"
						name="url" id="url"
						placeholder="http://" />
				</div>
				<div>
					<label for="location">Location:</label> 
					<input type="text"
						name="location" id="location"
						placeholder="Location" />
				</div>
				<div class="eventField">
					<label for="startTime">Start time:</label> 
					<input type="text" class="date-time-field"
						name="startTime" id="startTime"
						placeholder="DD-MM-YYYY HH:mm" />
				</div>
				<div class="eventField">
					<label for="endTime">End time:</label> 
					<input type="text" class="date-time-field"
						name="endTime" id="endTime"
						placeholder="DD-MM-YYYY HH:mm" />
				</div>
				<div>
					<script type="text/javascript">
						var linkTypes = {};
						<%for(ELinkType type : ELinkType.values()) {%>
							linkTypes["<%=type.name()%>"] = "<%=type.getTitle()%>"; 
						<%} %>
					</script>
					<label for="type">Link type:</label> 
					<select name="type" id="type" required>
						<%for(ELinkType type : ELinkType.values()) {%>
							<option value="<%=type.name()%>"><%=type.getTitle()%></option>
						<%} %>
					</select>
				</div>
				<div>
					<label for="description">Description:</label>
					<textarea name="description" id="description"></textarea>
				</div>
				<div>
					<div id="event-submit" class="eventField button">Add event</div>
					<div id="link-submit" class="button">Save</div>
					<div id="link-cancel-submit" class="button">Cancel</div>
				</div>
			</form>
		</div>
		<div id="links-layer" hidden="true">
			<div id="tripInfo">
				<div class="trip-control"><h3 id="tripLabel" class="trip-label"></h3></div>
				<div id="tripAddMember" class="trip-control button">Add Member</div>
				<div id="tripEdit" class="trip-control button">Edit</div>
				<div id="tripDelete" class="trip-control button">Delete</div>
				<div id="tripDescription" class="trip-description"></div>
				<div>Trip members: <div id="tripMembers"/></div>
			</div>
			<table id="linkTable" class="display" cellspacing="0" width="100%">
				<thead>
					<tr>
						<th>Id</th>
						<th>Name</th>
						<th>Location</th>
						<th>Type</th>
						<th></th>
					</tr>
				</thead>
			</table>
			<div id="addLinkButton" class="add-button  button">Add</div>
		</div>
	</div>
	<div id="trip-timeline" class="trip-timeline">
		<div id="tripInfo">
			<div class="trip-control"><h3 id="tripLabel" class="trip-label"></h3></div>
			<div id="tripAddMember" class="trip-control button">Add Member</div>
			<div id="tripEdit" class="trip-control button">Edit</div>
			<div id="tripDelete" class="trip-control button">Delete</div>
			<div id="tripDescription" class="trip-description"></div>
		</div>
		<div id="timelineTable" class="timeline-table">
		</div>
		<div id="eventTemplate" class="event-row">
			<div id="timeColumn" class="time-column">
				<div id="date"></div>
				<div id="startTime"></div>
			</div>
			<div id="eventInformation" class="event-information">
				<div id="name" class="event-name"></div>
				<div class="event-link"><a id="url" href="">Link</a></div>
				<div id="location" class="event-location"></div>
				<div id="type" class="event-type"></div>
				<div id="description" class="event-description"></div>
				<div id="eventDetails" class="event-details">Details (+)</div>
				<div id="eventComments" class="event-comments">Comments(0)(+)</div>
			</div>
		</div>
	</div>
	<div id="add-member-layer" hidden="true">
		<table id="friendTable" class="display" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>Id</th>
					<th>Full Name</th>
				</tr>
			</thead>
		</table>
		<div id="add-member-cancel-submit" class="button">Cancel</div>
	</div>
	<div id="linkDetails" hidden="true">
		<div id="linkId" hidden="true"></div>
		<div id="description"></div>
		<div id="linkAddToTimeline" class="button">Add to timeline</div>
		<div id="linkEdit" class="button">Edit</div>
		<div id="linkDelete" class="button">Delete</div>
	</div>
</body>
</html>