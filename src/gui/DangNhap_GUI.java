
package gui;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet.ColorAttribute;

import dao.KetnoiKaraoke;

public class DangNhap_GUI extends javax.swing.JFrame {

	/**
	 * Creates new form viewLogin
	 */
	private JPanel contentPane;
	private JTextField txttk;
	KetnoiKaraoke adapterCtr = new KetnoiKaraoke();
	private JButton btnNewButton;
	private JLabel lblNewLabel;
	private JLabel lblpass;
	private JLabel lblBackground;
	private JLabel lblNewLabel_1;
	private JPasswordField pfMk;

	public DangNhap_GUI() {
//		initComponents();
		setTitle("Đăng Nhập");
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
			}
		});
		try {
			setIconImage(ImageIO.read(new File("image/karaoke.png")));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 515, 721);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblBackground = new JLabel();
		lblBackground.setIcon(new ImageIcon("image/background-login.jpg"));
		lblBackground.setHorizontalAlignment(SwingConstants.CENTER);
		lblBackground.setForeground(Color.BLACK);
		lblBackground.setBackground(new Color(255, 153, 153));
		lblBackground.setBounds(0, 0, 501, 682);
		contentPane.add(lblBackground);
		lblBackground.setLayout(null);

		lblNewLabel = new JLabel();
		lblBackground.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon("image/username.png"));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(100, 230, 70, 43);

		lblpass = new JLabel();
		lblBackground.add(lblpass);
		lblpass.setIcon(new ImageIcon("image/password.png"));
		lblpass.setBackground(Color.WHITE);
		lblpass.setBounds(100, 310, 70, 43);

		txttk = new JTextField("admin");
		lblBackground.add(txttk);
		txttk.setBounds(191, 243, 190, 30);
		txttk.setColumns(10);

		pfMk = new JPasswordField("123");
		lblBackground.add(pfMk);
		pfMk.setBounds(191, 323, 190, 30);

		JButton btnNewButton_2 = new JButton("Đóng");
		lblBackground.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnNewButton_2.setBounds(306, 453, 100, 43);
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_2.setBackground(new Color(127, 255, 212));

		btnNewButton = new JButton();
		lblBackground.add(btnNewButton);
		btnNewButton.setBounds(102, 453, 125, 43);
		btnNewButton.setText("Đăng nhập");
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBackground(new Color(127, 255, 212));

		lblNewLabel_1 = new JLabel("ĐĂNG NHẬP");
		lblBackground.add(lblNewLabel_1);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_1.setForeground(Color.ORANGE);
		lblNewLabel_1.setBounds(177, 72, 157, 30);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txttk.getText().equals("") || String.valueOf(pfMk.getPassword()).equals("")) {
					if (txttk.getText().equals(""))
						JOptionPane.showMessageDialog(null, "vui lòng nhập tài khoản");
					else
						JOptionPane.showMessageDialog(null, "vui lòng nhập mật khẩu");

				} else if (adapterCtr
						.demDong("tb_login",
								adapterCtr.demTaikhoan(txttk.getText(), String.valueOf(pfMk.getPassword())))
						.equals("0"))
					JOptionPane.showMessageDialog(null, "Nhập sai tài khoản hoặc mật khẩu");
				else if (!adapterCtr
						.demDong("tb_login",
								adapterCtr.demTaikhoan(txttk.getText(), String.valueOf(pfMk.getPassword())))
						.equals("0")) {
					String tk = txttk.getText();
					String mk = String.valueOf(pfMk.getPassword());
					String matk = adapterCtr.ma_tk(tk, mk);
					adapterCtr.luuid("*", matk);
					DatPhong_GUI main = new DatPhong_GUI(
							"" + adapterCtr.cellTb("ten_tk", adapterCtr.itemLogin(adapterCtr.Id("id_1", "id"))));
					main.setVisible(true);
					main.setLocationRelativeTo(null);
					dispose();
				}
			}
		});
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
//			java.util.logging.Logger.getLogger(DangNhap_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//		} catch (InstantiationException ex) {
//			java.util.logging.Logger.getLogger(DangNhap_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//		} catch (IllegalAccessException ex) {
//			java.util.logging.Logger.getLogger(DangNhap_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
//			java.util.logging.Logger.getLogger(DangNhap_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//		}
//		// </editor-fold>
//		// </editor-fold>
//
//		/* Create and display the form */
//		java.awt.EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					DangNhap_GUI frame = new DangNhap_GUI();
//					frame.setVisible(true);
//					frame.setLocationRelativeTo(null);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	// End of variables declaration//GEN-END:variables
}
