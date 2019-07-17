package com.kim344.utils.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class Configs 
{
	private Context context;
	
	private SharedPreferences pref;
	private SharedPreferences.Editor editor;
	
	public Configs(String configName, Context context)
	{
		this.context = context;
		if(TextUtils.isEmpty(configName))
		{
			throw new RuntimeException("config name can't not null or blank.");
		}
		pref = this.context.getSharedPreferences(configName, Context.MODE_PRIVATE);
		editor = pref.edit();
	}
	
	// config 정보 출력
	public String getString(String key) 
	{
		return pref.getString(key, "");
	}

	public boolean getBoolean(String key, boolean defValue) 
	{
		return pref.getBoolean(key, defValue);
	}

	public void putString(String key, String value) 
	{
		editor.putString(key, value);
		editor.commit();
	}
	
	public void putInt(String key, int value) 
	{
		editor.putInt(key, value);
		editor.commit();
	}
	
	public int getInt(String key, int defaultVal)
	{
		return pref.getInt(key, defaultVal);
	}
	
	public void putStringArray(String[] keys, String values[])
	{
		if(keys.length != values.length)
		{
			throw new RuntimeException("Key data length not matched value data length.");
		}
		
		int nCount = keys.length;
		for(int i = 0; i < nCount; i++)
		{
			editor.putString(keys[i], values[i]);
		}
		editor.commit();
	}

	public void putBoolean(String key, boolean value) 
	{
		editor.putBoolean(key, value);
		editor.commit();
	}

	// config 정보 삭제
	public void removeConfig(String key) 
	{
		editor.remove(key);
		editor.commit();
	}

	// config 정보 전체삭제
	public void removeAllConfig() 
	{
		editor.clear();
		editor.commit();
	}
}
