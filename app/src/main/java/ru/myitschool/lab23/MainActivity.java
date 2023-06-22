package ru.myitschool.lab23;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ru.myitschool.lab23.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    EditText nameEvent;
    EditText timeEvent;
    EditText notesEvent;
    Button saveButton;
    CalendarView calendarView;
    String title = "title";
    String date = "date";
    String time = "time";
    String notes = "notes";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        nameEvent = (EditText) findViewById(R.id.event_title_user_input);
        timeEvent = (EditText) findViewById(R.id.event_time_user_input);
        notesEvent = (EditText) findViewById(R.id.event_notes_user_input);
        saveButton = (Button) findViewById(R.id.save);

        setDateText();
        buttonClick (saveButton);
    }

     void setDateText() {
        calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener( new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth)  {
                timeEvent.setText(String.valueOf(dayOfMonth / 10 + "" + dayOfMonth % 10 + "." + month / 10 + "" + month % 10 + "."+ year));
            }
        });
    }

     void buttonClick (View view) {
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    snackbarShow();
                }
            });
    }

     void snackbarShow() {
         title = nameEvent.getText().toString();
         date = timeEvent.getText().toString();
         notes = notesEvent.getText().toString();
        Date currentDate = new Date();
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        time = String.valueOf(timeFormat.format(currentDate));


        if (title.equals("") || date.equals("") || notes.equals("")) {
            Snackbar.make(findViewById(R.id.content), "Введите название события!", Snackbar.LENGTH_SHORT).show();
        } else  {
            new AlertDialog.Builder(MainActivity.this)

                    .setMessage(
                            "Записано!" +
                            "\nСобытие: " + title +
                            "\nДата: " + date +
                            "\nВремя: " + time +
                            "\nЗаметки: " + notes)
                    .setCancelable(false)
                    .setPositiveButton("OK", null)
                    .show();

            nameEvent.setText("");
            timeEvent.setText("");
            notesEvent.setText("");
        }
    }
}

/*
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
*/