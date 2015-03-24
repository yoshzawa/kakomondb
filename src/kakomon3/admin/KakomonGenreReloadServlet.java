package kakomon3.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kakomon3.jdo.Member;
import kakomon3.jdo.PMF;

@SuppressWarnings("serial")
public class KakomonGenreReloadServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		PersistenceManager pm = PMF.get().getPersistenceManager();

		List<Member> members = Member.getList(pm);

		ArrayList<String[]> memberList = new ArrayList<>();

		for (Member m : members) {
			List<String> genreList;
			genreList = m.getGenreList();
			String[] s = new String[3 + genreList.size()];
			s[0] = m.getMail();
			s[1] = m.getCoin()+"";
			s[2] = m.getExp()+"";
			for(int i = 0 ; i<genreList.size() ; i++){
				s[3+i]=genreList.get(i);
			}
			memberList.add(s);

		}

		req.setAttribute("memberList", memberList);
		pm.close();

		req.setAttribute("jsp_url", "/WEB-INF/jsp/admin/member.jsp");

		RequestDispatcher rd = req
				.getRequestDispatcher("/WEB-INF/jsp/jsp_base.jsp");
		rd.forward(req, resp);
	}
}
