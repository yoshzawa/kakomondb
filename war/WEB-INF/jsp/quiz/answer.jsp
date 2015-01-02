<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="java.util.List"%>

<%
	String[] m = (String[]) request.getAttribute("mondaiList");
%>

<!DOCTYPE html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bootstrap 101 Template</title>

<!-- Bootstrap -->
<link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
</head>
<body>
	<div class="container">

		<h1 class="h1">答え合わせ</H1>

		<%
			String s = "https://storage.googleapis.com/kakomondb/" + m[1];

			out.println("<hr>");
			out.print("<h2>" + m[0] + "");
			out.println("<small>" + m[2] + "</small></h2>");
			out.println("<br><img src='" + s
					+ "' width=800 class='img-thumbnail'><br>");
		%>
		解答：<%= m[3] %>　正解：<%= m[4] %><br>
		<%= m[5] %>

		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="/bootstrap/js/bootstrap.min.js"></script>

	</div>

</body>
</html>