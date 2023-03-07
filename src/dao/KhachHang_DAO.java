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

public class KhachHang_DAO {
	String chuoikn = "jdbc:sqlserver://localhost:1433;databaseName=QLYKARAOKE;user=sa;password=123456";
	DefaultTableModel tbModel = new DefaultTableModel();
	DefaultComboBoxModel cmbModel = new DefaultComboBoxModel();
	private Connection con;

	// load tất cả khách hàng
	public DefaultTableModel loadAllKh() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(chuoikn);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(
					"select DISTINCT tb_khachhang.ma_kh,ten_kh,cmnd_kh,case when gioitinh_kh=1 then N'Nam' else N'Nữ' end as gioitinh_kh,sdt_kh,tb_phong.ten_phong from tb_khachhang,tb_khachnhanphong,tb_phong where tb_khachhang.ma_kh=tb_khachnhanphong.ma_kh and tb_phong.ma_phong=tb_khachnhanphong.ma_phong order by ma_kh asc");
			String[] tieudecot = { "ID", "Tên KH", "CMND", "Giới tính", "Liên lạc", "Phòng" };
			ArrayList<String[]> dulieubang = new ArrayList<String[]>();
			while (rs.next()) {
				String[] dong = new String[8];
				dong[0] = rs.getString("ma_kh");
				dong[1] = rs.getString("ten_kh");
				dong[2] = rs.getString("cmnd_kh");
				dong[3] = rs.getString("gioitinh_kh");
				dong[4] = rs.getString("sdt_kh");
				// dong[6] = rs.getString("tinhtrang");
				dong[5] = rs.getString("ten_phong");
				dulieubang.add(dong);
			}
			//
			String[][] data = new String[dulieubang.size()][8];
			for (int i = 0; i < dulieubang.size(); i++) {
				data[i] = dulieubang.get(i);
			}
			tbModel.setDataVector(data, tieudecot);
			return tbModel;
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Lỗi khi load Kh " + ex.toString());
			return null;
		}
	}

	// load khách hàng chờ
	public DefaultTableModel loadKhachhang() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(chuoikn);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select ma_kh,ten_kh,case when gioitinh_kh=1 then N'Nam' else N'Nữ' end as gioitinh_kh from tb_khachhang where tinhtrang='0' order by ma_kh asc");
			String[] tieudecot = { "Mã KH", "Tên KH", "Giới tính" };
			ArrayList<String[]> dulieubang = new ArrayList<String[]>();
			while (rs.next()) {
				String[] dong = new String[3];
				dong[0] = rs.getString("ma_kh");
				dong[1] = rs.getString("ten_kh");
				dong[2] = rs.getString("gioitinh_kh");
				dulieubang.add(dong);
			}
			String[][] data = new String[dulieubang.size()][3];
			for (int i = 0; i < dulieubang.size(); i++) {
				data[i] = dulieubang.get(i);
			}
			tbModel.setDataVector(data, tieudecot);
			return tbModel;
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Báo lỗi: " + ex.toString());
			return null;
		}
	}
	
	// load danh sách phòng lên combobox
	public DefaultComboBoxModel loadPhongList() {
		cmbModel = new DefaultComboBoxModel();
		cmbModel.addElement("Chọn phòng:");
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(chuoikn);
			Statement st = con.createStatement();
			ResultSet rs = st
					.executeQuery("select ten_phong from tb_phong, tb_hdtp where tb_hdtp.ma_phong=tb_phong.ma_phong");
			while (rs.next()) {
				cmbModel.addElement(rs.getString("ten_phong"));
			}
			return cmbModel;
		} catch (Exception e) {
			return null;
		}
	}

	// cập nhật thông tin khách hàng
	public void updateAllKh(String ma_kh, String ten, String cmnd, String gioitinh, String sdt) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connection = DriverManager.getConnection(chuoikn);
			PreparedStatement st = connection.prepareStatement("update tb_khachhang set ten_kh=N'" + ten
					+ "', cmnd_kh=N'" + cmnd + "', quoctich_kh=N'VIỆT NAM', gioitinh_kh=N'" + gioitinh
					+ "', tuoi_kh=N'18', sdt_kh=N'" + sdt + "' where ma_kh='" + ma_kh + "'");
			st.executeUpdate();
			// JOptionPane.showMessageDialog(null, "Ðã trả phòng!","Thông báo:
			// ",JOptionPane.YES_NO_CANCEL_OPTION);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Lỗi khi update khách hàng!" + e.toString());
		}
	}

	// xoá id
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
	
	// truy vấn id
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
	
	
	
	//Thêm khách hàng
	public void ThemKhachHang(String ten_kh, String cmnd_kh, String gioitinh_kh, String sdt_kh, String tt) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(chuoikn);
			Statement st = con.createStatement();
			int i = st.executeUpdate(
					"insert into tb_khachhang  values(N'" + Id("tb_khachhang", "ma_kh") + "',N'" + ten_kh + "',N'"
							+ cmnd_kh + "',N'VIỆT NAM',N'" + gioitinh_kh + "',N'18',N'" + sdt_kh + "',N'" + tt + "')");
			if (i > 0)
				JOptionPane.showMessageDialog(null, "Đã thêm khách hàng");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Lỗi khi thêm khách hàng!" + ex.toString());
		}
	}
	
	public void updateKhachHang() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connection = DriverManager.getConnection(chuoikn);
			PreparedStatement st = connection
					.prepareStatement("update tb_khachhang set tinhtrang='1' where tinhtrang='0'");
			st.executeUpdate();
			// JOptionPane.showMessageDialog(null, "Ðã trả phòng!","Thông báo:
			// ",JOptionPane.YES_NO_CANCEL_OPTION);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Lỗi khi update khách hàng!" + e.toString());
		}
	}


}
