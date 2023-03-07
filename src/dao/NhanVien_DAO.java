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

public class NhanVien_DAO {
	String chuoikn = "jdbc:sqlserver://localhost:1433;databaseName=QLYKARAOKE;user=sa;password=123456";
	String ketnoi = "jdbc:sqlserver://localhost:1433;databaseName=QLYKARAOKE;user=sa;password=123456";
	DefaultTableModel tbModel = new DefaultTableModel();
	DefaultComboBoxModel cmbModel = new DefaultComboBoxModel();
	private Connection con;

	// LOAD NHÂN VIEN
	public DefaultTableModel loadNhanVien() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(chuoikn);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from tb_nhanvien order by ma_nv asc");
			String[] tieudecot = { "Mã NV", "Tên NV", "Chức vụ" };
			ArrayList<String[]> dulieubang = new ArrayList<String[]>();
			while (rs.next()) {
				String[] dong = new String[3];
				dong[0] = rs.getString("ma_nv");
				dong[1] = rs.getString("ten_nv");
				dong[2] = rs.getString("chucvu_nv");
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

	public DefaultTableModel loadAllNv() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(chuoikn);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(
					"select ma_nv,ten_nv,chucvu_nv,luong_nv,namsinh_nv,case when gioitinh_nv=1 then N'Nam' else N'Nữ' end as gioitinh_nv,chuthich_nv from tb_nhanvien order by ma_nv asc");
			String[] tieudecot = { "Mã NV", "Tên NV", "Chức vụ", "Lương", "Năm sinh", "Giới tính", "Chú thích" };
			ArrayList<String[]> dulieubang = new ArrayList<String[]>();
			while (rs.next()) {
				String[] dong = new String[7];
				dong[0] = rs.getString("ma_nv");
				dong[1] = rs.getString("ten_nv");
				dong[2] = rs.getString("chucvu_nv");
				dong[3] = rs.getString("luong_nv");
				dong[4] = rs.getString("namsinh_nv");
				dong[5] = rs.getString("gioitinh_nv");
				dong[6] = rs.getString("chuthich_nv");
				dulieubang.add(dong);
			}
			String[][] data = new String[dulieubang.size()][7];
			for (int i = 0; i < dulieubang.size(); i++) {
				data[i] = dulieubang.get(i);
			}
			tbModel.setDataVector(data, tieudecot);
			return tbModel;
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Lỗi khi load Nv" + ex.toString());
			return null;
		}
	}

	// truy vấn id
	public String Id(String idlay,String idloai)
	{
		String ma="0";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(ketnoi);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from id where "+idloai+"=N'*' order by ma_id asc");
			while(rs.next()) 
				{
				ma=new String();
				ma = rs.getString(idlay);;
				}
			return ma;
			}
			catch(Exception ex){
				JOptionPane.showMessageDialog(null, "lỗi id1: " + ex.toString());
				return null;
			}
	}
	public String Id1(String tb,String ma)
	{
	    int id1=0,id2=0;
		String id = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(chuoikn);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from "+tb+"");
				while(rs.next())
				{
					id = new String();
					id = rs.getString(ma);
					id1=Integer.parseInt(id);
					if(id1>=id2)
					{
						id2=id1;
					}
				}
				id=String.valueOf(id2+1);
				return id;
			}
			catch(Exception ex){
				JOptionPane.showMessageDialog(null, "lỗi id: " + ex.toString());
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

	// thêm nhân viên
	public void ThemNhanVien(String ten_nv, String chucvu_nv, String luong_nv, String namsinh_nv, String gioitinh_nv,
			String chuthich_nv) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(chuoikn);
			Statement st = con.createStatement();
			int i = st.executeUpdate("insert into tb_nhanvien  values(N'" + Id1("tb_nhanvien", "ma_nv") + "',N'" + ten_nv
					+ "',N'" + chucvu_nv + "',N'" + luong_nv + "',N'" + namsinh_nv + "',N'" + gioitinh_nv + "',N'"
					+ chuthich_nv + "')");
			if (i > 0)
				JOptionPane.showMessageDialog(null, "Nhân viên đã được thêm");
		} catch (Exception ex) {
			// ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Lỗi khi thêm nhân viên!" + ex.toString());
		}
	}

	// hàm lấy ra item của table
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
	// lấy quyền của tài khoản để sử dụng chức năng
	public String itemLogin(String giatri)
	{
	String select="select * from tb_login where ma_tk=N'"+giatri+"'";
	return select;
	}

}
