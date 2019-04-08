package com.example.macbookpro.ttcnloan.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.macbookpro.ttcnloan.R;
import com.example.macbookpro.ttcnloan.adappter.GioHangAdapter;
import com.example.macbookpro.ttcnloan.until.CheckConnection;

import java.text.DecimalFormat;


public class Giohang extends AppCompatActivity {

    ListView lvgiohang;
    TextView txtthongbao;
    static TextView txtongtien;
    Button btnthanhtoan, btntieptuc;
    Toolbar toolbargiohangg;
    GioHangAdapter gioHangAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_giohang );
        Anhxa ();
        ActionToolbar ();
        CheckData ();
        EventUntil ();
        CarctOnItemListView();
        EventButton();

    }

    private void EventButton() {
        btntieptuc.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent ( getApplicationContext (),MainActivity.class );
                startActivity ( intent );
            }
        } );
        btnthanhtoan.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (MainActivity.manggiohang.size ()>0){
                    Intent  intent=new Intent (getApplicationContext (), ThongTinKhachHang.class );
                    startActivity ( intent );

                }else {
                    CheckConnection.ShowToast_short ( getApplicationContext (),"Giỏ hàng của bạn chưa có sản phẩm để thanh toán" );
                }
            }
        } );
    }

    private void CarctOnItemListView() {
        lvgiohang.setOnItemLongClickListener ( new AdapterView.OnItemLongClickListener () {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, final long id) {
                AlertDialog.Builder builder=new AlertDialog.Builder ( Giohang.this );
                builder.setTitle ( "Xác nhận xóa sản phẩm" );
                builder.setMessage ( " Bạn có chắc là muốn xóa sản phẩm này không" );
                builder.setPositiveButton ( "Có! ", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        if (MainActivity.manggiohang.size ()<=0){
                            txtthongbao.setVisibility ( View.VISIBLE );
                        }else {
                            MainActivity.manggiohang.remove ( position );
                            gioHangAdapter.notifyDataSetChanged ();
                            EventUntil ();
                            if (MainActivity.manggiohang.size ()<=0){
                                txtthongbao.setVisibility ( View.VISIBLE );
                            }else {
                                txtthongbao.setVisibility ( View.INVISIBLE );
                                gioHangAdapter.notifyDataSetChanged ();
                                EventUntil ();
                            }

                        }

                    }
                } );
                builder.setNegativeButton ( "Không", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        gioHangAdapter.notifyDataSetChanged ();
                        EventUntil ();
                    }
                } );
                builder.show ();
                return true;
            }
        } );
    }

    public static void EventUntil() {
        long tongtien = 0;
        for (int i = 0; i < MainActivity.manggiohang.size (); i++)
        {
           tongtien+=MainActivity.manggiohang.get ( i ).getGiasp ();

        }
        DecimalFormat decimalFormat=new DecimalFormat ( "###,###,###" );
        txtongtien.setText ( decimalFormat.format ( tongtien )+"VND" );
    }


    private void CheckData() {
        if (MainActivity.manggiohang.size () <= 0) {
            gioHangAdapter.notifyDataSetChanged ();
            txtthongbao.setVisibility ( View.VISIBLE );
            lvgiohang.setVisibility ( View.INVISIBLE );
        } else {
            gioHangAdapter.notifyDataSetChanged ();
            txtthongbao.setVisibility ( View.INVISIBLE );
            lvgiohang.setVisibility ( View.VISIBLE );
        }

    }


    private void ActionToolbar() {

        setSupportActionBar ( toolbargiohangg );
        getSupportActionBar ().setDisplayHomeAsUpEnabled ( true );

        toolbargiohangg.setNavigationOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                finish ();
            }
        } );
    }


    private void Anhxa() {
        lvgiohang = findViewById ( R.id.lvgiohang );
        txtthongbao = findViewById ( R.id.txtthongbao );
        txtongtien = findViewById ( R.id.txttongtien );
        btnthanhtoan = findViewById ( R.id.btnthanhtoangiohang );
        btntieptuc = findViewById ( R.id.btntieptucmuahang );
        toolbargiohangg = findViewById ( R.id.toolbargiohang );
        gioHangAdapter = new GioHangAdapter ( Giohang.this, MainActivity.manggiohang );
        lvgiohang.setAdapter ( gioHangAdapter );

    }
}
