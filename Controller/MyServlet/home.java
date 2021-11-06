package MyServlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Loai;
import bean.Sach;
import bo.LoaiBO;
import bo.SachBO;

@WebServlet("/home")
public class home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public home() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
			HttpSession session = request.getSession();

			if (session.getAttribute("cart") == null) {
				session.setAttribute("cart", "");
			}

			if (request.getParameter("add") != null) {
				String cartStore = (String) session.getAttribute("cart");
				if (cartStore.length() == 0)
					cartStore += request.getParameter("add");
				else
					cartStore += "," + request.getParameter("add");
				session.setAttribute("cart", cartStore);
				response.sendRedirect("/Java/home");
			} else {
				LoaiBO lbo = new LoaiBO();
				SachBO sbo = new SachBO();

				ArrayList<Loai> dsLoai = lbo.getLoai();
				ArrayList<Sach> dsSach = sbo.getSach();

				request.setAttribute("dsLoai", dsLoai);
				request.setAttribute("dsSach", dsSach);

				rd.forward(request, response);
			}
		} catch(Exception e) {
			System.out.print(e.getMessage());
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
