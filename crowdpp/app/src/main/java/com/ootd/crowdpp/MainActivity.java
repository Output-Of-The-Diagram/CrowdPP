package com.ootd.crowdpp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.ootd.crowdpp.Retrofits.DataModel;
import com.ootd.crowdpp.Retrofits.RetrofitClient;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Call<DataModel> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.Text1);

        call = RetrofitClient.getApiService().test_my_server();

        call.enqueue(new Callback<DataModel>(){
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                if (response.code() == 200) {
                    DataModel result = response.body();
//                    textViewId.setText(result.getName());
//                    textViewAutohr.setText(result.getCity());
                    Log.e("TEST1", result.getName());
                    textView.setText(result.getName());
                    System.out.println(result);
                } else {
                }
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                Log.e("TEST2", t.getMessage());
            }
        });
    }
}