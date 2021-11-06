package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.Sach;
import context.DBContext;

public class SachDAO {
	Connection conn = null;
	PreparedStatement cmd = null;
	ResultSet rs = null;
	
	public ArrayList<Sach> getSach() {
		try {
			ArrayList<Sach> ds = new ArrayList<Sach>();
			String query = "select top 10 * from sach as s where s.anh is not null";
			conn = new DBContext().getConnection();
			cmd = conn.prepareStatement(query);
			rs = cmd.executeQuery();

			while (rs.next()) {
				String masach = rs.getString("masach");
				String tensach = rs.getString("tensach");
				String tacgia = rs.getString("tacgia");
				Long soluong = rs.getLong("soluong");
				Long gia = rs.getLong("gia");
				String anh = rs.getString("anh");
				String maloai = rs.getString("maloai");
				ds.add(new Sach(masach, tensach, tacgia, soluong, gia, anh, maloai));
			}

			return ds;
		} catch(Exception e) {
			System.out.print(e.getMessage());
		}
		return null;
	}
}