package com.example.permissionslib;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

public class PermissionHelper {

    public static void handlePermission(Context context, String permission) {
        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permission)) {
            showPermissionRationale(context, permission);
        } else {
            openAppSettings(context);
        }
    }

    private static void showPermissionRationale(Context context, String permission) {
        new AlertDialog.Builder(context)
                .setTitle("Permission Required")
                .setMessage("This permission is necessary for the app to function correctly. Please enable the permission in app settings.")
                .setPositiveButton("OK", (dialog, which) -> openAppSettings(context))
                .setNegativeButton("Cancel", null)
                .show();
    }

    private static void openAppSettings(Context context) {
        Intent settingsIntent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", context.getPackageName(), null));
        settingsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(settingsIntent);
    }
}
