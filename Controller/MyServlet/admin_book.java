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
import bo.SachBO;

/**
 * Servlet implementation class admin_book
 */
@WebServlet("/admin_book")
public class admin_book extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public admin_book() {
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

		RequestDispatcher rd = request.getRequestDispatcher("admin_book.jsp");
		HttpSession session = request.getSession();

		if (session.getAttribute("user") == null || !((KhachHang) session.getAttribute("user")).getIsAdmin()) {
			response.sendRedirect("/Java/home");
		} else {
			SachBO sbo = new SachBO();
			LoaiBO lbo = new LoaiBO();

			if (request.getParameter("typeclick") != null) {
				String typeclick = request.getParameter("typeclick");
				String masach = request.getParameter("masach");
				String name = request.getParameter("name");
				String author = request.getParameter("author");
				String maloai = request.getParameter("maloai");
				String amount = request.getParameter("amount");
				String price = request.getParameter("price");
				String url = request.getParameter("url") != null && request.getParameter("url").length() != 0
						? request.getParameter("url")
						: null;

				if (typeclick.equals("add")) {
					sbo.insertBook(name, author, maloai, amount, price, url);
				} else if (typeclick.equals("edit")) {
					sbo.updateBook(name, author, maloai, amount, price, url, masach);
				} else if (typeclick.equals("delete")) {
					sbo.deleteBook(masach);
				}
			}

			session.setAttribute("book", sbo.admin_getBook());
			session.setAttribute("type", lbo.getLoai());

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
