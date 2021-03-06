package home;



import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.BookAccessObject;
import model.CustomerBooksReturnAccessObject;
import model.CustomerBorrowBooksAccessObject;
import model.entity.qlMuonSach;
import model.entity.qlSach;
import model.entity.qlTraSach;
import main.Main;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;


import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    //bảng sách
    public TableView <qlSach>tbViewBook;
    public TableColumn<qlSach,Integer>tbSttB;
    public TableColumn <qlSach,String> tbIDb;
    public TableColumn<qlSach,String> tbNameb;
    public TableColumn<qlSach,String> tbAuthorb;
    public TableColumn<qlSach,String> tbCategory;
    public TableColumn<qlSach,Integer> tbNumberb;
    //bảng Người Mượn
    public TableView<qlMuonSach> tbViewKM;
    public TableColumn<qlMuonSach,Integer>tb_SttMS;
    public TableColumn <qlMuonSach,Integer> tb_IDKM;
    public TableColumn <qlMuonSach,Integer> tb_PhoneM;
    public TableColumn<qlMuonSach,String> tb_AdressM;
    public TableColumn<qlMuonSach,String> tb_NameKM;
    public TableColumn<qlMuonSach,String> tb_idBKM;
    public TableColumn<qlMuonSach,String> tb_NameBKM;
    public TableColumn<qlMuonSach,Integer>tb_SlKM;
    public TableColumn<qlMuonSach, Date> tb_NM;

    //bảng Người Trả
    public TableView<qlTraSach> tbViewKT;
    public TableColumn<qlTraSach,Integer>tb_SttTS;//số TT
    public TableColumn<qlTraSach,Integer>tb_SlT;//số lượng sách khách mượn
    public TableColumn<qlTraSach,Integer>tb_SdtKT;//sđt KH
    public TableColumn<qlTraSach,String>tb_IDKT;//mã KH
    public TableColumn<qlTraSach,String>tb_NameKT;//tên kh
    public TableColumn<qlTraSach,String>tb_DiaChiKT;//địa chỉ kh
    public TableColumn<qlTraSach,String>tb_idBKT;//mã sách trả
    public TableColumn<qlTraSach,String>tb_NameBKT;//ten khach sách trả\
    public TableColumn<qlTraSach,Date>tb_NT;//ngày gian trả
    //Nhập QL sách
   //ô nhâp tên sách

    public TextField txtMa_S;
    public TextField txtTen_S;
    public TextField txtTacgia;
    public TextField txtTheloai;
    public TextField txt_Soluong;

    //nhập qkl ngườii mượn
    public TextField txt_mkhm;
    public TextField txt_tkhm;
    public TextField txt_sdtm;
    public TextField txt_diachim;
    public TextField txt_masachm;
    public TextField txt_tensachm;
    public TextField txt_soluongm;
    public DatePicker txt_ngaym;

    //nhập qltrasach
    public TextField txt_makt;
    public TextField txt_tenkt;
    public TextField txt_sdtkt;
    public TextField txt_diachikt;
    public TextField txt_maSKT;
    public TextField txt_nhaptenkt;
    public TextField txt_soluongkt;
    public DatePicker txt_ngaykt;
    //ô thống kê
    public TextField tkS;
    public TextField tkMS;
    public TextField tkTS;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //các hiện thị danh sách viết vô dây
        //hiển thị sách
        tbSttB.setCellValueFactory(new  PropertyValueFactory<>("sttb"));
        tbIDb.setCellValueFactory(new PropertyValueFactory<>("idB"));
        tbNameb.setCellValueFactory(new PropertyValueFactory<>("nameB"));
        tbAuthorb.setCellValueFactory(new PropertyValueFactory<>("author"));
        tbCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        tbNumberb.setCellValueFactory(new PropertyValueFactory<>("amountB"));
        BookAccessObject BDAO = new BookAccessObject();
        ObservableList<qlSach> dsB = BDAO.getList();
        tbViewBook.setItems(dsB);
        tkS.setText(String.valueOf(dsB.size()));

        //hiển thị danh sách người mượn
        tb_SttMS.setCellValueFactory(new PropertyValueFactory<>("sttMS"));
        tb_IDKM.setCellValueFactory(new PropertyValueFactory<>("idKM"));
        tb_NameKM.setCellValueFactory(new PropertyValueFactory<>("nameKM"));
        tb_PhoneM.setCellValueFactory(new PropertyValueFactory<>("phoneKM"));
        tb_AdressM.setCellValueFactory(new PropertyValueFactory<>("adressKM"));
        tb_idBKM.setCellValueFactory(new PropertyValueFactory<>("idB"));
        tb_NameBKM.setCellValueFactory(new PropertyValueFactory<>("nameB"));
        tb_SlKM.setCellValueFactory(new PropertyValueFactory<>("soluongM"));
        tb_NM.setCellValueFactory(new PropertyValueFactory<>("ngayM"));
        CustomerBorrowBooksAccessObject KMDAO = new CustomerBorrowBooksAccessObject();
        ObservableList dsM = KMDAO.getList();
        tbViewKM.setItems(dsM);
        tkMS.setText(String.valueOf(dsM.size()));

        //Hiển thị danh sách người trả
        tb_SttTS.setCellValueFactory(new PropertyValueFactory<>("sttTS"));
        tb_IDKT.setCellValueFactory(new PropertyValueFactory<>("idKT"));
        tb_NameKT.setCellValueFactory(new PropertyValueFactory<>("nameKT"));
        tb_SdtKT.setCellValueFactory(new PropertyValueFactory<>("phoneKT"));
        tb_DiaChiKT.setCellValueFactory(new PropertyValueFactory<>("adressKT"));
        tb_idBKT.setCellValueFactory(new PropertyValueFactory<>("idB"));
        tb_NameBKT.setCellValueFactory(new PropertyValueFactory<>("nameB"));
        tb_SlT.setCellValueFactory(new PropertyValueFactory<>("soluongT"));
        tb_NT.setCellValueFactory(new PropertyValueFactory<>("ngayT"));
        CustomerBooksReturnAccessObject KTDAO = new CustomerBooksReturnAccessObject();
        ObservableList dsT = KTDAO.getList();
        tbViewKT.setItems(dsT);
        tkTS.setText(String.valueOf(dsT.size()));


    }

    //lấy dữ liệu của bảng từ sự kiện Cick


    public void ClickBook(){
        //Lấy Dữ Liệu Sách
        model.entity.qlSach sv = tbViewBook.getSelectionModel().getSelectedItem();
        txtMa_S.setText(sv.getIdB());
        txtTen_S.setText(sv.getNameB());
        txtTacgia.setText(sv.getAuthor());
        txtTheloai.setText(sv.getCategory());
        txt_Soluong.setText(String.valueOf(sv.amountB));
    }

    public void ClickKHM(){
        //Lấy Dữ Liệu Người Mượn
        model.entity.qlMuonSach svm = tbViewKM.getSelectionModel().getSelectedItem();
        txt_mkhm.setText(svm.getIdKM());
        txt_tkhm.setText(svm.getNameKM());
        txt_sdtm.setText(String.valueOf(svm.phoneKM));
        txt_diachim.setText(svm.getAdressKM());
        txt_masachm.setText(svm.getIdB());
        txt_tensachm.setText(svm.getNameB());
        txt_Soluong.setText(String.valueOf(svm.soluongM));

    }


    public void ClickKHT(){
        //Lấy Dữ Liệu Người Trả
        qlTraSach ms = tbViewKT.getSelectionModel().getSelectedItem();
        txt_maSKT.setText(ms.getIdB());
        txt_makt.setText(ms.getIdKT());
        txt_tenkt.setText(ms.getNameKT());
        txt_sdtkt.setText(String.valueOf(ms.phoneKT));
        txt_diachikt.setText(ms.getAdressKT());
        txt_nhaptenkt.setText(ms.getNameB());
        txt_soluongkt.setText(String.valueOf(ms.soluongT));

    }

    //Quân
    // Quản Lý Sách


    public void btDeleteBook() throws IOException{
        model.entity.qlSach  qls = tbViewBook.getSelectionModel().getSelectedItem();
        //xóa sách
        BookAccessObject BAO =new BookAccessObject();
        qlSach qlSach = new qlSach(null,qls.getIdB(),qls.getNameB(),qls.getAuthor(),qls.getCategory(),qls.getAmountB());
        BAO.delete(qlSach);
        JOptionPane.showMessageDialog(null,"Xóa Sách Thành Công");

    }
    //Sửa Sách
    public void btEditB() throws IOException {
        String maS = txtMa_S.getText();
        String tenS = txtTen_S.getText();
        String tacGia = txtTacgia.getText();
        String theL = txtTheloai.getText();
        String soLuong = txt_Soluong.getText();
        if(!maS.isEmpty()&&!tenS.isEmpty()&&!tacGia.isEmpty()&&!theL.isEmpty()&&!soLuong.isEmpty()){
            BookAccessObject bao = new BookAccessObject();
            Integer soL = Integer.parseInt(soLuong);
            qlSach s = new qlSach(null,maS,tenS,tacGia,theL,soL);
            bao.update(s);
            JOptionPane.showMessageDialog(null,"Sửa Thành Công");
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../home/LibraryManager.fxml"));
            Main.mainStage.setScene(new Scene(root, 1263, 944));
            Main.mainStage.show();
        }


    }
    public void btAddBook() throws SQLException, IOException {
        // lay input cua nguoi dung
        // tao doi tuong qlSach tu thong tin tren
        String maS = txtMa_S.getText();
        String tenS = txtTen_S.getText();
        String tacGia = txtTacgia.getText();
        String theL = txtTheloai.getText();
        String soLuong = txt_Soluong.getText();
        if(!maS.isEmpty()&&!tenS.isEmpty()&&!tacGia.isEmpty()&&!theL.isEmpty()&&!soLuong.isEmpty()){
            BookAccessObject bao = new BookAccessObject();
            Integer soL = Integer.parseInt(soLuong);
            qlSach s = new qlSach(null,maS,tenS,tacGia,theL,soL);
            System.out.println(bao.create(s));
//            bao.create(s);
            JOptionPane.showMessageDialog(null,"Thêm Thành Công");
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../home/LibraryManager.fxml"));
            Main.mainStage.setScene(new Scene(root, 1263, 944));
            Main.mainStage.show();

        }


    }


    //Quản Lý Khách Hàng Mượn Sách
    //khai tao các ô nghập ở đây


    public void themKhachMuon() throws IOException {
        String mkhms = txt_mkhm.getText();
        String tkhms = txt_tkhm.getText();
        String sdtms = txt_sdtm.getText();
        String diachims = txt_diachim.getText();
        String masachms = txt_masachm.getText();
        String tensachms = txt_tensachm.getText();
        String soluongms = txt_soluongm.getText();
        String ngayms = txt_ngaym.getValue().toString();
//        if (!mkhms.isEmpty()&&!tkhms.isEmpty()&&!sdtms.isEmpty()&&!diachims.isEmpty()&&!masachms.
//                isEmpty()&&!tensachms.isEmpty()&&!soluongms.isEmpty()){
            Integer phonem = Integer.parseInt(sdtms);
            Integer soluongm = Integer.parseInt(soluongms);
            CustomerBorrowBooksAccessObject book2 = new CustomerBorrowBooksAccessObject();
            qlMuonSach ms =new qlMuonSach(null,mkhms,tkhms,phonem,diachims,masachms,tensachms,soluongm,ngayms);
            book2.create(ms);
        JOptionPane.showMessageDialog(null,"Thêm KH Thành Công");
        Parent root;
        root = FXMLLoader.load(getClass().getResource("../home/LibraryManager.fxml"));
        Main.mainStage.setScene(new Scene(root, 1263, 944));
        Main.mainStage.show();
//        }
    }

    public void suaKhachMuon() throws IOException {
        String mkhms = txt_mkhm.getText();
        String tkhms = txt_tkhm.getText();
        String sdtms = txt_sdtm.getText();
        String diachims = txt_diachim.getText();
        String masachms = txt_masachm.getText();
        String tensachms = txt_tensachm.getText();
        String soluongms = txt_soluongm.getText();
        String ngayms = txt_ngaym.getValue().toString();
//        if (!mkhms.isEmpty()&&!tkhms.isEmpty()&&!sdtms.isEmpty()&&!diachims.isEmpty()&&!masachms.
//                isEmpty()&&!tensachms.isEmpty()&&!soluongms.isEmpty()){
        Integer phonem = Integer.parseInt(sdtms);
        Integer soluongm = Integer.parseInt(soluongms);
        CustomerBorrowBooksAccessObject book2 = new CustomerBorrowBooksAccessObject();
        qlMuonSach ms =new qlMuonSach(null,mkhms,tkhms,phonem,diachims,masachms,tensachms,soluongm,ngayms);
        book2.update(ms);
        JOptionPane.showMessageDialog(null,"Sửa Thành Công");
        Parent root;
        root = FXMLLoader.load(getClass().getResource("../home/LibraryManager.fxml"));
        Main.mainStage.setScene(new Scene(root, 1263, 944));
        Main.mainStage.show();


    }
    public void xoaKhachMuon() throws IOException {
        model.entity.qlMuonSach  qlms = tbViewKM.getSelectionModel().getSelectedItem();
        //xóa sách
        CustomerBorrowBooksAccessObject CBBAO = new CustomerBorrowBooksAccessObject();
        qlMuonSach qlMSach = new qlMuonSach(null,qlms.getNameKM(),qlms.getIdKM(),qlms.getPhoneKM(),qlms.getAdressKM(),qlms.getIdB(),qlms.getNameB(),qlms.getSoluongM(),qlms.getNgayM());
        CBBAO.delete(qlMSach);
        JOptionPane.showMessageDialog(null,"Xóa KH  Thành Công");
        Parent root;
        root = FXMLLoader.load(getClass().getResource("../home/LibraryManager.fxml"));
        Main.mainStage.setScene(new Scene(root, 1263, 944));
        Main.mainStage.show();
    }


    //Quản Lý Khách Hàng Trả Sách

    //khai tao các ô nghập ở đây


    public void themKhachTra() throws SQLException, IOException {
        String mkhts = txt_makt.getText();
        String tkhts = txt_tenkt.getText();
        String sdtts = txt_sdtkt.getText();
        String diachits = txt_diachikt.getText();
        String masachts = txt_maSKT.getText();
        String tensachts = txt_nhaptenkt.getText();
        String soluongts = txt_soluongkt.getText();
        String ngayts = txt_ngaykt.getValue().toString();
//        if (!mkhms.isEmpty()&&!tkhms.isEmpty()&&!sdtms.isEmpty()&&!diachims.isEmpty()&&!masachms.
//                isEmpty()&&!tensachms.isEmpty()&&!soluongms.isEmpty()){
        Integer phonet = Integer.parseInt(sdtts);
        Integer soluongt = Integer.parseInt(soluongts);
        Date ngayTS = Date.valueOf(ngayts);
        CustomerBooksReturnAccessObject book3 = new CustomerBooksReturnAccessObject();
        qlTraSach ts =new qlTraSach(null,mkhts,tkhts,phonet,diachits,masachts,tensachts,soluongt,ngayTS);
            book3.create(ts);
        JOptionPane.showMessageDialog(null,"Thêm Thành Công");
        Parent root;
        root = FXMLLoader.load(getClass().getResource("../home/LibraryManager.fxml"));
        Main.mainStage.setScene(new Scene(root, 1263, 944));
        Main.mainStage.show();
//        }
    }

    public void suaKhachTra() throws IOException {
        String mkhts = txt_makt.getText();
        String tkhts = txt_tenkt.getText();
        String sdtts = txt_sdtkt.getText();
        String diachits = txt_diachikt.getText();
        String masachts = txt_maSKT.getText();
        String tensachts = txt_nhaptenkt.getText();
        String soluongts = txt_soluongkt.getText();
        String ngayts = txt_ngaykt.getValue().toString();
//        if (!mkhms.isEmpty()&&!tkhms.isEmpty()&&!sdtms.isEmpty()&&!diachims.isEmpty()&&!masachms.
//                isEmpty()&&!tensachms.isEmpty()&&!soluongms.isEmpty()){
        Integer phonet = Integer.parseInt(sdtts);
        Integer soluongt = Integer.parseInt(soluongts);
        Date ngayTS = Date.valueOf(ngayts);
        CustomerBooksReturnAccessObject book3 = new CustomerBooksReturnAccessObject();
        qlTraSach ts =new qlTraSach(null,mkhts,tkhts,phonet,diachits,masachts,tensachts,soluongt,ngayTS);
        book3.update(ts);
        JOptionPane.showMessageDialog(null,"Sửa Thành Công");
        Parent root;
        root = FXMLLoader.load(getClass().getResource("../home/LibraryManager.fxml"));
        Main.mainStage.setScene(new Scene(root, 1263, 944));
        Main.mainStage.show();
    }


    public void xoaKhachTra() throws IOException {
        qlTraSach qlts = tbViewKT.getSelectionModel().getSelectedItem();
        //xóa sách
        CustomerBooksReturnAccessObject CBRAOM = new CustomerBooksReturnAccessObject();
        qlTraSach qlTs = new qlTraSach(null,qlts.getIdKT(),qlts.getNameKT(),qlts.getPhoneKT(),qlts.getAdressKT(),qlts.getIdB(),qlts.getNameB(),qlts.getSoluongT(),qlts.getNgayT());
        CBRAOM.delete(qlts);
        JOptionPane.showMessageDialog(null,"Xóa KH  Thành Công");
        Parent root;
        root = FXMLLoader.load(getClass().getResource("../home/LibraryManager.fxml"));
        Main.mainStage.setScene(new Scene(root, 1263, 944));
        Main.mainStage.show();

    }

    //Thống Kê

    public void restart(){
        //khai tao các ô xuất ở đây


    }



    public void close(){
        System.exit(0);

    }
}

