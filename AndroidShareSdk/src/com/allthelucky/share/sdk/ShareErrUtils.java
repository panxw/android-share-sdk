package com.allthelucky.share.sdk;

import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Context;

/**
 * 微博错误信码解析工具
 * 
 * @author savant-pan
 * 
 */
@SuppressLint("UseSparseArrays")
public class ShareErrUtils {
	private static HashMap<Integer, String> SINA_ERRS = new HashMap<Integer, String>();
	private static HashMap<Integer, String> QQ_ERRS = new HashMap<Integer, String>();

	private ShareErrUtils() {

	}

	/**
	 * 取错误码对应说明
	 * 
	 * @param type
	 *            微博类型
	 * @param code
	 *            错误码
	 * @return
	 */
	public static String getMessage(int type, int code) {
		if (ShareWebo.SINA == type) {
			return SINA_ERRS.get(code);
		} else {
			return QQ_ERRS.get(code);
		}
	}

	/**
	 * init ShareErrUtils
	 * 
	 * @param context
	 */
	public static void init(final Context context) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					parse("weibo_sina_code.json", SINA_ERRS);
					parse("weibo_qq_code.json", QQ_ERRS);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			protected void parse(String errorJson, HashMap<Integer, String> errorMap) throws Exception {
				JSONObject json = new JSONObject(Utils.loadAssetsText(context, errorJson));
				Iterator<?> keys = json.keys();
				while (keys.hasNext()) {
					String key = (String) keys.next();
					errorMap.put(Integer.valueOf(key), json.getString(key));
				}
			}
		}).start();
	}

}
