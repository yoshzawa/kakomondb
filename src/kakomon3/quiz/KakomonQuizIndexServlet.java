package kakomon3.quiz;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import kakomon3.jdo.Kaitou;
import kakomon3.jdo.Member;
import kakomon3.jdo.PMF;

@SuppressWarnings("serial")
public class KakomonQuizIndexServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		UserService service = UserServiceFactory.getUserService();
		User user = service.getCurrentUser();
		Member m = Member.getById(pm, user);
		
		String[] memberStatus = new String[4];
		memberStatus[0]=m.getMail();
		memberStatus[1]=m.getCreated()+"";
		memberStatus[2]=m.getCoin()+"";
		memberStatus[3]=m.getExp()+"";
		
		req.setAttribute("memberStatus", memberStatus);

		req.setAttribute("jsp_url", "/WEB-INF/jsp/quiz/index.jsp");
		RequestDispatcher rd = req
				.getRequestDispatcher("/WEB-INF/jsp/jsp_base.jsp");
		rd.forward(req, resp);

	}
}
