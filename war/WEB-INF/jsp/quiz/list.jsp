<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="java.util.List"%>

<%
	List<String[]> mondaiList = (List<String[]>) request
			.getAttribute("mondaiList");
%>

<h1>問題一覧</H1>

<a href="/quiz/list">問題順 </a>
ジャンル指定 タグ指定

<table class="table table-striped table-bordered">
	<tr>
		<th>問題</th>
		<th>ジャンル</th>
		<th>タグ</th>
	</tr>
	<%
		for (String[] m : mondaiList) {
	%>
	<tr>
		<th><a href='/quiz/toi?key=<%=m[0]%>'><%=m[2]%></a></th>
		<td><%=m[3]%></td>
		<td>
			<%
				for (int i = 5; i < m.length; i++) {
						out.print("[" + m[i] + "] ");
					}
			%>
		</td>
	</tr>
	<%
		}
	%>
</table>
