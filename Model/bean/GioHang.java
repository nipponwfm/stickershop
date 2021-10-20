package bean;

public class GioHang {
	private String maSach;
	private String tenSach;
	private Long gia;
	private Long soLuong;
	private Long thanhtien;
	public GioHang(String maSach, String tenSach, Long gia, Long soLuong) {
		super();
		this.maSach = maSach;
		this.tenSach = tenSach;
		this.gia = gia;
		this.soLuong = soLuong;
		this.thanhtien = gia*soLuong;
	}
	public GioHang() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMaSach() {
		return maSach;
	}
	public void setMaSach(String maSach) {
		this.maSach = maSach;
	}
	public String getTenSach() {
		return tenSach;
	}
	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
	}
	public Long getGia() {
		return gia;
	}
	public void setGia(Long gia) {
		this.gia = gia;
	}
	public Long getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(Long soLuong) {
		this.soLuong = soLuong;
	}
	public Long getThanhtien() {
		return soLuong*gia;
	}
	public void setThanhtien(Long thanhtien) {
		this.thanhtien = thanhtien;
	}
}