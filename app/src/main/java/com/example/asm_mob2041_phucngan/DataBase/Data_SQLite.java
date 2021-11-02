package com.example.asm_mob2041_phucngan.DataBase;

public class Data_SQLite {
    public static final String INSERT_THU_THU = "Insert into ThuThu(maTT, hoTen,matKhau) values " +
            "('admin','Nguyen Admin','admin')," +
            "('namnv','Nguyen Van Nam','123456')," +
            "('teonv','Nguyen Van Teo','123456')," +
            "('nott','Tran Thi No','123456')";
    public static final String INSER_THANH_VIEN = "insert into ThanhVien(hoTen, namSinh) values" +
            "('Trần Tuấn Công','2000')," +
            "('Lý Ngọc Sang','2000')," +
            "('Nguyễn Đình Thi','2000')," +
            "('Trần Quốc Thông','2000')," +
            "('Trần Ngọc Trà','2000')," +
            "('Đoàn Hữu Đoan','2000')," +
            "('Hoàng Minh Phú','2000')," +
            "('Lê Gia Bảo','2000')," +
            "('Lê Văn Minh Ngọc','2000')," +
            "('Dương Quang Nhân Lực','2000')," +
            "('Đinh Viết Phong','2000')," +
            "('Nguyễn Thái Quý','2000')," +
            "('Trần Văn Danh','2000')," +
            "('Trần Tiến Đạt','2000')," +
            "('Nguyễn Hưng','2000')," +
            "('Huỳnh Quang Trường','2000')," +
            "('Nguyễn Hoàng Hưng','2000')," +
            "('Trần Quốc Phương','2000')," +
            "('Nguyễn Văn Nhật','2000')," +
            "('Hồ Nhật Đức','2000')," +
            "('Phan Minh Nhân','2000')," +
            "('Nguyễn Chí Cường','2000')," +
            "('Nguyễn Nam Hiếu','2000')," +
            "('Nguyễn Phan Nhật Nguyên','2000')," +
            "('Nguyễn Thanh Tâm','2000')," +
            "('Hoàng Hồng Phúc','2000')," +
            "('Chế Văn Linh','2000')," +
            "('Nguyễn Hồng Nam','2000')," +
            "('Nguyễn Lương Hoàng Vĩ','2000')," +
            "('Đào Duy Hận','2000')," +
            "('Trần Đình Toàn','2000')," +
            "('Đoàn Văn Lộc','2000')," +
            "('Lê Quang Cao Nguyên','2000')," +
            "('Châu Minh Hiếu','2000')," +
            "('Lý Quang Cường','2000')," +
            "('Nguyễn Văn Tấn','2000')," +
            "('Nguyễn Thái Luật','2000')," +
            "('Lê Thị Thanh Trúc','2000')," +
            "('Trần Thị Ngọc Hương','2000')";
    public static final String INSERT_LOAI_SACH = "insert into LoaiSach(tenLoai) values" +
            "('Tiếng Anh cơ bản')," +
            "('Tiếng Anh nâng cao')," +
            "('Lập trình cơ bản')," +
            "('Lập trình android')," +
            "('Lập trình java')," +
            "('Lập trình web')";
    public static final String INSERT_SACH = "insert into Sach(tenSach,giaThue,maLoai) values" +
            "('Lập trình Java cơ bản','2000','5')," +
            "('Lập trình Java nâng cao','2000','5')," +
            "('Lập trình mạng với Java','2000','5')," +
            "('Lập trình desktop với Swing','2000','3')," +
            "('Dự án với công nghệ MS.NET MVC','2000','1')," +
            "('Dự án với công nghệ Spring MVC','2000','1')," +
            "('Dự án với công nghệ Servlet/JSP','2000','5')," +
            "('Dự án với AngularJS & WebAPI','2000','6')," +
            "('Dự án với Swing & JDBC','2000','5')," +
            "('Dự án với WindowForm','2000','1')," +
            "('Cơ sở dữ liệu SQL Server','2000','3')," +
            "('Lập trình JDBC','2000','4')," +
            "('Lập trình cơ sở dữ liệu Hibernate','2000','4')," +
            "('Lập trình web với Servlet/JSP','2000','3')," +
            "('Lập trình Spring MVC','2000','5')," +
            "('Lập trình MS.NET MVC','2000','1')," +
            "('Xây dựng Web API ','2000','3')," +
            "('Thiết kế web với HTML và CSS','2000','6')," +
            "('Thiết kế web với Bootstrap','2000','6')," +
            "('Lập trình front-end','2000','6')," +
            "('Lập trình AngularJS','2000','6')";
    public static final String INSERT_PHIEU_MUON = "insert into PhieuMuon(maTT,maTV,maSach,tienThue,ngay,traSach) values" +
            "('admin','1','1','2000','2021-09-10',1)," +
            "('admin','2','2','2500','2021-09-10',1)," +
            "('admin','3','3','3000','2021-09-10',0)," +
            "('admin','4','4','3200','2021-09-10',0)," +
            "('admin','5','5','4000','2021-09-10',0)," +
            "('admin','6','6','3000','2021-09-10',0)," +
            "('admin','7','7','3200','2021-09-10',0)," +
            "('admin','8','8','4000','2021-09-10',0)," +
            "('admin','9','9','2000','2021-09-10',1)" ;


}
