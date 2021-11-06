package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.Loai;
import context.DBContext;

public class LoaiDAO {
	Connection conn = null;
	PreparedStatement cmd = null;
	ResultSet rs = null;
	
	public ArrayList<Loai> getLoai() {
		try {
			ArrayList<Loai> ds = new ArrayList<Loai>();
			String query = "select * from loai";
			conn = new DBContext().getConnection();
			cmd = conn.prepareStatement(query);
			rs = cmd.executeQuery();
			
			ResultSet rs = cmd.executeQuery();
			while (rs.next()) {
				String maloai = rs.getString("maloai");
				String tenloai = rs.getString("tenloai");
				ds.add(new Loai(maloai, tenloai));
			}
			
			rs.close();
			conn.close();
			return ds;
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		return null;
	}
}