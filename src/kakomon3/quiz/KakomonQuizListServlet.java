package kakomon3.quiz;

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

@SuppressWarnings("serial")
public class KakomonQuizListServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		PersistenceManager pm = PMF.get().getPersistenceManager();

		Map<String, String> mapGenre = Genre.getMap(pm);

		List<Mondai> list = Mondai.getList(pm);
		
		Map<String, MondaiImage> mapMondaiImage = MondaiImage.getMap(pm);

		List<String[]> mondaiList = new ArrayList<String[]>();
		for (Mondai m : list) {
			List<String> tagList = m.getTags();
			int tagLength = tagList.size();
			
			String[] s = new String[5+tagLength];
			s[0] = m.getId();
			String id = m.getId();
			s[1] = mapMondaiImage.get(id).getURL();
			s[2] = m.getComment();
			s[3] = mapGenre.get(m.getGenre());
			s[4] = m.getKotae().toString();
			for(int i=0 ; i<tagLength ; i++){
				s[5+i]=tagList.get(i);
			}
			mondaiList.add(s);
		}

		req.setAttribute("mondaiList", mondaiList);
		pm.close();
	
		req.setAttribute("jsp_url", "/WEB-INF/jsp/quiz/list.jsp");
		
		RequestDispatcher rd = req
				.getRequestDispatcher("/WEB-INF/jsp/jsp_base.jsp");
		
		rd.forward(req, resp);
	}
}
