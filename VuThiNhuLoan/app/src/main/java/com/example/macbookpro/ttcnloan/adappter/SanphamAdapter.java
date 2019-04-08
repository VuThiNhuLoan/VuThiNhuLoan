package com.example.macbookpro.ttcnloan.adappter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.macbookpro.ttcnloan.R;

import com.example.macbookpro.ttcnloan.activity.ChiTietSanPham;
import com.example.macbookpro.ttcnloan.activity.MainActivity;
import com.example.macbookpro.ttcnloan.inteface.SetOnclick;
import com.example.macbookpro.ttcnloan.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SanphamAdapter extends RecyclerView.Adapter<SanphamAdapter.ItemHolder> {
   MainActivity context;
   ArrayList<Sanpham> arraysanpham;

    public SanphamAdapter(MainActivity context, ArrayList<Sanpham> arraysanpham) {
        this.context = context;
        this.arraysanpham = arraysanpham;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from (parent.getContext()).inflate ( R.layout.dong_sanphammoinhat,null );
        ItemHolder itemHolder=new ItemHolder (  v );
        return itemHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, final int position) {
    Sanpham sanpham=arraysanpham.get ( position );
    holder.txttensanpham.setText ( sanpham.getTensanpam () );
        DecimalFormat decimalFormat=new DecimalFormat ( "###,###,###" );
        holder.giasanpham.setText ( "Gia: "+ decimalFormat.format ( sanpham.getGiasanpham ())+"VND" ) ;
        Picasso.with ( context ).load ( sanpham.getHinhanhsanpham () )
                . placeholder ( R.drawable.ic_check_black_24dp )
                .error ( R.drawable.ic_error_black_24dp )
                .into ( holder.imghinhsanpham );
        holder.setSetOnclick(new SetOnclick() {
            @Override
            public void OnClick(View view, int posstion) {
                Log.d("CHUYENTRANG",arraysanpham.get(posstion).getTensanpam()+"");
                Intent i = new Intent(context,ChiTietSanPham.class);
                i.putExtra("thongtinsanpham",arraysanpham.get(posstion));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arraysanpham.size ();
    }

    public  class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imghinhsanpham;
        public TextView txttensanpham,giasanpham;
        private SetOnclick setOnclick;

        public void setSetOnclick(SetOnclick setOnclick) {
            this.setOnclick = setOnclick;
        }

        public ItemHolder(@NonNull View itemView) {
            super ( itemView );
            imghinhsanpham= itemView.findViewById ( R.id.imageviewsanpham );
            txttensanpham= itemView.findViewById ( R.id.textviewtensanpham );
            giasanpham=itemView.findViewById ( R.id.txtgiasp );
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            this.setOnclick.OnClick(v,getAdapterPosition());
        }
    }
}
