package com.bariscanyilmaz.musicplayer.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.bariscanyilmaz.musicplayer.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private static final String APP_SHARED_PREFS = "music_player_preferences";
    SharedPreferences sharedPreferences;
    private ActivityLoginBinding binding;
    private boolean isUserLoggedIn;

    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
    }

    @Override
    protected void onResume() {
        sharedPreferences=getApplicationContext().getSharedPreferences(APP_SHARED_PREFS, Context.MODE_PRIVATE);
        isUserLoggedIn=sharedPreferences.getBoolean("isLoggedIn",false);
        if(isUserLoggedIn){//redirect to songs
            Intent main=new Intent(this,MainActivity.class);
            startActivity(main);
            finish();
        }

        super.onResume();
    }

    public void login(View view){
        this.email=binding.userEmail.getText().toString();
        this.password=binding.userPassword.getText().toString();

        //check db has user with email and check password

    }




}