package com.example.da1.Model;

public class HocSinh {
    private String maSinhVien;
    private String hoTen;
    private String thuocLop;

    public HocSinh() {
        // Constructor mặc định cho Firestore
    }

    public HocSinh(String maSinhVien, String hoTen, String thuocLop) {
        this.maSinhVien = maSinhVien;
        this.hoTen = hoTen;
        this.thuocLop = thuocLop;
    }

    public String getMaSinhVien() {
        return maSinhVien;
    }

    public void setMaSinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getThuocLop() {
        return thuocLop;
    }

    public void setThuocLop(String thuocLop) {
        this.thuocLop = thuocLop;
    }
}
