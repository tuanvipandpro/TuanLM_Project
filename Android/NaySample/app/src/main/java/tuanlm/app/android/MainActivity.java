package tuanlm.app.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import tuanlm.app.android.utils.NotificationUtils;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private NotificationManagerCompat managerCompat;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        toolbar = this.findViewById(R.id.tolBar);
        this.setSupportActionBar(toolbar);

        drawerLayout = this.findViewById(R.id.drLayout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nvMain);
        navigationView.setNavigationItemSelectedListener(this);

        this.managerCompat = NotificationManagerCompat.from(this);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_message: {
                break;
            }
            case R.id.nav_person: break;
            case R.id.nav_share: break;
            case R.id.nav_exit: {
                this.finish();
                return true;
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void showMessage(View view) {
        Toast.makeText(this, "Toast Message", Toast.LENGTH_LONG).show();

        managerCompat.notify(1, new NotificationCompat.Builder(this, NotificationUtils.CHANNEL)
                .setSmallIcon(R.drawable.ic_baseline_email_24)
                .setContentTitle("Message")
                .setContentText("This is menu message")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build());
    }
}