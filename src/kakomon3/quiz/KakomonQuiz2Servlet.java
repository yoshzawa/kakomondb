package kakomon3.quiz;

import java.io.IOException;

import javax.jdo.JDOFatalInternalException;
import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kakomon3.jdo.Kaitou;
import kakomon3.jdo.Mondai;
import kakomon3.jdo.MondaiImage;
import kakomon3.jdo.PMF;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

//@WebServlet(name="Quiz",urlPatterns={"/quiz/toi"})

@SuppressWarnings("serial")
public class KakomonQuiz2Servlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		PersistenceManager pm = PMF.get().getPersistenceManager();

		String id = req.getParameter("key");
		if (id == null) {
			resp.sendRedirect("/");
		}
		try {

			UserService service = UserServiceFactory.getUserService();
			Kaitou k = Kaitou.getByUser(pm, service.getCurrentUser(), id);
			
			id = k.getMondaiId();
			Mondai mondai = Mondai.getById(pm, id);
			MondaiImage mondaiImage = MondaiImage.getById(pm, id);
			
			String kaitouId = k.getKeyString();

			String[] s = new String[4];
			s[0] = mondai.getId();
			s[1] = mondaiImage.getURL();
			s[2] = mondai.getComment();
			s[3] = kaitouId;

			req.setAttribute("mondaiList", s);
			pm.close();

			RequestDispatcher rd = req
					.getRequestDispatcher("/WEB-INF/jsp/quiz/toi2.jsp");
			rd.forward(req, resp);
		} catch (JDOObjectNotFoundException e) {
			System.out.println(e.toString()); 
			resp.sendRedirect("/");
		} catch (JDOFatalInternalException e) {
			System.out.println(e.toString()); 
			resp.sendRedirect("/");
		}
	}
}
