<%@ page import="java.util.*"%>
<html>
<head>
<title>Result</title>
</head>
<body>
	<h1 align="center">Expenditure table</h1>
	<p>
		<%
			List styles = (List) request.getAttribute("block");
			Iterator it = styles.iterator();
			while (it.hasNext()) {
				out.print("<br>" + it.next());
			}
		%>
	</p>
	<form method="POST" action="UserChoice.do">
		<p>
			Display Exact Amount from a Choice of Provinces or Territories: 
			<select name="prov_choice" size="1">
				<option>Alberta</option>
				<option>British Columbia</option>
				<option>Manitoba</option>
				<option>New Brunswick</option>
				<option>Newfoundland and Labrador</option>
				<option>Northwest Territories</option>
				<option>Nova Scotia</option>
				<option>Nunavut</option>
				<option>Ontario</option>
				<option>Prince Edward Island</option>
				<option>Quebec</option>
				<option>Saskatchewan</option>
				<option>Yukon</option>
				<option>      ------</option>
			</select>
		</p>
		<center>
		    <br> <br>
			<input type="SUBMIT">
		</center>
	</form>
	
</body>
</html>