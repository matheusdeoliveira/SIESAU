var map, heatmap;

function initMap() {
	map = new google.maps.Map(document.getElementById('map'), {
		zoom : 13,
		center : {
			lat : 37.775,
			lng : -122.434
		},
		mapTypeId : google.maps.MapTypeId.SATELLITE
	});

	heatmap = new google.maps.visualization.HeatmapLayer({
		data : getPoints(),
		map : map
	});
}

function toggleHeatmap() {
	heatmap.setMap(heatmap.getMap() ? null : map);
}

function changeGradient() {
	var gradient = [ 'rgba(0, 255, 255, 0)', 'rgba(0, 255, 255, 1)',
			'rgba(0, 191, 255, 1)', 'rgba(0, 127, 255, 1)',
			'rgba(0, 63, 255, 1)', 'rgba(0, 0, 255, 1)', 'rgba(0, 0, 223, 1)',
			'rgba(0, 0, 191, 1)', 'rgba(0, 0, 159, 1)', 'rgba(0, 0, 127, 1)',
			'rgba(63, 0, 91, 1)', 'rgba(127, 0, 63, 1)', 'rgba(191, 0, 31, 1)',
			'rgba(255, 0, 0, 1)' ]
	heatmap.set('gradient', heatmap.get('gradient') ? null : gradient);
}

function changeRadius() {
	heatmap.set('radius', heatmap.get('radius') ? null : 20);
}

function changeOpacity() {
	heatmap.set('opacity', heatmap.get('opacity') ? null : 0.2);
}

 
var coord=[["37.782551", "-122.445368"],["37.782745", "-122.444586"],["37.782842", "-122.443688"]];

// Heatmap data: 500 Points
	function getPoints() {
		
		return [ 
				new google.maps.LatLng(37.782551, -122.445368),
				new google.maps.LatLng(37.782745, -122.444586),
				new google.maps.LatLng(37.782842, -122.443688),
		        new google.maps.LatLng(37.782919, -122.442815),
				new google.maps.LatLng(37.782992, -122.442112),
				new google.maps.LatLng(37.770992, -122.410477)
		];
	}

 






