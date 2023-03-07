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

import dao.HoaDonPhong_DAO;
import dao.KetnoiKaraoke;
import dao.TruyvanKaraoke;

/**
 *
 * @author thanc
 */
public class HoaDonDichVu_GUI extends javax.swing.JFrame {

	private JPanel contentPane; 
	private JTable table;
	KetnoiKaraoke adapterCtr = new KetnoiKaraoke();
	HoaDonPhong_DAO hdp_dao = new HoaDonPhong_DAO();

	/**
	 * Creates new form viewHoadon
	 */
	public HoaDonDichVu_GUI() {
//		initComponents();
		setTitle("Hóa Đơn Dịch Vụ");
		setResizable(false);
		
		try {
			addWindowListener(new WindowAdapter() { 
				@Override
				public void windowOpened(WindowEvent arg0) {
					table.setModel(hdp_dao.loadDvPhong(adapterCtr.Id("id", "id_1")));
				}
			});
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Chưa chọn phòng để xem!!");
		}
		try {
			setIconImage(ImageIO.read(new File("image/karaoke.png")));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 442, 312);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.CYAN);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setBounds(0, 0, 424, 273);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Danh sách dịch vụ:",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(1, 1, 1)));
		contentPane.add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		scrollPane.setViewportView(table);
	}

}
