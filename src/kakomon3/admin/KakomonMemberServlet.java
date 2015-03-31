package kakomon3.admin;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kakomon3.MyHttpServlet;
import kakomon3.jdo.Member;
import kakomon3.jdo.MemberGenre;
import kakomon3.jdo.PMF;

@SuppressWarnings("serial")
public class KakomonMemberServlet extends MyHttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		PersistenceManager pm = PMF.get().getPersistenceManager();
		String id = req.getParameter("id");

		Member member = Member.getById(pm, id);
		List<MemberGenre> memberGenreList = member.getMemberGenreList();

		int size = memberGenreList.size();
		String[] s = new String[4 + size];
		s[0] = member.getMail();
		s[1] = member.getCoin() + "";
		s[2] = member.getExp() + "";
		s[3] = member.getCreated() + "";
		for (int i = 0; i < size; i++) {
			MemberGenre mg = memberGenreList.get(i);
			s[4 + i] = mg.getGenreId();

		}

		req.setAttribute("memberList", s);
		pm.close();

		String jsp_url = "/WEB-INF/jsp/admin/member.jsp";

		forwardJsp(req, resp, jsp_url);
	}
}
