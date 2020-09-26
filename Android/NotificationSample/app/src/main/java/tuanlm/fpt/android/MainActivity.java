package tuanlm.fpt.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private NotificationManagerCompat managerCompat;

    private EditText txtTitle;
    private EditText txtMessage;

    private Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTitle = this.findViewById(R.id.edTitle);
        txtMessage = this.findViewById(R.id.edMessage);

        this.managerCompat = NotificationManagerCompat.from(this);
    }

    public void onClickSend(View view) {
        String title = this.txtTitle.getText().toString();
        String message = this.txtMessage.getText().toString();

        this.managerCompat.notify(1, new NotificationCompat.Builder(this, NotificationApp.CHANNEL_1)
                .setSmallIcon(R.drawable.ic_call)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build());
    }
}