package kakomon3;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kakomon3.jdo.PMF;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class MyHttpServlet extends HttpServlet {

	protected PersistenceManager getPersistenceManager() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		return pm;
	}
	protected boolean checkParam(String s) {
		boolean b = ((s != null) && (s.length() > 0));
		return b;
	}

	protected void forwardJsp(HttpServletRequest req, HttpServletResponse resp,
			String jsp_url) throws ServletException, IOException {
		req.setAttribute("jsp_url", jsp_url);

		RequestDispatcher rd = req
				.getRequestDispatcher("/WEB-INF/jsp/jsp_base.jsp");
		rd.forward(req, resp);
	}
	protected User getUser() {
		UserService service = UserServiceFactory.getUserService();
		User user = service.getCurrentUser();
		return user;
	}
	protected String getUserId() {
		return getUserId(getUser());
	}
	protected String getUserId(User user) {
		return user.getEmail();
	}

}
