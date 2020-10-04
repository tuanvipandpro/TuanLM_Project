package tuanlm.fpt.androids3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private Spinner spNation;
    private String spValue;
    private TextView txtBirth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtBirth = findViewById(R.id.txtBirthday);

        final Calendar calendar = Calendar.getInstance();
        txtBirth.setText(calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + (calendar.get(Calendar.YEAR) - 18));

        spNation = findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> dataAdapter = ArrayAdapter.createFromResource(this, R.array.spinner_array, R.layout.support_simple_spinner_dropdown_item);
        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spNation.setAdapter(dataAdapter);
        spNation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spValue = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void clickChangeDate(View v) {
        new DatePickerFragment().show(getSupportFragmentManager(), "datePicker");
    }

    public void clickToRegister (View v) {
        Bundle bundle = new Bundle();

        bundle.putString("username", ((EditText) findViewById(R.id.txtUsername)).getText().toString());
        bundle.putString("password", ((EditText) findViewById(R.id.txtPassword)).getText().toString());
        bundle.putBoolean("male", ((CheckBox) findViewById(R.id.chkMale)).isChecked());
        RadioButton radioButton = findViewById(((RadioGroup) findViewById(R.id.radioGroup)).getCheckedRadioButtonId());
        bundle.putString("status", radioButton.getText().toString());

        txtBirth = findViewById(R.id.txtBirthday);
        bundle.putString("birthday", txtBirth.getText().toString());
        bundle.putString("nation", spValue);
        bundle.putInt("exp", ((SeekBar) findViewById(R.id.seekBar)).getProgress());
        bundle.putString("sport", ((ToggleButton) findViewById(R.id.toggleButton)).getText().toString());
        bundle.putFloat("rating", ((RatingBar) findViewById(R.id.ratingBar)).getRating());

        Intent intent = new Intent(this, ShowActivity.class);
        intent.putExtra("INFO", bundle);
        startActivity(intent);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        int mon = month + 1;
        int day = dayOfMonth;

        txtBirth = findViewById(R.id.txtBirthday);
        txtBirth.setText(day + "/" + mon + "/" + year);
    }
}