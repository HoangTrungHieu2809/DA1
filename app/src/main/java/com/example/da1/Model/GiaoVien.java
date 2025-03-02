package com.example.da1.Model;

public class GiaoVien {
    private String id; // ID chính là mã giảng viên
    private String hoTen;
    private String queQuan;
    private String maGiangVien;
    private String ngaySinh;
    private String monGiangDay;

    public GiaoVien() {
        // Constructor mặc định cần thiết cho Firestore
    }

    public GiaoVien(String id, String hoTen, String queQuan, String maGiangVien, String ngaySinh, String monGiangDay) {
        this.id = id;
        this.hoTen = hoTen;
        this.queQuan = queQuan;
        this.maGiangVien = maGiangVien;
        this.ngaySinh = ngaySinh;
        this.monGiangDay = monGiangDay;
    }

    // Getter và Setter cho ID
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Getter và Setter cho các thuộc tính khác
    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public String getMaGiangVien() {
        return maGiangVien;
    }

    public void setMaGiangVien(String maGiangVien) {
        this.maGiangVien = maGiangVien;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getMonGiangDay() {
        return monGiangDay;
    }

    public void setMonGiangDay(String monGiangDay) {
        this.monGiangDay = monGiangDay;
    }
}
