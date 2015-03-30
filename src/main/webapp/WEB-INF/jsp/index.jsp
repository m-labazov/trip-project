<%@page import="org.springframework.social.security.SocialAuthenticationToken"%>
<%@page import="ua.home.trip.api.data.IUser"%>

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
<script type="text/javascript" src="js/trip-members.js"></script>
<script type="text/javascript" src="js/location.js"></script>
<script type="text/javascript" src="js/information.js"></script>
</head>
<body>

	<div id="initParams" hidden="true">
		<div id="startTab">${tab}</div>
		<div id="id">${id}</div>
	</div>
	<div id="pageHeader" class="page-header">
		<div id="profileImage" class="profile-image-container"><img src="action/user/profileImage" class="profile-image" /></div>
		<div id="userFullName" class="user-full-name"><%= ((IUser)((SocialAuthenticationToken)request.getUserPrincipal()).getPrincipal()).getName() %></div>
		<div class="header-controls">
			<div id="menu-button" class="button menu-button">Menu</div>
			<div id="message-button" class="message-button">Messages</div>
			<div id="logout-button" class="button logout-button"><form method="post" id="logoutForm" action="logout">Logout</form></div>
		</div>
	</div>
	<div id="menu" class="menu">
		<div id="tripListButton" class="button">All trips</div>
		<div id="tripDraftButton" class="menu-optional-fields button">Trip information</div>
		<div id="tripTimelineButton" class="menu-optional-fields button">Timeline</div>
		<div id="tripMembersButton" class="menu-optional-fields button">Members</div>
	</div>
	<div id="map-canvas" hidden="true">
		<input type="text" id="location" />
		<div id="goEvent" onclick="search()">Go!</div>
	</div>
	<div id="main-layer">
		<section id="trip-list" class="container" src="display?tab=trip-list"></section>
		<section id="trip-create" class="container" src="display?tab=trip-create"></section>
		<section id="trip-member-add" class="container" src="display?tab=trip-member-add"></section>
		<section id="trip-members" class="container" src="display?tab=trip-members"></section>
		<section id="link-list" class="container" src="display?tab=link-list"></section>
		<section id="link-create" class="container" src="display?tab=link-create"></section>
		<section id="timeline" class="container" src="display?tab=timeline"></section>
		<section id="location-create" class="container" src="display?tab=location-create"></section>
		<section id="location-list" class="container" src="display?tab=location-list"></section>
		<section id="information-create" class="container" src="display?tab=information-create"></section>
		<section id="information-list" class="container" src="display?tab=information-list"></section>
	</div>
</body>
</html>