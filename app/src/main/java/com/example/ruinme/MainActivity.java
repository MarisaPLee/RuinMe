package com.example.ruinme;

import android.content.Intent;
import android.Manifest;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import com.example.ruinme.PermissionTracker;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.d("MainActivity", "Requesting permissions...");
        PermissionTracker.requestPermissions(this);
        Log.d("MainActivity", "Current Permissions: "+PermissionTracker.getPermissionSet(this, true));

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
