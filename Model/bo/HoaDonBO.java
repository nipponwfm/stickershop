package bo;

import java.util.ArrayList;

import dao.HoaDonDAO;

public class HoaDonBO {
	HoaDonDAO hDAO = new HoaDonDAO();
	
	public void setHoaDon(long makh) {
		hDAO.setHoaDon(makh);
	}
	
	public long getHoaDon() {
		return hDAO.getHoaDon();
	}
	
	public void purchase(String ms, Long sl, long mahoadon) {
		hDAO.purchase(ms, sl, mahoadon);
	}
	
	public void confirm(long mahoadon) {
		hDAO.confirm(mahoadon);
	}
	
	public ArrayList<String[]> admin_getHoaDon() {
		return hDAO.admin_getHoaDon();
	}
}
