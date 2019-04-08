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

public class SachGiaoKhoaAdapter extends BaseAdapter {
    Context context;

    public SachGiaoKhoaAdapter(Context context, ArrayList<Sanpham> arraysgk) {
        this.context = context;
        this.arraysgk = arraysgk;
    }

    ArrayList<Sanpham> arraysgk;



    @Override
    public int getCount() {
        return arraysgk.size ();
    }

    @Override
    public Object getItem(int i) {
        return arraysgk.get ( i );
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public  class  ViewHolder{
        public TextView txttensgk ,txtgiasgk,txtmotasgk;
        public ImageView imgsgk;


    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if (view ==null){
            viewHolder= new SachGiaoKhoaAdapter.ViewHolder ();
            LayoutInflater inflater=(LayoutInflater) context.getSystemService ( Context.LAYOUT_INFLATER_SERVICE );
            view=inflater.inflate ( R.layout.dong_sachgiaokhoa,null  );
            viewHolder.txttensgk=view.findViewById ( R.id.txttensachgk );
            viewHolder.txtgiasgk=view.findViewById ( R.id.txtgiasgk );
            viewHolder.txtmotasgk=view.findViewById ( R.id.txtmotasgk );
            viewHolder.imgsgk=(ImageView) view.findViewById ( R.id.imgsachgk);
            view.setTag ( viewHolder );
        }
        else {
            viewHolder=(SachGiaoKhoaAdapter.ViewHolder) view.getTag ();
        }
        Sanpham sanpham=(Sanpham) getItem ( i );
        viewHolder.txttensgk.setText ( sanpham.getTensanpam () );
        DecimalFormat decimalFormat=new DecimalFormat ( "###,###,###" );
        viewHolder.txtgiasgk.setText ( "Gia: "+ decimalFormat.format ( sanpham.getGiasanpham ())+"VND" ) ;
        viewHolder.txtmotasgk.setMaxLines ( 2 );// TRUYEN VAO  2 DONG xuong dong
        viewHolder.txtmotasgk.setEllipsize ( TextUtils.TruncateAt.END );
        viewHolder.txtmotasgk.setText ( sanpham.getMotasanpham () );
        Picasso.with ( context ).load ( sanpham.getHinhanhsanpham () )
                .placeholder ( R.drawable.ic_check_black_24dp )
                .error ( R.drawable.ic_error_black_24dp )
                .into ( viewHolder.imgsgk );
        return view;
    }
}
