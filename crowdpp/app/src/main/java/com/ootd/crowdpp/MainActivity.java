package com.ootd.crowdpp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.SharedPreferences;
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
    Fragment settingFragment;
//    BottomNavigationView bottomNavigationView;
    NavigationBarView navigationBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allCrowdFragment = new AllCrowdFragment();
        myCrowdFragment = new MyCrowdFragment();
        settingFragment = new SettingFragment();


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
                else if (item.getItemId() == R.id.item_setting){
                    SharedPreferences sharedPreferences = getSharedPreferences("loginUserInfo", MODE_PRIVATE);
                    Boolean isLogined = sharedPreferences.getBoolean("isLogined", false);
                    if (isLogined){
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainLayout, settingFragment).commitAllowingStateLoss();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "로그인이 필요합니다", Toast.LENGTH_SHORT).show();
                    }
                }
                return true;
            }
        });
    }
}