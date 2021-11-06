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

@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		
		if (request.getParameter("signout")!=null) {
			session.setAttribute("user", null);
		} else {
			KhachHangBO khbo = new KhachHangBO();
			
			String user = request.getParameter("user");
			String pwd = request.getParameter("pwd");
			
			KhachHang kh = khbo.login(user, pwd);
			session.setAttribute("user", kh);
		}
		
		response.sendRedirect("/Java/home");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
