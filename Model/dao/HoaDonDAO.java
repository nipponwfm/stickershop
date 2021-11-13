package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import context.DBContext;

public class HoaDonDAO {
	Connection conn = null;
	PreparedStatement cmd = null;
	ResultSet rs = null;
	
	public void setHoaDon(long makh) {
		try {
			conn = new DBContext().getConnection();
			String query = "insert into hoadon (makh) values(?)";
			cmd = conn.prepareStatement(query);
			cmd.setLong(1, makh);
			cmd.executeUpdate();
		} catch(Exception e) {
			System.out.print("Khong the lap hoa don: " + e.getMessage());
		}
	}
	
	public long getHoaDon() {
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
	
	public void purchase(String ms, Long sl, long mahoadon) {
		try {
			String query = "INSERT INTO ChiTietHoaDon (MaSach, SoLuongMua, MaHoaDon, damua) values(?, ?, ?, 0)";
			conn = new DBContext().getConnection();
			cmd = conn.prepareStatement(query);
			cmd.setString(1, ms);
			cmd.setLong(2, sl);
			cmd.setLong(3, mahoadon);
			
			cmd.executeUpdate();
		} catch(Exception e) {
			System.out.print("Khong the thuc hien thanh toan: " + e.getMessage());
		}
	}
	
	public void updateAmountBookAfterConfirm(long mahoadon) {
		String query = "";
		String masach = "";
		long soluongmua = 0;
		
		try {
			query = "select * from ChiTietHoaDon where machitiethd = ?";
			conn = new DBContext().getConnection();
			cmd = conn.prepareStatement(query);
			cmd.setLong(1, mahoadon);
			rs = cmd.executeQuery();
			while (rs.next()) {
				masach = rs.getString("masach");
				soluongmua = rs.getLong("soluongmua");
			}
			
			query = "UPDATE sach"
					+ " SET soluong = soluong - ?"
					+ " where masach = ?";
			conn = new DBContext().getConnection();
			cmd = conn.prepareStatement(query);
			cmd.setLong(1, soluongmua);
			cmd.setString(2, masach);
			cmd.executeUpdate();
			
		} catch(Exception e) {
			System.out.println("HoaDonDAO - Khong the updateAmountBookAfterConfirm - " + e.getMessage());	
		}
	}
	
	public void confirm(long mahoadon) {
		String query = "update chitiethoadon" + " set damua = 1" + " where machitiethd = ?";
		try {
			conn = new DBContext().getConnection();
			cmd = conn.prepareStatement(query);
			cmd.setLong(1, mahoadon);
			cmd.executeUpdate();
			
			updateAmountBookAfterConfirm(mahoadon);
			
		} catch(Exception e) {
			System.out.println("HoaDonDAO - Khong the confirm - " + e.getMessage());
		}
	}
	
	public ArrayList<String[]> admin_getHoaDon() {
		ArrayList<String> order = new ArrayList<String>();
		ArrayList<String[]> _order = new ArrayList<String[]>();
		String query = "SELECT dbo.KhachHang.makh, dbo.KhachHang.hoten, dbo.KhachHang.diachi, dbo.KhachHang.sodt, dbo.KhachHang.email, dbo.hoadon.MaHoaDon, dbo.hoadon.NgayMua, dbo.ChiTietHoaDon.damua, dbo.ChiTietHoaDon.MaSach,"
				+ " dbo.ChiTietHoaDon.SoLuongMua, dbo.ChiTietHoaDon.MaChiTietHD, dbo.sach.tensach, dbo.sach.soluong, dbo.sach.gia, dbo.loai.maloai, dbo.loai.tenloai"
				+ " FROM dbo.ChiTietHoaDon INNER JOIN"
				+ " dbo.hoadon ON dbo.ChiTietHoaDon.MaHoaDon = dbo.hoadon.MaHoaDon INNER JOIN"
				+ " dbo.KhachHang ON dbo.hoadon.makh = dbo.KhachHang.makh INNER JOIN"
				+ " dbo.sach ON dbo.ChiTietHoaDon.MaSach = dbo.sach.masach INNER JOIN"
				+ " dbo.loai ON dbo.sach.maloai = dbo.loai.maloai"
				+ " order by dbo.ChiTietHoaDon.damua, dbo.hoadon.NgayMua desc";
		try {
			conn = new DBContext().getConnection();
			cmd = conn.prepareStatement(query);
			rs = cmd.executeQuery();

			while (rs.next()) {
				String makh = rs.getString("makh");
				String hoten = rs.getString("hoten");
				String diachi = rs.getString("diachi");
				String sodt = rs.getString("sodt");
				String email = rs.getString("email");
				String MaHoaDon = rs.getString("MaHoaDon");
				Date NgayMua = rs.getDate("NgayMua");
				String damua = rs.getString("damua");
				String MaSach = rs.getString("MaSach");
				long SoLuongMua = rs.getLong("SoLuongMua");
				String MaChiTietHD = rs.getString("MaChiTietHD");
				String tensach = rs.getString("tensach");
				long soluong = rs.getLong("soluong");
				long gia = rs.getLong("gia");
				String maloai = rs.getString("maloai");
				String tenloai = rs.getString("tenloai");
				order.add(makh + ":" + hoten + ":" + diachi + ":" + sodt + ":" + email + ":" + MaHoaDon + ":"
						+ NgayMua + ":" + damua + ":" + MaSach + ":" + SoLuongMua + ":" + MaChiTietHD + ":"
						+ tensach + ":" + soluong + ":" + gia + ":" + maloai + ":" + tenloai);
			}

			for (String s : order) {
				String[] splitOrder = s.split(":");
				_order.add(splitOrder);
			}
		} catch(Exception e) {
			System.out.println("HoaDonDAO - Khong the admin_getHoaDon - " + e.getMessage());
		}
		
		return _order;
	}
}
