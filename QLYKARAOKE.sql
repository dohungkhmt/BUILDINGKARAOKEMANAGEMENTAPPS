USE [master]
GO

CREATE DATABASE [QLYKARAOKE]
 GO
ALTER DATABASE [QLYKARAOKE] SET COMPATIBILITY_LEVEL = 110
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QLYKARAOKE].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QLYKARAOKE] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QLYKARAOKE] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QLYKARAOKE] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QLYKARAOKE] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QLYKARAOKE] SET ARITHABORT OFF 
GO
ALTER DATABASE [QLYKARAOKE] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [QLYKARAOKE] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QLYKARAOKE] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QLYKARAOKE] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QLYKARAOKE] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QLYKARAOKE] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QLYKARAOKE] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QLYKARAOKE] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QLYKARAOKE] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QLYKARAOKE] SET  DISABLE_BROKER 
GO
ALTER DATABASE [QLYKARAOKE] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QLYKARAOKE] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QLYKARAOKE] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QLYKARAOKE] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QLYKARAOKE] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QLYKARAOKE] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QLYKARAOKE] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QLYKARAOKE] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [QLYKARAOKE] SET  MULTI_USER 
GO
ALTER DATABASE [QLYKARAOKE] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QLYKARAOKE] SET DB_CHAINING OFF 
GO
ALTER DATABASE [QLYKARAOKE] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [QLYKARAOKE] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [QLYKARAOKE] SET DELAYED_DURABILITY = DISABLED 
GO
USE [QLYKARAOKE]
GO
/****** Object:  Table [dbo].[id]  ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[id](
	[ma_id] [int] NOT NULL,
	[id] [nvarchar](50) NULL,
	[id_1] [nvarchar](50) NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tb_bienlai]   ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tb_bienlai](
	[ma_hd] [int] NULL,
	[ma_hddv] [int] NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tb_dichvu]  ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tb_dichvu](
	[ma_dv] [int] NOT NULL,
	[ten_dv] [nvarchar](50) NULL,
	[gia_dv] [int] NOT NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tb_hdtp]   ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tb_hdtp](
	[ma_hd] [int] NULL,
	[ma_phong] [int] NOT NULL,
	[ma_nv] [int] NOT NULL,
	[ngay_dp] [nvarchar](50) NULL,
	[gio_dp] [nvarchar](50) NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tb_hoadondv]   ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tb_hoadondv](
	[ma_hddv] [int] NOT NULL,
	[ma_dv] [int] NOT NULL,
	[gio_hddv] [nvarchar](50) NULL,
	[ngay_hddv] [nvarchar](50) NULL,
	[tinhtrang] [nvarchar](50) NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tb_khachhang]  ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tb_khachhang](
	[ma_kh] [int] NOT NULL,
	[ten_kh] [nvarchar](50) NULL,
	[cmnd_kh] [nvarchar](50) NULL,
	[quoctich_kh] [nvarchar](50) NULL,
	[gioitinh_kh] [nvarchar](50) NULL,
	[tuoi_kh] [nvarchar](50) NULL,
	[sdt_kh] [nvarchar](50) NULL,
	[tinhtrang] [nvarchar](50) NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tb_khachnhanphong]  ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tb_khachnhanphong](
	[ma_kh] [int] NOT NULL,
	[ma_phong] [int] NOT NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tb_login] ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tb_login](
	[ma_tk] [int] NOT NULL,
	[taikhoan] [nvarchar](50) NULL,
	[matkhau] [nvarchar](50) NULL,
	[ten_tk] [nvarchar](50) NULL,
	[tinhtrang] [nvarchar](50) NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tb_nhanvien]   ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tb_nhanvien](
	[ma_nv] [int] NOT NULL,
	[ten_nv] [nvarchar](50) NULL,
	[chucvu_nv] [nvarchar](50) NULL,
	[luong_nv] [nvarchar](50) NULL,
	[namsinh_nv] [nvarchar](50) NULL,
	[gioitinh_nv] [nvarchar](50) NULL,
	[chuthich_nv] [nvarchar](50) NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tb_phong]  ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tb_phong](
	[ma_phong] [int] NOT NULL,
	[ten_phong] [nvarchar](50) NULL,
	[loai_phong] [nvarchar](50) NULL,
	[gia_phong] [int] NULL,
	[chuthich] [nvarchar](50) NULL,
	[tinhtrang] [int] NULL
) ON [PRIMARY]

GO
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (1, N'*', N'1')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (2, N'6', N'*')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (3, N'*', N'1')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (4, N'1', N'*')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (5, N'1', N'*')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (6, N'0', N'*')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (7, N'*', N'1')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (8, N'*', N'1')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (9, N'*', N'1')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (10, N'*', N'1')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (11, N'*', N'1')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (12, N'*', N'1')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (13, N'*', N'1')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (14, N'*', N'1')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (15, N'*', N'1')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (16, N'*', N'1')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (17, N'*', N'1')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (18, N'*', N'1')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (19, N'*', N'1')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (20, N'*', N'1')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (21, N'*', N'1')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (22, N'*', N'1')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (23, N'0', N'*')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (24, N'1', N'*')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (25, N'*', N'1')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (26, N'0', N'*')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (27, N'*', N'1')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (28, N'*', N'1')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (29, N'*', N'1')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (30, N'*', N'1')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (31, N'*', N'1')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (32, N'*', N'1')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (33, N'*', N'1')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (34, N'*', N'1')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (35, N'*', N'1')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (36, N'*', N'1')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (37, N'*', N'1')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (38, N'*', N'1')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (39, N'0', N'*')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (40, N'*', N'1')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (41, N'*', N'1')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (42, N'*', N'1')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (43, N'*', N'1')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (44, N'*', N'1')
INSERT [dbo].[id] ([ma_id], [id], [id_1]) VALUES (45, N'*', N'1')
INSERT [dbo].[tb_bienlai] ([ma_hd], [ma_hddv]) VALUES (1, 2)
INSERT [dbo].[tb_bienlai] ([ma_hd], [ma_hddv]) VALUES (1, 3)
INSERT [dbo].[tb_bienlai] ([ma_hd], [ma_hddv]) VALUES (1, 4)
INSERT [dbo].[tb_bienlai] ([ma_hd], [ma_hddv]) VALUES (1, 7)
INSERT [dbo].[tb_bienlai] ([ma_hd], [ma_hddv]) VALUES (1, 9)
INSERT [dbo].[tb_bienlai] ([ma_hd], [ma_hddv]) VALUES (1, 8)
INSERT [dbo].[tb_bienlai] ([ma_hd], [ma_hddv]) VALUES (1, 1)
INSERT [dbo].[tb_bienlai] ([ma_hd], [ma_hddv]) VALUES (1, 5)
INSERT [dbo].[tb_bienlai] ([ma_hd], [ma_hddv]) VALUES (1, 6)
INSERT [dbo].[tb_dichvu] ([ma_dv], [ten_dv], [gia_dv]) VALUES (1, N'Thức ăn vặt', 330000)
INSERT [dbo].[tb_dichvu] ([ma_dv], [ten_dv], [gia_dv]) VALUES (2, N'Đĩa trái cây', 380000)
INSERT [dbo].[tb_dichvu] ([ma_dv], [ten_dv], [gia_dv]) VALUES (3, N'Coca Cola', 120000)
INSERT [dbo].[tb_dichvu] ([ma_dv], [ten_dv], [gia_dv]) VALUES (6, N'Pepsi', 200000)
INSERT [dbo].[tb_dichvu] ([ma_dv], [ten_dv], [gia_dv]) VALUES (7, N'Red bull', 5000)
INSERT [dbo].[tb_dichvu] ([ma_dv], [ten_dv], [gia_dv]) VALUES (9, N'Bia Tiger', 30000)
INSERT [dbo].[tb_dichvu] ([ma_dv], [ten_dv], [gia_dv]) VALUES (10, N'Bia Heineken', 24000)
INSERT [dbo].[tb_dichvu] ([ma_dv], [ten_dv], [gia_dv]) VALUES (11, N'Volka', 10000)
INSERT [dbo].[tb_dichvu] ([ma_dv], [ten_dv], [gia_dv]) VALUES (12, N'Whisky', 130000)
INSERT [dbo].[tb_dichvu] ([ma_dv], [ten_dv], [gia_dv]) VALUES (13, N'Jagermeister', 200000)
INSERT [dbo].[tb_dichvu] ([ma_dv], [ten_dv], [gia_dv]) VALUES (4, N'Bóng "hihi"', 18000)
INSERT [dbo].[tb_hdtp] ([ma_hd], [ma_phong], [ma_nv], [ngay_dp], [gio_dp]) VALUES (1, 3, 2, N'10-12-2017', N'23:39')
INSERT [dbo].[tb_hoadondv] ([ma_hddv], [ma_dv], [gio_hddv], [ngay_hddv], [tinhtrang]) VALUES (2, 3, N'23:39', N'10-12-2017', N'1')
INSERT [dbo].[tb_hoadondv] ([ma_hddv], [ma_dv], [gio_hddv], [ngay_hddv], [tinhtrang]) VALUES (3, 4, N'23:39', N'10-12-2017', N'1')
INSERT [dbo].[tb_hoadondv] ([ma_hddv], [ma_dv], [gio_hddv], [ngay_hddv], [tinhtrang]) VALUES (4, 4, N'23:39', N'10-12-2017', N'1')
INSERT [dbo].[tb_hoadondv] ([ma_hddv], [ma_dv], [gio_hddv], [ngay_hddv], [tinhtrang]) VALUES (7, 9, N'23:39', N'10-12-2017', N'1')
INSERT [dbo].[tb_hoadondv] ([ma_hddv], [ma_dv], [gio_hddv], [ngay_hddv], [tinhtrang]) VALUES (9, 6, N'23:39', N'10-12-2017', N'1')
INSERT [dbo].[tb_hoadondv] ([ma_hddv], [ma_dv], [gio_hddv], [ngay_hddv], [tinhtrang]) VALUES (10, 7, N'19:25', N'14-12-2017', N'0')
INSERT [dbo].[tb_hoadondv] ([ma_hddv], [ma_dv], [gio_hddv], [ngay_hddv], [tinhtrang]) VALUES (11, 6, N'19:25', N'14-12-2017', N'0')
INSERT [dbo].[tb_hoadondv] ([ma_hddv], [ma_dv], [gio_hddv], [ngay_hddv], [tinhtrang]) VALUES (12, 6, N'19:25', N'14-12-2017', N'0')
INSERT [dbo].[tb_hoadondv] ([ma_hddv], [ma_dv], [gio_hddv], [ngay_hddv], [tinhtrang]) VALUES (13, 8, N'19:25', N'14-12-2017', N'0')
INSERT [dbo].[tb_hoadondv] ([ma_hddv], [ma_dv], [gio_hddv], [ngay_hddv], [tinhtrang]) VALUES (8, 8, N'23:39', N'10-12-2017', N'1')
INSERT [dbo].[tb_hoadondv] ([ma_hddv], [ma_dv], [gio_hddv], [ngay_hddv], [tinhtrang]) VALUES (1, 1, N'23:39', N'10-12-2017', N'1')
INSERT [dbo].[tb_hoadondv] ([ma_hddv], [ma_dv], [gio_hddv], [ngay_hddv], [tinhtrang]) VALUES (5, 4, N'23:39', N'10-12-2017', N'1')
INSERT [dbo].[tb_hoadondv] ([ma_hddv], [ma_dv], [gio_hddv], [ngay_hddv], [tinhtrang]) VALUES (6, 4, N'23:39', N'10-12-2017', N'1')
INSERT [dbo].[tb_khachhang] ([ma_kh], [ten_kh], [cmnd_kh], [quoctich_kh], [gioitinh_kh], [tuoi_kh], [sdt_kh], [tinhtrang]) VALUES (1, N'Nguyễn Minh Thuận', N'4203942', N'VIỆT NAM', N'1', N'18', N'0123456789', N'0')
INSERT [dbo].[tb_khachhang] ([ma_kh], [ten_kh], [cmnd_kh], [quoctich_kh], [gioitinh_kh], [tuoi_kh], [sdt_kh], [tinhtrang]) VALUES (2, N'Đỗ Nguyên Hùng', N'4203942', N'VIỆT NAM', N'1', N'18', N'20934820', N'0')
INSERT [dbo].[tb_khachhang] ([ma_kh], [ten_kh], [cmnd_kh], [quoctich_kh], [gioitinh_kh], [tuoi_kh], [sdt_kh], [tinhtrang]) VALUES (3, N'Lê Tấn Phúc', N'4203942', N'VIỆT NAM', N'1', N'18', N'111223344556', N'0')
INSERT [dbo].[tb_khachhang] ([ma_kh], [ten_kh], [cmnd_kh], [quoctich_kh], [gioitinh_kh], [tuoi_kh], [sdt_kh], [tinhtrang]) VALUES (4, N'Lê Trung Hiếu', N'4203942', N'VIỆT NAM', N'1', N'21', N'565656565', N'0')
INSERT [dbo].[tb_khachhang] ([ma_kh], [ten_kh], [cmnd_kh], [quoctich_kh], [gioitinh_kh], [tuoi_kh], [sdt_kh], [tinhtrang]) VALUES (5, N'Nguyến Tiến Đạt', N'4203942', N'VIỆT NAM', N'1', N'23', N'11111111', N'0')
--INSERT [dbo].[tb_khachnhanphong] ([ma_kh], [ma_phong]) VALUES (1, 3)
--INSERT [dbo].[tb_khachnhanphong] ([ma_kh], [ma_phong]) VALUES (8, 2)
INSERT [dbo].[tb_login] ([ma_tk], [taikhoan], [matkhau], [ten_tk], [tinhtrang]) VALUES (1, N'admin', N'123', N'Nguyễn Minh Thuận', N'1')
INSERT [dbo].[tb_login] ([ma_tk], [taikhoan], [matkhau], [ten_tk], [tinhtrang]) VALUES (2, N'hungdo', N'1234', N'Đỗ Nguyên Hùng', N'0')
INSERT [dbo].[tb_login] ([ma_tk], [taikhoan], [matkhau], [ten_tk], [tinhtrang]) VALUES (3, N'phucle', N'1234', N'Lê Tấn Phúc', N'0')
INSERT [dbo].[tb_nhanvien] ([ma_nv], [ten_nv], [chucvu_nv], [luong_nv], [namsinh_nv], [gioitinh_nv], [chuthich_nv]) VALUES (1, N'Nguyễn Minh Thuận', N'Chủ Quán', N'5000000', N'2001', N'1', N'Chủ quán')
INSERT [dbo].[tb_nhanvien] ([ma_nv], [ten_nv], [chucvu_nv], [luong_nv], [namsinh_nv], [gioitinh_nv], [chuthich_nv]) VALUES (2, N'Đỗ Nguyên Hùng', N'nhân viên', N'5000000', N'2001', N'1', N'Quản lý')
INSERT [dbo].[tb_nhanvien] ([ma_nv], [ten_nv], [chucvu_nv], [luong_nv], [namsinh_nv], [gioitinh_nv], [chuthich_nv]) VALUES (3, N'Lê Tấn Phúc', N'nhân viên', N'5000000', N'2001', N'1', N'Kế toán')
INSERT [dbo].[tb_nhanvien] ([ma_nv], [ten_nv], [chucvu_nv], [luong_nv], [namsinh_nv], [gioitinh_nv], [chuthich_nv]) VALUES (4, N'Nguyễn Trọng Hiếu', N'nhân viên', N'4000000', N'1996', N'1', N'')
INSERT [dbo].[tb_nhanvien] ([ma_nv], [ten_nv], [chucvu_nv], [luong_nv], [namsinh_nv], [gioitinh_nv], [chuthich_nv]) VALUES (5, N'Nguyễn Tiến Đạt', N'nhân viên', N'4500000', N'1997', N'1', N'')
INSERT [dbo].[tb_phong] ([ma_phong], [ten_phong], [loai_phong], [gia_phong], [chuthich], [tinhtrang]) VALUES (1, N'A01', N'1', 100000, N'', 0)
INSERT [dbo].[tb_phong] ([ma_phong], [ten_phong], [loai_phong], [gia_phong], [chuthich], [tinhtrang]) VALUES (2, N'A02', N'1', 100000, N'', 0)
INSERT [dbo].[tb_phong] ([ma_phong], [ten_phong], [loai_phong], [gia_phong], [chuthich], [tinhtrang]) VALUES (3, N'A03', N'1', 100000, N'', 1)
INSERT [dbo].[tb_phong] ([ma_phong], [ten_phong], [loai_phong], [gia_phong], [chuthich], [tinhtrang]) VALUES (6, N'A06', N'2', 150000, N'', 0)
INSERT [dbo].[tb_phong] ([ma_phong], [ten_phong], [loai_phong], [gia_phong], [chuthich], [tinhtrang]) VALUES (8, N'A08', N'2', 150000, N'', 0)
INSERT [dbo].[tb_phong] ([ma_phong], [ten_phong], [loai_phong], [gia_phong], [chuthich], [tinhtrang]) VALUES (9, N'A09', N'2', 150000, N'', 0)
INSERT [dbo].[tb_phong] ([ma_phong], [ten_phong], [loai_phong], [gia_phong], [chuthich], [tinhtrang]) VALUES (12, N'A012', N'2', 150000, N'', 0)
INSERT [dbo].[tb_phong] ([ma_phong], [ten_phong], [loai_phong], [gia_phong], [chuthich], [tinhtrang]) VALUES (15, N'A015', N'2', 150000, N'', 0)
INSERT [dbo].[tb_phong] ([ma_phong], [ten_phong], [loai_phong], [gia_phong], [chuthich], [tinhtrang]) VALUES (16, N'A016', N'1', 100000, N'', 0)
INSERT [dbo].[tb_phong] ([ma_phong], [ten_phong], [loai_phong], [gia_phong], [chuthich], [tinhtrang]) VALUES (18, N'A018', N'1', 100000, N'', 0)
INSERT [dbo].[tb_phong] ([ma_phong], [ten_phong], [loai_phong], [gia_phong], [chuthich], [tinhtrang]) VALUES (19, N'A019', N'1', 100000, N'', 0)
INSERT [dbo].[tb_phong] ([ma_phong], [ten_phong], [loai_phong], [gia_phong], [chuthich], [tinhtrang]) VALUES (4, N'A04', N'1', 100000, N'', 0)
INSERT [dbo].[tb_phong] ([ma_phong], [ten_phong], [loai_phong], [gia_phong], [chuthich], [tinhtrang]) VALUES (10, N'A010', N'2', 150000, N'', 0)
INSERT [dbo].[tb_phong] ([ma_phong], [ten_phong], [loai_phong], [gia_phong], [chuthich], [tinhtrang]) VALUES (5, N'A05', N'1', 100000, N'', 0)
INSERT [dbo].[tb_phong] ([ma_phong], [ten_phong], [loai_phong], [gia_phong], [chuthich], [tinhtrang]) VALUES (13, N'A013', N'2', 150000, N'', 0)
INSERT [dbo].[tb_phong] ([ma_phong], [ten_phong], [loai_phong], [gia_phong], [chuthich], [tinhtrang]) VALUES (14, N'A014', N'2', 150000, N'', 0)
INSERT [dbo].[tb_phong] ([ma_phong], [ten_phong], [loai_phong], [gia_phong], [chuthich], [tinhtrang]) VALUES (17, N'A017', N'1', 100000, N'', 0)
INSERT [dbo].[tb_phong] ([ma_phong], [ten_phong], [loai_phong], [gia_phong], [chuthich], [tinhtrang]) VALUES (7, N'A07', N'2', 150000, N'', 0)
INSERT [dbo].[tb_phong] ([ma_phong], [ten_phong], [loai_phong], [gia_phong], [chuthich], [tinhtrang]) VALUES (11, N'A011', N'2', 150000, N'', 0)
USE [master]
GO
ALTER DATABASE [QLYKARAOKE] SET  READ_WRITE 
GO
