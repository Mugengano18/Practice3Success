package com.android1.practice3;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.awt.font.TextAttribute;

public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    ImageView image;
    TextView name,price;
    ItemClickListener itemClickListener;
    MyHolder(@NonNull View itemView) {
        super(itemView);
        this.image=itemView.findViewById(R.id.image1);
        this.name=itemView.findViewById(R.id.Menuname);
        this.price=itemView.findViewById(R.id.money);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClickListener(v,getLayoutPosition());
    }
    public void setItemClickListener(ItemClickListener listener){
        this.itemClickListener=listener;
    }
}
