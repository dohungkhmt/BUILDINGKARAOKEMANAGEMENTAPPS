package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class NguoiDung_DAO {
	
	String chuoikn = "jdbc:sqlserver://localhost:1433;databaseName=QLYKARAOKE;user=sa;password=123456";
	DefaultTableModel tbModel = new DefaultTableModel();
	DefaultComboBoxModel cmbModel = new DefaultComboBoxModel();
	private Connection con;

	public DefaultTableModel loadUser() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(chuoikn);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from tb_login order by ma_tk asc");
			String[] tieudecot = { "Id", "Tên", "Tên Tk", "Mật khẩu!" };
			ArrayList<String[]> dulieubang = new ArrayList<String[]>();
			int j = 0;
			while (rs.next()) {
				j++;
				if (j == 1)
					;
				else {
					String[] dong = new String[4];
					dong[0] = rs.getString("ma_tk");
					dong[1] = rs.getString("ten_tk");
					dong[2] = rs.getString("taikhoan");
					dong[3] = rs.getString("matkhau");
					dulieubang.add(dong);
				}
			}
			//
			String[][] data = new String[dulieubang.size()][4];
			for (int i = 0; i < dulieubang.size(); i++) {
				data[i] = dulieubang.get(i);
			}
			tbModel.setDataVector(data, tieudecot);
			return tbModel;
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Lỗi load user" + ex.toString());
			return null;
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

	public void ThemUser(String taikhoan, String matkhau, String hoten) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(chuoikn);
			Statement st = con.createStatement();
			int i = st.executeUpdate("insert into tb_login  values(N'" + Id("tb_login", "ma_tk") + "',N'" + taikhoan
					+ "',N'" + matkhau + "',N'" + hoten + "',N'0')");
			if (i > 0)
				JOptionPane.showMessageDialog(null, "Đã thêm người dùng");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "lỗi thêm người dùng!" + ex.toString());
		}
	}
}
