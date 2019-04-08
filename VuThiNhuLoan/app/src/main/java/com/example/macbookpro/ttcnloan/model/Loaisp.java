package com.example.macbookpro.ttcnloan.model;

import java.io.Serializable;

public class Loaisp implements Serializable {
    public  int Id;
    public  String Tenloaisp;
    public String Hinhanhloaisp;
    int hinhanh;

    public Loaisp(int id, String tenloaisp, int hinhanh) {
        Id = id;
        Tenloaisp = tenloaisp;
        this.hinhanh = hinhanh;
    }

    public Loaisp(int id, String tenloaisp, String hinhanhloaisp) {
        Id = id;
        Tenloaisp = tenloaisp;
        Hinhanhloaisp = hinhanhloaisp;
    }

    public int getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(int hinhanh) {
        this.hinhanh = hinhanh;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTenloaisp() {
        return Tenloaisp;
    }

    public void setTenloaisp(String tenloaisp) {
        Tenloaisp = tenloaisp;
    }

    public String getHinhanhloaisp() {
        return Hinhanhloaisp;
    }

    public void setHinhanhloaisp(String hinhanhloaisp) {
        Hinhanhloaisp = hinhanhloaisp;
    }


}
