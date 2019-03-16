package com.example.ruinme;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.HashSet;
import java.util.Set;

public class PermissionTracker {

    public static void requestPermissions(Activity act) {
        String[] ps = getPermissionSet(act, false).toArray(new String[0]);
        ActivityCompat.requestPermissions(act, ps, 0);
    }

    public static Set<String> getPermissionSet(Activity act, boolean granted) {
        Set<String> ret = new HashSet<>();
        int state = PackageManager.PERMISSION_DENIED;
        if(granted) state = PackageManager.PERMISSION_GRANTED;

        for(String p : permissions) {
            if (ContextCompat.checkSelfPermission(act, p) == state)
                ret.add(p);
        }

        return ret;
    }

    public static final String[] permissions = new String[]{
            Manifest.permission.READ_CALENDAR,
            Manifest.permission.WRITE_CALENDAR,
            Manifest.permission.READ_CALL_LOG,
            Manifest.permission.WRITE_CALL_LOG,
            Manifest.permission.PROCESS_OUTGOING_CALLS,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.GET_ACCOUNTS,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_PHONE_NUMBERS,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.ANSWER_PHONE_CALLS,
            Manifest.permission.ADD_VOICEMAIL,
            Manifest.permission.USE_SIP,
            Manifest.permission.BODY_SENSORS,
            Manifest.permission.SEND_SMS,
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.READ_SMS,
            Manifest.permission.RECEIVE_WAP_PUSH,
            Manifest.permission.RECEIVE_MMS,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };


}
