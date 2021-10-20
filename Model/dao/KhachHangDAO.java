package dao;

import java.util.ArrayList;

import bean.KhachHang;

public class KhachHangDAO {

	public ArrayList<KhachHang> getKhachHang(){
		 ArrayList<KhachHang> ds = new ArrayList<KhachHang>();
		 ds.add(new KhachHang("1","Dong 1","Dia Chi 1","1"));
		 return ds;
	}
}