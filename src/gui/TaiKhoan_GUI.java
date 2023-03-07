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
import dao.TaiKhoan_DAO;
import dao.TruyvanKaraoke;

public class TaiKhoan_GUI extends javax.swing.JFrame {

	private JPanel contentPane;
	private JTextField txtTentk;
	private JTextField txtQuyen;
	private JPasswordField pfMkm;
	private JPasswordField pfXacnhan;
	private JPasswordField pfMkcu;

	/**
	 * Creates new form viewHeThong
	 */
	public TaiKhoan_GUI() {
		TaiKhoan_DAO taiKhoan_DAO = new TaiKhoan_DAO();
//		initComponents();
		setTitle("Thông tin tài khoản");
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {

				txtTentk.setText(taiKhoan_DAO.cellTb("ten_tk", taiKhoan_DAO.itemLogin(taiKhoan_DAO.Id("id_1", "id"))));
				String quyen = taiKhoan_DAO.cellTb("tinhtrang", taiKhoan_DAO.itemLogin(taiKhoan_DAO.Id("id_1", "id")));
				if (quyen.equals("1"))
					txtQuyen.setText("Quản trị viên");
				else
					txtQuyen.setText("Thành viên");
			}
		});
		try {
			setIconImage(ImageIO.read(new File("image/karaoke.png")));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 409, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.CYAN);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Thông tin tài khoản:",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 51)));
		panel_2.setBounds(10, 11, 373, 145);
		panel_2.setBackground(Color.CYAN);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel = new JLabel("Tên USER:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(15, 24, 111, 14);
		panel_2.add(lblNewLabel);

		txtTentk = new JTextField();
		txtTentk.setEditable(false);
		txtTentk.setBounds(100, 16, 233, 30);
		panel_2.add(txtTentk);
		txtTentk.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Quyền USER:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(15, 59, 111, 14);
		panel_2.add(lblNewLabel_1);

		txtQuyen = new JTextField();
		txtQuyen.setEditable(false);
		txtQuyen.setBounds(100, 51, 233, 30);
		panel_2.add(txtQuyen);
		txtQuyen.setColumns(10);

		JButton btnNewButton_1 = new JButton();
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String quyen = taiKhoan_DAO.cellTb("tinhtrang", taiKhoan_DAO.itemLogin(taiKhoan_DAO.Id("id_1", "id")));
				if (!quyen.equals("1"))
					JOptionPane.showMessageDialog(null, "Bạn không được sử dụng chức năng này!");
				else {
					NguoiDung_GUI user = new NguoiDung_GUI();
					user.setVisible(true);
					user.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					user.setLocationRelativeTo(null);
				}
			}
		});
		btnNewButton_1.setBounds(100, 92, 170, 42);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setIcon(new ImageIcon("image/add-user2.png"));
		btnNewButton_1.setText("Thêm tài khoản");
		btnNewButton_1.setBackground(new Color(255, 246, 143));

		panel_2.add(btnNewButton_1);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Đổi mật khẩu:",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 51)));
		panel.setBounds(10, 158, 373, 198);
		panel.setBackground(Color.CYAN);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnNewButton = new JButton();
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String mkc = taiKhoan_DAO.cellTb("matkhau", taiKhoan_DAO.itemLogin(taiKhoan_DAO.Id("id_1", "id")));
				if (!String.valueOf(pfMkcu.getPassword()).equals(mkc))
					JOptionPane.showMessageDialog(null, "Nhập sai mật khẩu cũ!");
				else if (String.valueOf(pfMkm.getPassword()).equals("")
						|| String.valueOf(pfXacnhan.getPassword()).equals(""))
					JOptionPane.showMessageDialog(null, "Nhập đầy đủ, nhập lại!");
				else if (!String.valueOf(pfMkm.getPassword()).equals(String.valueOf(pfXacnhan.getPassword()))) {
					JOptionPane.showMessageDialog(null, "Xác nhận sai!");
					pfMkm.setText("");
					pfXacnhan.setText("");
				} else {
					taiKhoan_DAO.updateMatkhau(String.valueOf(pfMkm.getPassword()), taiKhoan_DAO.Id("id_1", "id"));
				}
			}
		});
		btnNewButton.setBounds(106, 137, 160, 42);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setIcon(new ImageIcon("image/key.png"));
		btnNewButton.setText("Đổi mật khẩu");
		btnNewButton.setBackground(new Color(255, 246, 143));

		panel.add(btnNewButton);

		pfMkm = new JPasswordField();
		pfMkm.setBounds(133, 62, 204, 30);
		panel.add(pfMkm);

		pfXacnhan = new JPasswordField();
		pfXacnhan.setBounds(133, 96, 204, 30);
		panel.add(pfXacnhan);

		pfMkcu = new JPasswordField();
		pfMkcu.setBounds(133, 26, 204, 30);
		panel.add(pfMkcu);

		JLabel lblNewLabel_4 = new JLabel("Mật khẩu cũ:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setBounds(25, 34, 101, 14);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Mật khẩu mới:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_5.setBounds(25, 69, 101, 14);
		panel.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Xác nhận:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_6.setBounds(25, 104, 101, 14);
		panel.add(lblNewLabel_6);
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
			java.util.logging.Logger.getLogger(TaiKhoan_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(TaiKhoan_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(TaiKhoan_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(TaiKhoan_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		// </editor-fold>
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new TaiKhoan_GUI().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	// End of variables declaration//GEN-END:variables
}
