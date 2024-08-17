package com.example.permissionslib;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PermissionHandler {

    private static PermissionHandler instance;
    private final Set<PermissionStatusListener> registeredListeners = new HashSet<>();
    private final Map<String, Boolean> permissionStatusMap = new HashMap<>();

    private PermissionHandler() {}

    public static PermissionHandler getInstance() {
        if (instance == null) {
            synchronized (PermissionHandler.class) {
                if (instance == null) {
                    instance = new PermissionHandler();
                }
            }
        }
        return instance;
    }

    public boolean checkPermissions(Context context, String[] requiredPermissions) {
        if (context != null && requiredPermissions != null) {
            for (String permission : requiredPermissions) {
                if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public void requestMissingPermissions(Activity activity, String[] permissionsToRequest, int requestCode) {
        for (String permission : permissionsToRequest) {
            if (!isPermissionAllowed(activity, permission)) {
                ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
            }
        }
    }

    public void notifyPermissionStatusChange() {
        for (PermissionStatusListener listener : registeredListeners) {
            listener.onPermissionStatusChanged();
        }
    }

    public void addPermissionStatusListener(PermissionStatusListener listener) {
        registeredListeners.add(listener);
    }

    public void removePermissionStatusListener(PermissionStatusListener listener) {
        registeredListeners.remove(listener);
    }

    public boolean isPermissionAllowed(Context context, String permission) {
        if (context != null && permission != null) {
            return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
        }
        return false;
    }

    public void requestPermissionIfNeeded(Activity activity, boolean shouldRequestPermission, String permission, int requestCode) {
        if (!isPermissionAllowed(activity, permission)) {
            if (shouldRequestPermission) {
                ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
            }
        }
    }

    public String[] getAppDeclaredPermissions(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_PERMISSIONS);
            return packageInfo.requestedPermissions != null ? packageInfo.requestedPermissions : new String[0];
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    public interface PermissionStatusListener {
        void onPermissionStatusChanged();
    }
}
