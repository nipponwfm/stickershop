package bean;

public class KhachHang {
	private String maKH;
	private String hoTen;
	private String diaChi;
	private String matKhau;
	
	public KhachHang(String maKH, String hoTen, String diaChi, String matKhau) {
		super();
		this.maKH = maKH;
		this.hoTen = hoTen;
		this.diaChi = diaChi;
		this.matKhau = matKhau;
	}
	public KhachHang() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getMaKH() {
		return maKH;
	}
	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
}