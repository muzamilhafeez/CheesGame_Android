package com.example.chessgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.example.chessgame.api.Api;
import com.example.chessgame.api.RetrofitClient;
import com.example.chessgame.models.GlobalData;
import com.example.chessgame.models.playerjoineddetails;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Waiting_Screen extends AppCompatActivity {


    private Handler handler;
    private  Runnable runnable;

    private void callApi(){
        Api api = RetrofitClient.getInstance().getMyApi();
        api.playerjoined(GlobalData.matchId).enqueue(new Callback<playerjoineddetails>() {
            @Override
            public void onResponse(Call<playerjoineddetails> call, Response<playerjoineddetails> response) {
                if(response.isSuccessful()){
                    playerjoineddetails details = response.body();
                    if(details.player2id != -1){
                        //go to board
                        GlobalData.player1Id = response.body().player1id;
                        GlobalData.player2Id = response.body().player2id;
                        Intent intent = new Intent(Waiting_Screen.this,ChessBoard_online.class);

                        startActivity(intent);
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<playerjoineddetails> call, Throwable t) {

            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_screen);

        handler = new Handler(Looper.getMainLooper());
        runnable = new Runnable() {
            @Override
            public void run() {
                callApi();
                handler.postDelayed(this, 5000);
            }
        };
        handler.postDelayed(runnable, 5000);


    }
    protected  void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}