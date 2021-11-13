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
import bo.HoaDonBO;

/**
 * Servlet implementation class home_admin
 */
@WebServlet("/admin_home")
public class admin_home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public admin_home() {
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

		RequestDispatcher rd = request.getRequestDispatcher("admin_home.jsp");
		HttpSession session = request.getSession();

		if (session.getAttribute("user") == null || !((KhachHang) session.getAttribute("user")).getIsAdmin()) {
			response.sendRedirect("/Java/home");
		} else {
			HoaDonBO hbo = new HoaDonBO();
			if (request.getParameter("mahoadon") != null) {
				long mahoadon = (long) Integer.parseInt(request.getParameter("mahoadon"));
				hbo.confirm(mahoadon);
			}

			session.setAttribute("order", hbo.admin_getHoaDon());

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
