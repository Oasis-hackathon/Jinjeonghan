package org.techtown.hackathon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    Fragment_home fragmentHome;
    Fragment_obituary fragmentObituary;
    Fragment_memorial fragmentMemorial;
    Fragment_calendar fragmentCalendar;
    Fragment_mypage fragmentMypage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentHome = new Fragment_home();
        fragmentObituary = new Fragment_obituary();
        fragmentMemorial = new Fragment_memorial();
        fragmentCalendar = new Fragment_calendar();
        fragmentMypage = new Fragment_mypage();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentHome).commit();

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab1:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, fragmentHome).commit();

                        return true;
                    case R.id.tab2:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, fragmentObituary).commit();

                        return true;
                    case R.id.tab3:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, fragmentMemorial).commit();

                        return true;
                    case R.id.tab4:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, fragmentCalendar).commit();

                        return true;
                    case R.id.tab5:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, fragmentMypage).commit();

                        return true;
                }

                return false;
            }
        });
    }
}