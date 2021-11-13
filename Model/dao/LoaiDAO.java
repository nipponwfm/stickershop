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

	public void insertLoai(String maloai, String tenloai) {
		String query = "insert into loai" + " values (?, ?)";
		try {
			conn = new DBContext().getConnection();
			cmd = conn.prepareStatement(query);
			cmd.setString(1, maloai);
			cmd.setString(2, tenloai);
			cmd.executeUpdate();
		} catch (Exception e) {
			System.out.println("LoaiDAO - Khong the insertLoai - " + e.getMessage());
		}
	}

	public void updateLoai(String maloai, String value) {
		String query = "update loai" + " set tenloai = ?" + " where maloai = ?";
		try {
			conn = new DBContext().getConnection();
			cmd = conn.prepareStatement(query);
			cmd.setString(1, value);
			cmd.setString(2, maloai);
			cmd.executeUpdate();
		} catch (Exception e) {
			System.out.println("LoaiDAO - Khong the updateLoai - " + e.getMessage());
		}
	}
	
	public void deleteLoai(String maloai) {
		String query = "DELETE FROM loai WHERE maloai = ?";
		try {
			conn = new DBContext().getConnection();
			cmd = conn.prepareStatement(query);
			cmd.setString(1, maloai);
			cmd.executeUpdate();
		} catch (Exception e) {
			System.out.println("LoaiDAO - Khong the deleteLoai - " + e.getMessage());
		}
	}
	
	public ArrayList<String[]> admin_getType() {
		ArrayList<String> type = new ArrayList<String>();
		ArrayList<String[]> _type = new ArrayList<String[]>();
		String query = "select l.maloai, l.tenloai, count(s.masach) as total"
				+ " from loai as l"
				+ " left join sach as s on s.maloai = l.maloai and s.soluong > 0"
				+ " group by l.maloai, l.tenloai";
		
		try {
			conn = new DBContext().getConnection();
			cmd = conn.prepareStatement(query);
			rs = cmd.executeQuery();
			
			while (rs.next()) {
				String maloai = rs.getString("maloai");
				String tenloai = rs.getString("tenloai");
				String total = rs.getString("total");
				type.add(maloai + "<:>" + tenloai + "<:>" + total);
			}
			
			for (String s : type) {
				String[] splitBook = s.split("<:>");
				_type.add(splitBook);
			}
		} catch (Exception e) {
			System.out.println("LoaiDAO - Khong the admin_getType - " + e.getMessage());
		}
		
		return _type;
	}
}