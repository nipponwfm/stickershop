package bo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.GioHang;
import context.DBContext;

public class GioHangBO {
	Connection conn = null;
	PreparedStatement cmd = null;
	ResultSet rs = null;

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
	
	public long createMaHoaDon() {
		try {
			conn = new DBContext().getConnection();
			String query = "select top 1 * from hoadon order by MaHoaDon desc";
			long mahoadon = 0;
			cmd = conn.prepareStatement(query);
			rs = cmd.executeQuery();
			ResultSet rs = cmd.executeQuery();
			while (rs.next()) {
				mahoadon = rs.getLong("MaHoaDon");
			}
			return mahoadon + 1;
		} catch(Exception e) {
			System.out.print("Khong the lay ma hoa don: " + e.getMessage());
		}
		return 0;
	}
	
	public void setHoaDon(long makh) {
		try {
			conn = new DBContext().getConnection();
			String query = "insert into hoadon (makh, damua) values(?, 0)";
			cmd = conn.prepareStatement(query);
			cmd.setLong(1, makh);
			cmd.executeUpdate();
		} catch(Exception e) {
			System.out.print("Khong the lap hoa don: " + e.getMessage());
		}
	}
	
	public void purchase(String ms, Long sl, long mahoadon) {
		try {
			conn = new DBContext().getConnection();
			String query = "INSERT INTO ChiTietHoaDon (MaSach, SoLuongMua, MaHoaDon) values(?, ?, ?)";
			cmd = conn.prepareStatement(query);
			cmd.setString(1, ms);
			cmd.setLong(2, sl);
			cmd.setLong(3, mahoadon);
			
			cmd.executeUpdate();
		} catch(Exception e) {
			System.out.print("Khong the thuc hien thanh toan: " + e.getMessage());
		}
	}

	public ArrayList<String> getHistory(long makh) {
		try {
			ArrayList<String> tam = new ArrayList<String>() ;
			String query = ""
					+ "select s.tensach, s.tacgia, s.gia, h.NgayMua, h.damua, ct.SoLuongMua, s.gia * ct.SoLuongMua as Tong "
					+ "from hoadon as h "
					+ "join ChiTietHoaDon as ct on h.MaHoaDon = ct.MaHoaDon and h.makh = ? "
					+ "join sach as s on ct.MaSach = s.masach "
					+ "order by ct.MaHoaDon desc";
			conn = new DBContext().getConnection();
			cmd = conn.prepareStatement(query);
			cmd.setLong(1, makh);
			rs = cmd.executeQuery();

			while (rs.next()) {
				String tensach = rs.getString("tensach");
				String tacgia = rs.getString("tacgia");
				Long gia = rs.getLong("gia");
				Date ngaymua = rs.getDate("NgayMua");
				int damua = rs.getInt("damua");  
				Long SoLuongMua = rs.getLong("SoLuongMua");
				Long total = rs.getLong("Tong");
				tam.add(tensach + ":" + tacgia + ":" + gia + ":" + ngaymua + ":" + damua + ":" + SoLuongMua + ":" + total);
			}
			return tam;
		} catch(Exception e) {
			System.out.print("Khong the lay lich su thanh toan: " + e.getMessage());
		}
		return null;
	}
}