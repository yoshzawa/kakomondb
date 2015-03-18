<%@page import="com.google.appengine.api.users.*"%>
<%@page import="kakomon3.PersonalData"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="java.util.List"%>

<%
	String url = (String) request.getAttribute("jsp_url");
	//	HttpSession session = request.getSession();
	UserService service = UserServiceFactory.getUserService();
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

<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', '<%=PersonalData.googleAnalyticsId%>
	', 'auto');
	ga('send', 'pageview');
</script>
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand">過去問学習システム</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="/">TOP</a></li>
				<%
					if (service.isUserLoggedIn()) {
				%>
				<li><a href="/quiz/list">問題一覧</a></li>
				<li><a href="/quiz/bunseki">結果分析1</a></li>
				<li><a href="/quiz/bunseki2">結果分析2</a></li>
				<%
					} else {
				%>
				<li><a>問題一覧</a></li>
				<li><a>結果分析1</a></li>
				<li><a>結果分析2</a></li>
				<%
					}
				%>
			</ul>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<%
					if (service.isUserLoggedIn()) {
						String loginName = service.getCurrentUser().getNickname();
						String isAdmin = service.isUserAdmin() ? "<B>admin</B>:" : "";
				%>
				<p class="navbar-text">
					[<%=isAdmin + loginName%>]でログイン
				</p>
				<li><a href="/logout"> <span
						class="glyphicon glyphicon glyphicon-log-in"></span> logout
				</a></li>
				<%
					} else {
						String loginurl = service.createLoginURL("/");
				%>
				<li><a href="<%=loginurl%>"> <span
						class="glyphicon glyphicon glyphicon-log-out"></span> login
				</a></li>
				<%
					}
				%>
			
		</div>
	</nav>
	<% if ((service.isUserLoggedIn())&&(service.isUserAdmin())){ %>
	<nav class="navbar navbar-inverse">
	    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand">管理者ツール</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="/admin/init">Initializer</a></li>
            <li><a href="/admin/mondai">List of "Mondai"</a></li>
            <li><a href="/admin/tag">List of "Tag"</a></li>
            <li><a href="/admin/genre">List of "Genre"</a></li>
            <li><a href="/admin/memberList">List of "Member"</a></li>
        </ul>
    </div>
	</nav>
	<% } %>
	<div class="container">

		<jsp:include page="<%=url%>" />

	</div>

</body>
</html>