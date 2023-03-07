package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class HoaDonPhong_DAO {
	String chuoikn = "jdbc:sqlserver://localhost:1433;databaseName=QLYKARAOKE;user=sa;password=123456";
	DefaultTableModel tbModel = new DefaultTableModel(); 
	DefaultComboBoxModel cmbModel = new DefaultComboBoxModel();
	private Connection con;
	
	
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
}
