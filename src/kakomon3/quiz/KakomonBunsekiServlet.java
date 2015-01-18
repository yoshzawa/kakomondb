package kakomon3.quiz;

import java.io.IOException;
import java.util.List;

import javax.jdo.JDOFatalInternalException;
import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import kakomon3.jdo.Kaitou;
import kakomon3.jdo.Mondai;
import kakomon3.jdo.MondaiImage;
import kakomon3.jdo.PMF;
import kakomon3.jdo.Sentaku;

@SuppressWarnings("serial")
public class KakomonBunsekiServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		PersistenceManager pm = PMF.get().getPersistenceManager();

		String id = req.getParameter("id");
		try {

			UserService service = UserServiceFactory.getUserService();
			User user = service.getCurrentUser();
			List<Kaitou> list = Kaitou.getListByUser(pm, user);
			
			int seikai=0;
			int machigai=0;
			
			for(Kaitou k:list){
				seikai += (k.isSeikai()?1:0);
				machigai += (k.isSeikai()?0:1);
			}
			
			String[] s = new String[7];
			
			s[0] = user.getNickname();
			s[1] = seikai+"";
			s[2] = machigai+"";

			req.setAttribute("result", s);
			pm.close();

			RequestDispatcher rd = req
					.getRequestDispatcher("/WEB-INF/jsp/quiz/bunseki.jsp");
			rd.forward(req, resp);
		} catch(IOException e){
			System.out.println(e.toString()); 
			resp.sendRedirect("/");
		} catch (ServletException e){
			System.out.println(e.toString()); 
			resp.sendRedirect("/");
		}

		}
	}

