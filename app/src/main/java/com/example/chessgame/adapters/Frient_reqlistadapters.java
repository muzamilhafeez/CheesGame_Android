package com.example.chessgame.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chessgame.R;
import com.example.chessgame.api.Api;
import com.example.chessgame.api.RetrofitClient;
import com.example.chessgame.models.GlobalData;
import com.example.chessgame.models.modelfriendlist;
import com.example.chessgame.viewholders.Friendreqlistviewholder;
import com.example.chessgame.viewholders.ViewholderList;

import java.io.ObjectInputStream;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Frient_reqlistadapters extends RecyclerView.Adapter<Friendreqlistviewholder> {
    ArrayList<modelfriendlist> data;

    public Frient_reqlistadapters(ArrayList<modelfriendlist> data, Context context) {
        this.data = data;
        this.context = context;
        Toast.makeText(context, ""+data.size(), Toast.LENGTH_SHORT).show();
    }

    Context context;
    @NonNull
    @Override
    public Friendreqlistviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.frienditemlist,parent,false);
        return new Friendreqlistviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Friendreqlistviewholder holder, int position) {

        holder.Playerid.setText(data.get(position).getId()+"");
        holder.PlayerName.setText(data.get(position).getName());
        //GlobalData.players=data.get(position).getId();

        holder.plussign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RetrofitClient rete =RetrofitClient.getInstance();
                Api api = rete.getMyApi();
                api.SendRequest(GlobalData.obj.id,data.get(position).getId()).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(context, ""+response.body(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
                Toast.makeText(context, ""+data.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
