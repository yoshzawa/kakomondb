<%@page import="kakomon3.PersonalData"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="java.util.List"%>
<%
	String[] memberStatus = (String[]) request
			.getAttribute("memberStatus");
%>

<html>
<body>
	<h1>情報処理試験 過去問学習システム</h1>


	<table class="table table-striped table-bordered">
		<Tr>
			<th>名前</th>
			<td><%=memberStatus[0]%>(登録日：<%=memberStatus[1]%>)</td>
		</Tr>
		<Tr>
			<th>coin</th>
			<td><%=memberStatus[2]%></td>
		</Tr>
		<Tr>
			<th>exp</th>
			<td><%=memberStatus[3]%></td>
		</Tr>
	</table>
	<p>
		詳しくは<a href='http://yoshzawa.github.io/kakomondb/'>github</a>で確認。
	</p>
	<p>
		version:<%=PersonalData.appVersion%></p>

</body>
</html>
