<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.List" %>

<%
List<String[]> mondaiList = (List<String[]>)request.getAttribute("mondaiList");
%>

<h1>問題一覧</H1>

<table border=1>
<tr><th>問題</th><td>ジャンル</td><td>タグ</td></tr>
<%
		 for ( String[] m : mondaiList) {
%>
	<tr>
	<th><a href='/quiz/toi?key=<%= m[0]%>'><%= m[2] %></a></th>
	<td><%= m[3] %></td>
	<td>
<% 		 
			for(int i=5 ; i < m.length ; i++){
				out.print("[" + m[i] + "]");
			}
%>
	</td></tr>
<% 			
		}
%>
</table>
