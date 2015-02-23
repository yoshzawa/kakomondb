<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.List" %>

<%
String[] result = (String[])request.getAttribute("result");
List<String[]> mondaiResult = (List<String[]>)request.getAttribute("mondaiResult");

%>

<h1>結果一覧</H1>

<%= result[0] %>の今までの結果
<table border=1>
<tr><th>正解</th><td><%= result[1] %>問</td></tr>
<tr><th>不正解</th><td><%= result[2] %>問</td></tr>
</table>

      <table class="table table-striped table-bordered">
<th>問題</th>
<th>正解</th>
<th>不正解</th>
<th>合計</th>
<% for(String[] s : mondaiResult){
%>
<tr>
<th><a href="/quiz/toi?key=<%= s[0] %>">
<%= s[1] %></a> 
<% 
for (int i=5;i<s.length;i++){
	out.print(" <span class='label label-default '>#"  + s[i]+"</span>");
}
%>
</td>
<td align="right"><%= s[2] %>
</td>
<td align="right"><%= s[3] %></td>
<td align="right"><%= s[4] %></td>
</tr>
<% }
%>
</table>
