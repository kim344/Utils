package com.kim344.utils.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class VersionCheck {

    private String getVersionInfo(Context context){
        String version = "";

        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(),0);
            version = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return version;
    }

    private boolean versionCompare(String appVersion, String serverVersion){

        if (appVersion != null && serverVersion != null){
            if (serverVersion.compareTo(appVersion) > 0){
                return false;
            } else {
                return true;
            }
        }

        return true;
    }

}
