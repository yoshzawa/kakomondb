<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="java.util.List"%>

<%
	String url  = (String) request.getAttribute("jsp_url");
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

<ul class="nav nav-pills">
    <li><a href="/quiz/bunseki">結果分析</a></li>
    <li><a href="/admin/ichiran.jsp">管理者用</a></li>
    <li><a href="/quiz/list">問題一覧</a></li>
</ul>

<jsp:include 	page= "<%= url %>" />

	</div>

</body>
</html>