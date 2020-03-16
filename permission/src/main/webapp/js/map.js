document.write("<script type='text/javascript' src='https://map.qq.com/api/js?v=2.exp&libraries=visualization&key=AGDBZ-RJWL4-E4BU5-DXABI-WAE2J-DABUL'></script>"); 
$(function() {
	var map = new qq.maps.Map("container", {
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
				style: {
					fillColor: "#3CF",
					strokeColor: "#FFF",
					strokeWidth: 1,
					radius: 2
				}
			});
			dots.setData(data);
			let groupBy = "status";
			dots.setGroupStyle("1", {
				strokeColor: "#FFF",
				fillColor: "#0D0",
				strokeWidth: 1,
				radius: 3
			});
			dots.setGroupStyle("2", {
				strokeColor: "#FFF",
				fillColor: "#FA3",
				strokeWidth: 1,
				radius: 3
			});
			dots.setGroupBy(groupBy);
		}
	});
});


