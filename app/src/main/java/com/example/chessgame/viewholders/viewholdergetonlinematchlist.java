package com.example.chessgame.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chessgame.R;

public class viewholdergetonlinematchlist extends RecyclerView.ViewHolder {
    public TextView Player1Name;
    public TextView Player2Name;
    public CardView cardview;

    public viewholdergetonlinematchlist(@NonNull View itemView) {
        super(itemView);
        Player1Name=itemView.findViewById(R.id.textView0);
        Player2Name=itemView.findViewById(R.id.textView2);
        cardview=itemView.findViewById(R.id.card_list);
    }
}
