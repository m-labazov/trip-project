<%@page import="ua.home.trip.enums.ERegex"%>
<div id="trip-create-layer" hidden="true">
	<form id="tripForm" onsubmit="return false;">
		<div>
			<input type="hidden" id="id"/>
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
			<label for="comment">Comment:</label>
			<textarea name="comment" id="comment"></textarea>
		</div>
		<div>
			<div id="trip-submit" class="button">Save</div>
			<div id="trip-cancel-submit" class="button">Cancel</div>
		</div>
	</form>
</div>