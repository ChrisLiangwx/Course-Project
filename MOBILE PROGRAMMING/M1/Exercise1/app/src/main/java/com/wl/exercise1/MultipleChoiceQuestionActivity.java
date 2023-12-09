package com.wl.exercise1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

public class MultipleChoiceQuestionActivity extends AppCompatActivity {
    int countTimes = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi);
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