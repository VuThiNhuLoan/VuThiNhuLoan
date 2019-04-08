package com.example.macbookpro.ttcnloan.activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.macbookpro.ttcnloan.R;

import com.example.macbookpro.ttcnloan.model.Giohang;
import com.example.macbookpro.ttcnloan.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ChiTietSanPham extends AppCompatActivity {
    Toolbar toolbarchitiet;
    ImageView imageViewchitiet;
    TextView txtten, txtgia, txtmota;
    Spinner spinner;
    Button buttonmua;
    int id = 0;
    String TenChitiet = "";
    int GiaChitiet = 0;
    String HinhanhChitiet = "";
    String MoTaChitiet = "";
    int Idsanpham = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_chi_tiet_san_pham );
        Anhxa ();
        ActionToolbar ();
        GetInformation ();
        CatchEventSpinner ();
        EventButton();
    }



    private void EventButton() {
        buttonmua.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (MainActivity.manggiohang.size ()>0){
                    int s1=Integer.parseInt (spinner.getSelectedItem ().toString ());
                    boolean exists=false;
                    for (int i=0;i<MainActivity.manggiohang.size ();i++){
                        if (MainActivity.manggiohang.get ( i ).getIdsp ()==id){
                            MainActivity.manggiohang.get ( i ).setSoluongsp (MainActivity.manggiohang.get ( i ).getSoluongsp ()+s1);
                            if (MainActivity.manggiohang.get ( i ).getSoluongsp ()>10){
                                MainActivity.manggiohang.get ( i ).setSoluongsp ( 10 );
                            }
                            MainActivity.manggiohang.get ( i ).setGiasp ( GiaChitiet * MainActivity.manggiohang.get ( i ).getSoluongsp () );
                            exists=true;

                        }
                    }
                    if (exists==false){
                        int soluong=Integer.parseInt (spinner.getSelectedItem ().toString ());
                        long Giamoi=soluong *GiaChitiet;
                        MainActivity.manggiohang.add ( new Giohang ( id,TenChitiet,Giamoi,HinhanhChitiet,soluong ) );

                    }

                }else {
                    int soluong=Integer.parseInt (spinner.getSelectedItem ().toString ());
                    long Giamoi=soluong *GiaChitiet;
                    MainActivity.manggiohang.add ( new Giohang ( id,TenChitiet,Giamoi,HinhanhChitiet,soluong ) );

                }
                Intent intent=new Intent ( getApplicationContext (), com.example.macbookpro.ttcnloan.activity.Giohang.class );
                startActivity ( intent );
            }
        } );
    }

    private void CatchEventSpinner() {
        Integer[] soluong = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer> ( this, R.layout.support_simple_spinner_dropdown_item, soluong );
        spinner.setAdapter ( arrayAdapter );
    }

    private void GetInformation() {

        Sanpham sanpham = (Sanpham) getIntent ().getSerializableExtra ( "thongtinsanpham" );
        id = sanpham.getID ();
        TenChitiet = sanpham.getTensanpam ();
        GiaChitiet = sanpham.getGiasanpham ();
        HinhanhChitiet = sanpham.getHinhanhsanpham ();
        MoTaChitiet = sanpham.getMotasanpham ();
        txtten.setText ( TenChitiet );
        DecimalFormat decimalFormat = new DecimalFormat ( "###,###,###" );
        txtgia.setText ( "Gia : " + decimalFormat.format ( GiaChitiet ) + "Đ" );
        txtmota.setText ( MoTaChitiet );
        Picasso.with ( getApplicationContext () ).load ( HinhanhChitiet )
                .placeholder ( R.drawable.ic_check_black_24dp )
                .error ( R.drawable.ic_error_black_24dp )
                .into ( imageViewchitiet );
    }


    private void ActionToolbar() {
        setSupportActionBar ( toolbarchitiet );
        getSupportActionBar ().setDisplayHomeAsUpEnabled ( true );
        toolbarchitiet.setNavigationOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                finish ();
            }
        } );

    }



    private void Anhxa() {
        toolbarchitiet = findViewById ( R.id.toolbarchitietsp );
        imageViewchitiet = findViewById ( R.id.imgchitietsp );
        txtten = findViewById ( R.id.txttenchitietsp );
        txtgia = findViewById ( R.id.txtgiachitietsp );
        txtmota = findViewById ( R.id.txtmotachitietsp );
        spinner = findViewById ( R.id.spinner );
        buttonmua = findViewById ( R.id.btndatmua );
        setSupportActionBar ( toolbarchitiet );
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate ( R.menu.menu,menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId ()){
            case R.id.menugiohang:
                Intent intent=new Intent ( getApplicationContext (), com.example.macbookpro.ttcnloan.activity.Giohang.class );
                startActivity ( intent );
        }
        return super.onOptionsItemSelected ( item );
    }
}


