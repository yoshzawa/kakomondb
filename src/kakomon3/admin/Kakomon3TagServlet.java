package kakomon3.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gmail.yoshzawa.kakomon3.dataAccess.Tag;

import kakomon3.MyHttpServlet;
import kakomon3.jdo.Genre;
import kakomon3.jdo.Mondai;
import kakomon3.jdo.MondaiImage;

@SuppressWarnings("serial")
public class Kakomon3TagServlet extends MyHttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		PersistenceManager pm = getPersistenceManager();
		Map<String, Genre> mapGenre = Genre.getMap(pm);
		List<Tag> allTag = Tag.getList();
		Map<String, Mondai> mapMondai = Mondai.getMap(pm);
		List<String[]> tagList = new ArrayList<String[]>();
		Map<String, MondaiImage> mapMondaiImage = MondaiImage.getMap(pm);

		for (Tag t : allTag) {
			String tagName = t.getName();
			for (String m : t.getMondais()) {
				Mondai mondai;
				mondai = mapMondai.get(m);
				List<String> tags = mondai.getTags();

				String[] s = new String[4 + tags.size()];

				s[0] = tagName;
				s[1] = mapMondaiImage.get(mondai.getId()).getURL();
				s[2] = mapGenre.get(mondai.getGenre()).getName();
				s[3] = mondai.getComment();

				for (int i = 0; i < tags.size(); i++) {
					s[4 + i] = tags.get(i);
				}

				tagList.add(s);
			}
		}

		req.setAttribute("tagList", tagList);
		pm.close();

		req.setAttribute("jsp_url", "/WEB-INF/jsp/admin/tag.jsp");

		RequestDispatcher rd = req
				.getRequestDispatcher("/WEB-INF/jsp/jsp_base.jsp");
		rd.forward(req, resp);
	}


}
