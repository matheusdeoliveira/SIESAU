      google.charts.load("current", {packages:["corechart"]});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Task', 'Hours per Day'],
          ['Dengue',     11],
          ['Zika',      2],
          ['Chikungunya',  2],
          ['Aids', 2],
          ['Câncer',    7]
        ]);

        var options = {
          title: 'Doenças',
          is3D: true,
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
        chart.draw(data, options);
      }
