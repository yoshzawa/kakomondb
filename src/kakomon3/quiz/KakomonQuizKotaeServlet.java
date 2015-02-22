package kakomon3.quiz;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kakomon3.jdo.Kaitou;
import kakomon3.jdo.Member;
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
//		try {
			Kaitou kaitou = Kaitou.getById(pm, id);
			Member member = Member.getById(pm, kaitou.getUser());

			String mondaiId = kaitou.getMondaiId();

			Mondai mondai = Mondai.getById(pm, mondaiId);
			MondaiImage mondaiImage = MondaiImage.getById(pm, mondaiId);

			// 解答と正解の取得

			int kaitouNo = Integer.parseInt(answer);
			Sentaku ansSentaku = Sentaku.get(kaitouNo);
			Sentaku seikaiSentaku = mondai.getKotae();

			// 正解・不正解の情報を格納
			kaitou.setSentaku(ansSentaku);
			boolean b = ansSentaku.equals(seikaiSentaku);
			kaitou.setSeikai(b);

			kaitou.makePersistent(pm);

			// Kaiinの更新
			if (b == true) {
				member.addWinMondaiIdSet(pm,mondai.getGenre(), mondaiId);
			} else {
				member.addLoseMondaiIdSet(pm,mondai.getGenre(), mondaiId);
			}

			// 結果表示の準備

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
/*		} catch (JDOObjectNotFoundException e) {
			resp.sendRedirect("/");
		} catch (JDOFatalInternalException e) {
			resp.sendRedirect("/");
		} catch (IllegalArgumentException e) {
			resp.sendRedirect("/");
		}
*/
	}
}
