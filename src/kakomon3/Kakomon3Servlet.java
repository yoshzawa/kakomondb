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
import kakomon3.jdo.Tag;

@SuppressWarnings("serial")
public class Kakomon3Servlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		PersistenceManager pm = PMF.get().getPersistenceManager();

		// List<Genre> list2 = Genre.findAll(pm);
		Map<String, String> mapGenre = Genre.getMapAll(pm);

		Map<Long, String> mapTag = Tag.getMapAll(pm);

		List<Mondai> list = Mondai.findAll(pm);
		


		List<String[]> mondaiList = new ArrayList<String[]>();
		for (Mondai m : list) {
			List<Long> tagList = m.getTags();
			int tagLength = tagList.size();
			
			String[] s = new String[3+tagLength];
			s[0] = m.getURL();
			s[1] = m.getComment();
			s[2] = mapGenre.get(m.getGenre());
			for(int i=0 ; i<tagLength ; i++){
				s[3+i]=mapTag.get(tagList.get(i));
			}
			mondaiList.add(s);
		}

		req.setAttribute("mondaiList", mondaiList);
		pm.close();

		RequestDispatcher rd = req
				.getRequestDispatcher("/WEB-INF/jsp/kakomon3.jsp");
		rd.forward(req, resp);
	}
}
