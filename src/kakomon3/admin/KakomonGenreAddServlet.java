package kakomon3.admin;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kakomon3.jdo.Genre;
import kakomon3.jdo.PMF;

//@WebServlet(name="genreAdd",urlPatterns={"/admin/genreAdd"})

@SuppressWarnings("serial")
public class KakomonGenreAddServlet extends HttpServlet {

	private boolean checkParam(String s) {
		boolean b = ((s != null) && (s.length() > 0));
		return b;
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		PersistenceManager pm = PMF.get().getPersistenceManager();

		String genreId = req.getParameter("genreId");
		String genreName = req.getParameter("genreName");

		if ((checkParam(genreName) == true) && (checkParam(genreId) == true)) {

			Genre genre = new Genre(genreId, genreName);
			genre.makePersistent(pm);

			req.setAttribute("message", "登録しました");
			pm.close();

			req.setAttribute("jsp_url", "/WEB-INF/jsp/admin/genreAdd.jsp");

			RequestDispatcher rd = req
					.getRequestDispatcher("/WEB-INF/jsp/jsp_base.jsp");
			rd.forward(req, resp);
		} else {
			req.setAttribute("message", "登録エラーが発生しました");
			req.setAttribute("jsp_url", "/WEB-INF/jsp/admin/genreAdd.jsp");

			RequestDispatcher rd = req
					.getRequestDispatcher("/WEB-INF/jsp/jsp_base.jsp");
			rd.forward(req, resp);
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		doGet(req, resp);
	}
}