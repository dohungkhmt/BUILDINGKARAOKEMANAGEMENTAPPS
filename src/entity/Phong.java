package entity;

import java.sql.SQLException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;


public class Phong {
	private String maPhong;
	private LoaiPhong loaiPhong;
	private String tenPhong;
	private int sucChua;
	private double giaPhong;
	private String tinhTrang;
	public Phong() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Phong(String maPhong, LoaiPhong loaiPhong, String tenPhong, int sucChua, double giaPhong, String tinhTrang) {
		super();
		this.maPhong = maPhong;
		this.loaiPhong = loaiPhong;
		this.tenPhong = tenPhong;
		this.sucChua = sucChua;
		this.giaPhong = giaPhong;
		this.tinhTrang = tinhTrang;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maPhong);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Phong other = (Phong) obj;
		return Objects.equals(maPhong, other.maPhong);
	}

	public String getMaPhong() {
		return maPhong;
	}
	public void setMaPhong(String maPhong){
		this.maPhong = maPhong;
	}
	public LoaiPhong getLoaiPhong() {
		return loaiPhong;
	}
	public void setLoaiPhong(LoaiPhong loaiPhong) {
		this.loaiPhong = loaiPhong;
	}
	public String getTenPhong() {
		return tenPhong;
	}
	public void setTenPhong(String tenPhong) {
		this.tenPhong = tenPhong;
	}
	public int getSucChua() {
		return sucChua;
	}
	public void setSucChua(int sucChua) {
		this.sucChua = sucChua;
	}
	public double getGiaPhong() {
		return giaPhong;
	}
	public void setGiaPhong(double giaPhong) {
		this.giaPhong = giaPhong;
	}
	public String getTinhTrang() {
		return tinhTrang;
	}
	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}
	@Override
	public String toString() {
		return "Phong [maPhong=" + maPhong + ", loaiPhong=" + loaiPhong+ ", tenPhong=" + tenPhong + ", sucChua="
				+ sucChua + ", giaPhong=" + giaPhong + ", tinhTrang=" + tinhTrang + "]";
	}
	
	
}
