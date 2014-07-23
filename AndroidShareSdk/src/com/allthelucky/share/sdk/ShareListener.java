package com.allthelucky.share.sdk;

/**
 * 分享微博结果通知回调
 * 
 * @author savant-pan
 * 
 */
public interface ShareListener {
	/**
	 * 数据返回成功
	 */
	public static final int OK = 0;
	/**
	 * 网络请求失败
	 */
	public static final int ERR = 1;

	/**
	 * 请求开始
	 */
	public void onStart();

	/**
	 * 数据返回
	 * 
	 * @param code
	 *            数据返回状态
	 * @param result
	 *            响应结果
	 */
	public void onResult(int code, String result);
}
