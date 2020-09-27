package tuanlm.app.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import tuanlm.app.android.utils.BottomNavUtils;

public class DashboardActivity extends AppCompatActivity {

    private AppCompatActivity getActivityInstance() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_bottom);
        bottomNavigationView.setSelectedItemId(R.id.nav_dashboard);

        BottomNavUtils.setSelectedListener(bottomNavigationView, this);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_dashboard: return true;
                    case R.id.nav_home: return BottomNavUtils.setMenuItem(getActivityInstance(), HomeActivity.class);
                    case R.id.nav_about: return BottomNavUtils.setMenuItem(getActivityInstance(), AboutActivity.class);
                    default: return false;
                }
            }
        });
    }
}