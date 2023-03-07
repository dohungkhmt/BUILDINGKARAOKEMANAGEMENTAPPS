package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TaiKhoan_DAO {
	String chuoikn = "jdbc:sqlserver://localhost:1433;databaseName=QLYKARAOKE;user=sa;password=123456";
	DefaultTableModel tbModel = new DefaultTableModel();
	DefaultComboBoxModel cmbModel = new DefaultComboBoxModel();
	private Connection con;
	
	public void updateMatkhau(String matkhaumoi, String ma_tk) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connection = DriverManager.getConnection(chuoikn);
			Statement st = connection.createStatement();
			int i = st.executeUpdate("update tb_login set matkhau=N'" + matkhaumoi + "' where ma_tk='" + ma_tk + "'");
			if (i > 0)
				JOptionPane.showMessageDialog(null, "Đã đổi mật khẩu!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Lỗi mật khẩu!" + e.toString());
		}
	}

	public String Id(String idlay, String idloai) {
		String ma = "0";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(chuoikn);
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

	public String itemLogin(String giatri) {
		String select = "select * from tb_login where ma_tk=N'" + giatri + "'";
		return select;
	}
	
	

	//hàm lấy ra item của table
	public String cellTb(String col, String select) {
		String cell = "";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(chuoikn);
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

}
