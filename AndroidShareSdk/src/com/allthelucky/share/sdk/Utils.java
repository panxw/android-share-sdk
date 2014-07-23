package com.allthelucky.share.sdk;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

public class Utils {
	/**
	 * 加载Assert文本文件，转换成String类型
	 * 
	 * @param context
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static String loadAssetsText(Context context, String fileName) throws IOException {
		InputStream inputStream = context.getAssets().open(fileName, Context.MODE_PRIVATE);
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		byte[] bytes = new byte[4096];
		int len = 0;
		while ((len = inputStream.read(bytes)) > 0) {
			byteStream.write(bytes, 0, len);
		}

		return new String(byteStream.toByteArray(), "UTF-8");
	}
	
	/**
	 * 字节码转JSONObject
	 * 
	 * @param data
	 *            字节码（源为字符）
	 * @return
	 */
	public static JSONArray bytesToJSONArray(byte data[]) {
		if (data == null)
			return null;
		return stringToJSONArray(bytesToString(data));
	}

	/**
	 * 字节码转JSONObject
	 * 
	 * @param data
	 *            字节码（源为字符）
	 * @return
	 */
	public static JSONObject bytesToJSONObject(byte data[]) {
		if (data == null)
			return null;
		return stringToJSONObject(bytesToString(data));
	}

	/**
	 * 字节码数据 转 字符串工具
	 */
	public static String bytesToString(byte[] data) {
		if (data == null)
			return null;
		try {
			return new String(data, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 字符串转JSONObject工具
	 * 
	 * @param json
	 * @return
	 */
	public static JSONObject stringToJSONObject(String json) {
		if (json == null)
			return null;
		try {
			return new JSONObject(json);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 字符串转JSONArray工具
	 * 
	 * @param json
	 * @return
	 */
	public static JSONArray stringToJSONArray(String json) {
		if (json == null)
			return null;
		try {
			return new JSONArray(json);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}

}
