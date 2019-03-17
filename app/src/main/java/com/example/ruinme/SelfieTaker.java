package com.example.ruinme;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.androidhiddencamera.CameraConfig;
import com.androidhiddencamera.HiddenCameraService;
import com.androidhiddencamera.HiddenCameraUtils;
import com.androidhiddencamera.config.CameraFacing;
import com.androidhiddencamera.config.CameraImageFormat;
import com.androidhiddencamera.config.CameraResolution;
import com.androidhiddencamera.config.CameraRotation;

import java.io.File;

public class SelfieTaker extends HiddenCameraService {

    private CameraConfig cameraConfig;

    public SelfieTaker() {
        // TODO: Figure out why EITHER of the following line throws null pointer exceptions
        // File cacheDir = this.getCacheDir();
        // File cacheDir = this.getApplication().getCacheDir();

        cameraConfig = new CameraConfig()
                .getBuilder(this)
                .setCameraFacing(CameraFacing.FRONT_FACING_CAMERA)
                .setCameraResolution(CameraResolution.MEDIUM_RESOLUTION)
                .setImageFormat(CameraImageFormat.FORMAT_JPEG)
                .setImageRotation(CameraRotation.ROTATION_270)
                .setImageFile(new File("AAAAAAA"))
                .build();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(SelfieTaker.this,
                        "Capturing image.", Toast.LENGTH_SHORT).show();

                takePicture();
            }
        }, 2000L);
        return START_NOT_STICKY;
    }

    @Override
    public void onCreate() {
        startCamera(cameraConfig);
    }

    @Override
    public void onImageCapture(File f) {
        Log.d("SelfieTaker", "" + f.getAbsolutePath());
    }

    @Override
    public void onCameraError(int e) {
        Log.e("SelfieTaker", "" + e);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
