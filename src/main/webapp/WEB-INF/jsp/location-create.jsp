<%@page import="ua.home.trip.enums.ERegex"%>
<div id="location-create-layer" hidden="true">
	<form id="locationForm" onsubmit="return false;">
		<div>
			<input type="hidden" id="id"/>
			<input type="hidden" id="tripId">
		</div>
		<div>
			<label for="name">Name:</label> 
			<input type="text" name="name"
				id="name" required placeholder="Name" autofocus pattern="<%=ERegex.ENG_TEXT.getExpr()%>"
				oninvalid="setCustomValidity('<%=ERegex.ENG_TEXT.getMessageText()%>')"
    			onchange="try{setCustomValidity('')}catch(e){}"/>
		</div>
		<div>
			<label for="startDate">Start date:</label> 
			<input type="text"
				name="startDate" id="startDate" class="trip-start-date date-field" required
				placeholder="DD-MM-YYYY" pattern="<%=ERegex.DATE.getExpr()%>"
				oninvalid="setCustomValidity('<%=ERegex.DATE.getMessageText()%>')"
    			onchange="try{setCustomValidity('')}catch(e){}" />
		</div>
		<div>
			<label for="endDate">End date:</label> 
			<input type="text"
				name="endDate" id="endDate" class="trip-end-date date-field" required
				placeholder="DD-MM-YYYY" pattern="<%=ERegex.DATE.getExpr()%>"
				oninvalid="setCustomValidity('<%=ERegex.DATE.getMessageText()%>')"
    			onchange="try{setCustomValidity('')}catch(e){}" />
		</div>
		<div>
			<label for="description">Description:</label>
			<textarea name="description" id="description"></textarea>
		</div>
		<div>
			<div id="location-submit" class="button">Save</div>
			<div id="location-cancel-submit" class="button">Cancel</div>
		</div>
	</form>
</div>