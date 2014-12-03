package kakomon3;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class Kakomon3Servlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
		resp.getWriter().println("<h1>Hello, Servlet and JSP world</h1>");

		PrintWriter out = resp.getWriter();

		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		Mondai.init(pm);
		Genre.init(pm);
		
		Query query = pm.newQuery(Mondai.class);
		@SuppressWarnings("unchecked")
		List<Mondai> list = (List<Mondai>)query.execute();
		
		for(Mondai m:list){
			String s = "https://storage.googleapis.com/kakomondb/" + m.getURL();
		
			out.print("<hr>");
			out.print("<h3>"+m.getComment()+"</h3>");
			out.print("<img src='" + s + "' width=1000>");
		}
		
		pm.close();
	}
}
