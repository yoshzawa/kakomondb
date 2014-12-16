<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.List" %>

<%
List<String[]> mondaiList = (List<String[]>)request.getAttribute("mondaiList");
%>

<h1>問題一覧</H1>

<%
		 for ( String[] m : mondaiList) {
		 
			String s = "https://storage.googleapis.com/kakomondb/" + m[0];

			out.print("<hr>");
			out.print("<h3>" + m[1] + "</h3>");
			out.print("genre:" + m[2]);
			out.print("<br>tag:");
			for(int i=3 ; i < m.length ; i++){
				out.print("[" + m[i] + "]");
			}
			out.println("<br><img src='" + s + "' width=1000>");
			
		}
%>