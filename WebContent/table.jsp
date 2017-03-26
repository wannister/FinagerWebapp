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
				out.print("<br>TRY: " + it.next());
			}
		%>
	
</body>
</html>