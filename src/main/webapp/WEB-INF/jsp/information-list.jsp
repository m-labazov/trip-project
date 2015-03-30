<%@page import="ua.home.trip.enums.ELinkType"%>
<script type="text/javascript">
	var linkTypes = {};
	<%for(ELinkType type : ELinkType.values()) {%>
		linkTypes["<%=type.name()%>"] = "<%=type.getTitle()%>"; 
	<%} %>
</script>
<div id="information-list-layer" hidden="true">
	<div id="tripInfo">
		<div><h3 id="tripLabel" class="trip-label"></h3></div>
		<div id="addInformationButton" class="trip-control button">Add information</div>
		<div id="tripEdit" class="trip-control button">Edit</div>
		<div id="tripDelete" class="trip-control button">Delete</div>
		<div id="tripDescription" class="trip-description"></div>
	</div>
	<table id="informationTable" class="display" cellspacing="0" width="100%">
		<thead>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Type</th>
				<th>&nbsp;</th>
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