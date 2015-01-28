<%@page import="kakomon3.PersonalData"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="java.util.List"%>

<%
	List<String[]> mondaiList = (List<String[]>) request
			.getAttribute("mondaiList");
	List<String[]> kotaeList = (List<String[]>) request
			.getAttribute("kotaeList");
	List<String[]> genreList = (List<String[]>) request
			.getAttribute("genreList");
	String message = (String) request.getAttribute("message");

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
		<form method='post' action="/admin/mondaiAdd">
			<td><input type='text' name='mondaiId'></td>
			<td><input type='text' name='comment'></td>
			<td><select name='genreId'>
					<%
						for (String[] k : genreList) {
							out.print("<option value='");
							out.print(k[0]);
							out.print("' >");
							out.print(k[1]);
							out.print("</option>");
						}
					%>

			</select></td>
			<td>---</td>
			<td><input type='text' name='mondaiImage'></td>
			<td><select name='kotae'>
					<%
						for (String[] k : kotaeList) {
							out.print("<option value='");
							out.print(k[0]);
							out.print("' >");
							out.print(k[1]);
							out.print("</option>");
						}
					%>

			</select> <input type="submit" value="追加"></td>
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
			out.println("</td><td><a href='" + s + "'>" + m[1] + "</td>");
			out.print("<td>" + m[4] + "</td>");

			out.print("</tr>");
		}
	%>
</table>
