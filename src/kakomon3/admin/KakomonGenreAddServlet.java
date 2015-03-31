package kakomon3.admin;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kakomon3.MyHttpServlet;
import kakomon3.jdo.Genre;
import kakomon3.jdo.PMF;

@SuppressWarnings("serial")
public class KakomonGenreAddServlet extends MyHttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		String jsp_url;
		PersistenceManager pm = PMF.get().getPersistenceManager();

		String genreId = req.getParameter("genreId");
		String genreName = req.getParameter("genreName");

		if ((checkParam(genreName) == true) && (checkParam(genreId) == true)) {

			Genre genre = new Genre(genreId, genreName);
			genre.makePersistent(pm);

			req.setAttribute("message", "登録しました");
			pm.close();
		} else {
			req.setAttribute("message", "登録エラーが発生しました");
		}
		jsp_url = "/WEB-INF/jsp/admin/genreAdd.jsp";

		forwardJsp(req, resp, jsp_url);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		doGet(req, resp);
	}
}
