package MyServlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import context.DBContext;

@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection conn = null;
	PreparedStatement cmd = null;
	ResultSet rs = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public static boolean isNumeric(String str) {
    	return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
		HttpSession session = request.getSession();
		String handle_error_user = "";
		String handle_error_email = "";
		
		session.setAttribute("error", null);
		try {
			if (request.getParameter("HotenKH")!=null) {
				String fullname = request.getParameter("HotenKH");
				String user = request.getParameter("TenDN");
				String pass = request.getParameter("Matkhau");
				String repass = request.getParameter("Matkhaunhatrlai");
				String email = request.getParameter("Email");
				String address = request.getParameter("Diachi");
				String number = request.getParameter("Dienthoai");
				
				handle_error_user = user;
				handle_error_email = email;
				
				if (!pass.equals(repass))
					throw new Exception("Mật khẩu nhập lại không trùng khớp");
				
				if (!isNumeric(number))
					throw new Exception("Số điện thoại không hợp lệ");
				
				String query = ""
						+ "insert into KhachHang(hoten, diachi, sodt, email, tendn, pass) "
						+ "values(?, ?, ?, ?, ?, ?)";
				conn = new DBContext().getConnection();
				cmd = conn.prepareStatement(query);
				cmd.setString(1, fullname);
				cmd.setString(2, address);
				cmd.setString(3, number);
				cmd.setString(4, email);
				cmd.setString(5, user);
				cmd.setString(6, pass);
			
				cmd.executeUpdate();
			}
		} catch(Exception e) {
			String error = e.getMessage();
			if (error.contains(handle_error_user)) error = "Tên tài khoản bị trùng";
			if (error.contains(handle_error_email)) error = "Email đã được sử dụng";
			session.setAttribute("error", error);;
		}
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
