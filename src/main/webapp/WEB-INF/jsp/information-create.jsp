<%@page import="ua.home.trip.enums.ELinkType"%>
<%@page import="ua.home.trip.enums.ERegex"%>
<div id="information-create-layer" hidden="true">
	<form id="informationForm" onsubmit="return false;">
		<div>
			<input type="hidden" id="id"/>
			<input type="hidden" id="locationId">
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
			<label for="type">Information type:</label> 
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
			<div id="information-submit" class="button">Save</div>
			<div id="information-cancel-submit" class="button">Cancel</div>
		</div>
	</form>
</div>
