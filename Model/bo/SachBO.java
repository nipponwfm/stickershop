package bo;

import java.util.ArrayList;

import bean.Sach;
import dao.SachDAO;

public class SachBO {
	SachDAO sDAO = new SachDAO();
	ArrayList<Sach> ds = sDAO.getSach();
	
	public ArrayList<Sach> getSach(){
		return ds;
	}
	
	public ArrayList<Sach> TimLoai(String maLoai){
		ArrayList<Sach> tam = new ArrayList<Sach>() ;
		for (Sach s: ds) { // forEach
			if (s.getMaLoai().equals(maLoai)) {
				tam.add(s);
			}
		}
		return tam;
	}
	
	public Sach TimSach(String maSach){
		Sach tam = new Sach();
		for (Sach s: ds) { // forEach
			if (s.getMaSach().equals(maSach)) {
				tam = s;
			}
		}
		return tam;
	}
	
	public ArrayList<Sach> Tim(String key){
		ArrayList<Sach> tam = new ArrayList<Sach>() ;
		for (Sach s: ds) { // forEach
			if (s.getMaLoai().toLowerCase().contains(key.toLowerCase()) ||
					s.getMaSach().toLowerCase().contains(key.toLowerCase()) ||
					s.getTacGia().toLowerCase().contains(key.toLowerCase()) ||
					s.getTenSach().toLowerCase().contains(key.toLowerCase()) 
					) {
				tam.add(s);
			}
		}
		return tam;
	}
}