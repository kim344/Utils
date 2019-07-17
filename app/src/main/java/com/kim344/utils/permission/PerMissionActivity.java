package com.kim344.utils.permission;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.kim344.utils.R;

import java.util.ArrayList;

public class PerMissionActivity extends AppCompatActivity {

    Button mBtnPermissionCheck;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        mBtnPermissionCheck = findViewById(R.id.btn_check);
        mBtnPermissionCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PermissionListener permissionlistener = new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        Toast.makeText(PerMissionActivity.this, "권한 허가", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                        Toast.makeText(PerMissionActivity.this, "권한 거부\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
                    }
                };

                TedPermission.with(PerMissionActivity.this)
                        .setPermissionListener(permissionlistener)
                        .setRationaleMessage("OOO을 하기 위해서는 접근 권한이 필요해요")
                        .setDeniedMessage("[설정] > [권한] 에서 권한을 허용할 수 있어요.")
                        .setPermissions(
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .check();

            }

        });
    }
}
