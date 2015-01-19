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
import kakomon3.jdo.Sentaku;

@SuppressWarnings("serial")
public class KakomonQuizKotaeServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		PersistenceManager pm = PMF.get().getPersistenceManager();

		String id = req.getParameter("id");
		String answer = req.getParameter("answer");
		if (id == null) {
			resp.sendRedirect("/");
		}
		try {
			Kaitou kaitou = Kaitou.getById(pm, id);

			String mondaiId = kaitou.getMondaiId();

			Mondai mondai = Mondai.getById(pm, mondaiId);
			MondaiImage mondaiImage = MondaiImage.getById(pm, mondaiId);

			int i = Integer.parseInt(answer);
			Sentaku ansSentaku = Sentaku.get(i);
			Sentaku seikaiSentaku = mondai.getKotae();

			kaitou.setSentaku(ansSentaku);
			boolean b = ansSentaku.equals(seikaiSentaku);
			kaitou.setSeikai(b);

			kaitou.makePersistent(pm);

			String[] s = new String[7];

			s[0] = mondai.getId();
			s[1] = mondaiImage.getURL();
			s[2] = mondai.getComment();
			s[3] = mondai.getKotae().toString();
			s[4] = kaitou.getSentaku().toString();
			s[5] = kaitou.isSeikai() ? "◯" : "×";
			s[6] = id;

			req.setAttribute("mondaiList", s);
			pm.close();

			req.setAttribute("jsp_url", "/WEB-INF/jsp/quiz/answer.jsp");

			RequestDispatcher rd = req
					.getRequestDispatcher("/WEB-INF/jsp/jsp_base.jsp");

			rd.forward(req, resp);
		} catch (JDOObjectNotFoundException e) {
			resp.sendRedirect("/");
		} catch (JDOFatalInternalException e) {
			resp.sendRedirect("/");
		} catch (IllegalArgumentException e) {
			resp.sendRedirect("/");
		}

	}
}
