package com.ootd.crowdpp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationBarView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.ootd.crowdpp.Retrofits.DataModel;
import com.ootd.crowdpp.Retrofits.RetrofitClient;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Call<DataModel> call;

    Fragment allCrowdFragment;
    Fragment myCrowdFragment;
//    BottomNavigationView bottomNavigationView;
    NavigationBarView navigationBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allCrowdFragment = new AllCrowdFragment();
        myCrowdFragment = new MyCrowdFragment();


        navigationBarView = findViewById(R.id.mainBottomNavigation);

        //초기 화면 설정
        getSupportFragmentManager().beginTransaction().replace(R.id.mainLayout, allCrowdFragment).commitAllowingStateLoss();
        //네비게이션 클릭에 따른 화면 전환
        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.item_allCrowd){
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainLayout, allCrowdFragment).commitAllowingStateLoss();
                }
                else if (item.getItemId() == R.id.item_myCrowd){
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainLayout, myCrowdFragment).commitAllowingStateLoss();
                }
                return true;
            }
        });


        TextView textView = findViewById(R.id.Text1);

        call = RetrofitClient.getApiService().test_my_server();

        call.enqueue(new Callback<DataModel>(){
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                if (response.code() == 200) {
                    DataModel result = response.body();
                    String msg = result.getName();
                    if (msg.equals("notRegistered")) {
                        Toast.makeText(MainActivity.this, "등록되지 않은 ID입니다", Toast.LENGTH_SHORT).show();
                        finish();
                    } else if (msg.equals("wrongPW")) {
                        Toast.makeText(MainActivity.this, "비밀번호가 올바르지 않습니다", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, msg ,Toast.LENGTH_SHORT).show();
                    }
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