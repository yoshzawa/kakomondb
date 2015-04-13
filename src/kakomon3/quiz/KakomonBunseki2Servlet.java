package kakomon3.quiz;

import java.io.IOException;
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
import kakomon3.jdo.Kaitou;
import kakomon3.jdo.Mondai;
import kakomon3.jdo.PMF;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class KakomonBunseki2Servlet extends HttpServlet {

	@SuppressWarnings("unused")
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {

			UserService service = UserServiceFactory.getUserService();
			User user = service.getCurrentUser();

			List<Kaitou> list = Kaitou.getListByUser(pm, user);

			List<Mondai> mondaiList = Mondai.getList(pm);
			Map<String, Mondai> mondaiMap = Mondai.getMap(pm, mondaiList);
			List<Genre> genreList = Genre.getList(pm);

			Map<String, Integer> genreWinCount = new HashMap<String, Integer>();
			Map<String, Integer> genreLoseCount = new HashMap<String, Integer>();
			Map<String, Integer> genreAllCount = new HashMap<String, Integer>();

			for (Mondai m : mondaiList) {
				String mId = m.getId();
				genreWinCount.put(mId, 0);
				genreLoseCount.put(mId, 0);
				genreAllCount.put(mId, 0);
			}

			int seikai = 0;
			int machigai = 0;

			for (Kaitou k : list) {
				String mId = k.getMondaiId();
				if (k.isSeikai() == true) {
					seikai++;
					int i = 1;
					try {
						i = genreWinCount.get(mId);
						i++;
					} finally {
						genreWinCount.put(mId, i);
					}
				} else {
					machigai++;
					if(genreLoseCount.containsKey(mId)){
						int i = 1;
						i = genreLoseCount.get(mId);
						i++;
						genreLoseCount.put(mId, i);
					} else {
						genreLoseCount.put(mId, 1);
					}
				}
			}
			List<String[]> mondaiResult = new ArrayList<>();

			for (Mondai m : mondaiList) {
				String mId = m.getId();
				int tagSize = m.getTags().size();
				String ss[] = new String[5 + tagSize];
				ss[0] = m.getId();
				ss[1] = m.getComment();
				ss[2] = genreWinCount.get(mId) + "";

				ss[3] = genreLoseCount.get(mId) + "";
				ss[4] = (genreAllCount.get(mId) + genreWinCount.get(mId)) + "";
				for (int i = 0; i < tagSize; i++) {
					ss[5 + i] = m.getTags().get(i);
				}

				mondaiResult.add(ss);
			}
			req.setAttribute("mondaiResult", mondaiResult);

			String[] s = new String[7];

			s[0] = user.getNickname();
			s[1] = seikai + "";
			s[2] = machigai + "";

			req.setAttribute("result", s);
			pm.close();

			req.setAttribute("jsp_url", "/WEB-INF/jsp/quiz/bunseki2.jsp");

			RequestDispatcher rd = req
					.getRequestDispatcher("/WEB-INF/jsp/jsp_base.jsp");

			rd.forward(req, resp);
		} catch (IOException e) {
			System.out.println(e.toString());
			resp.sendRedirect("/");
		} catch (ServletException e) {
			System.out.println(e.toString());
			resp.sendRedirect("/");
		}

	}
}
