package kakomon3.admin;

import java.io.IOException;
import java.util.ArrayList;
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
import kakomon3.jdo.MondaiImage;
import kakomon3.jdo.PMF;
import kakomon3.jdo.Sentaku;

@SuppressWarnings("serial")
public class KakomonGenreServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		PersistenceManager pm = PMF.get().getPersistenceManager();

//		Map<String, String> mapGenre = Genre.getMap(pm);
		List<Genre> listGenre = Genre.getList(pm);

		List<String[]> genreList = new ArrayList<String[]>();
		for (Genre g : listGenre) {

			String[] s = new String[3];
			s[0] = g.getId();
			s[1]=g.getName();
			s[2]=g.getMondais().size()+"";
			genreList.add(s);
		}

		req.setAttribute("genreList", genreList);
		pm.close();

		RequestDispatcher rd = req
				.getRequestDispatcher("/WEB-INF/jsp/admin/genre.jsp");
		rd.forward(req, resp);
	}
}
