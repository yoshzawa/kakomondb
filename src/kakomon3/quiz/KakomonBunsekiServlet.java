package kakomon3.quiz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
import kakomon3.jdo.Kaitou;
import kakomon3.jdo.Mondai;
import kakomon3.jdo.PMF;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class KakomonBunsekiServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {

			UserService service = UserServiceFactory.getUserService();
			User user = service.getCurrentUser();
			List<Kaitou> list = Kaitou.getListByUser(pm, user);
			Map<String, Mondai> mondais = Mondai.getMap(pm);

			List<Genre> genreList = Genre.getList(pm);
			Map<String , Integer> genreWinCount = new HashMap<String, Integer>();
			Map<String , Integer> genreLoseCount = new HashMap<String, Integer>();
			Map<String , Integer> genreAllCount = new HashMap<String, Integer>();
			for(Genre s : genreList){
				String gId = s.getId();
				genreWinCount.put(gId, 0);
				genreLoseCount.put(gId, 0);
				genreAllCount.put(gId, s.getMondais().size());
			}

			int seikai = 0;
			int machigai = 0;

			for (Kaitou k : list) {
				String mId = k.getMondaiId();
				String gId = mondais.get(mId).getGenre();
				if(k.isSeikai() == true){
					seikai ++;
					int i=genreWinCount.get(gId);
					i++;
					genreWinCount.put(gId, i);
				} else {
					machigai++;
					int i=genreLoseCount.get(gId);
					i++;
					genreLoseCount.put(gId, i);
				}
			}
			List<String[]> genreResult = new ArrayList<>();
			
			for(Genre s : genreList){
				String gId = s.getId();
				String ss[]=new String[4];
				ss[0] = s.getName();
				ss[1] = 
				genreWinCount.get(gId)+"";
				ss[2] = 
				genreLoseCount.get(gId)+"";
				ss[3] = (
				genreAllCount.get(gId) - 
				genreWinCount.get(gId) -
				genreLoseCount.get(gId)) + "";
				
				genreResult.add(ss);
			}
			req.setAttribute("genreResult", genreResult);

			String[] s = new String[7];

			s[0] = user.getNickname();
			s[1] = seikai + "";
			s[2] = machigai + "";

			req.setAttribute("result", s);
			pm.close();

			req.setAttribute("jsp_url", "/WEB-INF/jsp/quiz/bunseki.jsp");

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
