package MyServlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.GioHang;
import bean.KhachHang;
import bean.Sach;
import bo.GioHangBO;
import bo.HoaDonBO;
import bo.SachBO;

@WebServlet("/cart")
public class cart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("cart.jsp");
		HttpSession session = request.getSession();

		// handle function click
		if (request.getParameter("type") != null) {
			GioHangBO ghbo = (GioHangBO) session.getAttribute("ghbo");
			String type = request.getParameter("type");
			String ms = request.getParameter("ms");
			long sl = (long)Integer.parseInt(request.getParameter("sl"));
			switch (type) {
			case "d_selected": {
				String[] msSplit = ms.split(",");
				for (String m : msSplit) {
					ghbo.Xoa(m);
				}
				break;
			}
			case "d_row": {
				ghbo.Xoa(ms);
				break;
			}
			case "d_all": {
				ghbo = new GioHangBO();
				break;
			}
			case "u_amount": {
				ghbo.UpdateSL(ms, sl);
				break;
			}
			case "purchase": {
				HoaDonBO hbo = new HoaDonBO();
				long makh = ((KhachHang)session.getAttribute("user")).getMaKH();
				long mahoadon = hbo.getHoaDon();
				hbo.setHoaDon(makh);
				for (GioHang gh : ghbo.getGioHang()) {
					hbo.purchase(gh.getMaSach(), gh.getSoLuong(), mahoadon);
				}
				
				ghbo = new GioHangBO();
				break;
			}
			}
			session.setAttribute("ghbo", ghbo);
			response.sendRedirect("/Java/cart");
			
		} else {
			// give user a cart if don't have one
			if (session.getAttribute("ghbo") == null) {
				session.setAttribute("ghbo", new GioHangBO());
			}

			// move CartAttribute(selected item to buy) to ghbo
			if (session.getAttribute("cart") != null && !session.getAttribute("cart").equals("")) {
				GioHangBO ghbo = (GioHangBO) session.getAttribute("ghbo");
				String[] cartSplit = ((String) session.getAttribute("cart")).split(",");
				SachBO sbo = new SachBO();
				for (String maSach : cartSplit) {
					Sach s = sbo.TimSach(maSach);
					ghbo.Them(s.getMaSach(), s.getTenSach(), s.getGia());
				}
				session.setAttribute("cart", "");
			}

			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
