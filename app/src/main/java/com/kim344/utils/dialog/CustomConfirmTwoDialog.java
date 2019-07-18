package com.kim344.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.kim344.utils.R;

public class CustomConfirmTwoDialog extends Dialog implements View.OnClickListener {

    private Context mContext;
    private String mTitle;
    private String mContent;
    private String mOk;
    private String mCancel;
    private CustomConfirmTwoDialog.ClickCallBack mCallBack;

    public CustomConfirmTwoDialog(Context mContext, String mTitle, String mContent, String mOk, String mCancel, CustomConfirmTwoDialog.ClickCallBack mCallBack) {
        super(mContext);
        this.mContext = mContext;
        this.mTitle = mTitle;
        this.mContent = mContent;
        this.mOk = mOk;
        this.mCancel = mCancel;
        this.mCallBack = mCallBack;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_custom_confirm_two);

        TextView mTxtTitle = findViewById(R.id.confirm_main_tv);
        TextView mTxtContent = findViewById(R.id.confirm_main_tv2);
        TextView mTxtOk = findViewById(R.id.confirm_buy_ok);
        TextView mTxtCancel = findViewById(R.id.confirm_buy_cancel);

        mTxtOk.setOnClickListener(this);
        mTxtCancel.setOnClickListener(this);

        mTxtTitle.setText(mTitle);
        mTxtContent.setText(mContent);
        mTxtOk.setText(mOk);
        mTxtCancel.setText(mCancel);

        setCancelable(false);   // 다이얼로그 밖 터치 불가
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.confirm_buy_ok:
                mCallBack.onConfirm(true);
                dismiss();
                break;
            case R.id.confirm_buy_cancel:
                mCallBack.onConfirm(false);
                dismiss();
                break;
        }
    }

    public interface ClickCallBack {
        void onConfirm(boolean isClick);
    }


}
