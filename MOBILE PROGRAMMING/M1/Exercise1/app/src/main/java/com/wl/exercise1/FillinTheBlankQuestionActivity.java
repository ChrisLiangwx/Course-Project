package com.wl.exercise1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class FillinTheBlankQuestionActivity extends AppCompatActivity {
    int countTimes = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill);
    }

    public void submitFill(View view){
        countTimes ++;
        EditText answerEditText = (EditText) findViewById(R.id.Answer);


        String answer = answerEditText.getText().toString();
        String upperClassAnswer = answer.toUpperCase();
        String resultText = "No Result";
        if(upperClassAnswer.equals("DOUBLE")){
            resultText = "The answer is right. You solve the question in " + countTimes + "turns.";
        }else{
            if(countTimes >= 2){
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