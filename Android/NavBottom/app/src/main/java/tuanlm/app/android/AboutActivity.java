package tuanlm.app.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import tuanlm.app.android.utils.BottomNavUtils;

public class AboutActivity extends AppCompatActivity {

    private AppCompatActivity getActivityInstance() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_bottom);
        bottomNavigationView.setSelectedItemId(R.id.nav_about);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_dashboard: return BottomNavUtils.setMenuItem(getActivityInstance(), DashboardActivity.class);
                    case R.id.nav_home: return BottomNavUtils.setMenuItem(getActivityInstance(), HomeActivity.class);
                    case R.id.nav_about: return true;
                    default: return false;
                }
            }
        });
    }
}