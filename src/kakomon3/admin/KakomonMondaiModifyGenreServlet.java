package kakomon3.admin;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

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
public class KakomonMondaiModifyGenreServlet extends HttpServlet {

	private boolean checkParam(String s) {
		boolean b = ((s != null) && (s.length() > 0));
		return b;
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		PersistenceManager pm = PMF.get().getPersistenceManager();

		String mondaiId = req.getParameter("id");
		String genre = req.getParameter("Genre");

		if ((checkParam(mondaiId) == false) || (checkParam(genre) == false)) {
			req.setAttribute("message", "指定された問題が見つかりませんでした");
			pm.close();
		} else {
			Map<String, Genre> genreMap = Genre.getMap(pm);
			if (genreMap.containsKey(genre) == true) {
				Mondai m = Mondai.getById(pm, mondaiId);
				{
					Genre g = genreMap.get(m.getGenre());
					Set<String> mondais = g.getMondais();
					mondais.remove(m.getId());
					g.setMondais(mondais);
					g.makePersistent(pm);
				}

				m.setGenre(genre);
				{
					Genre g = genreMap.get(genre);
					Set<String> mondais = g.getMondais();
					mondais.add(mondaiId);
					g.setMondais(mondais);
					g.makePersistent(pm);
				}
				
				
				m.makePersistent(pm);
				
					req.setAttribute("message", "データを変更しました");
			}

			pm.close();
		}
		req.setAttribute("jsp_url", "/WEB-INF/jsp/admin/mondaiAdd.jsp");

		RequestDispatcher rd = req
				.getRequestDispatcher("/WEB-INF/jsp/jsp_base.jsp");
		rd.forward(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		doGet(req, resp);
	}
}
