package bo;

import java.util.ArrayList;

import bean.GioHang;

public class GioHangBO {
	
	public ArrayList<GioHang> ds = new ArrayList<GioHang>();
	
	public ArrayList<GioHang> getGioHang() {
		return ds;
	}
	
	public void Them(String masach, String tensach, Long gia) {
		int flag = 0;
		for (GioHang gh : ds) {
			if (gh.getMaSach().equals(masach)) {
				gh.setSoLuong(gh.getSoLuong()+1);
				gh.setThanhtien(gh.getThanhtien());
				flag = 1;
				break;
			}
		}
		if (flag==0) ds.add(new GioHang(masach, tensach, gia, (long)1));
	}
	
	public void UpdateSL(String masach, long sl) {
		for (GioHang gh : ds) {
			if (gh.getMaSach().equals(masach)) {
				gh.setSoLuong(sl);
				gh.setThanhtien(sl);
				break;
			}
		}
	}
	
	public void Xoa(String masach) {
		for (GioHang gh : ds) {
			if (gh.getMaSach().equals(masach)) {
				ds.remove(gh); 
				break;
			}
		}
	}
	
	public Long Tong() {
		Long s = (long)0;
		for (GioHang gh : ds) {
			s = s+ gh.getThanhtien();
		}
		return s;
	}
}