package bo;

import java.util.ArrayList;

import bean.KhachHang;
import dao.KhachHangDAO;

public class KhachHangBO {
	KhachHangDAO khDAO = new KhachHangDAO();
	ArrayList<KhachHang> ds = khDAO.getKhachHang();
	
	public ArrayList<KhachHang> getKhachHang(){
		return ds;
	}
	
	public KhachHang login(String user, String matKhau){
		KhachHang tam = new KhachHang();
		for (KhachHang kh: ds) { // forEach
			if (kh.getTendn().equals(user) && kh.getMatKhau().equals(matKhau)) {
				tam = kh;
				return tam;
			}
		}
		return null;
	}
	
	public ArrayList<String> getHistory(long makh) {
		return khDAO.getHistory(makh);
	}
	
	public String register(String fullname, String address, String number, String email, String user, String pass) {
		return khDAO.register(fullname, address, number, email, user, pass);
	}
	
	public ArrayList<String[]> admin_getUser() {
		return khDAO.admin_getUser();
	}
}