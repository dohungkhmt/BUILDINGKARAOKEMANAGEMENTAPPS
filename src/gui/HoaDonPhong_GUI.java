
package gui;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.border.*;
import javax.swing.table.*;

import dao.HoaDonPhong_DAO;
import dao.KetnoiKaraoke;
import dao.Phong_DAO;
import dao.TruyvanKaraoke;


public class HoaDonPhong_GUI extends javax.swing.JFrame {

	TruyvanKaraoke adapterMd = new TruyvanKaraoke();
	KetnoiKaraoke adapterCtr = new KetnoiKaraoke();
	HoaDonPhong_DAO hdp_dao = new HoaDonPhong_DAO();
	private JPanel contentPane;
	private JTextField txttenphong;
	private JTextField txtloaiphong;
	private JTextField txtgiaphong;
	private JTextField txtngayden;
	private JTextField txtngayht;
	private JTable tblphong;
	private JTextField txtngaydv;
	private JTextField txtsldv;
	private JTextField txttiendv;
	private JTextField txttongtien;
	String idP = "0";
	private JTextField txtnhanvien;
	private JLabel lblngaytt;
	private JTextField txttonggio;
	private int tien;
	private JLabel lbltientt;
	private JTextField txtgioden;
	private JTextField txtgioht;
	
	public HoaDonPhong_GUI() {
		Phong_DAO phong_DAO = new  Phong_DAO();
		setTitle("Hóa Đơn");
		setResizable(false);
		addWindowListener(new WindowAdapter() { 
			@Override
			public void windowOpened(WindowEvent e) {
				tblphong.setModel(phong_DAO.loadPhong("1"));
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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.CYAN);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblconten = new JLabel("THANH TOÁN - HOÁ ĐƠN PHÒNG");
		lblconten.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblconten.setForeground(new Color(210, 105, 30));
		lblconten.setBounds(10, 11, 294, 35);
		contentPane.add(lblconten);
		
		JButton btnXemThongKe = new JButton();
		btnXemThongKe.setBounds(480,20, 160, 42);
		contentPane.add(btnXemThongKe);
		btnXemThongKe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					txtngayht.setText(adapterCtr.ngayHt);
					txtgiaphong.setText(adapterCtr.cellTb("gia_phong", adapterCtr.itemGiaP(idP)));
					txtngayden.setText(adapterCtr.cellTb("ngay_dp", adapterCtr.itemTgden(idP)));
					txtgioden.setText(adapterCtr.cellTb("gio_dp", adapterCtr.itemTgden(idP)));
					txtngaydv.setText(adapterCtr.cellTb("ngay_hddv", adapterCtr.itemNgayDv(idP)));
					txtnhanvien.setText(adapterCtr.cellTb("ten_nv", adapterCtr.itemNv(idP)));
					txttenphong.setText(tblphong.getModel().getValueAt(tblphong.getSelectedRow(), 1).toString());
					txtloaiphong.setText(tblphong.getModel().getValueAt(tblphong.getSelectedRow(), 2).toString());
					txttiendv.setText(adapterCtr.demTien(adapterCtr.demtien2(idP)) + " VNĐ");
					txtsldv.setText(
							adapterCtr.demDong("tb_hoadondv,tb_bienlai,tb_hdtp,tb_phong", adapterCtr.demDichvu(idP)));
					lblngaytt.setText(adapterCtr.ngayHt);
					txtgioht.setText(adapterCtr.gioHt);
					/// tính tiền khách sạn:
					String sogio = adapterCtr.demGio(adapterCtr.cellTb("ngay_dp", adapterCtr.itemTgden(idP)),
							adapterCtr.cellTb("gio_dp", adapterCtr.itemTgden(idP)), adapterCtr.ngayHt, adapterCtr.gioHt);
					txttonggio.setText(sogio);
					if (Integer.parseInt(sogio) <= 1) {
						tien = Integer.parseInt(txtgiaphong.getText().toString());
					} else
						tien = Integer.parseInt(sogio) * Integer.parseInt(txtgiaphong.getText().toString());
					txttongtien.setText(tien + " VNĐ");
					int thanhtoan = tien + Integer.parseInt(adapterCtr.demTien(adapterCtr.demtien2(idP)));
					lbltientt.setText(thanhtoan + " VNĐ");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Chưa chọn phòng để xem!!!");
				}
			}
		});
		
		btnXemThongKe.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnXemThongKe.setIcon(new ImageIcon("image/eye.png"));
		btnXemThongKe.setText("Xem thống kê");
		btnXemThongKe.setBackground(Color.ORANGE);


		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Chọn phòng:",
		TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 51)));
		panel.setBounds(671, 68, 464, 620);
		panel.setBackground(Color.CYAN);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);

		tblphong = new JTable();
		tblphong.setRowHeight(40);
		tblphong.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tblphong.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 16));
		tblphong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				idP = tblphong.getModel().getValueAt(tblphong.getSelectedRow(), 0).toString();
			}
		});
		scrollPane.setViewportView(tblphong);
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Thông tin hóa đơn Karaoke :",
		TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 51)));
		panel_1.setBounds(10, 68, 651, 620);
		panel_1.setBackground(Color.CYAN);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panelTTngay = new JPanel();
		panelTTngay.setBackground(Color.CYAN);
		panelTTngay.setBorder(new TitledBorder(null, "Thông tin ngày", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
		panelTTngay.setBounds(10, 20, 631, 120);
		panel_1.add(panelTTngay);
		panelTTngay.setLayout(null);

		JLabel lblNgayTra = new JLabel("Ngày Trả :");
		lblNgayTra.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNgayTra.setBounds(331, 32, 81, 22);
		panelTTngay.add(lblNgayTra);

		JLabel lblNgayDen = new JLabel("Ngày Đến :");
		lblNgayDen.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNgayDen.setBounds(20, 30, 81, 27);
		panelTTngay.add(lblNgayDen);

		txtngayden = new JTextField();
		txtngayden.setEditable(false);
		txtngayden.setBounds(158, 29, 151, 30);
		panelTTngay.add(txtngayden);
		txtngayden.setColumns(10);

		txtngayht = new JTextField();
		txtngayht.setEditable(false);
		txtngayht.setBounds(425, 29, 175, 30);
		panelTTngay.add(txtngayht);
		txtngayht.setColumns(10);
		
		JLabel lblGioDen = new JLabel("Giờ đến :");
		lblGioDen.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGioDen.setBounds(20, 89, 65, 14);
		panelTTngay.add(lblGioDen);

		txtgioden = new JTextField();
		txtgioden.setEditable(false);
		txtgioden.setBounds(158, 82, 151, 30);
		panelTTngay.add(txtgioden);
		txtgioden.setColumns(10);

		JLabel lblGioTra = new JLabel("Giờ Trả:");
		lblGioTra.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGioTra.setBounds(331, 89, 55, 14);
		panelTTngay.add(lblGioTra);

		txtgioht = new JTextField();
		txtgioht.setEditable(false);
		txtgioht.setBounds(425, 82, 175, 30);
		panelTTngay.add(txtgioht);
		txtgioht.setColumns(10);

		JPanel panelTTPhong = new JPanel();
		panelTTPhong.setBackground(Color.CYAN);
		panelTTPhong.setBorder(new TitledBorder(null, "Thông tin phòng", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
		panelTTPhong.setBounds(10, 137, 631, 192);
		panel_1.add(panelTTPhong);
		panelTTPhong.setLayout(null);

		txttenphong = new JTextField();
		txttenphong.setBounds(158, 37, 151, 30);
		panelTTPhong.add(txttenphong);
		txttenphong.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txttenphong.setEditable(false);
		txttenphong.setColumns(10);

		JLabel lblTenPhong = new JLabel("Tên Phòng :");
		lblTenPhong.setBounds(20, 40, 81, 22);
		panelTTPhong.add(lblTenPhong);
		lblTenPhong.setFont(new Font("Tahoma", Font.BOLD, 13));

		JLabel lblLoaiPhong = new JLabel("Loại phòng :");
		lblLoaiPhong.setBounds(331, 40, 81, 22);
		panelTTPhong.add(lblLoaiPhong);
		lblLoaiPhong.setFont(new Font("Tahoma", Font.BOLD, 13));

		txtloaiphong = new JTextField();
		txtloaiphong.setBounds(422, 37, 175, 30);
		panelTTPhong.add(txtloaiphong);
		txtloaiphong.setEditable(false);
		txtloaiphong.setColumns(10);

		JLabel lblGiaPhong = new JLabel("Giá phòng/1h :");
		lblGiaPhong.setBounds(20, 89, 114, 22);
		panelTTPhong.add(lblGiaPhong);
		lblGiaPhong.setFont(new Font("Tahoma", Font.BOLD, 13));

		txtgiaphong = new JTextField();
		txtgiaphong.setBounds(158, 86, 151, 30);
		panelTTPhong.add(txtgiaphong);
		txtgiaphong.setEditable(false);
		txtgiaphong.setColumns(10);

		JLabel lblSoGio = new JLabel("Số giờ:");
		lblSoGio.setBounds(331, 87, 58, 27);
		panelTTPhong.add(lblSoGio);
		lblSoGio.setFont(new Font("Tahoma", Font.BOLD, 13));

		txttonggio = new JTextField();
		txttonggio.setBounds(422, 81, 175, 30);
		panelTTPhong.add(txttonggio);
		txttonggio.setEditable(false);
		txttonggio.setColumns(10);

		JLabel lblTienPhong = new JLabel("Tiền Phòng:");
		lblTienPhong.setBounds(20, 138, 81, 24);
		panelTTPhong.add(lblTienPhong);
		lblTienPhong.setFont(new Font("Tahoma", Font.BOLD, 13));

		txttongtien = new JTextField();
		txttongtien.setBounds(158, 136, 151, 30);
		panelTTPhong.add(txttongtien);
		txttongtien.setEditable(false);
		txttongtien.setColumns(10);

		JPanel panelTTDichVu = new JPanel();
		panelTTDichVu.setBorder(new TitledBorder(null, "Thông tin dịch vụ", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
		panelTTDichVu.setBackground(Color.CYAN);
		panelTTDichVu.setBounds(10, 340, 631, 207);
		panel_1.add(panelTTDichVu);
		panelTTDichVu.setLayout(null);

		JLabel lblNDDV = new JLabel("Ngày đặt dịch vụ:");
		lblNDDV.setBounds(10, 34, 128, 14);
		panelTTDichVu.add(lblNDDV);
		lblNDDV.setFont(new Font("Tahoma", Font.BOLD, 13));

		txtngaydv = new JTextField();
		txtngaydv.setBounds(158, 27, 153, 30);
		panelTTDichVu.add(txtngaydv);
		txtngaydv.setEditable(false);
		txtngaydv.setColumns(10);

		txtsldv = new JTextField();
		txtsldv.setBounds(158, 151, 153, 30);
		panelTTDichVu.add(txtsldv);
		txtsldv.setEditable(false);
		txtsldv.setColumns(10);

		txttiendv = new JTextField();
		txttiendv.setBounds(158, 110, 153, 30);
		panelTTDichVu.add(txttiendv);
		txttiendv.setEditable(false);
		txttiendv.setColumns(10);

		JLabel lblSLDV = new JLabel("Số lượng dịch vụ:");
		lblSLDV.setBounds(20, 154, 128, 23);
		panelTTDichVu.add(lblSLDV);
		lblSLDV.setFont(new Font("Tahoma", Font.BOLD, 13));

		JLabel lblTTDV = new JLabel("Tổng tiền dịch vụ:");
		lblTTDV.setBounds(20, 113, 128, 22);
		panelTTDichVu.add(lblTTDV);
		lblTTDV.setFont(new Font("Tahoma", Font.BOLD, 13));

		JLabel lblNVPV = new JLabel("Nhân viên phục vụ:");
		lblNVPV.setBounds(20, 75, 139, 14);
		panelTTDichVu.add(lblNVPV);
		lblNVPV.setFont(new Font("Tahoma", Font.BOLD, 13));

		txtnhanvien = new JTextField();
		txtnhanvien.setBounds(158, 68, 153, 30);
		panelTTDichVu.add(txtnhanvien);
		txtnhanvien.setEditable(false);
		txtnhanvien.setColumns(10);
		
		JButton btnXemDV = new JButton();
		btnXemDV.setBounds(460, 93, 160, 42);
		panelTTDichVu.add(btnXemDV);
		btnXemDV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					adapterCtr.luuid(idP, "*");
					HoaDonDichVu_GUI bl = new HoaDonDichVu_GUI();
					bl.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					bl.setVisible(true);
					bl.setLocationRelativeTo(null);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Chưa chọn phòng để xem!!");
				}
			}
		});
		btnXemDV.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnXemDV.setIcon(new ImageIcon("image/fast-food.png"));
		btnXemDV.setText("Xem Dịch Vụ");
		btnXemDV.setBackground(Color.ORANGE);

		JLabel lblTongTien = new JLabel("Tổng số tiền thanh toán:");
		lblTongTien.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblTongTien.setBounds(66, 590, 242, 33);
		panel_1.add(lblTongTien);
		
		lbltientt = new JLabel("0 VNĐ");
		lbltientt.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lbltientt.setBounds(351, 589, 239, 33);
		panel_1.add(lbltientt);
		
		JButton btnTraphong = new JButton();
		btnTraphong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (adapterCtr.demDong("tb_phong", adapterCtr.demPhongDat).equals("0"))
						JOptionPane.showMessageDialog(null, "Không có phòng!");
					else if (JOptionPane.showConfirmDialog(null,
							"Bạn có muốn trả phòng "
									+ tblphong.getModel().getValueAt(tblphong.getSelectedRow(), 1).toString() + " ",
							"Kiểm tra lại", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						txttenphong.setText("");
						txtloaiphong.setText("");
						txtgiaphong.setText("");
						txtngayden.setText("");
						txtngayht.setText("");
						txtngaydv.setText("");
						txtsldv.setText("");
						txttiendv.setText("");
						txttongtien.setText("");
						txtnhanvien.setText("");
						lblngaytt.setText("");
						txttonggio.setText("");
						lbltientt.setText("");
						txtgioden.setText("");
						txtgioht.setText("");
						// update phòng được chọn tinhtrang từ 1 thành 0
						phong_DAO.updatePhong("0", idP);
						// xóa hóa đơn dv theo phòng được chọn
						hdp_dao.XoaHddvTp(idP);
						// xóa biên lai theo phòng được chọn
						hdp_dao.XoaBlTp(idP);
						// xóa khách hàng theo phòng được chọn
						hdp_dao.XoaKhTp(idP);
						// xóa khách nhạn phòng theo phòng được chọn
						hdp_dao.XoaKnpTp(idP);
						// xóa hợp đồng thuê phòng theo phòng
						hdp_dao.XoaHdTp(idP);
						JOptionPane.showMessageDialog(null, "Đã in biên lai, tất cả dữ liệu đã xóa!");
						tblphong.setModel(phong_DAO.loadPhong("1"));
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Chưa chọn phòng để thanh toán!!!");
				}
			}
		});
		btnTraphong.setBounds(470, 550, 160, 42);
		btnTraphong.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnTraphong.setIcon(new ImageIcon("image/receipt.png"));
		btnTraphong.setText("Thanh Toán");
		btnTraphong.setBackground(Color.ORANGE);
		panel_1.add(btnTraphong);

		lblngaytt = new JLabel("");
		lblngaytt.setBounds(350, 280, 143, 14);
		lblngaytt.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel_1.add(lblngaytt);
		

	}
	public static void main(String args[]) {
	java.awt.EventQueue.invokeLater(new Runnable() {
		public void run() {
			new HoaDonPhong_GUI().setVisible(true);
		}
	});
	}
}
