<%@page import="kakomon3.PersonalData"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="java.util.List"%>

<%
	List<String[]> memberList = (List<String[]>) request
			.getAttribute("memberList");
%>


<h1>問題一覧</H1>

<table class="table table-hover">
	<tr>
		<th>Mail</th>
		<th>Coin</th>
		<th>Exp</th>
		<th>Genres</th>
	</tr>


	<%
		for (String[] m : memberList) {
	%>
	<TR>
		<TH><%= m[0] %></TH>
		<td><%= m[1] %></td>
		<td><%= m[2] %></td>
	<%
		for (int i=3;i<m.length;i++) {
	%>
		<td><%= m[i] %></td>
	<%
		}
	%>
	</TR>
	<%
		}
	%>
</table>
