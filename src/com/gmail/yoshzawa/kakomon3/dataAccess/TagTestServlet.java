package com.gmail.yoshzawa.kakomon3.dataAccess;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kakomon3.MyHttpServlet;

@SuppressWarnings("serial")
public class TagTestServlet extends MyHttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		PrintWriter out = resp.getWriter();
		
		out.println("Hello World!");
		Tag tag = Tag.GetObjectById("BNF");
		
		out.println(tag.getName());
		out.println(tag.getMondais());

	}


}
