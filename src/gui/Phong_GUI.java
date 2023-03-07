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
import dao.Phong_DAO;
import dao.TruyvanKaraoke;

import java.util.*;


public class Phong_GUI extends javax.swing.JFrame {
	private JPanel contentPane;
	private JTextField txtTen;
	private JTextField txtGia;
	private JTextField txtNote;
	private JTable tblPhong;
	private JComboBox cmbLp;
	private JComboBox comboBox;
	private String chonloaiphong;
	private String id;
	String namephong = "";

	/**
	 * Creates new form viewPhong
	 */
	public Phong_GUI() {
		Phong_DAO phong_DAO = new Phong_DAO();
//		initComponents();
		setTitle("Phòng Hát");
		setResizable(false);
		setLocationRelativeTo(null);
		setSize(958, 410);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				tblPhong.setModel(phong_DAO.timKiemPhong(6));
			}
		});
		try {
			setIconImage(ImageIO.read(new File("image/karaoke.png")));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Thông tin phòng:",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 51)));
		panel_1.setBounds(10, 53, 359, 307);
		panel_1.setBackground(Color.CYAN);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JButton btnXoa = new JButton();
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String quyen = phong_DAO.cellTb("tinhtrang", phong_DAO.itemLogin(phong_DAO.Id("id_1", "id")));
				if (!quyen.equals("1")) {
					JOptionPane.showMessageDialog(null, "Bạn không được sử dụng chức năng này!");
					return;
				}
				// trương hợp phòng đang có người dùng mà chúng ta xóa thì không được
				if (namephong.equals("Phòng đã đặt")) {
					JOptionPane.showMessageDialog(null, "xin lỗi phòng này đang dùng");
					return;
				}
				if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa!", "Kiểm tra lại",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					if (id.equals(""))
						JOptionPane.showMessageDialog(null, "Chưa chọn phòng!");
					else {
						phong_DAO.XoaId("tb_phong", "ma_phong", id);
						tblPhong.setModel(phong_DAO.timKiemPhong(6));
					}
				}
			}
		});
		btnXoa.setBounds(188, 204, 120, 42);
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnXoa.setIcon(new ImageIcon("image/signal.png"));
		btnXoa.setText("Xóa");
		btnXoa.setBackground(new Color(255, 246, 143));
		panel_1.add(btnXoa);

		JButton btnThem = new JButton();
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String quyen = phong_DAO.cellTb("tinhtrang", phong_DAO.itemLogin(phong_DAO.Id("id_1", "id")));
				if (!quyen.equals("1"))
					JOptionPane.showMessageDialog(null, "Bạn không được sử dụng chức năng này!");
				else if (txtTen.getText().toString().equals("") || chonloaiphong.equals("--selected--")
						|| txtGia.getText().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "Kiểm tra lại!");
				} else {
					phong_DAO.ThemPhong(txtTen.getText().toString(), chonloaiphong, txtGia.getText().toString(),
							txtNote.getText().toString());
					// load lên tất cả dữ liêu phòng
					tblPhong.setModel(phong_DAO.timKiemPhong(6));
					// setText lên label thông báo!
				}
			}
		});
		btnThem.setBounds(31, 204, 120, 42);
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnThem.setIcon(new ImageIcon("image/plus.png"));
		btnThem.setText("Thêm");
		btnThem.setBackground(new Color(255, 246, 143));
		panel_1.add(btnThem);

		txtTen = new JTextField();
		txtTen.setBounds(131, 28, 177, 30);
		panel_1.add(txtTen);
		txtTen.setColumns(10);

		JLabel lblNewLabel = new JLabel("Tên Phòng :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 34, 76, 16);
		panel_1.add(lblNewLabel);

		cmbLp = new JComboBox();
		cmbLp.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (cmbLp.getSelectedItem().toString().equals("phòng Thường"))
					chonloaiphong = "1";
				else if (cmbLp.getSelectedItem().toString().equals("phòng Vip"))
					chonloaiphong = "2";
			}
		});
		cmbLp.setModel(new DefaultComboBoxModel(new String[] { "--Selected--", "Phòng Thường", "Phòng Vip" }));
		cmbLp.setBounds(131, 150, 177, 30);
		panel_1.add(cmbLp);

		JLabel lblNewLabel_2 = new JLabel("Loại phòng :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(10, 116, 99, 16);
		panel_1.add(lblNewLabel_2);

		txtNote = new JTextField();
		txtNote.setBounds(131, 69, 177, 30);
		panel_1.add(txtNote);
		txtNote.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Chú thích :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setBounds(10, 78, 76, 14);
		panel_1.add(lblNewLabel_4);

		txtGia = new JTextField();
		txtGia.setBounds(131, 110, 177, 30);
		panel_1.add(txtGia);
		txtGia.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Giá Phòng/1h :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 156, 99, 16);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_3 = new JLabel("DANH SÁCH PHÒNG HÁT");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_3.setForeground(new Color(210, 105, 30));
		lblNewLabel_3.setBounds(10, 11, 260, 31);
		contentPane.add(lblNewLabel_3);

		JButton btnRf = new JButton("Refresh");
		btnRf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tblPhong.setModel(phong_DAO.timKiemPhong(6));
			}
		});
		btnRf.setBounds(782, 5, 150, 43);
		btnRf.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRf.setIcon(new ImageIcon("image/refresh-arrow.png"));
		btnRf.setText("CẬP NHẬT");
		btnRf.setBackground(new Color(255, 246, 143));
		contentPane.add(btnRf);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(379, 116, 553, 244);
		contentPane.add(panel_2);
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Danh sách phòng:",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 51)));
		panel_2.setBackground(Color.CYAN);
		panel_2.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 16, 537, 217);
		panel_2.add(scrollPane);

		tblPhong = new JTable();
		tblPhong.setRowHeight(40);
		tblPhong.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tblPhong.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 16));
		tblPhong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				namephong = tblPhong.getValueAt(tblPhong.getSelectedRow(), 4).toString();
				id = tblPhong.getModel().getValueAt(tblPhong.getSelectedRow(), 0).toString();
				txtTen.setText(tblPhong.getModel().getValueAt(tblPhong.getSelectedRow(), 1).toString());
			}
		});
		scrollPane.setViewportView(tblPhong);

		JPanel panel = new JPanel();
		panel.setBounds(379, 53, 182, 54);
		contentPane.add(panel);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tìm kiếm phòng:",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 51)));
		panel.setBackground(Color.CYAN);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				// load tất cả
				if (comboBox.getSelectedIndex() == 0)
					tblPhong.setModel(phong_DAO.timKiemPhong(6));
				// load phong tình trạng =0
				else if (comboBox.getSelectedIndex() == 1)
					tblPhong.setModel(phong_DAO.timKiemPhong(0));
				// load phòng tìnhtrang=1
				else if (comboBox.getSelectedIndex() == 2)
					tblPhong.setModel(phong_DAO.timKiemPhong(1));
				// load phòng loại phòng = phòng thường
				else if (comboBox.getSelectedIndex() == 3)
					tblPhong.setModel(phong_DAO.timKiemPhong(2));
				// load phòng loại phòng = phòng vip
				else if (comboBox.getSelectedIndex() == 4)
					tblPhong.setModel(phong_DAO.timKiemPhong(3));

			}
		});
		comboBox.setBounds(0, 0, 170, 33);
		panel.add(comboBox);

		comboBox.setModel(new DefaultComboBoxModel(
				new String[] { "---Phòng---", "Phòng trống", "Phòng đã đặt", "Phòng Thường", "Phòng vip" }));
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
//// TODO add your handling code here:
//	}// GEN-LAST:event_formWindowClosing
//
//	/**
//	 * @param args the command line arguments
//	 */
//	public static void main(String args[]) {
//		/* Set the Nimbus look and feel */
//		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
//		// (optional) ">
//		/*
//		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
//		 * look and feel. For details see
//		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
//		 */
//		try {
//			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//				if ("Nimbus".equals(info.getName())) {
//					javax.swing.UIManager.setLookAndFeel(info.getClassName());
//					break;
//				}
//			}
//		} catch (ClassNotFoundException ex) {
//			java.util.logging.Logger.getLogger(Phong_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//		} catch (InstantiationException ex) {
//			java.util.logging.Logger.getLogger(Phong_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//		} catch (IllegalAccessException ex) {
//			java.util.logging.Logger.getLogger(Phong_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
//			java.util.logging.Logger.getLogger(Phong_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//		}
//		// </editor-fold>
//		// </editor-fold>
//
//		/* Create and display the form */
//		java.awt.EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				new Phong_GUI().setVisible(true);
//			}
//		});
//	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	// End of variables declaration//GEN-END:variables
}
