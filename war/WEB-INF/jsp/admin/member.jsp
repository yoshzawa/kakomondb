<%@page import="kakomon3.PersonalData"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="java.util.List"%>

<%
	String[] memberList = (String[]) request
			.getAttribute("memberList");
%>


<h1>問題一覧</H1>

<table class="table table-hover">
	<tr>
		<th>Mail</th>
		<td><%=memberList[0]%></td>
	</tr>
	<tr>
		<th>Coin</th>
		<td><%=memberList[1]%></td>
	</tr>
	<tr>
		<th>Exp</th>
		<td><%=memberList[2]%></td>
	</tr>
	<tr>
		<th>Created</th>
		<td><%=memberList[3]%></td>
	</tr>
	<tr>
		<% 
			for(int i=4;i<memberList.length;i++){
		%>
		<tr>
		<th>Genre</th>
		<td><%=memberList[i]%></td>
		</tr>
		<%
			}
		%>
	</tr>



</table>
