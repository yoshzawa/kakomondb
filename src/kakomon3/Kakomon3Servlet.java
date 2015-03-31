package kakomon3;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class Kakomon3Servlet extends MyHttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		String jsp_url = "/WEB-INF/jsp/index.jsp";
		forwardJsp(req, resp, jsp_url);
	}
}
