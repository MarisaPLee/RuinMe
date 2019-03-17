package com.example.ruinme;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

public class PreventativeMeasuresActivity extends Activity {

    WindowManager windowManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("PreventativeMeasuresActivity","starting");
    }

    protected void onStart() {
        super.onStart();
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.START | Gravity.TOP;

        LayoutInflater factory = LayoutInflater.from(this);
        View myView = factory.inflate(R.layout.preventative_measures, null);
        windowManager.addView(myView, params);
    }
}
