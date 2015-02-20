<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="java.util.List"%>

<%
	List<String[]> genreList = (List<String[]>) request
			.getAttribute("genreList");
%>

<h1>問題一覧</H1>

<a href="/quiz/list">問題順 </a>
ジャンル指定 タグ指定

<table class="table table-striped table-bordered">
	<tr>
		<th>ジャンル</th>
		<th>正解</th>
		<th>不正解</th>
		<th>まだ</th>
	</tr>
	<%
		for (String[] m : genreList) {
	%>
	<tr>
		<th><a href='/quiz/genre?key=<%=m[0]%>'><%=m[1]%></a></th>
		<td><%=m[2]%></td>
		<td><%=m[3]%></td>
		<td><%=m[4]%></td>
	</tr>
	<%
		}
	%>
</table>
