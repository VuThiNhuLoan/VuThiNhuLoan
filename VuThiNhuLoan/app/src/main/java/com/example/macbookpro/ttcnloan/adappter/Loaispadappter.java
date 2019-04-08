package com.example.macbookpro.ttcnloan.adappter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.macbookpro.ttcnloan.R;

import com.example.macbookpro.ttcnloan.model.Loaisp;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class Loaispadappter extends BaseAdapter {
    ArrayList<Loaisp> arrayListloaisp;
    Context context;

    public Loaispadappter(ArrayList<Loaisp> arrayListloaisp, Context context) {
        this.arrayListloaisp = arrayListloaisp;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListloaisp.size ();//dua gia tri trong mang
    }

    @Override
    public Object getItem(int i) {
        return arrayListloaisp.get ( i );
    }


    @Override
    public long getItemId(int i) {
        return i;
    }
    public  class ViewHolder{
    TextView txttenloaisp;
    ImageView imgloaisp;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder  =null;
        if (view==null){
            viewHolder=new ViewHolder ();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService (context. LAYOUT_INFLATER_SERVICE );
            view= (View) inflater.inflate ( R.layout.dong_list_view_loaisp,null);
            viewHolder.txttenloaisp=(TextView) view.findViewById ( R.id.textviewloaisp );
            viewHolder.imgloaisp=(ImageView) view.findViewById ( R.id.imgloaisp );
            view.setTag ( viewHolder );
        } else {

        }
        viewHolder= (ViewHolder) view.getTag ();
        Loaisp loaisp= (Loaisp) getItem ( i );
        viewHolder.txttenloaisp.setText ( loaisp.getTenloaisp () );
        Picasso.with ( context ).load ( loaisp.getHinhanhloaisp () )
                .placeholder ( R.drawable.ic_check_black_24dp )
                .error ( R.drawable.ic_error_black_24dp )
                .into ( viewHolder.imgloaisp  );
        return view;
    }
}
