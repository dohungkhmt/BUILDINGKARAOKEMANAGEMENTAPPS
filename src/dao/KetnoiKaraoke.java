/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nguyenchienjf
 */

public class KetnoiKaraoke {
	String ketnoi = "jdbc:sqlserver://localhost:1433;databaseName=QLYKARAOKE;user=sa;password=123456";
	private Connection con;
	DateFormat ngay = new SimpleDateFormat("dd-MM-yyyy");
	DateFormat gio = new SimpleDateFormat("HH:mm");
	Calendar cal = Calendar.getInstance();
	public String ngayHt = ngay.format(cal.getTime());
	public String gioHt = gio.format(cal.getTime());
	TruyvanKaraoke adapter = new TruyvanKaraoke();
	String demPhongTrong = "where tinhtrang='0'";
	public String demPhongDat = "where tinhtrang='1'";
	String demPhongThuong = "where loai_phong='1'";
	String demPhongVip = "where loai_phong='2'";
	public String demHddv = "where tb_hoadondv.tinhtrang=N'0'";
	public String demtien1 = "select gia_dv from tb_hoadondv, tb_dichvu where tb_dichvu.ma_dv=tb_hoadondv.ma_dv and tb_hoadondv.tinhtrang=N'0' order by ma_hddv asc";

	public String ma_tk(String taikhoan, String matkhau) {
		String ma_tk = "0";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(ketnoi);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(
					"select * from tb_login where taikhoan like'" + taikhoan + "' and matkhau=N'" + matkhau + "'");
			while (rs.next()) {
				ma_tk = rs.getString("ma_tk");
			}
			return ma_tk;
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "lỗi login: " + ex.toString());
			return null;
		}
	}

	public String demGio(String ngayden, String gioden, String ngaydi, String giodi) {
		String dateStart = ngayden + " " + gioden;
		String dateStop = ngaydi + " " + giodi;
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");

		Date d1 = null;
		Date d2 = null;

		try {
			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);

			long diff = d2.getTime() - d1.getTime();
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);

			return diffHours + "";

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "lỗi đếm giờ: " + e.toString());
			return null;
		}
	}

	public void luuid(String idHd, String idTk) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(ketnoi);
			Statement st = con.createStatement();
			st.executeUpdate(
					"insert into id  values(N'" + adapter.Id("id", "ma_id") + "',N'" + idHd + "',N'" + idTk + "')");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "lỗi lưu id");
		}
	}
	public boolean daChonPhong(boolean daChonPhong ) {
		return daChonPhong;
	}

	public String Id(String idlay, String idloai) {
		String ma = "0";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(ketnoi);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from id where " + idloai + "=N'*' order by ma_id asc");
			while (rs.next()) {
				ma = new String();
				ma = rs.getString(idlay);
				;
			}
			return ma;
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "lỗi id: " + ex.toString());
			return null;
		}
	}

	public String demtien2(String id) {
		String demtien2 = "select tb_dichvu.gia_dv from tb_hoadondv,tb_dichvu,tb_bienlai,tb_hdtp,tb_phong where tb_dichvu.ma_dv=tb_hoadondv.ma_dv and tb_hoadondv.ma_hddv=tb_bienlai.ma_hddv and tb_bienlai.ma_hd=tb_hdtp.ma_hd and tb_hdtp.ma_phong=tb_phong.ma_phong and tb_phong.ma_phong='"
				+ id + "'";
		return demtien2;
	}

	public String demTaikhoan(String taikhoan, String matkhau) {
		String where = "where taikhoan=N'" + taikhoan + "' and matkhau=N'" + matkhau + "'";
		return where;
	}

	public String demKhach(String giatri) {
		String where = "where tb_khachnhanphong.ma_phong=tb_phong.ma_phong and tb_phong.ma_phong=N'" + giatri + "'";
		return where;
	}

	public String demDichvu(String giatri) {
		String where = "where tb_hoadondv.ma_hddv=tb_bienlai.ma_hddv and tb_bienlai.ma_hd=tb_hdtp.ma_hd and tb_hdtp.ma_phong=tb_phong.ma_phong and tb_hdtp.ma_phong='"
				+ giatri + "'";
		return where;
	}

	public String itemGiaP(String giatri) {
		String select = "select DISTINCT * from tb_phong where tb_phong.ma_phong=N'" + giatri + "'";
		return select;
	}

	public String itemNgayDp(String giatri) {
		String select = "select tb_hdtp.ngay_dp from tb_hdtp,tb_phong where tb_hdtp.ma_phong=tb_phong.ma_phong and tb_phong.ma_phong=N'"
				+ giatri + "'";
		return select;
	}

	public String itemNgayDv(String giatri) {
		String select = "select DISTINCT tb_hoadondv.ngay_hddv from tb_hoadondv,tb_bienlai,tb_hdtp,tb_phong where tb_hoadondv.ma_hddv=tb_bienlai.ma_hddv and tb_bienlai.ma_hd=tb_hdtp.ma_hd and tb_hdtp.ma_phong=tb_phong.ma_phong and tb_phong.ma_phong='"
				+ giatri + "'";
		return select;
	}

	public String itemNv(String giatri) {
		String select = "select tb_nhanvien.ten_nv from tb_nhanvien,tb_hdtp,tb_phong where tb_nhanvien.ma_nv=tb_hdtp.ma_nv and tb_hdtp.ma_phong=tb_phong.ma_phong and tb_phong.ma_phong=N'"
				+ giatri + "'";
		return select;
	}

	public String itemLogin(String giatri) {
		String select = "select * from tb_login where ma_tk=N'" + giatri + "'";
		return select;
	}

	public String itemTgden(String giatri) {
		String select = "select * from tb_hdtp,tb_phong where tb_hdtp.ma_phong=tb_phong.ma_phong and tb_phong.ma_phong=N'"
				+ giatri + "'";
		return select;
	}

	public String demDong(String table, String where) {
		String soDong = "";
		int countRow = 0;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(ketnoi);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select DISTINCT * from " + table + " " + where + "");
			while (rs.next()) {
				countRow++;
			}
			soDong = "" + countRow;
			return soDong;
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Lỗi đếm" + ex.toString());
			return null;
		}
	}

	public String demTien(String select) {
		String tien = "";
		int congtien = 0;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(ketnoi);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("" + select + "");
			while (rs.next()) {
				congtien += Integer.parseInt(rs.getString("gia_dv"));
			}
			tien = String.valueOf(congtien);
			return tien;
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Cộng tiền bị lỗi");
			return null;
		}
	}

	//hàm lấy ra item của table
	public String cellTb(String col, String select) {
		String cell = "";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(ketnoi);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("" + select + "");
			while (rs.next()) {
				cell = rs.getString(col);
				;
			}
			return cell;
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Lỗi: " + ex.toString());
			return null;
		}
	}

//trả về ma_hd của tb_hdtp khi chọn 1 phòng dựa vào maphong
	public String iDhopdong(String maphong) {
		String id = "báo lỗi";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(ketnoi);
			Statement st = con.createStatement();
			ResultSet rs = st
					.executeQuery("select DISTINCT tb_hdtp.ma_hd from tb_hdtp where ma_phong=N'" + maphong + "'");
			while (rs.next()) {
				id = rs.getString("ma_hd");
				;
			}
			return id;
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Báo lỗi: " + ex.toString());
			return null;
		}
	}

//lấy ma_hd của tb_hdtp insert những dòng có tinhtrang=2 + ma_hd (tương ứng tên phòng được chọn) 
	public void themvaoBl(String ma_hd) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(ketnoi);
			Statement st = con.createStatement();
			st.executeUpdate(
					"insert into tb_bienlai select tb_hdtp.ma_hd, ma_hddv from tb_hoadondv,tb_hdtp where tb_hoadondv.tinhtrang=N'2' and tb_hdtp.ma_hd='"
							+ ma_hd + "'");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Lỗi thêm HDdv,Hd vao bien lai!" + ex.toString());
		}
	}

	public void dvThanhToanSau(String maphong) {
		// update cot tinh trang của tb_hoadondv từ 0 thành 2
		adapter.updateHDDV("2", "0");
		// lấy những cột tinhtrang=2 + ma_hd( tương ứng phòng được chọn) chèn vào
		// tb_bienlai
		themvaoBl(iDhopdong(maphong));
		// sau update cot tinhtrang của tb_hoadondv từ 2 thành 1, những cột có có
		// tinhtrang=1 được lưu lại!
		adapter.updateHDDV("1", "2");
	}
}
