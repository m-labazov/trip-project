<%@page import="ua.home.trip.enums.ELinkType"%>
<%@page import="ua.home.trip.enums.ERegex"%>
<div id="link-create-layer" hidden="true">
	<form id="linkForm" onsubmit="return false;">
		<div>
			<input type="hidden" id="linkId"/>
		</div>
		<div>
			<label for="name">Name:</label> 
			<input type="text" name="name"
				id="name" required placeholder="Name" pattern="<%=ERegex.ENG_TEXT.getExpr()%>"
				oninvalid="setCustomValidity('<%=ERegex.ENG_TEXT.getMessageText()%>')"
    			onchange="try{setCustomValidity('')}catch(e){}"/>
		</div>
		<div>
			<label for="url">URL:</label> 
			<input type="text"
				name="url" id="url"
				placeholder="http://" 
				pattern="<%=ERegex.URL.getExpr()%>"
				oninvalid="setCustomValidity('<%=ERegex.URL.getMessageText()%>')"
    			onchange="try{setCustomValidity('')}catch(e){}"/>
		</div>
		<div>
			<label for="location">Location:</label> 
			<input type="text"
				name="location" id="location"
				placeholder="Location" pattern="<%=ERegex.ENG_TEXT.getExpr()%>"
				oninvalid="setCustomValidity('<%=ERegex.ENG_TEXT.getMessageText()%>')"
    			onchange="try{setCustomValidity('')}catch(e){}" />
		</div>
		<div class="eventField">
			<label for="startTime">Start time:</label> 
			<input type="text" class="date-time-field"
				name="startTime" id="startTime"
				placeholder="DD-MM-YYYY HH:mm"  pattern="<%=ERegex.DATE.getExpr()%>"
				oninvalid="setCustomValidity('<%=ERegex.DATE.getMessageText()%>')"
    			onchange="try{setCustomValidity('')}catch(e){}"/>
		</div>
		<div class="eventField">
			<label for="endTime">End time:</label> 
			<input type="text" class="date-time-field"
				name="endTime" id="endTime"
				placeholder="DD-MM-YYYY HH:mm"  pattern="<%=ERegex.DATE.getExpr()%>"
				oninvalid="setCustomValidity('<%=ERegex.DATE.getMessageText()%>')"
    			onchange="try{setCustomValidity('')}catch(e){}"/>
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
			<textarea name="description" id="description" pattern="<%=ERegex.ENG_TEXT.getExpr()%>"></textarea>
		</div>
		<div>
			<div id="event-submit" class="eventField button">Add event</div>
			<div id="link-submit" class="button">Save</div>
			<div id="link-cancel-submit" class="button">Cancel</div>
		</div>
	</form>
</div>
