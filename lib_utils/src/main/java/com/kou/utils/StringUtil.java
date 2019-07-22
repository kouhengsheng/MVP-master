package com.kou.utils;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.TextView;

/**
 * 字符串相关工具类
 * <p>
 * Author: nanchen
 * Email: liushilin520@foxmail.com
 * Date: 2017-02-19  9:52
 */

public class StringUtil {
	public static final String EMPTY = "";

	private StringUtil() {
		throw new UnsupportedOperationException("u can't instantiate me...");
	}


	public static boolean isCamera(String title) {
		if (!TextUtils.isEmpty(title) && title.startsWith("相机胶卷")
				|| title.startsWith("CameraRoll")
				|| title.startsWith("所有音频")
				|| title.startsWith("All audio")) {
			return true;
		}

		return false;
	}


	public static void modifyTextViewDrawable(TextView v, Drawable drawable, int index) {
		drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
		//index 0:左 1：上 2：右 3：下
		if (index == 0) {
			v.setCompoundDrawables(drawable, null, null, null);
		} else if (index == 1) {
			v.setCompoundDrawables(null, drawable, null, null);
		} else if (index == 2) {
			v.setCompoundDrawables(null, null, drawable, null);
		} else {
			v.setCompoundDrawables(null, null, null, drawable);
		}
	}


	/**
	 * 判断字符串是否为空
	 *
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0 || str.equalsIgnoreCase("null");
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * @param str
	 * @return
	 */
	public static String trim(String str) {
		return str == null ? EMPTY : str.trim();
	}

	/**
	 * 截取字符串
	 *
	 * @param search       待搜索的字符串
	 * @param start        起始字符串 例如：<title>
	 * @param end          结束字符串 例如：</title>
	 * @param defaultValue
	 * @return
	 */
	public static String substring(String search, String start, String end, String defaultValue) {
		int start_len = start.length();
		int start_pos = StringUtil.isEmpty(start) ? 0 : search.indexOf(start);
		if (start_pos > -1) {
			int end_pos = StringUtil.isEmpty(end) ? -1 : search.indexOf(end,
					start_pos + start_len);
			if (end_pos > -1)
				return search.substring(start_pos + start.length(), end_pos);
			else
				return search.substring(start_pos + start.length());
		}
		return defaultValue;
	}

	/**
	 * 截取字符串
	 *
	 * @param search 待搜索的字符串
	 * @param start  起始字符串 例如：<title>
	 * @param end    结束字符串 例如：</title>
	 * @return
	 */
	public static String substring(String search, String start, String end) {
		return substring(search, start, end, "");
	}


	/**
	 * Helper function for making null strings safe for comparisons, etc.
	 *
	 * @return (s == null) ? "" : s;
	 */
	public static String makeSafe(String s) {
		return (s == null) ? "" : s;
	}
}
