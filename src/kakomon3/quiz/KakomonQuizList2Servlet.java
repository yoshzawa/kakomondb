package kakomon3.quiz;

import java.io.IOException;
import java.util.ArrayList;
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
import kakomon3.jdo.Member;
import kakomon3.jdo.MemberGenre;
import kakomon3.jdo.PMF;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

//@WebServlet(name="QuizList",urlPatterns={"/quiz/list"})

@SuppressWarnings("serial")
public class KakomonQuizList2Servlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		PersistenceManager pm = PMF.get().getPersistenceManager();

		UserService service = UserServiceFactory.getUserService();
		Member member = Member.getById(pm, service.getCurrentUser());
		Map<String, MemberGenre> kaiinGenreMap = member.getKaiinGenreMap();
		List<String> list = member.getGenreList();
		Map<String, Genre> mapGenre = Genre.getMap(pm);
		List<String[]> genreList = new ArrayList<String[]>();
		for (String genreId : list) {

			String[] s = new String[5];
			s[0] = genreId;
			s[1] = mapGenre.get(genreId).getName();
			MemberGenre kaiinGenre = kaiinGenreMap.get(s[0]);
			Set<String> mondaiIdMap = kaiinGenre.getWinMondaiIdSet();
			int win = mondaiIdMap.size();
			int lose = kaiinGenre.getLoseMondaiIdSet().size();
			s[2] = win + "";
			s[3] = lose + "";
			Genre g = mapGenre.get(genreId);
			Set<String> mondaiList = g.getMondaiList();
			s[4] = (mondaiList.size() - win - lose) + "";

			genreList.add(s);
		}

		req.setAttribute("genreList", genreList);
		pm.close();

		req.setAttribute("jsp_url", "/WEB-INF/jsp/quiz/list2.jsp");

		RequestDispatcher rd = req
				.getRequestDispatcher("/WEB-INF/jsp/jsp_base.jsp");

		rd.forward(req, resp);
	}
}
