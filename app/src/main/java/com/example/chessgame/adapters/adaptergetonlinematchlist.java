package com.example.chessgame.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chessgame.ChooseScreen;
import com.example.chessgame.ListSpectatormatches;
import com.example.chessgame.Login_Screen;
import com.example.chessgame.R;
import com.example.chessgame.Spectator_Screen;
import com.example.chessgame.models.GetOnlineMatchList;
import com.example.chessgame.viewholders.Friendreqlistviewholder;
import com.example.chessgame.viewholders.viewholdergetonlinematchlist;

import java.util.ArrayList;

public class adaptergetonlinematchlist extends RecyclerView.Adapter<viewholdergetonlinematchlist> {
    ArrayList<GetOnlineMatchList> data;
    Context context;

    public adaptergetonlinematchlist(ArrayList<GetOnlineMatchList> data, Context context) {
        this.data = data;
        this.context = context;
    }



    @NonNull
    @Override
    public viewholdergetonlinematchlist onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.item_list,parent,false);
        return new viewholdergetonlinematchlist(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholdergetonlinematchlist holder, int position) {
    holder.Player1Name.setText(data.get(position).p1name);
        holder.Player2Name.setText(data.get(position).p2name);
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "gooo",+ Toast.LENGTH_SHORT).show();

                ListSpectatormatches listSpectatormatches = (ListSpectatormatches) context;
                listSpectatormatches.moveToNext();

            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
