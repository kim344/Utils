package com.kim344.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kim344.utils.R;

public class CustomActivityDialog extends Dialog {

    private Context mContext;
    private String mTitle;
    private IConfirmCallback mCallback;

    private TextView mTxtTitle;
    private LinearLayout mLinearConfirm;

    public CustomActivityDialog(Context ctx, String mTitle, IConfirmCallback mCallback) {
        super(ctx);
        this.mContext = ctx;
        this.mTitle = mTitle;
        this.mCallback = mCallback;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_custom_confirm);

        DisplayMetrics displayMetrics = mContext.getApplicationContext().getResources().getDisplayMetrics();    // 디바이스 크기

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(getWindow().getAttributes());
        layoutParams.width = (int) (displayMetrics.widthPixels * 0.89);     // 다이얼로그 너비 %
        layoutParams.height = (int) (displayMetrics.heightPixels * 0.35);   // 다이얼로그 높이 %
        layoutParams.gravity = Gravity.CENTER;
        this.getWindow().setAttributes(layoutParams);

        setCancelable(false);   // 다이얼로그 밖 터치 불가

        mTxtTitle = findViewById(R.id.dialog_confirm_msg_tv);
        mTxtTitle.setText(mTitle);

        mLinearConfirm = findViewById(R.id.dialog_confirm_ly);
        mLinearConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCallback != null) {
                    mCallback.onConfirm();
                }
                dismiss();
            }
        });
    }

    public interface IConfirmCallback {
        void onConfirm();
    }
}
