package kakomon3.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kakomon3.jdo.Genre;
import kakomon3.jdo.Mondai;
import kakomon3.jdo.PMF;
import kakomon3.jdo.Tag;

@SuppressWarnings("serial")
public class Kakomon3TagServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		PersistenceManager pm = PMF.get().getPersistenceManager();
		Map<String, String> mapGenre = Genre.getMap(pm);
		List<Tag> allTag = Tag.getList(pm);
		Map<String, Mondai> mapMondai = Mondai.getMap(pm);
		List<String[]> tagList = new ArrayList<String[]>();

		for (Tag t : allTag) {
			String tagName = t.getName();
			for (String m : t.getMondais()) {
				Mondai mondai;
				mondai = mapMondai.get(m);
				List<String> tags = mondai.getTags();

				String[] s = new String[4 + tags.size()];

				s[0] = tagName;
				s[1] = mondai.getId();
				s[2] = mapGenre.get(mondai.getGenre());
				s[3] = mondai.getComment();
				s[4] = "";

				// List<String> tList = mondai.getTags();
				for (int i = 0; i < tags.size(); i++) {
					s[4 + i] = tags.get(i);
				}

				tagList.add(s);
				System.out.println(s[0] + ";" + s[2] + ";" + s[3]);
			}
		}

		req.setAttribute("tagList", tagList);
		pm.close();

		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/tag.jsp");
		rd.forward(req, resp);
	}
}
