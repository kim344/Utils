package com.kim344.utils.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kim344.utils.R;

public class DialogActivity extends AppCompatActivity implements View.OnClickListener {

    Button mBtnProgressDialog;
    Button mBtnYesNoBasicDialog;
    Button mBtnConfirmBasicDialog;
    Button mBtnYesNoCustomDialog;
    Button mBtnConfirmCustomDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        mBtnProgressDialog = findViewById(R.id.btn_progress_dialog);
        mBtnYesNoBasicDialog = findViewById(R.id.btn_yes_no_basic_dialog);
        mBtnConfirmBasicDialog = findViewById(R.id.btn_confirm_basic_dialog);
        mBtnYesNoCustomDialog = findViewById(R.id.btn_yes_no_custom_dialog);
        mBtnConfirmCustomDialog = findViewById(R.id.btn_confirm_custom_dialog);

        mBtnProgressDialog.setOnClickListener(this);
        mBtnYesNoBasicDialog.setOnClickListener(this);
        mBtnConfirmBasicDialog.setOnClickListener(this);
        mBtnYesNoCustomDialog.setOnClickListener(this);
        mBtnConfirmCustomDialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_progress_dialog:
                startProgress(1000, "Loading...");
                break;
            case R.id.btn_yes_no_basic_dialog:
                basicYesOrNoDialog();
                break;
            case R.id.btn_confirm_basic_dialog:
                basicConfirmDialog();
                break;
            case R.id.btn_yes_no_custom_dialog:
                customYesOrNoDialog();
                break;
            case R.id.btn_confirm_custom_dialog:
                customActivityDialog();
                break;
        }
    }

    private void startProgress(int delay, String message) {
        final ProgressDialog progressDialog = new ProgressDialog();
        progressDialog.progressON(DialogActivity.this, message);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.progressOFF();
            }
        }, delay);
    }

    private void basicYesOrNoDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("BasicAlertDialog Title");
        builder.setMessage("BasicAlertDialog Content");
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Yes 를 선택했습니다.", Toast.LENGTH_LONG).show();
                    }
                });
        builder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "No 를 선택했습니다.", Toast.LENGTH_LONG).show();
                    }
                });
        //다이얼로그 밖 터치 막기
        builder.setCancelable(false);
        builder.show();
    }

    private void basicConfirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("BasicAlertDialog Title");
        builder.setMessage("BasicAlertDialog Content");
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Yes 를 선택했습니다.", Toast.LENGTH_LONG).show();
                    }
                });
        //다이얼로그 밖 터치 막기
        builder.setCancelable(false);
        builder.show();
    }

    private void customYesOrNoDialog() {
        new CustomConfirmTwoDialog(DialogActivity.this,
                getString(R.string.txt_title_yes_no_custom_dialog),
                getString(R.string.txt_contents_yes_no_custom_dialog),
                "Yes", "No",
                new CustomConfirmTwoDialog.ClickCallBack() {
                    @Override
                    public void onConfirm(boolean isClick) {
                        if (isClick) {
                            Toast.makeText(getApplicationContext(), "Yes 를 선택했습니다.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "No 를 선택했습니다.", Toast.LENGTH_LONG).show();
                        }
                    }
                }).show();
    }

    private void customActivityDialog(){
        new CustomActivityDialog(DialogActivity.this,
                getString(R.string.txt_title_yes_no_custom_dialog),
                new CustomActivityDialog.IConfirmCallback() {
                    @Override
                    public void onConfirm() {
                        Toast.makeText(getApplicationContext(), "OK 를 선택했습니다.", Toast.LENGTH_LONG).show();
                    }
                }).show();
    }
}
