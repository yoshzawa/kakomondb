package kakomon3.quiz;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kakomon3.MyHttpServlet;
import kakomon3.jdo.Member;

import com.google.appengine.api.users.User;

@SuppressWarnings("serial")
public class KakomonQuizIndexServlet extends MyHttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		PersistenceManager pm = getPersistenceManager();
		User user = getUser();
		Member m = Member.getById(pm, getUserId(user));
		if(m == null){
			m=new Member(user);
			m.makePersistent(pm);
		}
		
		String[] memberStatus = new String[4];
		memberStatus[0]=m.getMail();
		memberStatus[1]=m.getCreated()+"";
		memberStatus[2]=m.getCoin()+"";
		memberStatus[3]=m.getExp()+"";
		
		req.setAttribute("memberStatus", memberStatus);


		String jsp_url = "/WEB-INF/jsp/quiz/index.jsp";

		forwardJsp(req, resp, jsp_url);

	}


}
