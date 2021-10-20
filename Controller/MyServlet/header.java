package MyServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.KhachHang;
import bo.KhachHangBO;

@WebServlet("/header")
public class header extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public header() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		
		KhachHangBO khbo = new KhachHangBO();
		
		String user = request.getParameter("user");
		String pwd = request.getParameter("pwd");
		
		KhachHang kh = khbo.Tim(user, pwd);
		if (kh!=null) {
			session.setAttribute("name", kh.getHoTen());
		}
		
		response.sendRedirect("/Java/home");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
