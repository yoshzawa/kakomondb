<%@page import="kakomon3.PersonalData"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="java.util.List"%>

<%
	List<String[]> mondaiList = (List<String[]>) request
			.getAttribute("mondaiList");
	String message = (String) request.getAttribute("message");
	if (message == null) {
		message = "";
	}
%>

<h1>問題一覧</H1>


<table border=1>
	<tr>
		<th>ID</th>
		<th>Comment</th>
		<th>Genre</th>
		<th>Tag</th>
		<th>StorageImage</th>
		<th>Answer</th>
	</tr>
	<tr>
	<form method='post' action="/admin/listAdd">
		<td><input type='text' name='mondaiId'></td>
		<td><input type='text' name='comment'></td>
		<td><input type='text' name='genreId'></td>
		<td>---</td>
		<td><input type='text' name='mondaiImage'></td>
		<td><input type='text' name='kotae'></td>
	</form>
	</tr>
	
	<%
		for (String[] m : mondaiList) {

			String s = "http://storage.googleapis.com/"
					+ PersonalData.googleStorageBucket + "/" + m[1];

			out.print("<tr>");
			out.print("<th>" + m[0] + "</th>");
			out.print("<td>" + m[2] + "</td>");
			out.print("<td>" + m[3] + "</td>");
			out.print("<td>");
			for (int i = 5; i < m.length; i++) {
				out.print("[" + m[i] + "]");
			}
			out.println("</td><td><a href='" + s + "'>" + s + "</td>");
			out.print("<td>" + m[4] + "</td>");

			out.print("</tr>");
		}
	%>
</table>
