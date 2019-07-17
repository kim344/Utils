package com.kim344.utils.util;

import android.text.TextUtils;

public class StringUtil
{
	public static String formatJson(String jsonText)
	{
		String tmp = jsonText.replaceAll("\\{", "\\{\r\n");
		tmp = tmp.replaceAll(",", ",\r\n");
		return tmp.replaceAll("\\}", "\r\n\\}");
	}

    public static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}
