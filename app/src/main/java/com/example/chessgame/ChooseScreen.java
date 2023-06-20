package com.example.chessgame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ChooseScreen extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_screen);


        Button player;
        Button oplayer;
        Button hj;
        Button exit;


        player = findViewById(R.id.button4);
        exit = findViewById(R.id.btnexit);
        player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ChooseScreen.this, "Enter in Rooms", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ChooseScreen.this,MainActivity2.class);
                startActivity(intent);
            }
        });


        oplayer = findViewById(R.id.button5);
        oplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             Intent intent = new Intent(ChooseScreen.this,Friend_requestScreen.class);
//               Intent intent = new Intent(ChooseScreen.this,Friend_requestScreen.class);
                startActivity(intent);
            }
        });

        hj = findViewById(R.id.button3);
        hj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ChooseScreen.this,ListSpectatormatches .class);
//               Intent intent = new Intent(ChooseScreen.this,Friend_requestScreen.class);
                startActivity(intent);
            }
        });


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ChooseScreen.this,Practice_Screen .class);
//               Intent intent = new Intent(ChooseScreen.this,Friend_requestScreen.class);
                startActivity(intent);
            }
        });
    }
}