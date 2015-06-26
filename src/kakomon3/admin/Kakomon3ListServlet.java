package kakomon3.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kakomon3.MyHttpServlet;
import kakomon3.jdo.Genre;
import kakomon3.jdo.Mondai;
import kakomon3.jdo.MondaiImage;
import kakomon3.jdo.PMF;
import kakomon3.jdo.Sentaku;

@SuppressWarnings("serial")
public class Kakomon3ListServlet extends MyHttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		PersistenceManager pm = PMF.get().getPersistenceManager();

		Map<String, Genre> mapGenre = Genre.getMap(pm);

		List<Mondai> list = Mondai.getList(pm);

		Map<String, MondaiImage> mapMondaiImage = MondaiImage.getMap(pm);
		
		ArrayList<String[]> kotaeList = Sentaku.getStringList();
		
		
		ArrayList<String[]> genreList = new ArrayList<String[]>();
		for(String s:mapGenre.keySet()){
			String[] data=new String[2];
			data[0] =s;
			data[1] =mapGenre.get(s).getName();
			genreList.add(data);
		}

		List<String[]> mondaiList = new ArrayList<String[]>();
		for (Mondai m : list) {
			List<String> tagList = m.getTags();
			int tagLength = tagList.size();

			String[] s = new String[5 + tagLength];
			s[0] = m.getId();
			String id = m.getId();
			s[1] = mapMondaiImage.get(id).getURL();
			s[2] = m.getComment();
			s[3] = mapGenre.get(m.getGenre()).getName();
			s[4] = m.getKotae().toString();
			for (int i = 0; i < tagLength; i++) {
				s[5 + i] = tagList.get(i);
			}
			mondaiList.add(s);
		}

		req.setAttribute("mondaiList", mondaiList);
		req.setAttribute("kotaeList", kotaeList);
		req.setAttribute("genreList", genreList);
		pm.close();
		
		String jsp_url = "/WEB-INF/jsp/admin/mondai.jsp";
		forwardJsp(req, resp, jsp_url);
	}


}
