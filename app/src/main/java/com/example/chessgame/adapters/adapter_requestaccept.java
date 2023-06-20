package com.example.chessgame.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chessgame.ListSpectatormatches;
import com.example.chessgame.R;
import com.example.chessgame.api.Api;
import com.example.chessgame.api.RetrofitClient;
import com.example.chessgame.models.GetOnlineMatchList;
import com.example.chessgame.models.GetPendingRequests;
import com.example.chessgame.models.GlobalData;
import com.example.chessgame.viewholders.viewholder_requestaccept;
import com.example.chessgame.viewholders.viewholdergetonlinematchlist;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class adapter_requestaccept extends RecyclerView.Adapter<viewholder_requestaccept> {

    ArrayList<GetPendingRequests> data;
    Context context;

    public adapter_requestaccept(ArrayList<GetPendingRequests> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder_requestaccept onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.item_request_acceptlist,parent,false);
        return new viewholder_requestaccept(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder_requestaccept holder, int position) {
        holder.PlayerId.setText(data.get(position).request_from+"");
        holder.PlayerName.setText(data.get(position).name);
        holder.btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                RetrofitClient rete =RetrofitClient.getInstance();
                Api api = rete.getMyApi();
                api.acceptfriendrequest_call(GlobalData.obj.id,data.get(position).request_from,1).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(context, response.body()+"accepted"+data.get(position).request_from+"  "+GlobalData.obj.id, Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });

            }
        });

//        holder.btnDecline.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//
//                RetrofitClient rete =RetrofitClient.getInstance();
//                Api api = rete.getMyApi();
//                api.acceptfriendrequest_call(GlobalData.obj.id,0,0).enqueue(new Callback<String>() {
//                    @Override
//                    public void onResponse(Call<String> call, Response<String> response) {
//                        Toast.makeText(context, response.body()+"rejected"+data.get(position).request_from+"  "+GlobalData.obj.id, Toast.LENGTH_SHORT).show();
//
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<String> call, Throwable t) {
//
//                    }
//                });
//
//            }
//        });



    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
