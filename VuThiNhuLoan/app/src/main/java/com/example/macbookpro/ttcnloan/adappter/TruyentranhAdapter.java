package com.example.macbookpro.ttcnloan.adappter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.macbookpro.ttcnloan.R;

import com.example.macbookpro.ttcnloan.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TruyentranhAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> arraytruyentranh;

    public TruyentranhAdapter(Context context, ArrayList<Sanpham> arraytruyentranh) {
        this.context = context;
        this.arraytruyentranh = arraytruyentranh;
    }

    @Override
    public int getCount() {
        return arraytruyentranh.size ();
    }

    @Override
    public Object getItem(int i) {
        return arraytruyentranh.get ( i );
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public static class  ViewHolder{
        public TextView txttentruyentranh ,txtgiatruyentranh,txtmotatruyentranh;
        public ImageView imgtruyentranh;


    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if (view ==null){
            viewHolder=new ViewHolder ();
            LayoutInflater inflater=(LayoutInflater) context.getSystemService ( Context.LAYOUT_INFLATER_SERVICE );
            view=inflater.inflate ( R.layout.dong_truyentranh,null  );
            viewHolder.txttentruyentranh=view.findViewById ( R.id.txttruyentranh );
            viewHolder.txtgiatruyentranh=view.findViewById ( R.id.txtgiatruyentranh );
            viewHolder.txtmotatruyentranh=view.findViewById ( R.id.txtmotatruyentranh );
            viewHolder.imgtruyentranh=(ImageView) view.findViewById ( R.id.hihihihi);
            view.setTag ( viewHolder );
        }
        else {
            viewHolder=(ViewHolder) view.getTag ();
        }
        Sanpham sanpham=(Sanpham) getItem ( i );
        viewHolder.txttentruyentranh.setText ( sanpham.getTensanpam () );
        DecimalFormat decimalFormat=new DecimalFormat ( "###,###,###" );
        viewHolder.txtgiatruyentranh.setText ( "Gia: "+ decimalFormat.format ( sanpham.getGiasanpham ())+"VND" ) ;
        viewHolder.txttentruyentranh.setMaxLines ( 2 );// TRUYEN VAO  2 DONG
        viewHolder.txtmotatruyentranh.setEllipsize ( TextUtils.TruncateAt.END );
        viewHolder.txtmotatruyentranh.setText ( sanpham.getMotasanpham () );
        Picasso.with ( context ).load ( sanpham.getHinhanhsanpham () )
              .placeholder ( R.drawable.ic_check_black_24dp )
               .error ( R.drawable.ic_error_black_24dp )
                .into ( viewHolder.imgtruyentranh );
        return view;
    }
}
