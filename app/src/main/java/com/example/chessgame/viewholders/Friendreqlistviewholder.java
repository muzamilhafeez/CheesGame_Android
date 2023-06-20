package com.example.chessgame.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chessgame.R;

public class Friendreqlistviewholder extends RecyclerView.ViewHolder {

    public ImageView plussign;
    public TextView Playerid;
    public TextView PlayerName;
    public Friendreqlistviewholder(@NonNull View itemView) {
        super(itemView);
        plussign=itemView.findViewById(R.id.imageView2);
        Playerid=itemView.findViewById(R.id.textView4);
        PlayerName=itemView.findViewById(R.id.textView3);


    }
}
