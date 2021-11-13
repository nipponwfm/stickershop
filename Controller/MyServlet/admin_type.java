package MyServlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.KhachHang;
import bo.LoaiBO;

/**
 * Servlet implementation class admin_type
 */
@WebServlet("/admin_type")
public class admin_type extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public admin_type() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		RequestDispatcher rd = request.getRequestDispatcher("admin_type.jsp");
		HttpSession session = request.getSession();

		if (session.getAttribute("user") == null || !((KhachHang) session.getAttribute("user")).getIsAdmin()) {
			response.sendRedirect("/Java/home");
		} else {
			LoaiBO lbo = new LoaiBO();
			if (request.getParameter("tl") != null) {
				lbo.insertLoai(request.getParameter("ml"), request.getParameter("tl"));
			} else if (request.getParameter("value") != null && request.getParameter("value").length() != 0) {
				lbo.updateLoai(request.getParameter("ml"), request.getParameter("value"));
			} else if (request.getParameter("mlbx") != null) {
				lbo.deleteLoai(request.getParameter("mlbx"));
			}

			session.setAttribute("_type", lbo.admin_getType());

			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
