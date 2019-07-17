package com.kim344.utils.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.kim344.utils.R;

public class FragmentSampleActivity extends AppCompatActivity implements View.OnClickListener {

    Button mBtn1, mBtn2, mBtn3;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    FragmentPage1 mFragmentPage1;
    FragmentPage2 mFragmentPage2;
    FragmentPage3 mFragmentPage3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        mBtn1 = findViewById(R.id.btn1);
        mBtn2 = findViewById(R.id.btn2);
        mBtn3 = findViewById(R.id.btn3);

        mBtn1.setOnClickListener(this);
        mBtn2.setOnClickListener(this);
        mBtn3.setOnClickListener(this);

        mFragmentPage1 = new FragmentPage1();
        mFragmentPage2 = new FragmentPage2();
        mFragmentPage3 = new FragmentPage3();

        setFrag(0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                setFrag(0);
                break;
            case R.id.btn2:
                setFrag(1);
                break;
            case R.id.btn3:
                setFrag(2);
                break;
        }
    }

    public void setFrag(int page) {

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();

        switch (page) {
            case 0:
                mFragmentTransaction.replace(R.id.main_frame, mFragmentPage1);
                break;
            case 1:
                mFragmentTransaction.replace(R.id.main_frame, mFragmentPage2);
                break;
            case 2:
                mFragmentTransaction.replace(R.id.main_frame, mFragmentPage3);
                break;
        }
        mFragmentTransaction.commit();
    }
}
