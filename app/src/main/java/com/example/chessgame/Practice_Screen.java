package com.example.chessgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.chessgame.api.Api;
import com.example.chessgame.api.RetrofitClient;
import com.example.chessgame.databinding.ActivityPracticeScreenBinding;
import com.example.chessgame.models.GlobalData;
import com.example.chessgame.models.PlayOnlineDetails;
import com.example.chessgame.models.playerjoineddetails;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Practice_Screen extends AppCompatActivity {
    ActivityPracticeScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPracticeScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnplyonlne.setOnClickListener(new View.OnClickListener() {
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
                                Intent intent = new Intent(Practice_Screen.this,ChessBoard_online.class);

                                startActivity(intent);

                            }else{
                                //go to waiting screen
                                Intent intent = new Intent(Practice_Screen.this,Waiting_Screen.class);

                                startActivity(intent);
                            }
                            Toast.makeText(Practice_Screen.this, "81", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<PlayOnlineDetails> call, Throwable t) {

                    }
                });
            }
        });


        binding.btnplayerjoined.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Api apiObj = RetrofitClient.getInstance().getMyApi();

                apiObj.playerjoined( GlobalData.matchId).enqueue(new Callback<playerjoineddetails>() {
                    @Override
                    public void onResponse(Call<playerjoineddetails> call, Response<playerjoineddetails> response) {

                        Toast.makeText(Practice_Screen.this,  GlobalData.matchId, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<playerjoineddetails> call, Throwable t) {

                    }
                });

            }
        });


    }
}