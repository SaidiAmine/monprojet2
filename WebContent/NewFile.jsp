<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
  <head>
  <title>YO YO YO</title>
  </head>


  <body>
  	
    <table id="tab1">
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
    </table>
    
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="http://canvasjs.com/assets/script/canvasjs.min.js"></script>
<script type="text/javascript">

window.onload = function () {
	var pstr = document.getElementById('3').text;
	var pint = parseInt(pstr);
	
	var param1 = <%out.print(request.getAttribute("param0")); %>
	var param2= <%out.print(request.getAttribute("param1")); %>
	var param3= <%out.print(request.getAttribute("param2")); %>
	var param4= <%out.print(request.getAttribute("param3")); %>
	var param5= <%out.print(request.getAttribute("param4")); %>
	
	
	var chart = new CanvasJS.Chart("chartContainer", {
		theme: "theme2",//theme1
		title:{
			text: "Tickets dans la BDD selon leurs états"              
		},
		animationEnabled: false,   // change to true
		data: [              
		{
			// Change type to "bar", "area", "spline", "pie", "column" etc.
			type: "pie",
			dataPoints: [
				{ label: "En attente de prise en charge",  y: param1 },
				{ label: "Non défini", y: param2 },
				{ label: "En attente de validation", y: param3  },
				{ label: "En cours de traitement",  y: param4  },
				{ label: "Cloturé",  y: param5  }
			]
		}
		]
	});
	chart.render();
}
</script>
<div id="chartContainer" style="height: 300px; width: 100%;"></div>
    
      </body>
</html>
