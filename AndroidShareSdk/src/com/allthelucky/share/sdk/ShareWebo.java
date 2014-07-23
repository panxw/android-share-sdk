package com.allthelucky.share.sdk;

/**
 * 微博参数配置
 * 
 * @author savant-pan
 * 
 */
public class ShareWebo {
	/**
	 * 新浪微博
	 */
	public static final int SINA = 0;
	/**
	 * 新浪微博授权地址
	 */
	protected static final String SINA_AUTH_URL = "https://open.weibo.cn/oauth2/authorize";
	/**
	 * SINA 微博发布接口地址
	 */
	protected static final String SINA_URL = "https://api.weibo.com/2/statuses/update.json";
	/**
	 * 腾讯微博微博
	 */
	public static final int TECENT = 1;
	/**
	 * 腾讯微博应用授权地址
	 */
	protected static final String TECENT_AUTH_URL = "https://open.t.qq.com/cgi-bin/oauth2/authorize";
	/**
	 * TECENT 微博发布接口地址
	 */
	protected static final String TECENT_URL = "https://open.t.qq.com/api/t/add";
	/**
	 * 新浪微博应用ID
	 */
	protected static String SINA_KEY = "";
	/**
	 * 新浪微博应用SECRET protected
	 */
	protected static String SINA_SECRET = "";
	/**
	 * 腾讯微博应用ID
	 */
	protected static String TECENT_KEY = "";
	/**
	 * 腾讯微博应用SECRET
	 */
	protected static String TECENT_SECRET = "";
	/**
	 * 微博授权回调地址（公用）
	 */
	protected static String CALLBACK_RUL = "";

	/**
	 * 初始化Sina及Tecent微博参数
	 * 
	 * @param sinaKey
	 *            Sina微博Key
	 * @param sinaSecret
	 *            Sina微博Secret
	 * @param tecentKey
	 *            Tecent微博Key
	 * @param tecentSecret
	 *            　Tecent微博Secret
	 * @param callbackUrl
	 *            　公用回调地址，也是Tecent微博的应用地址
	 */
	public static void init(String sinaKey, String sinaSecret, String tecentKey, String tecentSecret, String callbackUrl) {
		SINA_KEY = sinaKey;
		SINA_SECRET = sinaSecret;
		TECENT_KEY = tecentKey;
		TECENT_SECRET = tecentSecret;
		CALLBACK_RUL = callbackUrl;
	}

}
