package com.allthelucky.share.sdk.demo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.allthelucky.share.sdk.R;
import com.allthelucky.share.sdk.ShareManager;
import com.allthelucky.share.sdk.ShareWebo;

/**
 * 授权页面
 * 
 * @author savant-pan
 * 
 */
@SuppressLint("SetJavaScriptEnabled")
public class ShareAuthActivity extends Activity {
	private static final String TAG = "WeiboAuthorizeActivity";

	private WebView webView;
	private View progressView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.weibo_webview_layout);

		this.webView = (WebView) findViewById(R.id.webView);
		this.progressView = findViewById(R.id.show_request_progress_bar);
		this.progressView.setVisibility(View.GONE);

		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setSupportZoom(true);
		webSettings.setCacheMode(WebSettings.LOAD_NORMAL);
		webView.requestFocus();
		webView.requestFocus();

		initWebView();
	}

	private void initWebView() {
		Intent intent = this.getIntent();
		int type = intent.getIntExtra("type", 0);
		String authUrl = intent.getStringExtra("auth_url");

		AuthWebViewClient client = new AuthWebViewClient(type);
		webView.loadUrl(authUrl);
		webView.setWebViewClient(client);
	}

	private class AuthWebViewClient extends WebViewClient {
		private int type;

		public AuthWebViewClient(int type) {
			this.type = type;
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			progressView.setVisibility(View.VISIBLE);
			Log.i(TAG, "URL = " + url);
			if (url.indexOf("access_token=") != -1) {
				int start = url.indexOf("access_token=");
				String responseData = url.substring(start);
				ShareManager.setupAuth(ShareAuthActivity.this, type, responseData);
				Toast.makeText(ShareAuthActivity.this, "授权成功！", Toast.LENGTH_SHORT).show();
				finishClient(view);
			}
			super.onPageStarted(view, url, favicon);
		}

		private void finishClient(WebView view) {
			view.stopLoading();
			view.destroyDrawingCache();
			view.destroy();
			finish();
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
			progressView.setVisibility(View.GONE);
		}

		public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
			if (ShareWebo.SINA == type) {// WEIBO_SINA
				handler.proceed();
			} else {// WEIBO_QQ，Android2.2及以上版本才能使用该方法，目前https://open.t.qq.com中存在http资源会引起sslerror，待网站修正后可去掉该方法
				if ((null != view.getUrl()) && (view.getUrl().startsWith("https://open.t.qq.com"))) {
					handler.proceed();// 接受证书
				} else {
					handler.cancel(); // 默认的处理方式，WebView变成空白页
				}
			}
		}
	}

}
