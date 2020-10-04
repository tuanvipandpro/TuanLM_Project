package tuanlm.app.android.utils;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class NotificationUtils extends Application {
    public static final String CHANNEL = "MAIN_CHANNEL";

    @Override
    public void onCreate() {
        super.onCreate();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL, "Channel", NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("TuanLM's notification");

            NotificationManager manager = this.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
}
