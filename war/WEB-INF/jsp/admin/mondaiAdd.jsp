<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="java.util.List"%>

<%
	String message = (String) request.getAttribute("message");
	if (message == null) {
		message = "メッセージがありませんでした";
	}
%>

<H2><%= message %></H2>
<a href="/admin/mondai">リストに戻る</a>