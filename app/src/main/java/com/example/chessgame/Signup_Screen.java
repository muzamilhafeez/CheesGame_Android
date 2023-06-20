package com.example.chessgame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.chessgame.api.Api;
import com.example.chessgame.api.RetrofitClient;
import com.example.chessgame.databinding.ActivitySignupScreenBinding;
import com.example.chessgame.models.usersignup;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signup_Screen extends AppCompatActivity {
    ActivitySignupScreenBinding binding;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignupScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Button sgnup;
        sgnup = findViewById(R.id.btncreate);
        sgnup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        binding.btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name= binding.tbName.getText().toString();
                String password=binding.tbPassword.getText().toString();
                usersignup usersignup= new usersignup();
                usersignup.name=name;
                usersignup.password=password;

                Api apiObj = RetrofitClient.getInstance().getMyApi();
                apiObj.CreateAccount(usersignup.name,usersignup.password).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.isSuccessful())
                        {
                            Toast.makeText(Signup_Screen.this, "Signed up", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else
                            Toast.makeText(Signup_Screen.this, "Failed", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(Signup_Screen.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });


    }
}