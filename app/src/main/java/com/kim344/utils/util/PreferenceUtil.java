package com.kim344.utils.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtil {

    //Preference KEY 값
    public static final String PREFERENCE_NAME="preference_nm";
    private static PreferenceUtil preferencemodule = null;
    private static Context mContext;
    private static SharedPreferences prefs;
    private static SharedPreferences.Editor editor;

    public static PreferenceUtil getInstance(Context context) {
        mContext = context;

        if (preferencemodule == null) {
            preferencemodule = new PreferenceUtil();
        }
        if(prefs==null){
            prefs = mContext.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
            editor = prefs.edit();
        }
        return preferencemodule;
    }


    // PutExtra
    public void putBooleanExtra(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void putIntExtra(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public void putLongExtra(String key, long value) {
        editor.putLong(key, value);
        editor.commit();
    }

    public void putFloatExtra(String key, float value) {
        editor.putFloat(key, value);
        editor.commit();
    }

    public void putStringExtra(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }



    // GetExtra
    public boolean getBooleanExtra(String key) {
        return prefs.getBoolean(key, false);
    }

    public int getIntExtra(String key) {
        return prefs.getInt(key, 0);
    }

    public long getLongExtra(String key) {
        return prefs.getLong(key, 0);
    }

    public float getFloatExtra(String key) {
        return prefs.getFloat(key, 0f);
    }

    public String getStringExtra(String key) {
        return prefs.getString(key, "");
    }



    // Remove , Clear
    public void removePreference(String key) {
        editor.remove(key).commit();
    }

    public void clearPreference() {
        editor.clear();
        editor.commit();
    }

    public boolean containCheck(String key) {
        return prefs.contains(key);
    }
}
