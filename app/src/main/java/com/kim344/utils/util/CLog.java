package com.kim344.utils.util;

import android.util.Log;

public class CLog
{
	public static boolean DEBUG = false; 
	
	public static void d(String TAG, String message)
	{
		if(DEBUG)
		{
			Log.d(TAG, message);
		}
	}
	public static void i(String TAG, String message)
	{
		if(DEBUG)
		{
			Log.i(TAG, message);
		}
	}
	public static void e(String TAG, String message, Throwable e)
	{
		Log.e(TAG, message, e);
	}
	public static void e(String TAG, String message)
	{
		Log.e(TAG, message);
	}
}
