package com.wl.exercise4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        String resultText = intent.getStringExtra(intent.EXTRA_TEXT);
        TextView resultTextView = (TextView) findViewById(R.id.Result);
        resultTextView.setText(resultText);
    }


}