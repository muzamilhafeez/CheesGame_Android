package com.example.chessgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.chessgame.adapters.Frient_reqlistadapters;
import com.example.chessgame.adapters.adapter_requestaccept;
import com.example.chessgame.adapters.adaptergetonlinematchlist;
import com.example.chessgame.api.Api;
import com.example.chessgame.api.RetrofitClient;
import com.example.chessgame.models.GetPendingRequests;
import com.example.chessgame.models.GlobalData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RequestAcceptScreen extends AppCompatActivity {
    RecyclerView rcv;
    adapter_requestaccept adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_accept_screen);

        RetrofitClient rete =RetrofitClient.getInstance();
        Api api = rete.getMyApi();
//        Toast.makeText(this, ""+GlobalData.obj.id, Toast.LENGTH_SHORT).show();
        api.GET_PENDING_REQUESTS_CALL(GlobalData.obj.id).enqueue(new Callback<ArrayList<GetPendingRequests>>() {
            @Override
            public void onResponse(Call<ArrayList<GetPendingRequests>> call, Response<ArrayList<GetPendingRequests>> response) {
                if(response.code()==200){
                    rcv = findViewById(R.id.list_requestaccept);
                    rcv.setLayoutManager(new LinearLayoutManager(RequestAcceptScreen.this));

                    adapter =new adapter_requestaccept(response.body(), RequestAcceptScreen.this);
                    rcv.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<ArrayList<GetPendingRequests>> call, Throwable t) {

            }
        });
    }
}