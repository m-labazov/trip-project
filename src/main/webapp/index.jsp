<%@page import="ua.home.trip.enums.ELinkType"%>
<html>
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css"
	href="//cdn.datatables.net/1.10.4/css/jquery.dataTables.css">
<script type="text/javascript"
	src="http://maps.googleapis.com/maps/api/js?libraries=drawing&sensor=false"></script>
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript"
	src="//cdn.datatables.net/1.10.4/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="js/maps-handler.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<script type="text/javascript" src="js/trip-view.js"></script>
<script type="text/javascript" src="js/link-view.js"></script>
<script type="text/javascript" src="js/common-functions.js"></script>
</head>
<body>


	<div id="map-canvas" hidden="true">
		<input type="text" id="location" />
		<div id="goEvent" onclick="search()">Go!</div>
	</div>
	<div id="main-layer">
		<div id="trip-layer">
			<div id="addTripButton" class="add-button">New Trip</div>
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
						name="startDate" id="startDate" class="trip-start-date" required
						placeholder="DD-MM-YYYY" />
				</div>
				<div>
					<label for="endDate">End date:</label> 
					<input type="text"
						name="endDate" id="endDate" class="trip-end-date" required
						placeholder="DD-MM-YYYY" />
				</div>
				<div>
					<label for="comment">Comment:</label>
					<textarea name="comment" id="comment"></textarea>
				</div>
				<div>
					<div id="trip-submit">Save</div>
					<div id="trip-cancel-submit">Cancel</div>
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
					<div id="link-submit">Save</div>
					<div id="link-cancel-submit">Cancel</div>
				</div>
			</form>
		</div>
		<div id="links-layer" hidden="true">
			<div id="backToTripsButton">Back</div>
			<div id="tripInfo">
				<div class="trip-control"><h3 id="tripLabel" class="trip-label"></h3></div>
				<div id="tripEdit" class="trip-control">Edit</div>
				<div id="tripDelete" class="trip-control">Delete</div>
				<div id="tripDescription" class="trip-description"></div>
			</div>
			<table id="linkTable" class="display" cellspacing="0" width="100%">
				<thead>
					<tr>
						<th>Id</th>
						<th>Name</th>
						<th>Type</th>
					</tr>
				</thead>
			</table>
			<div id="addLinkButton" class="add-button">Add</div>
		</div>
	</div>
	<div id="linkDetails" hidden="true">
		<div id="linkId" hidden="true"></div>
		<div id="description"></div>
		<div id="linkEdit">Edit</div>
		<div id="linkDelete">Delete</div>
	</div>
</body>
</html>