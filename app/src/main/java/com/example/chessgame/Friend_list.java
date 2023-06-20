package com.example.chessgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.chessgame.adapters.Frient_reqlistadapters;
import com.example.chessgame.adapters.list_adapter;
import com.example.chessgame.api.Api;
import com.example.chessgame.api.RetrofitClient;
import com.example.chessgame.models.GlobalData;
import com.example.chessgame.models.UserDetails;
import com.example.chessgame.models.modelfriendlist;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Friend_list extends AppCompatActivity {

    RecyclerView rcv;
    Frient_reqlistadapters adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);
        RetrofitClient rete =RetrofitClient.getInstance();
        Api api = rete.getMyApi();
        api.getplayerdata(GlobalData.obj.id).enqueue(new Callback<ArrayList<modelfriendlist>>() {
    @Override
    public void onResponse(Call<ArrayList<modelfriendlist>> call, Response<ArrayList<modelfriendlist>> response) {
        if(response.code()==200){
            rcv = findViewById(R.id.rrr);
            rcv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            adapter =new Frient_reqlistadapters(response.body(),getApplicationContext());
            rcv.setAdapter(adapter);

        }
    }

    @Override
    public void onFailure(Call<ArrayList<modelfriendlist>> call, Throwable t) {

    }
});

    }








    }
