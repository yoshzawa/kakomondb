package kakomon3;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kakomon3.jdo.Genre;
import kakomon3.jdo.Mondai;
import kakomon3.jdo.PMF;
import kakomon3.jdo.Tag;

@SuppressWarnings("serial")
public class Kakomon3InitServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<h1>kakomondb Initializer</h1>");


		PersistenceManager pm = PMF.get().getPersistenceManager();

		Genre.init(pm);
		Mondai.init(pm);
		Tag.init(pm);

		out.println("<h2>Initialize Complete!</h2>");

		pm.close();
	}
}
