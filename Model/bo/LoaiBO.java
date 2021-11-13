package bo;

import java.util.ArrayList;

import bean.Loai;
import dao.LoaiDAO;

public class LoaiBO {
	LoaiDAO lDAO = new LoaiDAO();
	ArrayList<Loai> ds = lDAO.getLoai();
	
	public ArrayList<Loai> getLoai() {
		return ds;
	}
	
	public void insertLoai(String maloai, String tenloai) {
		lDAO.insertLoai(maloai, tenloai);
	}
	
	public void updateLoai(String maloai, String value) {
		lDAO.updateLoai(maloai, value);
	}
	
	public void deleteLoai(String maloai) {
		lDAO.deleteLoai(maloai);
	}
	
	public ArrayList<String[]> admin_getType() {
		return lDAO.admin_getType();
	}
}