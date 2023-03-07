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

import dao.NguoiDung_DAO;
import dao.TruyvanKaraoke;

/**
 *
 * @author thanc
 */
public class NguoiDung_GUI extends javax.swing.JFrame {
	NguoiDung_DAO nguoiDung_DAO = new  NguoiDung_DAO();
	private JPanel contentPane;
	private JTextField txtten;
	private JTextField txttaikhoan;
	private JTextField txtmatkhau;
	private JTable table;
	private String chonuser = "";
	TruyvanKaraoke adapterMd = new TruyvanKaraoke();

	/**
	 * Creates new form viewUser
	 */
	public NguoiDung_GUI() {
		NguoiDung_DAO nguoiDung_DAO = new NguoiDung_DAO();
//		initComponents();
		setTitle("Quản Lý Tài Khoản ");
		setResizable(false);
		setLocationRelativeTo(null);
		setSize(869, 272);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				table.setModel(nguoiDung_DAO.loadUser());
			}
		});
		try {
			setIconImage(ImageIO.read(new File("image/karaoke.png")));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "Qu\u1EA3n l\u00FD t\u00E0i kho\u1EA3n", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		contentPane.setBackground(Color.CYAN);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "Danh sách người dùng :", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(353, 23, 491, 209);
		panel.setBackground(Color.CYAN);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);

		table = new JTable();
		table.setRowHeight(40);
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 16));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				chonuser = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
			}
		});
		scrollPane.setViewportView(table);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.CYAN);
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 30, 333, 202);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Họ Tên:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(10, 11, 68, 20);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel = new JLabel("Tài khoản:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 53, 88, 20);
		panel_1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Mật Khẩu:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 97, 68, 14);
		panel_1.add(lblNewLabel_1);

		txtten = new JTextField();
		txtten.setBounds(88, 6, 175, 30);
		panel_1.add(txtten);
		txtten.setColumns(10);

		txttaikhoan = new JTextField();
		txttaikhoan.setBounds(88, 48, 175, 30);
		panel_1.add(txttaikhoan);
		txttaikhoan.setColumns(10);

		txtmatkhau = new JTextField();
		txtmatkhau.setBounds(88, 89, 175, 30);
		panel_1.add(txtmatkhau);
		txtmatkhau.setColumns(10);

		JButton btnthem = new JButton("Thêm");
		btnthem.setBounds(57, 149, 100, 42);
		panel_1.add(btnthem);
		btnthem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txttaikhoan.getText().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "Kiểm tra tên tài khoản");
				} else if (txtten.getText().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "Kiểm tra tên ");
				} else if (txtmatkhau.getText().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "Kiểm tra mật khẩu ");
				}

				else
					nguoiDung_DAO.ThemUser(txttaikhoan.getText(), txtmatkhau.getText(), txtten.getText());
				table.setModel(nguoiDung_DAO.loadUser());
			}
		});
		btnthem.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnthem.setIcon(new ImageIcon("image/plus.png"));
		btnthem.setText("Thêm");
		btnthem.setBackground(new Color(255, 246, 143));

		JButton btnxoa = new JButton("Xóa");
		btnxoa.setBounds(192, 149, 100, 42);
		panel_1.add(btnxoa);
		btnxoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chonuser.equals("")) {
					JOptionPane.showMessageDialog(null, "Chưa chọn người dùng");
				}

				else

				if (JOptionPane.showConfirmDialog(null,
						"Bạn có muốn xóa " + table.getModel().getValueAt(table.getSelectedRow(), 1).toString() + "",
						"Kiểm tra lại", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					adapterMd.XoaId("tb_login", "ma_tk", chonuser);
					table.setModel(nguoiDung_DAO.loadUser());
				}
			}
		});
		btnxoa.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnxoa.setIcon(new ImageIcon("image/signal.png"));
		btnxoa.setText("Xóa");
		btnxoa.setBackground(new Color(255, 246, 143));
	}
}
