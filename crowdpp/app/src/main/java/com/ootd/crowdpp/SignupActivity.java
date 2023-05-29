package com.ootd.crowdpp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ootd.crowdpp.Retrofits.Result;
import com.ootd.crowdpp.Retrofits.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    Call<Result> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        EditText signupId = findViewById(R.id.signupId);
        EditText signupPw = findViewById(R.id.signupPw);
        EditText signupName = findViewById(R.id.signupName);
        EditText signupEmail = findViewById(R.id.signupEmail);
        Button signupButton = findViewById(R.id.signupButton);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputId = signupId.getText().toString();
                String inputPw = signupPw.getText().toString();
                String inputName = signupName.getText().toString();
                String inputEmail = signupEmail.getText().toString();

                call = RetrofitClient.getApiService().signup(inputId, inputPw, inputName, inputEmail);
                call.enqueue(new Callback<Result>(){
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        if (response.code() == 200) {
                            Result result = response.body();
                            String msg = result.getMsg();
                            if (msg.equals("success")) {
                                Toast.makeText(SignupActivity.this, "회원가입 완료", Toast.LENGTH_SHORT).show();
                                finish();
                            } else if (msg.equals("duplicated")) {
                                Toast.makeText(SignupActivity.this, "ID가 중복되었습니다", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SignupActivity.this, msg ,Toast.LENGTH_SHORT).show();
                            }
                            System.out.println(msg);
                        } else {
                        }
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        Log.e("request fail", t.getMessage());
                    }
                });
            }
        });
    }
}