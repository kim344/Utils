package com.kim344.utils.dialog;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatDialog;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kim344.utils.R;

public class ProgressDialog {

    private AppCompatDialog progressDialog;

    public void progressON(final Activity activity, String message) {

        if (activity == null || activity.isFinishing()) {
            return;
        }

        if (progressDialog != null && progressDialog.isShowing()) {
            progressSET(message);
        } else {
            progressDialog = new AppCompatDialog(activity);
            progressDialog.setCancelable(false);
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            progressDialog.setContentView(R.layout.dialog_progress);
            progressDialog.show();
        }


        final ImageView imgLoadingFrame = (ImageView) progressDialog.findViewById(R.id.iv_frame_loading);
        if (imgLoadingFrame != null) {

            imgLoadingFrame.post(new Runnable() {
                @Override
                public void run() {
                    Glide.with(activity).load(R.raw.loding).into(imgLoadingFrame);
                }
            });

        }

        TextView txtProgressMessage = (TextView) progressDialog.findViewById(R.id.tv_progress_message);
        if (!TextUtils.isEmpty(message)) {
            txtProgressMessage.setText(message);
        }


    }

    public void progressSET(String message) {

        if (progressDialog == null || !progressDialog.isShowing()) {
            return;
        }

        TextView txtProgressMessage = (TextView) progressDialog.findViewById(R.id.tv_progress_message);
        if (!TextUtils.isEmpty(message)) {
            txtProgressMessage.setText(message);
        }

    }

    public void progressOFF() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
