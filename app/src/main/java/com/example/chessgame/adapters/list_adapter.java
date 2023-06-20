package com.example.chessgame.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chessgame.R;
import com.example.chessgame.models.modelfriendlist;
import com.example.chessgame.viewholders.ViewholderList;

import java.util.ArrayList;

public class list_adapter extends  RecyclerView.Adapter<ViewholderList> {
    ArrayList<modelfriendlist> data;
    Context context;

    public list_adapter(ArrayList<modelfriendlist> data) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewholderList onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.item_list,parent,false);
        return new ViewholderList(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewholderList holder, int position) {
        holder.t1.setText(data.get(position).getName());
        //holder.t2.setText(data.get(position).getText2());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
