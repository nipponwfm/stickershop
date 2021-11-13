package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;

import bean.Sach;
import context.DBContext;

public class SachDAO {
	Connection conn = null;
	PreparedStatement cmd = null;
	ResultSet rs = null;

	public ArrayList<Sach> getSach() {
		try {
			ArrayList<Sach> ds = new ArrayList<Sach>();
			String query = "select top 10 * from sach as s where s.anh is not null and s.soluong>0 order by s.NgayNhap desc";
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

	public void insertBook(String name, String author, String maloai, String amount, String price, String url) {
		Random rand = new Random();
		String query = "insert into sach(tensach, tacgia, maloai, soluong, gia, anh, masach)"
				+ " values (?, ?, ?, ?, ?, ?, ?)";
		try {
			conn = new DBContext().getConnection();
			cmd = conn.prepareStatement(query);
			cmd.setString(1, name);
			cmd.setString(2, author);
			cmd.setString(3, maloai);
			cmd.setLong(4, (long) Integer.parseInt(amount));
			cmd.setLong(5, (long) Integer.parseInt(price));
			cmd.setString(6, url);
			cmd.setString(7, String.valueOf(rand.nextInt(10000) + rand.nextInt(10000) + rand.nextInt(10000) + rand.nextInt(10000)));
			cmd.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("SachDAO - Khong the insertBook - " + e.getMessage());
		}
	}
	
	public void updateBook(String name, String author, String maloai, String amount, String price, String url, String masach) {
		String query = "update sach"
				+ " set tensach=?, tacgia=?, maloai=?, soluong=?, gia=?, anh=?"
				+ " where masach = ?";
		try {
			conn = new DBContext().getConnection();
			cmd = conn.prepareStatement(query);
			cmd.setString(1, name);
			cmd.setString(2, author);
			cmd.setString(3, maloai);
			cmd.setLong(4, (long) Integer.parseInt(amount));
			cmd.setLong(5, (long) Integer.parseInt(price));
			cmd.setString(6, url);
			cmd.setString(7, masach);
			cmd.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("SachDAO - Khong the updateBook - " + e.getMessage());		
		}
	}
	
	public void deleteBook(String masach) {
		String query = "DELETE FROM sach WHERE masach = ?";
		try {
			conn = new DBContext().getConnection();
			cmd = conn.prepareStatement(query);
			cmd.setString(1, masach);
			cmd.executeUpdate();	
		} catch (Exception e) {
			System.out.println("SachDAO - Khong the deleteBook - " + e.getMessage());		
		}
	}
	
	public ArrayList<String[]> admin_getBook() {
		ArrayList<String> book = new ArrayList<String>();
		ArrayList<String[]> _book = new ArrayList<String[]>();
		String query = "select top 20 * from sach as s"
				+ " join loai as l on l.maloai = s.maloai"
				+ " order by s.Ngaynhap desc";
		try {
			conn = new DBContext().getConnection();
			cmd = conn.prepareStatement(query);
			rs = cmd.executeQuery();
			
			while (rs.next()) {
				String tensach = rs.getString("tensach");
				String soluong = rs.getString("soluong");
				String gia = rs.getString("gia");
				String maloai = rs.getString("maloai");
				String tenloai = rs.getString("tenloai");
				String anh = rs.getString("anh");
				String tacgia = rs.getString("tacgia");
				String masach = rs.getString("masach");
				book.add(tensach + "<:>" + soluong + "<:>" + gia + "<:>" + maloai + "<:>" + tenloai + "<:>" + anh + "<:>"
						+ tacgia + "<:>" + masach);
			}
			
			for (String s : book) {
				String[] splitBook = s.split("<:>");
				_book.add(splitBook);
			}
		} catch (Exception e) {
			System.out.println("SachDAO - Khong the admin_getBook - " + e.getMessage());		
		}
		
		return _book;
	}
}