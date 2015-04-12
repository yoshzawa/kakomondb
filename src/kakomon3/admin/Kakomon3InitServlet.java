package kakomon3.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kakomon3.jdo.Genre;
import kakomon3.jdo.Mondai;
import kakomon3.jdo.MondaiImage;
import kakomon3.jdo.PMF;

//@WebServlet(name="Kakomon3Init",urlPatterns={"/admin/init"})

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
		MondaiImage.init(pm);
//		Tag.init(pm);

		out.println("<h2>Initialize Complete!</h2>");
		out.println("<a href='/'>home</a>");

		pm.close();
	}
}
