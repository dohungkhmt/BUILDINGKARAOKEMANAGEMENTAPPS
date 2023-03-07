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


public class DichVu_DAO {
	String chuoikn = "jdbc:sqlserver://localhost:1433;databaseName=QLYKARAOKE;user=sa;password=123456";
	DefaultTableModel tbModel = new DefaultTableModel();
	DefaultComboBoxModel cmbModel = new DefaultComboBoxModel();
	private Connection con;
	
	//load dữ liệu dịch vụ lên bảng
	public DefaultTableModel loadDichVu() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(chuoikn);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from tb_dichvu order by ma_dv asc");
			String[] tieudecot = { "ID", "Tên dịch vụ", "Đơn giá" };
			ArrayList<String[]> dulieubang = new ArrayList<String[]>();
			while (rs.next()) {
				String[] dong = new String[3];
				dong[0] = rs.getString("ma_dv");
				dong[1] = rs.getString("ten_dv");
				dong[2] = rs.getString("gia_dv");
				dulieubang.add(dong);
			}
			//
			String[][] data = new String[dulieubang.size()][3];
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
	
	//load dịch vụ phòng
	public DefaultTableModel loadDvPhongTheoTenPhong(String tenphong) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(chuoikn);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(
					"select tb_dichvu.ma_dv,tb_dichvu.ten_dv,tb_dichvu.gia_dv,tb_hoadondv.gio_hddv,tb_hoadondv.ngay_hddv,tb_phong.ten_phong from tb_dichvu,tb_hoadondv,tb_hdtp,tb_bienlai,tb_phong where tb_dichvu.ma_dv=tb_hoadondv.ma_dv and tb_hoadondv.ma_hddv=tb_bienlai.ma_hddv and tb_bienlai.ma_hd=tb_hdtp.ma_hd and tb_hdtp.ma_phong=tb_phong.ma_phong and tb_phong.ten_phong=N'"
							+ tenphong + "' order by ma_dv asc");
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

	//load hoá đơn dịch vụ lên form
	public DefaultTableModel loadHdDv() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(chuoikn);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(
					"select ma_hddv,tb_dichvu.ten_dv,gio_hddv,ngay_hddv from tb_hoadondv, tb_dichvu where tb_dichvu.ma_dv=tb_hoadondv.ma_dv and tinhtrang=0 order by ma_hddv asc");
			String[] tieudecot = { "Tên dịch vụ", "Thời gian đặt", "Ngày đặt" };
			ArrayList<String[]> dulieubang = new ArrayList<String[]>();
			while (rs.next()) {
				String[] dong = new String[4];
				//dong[0] = rs.getString("ma_hddv");
				dong[0] = rs.getString("ten_dv");
				dong[1] = rs.getString("gio_hddv");
				dong[2] = rs.getString("ngay_hddv");
				dulieubang.add(dong);
			}
			//
			String[][] data = new String[dulieubang.size()][4];
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
	
	
	
	// load tên nhân viên
	public DefaultComboBoxModel loadNhanvienCmb() {
		cmbModel = new DefaultComboBoxModel();
		cmbModel.addElement("--Chọn nv thanh toán--");
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(chuoikn);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select ten_nv from tb_nhanvien");
			while (rs.next()) {
				cmbModel.addElement(rs.getString("ten_nv"));
			}
			return cmbModel;
		} catch (Exception e) {
			return null;
		}
	}
	
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
	
	//thêm dịch vụ 

	public void ThemDichVu(String ten_dv, String gia_dv) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(chuoikn);
			Statement st = con.createStatement();
			int i = st.executeUpdate("insert into tb_dichvu  values(N'" + Id("tb_dichvu", "ma_dv") + "',N'" + ten_dv
					+ "',N'" + gia_dv + "')");
			if (i > 0)
				JOptionPane.showMessageDialog(null, "Đã thêm dịch vụ");
		} catch (Exception ex) {
			// ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Nhập sai tiền, phải nhập số /1000đ");
		}
	}
	
	public void ThemHoaDonDv(String ma_dv,String giodv,String ngaydv)
	{
			try{
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				con = DriverManager.getConnection(chuoikn);
				Statement st = con.createStatement();
			    st.executeUpdate("insert into tb_hoadondv  values(N'"+Id("tb_hoadondv","ma_hddv")+"',N'"+ma_dv+"',N'" +giodv+"',N'" +ngaydv+"',N'0')");
			}catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, "Chưa chọn dịch vụ !");
			}
	}
	//load thông tin dịch vụ
	public DefaultTableModel loadTtDv() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(chuoikn);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(
					"select tb_dichvu.ten_dv,gio_hddv,tb_dichvu.gia_dv from tb_hoadondv, tb_dichvu where tb_dichvu.ma_dv=tb_hoadondv.ma_dv and tinhtrang=0 order by ma_hddv asc");
			String[] tieudecot = { "Dịch vụ", "Đơn Giá", "Thời gian đặt" };
			ArrayList<String[]> dulieubang = new ArrayList<String[]>();
			while (rs.next()) {
				String[] dong = new String[3];
				dong[0] = rs.getString("ten_dv");
				dong[1] = rs.getString("gia_dv");
				dong[2] = rs.getString("gio_hddv");
				dulieubang.add(dong);
			}
			//
			String[][] data = new String[dulieubang.size()][3];
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
	public void themvaoBl(String ma_hd) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(chuoikn);
			Statement st = con.createStatement();
			st.executeUpdate(
					"insert into tb_bienlai select tb_hdtp.ma_hd, ma_hddv from tb_hoadondv,tb_hdtp where tb_hoadondv.tinhtrang=N'2' and tb_hdtp.ma_hd='"
							+ ma_hd + "'");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Lỗi thêm HDdv,Hd vao bien lai!" + ex.toString());
		}
	}
	public void xoaBl(String ma_hd) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(chuoikn);
			Statement st = con.createStatement();
			st.executeUpdate(
					"delete from tb_bienlai  where tb_hoadondv.tinhtrang=N'2' and tb_hdtp.ma_hd='"
							+ ma_hd + "'");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Lỗi thêm HDdv,Hd vao bien lai!" + ex.toString());
		}
	}
	public String iDhopdong(String tenphong) {
		String id = "báo lỗi";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(chuoikn);
			Statement st = con.createStatement();
			ResultSet rs = st
					.executeQuery("select DISTINCT tb_hdtp.ma_hd from tb_hdtp,tb_phong where tb_hdtp.ma_phong=tb_phong.ma_phong and tb_phong.ten_phong=N'" + tenphong + "'");
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
	public String iDPhong(String tenphong) {
		String id = "báo lỗi";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(chuoikn);
			Statement st = con.createStatement();
			ResultSet rs = st
					.executeQuery("select DISTINCT tb_hdtp.ma_hd from tb_hdtp,tb_phong where tb_hdtp.ma_phong=tb_phong.ma_phong and tb.ten_phong=N'" + tenphong + "'");
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
	
	public void huyDV(String maphong) {
		// update cot tinh trang của tb_hoadondv từ 0 thành 2
		updateHDDV("2", "0");
		// lấy những cột tinhtrang=2 + ma_hd( tương ứng phòng được chọn) chèn vào
		// tb_bienlai
		themvaoBl(iDhopdong(maphong));
		// sau update cot tinhtrang của tb_hoadondv từ 2 thành 1, những cột có có
		// tinhtrang=1 được lưu lại!
		updateHDDV("1", "2");
	}
	
	public void dvThanhToanSau(String maphong) {
		// update cot tinh trang của tb_hoadondv từ 0 thành 2
		updateHDDV("2", "0");
		// lấy những cột tinhtrang=2 + ma_hd( tương ứng phòng được chọn) chèn vào
		// tb_bienlai
		themvaoBl(iDhopdong(maphong));
		// sau update cot tinhtrang của tb_hoadondv từ 2 thành 1, những cột có có
		// tinhtrang=1 được lưu lại!
		updateHDDV("1", "2");
	}
	
	
	//xoá hoá đơn dịch vụ
	public void XoaHddv() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connection = DriverManager.getConnection(chuoikn);
			PreparedStatement st = connection.prepareStatement("delete from tb_hoadondv where tinhtrang='0'");
			st.executeUpdate();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Lỗi khi xóa!" + e.toString());
		}
	}

}
