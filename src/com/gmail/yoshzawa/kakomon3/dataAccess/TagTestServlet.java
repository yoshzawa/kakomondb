package com.gmail.yoshzawa.kakomon3.dataAccess;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kakomon3.MyHttpServlet;

@SuppressWarnings("serial")
public class TagTestServlet extends MyHttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		
		PrintWriter out = resp.getWriter();
		
		Tag tag = Tag.GetObjectById("BNF");
		
		out.println(tag.getName());
		out.println(tag.getMondais());
		out.println("<br>");
		
		List<Tag> tagList = Tag.getList();
		for(Tag tag2 : tagList){
			out.println(tag2.getName());
			out.println(tag2.getMondais());
			out.println("<br>");
		}

	}


}
