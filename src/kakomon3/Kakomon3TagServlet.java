package kakomon3;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.corba.se.impl.monitoring.MonitoredObjectImpl;

import kakomon3.jdo.Genre;
import kakomon3.jdo.Mondai;
import kakomon3.jdo.PMF;
import kakomon3.jdo.Tag;

@SuppressWarnings("serial")
public class Kakomon3TagServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		PersistenceManager pm = PMF.get().getPersistenceManager();

		// List<Genre> list2 = Genre.findAll(pm);
		Map<String, String> mapGenre = Genre.getMapAll(pm);

		Map<String,Tag> mapTag = Tag.getMapAll(pm);
		List<Tag> allTag = Tag.findAll(pm);

		List<Mondai> list = Mondai.findAll(pm);
		Map<String, Mondai> mapMondai = Mondai.getMapAll(pm);
		
		List<String[]> tagList = new ArrayList<String[]>();

		for (Tag t : allTag) {
			String tagName = t.getName();
			for (String m : t.getMondais()) {
				Mondai mondai;
				mondai = mapMondai.get(m);
				List<String> tags = mondai.getTags();

				String[] s = new String[4 + tags.size()];
				s[0] = tagName;
				s[1] = mondai.getURL();
				s[2] = mapGenre.get(mondai.getGenre());
				s[3] = mondai.getComment();
				s[4] = "";
				List<String> tList = mondai.getTags();
				for (int i=0 ; i<tags.size() ;i++) {
					s[4+i] = tags.get(i);
				}

				tagList.add(s);
				System.out.println(s[0]+";"+s[2]+";"+s[3]);
			}
		}

		req.setAttribute("tagList", tagList);
		pm.close();

		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/tag.jsp");
		rd.forward(req, resp);
	}
}
