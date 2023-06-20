package com.example.chessgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.chessgame.adapters.Frient_reqlistadapters;
import com.example.chessgame.adapters.adaptergetonlinematchlist;
import com.example.chessgame.api.Api;
import com.example.chessgame.api.RetrofitClient;
import com.example.chessgame.databinding.ActivitySpectatorScreenBinding;
import com.example.chessgame.models.GetOnlineMatchList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListSpectatormatches extends AppCompatActivity {
    RecyclerView rcv;
    adaptergetonlinematchlist adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_spectatormatches);

        RetrofitClient rete =RetrofitClient.getInstance();
        Api api = rete.getMyApi();
        api.getonlinematchlist().enqueue(new Callback<ArrayList<GetOnlineMatchList>>() {
            @Override
            public void onResponse(Call<ArrayList<GetOnlineMatchList>> call, Response<ArrayList<GetOnlineMatchList>> response) {
                rcv = findViewById(R.id.list_spec);
                rcv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter =new adaptergetonlinematchlist(response.body(),ListSpectatormatches.this);
                rcv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<GetOnlineMatchList>> call, Throwable t) {

            }
        });
    }

    public void moveToNext() {
        Intent intent = new Intent(this, Spectator_Screen.class);
        startActivity(intent);
    }
}