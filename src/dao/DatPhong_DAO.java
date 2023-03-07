package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class DatPhong_DAO {
	String ketnoi = "jdbc:sqlserver://localhost:1433;databaseName=QLYKARAOKE;user=sa;password=123456";
	DefaultTableModel tbModel = new DefaultTableModel(); 
	DefaultComboBoxModel cmbModel = new DefaultComboBoxModel();
	private Connection con;
	DateFormat ngay = new SimpleDateFormat("dd-MM-yyyy");
	DateFormat gio = new SimpleDateFormat("HH:mm");
	Calendar cal = Calendar.getInstance();
	
	// Thêm khách chờ nhận phòng
	public void ThemKhachNhanPhong(String ma_phong, String tt) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(ketnoi);
			Statement st = con.createStatement();
			st.executeUpdate(
					"insert into tb_khachnhanphong select ma_kh,ma_phong from tb_phong,tb_khachhang where tb_khachhang.tinhtrang=N'"
							+ tt + "' and tb_phong.ma_phong='" + ma_phong + "'");
		} catch (Exception ex) {

			JOptionPane.showMessageDialog(null, "Lỗi khi thêm khách hàng nhận phòng " + ex.toString());
		}
	}
	
	public String Id(String tb, String ma) {
		int id1 = 0, id2 = 0;
		String id = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(ketnoi);
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
	
	//thêm hợp đồng đặt phòng 
	public void ThemHopDong(String ma_phong, String ma_nv) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(ketnoi);
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

	
}
