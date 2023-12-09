package com.wl.exercise4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MultipleChoiceQuestionActivity extends AppCompatActivity {
    //Timer using service
    private TimerService timer;
    private boolean bound = false;

    //count the times that question has been answered
    int countTimes = 0;

    //time limit
    private int timeLimit = 0;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder binder) {
            TimerService.TimerBinder timerBinder = (TimerService.TimerBinder)binder;
            timer = timerBinder.getTimer();
            bound = true;
            timer.startTimer();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bound = false;
        }
    };


    //when the activity is created, it will start bind the timer service
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi);
        Intent intent = new Intent(this, TimerService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
        displayTime();


    }

    @Override
    protected void onResume() {
        super.onResume();
        if (bound && timer != null) {
            timer.startTimer();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (bound && timer != null) {
            timer.stopTimer();
        }
    }



//submit all the answers, go to the result layout
    public void submitMulti(View view){
        countTimes ++;
        Spinner options = (Spinner) findViewById(R.id.Options);
        int maxTimes = options.getAdapter().getCount() - 2;

        CheckBox option2_1 = (CheckBox) findViewById(R.id.Option2_1);
        CheckBox option2_2 = (CheckBox) findViewById(R.id.Option2_2);
        CheckBox option2_3 = (CheckBox) findViewById(R.id.Option2_3);

        //if in the second question A, B, C are chosen and
        //if in the first question the second entry is chosen, then the answer is right
        boolean answer = options.getSelectedItemId() == 1;
        String resultText = "No Result";
        if(answer && option2_1.isChecked() && option2_2.isChecked() && option2_3.isChecked()){
            resultText = "The answer is right. You solve the question in " + countTimes + "turns.";
        }else{
            if(countTimes >= maxTimes){
                resultText = "The answer is wrong. You failed the question for " + countTimes + "times.";
            }else{
                Toast toast = Toast.makeText(this, "wrong answer or timer expired!", Toast.LENGTH_SHORT);
                toast.show();
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


    //set the timer limit for timer
    public void onSetTimeClicked(View view){
        EditText timeLimitText = (EditText) findViewById(R.id.setTimerTextView);
        String timeLimitStr = timeLimitText.getText().toString();
        try {
            timeLimit = Integer.parseInt(timeLimitStr);
//            Intent intent = new Intent(this, TimerService.class);
//            intent.putExtra(TimerService.EXTRA_MESSAGE, timeLimit);
            timer.setTimeLimit(timeLimit);
        } catch (NumberFormatException e) {
            timeLimit = 0;
            Toast toast = Toast.makeText(this, "invalid time!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void onSwitchClicked(View view){
        TextView timeViewA = (TextView) findViewById(R.id.timerA);
        EditText timeLimitText = (EditText) findViewById(R.id.setTimerTextView);
        ImageButton setTimerButton = (ImageButton) findViewById(R.id.setTimerButton);

        if(timeViewA.getVisibility() == View.VISIBLE){
            timeViewA.setVisibility(View.GONE);
            timeLimitText.setVisibility(View.GONE);
            setTimerButton.setVisibility(View.GONE);
        }else{
            timeViewA.setVisibility(View.VISIBLE);
            timeLimitText.setVisibility(View.VISIBLE);
            setTimerButton.setVisibility(View.VISIBLE);
        }


    }

    private void displayTime(){
        final TextView timerView = (TextView) findViewById(R.id.timerA);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                String time = "";
                if(bound && timer != null){
                    time = timer.getTime();
                }
                timerView.setText(time);
                handler.postDelayed(this, 1000);
            }
        });
    }
}