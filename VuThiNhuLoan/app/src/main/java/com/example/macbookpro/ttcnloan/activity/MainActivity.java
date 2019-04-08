package com.example.macbookpro.ttcnloan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
//import com.example.myapplication.activity.R;
import com.example.macbookpro.ttcnloan.R;

import com.example.macbookpro.ttcnloan.adappter.Loaispadappter;
import com.example.macbookpro.ttcnloan.adappter.SanphamAdapter;
import com.example.macbookpro.ttcnloan.model.Giohang;
import com.example.macbookpro.ttcnloan.model.Loaisp;
import com.example.macbookpro.ttcnloan.model.Sanpham;
import com.example.macbookpro.ttcnloan.until.CheckConnection;
import com.example.macbookpro.ttcnloan.until.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewmanhinhchinh;
    NavigationView navigationView;
    ListView  listViewmanhinhhinh;
    DrawerLayout drawerLayout;
    ArrayList<Loaisp>mangloaisp;
    Loaispadappter loaispadappter;
    int id=0;
    String tenloaisp =" ";
    String hinhanhloaisp =" ";
    ArrayList<Sanpham>mangsanpham;
    SanphamAdapter sanphamAdapter;
    public  static  ArrayList<Giohang> manggiohang;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main);
    Anhxa();
    if (CheckConnection.haveNetworkConnection ( getApplicationContext () )){
        ActionBar();GetDuLieuLoaisp();
        ActionViewflipper();

        GetDuLieuSPMoinhat ();
        CateOnItemListView();
    }else {
        CheckConnection.ShowToast_short ( getApplicationContext (),"ban hay kiem tra lai ket noi" );
        finish ();
    }

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

    private void CateOnItemListView() {
        listViewmanhinhhinh.setOnItemClickListener ( new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                switch (i){
                    case 0:
                        if (CheckConnection.haveNetworkConnection ( getApplicationContext () )){
                           // Intent intent =new Intent ( MainActivity.this,TruyenTranh.class );
                           // startActivity ( intent );

                        } else {
                            CheckConnection.ShowToast_short ( getApplicationContext (),"ban hay kiem tra lai ket noi" );

                        }
                        drawerLayout.closeDrawer ( GravityCompat.START );
                        break;
                    case 1:
                        if (CheckConnection.haveNetworkConnection ( getApplicationContext () )){
                            Intent intent =new Intent ( MainActivity.this,TruyenTranh.class );
                            intent.putExtra ( "page",1 );
                            intent.putExtra ( "idloaisanpham",mangloaisp.get ( i ).getId () );
                            Log.d("LOAI",mangloaisp.get ( i ).getId ()+"");
                            startActivity ( intent );
                        } else {
                            CheckConnection.ShowToast_short ( getApplicationContext (),"ban hay kiem tra lai ket noi" );

                        }
                        drawerLayout.closeDrawer ( GravityCompat.START );
                        break;
                    case 2:
                        if (CheckConnection.haveNetworkConnection ( getApplicationContext () )){
                            Intent intent =new Intent ( MainActivity.this,TruyenTranh.class  );

                            intent.putExtra ( "page",2 );

                            intent.putExtra ( "idloaisanpham",mangloaisp.get ( i ).getId () );
                            Log.d("LOAI",mangloaisp.get ( i ).getId ()+"");
                            startActivity ( intent );
                        } else {
                            CheckConnection.ShowToast_short ( getApplicationContext (),"ban hay kiem tra lai ket noi" );

                        }
                        drawerLayout.closeDrawer ( GravityCompat.START );
                        break;
                    case 3:
                        if (CheckConnection.haveNetworkConnection ( getApplicationContext () )){
                            Intent intent =new Intent ( MainActivity.this,TruyenTranh.class );
                            intent.putExtra ( "page",3 );
                            intent.putExtra ( "idloaisanpham",mangloaisp.get ( i ).getId () );
                            Log.d("LOAI",mangloaisp.get ( i ).getId ()+"");
                            startActivity ( intent );
                        } else {
                            CheckConnection.ShowToast_short ( getApplicationContext (),"ban hay kiem tra lai ket noi" );

                        }
                        drawerLayout.closeDrawer ( GravityCompat.START );
                        break;
                    case 4:
                        if (CheckConnection.haveNetworkConnection ( getApplicationContext () )){
                            Intent intent =new Intent ( MainActivity.this,TruyenTranh.class );
                            intent.putExtra ( "page",5 );
                            intent.putExtra ( "idloaisanpham",mangloaisp.get ( i ).getId () );
                            Log.d("LOAI",mangloaisp.get ( i ).getId ()+"");
                            startActivity ( intent );
                        } else {
                            CheckConnection.ShowToast_short ( getApplicationContext (),"ban hay kiem tra lai ket noi" );

                        }
                        drawerLayout.closeDrawer ( GravityCompat.START );
                        break;
                    case 5:
                        if (CheckConnection.haveNetworkConnection ( getApplicationContext () )){
                            Intent intent =new Intent ( MainActivity.this,TruyenTranh.class );
                            intent.putExtra ( "page",5 );
                            intent.putExtra ( "idloaisanpham",mangloaisp.get ( i ).getId () );
                            Log.d("LOAI",mangloaisp.get ( i ).getId ()+"");
                            startActivity ( intent );
                        } else {
                            CheckConnection.ShowToast_short ( getApplicationContext (),"ban hay kiem tra lai ket noi" );

                        }
                        drawerLayout.closeDrawer ( GravityCompat.START );
                        break;
                    case 6:
                        if (CheckConnection.haveNetworkConnection ( getApplicationContext () )){
                            Intent intent =new Intent ( MainActivity.this,LienHe.class );
                            Log.d("LOAI",mangloaisp.get ( i ).getId ()+"");
                            startActivity ( intent );
                        } else {
                            CheckConnection.ShowToast_short ( getApplicationContext (),"ban hay kiem tra lai ket noi" );

                        }
                        drawerLayout.closeDrawer ( GravityCompat.START );
                        break;
                    case 7:
                        if (CheckConnection.haveNetworkConnection ( getApplicationContext () )){
                            Intent intent =new Intent ( MainActivity.this,ThongTin.class );
                            Log.d("LOAI",mangloaisp.get ( i ).getId ()+"");

                            startActivity ( intent );
                        } else {
                            CheckConnection.ShowToast_short ( getApplicationContext (),"ban hay kiem tra lai ket noi" );

                        }
                        drawerLayout.closeDrawer ( GravityCompat.START );
                        break;

                }
            }
        } );
    }

    private void GetDuLieuSPMoinhat() {
        RequestQueue requestQueue=Volley.newRequestQueue ( getApplicationContext () );
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest ( Server.Duongdansanphammoinhat, new Response.Listener<JSONArray> () {
            @Override
            public void onResponse(JSONArray response) {
                if (response !=null){
                    int ID=0;
                    String Tensanpham="";
                    Integer Giasanpham=0;
                    String Hinhanhsanpham="";
                    String Motasanpham="";
                    int IDsanpham=0;
                    for (int i=0;i<response.length ();i++){
                        try {
                            JSONObject jsonObject=response.getJSONObject ( i );
                            ID=jsonObject.getInt ( "id" );
                            Tensanpham=jsonObject.getString ( "tensp" );
                            Giasanpham=jsonObject.getInt ( "giasp" );
                            Hinhanhsanpham=jsonObject.getString ( "hinhanhsp" );
                            Motasanpham=jsonObject.getString ( "motasp" );
                            IDsanpham=jsonObject.getInt ( "idsanpham" );
                            mangsanpham.add ( new Sanpham ( ID,Tensanpham,Giasanpham,Hinhanhsanpham,Motasanpham,IDsanpham ) );
                            //System.out.println ("BBBBBBBBBB"+ Tensanpham);
                            sanphamAdapter.notifyDataSetChanged ();
                        } catch (JSONException e) {
                            e.printStackTrace ();
                        }
                    }


                }
            }
        }, new Response.ErrorListener () {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        } );
        requestQueue.add ( jsonArrayRequest );

    }

    private void GetDuLieuLoaisp() {
        RequestQueue requestQueue=Volley.newRequestQueue ( getApplicationContext () );
        JsonArrayRequest jsonArrayRequest =new JsonArrayRequest ( Server.Duongdanloaisp, new Response.Listener<JSONArray> () {
            @Override
            public void onResponse(JSONArray response) {
                if (response!=null){
                    mangloaisp.clear ();
                    mangloaisp.add ( new Loaisp ( 1,"Trang chu",R.drawable.ic_home) );
                    for (int i=0; i<response.length ();i++){
                        try {
                            JSONObject jsonObject=response.getJSONObject (i);
                            id  =jsonObject.getInt ( "id" );
                            tenloaisp = jsonObject.getString ( "tenloaisp" );
                            hinhanhloaisp = jsonObject.getString ( "hinhanhloaisp" );
                            mangloaisp.add ( new Loaisp ( id,tenloaisp,hinhanhloaisp ) );

                            //System.out.println ("AAAAAAAAA"+ tenloaisp);
                        } catch (JSONException e) {
                            e.printStackTrace ();
                        }

                    }
                    mangloaisp.add ( new Loaisp ( 0,"Liên hệ","https://i.ytimg.com/vi/PAuMs3WCd68/maxresdefault.jpg" ) );
                    mangloaisp.add ( new Loaisp ( 0,"Thông tin","https://i.ytimg.com/vi/PAuMs3WCd68/maxresdefault.jpg" ) );
                    loaispadappter.notifyDataSetChanged ();
                }

            }
        }, new Response.ErrorListener () {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.ShowToast_short ( getApplicationContext (),error.toString () );
            }
        } );
        requestQueue.add ( jsonArrayRequest );

//        Toast.makeText ( this, ""+mangloaisp.get ( 0 ).Tenloaisp, Toast.LENGTH_SHORT ).show ();
    }

    private void ActionViewflipper() {
        ArrayList<String> mangquangcao=new ArrayList<> ( );
        mangquangcao.add ("http://nt.svis.edu.vn/wp-content/uploads/sites/2/Hinh-6.jpg");
        mangquangcao.add ("http://file.vforum.vn/hinh/2018/03/hinh-anh-hinh-nen-quyen-sach-dep-nhat-9.jpg");
        mangquangcao.add ("https://znews-photo.zadn.vn/w660/Uploaded/neg_iflemly/2017_08_07/1487647138739_8415534.jpg");
        mangquangcao.add ("https://traumvietnam.com/wp-content/uploads/2015/11/10-cuon-sach-hoc-tieng-nhat-ban-nen-biet.jpg");
        for (int i=0;i<mangquangcao.size ();i++){
            ImageView imageView =new ImageView ( getApplicationContext () );
            Picasso.with ( getApplicationContext () ).load ( mangquangcao.get ( i ) ).into (imageView );
            imageView.setScaleType ( ImageView.ScaleType.FIT_XY );
            viewFlipper.addView ( imageView );
        }
        viewFlipper.setFlipInterval ( 5000 );
        viewFlipper.setAutoStart ( true );
        Animation animation_slide_in= AnimationUtils.loadAnimation ( getApplicationContext (),R.anim.slide_in_right );
        Animation animation_slide_out= AnimationUtils.loadAnimation ( getApplicationContext (),R.anim.slide_out_right );
        viewFlipper.setInAnimation ( animation_slide_in );
        viewFlipper.setOutAnimation ( animation_slide_out );

    }

    private void ActionBar() {
        setSupportActionBar ( toolbar );
        getSupportActionBar ().setDisplayHomeAsUpEnabled ( true );
        toolbar.setNavigationIcon ( android.R.drawable.ic_menu_sort_by_size );
        toolbar.setNavigationOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer ( GravityCompat.START );
            }
        } );
    }


    private void Anhxa() {
        toolbar=findViewById ( R.id.toolbarmanhinhchinh );
        viewFlipper=findViewById ( R.id.viewflipper );
        recyclerViewmanhinhchinh=findViewById ( R.id.recyclerview );
        navigationView=findViewById ( R.id.navigationview );
        listViewmanhinhhinh=findViewById ( R.id.listviewmanhinhchinh );
        drawerLayout=findViewById ( R.id.drawerLayout );
        mangloaisp=new ArrayList<> (  );
        mangloaisp.add ( 0,new Loaisp ( 0,"Trang Chính","https://i.ytimg.com/vi/PAuMs3WCd68/maxresdefault.jpg" ) );
        loaispadappter=new Loaispadappter (mangloaisp,getApplicationContext ());
        listViewmanhinhhinh.setAdapter ( loaispadappter );
        mangsanpham=new ArrayList<> (  );
        sanphamAdapter=new SanphamAdapter ( this,mangsanpham );
        recyclerViewmanhinhchinh.setHasFixedSize ( true );
        recyclerViewmanhinhchinh.setLayoutManager ( new GridLayoutManager ( getApplicationContext (),2 ) );
        recyclerViewmanhinhchinh.setAdapter ( sanphamAdapter );
        if (manggiohang!=null){


        }else {

            manggiohang =new ArrayList<> (  );
        }
    }

}
