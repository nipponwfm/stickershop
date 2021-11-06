package bo;

import java.util.ArrayList;

import bean.KhachHang;
import dao.KhachHangDAO;

public class KhachHangBO {
	KhachHangDAO khBO = new KhachHangDAO();
	ArrayList<KhachHang> ds = khBO.getKhachHang();
	
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
}