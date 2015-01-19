<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.List" %>

<%
List<String[]> mondaiList = (List<String[]>)request.getAttribute("mondaiList");
%>

<h1>問題一覧</H1>

<%
		 for ( String[] m : mondaiList) {
		 
			String s = "http://storage.googleapis.com/kakomondb/" + m[1];

			out.print("<hr>");
			out.print("<h3>" + m[0] + "</h3>");
			out.print("<h3>" + m[2] + "</h3>");
			out.print("ジャンル:" + m[3]);
			out.print("<br>タグ:");
			for(int i=5 ; i < m.length ; i++){
				out.print("[" + m[i] + "]");
			}
			out.println("<br><img src='" + s + "' width=1000><br>");
			out.print("答え:" + m[3]);
			
		}
%>