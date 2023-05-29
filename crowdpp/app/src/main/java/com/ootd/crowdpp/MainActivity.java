package com.ootd.crowdpp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

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

//        bottomNavigationView = findViewById(R.id.mainBottomNavigation);
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



    }


}