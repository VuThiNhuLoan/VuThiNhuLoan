package com.example.macbookpro.ttcnloan.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.macbookpro.ttcnloan.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.macbookpro.ttcnloan.adappter.SachGiaoKhoaAdapter;
import com.example.macbookpro.ttcnloan.model.Sanpham;
import com.example.macbookpro.ttcnloan.until.CheckConnection;
import com.example.macbookpro.ttcnloan.until.Server;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SachGiaoKhoa extends AppCompatActivity {
    Toolbar toolbarsgk;
    ListView lvsgk;
    SachGiaoKhoaAdapter sachGiaoKhoaAdapter;
    ArrayList<Sanpham> mangsgk;
    int idsgk = 0;
    int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_sach_giao_khoa );

        if (CheckConnection.haveNetworkConnection ( getApplicationContext () )) {
            GetIdloaisp ();
            ActionToolbar ();
            Anhxa ();
            GetData ( page );

        } else {
            CheckConnection.ShowToast_short ( getApplicationContext (), "Ban hay kiem tra Internert" );
            finish ();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate ( R.menu.menu, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId ()) {
            case R.id.menugiohang:
                Intent intent = new Intent ( getApplicationContext (), com.example.macbookpro.ttcnloan.activity.Giohang.class );
                startActivity ( intent );
        }
        return super.onOptionsItemSelected ( item );
    }

    private void GetData(int Page) {
        RequestQueue requestQueue = Volley.newRequestQueue ( getApplicationContext () );
        String duongdan = Server.Duongdantruyentranh + String.valueOf ( Page );
        StringRequest stringRequest = new StringRequest ( Request.Method.POST, duongdan, new Response.Listener<String> () {
            @Override
            public void onResponse(String response) {
                int id = 0;
                String TenSgk = "";
                int GiaSgk = 0;
                String Hinhanhsgk = "";
                String Motasgk = "";
                int Idspsgk = 0;
                if (response != null && response.length () != 2) {

                    try {
                        JSONArray jsonArray = new JSONArray ( response );
                        for (int i = 0; i < jsonArray.length (); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject ( i );
                            id = jsonObject.getInt ( "id" );
                            TenSgk = jsonObject.getString ( "tensp" );
                            GiaSgk = jsonObject.getInt ( "giasp" );
                            Hinhanhsgk = jsonObject.getString ( "hinhanhsp" );
                            Motasgk = jsonObject.getString ( "motasp" );
                            Idspsgk = jsonObject.getInt ( "idsanpham" );
                            mangsgk.add ( new Sanpham ( id, TenSgk, GiaSgk, Hinhanhsgk, Motasgk, Idspsgk ) );
                            sachGiaoKhoaAdapter.notifyDataSetChanged ();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace ();
                    }
                }


            }
        }, new Response.ErrorListener () {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        } ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String> ();
                param.put ( "idsanpham", String.valueOf ( idsgk ) );
                return param;
            }
        };
        requestQueue.add ( stringRequest );
    }

    private void ActionToolbar() {
        setSupportActionBar ( toolbarsgk );

        getSupportActionBar ().setDisplayHomeAsUpEnabled ( true );
        toolbarsgk.setNavigationOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                finish ();
            }
        } );


    }

    private void GetIdloaisp() {
        idsgk = getIntent ().getIntExtra ( "idloaisanpham", -1 );

    }

    private void Anhxa() {
        toolbarsgk = findViewById ( R.id.toolbarsgk );
        lvsgk = findViewById ( R.id.listviewsgk );
        mangsgk = new ArrayList<> ();
        sachGiaoKhoaAdapter = new SachGiaoKhoaAdapter ( getApplicationContext (), mangsgk );
        lvsgk.setAdapter ( sachGiaoKhoaAdapter );
    }

}
