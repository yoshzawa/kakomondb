package kakomon3;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kakomon3.jdo.Genre;
import kakomon3.jdo.Mondai;
import kakomon3.jdo.PMF;

@SuppressWarnings("serial")
public class Kakomon3Servlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		resp.setContentType("text/html");
		// resp.getWriter().println("<h1>Hello, Servlet and JSP world</h1>");

		PrintWriter out = resp.getWriter();

		PersistenceManager pm = PMF.get().getPersistenceManager();

		List<Genre> list2 = Genre.findAll(pm);

		Map<String, String> mapGenre = new HashMap<String, String>();

		for (Genre g : list2) {
			mapGenre.put(g.getId(), g.getName());
		}

		List<Mondai> list = Mondai.findAll(pm);

		List<String[]> mondaiList = new ArrayList<String[]>();
		for (Mondai m : list) {
			String[] s = new String[3];
			s[0] = m.getURL();
			s[1] = m.getComment();
			s[2] = mapGenre.get(m.getGenre());
			mondaiList.add(s);
		}

		req.setAttribute("mondaiList", mondaiList);
		pm.close();

		RequestDispatcher rd = req
				.getRequestDispatcher("/WEB-INF/jsp/kakomon3.jsp");
		rd.forward(req, resp);
	}
}
