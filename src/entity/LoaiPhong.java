package entity;

public class LoaiPhong {
	private String maLoai;
	private String tenLoai;
	public LoaiPhong() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoaiPhong(String maLoai, String tenLoai) {
		super();
		this.maLoai = maLoai;
		this.tenLoai = tenLoai;
	}
	public String getMaLoai() {
		return maLoai;
	}
	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}
	public String getTenLoai() {
		return tenLoai;
	}
	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}
	@Override
	public String toString() {
		return "LoaiPhong [maLoai=" + maLoai + ", tenLoai=" + tenLoai + "]";
	}
	
	
}
