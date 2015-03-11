package kakomon3.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kakomon3.jdo.Genre;
import kakomon3.jdo.Mondai;
import kakomon3.jdo.PMF;
import kakomon3.jdo.Sentaku;
import kakomon3.jdo.Tag;

@SuppressWarnings("serial")
public class KakomonMondaiModifyCommentServlet extends HttpServlet {

	private boolean checkParam(String s) {
		boolean b = ((s != null) && (s.length() > 0));
		return b;
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		PersistenceManager pm = PMF.get().getPersistenceManager();

		String mondaiId = req.getParameter("id");
		String comment = req.getParameter("Comment");

		if ((checkParam(mondaiId) == false) || (checkParam(comment) == false)) {
			req.setAttribute("message", "指定された問題が見つかりませんでした");
			pm.close();
		} else {
				Mondai m = Mondai.getById(pm, mondaiId);
				Map<String, Tag> tagMap = Tag.getMap(pm);
				
				{
					List<String> taglist = m.getTags();
					for(String tag:taglist){
						Tag t = tagMap.get(tag);
						List<String> mondais = t.getMondais();
						mondais.remove(mondaiId);
						t.setMondais(mondais);
						t.makePersistent(pm);
						
						taglist.remove(tag);
					}
				}
							
				StringTokenizer st = new StringTokenizer(comment," #");
				
				{
					m.setComment(st.nextToken());
					while(st.hasMoreTokens()){
						String token = st.nextToken();
						Tag t = tagMap.get(token);
						if(t == null){
							t = new Tag(token);
							t.makePersistent(pm);
						} 
							List<String> mondais = t.getMondais();
							mondais.add(mondaiId);
							t.setMondais(mondais);
							t.makePersistent(pm);
						
						List<String> tags = m.getTags();
						tags.add(token);
						m.setTags(tags);
				}

				m.makePersistent(pm);
				req.setAttribute("message", "データを変更しました");
			}

			pm.close();
		}
		req.setAttribute("jsp_url", "/WEB-INF/jsp/admin/mondaiAdd.jsp");

		RequestDispatcher rd = req
				.getRequestDispatcher("/WEB-INF/jsp/jsp_base.jsp");
		rd.forward(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		doGet(req, resp);
	}
}
