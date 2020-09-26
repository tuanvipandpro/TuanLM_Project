package tuanlm.fpt.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView txtResult = this.findViewById(R.id.txtResult);
        txtResult.setText("Result : " + this.getIntent().getIntExtra("result", 0) + "");
    }

    public void onClickBackButton(View view) {
        this.finish();
    }


}