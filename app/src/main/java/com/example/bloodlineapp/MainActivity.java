
package com.example.bloodlineapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.text);
        /**
         * Notification Data Received in Intent Extras if App not Running or in Background
         **/
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String dataText = "";
            for (String key : bundle.keySet()) {
                dataText += key + " = " + bundle.get(key) + "\n";
            }
            dataText = "Data Received from Notification\n\n" + dataText.trim();
            textView.setText(dataText);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        TextView textView = findViewById(R.id.text);
        /**
         * Notification Data Received in Intent Extras when App is Running
         **/
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String dataText = "";
            for (String key : bundle.keySet()) {
                dataText += key + " = " + bundle.get(key) + "\n";
            }
            dataText = "Data Received from Notification\n\n" + dataText.trim();
            textView.setText(dataText);
        }

    }
}