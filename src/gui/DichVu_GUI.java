
package gui;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.border.*;
import javax.swing.table.*;

import dao.DichVu_DAO;
import dao.KetnoiKaraoke;
import dao.KhachHang_DAO;

public class DichVu_GUI extends javax.swing.JFrame {

	KetnoiKaraoke adapterCtr = new KetnoiKaraoke();
	DichVu_DAO dichVu_DAO  =new DichVu_DAO();
	KhachHang_DAO khachHang_dao= new KhachHang_DAO();
	private JPanel contentPane;
	private JTable tblDv;
	private JTable tblDvDat;
	private JTextField txtTenDv;
	private JTextField txtGiaDv;
	private String chonDichvu;
	private String chonDvvD;
	private JComboBox cmbPhong;
	private JTable tblHDDV;
	private String ten_phong;

	public DichVu_GUI() {
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Dịch Vụ");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				dichVu_DAO  =new DichVu_DAO();
				tblDv.setModel(dichVu_DAO.loadDichVu());
				dichVu_DAO  =new DichVu_DAO();
				tblHDDV.setModel(dichVu_DAO.loadHdDv());	
				cmbPhong.setModel(khachHang_dao.loadPhongList());
			}
		});
		try {
			setIconImage(ImageIO.read(new File("image/karaoke.png")));
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1159, 735);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDịchVu = new JLabel("DỊCH VỤ ĂN UỐNG");
		lblDịchVu.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDịchVu.setForeground(Color.ORANGE);
		lblDịchVu.setBounds(20, 11, 200, 35);
		contentPane.add(lblDịchVu);

		JPanel panelQuanLyDichVu = new JPanel();
		panelQuanLyDichVu.setBackground(Color.CYAN);
		panelQuanLyDichVu.setBorder(
				new TitledBorder(null, "Quản lý dịch vụ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelQuanLyDichVu.setBounds(10, 37, 540, 655);
		contentPane.add(panelQuanLyDichVu);
		panelQuanLyDichVu.setLayout(null);

		JPanel panelDanhSachDichVu = new JPanel();
		panelDanhSachDichVu.setBounds(10, 219, 510, 420);
		panelQuanLyDichVu.add(panelDanhSachDichVu);
		panelDanhSachDichVu.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Danh sách dịch vụ:",
		TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 51)));
		panelDanhSachDichVu.setBackground(Color.CYAN);
		panelDanhSachDichVu.setLayout(new GridLayout(1, 0, 0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panelDanhSachDichVu.add(scrollPane);

		tblDv = new JTable();
		tblDv.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}));
		tblDv.setRowHeight(40);
		tblDv.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tblDv.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 20));
		tblDv.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				chonDichvu = tblDv.getModel().getValueAt(tblDv.getSelectedRow(), 0).toString();
			}
		});
		scrollPane.setViewportView(tblDv);

		JPanel panelThemXoa = new JPanel();
		panelThemXoa.setBounds(10, 34, 294, 174);
		panelQuanLyDichVu.add(panelThemXoa);
		panelThemXoa.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Nhập dịch vụ:",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 51)));
		panelThemXoa.setBackground(Color.CYAN);
		panelThemXoa.setLayout(null);

		JLabel lblNewLabel = new JLabel("Tên Dv :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 25, 80, 28);
		panelThemXoa.add(lblNewLabel);

		txtTenDv = new JTextField();
		txtTenDv.setBounds(75, 24, 195, 30);
		panelThemXoa.add(txtTenDv);
		txtTenDv.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Giá Dv :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 63, 80, 30);
		panelThemXoa.add(lblNewLabel_1);

		txtGiaDv = new JTextField();
		txtGiaDv.setBounds(75, 63, 195, 30);
		panelThemXoa.add(txtGiaDv);
		txtGiaDv.setColumns(10);

		JButton btnThemDV = new JButton("Thêm Dv");
		btnThemDV.setBounds(10, 104, 120, 42);
		panelThemXoa.add(btnThemDV);
		btnThemDV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txtTenDv.getText().equals("") || txtGiaDv.getText().equals("")) {
					if (txtTenDv.getText().equals(""))
						JOptionPane.showMessageDialog(null, "vui lòng nhập tên dịch vụ");
					else
						JOptionPane.showMessageDialog(null, "vui lòng nhập giá dịch vụ");

				} else if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn thêm!", "Kiểm tra lại",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					dichVu_DAO.ThemDichVu(txtTenDv.getText().toString(), txtGiaDv.getText().toString());
					dichVu_DAO  =new DichVu_DAO();
					tblDv.setModel(dichVu_DAO.loadDichVu());
				}
			}
		});
		btnThemDV.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnThemDV.setIcon(new ImageIcon("image/plus.png"));
		btnThemDV.setText("Thêm");
		btnThemDV.setBackground(Color.ORANGE);

		JButton btnXoaDV = new JButton("Xóa Dv");
		btnXoaDV.setBounds(150, 104, 120, 42);
		panelThemXoa.add(btnXoaDV);
		btnXoaDV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				JOptionPane.showConfirmDialog(null, "Kiểm tra lại các mặt hàng chưa thanh toán trc khi xóa!",
						"Cẩn thận!", dialogButton);
				if (dialogButton == JOptionPane.YES_OPTION) {
					dichVu_DAO.XoaId("tb_dichvu", "ma_dv", chonDichvu);
					tblDv.setModel(dichVu_DAO.loadDichVu());
				}
			}
		});
		btnXoaDV.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnXoaDV.setIcon(new ImageIcon("image/signal.png"));
		btnXoaDV.setText("Xóa");
		btnXoaDV.setBackground(Color.ORANGE);

		JButton btnDatDV = new JButton();
		btnDatDV.setBounds(359, 55, 165, 42);
		panelQuanLyDichVu.add(btnDatDV);
		btnDatDV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dichVu_DAO  =new DichVu_DAO();
				dichVu_DAO.ThemHoaDonDv(chonDichvu, adapterCtr.gioHt, adapterCtr.ngayHt);
				dichVu_DAO  =new DichVu_DAO();
				tblHDDV.setModel(dichVu_DAO.loadHdDv());
			}
		});
		btnDatDV.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDatDV.setIcon(new ImageIcon("image/menu.png"));
		btnDatDV.setText("Đặt Dịch vụ");
		btnDatDV.setBackground(Color.ORANGE);

		JButton btnHuyDV = new JButton();
		btnHuyDV.setBounds(359, 122, 165, 42);
		panelQuanLyDichVu.add(btnHuyDV);
		btnHuyDV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dichVu_DAO.XoaId("tb_bienlai", "ma_hddv", chonDvvD);
				dichVu_DAO  =new DichVu_DAO();
				tblHDDV.setModel(dichVu_DAO.loadHdDv());
				
			}
		});
		btnHuyDV.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnHuyDV.setIcon(new ImageIcon("image/cancel2.png"));
		btnHuyDV.setText("Hủy Dịch vụ");
		btnHuyDV.setBackground(Color.ORANGE);

		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null, "Quản lí đặt dịch vụ",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.setBackground(Color.CYAN);
		panel_6.setBounds(560, 37, 575, 655);
		contentPane.add(panel_6);
		panel_6.setLayout(null);
		
		JLabel lblPhong=  new JLabel("Chọn phòng:");
		lblPhong.setBounds(20, 310, 100, 23);
		panel_6.add(lblPhong);
		cmbPhong = new JComboBox<>();
		cmbPhong.setBounds(120, 310, 150, 23);
		panel_6.add(cmbPhong);
		cmbPhong.addItemListener(new ItemListener() {
			

			public void itemStateChanged(ItemEvent arg0) {
				if (cmbPhong.getSelectedIndex() == 0)
				{
					dichVu_DAO  =new DichVu_DAO();
					tblHDDV.setModel(dichVu_DAO.loadHdDv());	
					cmbPhong.setModel(khachHang_dao.loadPhongList());
				}
				else 
				tblDvDat.setModel(dichVu_DAO.loadDvPhongTheoTenPhong(cmbPhong.getSelectedItem().toString()));
				dichVu_DAO  =new DichVu_DAO();
				tblHDDV.setModel(dichVu_DAO.loadHdDv());	
				ten_phong= cmbPhong.getSelectedItem().toString();
			}
		});
		

		JPanel panelDichVuDat = new JPanel();
		panelDichVuDat.setBounds(10, 340, 550, 300);
		panel_6.add(panelDichVuDat);
		panelDichVuDat.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Danh sách dịch vụ đã thêm vào hóa đơn:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 51)));
		panelDichVuDat.setBackground(Color.CYAN);
		panelDichVuDat.setLayout(new GridLayout(1, 0, 0, 0));

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setEnabled(false);
		panelDichVuDat.add(scrollPane_2);

		tblDvDat = new JTable();
		tblDvDat.setRowSelectionAllowed(false);
		tblDvDat.setRowHeight(40);
		tblDvDat.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tblDvDat.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 20));
		scrollPane_2.setViewportView(tblDvDat);
		
		JPanel panelHDDV = new JPanel();
		panelHDDV.setBounds(10, 30, 550, 240);
		panel_6.add(panelHDDV);
		panelHDDV.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Danh sách dịch vụ đang đặt( chưa thêm vào hóa đơn):", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 51)));
		panelHDDV.setBackground(Color.CYAN);
		panelHDDV.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setEnabled(false);
		panelHDDV.add(scrollPane_3);

		tblHDDV = new JTable();
		tblHDDV.setRowSelectionAllowed(false);
		tblHDDV.setRowHeight(40);
		tblHDDV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tblHDDV.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 20));
		tblHDDV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				chonDvvD = tblHDDV.getModel().getValueAt(tblHDDV.getSelectedRow(), 0).toString();
			}
		});
		scrollPane_3.setViewportView(tblHDDV);
		
		
		
		JButton xacNhan = new JButton("Xác Nhận Thêm");
		xacNhan.setBounds(200, 275, 200, 30);
		panel_6.add(xacNhan);
		xacNhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ten_phong==null) {
					JOptionPane.showMessageDialog(null,"Chưa chọn phòng muốn đặt dịch vụ" );
				}
				else {
					dichVu_DAO.dvThanhToanSau(ten_phong);
					dichVu_DAO  =new DichVu_DAO();
					tblHDDV.setModel(dichVu_DAO.loadHdDv());
					
				}
				}
		});
		xacNhan.setFont(new Font("Tahoma", Font.BOLD, 13));
		xacNhan.setIcon(new ImageIcon("image/income.png"));
		xacNhan.setBackground(Color.ORANGE);
		
		


	}

	
	public static void main(String args[]) {
		
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new DichVu_GUI().setVisible(true);
			}
		});
	}
}
