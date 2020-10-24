package demo.fpt.androidsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import demo.fpt.androidsqlite.utils.MyDatabaseUtils;

public class MainActivity extends AppCompatActivity {
    private EditText txtUsername;
    private EditText txtPassword;
    private MyDatabaseUtils db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        db = new MyDatabaseUtils(this);
        db.createDefaultAccount();
    }

    private void initView() {
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
    }

    public void onClickLogin(View view) {
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();

        if (db.login(username, password)){
            Log.e("Login Result", "Login Success !");
            Toast.makeText(this, "Login Successfully", Toast.LENGTH_LONG).show();
        } else {
            Log.e("Login Result", "Login Fail !");
            Toast.makeText(this, "Login Fail", Toast.LENGTH_LONG).show();
        };
    }
}