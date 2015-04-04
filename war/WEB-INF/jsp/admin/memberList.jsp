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
		<th>Genres
			<button type="button" onClick="location.href='/admin/memberReload'">
				<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
			</button>
		</th>
	</tr>


	<%
		for (String[] m : memberList) {
	%>
	<TR>
		<TH><a href="/admin/member?id=<%=m[0]%>"><%=m[0]%></a></TH>
		<td><%=m[1]%></td>
		<td><%=m[2]%></td>
		<%
			for (int i = 3; i < m.length; i++) {
		%>
		<td><%=m[i]%></td>
		<%
			}
		%>
	</TR>
	<%
		}
	%>
</table>
