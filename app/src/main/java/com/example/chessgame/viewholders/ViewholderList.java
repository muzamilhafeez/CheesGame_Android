package com.example.chessgame.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chessgame.R;

public class ViewholderList extends RecyclerView.ViewHolder {
   public TextView t1;
    public TextView t2;
    public ViewholderList(@NonNull View itemView) {
        super(itemView);
        t1=itemView.findViewById(R.id.textView0);
        t2=itemView.findViewById(R.id.textView2);
    }
}
