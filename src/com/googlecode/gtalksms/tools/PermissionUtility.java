package com.googlecode.gtalksms.tools;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

public class PermissionUtility {

    private Context context;

    private String[] appPermisions;

    public PermissionUtility(Context context) {
        this.context = context;
        this.appPermisions = this.retrieveAppPermissions(context);
    }

    public boolean arePermissionsEnabled() {
        for (String permission : appPermisions) {
            if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED)
                return false;
        }
        return true;
    }

    public void requestMultiplePermissions() {
        List<String> remainingPermissions = new ArrayList<>();
        for (String permission : appPermisions) {
            if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                remainingPermissions.add(permission);
            }
        }
        ActivityCompat.requestPermissions((Activity) context, remainingPermissions.toArray(
                new String[remainingPermissions.size()]), 101);
    }

    public boolean onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 101) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permissions[i])) {
                        requestMultiplePermissions();
                    }
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Retrieves permissions listed in the manifest file
     * @param context Context
     * @return Returns String array of permissions
     */
    public static String[] retrieveAppPermissions(Context context) {
        try {
            return context
                    .getPackageManager()
                    .getPackageInfo(context.getPackageName(), PackageManager.GET_PERMISSIONS)
                    .requestedPermissions;
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("This should have never happened.", e);
        }
    }
}
