document.write("<script type='text/javascript' src='https://map.qq.com/api/js?v=2.exp&libraries=visualization&key=AGDBZ-RJWL4-E4BU5-DXABI-WAE2J-DABUL'></script>"); 
$(function() {
	var geocoder, map = null;
	var BaseRegion = "枝江市";
	map = new qq.maps.Map("container", {
		center: new qq.maps.LatLng(30.42583, 111.76044),
		zoom: 11
	});
	var url = "pond/findPondMapData";
	$.ajax({
		url : url,
		//data : {"region_id":"83001"},
		data : {},
		dataType : 'json',
		type : 'post',
		success : function(data) {
			var dots = new qq.maps.visualization.Dots({
				map: map, // 必填参数，指定显示散点图的地图对象
				groupBy:"status",
				groupStyles:{
				"2":{
					fillColor: "#0D0",
					strokeColor: "#FFF",
					strokeWidth: 1,
					radius: 4
				},
				"1":{
						fillColor: "#0D0",
						strokeColor: "#FFF",
						strokeWidth: 1,
						radius: 4
				},	
				"0":{
					fillColor: "#F00",
					strokeColor: "#FFF",
					strokeWidth: 1,
					radius: 4
			}	
				},
				
			});
			dots.setData(data);
		}
	});
	document.getElementById("regionMap").addEventListener("click", function(e) {
		var target = e.target;
		var ctr=null;
		switch (target.id) {
			case "83000":
				ctr = new qq.maps.LatLng(30.42583,111.76044);
				map.panTo(ctr);
		    	map.zoomTo(11);
				break;
			case "83001":
				ctr = new qq.maps.LatLng(30.42277,111.76068);
				map.panTo(ctr);
		    	map.zoomTo(13);
		    	break;
			case "83101":
				ctr = new qq.maps.LatLng(30.52160,111.59223);
				map.panTo(ctr);
		    	map.zoomTo(13);
				break;
			case "83103":
				ctr = new qq.maps.LatLng(30.33600,111.56613);
				map.panTo(ctr);
		    	map.zoomTo(13);
		    	break;
			case "83104":
				ctr = new qq.maps.LatLng(30.40932,111.70370);
				map.panTo(ctr);
		    	map.zoomTo(13);
				break;
			case "83105":
				ctr = new qq.maps.LatLng(30.52649,111.74859);
				map.panTo(ctr);
		    	map.zoomTo(13);
		    	break;
			case "83106":
				ctr = new qq.maps.LatLng(30.52138,111.82990);
				map.panTo(ctr);
		    	map.zoomTo(13);
				break;
			case "83107":
				ctr = new qq.maps.LatLng(30.41122,111.89280);
				map.panTo(ctr);
		    	map.zoomTo(13);
		    	break;
			case "83108":
				ctr = new qq.maps.LatLng(30.40434,111.81092);
				map.panTo(ctr);
		    	map.zoomTo(13);
				break;
			default:
		}
	});
});


