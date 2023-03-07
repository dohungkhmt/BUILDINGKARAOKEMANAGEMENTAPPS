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
import java.io.File;
import java.io.IOException;

import javax.swing.border.*;

import dao.KetnoiKaraoke;
import dao.NhanVien_DAO;
import dao.TruyvanKaraoke;

public class NhanVien_GUI extends javax.swing.JFrame {

	private JPanel contentPane;
	private JTextField txtTenNv;
	private JTextField txtChucvu;
	private JTextField txtLuong;
	private JTextField txtNamsinh;
	private JTable tblNv;
	private JTextField txtChuthich;
	private JComboBox comboBox;
	String IdNv = "";
	String chonGioitinh = new String();

	/**
	 * Creates new form viewNhanVien
	 */
	public NhanVien_GUI() {
		NhanVien_DAO nhanVien_DAO = new NhanVien_DAO();
//		initComponents();
		setTitle("Nhân Viên");
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				tblNv.setModel(nhanVien_DAO.loadAllNv());
			}
		});
		try {
			setIconImage(ImageIO.read(new File("image/karaoke.png")));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1231, 484);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelDanhSach = new JPanel();
		panelDanhSach.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Danh s\u00E1ch nh\u00E2n vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 51)));
		panelDanhSach.setBounds(436, 68, 769, 371);
		panelDanhSach.setBackground(Color.CYAN);
		contentPane.add(panelDanhSach);
		panelDanhSach.setLayout(new GridLayout(1, 0, 0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panelDanhSach.add(scrollPane);

		tblNv = new JTable();
		tblNv.setRowHeight(40);
		tblNv.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tblNv.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 16));
		tblNv.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				IdNv = tblNv.getModel().getValueAt(tblNv.getSelectedRow(), 0).toString();
				txtTenNv.setText(tblNv.getModel().getValueAt(tblNv.getSelectedRow(), 1).toString());
				txtChucvu.setText(tblNv.getModel().getValueAt(tblNv.getSelectedRow(), 2).toString());
				txtLuong.setText(tblNv.getModel().getValueAt(tblNv.getSelectedRow(), 3).toString());
				txtNamsinh.setText(tblNv.getModel().getValueAt(tblNv.getSelectedRow(), 4).toString());
				txtChuthich.setText(tblNv.getModel().getValueAt(tblNv.getSelectedRow(), 6).toString());
			}
		});
		scrollPane.setViewportView(tblNv);

		JPanel panelQuanLy = new JPanel();
		panelQuanLy.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Thông tin nhân viên:",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 51)));
		panelQuanLy.setBounds(10, 68, 416, 371);
		panelQuanLy.setBackground(Color.CYAN);
		contentPane.add(panelQuanLy);
		panelQuanLy.setLayout(null);

		JLabel lblTenNV = new JLabel("Tên nhân viên:");
		lblTenNV.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTenNV.setBounds(18, 36, 103, 14);
		panelQuanLy.add(lblTenNV);

		txtTenNv = new JTextField();
		txtTenNv.setBounds(166, 29, 207, 30);
		panelQuanLy.add(txtTenNv);
		txtTenNv.setColumns(10);

		JLabel lblChucVu = new JLabel("Chức vụ :");
		lblChucVu.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblChucVu.setBounds(18, 75, 65, 14);
		panelQuanLy.add(lblChucVu);

		txtChucvu = new JTextField();
		txtChucvu.setBounds(166, 68, 207, 30);
		panelQuanLy.add(txtChucvu);
		txtChucvu.setColumns(10);

		txtLuong = new JTextField();
		txtLuong.setBounds(166, 109, 207, 30);
		panelQuanLy.add(txtLuong);
		txtLuong.setColumns(10);

		JLabel lblLuong = new JLabel("Lương :");
		lblLuong.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLuong.setBounds(18, 112, 65, 21);
		panelQuanLy.add(lblLuong);

		txtNamsinh = new JTextField();
		txtNamsinh.setBounds(166, 149, 207, 30);
		panelQuanLy.add(txtNamsinh);
		txtNamsinh.setColumns(10);

		JLabel lblNamSinh = new JLabel("Năm sinh :");
		lblNamSinh.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNamSinh.setBounds(18, 156, 90, 14);
		panelQuanLy.add(lblNamSinh);

		comboBox = new JComboBox();
		comboBox.setBounds(166, 187, 207, 30);
		panelQuanLy.add(comboBox);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (comboBox.getSelectedIndex() == 0)
					chonGioitinh = "chưa rõ";
				else if (comboBox.getSelectedIndex() == 1)
					chonGioitinh = "1";
				else if (comboBox.getSelectedIndex() == 2)
					chonGioitinh = "0";
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "--Chọn--", "Nam", "Nữ" }));

		JLabel lblGioiTinh = new JLabel("Giới tính :");
		lblGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGioiTinh.setBounds(18, 194, 65, 14);
		panelQuanLy.add(lblGioiTinh);

		txtChuthich = new JTextField();
		txtChuthich.setBounds(166, 232, 207, 30);
		panelQuanLy.add(txtChuthich);
		txtChuthich.setColumns(10);

		JLabel lblChuThich = new JLabel("Chú thích :");
		lblChuThich.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblChuThich.setBounds(18, 239, 79, 14);
		panelQuanLy.add(lblChuThich);

		JButton btnXoa = new JButton("Xóa");
		btnXoa.setBounds(231, 297, 120, 42);
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnXoa.setIcon(new ImageIcon("image/signal.png"));
		btnXoa.setText("Xóa");
		btnXoa.setBackground(Color.ORANGE);
		panelQuanLy.add(btnXoa);
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String quyen = nhanVien_DAO.cellTb("tinhtrang", nhanVien_DAO.itemLogin(nhanVien_DAO.Id("id_1", "id")));
				if (!quyen.equals("1")) {
					JOptionPane.showMessageDialog(null, "Bạn không được sử dụng chức năng này!");
					return;
				}
				if (IdNv.equals("")) {
					JOptionPane.showMessageDialog(null, "Chưa chọn nhân viên");
				} else {
					nhanVien_DAO.XoaId("tb_nhanvien", "ma_nv", IdNv);
					tblNv.setModel(nhanVien_DAO.loadAllNv());

				}
			}
		});
		JButton btnThem = new JButton("Thêm");
		btnThem.setBounds(50, 297, 120, 42);
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnThem.setIcon(new ImageIcon("image/plus.png"));
		btnThem.setText("Thêm");
		btnThem.setBackground(Color.ORANGE);
		panelQuanLy.add(btnThem);

		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String quyen = nhanVien_DAO.cellTb("tinhtrang", nhanVien_DAO.itemLogin(nhanVien_DAO.Id("id_1", "id")));
				if (!quyen.equals("1")) {
					JOptionPane.showMessageDialog(null, "Bạn không được sử dụng chức năng này!");
					return;
				}
				if (txtTenNv.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Kiểm tra lại tên");
				else if (chonGioitinh.equals("1") || chonGioitinh.equals("0")) {
					nhanVien_DAO.ThemNhanVien(txtTenNv.getText(), txtChucvu.getText(), txtLuong.getText(),
							txtNamsinh.getText(), chonGioitinh, txtChuthich.getText());
					tblNv.setModel(nhanVien_DAO.loadAllNv());

				} else {
					JOptionPane.showMessageDialog(null, "Chưa chọn giới tính");
				}
			}
		});

		JLabel lblNewLabel_8 = new JLabel("QUẢN LÝ NHÂN VIÊN");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_8.setBounds(30, 22, 294, 35);
		lblNewLabel_8.setForeground(new Color(210, 105, 30));
		contentPane.add(lblNewLabel_8);

		JButton btnRf = new JButton();
		btnRf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tblNv.setModel(nhanVien_DAO.loadAllNv());

			}
		});
		btnRf.setBounds(1055, 14, 150, 53);
		btnRf.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRf.setIcon(new ImageIcon("image/refresh-arrow.png"));
		btnRf.setText("CẬP NHẬT");
		btnRf.setBackground(Color.ORANGE);
		contentPane.add(btnRf);

	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
//	@SuppressWarnings("unchecked")
//	// <editor-fold defaultstate="collapsed" desc="Generated
//	// Code">//GEN-BEGIN:initComponents
//	private void initComponents() {
//
//		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
//		addWindowListener(new java.awt.event.WindowAdapter() {
//			public void windowClosing(java.awt.event.WindowEvent evt) {
//				formWindowClosing(evt);
//			}
//		});
//
//		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
//		getContentPane().setLayout(layout);
//		layout.setHorizontalGroup(
//				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 400, Short.MAX_VALUE));
//		layout.setVerticalGroup(
//				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 300, Short.MAX_VALUE));
//
//		pack();
//	}// </editor-fold>//GEN-END:initComponents
//
//	private void formWindowClosing(java.awt.event.WindowEvent evt) {// GEN-FIRST:event_formWindowClosing
//		DatPhong_GUI vm = new DatPhong_GUI();
//		vm.setVisible(true);
//		// TODO add your handling code here:
//	}// GEN-LAST:event_formWindowClosing

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new NhanVien_GUI().setVisible(true);
			}
		});
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(NhanVien_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(NhanVien_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(NhanVien_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(NhanVien_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		// </editor-fold>
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new NhanVien_GUI().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	// End of variables declaration//GEN-END:variables
}
