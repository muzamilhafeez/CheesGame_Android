package com.example.chessgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.chessgame.api.Api;
import com.example.chessgame.api.RetrofitClient;
import com.example.chessgame.databinding.ActivityFriendRequestScreenBinding;
import com.example.chessgame.models.GlobalData;
import com.example.chessgame.models.PlayOnlineDetails;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Friend_requestScreen extends AppCompatActivity {
    ActivityFriendRequestScreenBinding binding;

    Button playrandom;
    Button plywthFrnds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityFriendRequestScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        playrandom = findViewById(R.id.button6);
        playrandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Api apiObj = RetrofitClient.getInstance().getMyApi();
                int playerId = GlobalData.obj.id;
                apiObj.playonline(playerId).enqueue(new Callback<PlayOnlineDetails>() {
                    @Override
                    public void onResponse(Call<PlayOnlineDetails> call, Response<PlayOnlineDetails> response) {
                        if(response.isSuccessful()) {
                            GlobalData.matchId = response.body().id;
                            if(response.body().player2id !=-1){
                                //go to board
                                GlobalData.player1Id = response.body().player1id;
                                GlobalData.player2Id = response.body().player2id;
                                Intent intent = new Intent(Friend_requestScreen.this,ChessBoard_online.class);

                                startActivity(intent);

                            }else{
                                //go to waiting screen
                                Intent intent = new Intent(Friend_requestScreen.this,Waiting_Screen.class);

                                startActivity(intent);
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<PlayOnlineDetails> call, Throwable t) {

                    }
                });


            }
        });

        plywthFrnds = findViewById(R.id.button3);
        plywthFrnds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Friend_requestScreen.this,Friend_list.class);
                startActivity(intent);
            }
        });

        binding.button33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Friend_requestScreen.this,RequestAcceptScreen.class);
                startActivity(intent);

            }
        });


        binding.btnaddfriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Friend_requestScreen.this,FriendsData.class);
                startActivity(intent);

            }
        });



    }
}