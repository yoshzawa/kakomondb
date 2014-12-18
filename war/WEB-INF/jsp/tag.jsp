<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.List" %>

<%
List<String[]> tagList = (List<String[]>)request.getAttribute("tagList");
%>

<h1>タグ一覧</H1>

<%
		 for ( String[] m : tagList) {
		 
			String s = "https://storage.googleapis.com/kakomondb/" + m[1];

			out.print("<hr>");
			out.print("<h2>" + m[0] + "</h2>");
			out.print("genre:" + m[2]);
			out.print("<br>comment:" + m[3]);
			out.print("<br>tag:");
			for(int i=4 ; i < m.length ; i++){
				out.print("[" + m[i] + "]");
			}
			out.println("<br><img src='" + s + "' width=1000>");
			
		}
%>