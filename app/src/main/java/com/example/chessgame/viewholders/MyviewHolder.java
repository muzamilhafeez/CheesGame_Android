package com.example.chessgame.viewholders;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chessgame.R;

public class MyviewHolder extends RecyclerView.ViewHolder {
    public ImageView boardimage;


    public MyviewHolder(@NonNull View itemView) {
        super(itemView);
        boardimage=itemView.findViewById(R.id.Lib);
    }
}
