/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.border.*;

import dao.DatPhong_DAO;
import dao.KetnoiKaraoke;
import dao.KhachHang_DAO;
import dao.NhanVien_DAO;
import dao.Phong_DAO;
import dao.TruyvanKaraoke;

public class DatPhong_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTenKh;
	private JTextField txtCmnd;
	private JTextField txtLienlac;
	private JTable tblPhongtrong;
	private JTable tblKh;
	private JTable tblNvDp;
	private JTable tblDSPD;
	private JComboBox cboGioTinh;
	String chonGioitinh = "1";
	String chonPhong = new String();
	String chonNhanvien = new String();
	String chonKhachhang = "";
	int demHangTbKh;
	private JTabbedPane tabbedPane;
	private JPanel panelDatPhong;
	private JToolBar toolBar;
	private JPanel panel_10;
	private JLabel lblNewLabel_6;
	private JLabel lblPhng;
	private JLabel lblNhnVin;
	private JLabel lblDchV;

	/**
	 * Creates new form viewMain
	 */
	public DatPhong_GUI(String nv) {
		KhachHang_DAO khachHang_DAO = new KhachHang_DAO();
		Phong_DAO phong_DAO = new Phong_DAO();
		NhanVien_DAO nhanVien_DAO = new NhanVien_DAO();
		DatPhong_DAO datPhong_DAO = new DatPhong_DAO();
//		initComponents();
		setTitle("KARAOKE SUN");
		setResizable(false);
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				tblKh.setModel(khachHang_DAO.loadKhachhang());
				tblPhongtrong.setModel(phong_DAO.loadPhong("0"));
				tblNvDp.setModel(nhanVien_DAO.loadNhanVien());
				Phong_DAO phong_DAO = new Phong_DAO();
				tblDSPD.setModel(phong_DAO.loadDSDatPhong());
			}
		});
		try {
			setIconImage(ImageIO.read(new File("image/karaoke.png")));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1270, 660);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setForeground(Color.LIGHT_GRAY);
		contentPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panelDatPhong = new JPanel();
		panelDatPhong.setBounds(185, 0, 1065, 643);
		panelDatPhong.setBackground(Color.CYAN);
		panelDatPhong.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "?????t Ph??ng",
		TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 51)));
		contentPane.add(panelDatPhong);
		panelDatPhong.setLayout(null);

		JPanel panelThemKhachHang = new JPanel();
		panelThemKhachHang.setBounds(10, 45, 235, 353);
		panelDatPhong.add(panelThemKhachHang);
		panelThemKhachHang.setBackground(Color.CYAN);
		panelThemKhachHang.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Th??ng tin kh??ch h??ng:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 51)));
		panelThemKhachHang.setLayout(null);

		JLabel lblTenKhachHang = new JLabel("T??n KH :");
		lblTenKhachHang.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTenKhachHang.setBounds(10, 46, 60, 17);
		panelThemKhachHang.add(lblTenKhachHang);

		txtTenKh = new JTextField();
		txtTenKh.setBounds(74, 42, 150, 30);
		panelThemKhachHang.add(txtTenKh);
		txtTenKh.setColumns(10);

		JLabel lblCMND = new JLabel("CMND :");
		lblCMND.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCMND.setBounds(10, 147, 60, 20);
		panelThemKhachHang.add(lblCMND);

		txtCmnd = new JTextField();
		txtCmnd.setBounds(74, 143, 150, 30);
		panelThemKhachHang.add(txtCmnd);
		txtCmnd.setColumns(10);

		JLabel lblSoDienThoai = new JLabel("Li??n l???c :");
		lblSoDienThoai.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSoDienThoai.setBounds(10, 192, 60, 20);
		panelThemKhachHang.add(lblSoDienThoai);

		txtLienlac = new JTextField();
		txtLienlac.setBounds(74, 188, 150, 30);
		panelThemKhachHang.add(txtLienlac);
		txtLienlac.setColumns(10);

		cboGioTinh = new JComboBox();
		cboGioTinh.setBounds(74, 92, 150, 30);
		panelThemKhachHang.add(cboGioTinh);
		cboGioTinh.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (cboGioTinh.getSelectedIndex() == 0)
					chonGioitinh = "1";
				else if (cboGioTinh.getSelectedIndex() == 1)
					chonGioitinh = "0";

			}
		});
		cboGioTinh.setModel(new DefaultComboBoxModel(new String[] { "Nam", "N???" }));

		JLabel lblGioiTinh = new JLabel("Gi???i t??nh :");
		lblGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGioiTinh.setBounds(10, 99, 84, 14);
		panelThemKhachHang.add(lblGioiTinh);

		JButton btnThemkh = new JButton();
		btnThemkh.setBounds(19, 235, 192, 42);
		btnThemkh.setIcon(new ImageIcon("image/add-user.png"));
		btnThemkh.setText("Th??m KH");
		btnThemkh.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnThemkh.setBackground(Color.ORANGE);
		panelThemKhachHang.add(btnThemkh);

		JButton btnXoakh = new JButton();
		btnXoakh.setIcon(new ImageIcon("image/user.png"));
		btnXoakh.setText("X??a KH");
		btnXoakh.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnXoakh.setBackground(Color.ORANGE);
		btnXoakh.setBounds(19, 290, 192, 42);
		panelThemKhachHang.add(btnXoakh);
		btnXoakh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chonKhachhang.equals("")) {
					JOptionPane.showMessageDialog(null, "Ch??a ch???n kh??ch h??ng");
				} else {
					khachHang_DAO.XoaId("tb_khachhang", "ma_kh", chonKhachhang);
					tblKh.setModel(khachHang_DAO.loadKhachhang());
					chonKhachhang = "";
				}
			}
		});
		btnThemkh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtTenKh.getText().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "Ki???m tra l???i t??n");
				} else {
					khachHang_DAO.ThemKhachHang(txtTenKh.getText().toString(), txtCmnd.getText().toString(), chonGioitinh,
							txtLienlac.getText().toString(), "0");
					tblKh.setModel(khachHang_DAO.loadKhachhang());
					txtTenKh.setText("");
					txtCmnd.setText("");
					txtLienlac.setText("");
				}

			}
		});

		JPanel panelKhachHang = new JPanel();
		panelKhachHang.setBounds(245, 45, 280, 353);
		panelDatPhong.add(panelKhachHang);
		panelKhachHang.setBackground(Color.CYAN);
		panelKhachHang.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Danh s??ch kh??ch h??ng ch???:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 51)));
		panelKhachHang.setLayout(new GridLayout(1, 0, 0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		panelKhachHang.add(scrollPane_1);
		scrollPane_1.setBackground(new Color(102, 0, 102));

		tblKh = new JTable();
		tblKh.setRowHeight(40);
		tblKh.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tblKh.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 16));
		tblKh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				chonKhachhang = tblKh.getModel().getValueAt(tblKh.getSelectedRow(), 0).toString();
			}
		});
		scrollPane_1.setViewportView(tblKh);

		JPanel panelPhongTrong = new JPanel();
		panelPhongTrong.setBounds(528, 45, 280, 353);
		panelDatPhong.add(panelPhongTrong);
		panelPhongTrong.setBackground(Color.CYAN);
		panelPhongTrong.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Danh s??ch ph??ng tr???ng:",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 51)));
		panelPhongTrong.setLayout(new GridLayout(1, 0, 0, 0));

		JScrollPane scrollPane_2 = new JScrollPane();
		panelPhongTrong.add(scrollPane_2);
		tblPhongtrong = new JTable();
		tblPhongtrong.setRowHeight(40);
		tblPhongtrong.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tblPhongtrong.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 16));
		scrollPane_2.setViewportView(tblPhongtrong);

		JPanel panelNhanVien = new JPanel();
		panelNhanVien.setBounds(810, 45, 245, 353);
		panelDatPhong.add(panelNhanVien);
		panelNhanVien.setBackground(Color.CYAN);
		panelNhanVien.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Nh??n vi??n ph???c v???:",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 51)));
		panelNhanVien.setLayout(new GridLayout(1, 0, 0, 0));

		JScrollPane scrollPane_3 = new JScrollPane();
		panelNhanVien.add(scrollPane_3);

		tblNvDp = new JTable();
		tblNvDp.setRowHeight(40);
		tblNvDp.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tblNvDp.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 16));
		tblNvDp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "NV "
						+ tblNvDp.getModel().getValueAt(tblNvDp.getSelectedRow(), 1).toString() + " v???a ???????c ch???n.");
			}
		});
		scrollPane_3.setViewportView(tblNvDp);
		JPanel panelPhongDaDat = new JPanel();
		panelPhongDaDat.setBounds(10, 440, 1045, 180);
		panelDatPhong.add(panelPhongDaDat);
		panelPhongDaDat.setBackground(Color.CYAN);
		panelPhongDaDat.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Danh s??ch ph??ng ???? ?????t :", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 51)));
		panelPhongDaDat.setLayout(new GridLayout(1, 0, 0, 0));
		JScrollPane scrollPane = new JScrollPane();
		panelPhongDaDat.add(scrollPane);

		tblDSPD = new JTable();
		tblDSPD.setRowHeight(40);
		tblDSPD.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tblDSPD.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 16));
		scrollPane.setViewportView(tblDSPD);

		JButton btnLammoi = new JButton();
		btnLammoi.setForeground(Color.BLACK);
		btnLammoi.setBounds(863, 11, 142, 33);
		btnLammoi.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLammoi.setIcon(new ImageIcon("image/refresh-arrow.png"));
		btnLammoi.setText("C???P NH???T");
		btnLammoi.setBackground(Color.ORANGE);
		panelDatPhong.add(btnLammoi);

		JButton btnDatPhong = new JButton();
		btnDatPhong.setForeground(Color.BLACK);
		btnDatPhong.setBounds(528, 400, 150, 33);
		btnDatPhong.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDatPhong.setIcon(new ImageIcon("image/calendar.png"));
		btnDatPhong.setText("?????T PH??NG");
		btnDatPhong.setBackground(Color.ORANGE);
		panelDatPhong.add(btnDatPhong);

		JButton btnBochon = new JButton();
		btnBochon.setBounds(708, 400, 150, 33);
		panelDatPhong.add(btnBochon);
		btnBochon.setIcon(new ImageIcon("image/cancel.png"));
		btnBochon.setText("B??? CH???N");
		btnBochon.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBochon.setBackground(Color.ORANGE);

		btnBochon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tblPhongtrong.clearSelection();
				tblNvDp.clearSelection();
				tblKh.clearSelection();
			}
		});
		btnDatPhong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (JOptionPane.showConfirmDialog(null, "B???n c?? mu???n ?????t ph??ng n??y!", "Ki???m tra l???i",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					try {
						if (tblKh.getRowCount() == 0) {
							JOptionPane.showMessageDialog(null, "Ch??a th??m kh??ch h??ng!");
						} else {
							demHangTbKh = tblKh.getRowCount();
							chonPhong = tblPhongtrong.getModel().getValueAt(tblPhongtrong.getSelectedRow(), 0)
									.toString();
							chonNhanvien = tblNvDp.getModel().getValueAt(tblNvDp.getSelectedRow(), 0).toString();
							datPhong_DAO.ThemKhachNhanPhong(chonPhong, "0");
							datPhong_DAO.ThemHopDong(chonPhong, chonNhanvien);
							khachHang_DAO.updateKhachHang();
							phong_DAO.updatePhong("1", chonPhong);
							tblKh.setModel(khachHang_DAO.loadKhachhang());
							tblPhongtrong.setModel(phong_DAO.loadPhong("0"));
							tblDSPD.setModel(phong_DAO.loadDSDatPhong());
							// hi???n th??? l???i c??c ph??ng
							chonPhong = new String();
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Ph???i ch???n Ph??ng v?? Nh??n vi??n, ki???m tra l???i!");
					}

				}
			}
		});

		btnLammoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tblKh.setModel(khachHang_DAO.loadKhachhang());
				tblPhongtrong.setModel(phong_DAO.loadPhong("0"));
				tblNvDp.setModel(nhanVien_DAO.loadNhanVien());
				tblDSPD.setModel(phong_DAO.loadDSDatPhong());
			}
		});

		JButton btnKhachHang = new JButton();
		btnKhachHang.setForeground(Color.BLACK);
		btnKhachHang.setBounds(5, 220, 180, 40);
		btnKhachHang.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnKhachHang.setIcon(new ImageIcon("image/rating.png"));
		btnKhachHang.setText("Kh??ch H??ng");
		btnKhachHang.setBackground(Color.ORANGE);
		contentPane.add(btnKhachHang);
		btnKhachHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KhachHang_GUI kh = new KhachHang_GUI();
				kh.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				kh.setVisible(true);
				kh.setLocationRelativeTo(null);
			}
		});
		JButton btnPhongHat = new JButton();
		btnPhongHat.setForeground(Color.BLACK);
		btnPhongHat.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnPhongHat.setIcon(new ImageIcon("image/room.png"));
		btnPhongHat.setText("Ph??ng H??t");
		btnPhongHat.setBackground(Color.ORANGE);
		btnPhongHat.setBounds(5, 270, 180, 40);
		contentPane.add(btnPhongHat);
		btnPhongHat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Phong_GUI ph = new Phong_GUI();
				ph.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				ph.setVisible(true);
				ph.setLocationRelativeTo(null);
			}
		});

		JButton btnNhanVien = new JButton();
		btnNhanVien.setForeground(Color.BLACK);
		btnNhanVien.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNhanVien.setIcon(new ImageIcon("image/employee.png"));
		btnNhanVien.setText("Nh??n vi??n");
		btnNhanVien.setBackground(Color.ORANGE);
		btnNhanVien.setBounds(5, 320, 180, 40);
		contentPane.add(btnNhanVien);

		btnNhanVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NhanVien_GUI nv = new NhanVien_GUI();
				nv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				nv.setVisible(true);
				nv.setLocationRelativeTo(null);
			}
		});

		JButton btnDichVu = new JButton();
		btnDichVu.setForeground(Color.BLACK);
		btnDichVu.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDichVu.setIcon(new ImageIcon("image/fast-food.png"));
		btnDichVu.setText("D???ch V???");
		btnDichVu.setBackground(Color.ORANGE);
		btnDichVu.setBounds(5, 370, 180, 40);
		contentPane.add(btnDichVu);

		btnDichVu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DichVu_GUI dv = new DichVu_GUI();
				dv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dv.setVisible(true);
				dv.setLocationRelativeTo(null);
			}
		});
		JButton btnHoaDon = new JButton("Tr??? Ph??ng");
		btnHoaDon.setForeground(Color.BLACK);
		btnHoaDon.setBounds(5, 420, 180, 40);
		btnHoaDon.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnHoaDon.setIcon(new ImageIcon("image/invoice.png"));
		btnHoaDon.setText("Ho?? ????n");
		btnHoaDon.setBackground(Color.ORANGE);
		contentPane.add(btnHoaDon);
		btnHoaDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HoaDonPhong_GUI bl = new HoaDonPhong_GUI();
				bl.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				bl.setVisible(true);
				bl.setLocationRelativeTo(null);
			}
		});

		JButton btnTaiKhoan = new JButton("T??i Kho???n");
		btnTaiKhoan.setForeground(Color.BLACK);
		btnTaiKhoan.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnTaiKhoan.setIcon(new ImageIcon("image/account.png"));
		btnTaiKhoan.setText("T??i Kho???n");
		btnTaiKhoan.setBackground(Color.ORANGE);
		btnTaiKhoan.setBounds(5, 470, 180, 40);
		contentPane.add(btnTaiKhoan);

		JButton btnDangXuat = new JButton("????ng Xu???t");
		btnDangXuat.setBounds(5, 520, 180, 40);
		contentPane.add(btnDangXuat);
		btnDangXuat.setForeground(Color.BLACK);
		btnDangXuat.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDangXuat.setIcon(new ImageIcon("image/logout.png"));
		btnDangXuat.setText("????ng Xu???t");
		btnDangXuat.setBackground(Color.ORANGE);

		JPanel panelUser = new JPanel();
		panelUser.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelUser.setBackground(Color.WHITE);
		panelUser.setBounds(5, 0, 200, 180);
		contentPane.add(panelUser);
		JLabel picLabel = new JLabel(new ImageIcon("image/user3.png"));
		panelUser.add(picLabel);


		Label lblTenUser = new Label("T??nNV: "+nv);
		lblTenUser.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTenUser.setForeground(Color.BLACK);
		lblTenUser.setBounds(5, 190, 200, 22);
		contentPane.add(lblTenUser);

		btnDangXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "B???n c?? mu???n ????ng xu???t!", null,
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					DangNhap_GUI lg = new DangNhap_GUI();
					lg.setVisible(true);
					lg.setLocationRelativeTo(null);
					dispose();
				}
			}
		});
		btnTaiKhoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TaiKhoan_GUI ht = new TaiKhoan_GUI();
				ht.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				ht.setVisible(true);
				ht.setLocationRelativeTo(null);
			}
		});
	}
//	@SuppressWarnings("unchecked")
//	private void initComponents() {
//
//		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
//
//		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
//		getContentPane().setLayout(layout);
//		layout.setHorizontalGroup(
//				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 400, Short.MAX_VALUE));
//		layout.setVerticalGroup(
//				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 300, Short.MAX_VALUE));
//
//		pack();
//	}
//
//	/**
//	 * @param args the command line arguments
//	 */
//	public static void main(String args[]) {
//		try {
//			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//				if ("Nimbus".equals(info.getName())) {
//					javax.swing.UIManager.setLookAndFeel(info.getClassName());
//					break;
//				}
//			}
//		} catch (ClassNotFoundException ex) {
//			java.util.logging.Logger.getLogger(DatPhong_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//		} catch (InstantiationException ex) {
//			java.util.logging.Logger.getLogger(DatPhong_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//		} catch (IllegalAccessException ex) {
//			java.util.logging.Logger.getLogger(DatPhong_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
//			java.util.logging.Logger.getLogger(DatPhong_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//		}
//		java.awt.EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					DatPhong_GUI frame = new DatPhong_GUI();
//					frame.setVisible(true);
//					frame.setLocationRelativeTo(null);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
}
