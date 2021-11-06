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
	
}