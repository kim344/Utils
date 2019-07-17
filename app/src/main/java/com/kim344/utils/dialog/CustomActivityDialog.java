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

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        setContentView(R.layout.dialog_custom_confirm);

        DisplayMetrics displayMetrics = mContext.getApplicationContext().getResources().getDisplayMetrics();

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
        lp.width = (int) (displayMetrics.widthPixels * 0.89);
        lp.height = (int) (displayMetrics.heightPixels * 0.35);
        lp.gravity = Gravity.CENTER;
        lp.y = -50;

        this.getWindow().setAttributes(lp);

        //다이얼로그 밖 터치 막기
        setCancelable(false);

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
