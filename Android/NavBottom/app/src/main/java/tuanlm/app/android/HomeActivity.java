package tuanlm.app.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import tuanlm.app.android.utils.BottomNavUtils;

public class HomeActivity extends AppCompatActivity {

    private AppCompatActivity getActivityInstance() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Init
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_bottom);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_dashboard: return BottomNavUtils.setMenuItem(getActivityInstance(), DashboardActivity.class);
                    case R.id.nav_home: return true;
                    case R.id.nav_about: return BottomNavUtils.setMenuItem(getActivityInstance(), AboutActivity.class);
                    default: return false;
                }
            }
        });
    }
}