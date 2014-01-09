<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <style type="text/css">
      html { height: 100% }
      body { height: 100%; margin: 0; padding: 0 }
      #map-canvas { height: 500px; width:500px }
    </style>
    <script type="text/javascript"
      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBzwkRxhM5ly3HwFjz-aYSr9XB68rXLTDk&sensor=false">
    </script>
    <script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?libraries=drawing&sensor=false"></script>
    <script type="text/javascript" src="js/jquery-1.7.2.js"></script>
    <script type="text/javascript">
    var map ;
    var markers = [];
      function initialize() {
        var mapOptions = {
          center: new google.maps.LatLng(-34.397, 150.644),
          zoom: 8
        };
        map = new google.maps.Map(document.getElementById("map-canvas"),
            mapOptions);
      google.maps.event.addListener(map, 'click', function(event) {
    	    placeMarker(event.latLng);
    	  });
      }
      function search() {
    	  var geocoder = new google.maps.Geocoder();
    	  var address = document.getElementById('location').value;
    	  geocoder.geocode( { 'address': address}, function (results, status) {
    		  console.log(address);
    		  console.log(results[0]);
    		  console.log(results[0].geometry.location);
    		  var position = results[0].geometry.location;
    		  map.setCenter(position, 12);
    		  var marker = new google.maps.Marker({
				map:map,
				position: position,
				title: results[0].formatted_address
    		  });
    	  });
      }
      

    	function placeMarker(location) {
    		console.log(location.nb + ' rrr '+ location.ob);
    	  var marker = new google.maps.Marker({
    	      position: location,
    	      map: map
    	  });
    	  markers[markers.length] = {nb:location.nb, ob:location.ob};
    	}
    	
    	function storeMarkers() {
    		$.ajax({
    			  url: "action/marker/store",
    			  data: JSON.stringify({'locations':markers}),
    			  type: 'post',
    			  contentType : 'application/json',
    			  success: function(data) {
    			alert(data);
    			console.log(markers);
    			}});
    	}
      
      google.maps.event.addDomListener(window, 'load', initialize);
    </script>
  </head>
  <body>
  
  	<input type="text" id="location" />
  	<div id="goEvent" onclick="search()">Go!</div>
  	<div id="save" onclick="storeMarkers()">Store</div>
    <div id="map-canvas"/>
    
  </body>
</html>