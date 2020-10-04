package tuanlm.fpt.androids3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        TextView username = findViewById(R.id.txtRUsername);
        TextView password = findViewById(R.id.txtRPassword);
        TextView male = findViewById(R.id.txtRMale);
        TextView nation = findViewById(R.id.txtRNation);
        TextView birthday = findViewById(R.id.txtRBirthday);
        TextView sport = findViewById(R.id.txtRSport);
        TextView status = findViewById(R.id.txtRStatus);
        TextView exp = findViewById(R.id.txtRExp);
        TextView rating = findViewById(R.id.txtRRating);

        Bundle bundle = this.getIntent().getBundleExtra("INFO");

        username.setText(bundle.getString("username"));
        password.setText(bundle.getString("password"));
        male.setText(bundle.getBoolean("male") ? "Male" : "Female");
        nation.setText(bundle.getString("nation"));
        birthday.setText(bundle.getString("birthday"));
        sport.setText(bundle.getString("sport"));
        status.setText(bundle.getString("status"));
        exp.setText(bundle.getString("exp"));
        rating.setText(bundle.getString("rating"));

        Toast.makeText(getApplicationContext(), "Check your info !", Toast.LENGTH_LONG).show();
    }

    public void clickConfirm(View view) {
        Toast.makeText(getApplicationContext(), "Confirm !", Toast.LENGTH_LONG).show();

        Button confirm = findViewById(R.id.btnOK);
        confirm.setEnabled(false);
    }

    public void clickGoToWeb(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
        startActivity(browserIntent);
    }
}