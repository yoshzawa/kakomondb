<%@page import="kakomon3.PersonalData"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="java.util.List"%>

<%
	String[] m = (String[]) request.getAttribute("mondaiList");
%>

<!DOCTYPE html>


<body>

		<h1 class="h1">答え合わせ</H1>

		<%
			String s = "http://storage.googleapis.com/"+PersonalData.googleStorageBucket+"/" + m[1];

			out.println("<hr>");
			out.print("<h2>" + m[0] + "");
			out.println("<small>" + m[2] + "</small></h2>");
			out.println("<br><img src='" + s
					+ "' width=800 class='img-thumbnail'><br>");
		%>
		<P>解答：<%= m[3] %>　
		<%= m[5] %><br>
		<%= m[7] %>ポイントのExpとcoinを獲得</br>
		<a href='/quiz/bunseki'>結果分析</a>
		</P>
		



