package com.example.macbookpro.ttcnloan.adappter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.macbookpro.ttcnloan.R;
import com.example.macbookpro.ttcnloan.activity.MainActivity;
import com.example.macbookpro.ttcnloan.model.Giohang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GioHangAdapter extends BaseAdapter {
    Context context;
    ArrayList<Giohang> arraygiohang;

    public GioHangAdapter(Context context, ArrayList<Giohang> arraygiohang) {
        this.context = context;
        this.arraygiohang = arraygiohang;
    }

    @Override
    public int getCount() {
        return arraygiohang.size ();
    }

    @Override
    public Object getItem(int i) {
        return arraygiohang.get ( i );
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHoder {
        public TextView txttengiohang, txtgiagiohang;
        public ImageView imggiohang;
        public Button btnminus, btnvalues, btnplus;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHoder viewHoder = null;
        if (view == null) {
            viewHoder = new ViewHoder ();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService ( Context.LAYOUT_INFLATER_SERVICE );
            view = inflater.inflate ( R.layout.dong_giohang, null );
            viewHoder.txttengiohang = view.findViewById ( R.id.txttengiohang );
            viewHoder.txtgiagiohang = view.findViewById ( R.id.txtgiagiohang );
            viewHoder.imggiohang = view.findViewById ( R.id.imggiohang );
            viewHoder.btnminus = view.findViewById ( R.id.btnminius );
            viewHoder.btnvalues = view.findViewById ( R.id.btnvalues );
            viewHoder.btnplus = view.findViewById ( R.id.btnplus );
            view.setTag ( viewHoder );

        } else {
            viewHoder = (ViewHoder) view.getTag ();

        }
        Giohang giohang = (Giohang) getItem ( i );

        viewHoder.txttengiohang.setText ( giohang.getTensp () );
        DecimalFormat decimalFormat = new DecimalFormat ( "###,###,###" );
        viewHoder.txtgiagiohang.setText ( decimalFormat.format ( giohang.getGiasp () ) + "VND" );
        Picasso.with ( context ).load ( giohang.getHinhsp () )
                .placeholder ( R.drawable.ic_check_black_24dp )
                .error ( R.drawable.ic_error_black_24dp )
                .into ( viewHoder.imggiohang );
        viewHoder.btnvalues.setText ( giohang.getSoluongsp () + "" );
        int sl = Integer.parseInt ( viewHoder.btnvalues.getText ().toString () );
        if (sl >= 10) {
            viewHoder.btnplus.setVisibility ( View.INVISIBLE );
            viewHoder.btnminus.setVisibility ( View.VISIBLE );
        } else if (sl <= 1) {
            viewHoder.btnminus.setVisibility ( View.INVISIBLE );
        } else if (sl >= 1) {
            viewHoder.btnminus.setVisibility ( View.VISIBLE );
            viewHoder.btnplus.setVisibility ( View.VISIBLE );
        }

        final ViewHoder finalViewHoder = viewHoder;

        viewHoder.btnplus.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                int slmoinhat = Integer.parseInt ( finalViewHoder.btnvalues.getText ().toString () ) + 1;
                int slht = MainActivity.manggiohang.get ( i ).getSoluongsp ();
                long giaht = MainActivity.manggiohang.get ( i ).getGiasp ();

                MainActivity.manggiohang.get ( i ).setSoluongsp ( slmoinhat );
                long giamoinhat = (giaht * slmoinhat) / slht;
                MainActivity.manggiohang.get ( i ).setGiasp ( giamoinhat );
                DecimalFormat decimalFormat = new DecimalFormat ( "###,###,###" );
                finalViewHoder.txtgiagiohang.setText ( decimalFormat.format ( giamoinhat ) + "VND" );
                com.example.macbookpro.ttcnloan.activity.Giohang.EventUntil ();
                if (slmoinhat > 9) {
                    finalViewHoder.btnplus.setVisibility ( View.INVISIBLE );
                    finalViewHoder.btnminus.setVisibility ( View.VISIBLE );
                    finalViewHoder.btnvalues.setText ( String.valueOf ( slmoinhat ) );
                } else {
                    finalViewHoder.btnminus.setVisibility ( View.VISIBLE );
                    finalViewHoder.btnplus.setVisibility ( View.VISIBLE );
                    finalViewHoder.btnvalues.setText ( String.valueOf ( slmoinhat ) );
                }
            }
        } );
        viewHoder.btnminus.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                int slmoinhat = Integer.parseInt ( finalViewHoder.btnvalues.getText ().toString () ) - 1;
                int slht = MainActivity.manggiohang.get ( i ).getSoluongsp ();
                long giaht = MainActivity.manggiohang.get ( i ).getGiasp ();

                MainActivity.manggiohang.get ( i ).setSoluongsp (slmoinhat);
                long giamoinhat=(giaht * slmoinhat)/slht;
                MainActivity.manggiohang.get ( i ).setGiasp ( giamoinhat );
                DecimalFormat decimalFormat = new DecimalFormat ( "###,###,###" );
                finalViewHoder.txtgiagiohang.setText ( decimalFormat.format ( giamoinhat ) + "VND" );
                com.example.macbookpro.ttcnloan.activity.Giohang.EventUntil ();
                if (slmoinhat<2){
                    finalViewHoder.btnminus.setVisibility ( View.INVISIBLE );
                    finalViewHoder.btnplus.setVisibility ( View.VISIBLE );
                    finalViewHoder.btnvalues.setText ( String.valueOf ( slmoinhat ));
                }else {
                    finalViewHoder.btnminus.setVisibility ( View.VISIBLE );
                    finalViewHoder.btnplus.setVisibility ( View.VISIBLE );
                    finalViewHoder.btnvalues.setText ( String.valueOf ( slmoinhat ));
                }
            }
        } );
        return view;
    }
}
