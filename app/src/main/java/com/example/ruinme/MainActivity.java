package com.example.ruinme;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.Manifest;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import com.example.ruinme.PermissionTracker;

public class MainActivity extends AppCompatActivity {

    Intent exploitIntent;
    private ExploitService exploitService;
    private boolean isSvcRunning = false;
    public final static int REQUEST_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.d("MainActivity", "Requesting permissions...");
        PermissionTracker.requestPermissions(this);
        Log.d("MainActivity", "Current Permissions: "+PermissionTracker.getPermissionSet(this, true));

        exploitService = new ExploitService();
        exploitIntent = new Intent(MainActivity.this, exploitService.getClass());





        final Button butt = findViewById(R.id.ruinme_button);
        butt.setTag(0);
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int presses = (Integer) v.getTag();

                LocalBroadcastManager manager = LocalBroadcastManager.getInstance(getApplicationContext());
                manager.registerReceiver(mReceiver, new IntentFilter(ExploitService.ACTION_PONG));
                // the service will respond to this broadcast only if it's running
                manager.sendBroadcast(new Intent(ExploitService.ACTION_PING));
                if (isSvcRunning) { Log.i("Service status", "running"); }
                else {Log.i("Service status", "stopped"); }

                if (!isSvcRunning) { startService(exploitIntent); }

                v.setTag(++presses);
                butt.setText(String.format(Locale.getDefault(), "ruin me %d", presses));
            }
        });
    }

    @Override
    protected void onDestroy() {
        Log.d("MainActivity", "stopping exploit service");
        stopService(exploitIntent);
        super.onDestroy();
    }

    protected BroadcastReceiver mReceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive (Context context, Intent intent)
        {
            // here you receive the response from the service
            if (intent.getAction().equals(ExploitService.ACTION_PONG))
            {
                isSvcRunning = true;
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (Settings.canDrawOverlays(this)) {
                // permission granted...
                Log.d("MainActivity", "overlay permission granted");
            }else{
                // permission not granted...
                Log.d("MainActivity", "overlay permission not granted");
            }
        }
    }
}
