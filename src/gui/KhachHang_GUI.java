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
import dao.KhachHang_DAO;
import dao.Phong_DAO;
import dao.TruyvanKaraoke;

import java.util.*;

public class KhachHang_GUI extends javax.swing.JFrame {

	private JPanel contentPane;
	private JTextField txtTen;
	private JTextField txtCmnd;
	private JTextField txtLienlac;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel txtlienlac;
	private JTable tblKh;
	private JComboBox cmbGioitinh;
	private String chonGioitinh = "1";
	private String chonPhong = "";
	private String idKh = "";
	private JLabel lblNewLabel_5;
	private JPanel panel_3;
	private JPanel panel_4;
	private JTable tblTimKh;
	private JScrollPane scrollPane_2;
	private JComboBox cmbPhong;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_10;

	/**
	 * Creates new form viewKhachHang
	 */
	public KhachHang_GUI() {
		KhachHang_DAO khachHang_DAO = new KhachHang_DAO();
		Phong_DAO phong_DAO = new Phong_DAO();
//		initComponents();
		setTitle("Khách Hàng");
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				tblKh.setModel(khachHang_DAO.loadAllKh());
				cmbPhong.setModel(khachHang_DAO.loadPhongList());
			}
		});
		try {
			setIconImage(ImageIO.read(new File("image/karaoke.png")));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 411);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setForeground(new Color(83, 134, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Danh sách khách hàng:",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 51)));
		panel.setBounds(410, 120, 644, 250);
		panel.setBackground(Color.CYAN);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);

		tblKh = new JTable();
		tblKh.setRowHeight(40);
		tblKh.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tblKh.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 16));
		tblKh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				idKh = tblKh.getModel().getValueAt(tblKh.getSelectedRow(), 0).toString();
				txtTen.setText(tblKh.getModel().getValueAt(tblKh.getSelectedRow(), 1).toString());
				txtCmnd.setText(tblKh.getModel().getValueAt(tblKh.getSelectedRow(), 2).toString());
				if (tblKh.getModel().getValueAt(tblKh.getSelectedRow(), 3).toString().equals("1"))
					chonGioitinh = "1";
				else
					chonGioitinh = "0";
				txtLienlac.setText(tblKh.getModel().getValueAt(tblKh.getSelectedRow(), 4).toString());
			}
		});
		scrollPane.setViewportView(tblKh);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Thông tin khách hàng:",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 51)));
		panel_1.setBounds(10, 64, 390, 306);
		panel_1.setBackground(Color.CYAN);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JButton btnSua = new JButton();
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				khachHang_DAO.updateAllKh(idKh, txtTen.getText(), txtCmnd.getText(), chonGioitinh,
						txtLienlac.getText());
				tblKh.setModel(khachHang_DAO.loadAllKh());
			}
		});
		btnSua.setBounds(53, 230, 127, 42);
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSua.setIcon(new ImageIcon("image/settings.png"));
		btnSua.setText("Cập nhật");
		btnSua.setBackground(Color.ORANGE);
		panel_1.add(btnSua);

		JButton btnXoa = new JButton();
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa!", "Kiểm tra lại",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					if (idKh.equals(""))
						JOptionPane.showMessageDialog(null, "Chưa chọn KH!");
					else {
						khachHang_DAO.XoaId("tb_khachhang", "ma_kh", idKh);
						tblKh.setModel(khachHang_DAO.loadAllKh());
					}
				}
			}
		});
		btnXoa.setBounds(214, 230, 120, 42);
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnXoa.setIcon(new ImageIcon("image/signal.png"));
		btnXoa.setText("Xóa");
		btnXoa.setBackground(Color.ORANGE);
		panel_1.add(btnXoa);

		lblNewLabel = new JLabel("Tên khách hàng:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 45, 120, 20);
		panel_1.add(lblNewLabel);

		txtTen = new JTextField();
		txtTen.setBounds(140, 41, 150, 30);
		panel_1.add(txtTen);
		txtTen.setColumns(10);

		lblNewLabel_1 = new JLabel("Căn cước:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 87, 85, 14);
		panel_1.add(lblNewLabel_1);

		txtCmnd = new JTextField();
		txtCmnd.setBounds(140, 82, 150, 30);
		panel_1.add(txtCmnd);
		txtCmnd.setColumns(10);

		txtlienlac = new JLabel("Số điện thoại:");
		txtlienlac.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtlienlac.setBounds(10, 129, 120, 14);

		panel_1.add(txtlienlac);
		txtLienlac = new JTextField();
		txtLienlac.setBounds(140, 123, 150, 30);
		panel_1.add(txtLienlac);
		txtLienlac.setColumns(10);

		lblNewLabel_3 = new JLabel("Giới Tính:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(10, 171, 85, 14);
		panel_1.add(lblNewLabel_3);

		cmbGioitinh = new JComboBox();
		cmbGioitinh.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (cmbGioitinh.getSelectedIndex() == 0)
					chonGioitinh = "1";
				else if (cmbGioitinh.getSelectedIndex() == 1)
					chonGioitinh = "0";
			}
		});
		cmbGioitinh.setModel(new DefaultComboBoxModel(new String[] { "Nam", "Nữ" }));
		cmbGioitinh.setBounds(140, 164, 150, 30);
		panel_1.add(cmbGioitinh);

		lblNewLabel_10 = new JLabel("DANH SÁCH KHÁCH HÀNG");
		lblNewLabel_10.setForeground(new Color(210, 105, 30));
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_10.setBounds(10, 11, 294, 35);
		contentPane.add(lblNewLabel_10);

		JButton btnRf = new JButton("refresh");
		btnRf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tblKh.setModel(khachHang_DAO.loadAllKh());
				cmbPhong.setModel(khachHang_DAO.loadPhongList());

			}
		});

		btnRf.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRf.setIcon(new ImageIcon("image/refresh-arrow.png"));
		btnRf.setText("CẬP NHẬT");
		btnRf.setBackground(Color.ORANGE);
		btnRf.setBounds(904, 11, 150, 43);
		contentPane.add(btnRf);

		JPanel panel_3_1 = new JPanel();
		panel_3_1.setBounds(410, 65, 170, 56);
		contentPane.add(panel_3_1);
		panel_3_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tìm khách theo phòng:",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 51)));
		panel_3_1.setBackground(Color.CYAN);
		panel_3_1.setLayout(new GridLayout(1, 0, 0, 0));

		cmbPhong = new JComboBox();
		cmbPhong.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (cmbPhong.getSelectedIndex() == 0)
					tblKh.setModel(khachHang_DAO.loadAllKh());
				else
					tblKh.setModel(phong_DAO.loadTimPhong(cmbPhong.getSelectedItem().toString()));
			}
		});
		cmbPhong.setBounds(10, 290, 148, 20);
		panel_3_1.add(cmbPhong);
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
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
		// (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
		 * look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(KhachHang_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(KhachHang_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(KhachHang_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(KhachHang_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		// </editor-fold>
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new KhachHang_GUI().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	// End of variables declaration//GEN-END:variables
}
