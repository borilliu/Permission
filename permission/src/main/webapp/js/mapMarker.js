document.write("<script type='text/javascript' src='https://map.qq.com/api/js?v=2.exp&libraries=visualization&key=AGDBZ-RJWL4-E4BU5-DXABI-WAE2J-DABUL'></script>"); 
$(function() {
	var geocoder, map = null;
	var markersArray = [];
	var BaseRegion = "枝江市";
	var sw = new qq.maps.LatLng (29.681981072204525, 110.71229505761717);
	var ne = new qq.maps.LatLng (31.1668799036797, 112.78047132714842);
	var bounds = new qq.maps.LatLngBounds(sw,ne);
	map = new qq.maps.Map("container", {
		center: new qq.maps.LatLng(30.42583, 111.76044),
		zoom: 11,
		minZoom: 10,
		maxZoom: 16,
		boundary:bounds
		
	});

	document.getElementById("regionMap").addEventListener("click", function(e) {
		var target = e.target;
		var ctr=null;
		switch (target.id) {
			case "83001":
				ctr = new qq.maps.LatLng(30.42277,111.76068);
				map.panTo(ctr);
		    	map.zoomTo(13);
		    	setMapMarker(target.id);
		    	break;
			case "83101":
				ctr = new qq.maps.LatLng(30.52160,111.59223);
				map.panTo(ctr);
		    	map.zoomTo(13);
		    	setMapMarker(target.id);
				break;
			case "83103":
				ctr = new qq.maps.LatLng(30.33600,111.56613);
				map.panTo(ctr);
		    	map.zoomTo(13);
		    	setMapMarker(target.id);
		    	break;
			case "83104":
				ctr = new qq.maps.LatLng(30.40932,111.70370);
				map.panTo(ctr);
		    	map.zoomTo(13);
		    	setMapMarker(target.id);
				break;
			case "83105":
				ctr = new qq.maps.LatLng(30.52649,111.74859);
				map.panTo(ctr);
		    	map.zoomTo(13);
		    	setMapMarker(target.id);
		    	break;
			case "83106":
				ctr = new qq.maps.LatLng(30.52138,111.82990);
				map.panTo(ctr);
		    	map.zoomTo(13);
		    	setMapMarker(target.id);
				break;
			case "83107":
				ctr = new qq.maps.LatLng(30.41122,111.89280);
				map.panTo(ctr);
		    	map.zoomTo(13);
		    	setMapMarker(target.id);
		    	break;
			case "83108":
				ctr = new qq.maps.LatLng(30.40434,111.81092);
				map.panTo(ctr);
		    	map.zoomTo(13);
		    	setMapMarker(target.id);
				break;
			default:
		}
	});
	function setMapMarker(regionId){
		deleteOverlays();
		var url = "pond/findPondMapMarker";
		$.ajax({
			url : url,
			data : {"region_id":regionId},
			dataType : 'json',
			type : 'post',
			success : function(data) {
				   for (var i = 0; i < data.length; i++) {
					   	var title = data[i].title;
					   	console.log(title);
					   	var lat = data[i].lat;
					   	var lng = data[i].lng;
			            var latLng = new qq.maps.LatLng(lat,lng);
			            var marker = new qq.maps.Marker({
			                'position':latLng,
			                 map:map
			            });
			            marker.setTitle(title);
			            markersArray.push(marker);
			        }
			}
		});
	};
	function deleteOverlays() {
	    if (markersArray) {
	        for (i in markersArray) {
	            markersArray[i].setMap(null);
	        }
	        markersArray.length = 0;
	    }
	}
});


