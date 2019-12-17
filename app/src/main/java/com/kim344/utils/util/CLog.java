package com.kim344.utils.util;

import android.util.Log;

import com.kim344.utils.BuildConfig;

public class CLog {

    private static String TAG = "SUDO";

    public static void v(String msg){
        if (BuildConfig.DEBUG){
            Log.v(TAG,buildLogMsg(msg));
        }
    }

    public static void d(String msg){
        if (BuildConfig.DEBUG){
            Log.d(TAG,buildLogMsg(msg));
        }
    }

    public static void i(String msg){
        if (BuildConfig.DEBUG){
            Log.i(TAG,buildLogMsg(msg));
        }
    }

    public static void w(String msg){
        if (BuildConfig.DEBUG){
            Log.w(TAG,buildLogMsg(msg));
        }
    }

    public static void e(String msg){
        if (BuildConfig.DEBUG){
            Log.e(TAG,buildLogMsg(msg));
        }
    }

    private static String buildLogMsg(String message){
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[4];
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        stringBuilder.append(stackTraceElement.getFileName().replace(".java","")); stringBuilder.append("::");
        stringBuilder.append(stackTraceElement.getMethodName()); stringBuilder.append("]");
        stringBuilder.append(message);

        return stringBuilder.toString();
    }

}
