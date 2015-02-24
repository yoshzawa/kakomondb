package kakomon3.admin;

import java.io.IOException;
import java.util.List;
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
public class KakomonGenreReloadServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		List<Mondai> mondaiList = Mondai.getList(pm);
		Map<String, Mondai> mondaiMap = Mondai.getMap(pm, mondaiList);
		List<Genre> genreList = Genre.getList(pm);
		Map<String, Genre> genreMap = Genre.getMap(pm, genreList);
		
		for(Mondai m:mondaiList){
			String genreId = m.getGenre();
			Genre genre = genreMap.get(genreId);
			Set<String> genreMondaiList = genre.getMondais();
			if(genreMondaiList.contains(m.getId())){
				genreMondaiList.add(m.getId());
				genre.setMondais(genreMondaiList);
				genre.makePersistent(pm);
			}
		}
		for(Genre g:genreList){
			Set<String> list = g.getMondais();
			for(String id:list){
				Mondai m = mondaiMap.get(id);
				if(m.getGenre().equals(g.getId()) == false){
					list.remove(id);
					g.setMondais(list);
					g.makePersistent(pm);
				}
			}

		}

			req.setAttribute("message", "GenreごとのMondaiを再構築しました");
			req.setAttribute("jsp_url", "/WEB-INF/jsp/admin/genreAdd.jsp");

			RequestDispatcher rd = req
					.getRequestDispatcher("/WEB-INF/jsp/jsp_base.jsp");
			rd.forward(req, resp);
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		doGet(req, resp);
	}
}
