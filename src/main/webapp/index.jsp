<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <script type="text/javascript"
      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBzwkRxhM5ly3HwFjz-aYSr9XB68rXLTDk&sensor=false">
    </script>
    <script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?libraries=drawing&sensor=false"></script>
    <script type="text/javascript" src="js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="js/main.js"></script>
  </head>
  <body>
  
  	<input type="text" id="location" />
  	<div id="goEvent" onclick="search()">Go!</div>
  	<div id="save" onclick="storeMarkers()">Store</div>
    
    <div id="main-layer">
    	<div id="map-canvas"></div>
    	<div id="trip-layer">
    		<input id="trip-add-name" type="text"/>
			<div id="trip-create">Create trip</div>    	
    	</div>
    	<div id="links-layer">
    		<div id="trip-search">
    			<input id="trip-search-name" type="text"/>
				<div id="trip-open">Open trip</div>
    		</div>
   			<div id="linkListHeader" class="links-table-header">
   				<div id="linkNameHeader" class="link-name-header">Name</div>
   				<div id="linkTypeHeader" class="link-type-header">Type</div>
   				<div id="linkUrlHeader" class="link-url-header"></div>
   			</div>
    		<div id="linkList" class="links-table">
    		</div>
		    <div id="createLinkLayer" hidden="true">
		    	<input type="hidden" id ="linkId"/>
				<input type="text" id="linkName" />
				<select id="linkType"></select>
				<input type="text" id="linkUrl"/>
				<div id="okLinkButton" class="link-button">Ok</div>
		    </div>
    		<div id="addLinkButton" style="text-decoration: underline">Add</div>
   			<div id="createLinkContainer" class="row-container">
   				<input type="hidden" id ="linkId"/>
   				<div id="linkName" class="link-name"></div>
   				<div id="linkType" class="link-type"></div>
   				<div id="linkUrl" class="link-url"><a class="trip-link" href="" target="_tab">Link</a></div>
   				<div class="link-edit">Edit</div>
   				<div class="link-delete">Delete</div>
   			</div>
    	</div>
    </div>
    
  </body>
</html>