package com.ootd.crowdpp;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button goMainButton = findViewById(R.id.goMainButton);
        Button loginButton = findViewById(R.id.loginButton);
        Button signupButton = findViewById(R.id.signupButton);
        EditText idInput = findViewById(R.id.idInput);
        EditText pwInput = findViewById(R.id.pwInput);

        goMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputId = idInput.getText().toString();
                String inputPw = pwInput.getText().toString();

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
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputId = idInput.getText().toString();
                String inputPw = pwInput.getText().toString();

                call = RetrofitClient.getApiService().signup(inputId, inputPw);
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
            }
        });
    }
}
