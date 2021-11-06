package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.KhachHang;
import context.DBContext;

public class KhachHangDAO {
	Connection conn = null;
	PreparedStatement cmd = null;
	ResultSet rs = null;
	
	public ArrayList<KhachHang> getKhachHang(){
		try {
			ArrayList<KhachHang> ds = new ArrayList<KhachHang>();
			String query = "select * from khachhang";
			conn = new DBContext().getConnection();
			cmd = conn.prepareStatement(query);
			rs = cmd.executeQuery();

			while (rs.next()) {
				long maKH = rs.getLong("makh");
				String hoTen = rs.getString("hoten");
				String diaChi = rs.getString("diachi");
				String sdt = rs.getString("sodt");
				String email = rs.getString("email");
				String tendn = rs.getString("tendn");
				String matKhau = rs.getString("pass");
				ds.add(new KhachHang(maKH, hoTen, diaChi, sdt, email, tendn, matKhau));
			}

			return ds;
		} catch(Exception e) {
			System.out.print(e.getMessage());
		}
		return null;
	}
}