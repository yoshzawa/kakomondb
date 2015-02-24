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

<table class="table table-hover">
	<tr>
		<th>ID</th>
		<th>Comment</th>
		<th>Genre</th>
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
	%>
	<TR>
		<TH><%= m[0] %>
</TH>
		<TD>
					<button type="button"
				onClick="location.href='/admin/mondaiModify?id=<%= m[0]%>&param=comment'">
				<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
			</button><%= m[2] %> <%
		for (int i = 5; i < m.length; i++) {
			out.print(" <span class='label label-default'>#" + m[i] +"</span>");
		}
		%></TD>
		<TD>
					<button type="button"
				onClick="location.href='/admin/mondaiModify?id=<%= m[0]%>&param=genre'">
				<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
			</button><%= m[3] %></TD>
		<TD>			<button type="button"
				onClick="location.href='/admin/mondaiModify?id=<%= m[0]%>&param=storage'">
				<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
			</button>
			<a href='<%= s %>'><%= m[1] %></TD>
		<TD>			<button type="button"
				onClick="location.href='/admin/mondaiModify?id=<%= m[0]%>&param=answer'">
				<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
			</button>
			<%= m[4] %></TD>
	</TR>
	<%
		}
	%>
</table>
