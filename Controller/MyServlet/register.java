package MyServlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.KhachHangBO;

@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public register() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?"); // match a number with optional '-' and decimal.
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
		HttpSession session = request.getSession();

		String fullname = "";
		String user = "";
		String pass = "";
		String repass = "";
		String email = "";
		String address = "";
		String number = "";

		session.setAttribute("error", null);
		session.setAttribute("success", null);
		try {
			if (request.getParameter("HotenKH") != null) {
				KhachHangBO khbo = new KhachHangBO();
				
				fullname = request.getParameter("HotenKH");
				user = request.getParameter("TenDN");
				pass = request.getParameter("Matkhau");
				repass = request.getParameter("Matkhaunhatrlai");
				email = request.getParameter("Email");
				address = request.getParameter("Diachi");
				number = request.getParameter("Dienthoai");

				if (!pass.equals(repass))
					throw new Exception("Mật khẩu nhập lại không trùng khớp");

				if (!isNumeric(number))
					throw new Exception("Số điện thoại không hợp lệ");

				String handle_error_register = khbo.register(fullname, address, number, email, user, repass);
				if (handle_error_register!=null) {
					throw new Exception(handle_error_register);
				}
				
				session.setAttribute("success", "true");
			}
		} catch (Exception e) {
			String error = e.getMessage();
			if (error.contains(user)) error = "Tên tài khoản bị trùng";
			else if (error.contains(email)) error = "Email đã được sử dụng";

			request.setAttribute("HotenKH", fullname);
			request.setAttribute("TenDN", user);
			request.setAttribute("Email", email);
			request.setAttribute("Diachi", address);
			request.setAttribute("Dienthoai", number);

			session.setAttribute("error", error);
		}
		rd.forward(request, response);
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
