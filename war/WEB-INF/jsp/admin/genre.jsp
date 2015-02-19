<%@page import="kakomon3.PersonalData"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="java.util.List"%>

<%
	List<String[]> genreList = (List<String[]>) request
			.getAttribute("genreList");
%>


<h1>ジャンル一覧</H1>

<table border=1>
	<tr>
		<th>ID</th>
		<th>Comment</th>
		<th>問題数</th>
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
			out.print("<tr>");
			out.print("<th>" + m[0] + "</th>");
			out.print("<td>" + m[1] + "</td>");
			out.print("<td>" + m[2] + "</td>");
			out.print("</tr>");
		}
	%>
</table>
