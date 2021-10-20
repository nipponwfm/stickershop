package bean;

public class Sach {
	private String maSach;
	private String tenSach;
	private String tacGia;
	private Long soLuong;
	private Long gia;
	private String url;
	private String maLoai;
	
	public Sach(String maSach, String tenSach, String tacGia, Long soLuong, Long gia, String url, String maLoai) {
		super();
		this.maSach = maSach;
		this.tenSach = tenSach;
		this.tacGia = tacGia;
		this.soLuong = soLuong;
		this.gia = gia;
		this.url = url;
		this.maLoai = maLoai;
	}
	public Sach() {
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
	public String getTacGia() {
		return tacGia;
	}
	public void setTacGia(String tacGia) {
		this.tacGia = tacGia;
	}
	public Long getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(Long soLuong) {
		this.soLuong = soLuong;
	}
	public Long getGia() {
		return gia;
	}
	public void setGia(Long gia) {
		this.gia = gia;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMaLoai() {
		return maLoai;
	}
	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}
}