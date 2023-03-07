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

public class Phong_DAO {
	String chuoikn = "jdbc:sqlserver://localhost:1433;databaseName=QLYKARAOKE;user=sa;password=123456";
	DefaultTableModel tbModel = new DefaultTableModel();
	DefaultComboBoxModel cmbModel = new DefaultComboBoxModel();
	private Connection con;

	// load phòng theo tình trạng phòng
	public DefaultTableModel loadPhong(String tt) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(chuoikn);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(
					"select ma_phong,ten_phong,case loai_phong when 1 then N'Phòng thường' else N'Phòng Vip' end as loai_phong from tb_phong where tinhtrang=N'"
							+ tt + "' order by ma_phong asc");
			String[] tieudecot = { "Mã Phòng", "Tên Phòng", "Loại Phòng" };
			ArrayList<String[]> dulieubang = new ArrayList<String[]>();
			while (rs.next()) {
				String[] dong = new String[3];
				dong[0] = rs.getString("ma_phong");
				dong[1] = rs.getString("ten_phong");
				dong[2] = rs.getString("loai_phong");
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

	// load danh sách các phòng đã đặt
	public DefaultTableModel loadDSDatPhong() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(chuoikn);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(
					"select ten_phong,loai_phong,gia_phong,tb_hdtp.ngay_dp,tb_hdtp.gio_dp from tb_phong,tb_hdtp where tb_phong.tinhtrang='1' and tb_phong.ma_phong=tb_hdtp.ma_phong order by ma_nv asc");// load
																																																			// du
																																																			// lieu
																																																			// len
																																																			// JTable

			String[] tieudecot = { "Tên Phòng", "Loại Phòng", "Giá Phòng", "Ngày Đặt Phòng", "Giờ Đặt Phòng" };
			ArrayList<String[]> dulieubang = new ArrayList<String[]>();
			while (rs.next()) {
				String[] dong = new String[5];
				dong[0] = rs.getString("ten_phong");
				dong[1] = rs.getString("loai_phong");
				dong[2] = rs.getString("gia_phong");
				dong[3] = rs.getString("ngay_dp");
				dong[4] = rs.getString("gio_dp");
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
			JOptionPane.showMessageDialog(null, "Lỗi khi load phòng! " + ex.toString());
			return null;
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

	// tìm kiếm phòng
	public DefaultTableModel loadTimPhong(String phong) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(chuoikn);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(
					"select DISTINCT tb_khachhang.ma_kh,ten_kh,cmnd_kh,quoctich_kh,case when gioitinh_kh=1 then N'Nam' else N'Nữ' end as gioitinh_kh,tuoi_kh,sdt_kh,tb_phong.ten_phong from tb_khachhang,tb_khachnhanphong,tb_phong where tb_khachhang.ma_kh=tb_khachnhanphong.ma_kh and tb_phong.ma_phong=tb_khachnhanphong.ma_phong and ten_phong=N'"
							+ phong + "' order by ma_kh asc");
			String[] tieudecot = { "ID", "Tên KH", "CMND", "Quốc tịch", "Giới tính", "Tuổi", "Liên lạc", "Phòng" };
			ArrayList<String[]> dulieubang = new ArrayList<String[]>();
			while (rs.next()) {
				String[] dong = new String[8];
				dong[0] = rs.getString("ma_kh");
				dong[1] = rs.getString("ten_kh");
				dong[2] = rs.getString("cmnd_kh");
				dong[3] = rs.getString("quoctich_kh");
				dong[4] = rs.getString("gioitinh_kh");
				dong[5] = rs.getString("tuoi_kh");
				dong[6] = rs.getString("sdt_kh");
				// dong[6] = rs.getString("tinhtrang");
				dong[7] = rs.getString("ten_phong");
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
			JOptionPane.showMessageDialog(null, "Báo l?i: " + ex.toString());
			return null;
		}
	}

	// update tình trạng phòng sau khi đặt phòng
	public void updatePhong(String tt, String chonPhong) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connection = DriverManager.getConnection(chuoikn);
			PreparedStatement st = connection
					.prepareStatement("update tb_phong set tinhtrang='" + tt + "' where ma_phong=" + chonPhong + "");
			st.executeUpdate();
			// JOptionPane.showMessageDialog(null, "Ðã trả phòng!","Thông báo:
			// ",JOptionPane.YES_NO_CANCEL_OPTION);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Lỗi khi update phòng!" + e.toString());
		}
	}

	public void ThemPhong(String ten_phong, String loai_phong, String gia_phong, String chuthich) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(chuoikn);
			Statement st = con.createStatement();
			int i = st.executeUpdate("insert into tb_phong  values(N'" + Id("tb_phong", "ma_phong") + "',N'" + ten_phong
					+ "',N'" + loai_phong + "',N'" + gia_phong + "',N'" + chuthich + "',N'0')");
			// if(i>0&&tinhtrang==1) JOptionPane.showMessageDialog(null, "Phòng đã được
			// đặt");
			if (i > 0)
				JOptionPane.showMessageDialog(null, "Đã thêm phòng");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Lỗi khi thêm khách hàng!" + ex.toString());
		}
	}

	// tìm kiếm phòng
	// nếu i=1 thì load danh sách phòng đặt else load danh sách phòng trống else
	// load tất cả
	public DefaultTableModel timKiemPhong(int j) {
		DefaultTableModel tbModel = new DefaultTableModel();
		String where = "";
		if (j == 0)
			where = "where tinhtrang=N'0'";
		else if (j == 1)
			where = "where tinhtrang=N'1'";
		else if (j == 2)
			where = "where loai_phong=N'1'";
		else if (j == 3)
			where = "where loai_phong=N'2'";
		else
			where = "";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(chuoikn);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(
					"select ma_phong,ten_phong,case loai_phong when 1 then N'phòng thường' else N'Phòng Vip' end as loai_phong,gia_phong,chuthich,case when tinhtrang=1 then N'Phòng đã đặt' else N'Phòng trống' end as tinhtrang from tb_phong order by ma_phong asc");// "+where+"load
																																																																		// du
																																																																		// lieu
																																																																		// len
																																																																		// JTable

			String[] tieudecot = { "Mã Phòng", "Tên Phòng", "Loại Phòng", "Giá Phòng", "Tình trạng", "Chú thích" };
			ArrayList<String[]> dulieubang = new ArrayList<String[]>();
			while (rs.next()) {
				String[] dong = new String[6];
				dong[0] = rs.getString("ma_phong");
				dong[1] = rs.getString("ten_phong");
				dong[2] = rs.getString("loai_phong");
				dong[3] = rs.getString("gia_phong");
				dong[4] = rs.getString("tinhtrang");
				dong[5] = rs.getString("chuthich");
				dulieubang.add(dong);
			}
			//
			String[][] data = new String[dulieubang.size()][6];
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
	
	//lay quyền của tài khoản 
	public String itemLogin(String giatri) {
		String select = "select * from tb_login where ma_tk=N'" + giatri + "'";
		return select;
	}

}
