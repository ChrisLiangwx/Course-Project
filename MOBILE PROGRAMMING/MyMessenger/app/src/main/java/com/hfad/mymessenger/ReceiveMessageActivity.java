package com.hfad.mymessenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ReceiveMessageActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_message);

        Intent intent = getIntent();
//        String messageText = intent.getStringExtra(EXTRA_MESSAGE);
        String messageText = intent.getStringExtra(intent.EXTRA_TEXT);

        TextView messageView = (TextView) findViewById(R.id.message);
        messageView.setText(messageText);
    }

}