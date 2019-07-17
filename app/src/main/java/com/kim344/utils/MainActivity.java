package com.kim344.utils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.kim344.utils.dialog.DialogActivity;
import com.kim344.utils.fragment.FragmentSampleActivity;
import com.kim344.utils.permission.PerMissionActivity;
import com.kim344.utils.recycler.RecyclerActivity;
import com.kim344.utils.retrofit2.RetrofitActivity;
import com.kim344.utils.tabLayout.TabLayoutActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button mBtnRecycler;
    Button mBtnFragment;
    Button mBtnTabLayout;
    Button mBtnRetrofit2;
    Button mBtnPermission;
    Button mBtnDialog;

    /**
     *
     * @Auther :
     * @param :
     * @param :
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnRecycler = findViewById(R.id.btn_recycler);
        mBtnFragment = findViewById(R.id.btn_fragment);
        mBtnTabLayout = findViewById(R.id.btn_tabLayout);
        mBtnRetrofit2 = findViewById(R.id.btn_retrofit2);
        mBtnPermission = findViewById(R.id.btn_permission);
        mBtnDialog = findViewById(R.id.btn_dialog);

        mBtnRecycler.setOnClickListener(this);
        mBtnFragment.setOnClickListener(this);
        mBtnTabLayout.setOnClickListener(this);
        mBtnRetrofit2.setOnClickListener(this);
        mBtnPermission.setOnClickListener(this);
        mBtnDialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent intent = null;

        switch (view.getId()) {

            case R.id.btn_recycler:
                intent = new Intent(MainActivity.this, RecyclerActivity.class);
                break;
            case R.id.btn_fragment:
                intent = new Intent(MainActivity.this, FragmentSampleActivity.class);
                break;
            case R.id.btn_tabLayout:
                intent = new Intent(MainActivity.this, TabLayoutActivity.class);
                break;
            case R.id.btn_retrofit2:
                intent = new Intent(MainActivity.this, RetrofitActivity.class);
                break;
            case R.id.btn_permission:
                intent = new Intent(MainActivity.this, PerMissionActivity.class);
                break;
            case R.id.btn_dialog:
                intent = new Intent(MainActivity.this,DialogActivity.class);
                break;

        }

        if (intent != null) {
            startActivity(intent);
        }

    }
}
