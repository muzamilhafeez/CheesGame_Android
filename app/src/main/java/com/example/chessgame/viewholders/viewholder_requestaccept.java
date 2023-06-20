package com.example.chessgame.viewholders;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chessgame.R;

public class viewholder_requestaccept extends RecyclerView.ViewHolder {
    public TextView PlayerId;
    public TextView PlayerName;
    public Button btnAccept;
    public Button btnDecline;

    public viewholder_requestaccept(@NonNull View itemView) {
        super(itemView);
        PlayerId = itemView.findViewById(R.id.textView5);
        PlayerName = itemView.findViewById(R.id.textView6);
        btnAccept = itemView.findViewById(R.id.button7);
        btnDecline = itemView.findViewById(R.id.button10);
    }
}
