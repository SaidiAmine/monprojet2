<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
  <head>
  <head>
    <!--Load the AJAX API-->
    
    <link rel="stylesheet" href="inc/gstatic.com">
    <script type="text/javascript" src="inc/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="inc/gstatic.com"></script>s
    <script type="text/javascript">
       var jsonData = [];
       $(document).ready(function(){
           $.ajax({url: "/monprojet2/test?", success: function(result){
               jsonData = result.map;
           }});
       });


      // Load the Visualization API and the corechart package.
      google.charts.load('current', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.charts.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {

        // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Etat');
        data.addColumn('number', 'Nombre');
        for (var key in jsonData) {
          data.addRow([key,jsonData[key]]);
         }

        // Set chart options
        var options = {'title':'Tickets par Etat',
                       'width':400,
                       'height':300};

        // Instantiate and draw our chart, passing in some options. -- ColumnChart
        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }
    </script>
    <script type="text/javascript">
    var jsonData2 = [];
    $(document).ready(function(){
        $.ajax({url: "/monprojet2/test2?", success: function(result){
            jsonData2 = result.map;
        }});
    });


   // Load the Visualization API and the corechart package.
   google.charts.load('current', {'packages':['corechart']});

   // Set a callback to run when the Google Visualization API is loaded.
   google.charts.setOnLoadCallback(drawChart);

   // Callback that creates and populates a data table,
   // instantiates the pie chart, passes in the data and
   // draws it.
   function drawChart() {

     // Create the data table.
     var data2 = new google.visualization.DataTable();
     data2.addColumn('string', 'Technicien');
     data2.addColumn('number', 'Tickets');
     for (var key in jsonData2) {
       data2.addRow([key,jsonData2[key]]);
      }

     // Set chart options
     var options2 = {'title':'Tickets pris en charge par Technicien',
                    'width':400,
                    'height':300};

     // Instantiate and draw our chart, passing in some options. -- ColumnChart
     var chart2 = new google.visualization.ColumnChart(document.getElementById('chart_div2'));
     chart2.draw(data2, options2);
   }

    </script>
    <script type="text/javascript">
    var jsonData3 = [];
    $(document).ready(function(){
        $.ajax({url: "/monprojet2/test3?", success: function(result){
            jsonData3 = result.map;
        }});
    });


   // Load the Visualization API and the corechart package.
   google.charts.load('current', {'packages':['corechart']});

   // Set a callback to run when the Google Visualization API is loaded.
   google.charts.setOnLoadCallback(drawChart);

   // Callback that creates and populates a data table,
   // instantiates the pie chart, passes in the data and
   // draws it.
   function drawChart() {

     // Create the data table.
     var data3 = new google.visualization.DataTable();
     data3.addColumn('string', 'Employé');
     data3.addColumn('number', 'Tickets');
     for (var key in jsonData3) {
       data3.addRow([key,jsonData3[key]]);
      }

     // Set chart options
     var options3 = {'title':'Tickes déposés par Employé',
                    'width':400,
                    'height':300};

     // Instantiate and draw our chart, passing in some options. -- ColumnChart
     var chart3 = new google.visualization.ColumnChart(document.getElementById('chart_div3'));
     chart3.draw(data3, options3);
   }
   </script>
  </head>
  <title>Statistiques</title>
  </head>


  <body>
  <c:if test="${masession.type=='Admin'}"><c:import url="/inc/menuadmin.jsp" /></c:if>
 <c:if test="${masession.type=='Admin'}">
    <%-- <table id="tab1">
    <tr>
    <th>Etat</th>
    <th>Nombre</th>
    </tr>
    <c:forEach items="${lticket}" var="ticket" varStatus="theCount">
    <tr>
    <td>${ticket[0]}  </td>
    <td id="${theCount.count}">${ticket[1]}  </td>
    </tr>
    </c:forEach>
    </table>--%>
<div class="container">
<div class="row">
<div class="col-sm-4">
<div id="chart_div"></div>
</div>

<div class="col-sm-4">
	<div id="chart_div2"></div>
</div>
</div>
<div class="row">
<div class="col-sm-4">
<div id="chart_div3"></div>
</div>
</div>
</div>
</c:if>
</body>
</html>
