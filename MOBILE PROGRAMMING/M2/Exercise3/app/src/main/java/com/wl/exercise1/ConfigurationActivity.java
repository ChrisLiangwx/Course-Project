package com.wl.exercise1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

public class ConfigurationActivity extends AppCompatActivity {
    private Switch timerSwitch;
    private EditText timeLimitEditText;
    private RadioButton useImageButtonRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSwitch = findViewById(R.id.timerSwitch);
        timeLimitEditText = findViewById(R.id.timeLimitEditText);
        useImageButtonRadioButton = findViewById(R.id.useImageButtonRadioButton);
    }

    public void chooseMulti(View view){
        Intent intent = new Intent(this, MultipleChoiceQuestionActivity.class);

        boolean isTimerVisible = timerSwitch.isChecked();

        EditText timeLimitEditText = findViewById(R.id.timeLimitEditText);

        String timeLimitString = timeLimitEditText.getText().toString();
        if (timeLimitString.isEmpty()) {
            Toast.makeText(this, "Please Set Time Limit", Toast.LENGTH_SHORT).show();
            return;
        }

        int timeLimit;
        try {
            timeLimit = Integer.parseInt(timeLimitString);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please Input Valid Time", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean useImageButton = useImageButtonRadioButton.isChecked();

        intent.putExtra("TIMER_VISIBLE", isTimerVisible);
        intent.putExtra("TIME_LIMIT", timeLimit);
        intent.putExtra("USE_IMAGE_BUTTON", useImageButton);

        startActivity(intent);
    }

    public void chooseFill(View view){
        Intent intent = new Intent(this, FillinTheBlankQuestionActivity.class);

        boolean isTimerVisible = timerSwitch.isChecked();
        EditText timeLimitEditText = findViewById(R.id.timeLimitEditText);

        String timeLimitString = timeLimitEditText.getText().toString();
        if (timeLimitString.isEmpty()) {
            Toast.makeText(this, "Please Set Time Limit", Toast.LENGTH_SHORT).show();
            return;
        }

        int timeLimit;
        try {
            timeLimit = Integer.parseInt(timeLimitString);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please Input Valid Time", Toast.LENGTH_SHORT).show();
            return;
        }
        boolean useImageButton = useImageButtonRadioButton.isChecked();

        intent.putExtra("TIMER_VISIBLE", isTimerVisible);
        intent.putExtra("TIME_LIMIT", timeLimit);
        intent.putExtra("USE_IMAGE_BUTTON", useImageButton);

        startActivity(intent);
    }
}