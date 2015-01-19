<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.List" %>

<%
String[] result = (String[])request.getAttribute("result");
List<String[]> genreResult = (List<String[]>)request.getAttribute("genreResult");

%>

<h1>結果一覧</H1>

<%= result[0] %>の今までの結果
<table border=1>
<tr><th>正解</th><td><%= result[1] %>問</td></tr>
<tr><th>不正解</th><td><%= result[2] %>問</td></tr>
</table>

<table border=1>
<% for(String[] s : genreResult){
%>
<tr>
<th><%= s[0] %></th>
<td><%= s[1] %></td>
<td><%= s[2] %></td>
<td><%= s[3] %></td>
</tr>
<% }
%>
</table>
