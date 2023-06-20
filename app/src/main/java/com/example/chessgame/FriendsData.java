package com.example.chessgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.chessgame.adapters.FriendsDataAdapter;
import com.example.chessgame.adapters.adapter_requestaccept;
import com.example.chessgame.api.Api;
import com.example.chessgame.api.RetrofitClient;
import com.example.chessgame.models.GetFriendsdatamodel;
import com.example.chessgame.models.GlobalData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendsData extends AppCompatActivity {
    RecyclerView rcv;
    FriendsDataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_data);
        RetrofitClient rete =RetrofitClient.getInstance();
        Api api = rete.getMyApi();
        api.getFriendsdata_call(GlobalData.obj.id).enqueue(new Callback<ArrayList<GetFriendsdatamodel>>() {
            @Override
            public void onResponse(Call<ArrayList<GetFriendsdatamodel>> call, Response<ArrayList<GetFriendsdatamodel>> response) {

                if(response.code()==200){
                    Toast.makeText(FriendsData.this, " xx"+response.body(), Toast.LENGTH_SHORT).show();

                    rcv = findViewById(R.id.list_friends);
                    rcv.setLayoutManager(new LinearLayoutManager(FriendsData.this));

                    adapter =new FriendsDataAdapter(response.body(), FriendsData.this);
                    rcv.setAdapter(adapter);
                    Toast.makeText(FriendsData.this, " yy"+response.body(), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<ArrayList<GetFriendsdatamodel>> call, Throwable t) {

            }
        });
    }
}