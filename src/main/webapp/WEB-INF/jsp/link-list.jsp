<%@page import="ua.home.trip.enums.ELinkType"%>
<script type="text/javascript">
	var linkTypes = {};
	<%for(ELinkType type : ELinkType.values()) {%>
		linkTypes["<%=type.name()%>"] = "<%=type.getTitle()%>"; 
	<%} %>
</script>
<div id="links-layer" hidden="true">
	<div id="tripInfo">
		<div><h3 id="tripLabel" class="trip-label"></h3></div>
		<div id="addLinkButton" class="trip-control button">New Link</div>
		<div id="tripAddMember" class="trip-control button">Add Member</div>
		<div id="tripEdit" class="trip-control button">Edit</div>
		<div id="tripDelete" class="trip-control button">Delete</div>
		<div id="tripDescription" class="trip-description"></div>
		<div>Trip members: <div id="tripMembers"></div>
		</div>
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
	<div id="linkDetails" hidden="true">
		<div id="linkId" hidden="true"></div>
		<div id="description"></div>
		<div id="linkAddToTimeline" class="button">Add to timeline</div>
		<div id="linkEdit" class="button">Edit</div>
		<div id="linkDelete" class="button">Delete</div>
	</div>
</div>
