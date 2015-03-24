package kakomon3.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;

import kakomon3.jdo.Genre;
import kakomon3.jdo.Kaitou;
import kakomon3.jdo.Member;
import kakomon3.jdo.MemberGenre;
import kakomon3.jdo.Mondai;
import kakomon3.jdo.PMF;

@SuppressWarnings("serial")
public class KakomonMemberReloadServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		List<Mondai> mondaiList = Mondai.getList(pm);
		Map<String, Mondai> mondaiMap = Mondai.getMap(pm, mondaiList);
		List<Genre> genreList = Genre.getList(pm);
		Map<String, Genre> genreMap = Genre.getMap(pm, genreList);
		
		List<Kaitou> kaitouList = Kaitou.getList(pm);
		
		Map<String, Member> memberMap =  Member.getMap(pm);
		for(Kaitou k : kaitouList){
			User user = k.getUser();
			String eMail = user.getEmail();
			
			// Memberが登録されていなければ追加する
			if(memberMap.containsKey(eMail) == false){
				Member member = new Member(user);
				member.makePersistent(pm);
				memberMap.put(eMail, member);
			}
			Member m = memberMap.get(eMail);
			Map<String, MemberGenre> mgenreList = m.getMemberGenreMap();
			String mondaiId = k.getMondaiId();
			Mondai mondai = mondaiMap.get(mondaiId);
			String genreId = mondai.getGenre();
			
			MemberGenre mg;
			//MemberGenreが登録されていなければ追加
			if(mgenreList.containsKey(genreId) == false){
				mg = new MemberGenre(genreId,m);
				List<MemberGenre> memberGenreList = m.getMemberGenreList();
				memberGenreList.add(mg);
				m.setMemberGenreList(memberGenreList);
				m.makePersistent(pm);
			} 
			else
			{
				mg = mgenreList.get(genreId);
			}
			
			if(k.isSeikai() == true){
				if(mg.getWinMondaiIdSet().contains(mondaiId) == false){
					mg.addWinMondaiIdSet(mondaiId);
					mg.makePersistent(pm);
					
					m.addCoin(100);
					m.addExp(100);
					m.makePersistent(pm);
					memberMap.put(eMail, m);
				}
			} else {
				if(mg.getLoseMondaiIdSet().contains(mondaiId) == false){
					mg.addLoseMondaiIdSet(mondaiId);
					mg.makePersistent(pm);
					
					m.addCoin(1);
					m.addExp(1);
					m.makePersistent(pm);
					memberMap.put(eMail, m);
				}
			}
		}
		
		



		
			req.setAttribute("message", "KaitouごとのMemberを再構築しました");
			req.setAttribute("jsp_url", "/WEB-INF/jsp/admin/memberAdd.jsp");

			RequestDispatcher rd = req
					.getRequestDispatcher("/WEB-INF/jsp/jsp_base.jsp");
			rd.forward(req, resp);
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		doGet(req, resp);
	}
}
