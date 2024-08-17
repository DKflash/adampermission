package com.example.adampermission;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.permissionslib.PermissionHelper;
import com.example.permissionslib.PermissionHandler;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PermissionHandler.PermissionStatusListener, cardInterf {

    private ArrayList<PermissionItem> permissionItemList = new ArrayList<>();
    private static final int PERMISSION_CODE_REQUEST = 1;
    private PermissionAdapter permissionAdapter;
    int[] permissionIcons = {R.drawable.internet, R.drawable.calling, R.drawable.vibration,
            R.drawable.audio, R.drawable.location, R.drawable.precise_location,
            R.drawable.sms, R.drawable.contacts, R.drawable.camera,
            R.drawable.calendar, R.drawable.sensor, R.drawable.bluetooth};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView permissionRecyclerView = findViewById(R.id.mRecyclerView);
        initializePermissionItems();
        permissionAdapter = new PermissionAdapter(this, permissionItemList, this);
        permissionRecyclerView.setAdapter(permissionAdapter);
        permissionRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        PermissionHandler.getInstance().addPermissionStatusListener(this);
        requestAllDeclaredPermissions();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PermissionHandler.getInstance().removePermissionStatusListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshPermissionStatus();
    }

    private void initializePermissionItems() {
        String[] permissionTitlesArray = getResources().getStringArray(R.array.app_permissions_txt);
        for (int i = 0; i < permissionTitlesArray.length; i++) {
            permissionItemList.add(new PermissionItem(permissionTitlesArray[i], permissionIcons[i]));
        }
    }

    private void requestAllDeclaredPermissions() {
        String[] declaredPermissions = PermissionHandler.getInstance().getAppDeclaredPermissions(this);

        if (!PermissionHandler.getInstance().checkPermissions(this, declaredPermissions)) {
            PermissionHandler.getInstance().requestMissingPermissions(this, declaredPermissions, PERMISSION_CODE_REQUEST);
        }
    }

    private void refreshPermissionStatus() {
        for (PermissionItem permissionItem : permissionItemList) {
            if (PermissionHandler.getInstance().isPermissionAllowed(this, "android.permission." + permissionItem.getPermissionTitle())) {
                permissionItem.setPermissionGranted(true);
            } else {
                permissionItem.setPermissionGranted(false);
            }
        }
        permissionAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        refreshPermissionStatus();
        PermissionHandler.getInstance().notifyPermissionStatusChange();
    }

    @Override
    public void onPermissionStatusChanged() {
        refreshPermissionStatus();
    }

    @Override
    public void onItemClick(int itemPosition) {
        PermissionItem selectedPermission = permissionItemList.get(itemPosition);
        String fullPermissionName = "android.permission." + selectedPermission.getPermissionTitle();

        if (PermissionHandler.getInstance().isPermissionAllowed(this, fullPermissionName)) {
            displayToast("Permission " + selectedPermission.getPermissionTitle() + " is granted");
        } else {
            displayToast("Permission " + selectedPermission.getPermissionTitle() + " is not granted");
            PermissionHelper.handlePermission(this, fullPermissionName);
        }
    }

    private void displayToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
