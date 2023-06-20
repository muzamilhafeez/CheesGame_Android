package com.example.chessgame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.chessgame.api.Api;
import com.example.chessgame.api.RetrofitClient;
import com.example.chessgame.models.PlayOnlineDetails;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Id_send_screen extends AppCompatActivity {
    TextInputEditText usr;
    Button sendid;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_send_screen);
        usr= findViewById(R.id.txtusrid);

        sendid = findViewById(R.id.btnenter1);
        sendid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Api apiObj = RetrofitClient.getInstance().getMyApi();
               String txt= usr.getText().toString();
              int i = Integer.parseInt(txt);
                apiObj.playonline(i).enqueue(new Callback<PlayOnlineDetails>() {
                    @Override
                    public void onResponse(Call<PlayOnlineDetails> call, Response<PlayOnlineDetails> response) {
                        if(response.isSuccessful()){
                            if(response.isSuccessful()) {
                                Intent intent = new Intent(Id_send_screen.this, ChessBoard_online.class);
                                startActivity(intent);
                            }else{
                                Intent intent = new Intent(Id_send_screen.this, Waiting_Screen.class);
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
    }
}