package com.wl.exercise1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

public class MultipleChoiceQuestionActivity extends AppCompatActivity {
    //A: count time with activity in the foreground
    //B: count time with activity alive
    private int secondsA = 0;
    private int secondsB = 0;

    private boolean runningA = false;
    private boolean wasRunningA = false;

    //count the times that question has been answered
    int countTimes = 0;

    //when the activity is created, it will start both timer A and B
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

    //timer A will be restored when activity is in the foreground
    @Override
    protected void onResume(){
        super.onResume();
        if(wasRunningA){
            runningA = true;
            runTimerA();
        }
    }

    //timer A will be paused when activity is visible but not in the foreground
    @Override
    protected void onPause(){
        super.onPause();
        wasRunningA = runningA;
        runningA = false;
    }

    //timer A will reset when activity is stopped
    @Override
    protected void onStop(){
        super.onStop();
        wasRunningA = false;
    }

    //timer A will restart when activity is visible again
    @Override
    protected void onRestart(){
        super.onRestart();
        runningA = true;
    }


    //timer B will reset when activity is destroyed
    @Override
    protected void onDestroy(){
        super.onDestroy();
        secondsB = 0;
    }

    //show both timers
    private void runTimerA(){
        final TextView timeViewA = (TextView) findViewById(R.id.timerA);
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
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    private void runTimerB(){
        final TextView timeViewB = (TextView) findViewById(R.id.timerB);
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
        countTimes ++;
        Spinner options = (Spinner) findViewById(R.id.Options);
        int maxTimes = options.getAdapter().getCount() - 2;

        //if the second entry is chosen, then the answer is right
        boolean answer = options.getSelectedItemId() == 1;
        String resultText = "No Result";
        if(answer){
            resultText = "The answer is right. You solve the question in " + countTimes + "turns.";
        }else{
            if(countTimes >= maxTimes){
                resultText = "The answer is wrong. You failed the question for " + countTimes + "times.";
            }else{
                return;
            }
        }

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, resultText);
        String chooserTitle = getString(R.string.chooser);
        Intent chosenIntent = Intent.createChooser(intent, chooserTitle);
        startActivity(chosenIntent);

    }
}