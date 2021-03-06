package kakomon3.admin;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kakomon3.MyHttpServlet;
import kakomon3.jdo.Genre;
import kakomon3.jdo.Mondai;
import kakomon3.jdo.MondaiImage;
import kakomon3.jdo.PMF;
import kakomon3.jdo.Sentaku;

//@WebServlet(name="mondai",urlPatterns={"/admin/mondai"})

@SuppressWarnings("serial")
public class Kakomon3ListAddServlet extends MyHttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		PersistenceManager pm = PMF.get().getPersistenceManager();

		boolean isOK = true;

		String mondaiId = req.getParameter("mondaiId");
		String comment = req.getParameter("comment");
		String genreId = req.getParameter("genreId");
		String kotaeStr = req.getParameter("kotae");
		String mondaiImage = req.getParameter("mondaiImage");
		int kotaeInt = 0;

		if ((checkParam(mondaiId) == false) || (checkParam(comment) == false)
				|| (checkParam(genreId) == false)
				|| (checkParam(kotaeStr) == false)) {
			isOK = false;
		} else {

			try {
				kotaeInt = Integer.parseInt(kotaeStr);
			} catch (NumberFormatException e) {
				kotaeInt = 0;
				isOK = false;
			}
		}

		if (isOK == false) {
			req.setAttribute("message", "登録エラーが発生しました");
			RequestDispatcher rd = req
					.getRequestDispatcher("/WEB-INF/jsp/admin/mondaiAdd.jsp");
			rd.forward(req, resp);
		}
		Mondai m = Mondai.getById(pm, mondaiId);
		if (m == null) {
			Genre genre = Genre.getById(pm, genreId);
			Sentaku kotae = Sentaku.get(kotaeInt);
			m = new Mondai(mondaiId, comment, genre, kotae);
			genre.addMondais(mondaiId);
			m.makePersistent(pm);
			MondaiImage mi = new MondaiImage(mondaiId, mondaiImage);
			mi.makePersistent(pm);
			req.setAttribute("message", "登録しました");
			pm.close();
		} else {
			req.setAttribute("message", "重複したキーが検出されました。登録を中止します。");
			pm.close();
		}
		String jsp_url = "/WEB-INF/jsp/admin/mondaiAdd.jsp";
		forwardJsp(req, resp, jsp_url);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		doGet(req, resp);
	}
}
