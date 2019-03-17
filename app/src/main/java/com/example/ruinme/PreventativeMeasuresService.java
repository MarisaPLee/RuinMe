package com.example.ruinme;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.app.IntentService;

public class PreventativeMeasuresService extends IntentService {

    public PreventativeMeasuresService() {
        super("PreventativeMeasuresService");
    }
    WindowManager windowManager;
    FrameLayout screenLayout;

    @Override
    protected void onHandleIntent(Intent i) {
        Log.d("PreventativeMeasuresService","starting");
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        screenLayout = new FrameLayout(this);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.START | Gravity.TOP;

        LayoutInflater factory = LayoutInflater.from(this);
        View myView = factory.inflate(R.layout.preventative_measures, screenLayout);
        windowManager.addView(myView, params);
    }

}
