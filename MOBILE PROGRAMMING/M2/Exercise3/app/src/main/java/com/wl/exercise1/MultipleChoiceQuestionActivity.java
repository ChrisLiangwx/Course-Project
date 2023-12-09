package com.wl.exercise1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MultipleChoiceQuestionActivity extends AppCompatActivity {
    private int secondsA = 0; // A: count time with activity in the foreground
    private int secondsB = 0; // B: count time with activity alive
    private boolean runningA = false;
    private boolean wasRunningA = false;
    private int countTimes = 0; // count the times that question has been answered
    private int timeLimit = 0; // time limit
    private boolean isTimeSet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi);

        if(savedInstanceState != null){
            secondsA = savedInstanceState.getInt("secondsA");
            secondsB = savedInstanceState.getInt("secondsB");
            runningA = savedInstanceState.getBoolean("running");
            wasRunningA = savedInstanceState.getBoolean("wasRunning");
        }

        // Get the timer settings from MainActivity
        Intent intent = getIntent();
        boolean isTimerVisible = intent.getBooleanExtra("TIMER_VISIBLE", true);
        timeLimit = intent.getIntExtra("TIME_LIMIT", 0);
        isTimeSet = timeLimit > 0;

        TextView timeViewA = findViewById(R.id.timerA);
        TextView timeViewB = findViewById(R.id.timerB);
        timeViewA.setVisibility(isTimerVisible ? View.VISIBLE : View.GONE);
        timeViewB.setVisibility(isTimerVisible ? View.VISIBLE : View.GONE);

        boolean useImageButton = intent.getBooleanExtra("USE_IMAGE_BUTTON", false);

        Button submitButton = findViewById(R.id.SubmitMulti);
        ImageButton submitImageButton = findViewById(R.id.SubmitMultiImage);
        if(useImageButton) {
            submitButton.setVisibility(View.GONE);
            submitImageButton.setVisibility(View.VISIBLE);
        } else {
            submitButton.setVisibility(View.VISIBLE);
            submitImageButton.setVisibility(View.GONE);
        }

        runningA = true;
        runTimerA();
        runTimerB();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("secondsA", secondsA);
        savedInstanceState.putInt("secondsB", secondsB);
        savedInstanceState.putBoolean("running", runningA);
        savedInstanceState.putBoolean("wasRunning", wasRunningA);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(wasRunningA){
            runningA = true;
            runTimerA();
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        wasRunningA = runningA;
        runningA = false;
    }

    @Override
    protected void onStop(){
        super.onStop();
        wasRunningA = false;
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        secondsB = 0;
    }

    private void runTimerA(){
        final TextView timeViewA = findViewById(R.id.timerA);
        final Handler handler =  new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hoursA = secondsA/3600;
                int minutesA = (secondsA%3600)/60;
                int secsA = secondsA%60;
                String timeA = String.format(Locale.getDefault(),"%d:%02d:%02d", hoursA, minutesA, secsA);
                timeViewA.setText(timeA);

                if(runningA){
                    secondsA++;
                    if(isTimeSet && secondsA > timeLimit){
                        submitMulti(null);
                    }
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    private void runTimerB(){
        final TextView timeViewB = findViewById(R.id.timerB);
        final Handler handler =  new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hoursB = secondsB/3600;
                int minutesB = (secondsB%3600)/60;
                int secsB = secondsB%60;
                String timeB = String.format(Locale.getDefault(),"%d:%02d:%02d", hoursB, minutesB, secsB);
                timeViewB.setText(timeB);

                secondsB++;
                handler.postDelayed(this, 1000);
            }
        });
    }

    public void submitMulti(View view){
        countTimes++;
        Spinner options = findViewById(R.id.Options);
        int maxTimes = options.getAdapter().getCount() - 2;

        CheckBox option2_1 = findViewById(R.id.Option2_1);
        CheckBox option2_2 = findViewById(R.id.Option2_2);
        CheckBox option2_3 = findViewById(R.id.Option2_3);

        boolean answer = options.getSelectedItemId() == 1;
        String resultText = "No Result";
        if(answer && option2_1.isChecked() && option2_2.isChecked() && option2_3.isChecked()){
            resultText = "The answer is right. You solve the question in " + countTimes + " turns.";
        } else {
            if(countTimes >= maxTimes){
                resultText = "The answer is wrong. You failed the question for " + countTimes + " times.";
            } else {
                Toast.makeText(this, "Wrong answer or timer expired!", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, resultText);
        String chooserTitle = getString(R.string.chooser);
        startActivity(Intent.createChooser(intent, chooserTitle));
    }
}
