<%@page import="kakomon3.PersonalData"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="java.util.List"%>

<%
	List<String[]> genreList = (List<String[]>) request
			.getAttribute("genreList");
%>


<h1>ジャンル一覧</H1>

<table class="table table-hover">
	<tr>
		<th>ID</th>
		<th>Comment</th>
		<th>問題数
			<button type="button" onClick="location.href='/admin/genreReload'">
				<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
			</button>
		</th>
	</tr>
	<tr>
		<form method='post' action="/admin/genreAdd">

			<td><input type='text' name='genreId'></td>
			<td><input type='text' name='genreName'><input
				type="submit" value="追加"></td>
			<td></td>
		</form>

	</tr>

	<%
		for (String[] m : genreList) {

			%>
	<TR>
		<TH><%= m[0] %>
			<button type="button"
				onClick="location.href='/admin/modifyGenre?id=<%= m[0]%>'">
				<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
			</button></TH>
		<TD><%= m[1] %></TD>
		<TD><%= m[2] %></TD>
	</TR>
	<% 
		}
	%>
</table>
