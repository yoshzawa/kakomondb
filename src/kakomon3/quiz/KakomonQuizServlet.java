package kakomon3.quiz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.jdo.JDOFatalInternalException;
import javax.jdo.JDOObjectNotFoundException;
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
public class KakomonQuizServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		PersistenceManager pm = PMF.get().getPersistenceManager();

		String id = req.getParameter("key");
		if (id == null) {
			resp.sendRedirect("/");
		}
		try {
			Mondai mondai = Mondai.getById(pm, id);
			MondaiImage mondaiImage = MondaiImage.getById(pm, id);

			String[] s = new String[3];
			s[0] = mondai.getId();
			s[1] = mondaiImage.getURL();
			s[2] = mondai.getComment();

			req.setAttribute("mondaiList", s);
			pm.close();

			RequestDispatcher rd = req
					.getRequestDispatcher("/WEB-INF/jsp/quiz/toi.jsp");
			rd.forward(req, resp);
		} catch (JDOObjectNotFoundException e) {
			resp.sendRedirect("/");
		} catch (JDOFatalInternalException e){
			resp.sendRedirect("/");
		}
	}
}
