/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;


public class TruyvanKaraoke {
	String chuoikn = "jdbc:sqlserver://localhost:1433;databaseName=QLYKARAOKE;user=sa;password=123456";
	DefaultTableModel tbModel = new DefaultTableModel();
	DefaultComboBoxModel cmbModel = new DefaultComboBoxModel();
	private Connection con;
	DateFormat ngay = new SimpleDateFormat("dd-MM-yyyy");
	DateFormat gio = new SimpleDateFormat("HH:mm");
	Calendar cal = Calendar.getInstance();

	public String Id(String tb, String ma) {
		int id1 = 0, id2 = 0;
		String id = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(chuoikn);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from " + tb + "");
			while (rs.next()) {
				id = new String();
				id = rs.getString(ma);
				id1 = Integer.parseInt(id);
				if (id1 >= id2) {
					id2 = id1;
				}
			}
			id = String.valueOf(id2 + 1);
			return id;
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "lỗi id: " + ex.toString());
			return null;
		}
	}

	public void ThemHopDong(String ma_phong, String ma_nv) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(chuoikn);
			Statement st = con.createStatement();
			int i = st.executeUpdate("insert into tb_hdtp  values(N'" + Id("tb_hdtp", "ma_hd") + "',N'" + ma_phong
					+ "',N'" + ma_nv + "',N'" + ngay.format(cal.getTime()) + "',N'" + gio.format(cal.getTime()) + "')");
			if (i > 0)
				JOptionPane.showMessageDialog(null, "Phòng đã được đặt");
		} catch (Exception ex) {
			// ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Lỗi khi thêm hợp đồng " + ex.toString());
		}
	}

	public void ThemKhachNhanPhong(String ma_phong, String tt) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(chuoikn);
			Statement st = con.createStatement();
			st.executeUpdate(
					"insert into tb_khachnhanphong select ma_kh,ma_phong from tb_phong,tb_khachhang where tb_khachhang.tinhtrang=N'"
							+ tt + "' and tb_phong.ma_phong='" + ma_phong + "'");
		} catch (Exception ex) {

			JOptionPane.showMessageDialog(null, "Lỗi khi thêm khách hàng nhận phòng " + ex.toString());
		}
	}

	public void ThemHoaDonDv(String ma_dv, String giodv, String ngaydv) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(chuoikn);
			Statement st = con.createStatement();
			st.executeUpdate("insert into tb_hoadondv  values(N'" + Id("tb_hoadondv", "ma_hddv") + "',N'" + ma_dv
					+ "',N'" + giodv + "',N'" + ngaydv + "',N'0')");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Chưa chọn dịch vụ !");
		}
	}

	public void ThemBienLai(String ma_hopdong, String ma_hddv) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(chuoikn);
			Statement st = con.createStatement();
			int i = st.executeUpdate("insert into tb_bienlai  values(N'" + Id("tb_bienlai", "ma_bl") + "',N'"
					+ ma_hopdong + "',N'" + ma_hddv + "')");
			if (i > 0)
				JOptionPane.showMessageDialog(null, "Biên lai đã được thêm");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Lỗi khi thêm biên lai!" + ex.toString());
		}
	}

	public void updateMaKhto0(String makh) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connection = DriverManager.getConnection(chuoikn);
			PreparedStatement st = connection.prepareStatement(
					"update tb_khachhang set tinhtrang='0' where tinhtrang='1' and ma_kh=N'" + makh + "'");
			st.executeUpdate();
			// JOptionPane.showMessageDialog(null, "Ðã trả phòng!","Thông báo:
			// ",JOptionPane.YES_NO_CANCEL_OPTION);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Lỗi khi update khách hàng!" + e.toString());
		}
	}
	public void updateHDDV(String mot, String ko) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connection = DriverManager.getConnection(chuoikn);
			PreparedStatement st = connection
					.prepareStatement("update tb_hoadondv set tinhtrang=N'" + mot + "' where tinhtrang=N'" + ko + "'");
			st.executeUpdate();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Lỗi khi update HDDV!" + e.toString());
		}
	} 

	public void updateHopDongInHDon(String ma_hd) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connection = DriverManager.getConnection(chuoikn);
			PreparedStatement st = connection
					.prepareStatement("update tb_hoadondv set ma_hd=N'" + ma_hd + "' where tinhtrang=N'1'");
			st.executeUpdate();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Lỗi khi update dịch vụ!" + e.toString());
		}
	}

	// trả phòng 3 xóa khách hàng
	public void XoaKhTp(String maphong) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connection = DriverManager.getConnection(chuoikn);
			PreparedStatement st = connection.prepareStatement(
					"delete from tb_khachhang where ma_kh in (select tb_khachhang.ma_kh from tb_khachhang,tb_khachnhanphong,tb_phong where tb_khachhang.ma_kh=tb_khachnhanphong.ma_kh and tb_khachnhanphong.ma_phong=tb_phong.ma_phong and tb_phong.ma_phong=N'"
							+ maphong + "')");
			st.executeUpdate();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Lỗi khi xóa. XoaKhTp!" + e.toString());
		}
	}

	// trả phòng 4 xóa khachnhanphong
	public void XoaKnpTp(String maphong) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connection = DriverManager.getConnection(chuoikn);
			PreparedStatement st = connection.prepareStatement(
					"delete from tb_khachnhanphong where ma_kh in (select tb_khachnhanphong.ma_kh from tb_khachnhanphong,tb_phong where tb_khachnhanphong.ma_phong=tb_phong.ma_phong and tb_phong.ma_phong=N'"
							+ maphong + "')");
			st.executeUpdate();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Lỗi khi xóa. XoaKnpTp!" + e.toString());
		}
	}

	// trả phòng 1
	public void XoaHddvTp(String maphong) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connection = DriverManager.getConnection(chuoikn);
			PreparedStatement st = connection.prepareStatement(
					"delete from tb_hoadondv where ma_hddv in (select tb_bienlai.ma_hddv from tb_bienlai,tb_hdtp,tb_phong where tb_bienlai.ma_hd=tb_hdtp.ma_hd and tb_hdtp.ma_phong=tb_phong.ma_phong and tb_phong.ma_phong='"
							+ maphong + "')");
			st.executeUpdate();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Lỗi khi xóa. XoaHddvTp!" + e.toString());
		}
	}

	// trả phòng 2 xóa biên lai
	public void XoaBlTp(String maphong) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connection = DriverManager.getConnection(chuoikn);
			PreparedStatement st = connection.prepareStatement(
					"delete from tb_bienlai where ma_hddv in (select tb_bienlai.ma_hddv from tb_bienlai,tb_hdtp,tb_phong where tb_bienlai.ma_hd=tb_hdtp.ma_hd and tb_hdtp.ma_phong=tb_phong.ma_phong and tb_phong.ma_phong='"
							+ maphong + "')");
			st.executeUpdate();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "không có thông tin để xóa");
		}
	}

	// trả phòng 5 xóa hdtp
	public void XoaHdTp(String maphong) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connection = DriverManager.getConnection(chuoikn);
			PreparedStatement st = connection.prepareStatement(
					"delete from tb_hdtp where ma_phong in (select tb_hdtp.ma_phong from tb_hdtp, tb_phong where tb_hdtp.ma_phong=tb_phong.ma_phong and tb_phong.ma_phong=N'"
							+ maphong + "')");
			st.executeUpdate();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Lỗi khi xóa. XoaBlTp!" + e.toString());
		}
	}

	public void XoaId(String table, String id1, String id2) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connection = DriverManager.getConnection(chuoikn);
			PreparedStatement st = connection
					.prepareStatement("delete from " + table + " where " + id1 + "=N'" + id2 + "'");
			st.executeUpdate();
			JOptionPane.showMessageDialog(null, "Ðã xóa", "Thông báo: ", JOptionPane.YES_NO_CANCEL_OPTION);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Không có gì để xóa");
		}
	}


	public DefaultTableModel loadDvPhong(String maphong) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(chuoikn);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(
					"select tb_dichvu.ma_dv,tb_dichvu.ten_dv,tb_dichvu.gia_dv,tb_hoadondv.gio_hddv,tb_hoadondv.ngay_hddv,tb_phong.ten_phong from tb_dichvu,tb_hoadondv,tb_hdtp,tb_bienlai,tb_phong where tb_dichvu.ma_dv=tb_hoadondv.ma_dv and tb_hoadondv.ma_hddv=tb_bienlai.ma_hddv and tb_bienlai.ma_hd=tb_hdtp.ma_hd and tb_phong.ma_phong=tb_hdtp.ma_phong and tb_phong.ma_phong=N'"
							+ maphong + "' order by ma_dv asc");
			String[] tieudecot = { "Tên dịch vụ", "Đơn giá", "Thời gian đặt", "Ngày đặt", "Tên phòng" };
			ArrayList<String[]> dulieubang = new ArrayList<String[]>();
			while (rs.next()) {
				String[] dong = new String[5];
				dong[0] = rs.getString("ten_dv");
				dong[1] = rs.getString("gia_dv");
				dong[2] = rs.getString("gio_hddv");
				dong[3] = rs.getString("ngay_hddv");
				dong[4] = rs.getString("ten_phong");
				dulieubang.add(dong);
			}
			//
			String[][] data = new String[dulieubang.size()][5];
			for (int i = 0; i < dulieubang.size(); i++) {
				data[i] = dulieubang.get(i);
			}
			tbModel.setDataVector(data, tieudecot);
			return tbModel;
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Lỗi khi load dich vụ! " + ex.toString());
			return null;
		}
	}

	public DefaultComboBoxModel loadQuoctich() {
		cmbModel = new DefaultComboBoxModel();
		cmbModel.addElement("chọn quốc tịch");
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(chuoikn);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select DISTINCT quoctich_kh from tb_khachhang");
			while (rs.next()) {
				cmbModel.addElement(rs.getString("quoctich_kh"));
			}
			return cmbModel;
		} catch (Exception e) {
			return null;
		}
	}



	public DefaultComboBoxModel loadUserList() {
		cmbModel = new DefaultComboBoxModel();
		cmbModel.addElement("chọn User:");
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(chuoikn);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select ten_tk from tb_login");
			while (rs.next()) {
				cmbModel.addElement(rs.getString("ten_tk"));
			}
			return cmbModel;
		} catch (Exception e) {
			return null;
		}
	}

}
