package kakomon3;

import java.io.IOException;
import javax.servlet.http.*;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class Kakomon3Servlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
		resp.getWriter().println("<h1>Hello, Servlet and JSP world</h1>");

		String s = "https://storage.cloud.google.com/kakomondb/ap/APH241003.png";

		resp.getWriter().print("<hr>");
		resp.getWriter().print("<img src='" + s + "' width=1000>");

		
		
	}
}
