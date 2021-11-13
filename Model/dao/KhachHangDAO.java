package dao;

import java.sql.Connection;
import java.sql.Date;
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
				Boolean isAdmin = rs.getBoolean("isAdmin");
				ds.add(new KhachHang(maKH, hoTen, diaChi, sdt, email, tendn, matKhau, isAdmin));
			}

			return ds;
		} catch(Exception e) {
			System.out.print("Khong the lay du lieu khach hang tu database: " + e.getMessage());
		}
		return null;
	}
	
	public String register(String fullname, String address, String number, String email, String user, String pass) {
		try {
			String query = "" + "insert into KhachHang(hoten, diachi, sodt, email, tendn, pass) "
					+ "values(?, ?, ?, ?, ?, ?)";
			conn = new DBContext().getConnection();
			cmd = conn.prepareStatement(query);
			cmd.setString(1, fullname);
			cmd.setString(2, address);
			cmd.setString(3, number);
			cmd.setString(4, email);
			cmd.setString(5, user);
			cmd.setString(6, pass);

			cmd.executeUpdate();
			
			return null;
		} catch(Exception e) {
			return e.getMessage();
		}
	}
	
	public ArrayList<String> getHistory(long makh) {
		try {
			ArrayList<String> tam = new ArrayList<String>() ;
			String query = ""
					+ "select s.tensach, s.tacgia, s.gia, h.NgayMua, ct.damua, ct.SoLuongMua, s.gia * ct.SoLuongMua as Tong "
					+ "from hoadon as h "
					+ "join ChiTietHoaDon as ct on h.MaHoaDon = ct.MaHoaDon and h.makh = ? "
					+ "join sach as s on ct.MaSach = s.masach "
					+ "order by h.NgayMua desc, ct.damua";
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
	
	public ArrayList<String[]> admin_getUser() {
		ArrayList<String> user = new ArrayList<String>();
		ArrayList<String[]> _user = new ArrayList<String[]>();
		String query = "select tb1.*, tb2.chuamua "
				+ "from ( "
				+ "	select kh.makh, kh.hoten, kh.diachi, kh.sodt,  "
				+ "			sum(ct.soluongmua*s.gia) as totalmua,  "
				+ "			count(ct.MaChiTietHD) as damua "
				+ "	from ChiTietHoaDon as ct "
				+ "	join hoadon as h on h.MaHoaDon = ct.MaHoaDon and ct.damua = 1 "
				+ "	join sach as s on s.masach = ct.MaSach "
				+ "	right join KhachHang as kh on kh.makh = h.makh "
				+ "	group by kh.makh, kh.hoten, kh.diachi, kh.sodt, ct.damua "
				+ ") as tb1 "
				+ "right join ( "
				+ "	select kh.makh, count(ct.MaChiTietHD) as chuamua "
				+ "	from ChiTietHoaDon as ct "
				+ "	join hoadon as h on h.MaHoaDon = ct.MaHoaDon and ct.damua = 0 "
				+ "	join sach as s on s.masach = ct.MaSach "
				+ "	right join KhachHang as kh on kh.makh = h.makh "
				+ "	group by kh.makh, kh.hoten, kh.diachi, kh.sodt, ct.damua "
				+ ") as tb2 on tb1.makh = tb2.makh "
				+ "order by tb1.totalmua desc ";
		
		try {
			conn = new DBContext().getConnection();
			cmd = conn.prepareStatement(query);
			rs = cmd.executeQuery();

			while (rs.next()) {
				String hoten = rs.getString("hoten");
				String diachi = rs.getString("diachi");
				String sodt = rs.getString("sodt");
				String totalmua = rs.getString("totalmua");
				String damua = rs.getString("damua");
				String chuamua = rs.getString("chuamua");
				user.add(hoten + "<:>" + diachi + "<:>" + sodt + "<:>" + totalmua + "<:>" + damua + "<:>" + chuamua);
			}

			for (String s : user) {
				String[] splitBook = s.split("<:>");
				_user.add(splitBook);
			}	
		} catch(Exception e) {
			System.out.print(e.getMessage());
		}
		
		return _user;
	}
}