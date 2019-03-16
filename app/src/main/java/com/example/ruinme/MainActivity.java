package com.example.ruinme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button butt = findViewById(R.id.ruinme_button);
        butt.setTag(0);
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int presses = (Integer) v.getTag();

                Intent intent = new Intent(MainActivity.this, ExploitService.class);
                startService(intent);

                v.setTag(++presses);
                butt.setText(String.format(Locale.getDefault(), "ruin me %d", presses));
            }
        });
    }
}
