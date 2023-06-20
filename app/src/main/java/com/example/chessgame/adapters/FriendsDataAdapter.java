package com.example.chessgame.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chessgame.R;
import com.example.chessgame.models.GetFriendsdatamodel;
import com.example.chessgame.models.GetOnlineMatchList;
import com.example.chessgame.viewholders.FriendsDataViewholders;
import com.example.chessgame.viewholders.viewholdergetonlinematchlist;

import java.util.ArrayList;

public class FriendsDataAdapter extends RecyclerView.Adapter<FriendsDataViewholders> {

    ArrayList<GetFriendsdatamodel> data;
    Context context;

    public FriendsDataAdapter(ArrayList<GetFriendsdatamodel> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public FriendsDataViewholders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.item_friendsdata,parent,false);
        return new FriendsDataViewholders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendsDataViewholders holder, int position) {

        holder.friendid.setText(data.get(position).id);
        holder.friendname.setText(data.get(position).name);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
