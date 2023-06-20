package com.example.chessgame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chessgame.api.Api;
import com.example.chessgame.api.RetrofitClient;
import com.example.chessgame.models.GlobalData;
import com.example.chessgame.models.UserDetails;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_Screen extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen2);

        //getSupportActionBar().hide();
        Button login;
        Button signup;
        TextView usr;
        TextView paswrd;

        usr=findViewById(R.id.txtusename);
        paswrd=findViewById(R.id.txtpassword);



        login = findViewById(R.id.btnenter);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Api apiObj = RetrofitClient.getInstance().getMyApi();
                apiObj.login( usr.getText().toString(),
                        paswrd.getText().toString()).enqueue(new Callback<UserDetails>() {
                    @Override
                    public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {
                        if(response.isSuccessful()){
                            GlobalData.obj = response.body();
                            Toast.makeText(Login_Screen.this, "LOGIN Clicked", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Login_Screen.this,ChooseScreen.class);
                            //intent.putExtra("id", userId);
                            startActivity(intent);
                        }else{
                            try{
                                Toast.makeText(getApplicationContext(),response.errorBody().string(), Toast.LENGTH_LONG).show();
                            }catch (Exception ex){
                                Toast.makeText(getApplicationContext(),ex.toString(), Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<UserDetails> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),t.toString(), Toast.LENGTH_LONG).show();
                    }
                });





            }
        });
        signup = findViewById(R.id.btnsignup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Login_Screen.this, "Signup Clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login_Screen.this,Signup_Screen.class);
                startActivity(intent);
            }
        });
    }

    }
