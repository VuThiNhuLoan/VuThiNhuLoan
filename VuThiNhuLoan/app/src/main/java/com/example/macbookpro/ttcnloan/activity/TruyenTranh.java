package com.example.macbookpro.ttcnloan.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;

import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.macbookpro.ttcnloan.R;
import com.example.macbookpro.ttcnloan.adappter.TruyentranhAdapter;
import com.example.macbookpro.ttcnloan.model.Sanpham;
import com.example.macbookpro.ttcnloan.until.CheckConnection;
import com.example.macbookpro.ttcnloan.until.Server;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TruyenTranh extends AppCompatActivity {
    Toolbar toolbartt;
    ListView lvtt;
    TruyentranhAdapter truyentranhAdapter;
    ArrayList<Sanpham> mangtt;
    int idtt=0;
    int page=1;
    View footerview;
    boolean isLoading=false;
    boolean limitdata=false;
    mHandLer  mHandLer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_truyen_tranh );
        Anhxa();


        if (CheckConnection.haveNetworkConnection ( getApplicationContext () )){
            GetIdloaisp();
            ActionToolbar();
//            page=getIntent().getIntExtra( "page",1 );
            Log.d("AA",page+"");
            GetData(page);
//            LoadMoreData();

            
        } else {
            CheckConnection.ShowToast_short ( getApplicationContext (),"ban hay kiem tra internet" );
            finish ();
        }
        lvtt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(TruyenTranh.this,ChiTietSanPham.class);
                i.putExtra("thongtinsanpham",mangtt.get(position));
                startActivity(i);
            }
        });


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

    private void LoadMoreData() {

        lvtt.setOnItemClickListener ( new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent=new Intent ( getApplicationContext (),ChiTietSanPham.class );
                intent.putExtra ( "thongtinsanpham",mangtt.get ( i ) );
                startActivity ( intent );
            }
        } );
        lvtt.setOnScrollListener ( new AbsListView.OnScrollListener () {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int FirstItem, int VisibleItem, int TotalItem) {
                if (FirstItem + VisibleItem == TotalItem && TotalItem!=0 && isLoading==false && limitdata==false){
                    isLoading=true;

                    ThreadData threadData=new ThreadData ();
                    threadData.start ();
                }
            }
        } );
    }

    private void GetData(int Page) {
        RequestQueue requestQueue  =Volley.newRequestQueue ( getApplicationContext () );
        String duongdan =Server.Duongdantruyentranh +String.valueOf ( Page );
        StringRequest stringRequest= new StringRequest ( Request.Method.POST, duongdan, new Response.Listener<String> () {
            @Override
            public void onResponse(String response) {
                int id=0;
                String Tentt="";
                int Giatt=0;
                String Hinhanhtt="";
                String Mota="";
                int Idsptt=0;
                if (response!=null&&response.length ()!=2){
//                    lvtt.removeFooterView ( footerview );
                    try {
                        JSONArray jsonArray=new JSONArray ( response );
                        for (int i=0;i<jsonArray.length ();i++){
                            JSONObject jsonObject=jsonArray.getJSONObject ( i );
                            id=jsonObject.getInt ( "id" );
                            Tentt=jsonObject.getString ( "tensp" );
                            Giatt=jsonObject.getInt ( "giasp" );
                            Hinhanhtt=jsonObject.getString ( "hinhanhsp" );
                            Mota=jsonObject.getString ( "motasp" );
                            Idsptt=jsonObject.getInt ( "idsanpham" );
                            mangtt.add ( new Sanpham ( id,Tentt,Giatt,Hinhanhtt,Mota,Idsptt ) );
                            truyentranhAdapter.notifyDataSetChanged ();
                        }
                    }
                    catch (JSONException e){
                        e.printStackTrace ();
                    }
                }
                else{
                    limitdata=true;
                    lvtt.removeFooterView ( footerview );
                    CheckConnection.ShowToast_short ( getApplicationContext (),"da het du lieu" );
                }

            }
        }, new Response.ErrorListener () {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        } ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
               HashMap<String,String>param=new HashMap<String,String> (  );
               param.put ( "idsanpham",String.valueOf ( idtt ) );
                return param;
            }
        };
        requestQueue.add ( stringRequest );
    }



    private void ActionToolbar() {
     setSupportActionBar (toolbartt );

     getSupportActionBar ().setDisplayHomeAsUpEnabled ( true );
     toolbartt.setNavigationOnClickListener ( new View.OnClickListener () {
           @Override
           public void onClick(View view) {
                finish ();
           }
       } );


}




    private void GetIdloaisp() {
        idtt=getIntent ().getIntExtra ( "idloaisanpham",6 );
//        idtt=getIntent().getIntExtra( "page",1 );
        switch (idtt){
            case 1:
                toolbartt.setTitle ( "Truyện Tranh" );
                break;
            case 2:
                toolbartt.setTitle ( "Sách Giáo Khoa" );
                break;
            case 3:
                toolbartt.setTitle ( "Trinh Thám" );
                break;
            case 4:
                toolbartt.setTitle ( "Khoa Học" );
                break;
            case 5:
                toolbartt.setTitle ( "Kỹ Thuật" );
                break;
            case 6:
                toolbartt.setTitle ( "Test " );
                break;
        }
        Log.d ( "giatriloaisanpham",idtt+" " );
    }

    private void Anhxa() {
        toolbartt= findViewById ( R.id.toolbartruyentranh );
        lvtt=findViewById ( R.id.listviewtruyentranh );
        mangtt=new ArrayList<> (  );
        truyentranhAdapter=new TruyentranhAdapter ( getApplicationContext (),mangtt );
        lvtt.setAdapter ( truyentranhAdapter );
        LayoutInflater inflater = (LayoutInflater) getSystemService ( LAYOUT_INFLATER_SERVICE );
        footerview =inflater.inflate ( R.layout.protressbar ,null);
        mHandLer= new mHandLer ();
    }
    public  class  mHandLer extends  Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    lvtt.addFooterView ( footerview );
                    break;
                case 1:
                    GetData(++page);
                    isLoading=false;
                    break;
            }
            super.handleMessage ( msg );
        }
    }
    public  class  ThreadData extends  Thread{
    public  void  run(){
    mHandLer.sendEmptyMessage ( 0 );
    try {
        Thread.sleep ( 3000);
    } catch (InterruptedException e) {
        e.printStackTrace ();
    }
    Message message=mHandLer.obtainMessage (1);
    mHandLer.sendMessage (message);
    super.run ();
}
    }
}
