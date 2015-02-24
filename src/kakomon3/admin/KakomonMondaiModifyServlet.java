package kakomon3.admin;

import java.io.IOException;

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
import kakomon3.jdo.Sentaku;

@SuppressWarnings("serial")
public class KakomonMondaiModifyServlet extends HttpServlet {

	private boolean checkParam(String s) {
		boolean b = ((s != null) && (s.length() > 0));
		return b;
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		PersistenceManager pm = PMF.get().getPersistenceManager();

		boolean isOK = true;

		String mondaiId = req.getParameter("id");
		String param = req.getParameter("param");

		if ((checkParam(mondaiId) == false) || (checkParam(param) == false)) {
			req.setAttribute("message", "指定された問題が見つかりませんでした");
			pm.close();
		} else {

			Mondai m = Mondai.getById(pm, mondaiId);
			String[] mondai = new String[6];
			mondai[0]=m.getId();
			mondai[1]=m.getComment();
			mondai[2]=m.getGenre();
			mondai[4]=m.getKotae().getNo()+"";
			mondai[5]=param;
			
			req.setAttribute("message", "編集してください");
			req.setAttribute("mondai", mondai);
			pm.close();
		}
		req.setAttribute("jsp_url", "/WEB-INF/jsp/admin/mondaiModify.jsp");

		RequestDispatcher rd = req
				.getRequestDispatcher("/WEB-INF/jsp/jsp_base.jsp");
		rd.forward(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		doGet(req, resp);
	}
}
