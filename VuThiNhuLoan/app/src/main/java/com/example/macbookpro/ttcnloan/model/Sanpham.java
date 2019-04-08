package com.example.macbookpro.ttcnloan.model;

import java.io.Serializable;

public class Sanpham implements Serializable {
    public int ID;
    public  String Tensanpam;
    public Integer Giasanpham;
    public  String Hinhanhsanpham;
    public  String Motasanpham;
    public  int IDsanpham;

    public Sanpham(int ID, String tensanpam, Integer giasanpham, String hinhanhsanpham, String motasanpham, int IDsanpham) {
        this.ID = ID;
        Tensanpam = tensanpam;
        Giasanpham = giasanpham;
        Hinhanhsanpham = hinhanhsanpham;
        Motasanpham = motasanpham;
        this.IDsanpham = IDsanpham;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTensanpam() {
        return Tensanpam;
    }

    public void setTensanpam(String tensanpam) {
        Tensanpam = tensanpam;
    }

    public Integer getGiasanpham() {
        return Giasanpham;
    }

    public void setGiasanpham(Integer giasanpham) {
        Giasanpham = giasanpham;
    }

    public String getHinhanhsanpham() {
        return Hinhanhsanpham;
    }

    public void setHinhanhsanpham(String hinhanhsanpham) {
        Hinhanhsanpham = hinhanhsanpham;
    }

    public String getMotasanpham() {
        return Motasanpham;
    }

    public void setMotasanpham(String motasanpham) {
        Motasanpham = motasanpham;
    }

    public int getIDsanpham() {
        return IDsanpham;
    }

    public void setIDsanpham(int IDsanpham) {
        this.IDsanpham = IDsanpham;
    }
}



