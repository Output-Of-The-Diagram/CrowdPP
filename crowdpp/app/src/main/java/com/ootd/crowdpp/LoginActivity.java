package com.ootd.crowdpp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.ootd.crowdpp.Retrofits.DataModel;
import com.ootd.crowdpp.Retrofits.Result;
import com.ootd.crowdpp.Retrofits.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    Call<Result> call;
    Boolean isLogined = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button goMainButton = findViewById(R.id.goMainButton);
        Button loginButton = findViewById(R.id.loginButton);
        Button signupButton = findViewById(R.id.signupButton);
        EditText idInput = findViewById(R.id.idInput);
        EditText pwInput = findViewById(R.id.pwInput);


        SharedPreferences sharedPreferences = getSharedPreferences("loginUserInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String id = sharedPreferences.getString("id", "");
        String pw = sharedPreferences.getString("pw", "");

        idInput.setText(id); // id 자동저장
        pwInput.setText(pw); // pw 자동저장


        goMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putBoolean("isLogined", false);
                editor.apply();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputId = idInput.getText().toString();
                String inputPw = pwInput.getText().toString();

                editor.putString("id", inputId);
                editor.putString("pw", inputPw);
                editor.putBoolean("isLogined", true);
                editor.apply();

                call = RetrofitClient.getApiService().login(inputId, inputPw);
                call.enqueue(new Callback<Result>(){
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        if (response.code() == 200) {
                            Result result = response.body();
                            System.out.println(result.getMsg());
                        } else {
                        }
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        Log.e("TEST2", t.getMessage());
                    }
                });

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);

                String inputId = idInput.getText().toString();
                String inputPw = pwInput.getText().toString();
            }
        });
    }
}
