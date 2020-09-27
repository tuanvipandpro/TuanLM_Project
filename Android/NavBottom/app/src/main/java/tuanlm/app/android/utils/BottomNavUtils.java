package tuanlm.app.android.utils;

import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import tuanlm.app.android.AboutActivity;
import tuanlm.app.android.DashboardActivity;
import tuanlm.app.android.HomeActivity;
import tuanlm.app.android.R;

public class BottomNavUtils {
    public static boolean setMenuItem(AppCompatActivity activity, Class<?> cls) {
        activity.startActivity(new Intent(activity.getApplicationContext(), cls));
        activity.overridePendingTransition(0,0);
        return true;
    }

    @Deprecated
    public static void setSelectedListener(BottomNavigationView bottomNavigationView, final AppCompatActivity activity) {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_dashboard: return setMenuItem(activity, DashboardActivity.class);
                    case R.id.nav_home: return setMenuItem(activity, HomeActivity.class);
                    case R.id.nav_about: return setMenuItem(activity, AboutActivity.class);
                    default: return false;
                }
            }
        });
    }
}
