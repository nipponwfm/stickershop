package dao;

import java.util.ArrayList;

import bean.Loai;

public class LoaiDAO {
	public ArrayList<Loai> getLoai(){
		 ArrayList<Loai> ds = new ArrayList<Loai>();
		 ds.add(new Loai("cntt","CÃ´ng nghá»‡ thÃ´ng tin"));
		 ds.add(new Loai("yte","Y táº¿"));
		 ds.add(new Loai("sinh","Sinh há»�c"));
		 ds.add(new Loai("vatly","Váº­t lÃ½"));
		 return ds;
	}
}