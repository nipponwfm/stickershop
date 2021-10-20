package dao;

import java.util.ArrayList;

import bean.Sach;

public class SachDAO {
	public ArrayList<Sach> getSach(){
		 ArrayList<Sach> ds = new ArrayList<Sach>();
		 
		 for (int i = 1; i <= 10; i++) {
			 ds.add(new Sach("MS" + i, "Book " + i, "Dong " + i,(long)100,(long)120000,"images/1 ("+i+").jpg","cntt"));	 
		 }
		 
		 return ds;
	}
}