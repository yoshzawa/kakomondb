package kakomon3;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kakomon3.jdo.Genre;
import kakomon3.jdo.Mondai;
import kakomon3.jdo.PMF;

@SuppressWarnings("serial")
public class Kakomon3Servlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
		resp.getWriter().println("<h1>Hello, Servlet and JSP world</h1>");

		PrintWriter out = resp.getWriter();

		PersistenceManager pm = PMF.get().getPersistenceManager();

		Query queryGenre = pm.newQuery(Genre.class);
		@SuppressWarnings("unchecked")
		List<Genre> list2 = (List<Genre>) queryGenre.execute();

		Map<String, String> mapGenre = new HashMap<String, String>();

		for (Genre g : list2) {
			mapGenre.put(g.getId(), g.getName());
		}

		Query queryMondai = pm.newQuery(Mondai.class);
		@SuppressWarnings("unchecked")
		List<Mondai> list = (List<Mondai>) queryMondai.execute();

		for (Mondai m : list) {
			String s = "https://storage.googleapis.com/kakomondb/" + m.getURL();

			out.print("<hr>");
			out.print("<h3>" + m.getComment() + "</h3>");
			out.print("genre:" + mapGenre.get(m.getGenre()));
			out.println("<img src='" + s + "' width=1000>");
		}

		pm.close();
	}
}
