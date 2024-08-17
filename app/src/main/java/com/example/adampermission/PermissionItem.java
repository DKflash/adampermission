package com.example.adampermission;

public class PermissionItem {
    private String permissionTitle;
    private int iconResource;
    private boolean permissionGranted;

    public PermissionItem(String permissionTitle, int iconResource) {
        this.permissionTitle = permissionTitle;
        this.iconResource = iconResource;
        this.permissionGranted = false;
    }

    public String getPermissionTitle() {
        return permissionTitle;
    }

    public int getIconResource() {
        return iconResource;
    }

    public boolean isPermissionGranted() {
        return permissionGranted;
    }

    public void setPermissionGranted(boolean permissionGranted) {
        this.permissionGranted = permissionGranted;
    }
}
