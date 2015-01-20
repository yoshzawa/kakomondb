<%@page import="kakomon3.PersonalData"%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.List" %>

<%
List<String[]> tagList = (List<String[]>)request.getAttribute("tagList");
%>

<h1>タグ一覧</H1>

<%
		 for ( String[] m : tagList) {
		 
			String s = "http://storage.googleapis.com/"+PersonalData.googleStorageBucket+"/" + m[1];

			out.print("<hr>");
			out.print("<h2>" + m[0] + "</h2>");
			out.print("ジャンル:" + m[2]);
			out.print("<br>コメント:" + m[3]);
			out.print("<br>タグ:");
			for(int i=4 ; i < m.length ; i++){
				out.print("[" + m[i] + "]");
			}
			out.println("<br><img src='" + s + "' width=1000>");
			
		}
%>