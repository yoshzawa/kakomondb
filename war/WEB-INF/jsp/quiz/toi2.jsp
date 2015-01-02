<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.List" %>

<%
String[]m = (String[])request.getAttribute("mondaiList");
%>

<h1>問題</H1>

<%
		 
			String s = "https://storage.googleapis.com/kakomondb/" + m[1];

			out.print("<hr>");
			out.print("<h3>" + m[0] + "</h3>");
			out.print("<h3>" + m[2] + "</h3>");
			out.println("<br><img src='" + s + "' width=1000><br>");
			out.print("<p>" + m[3] + "</p>");
			

%>