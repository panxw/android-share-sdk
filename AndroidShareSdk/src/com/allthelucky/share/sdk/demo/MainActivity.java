package com.allthelucky.share.sdk.demo;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.allthelucky.share.sdk.R;
import com.allthelucky.share.sdk.ShareErrUtils;
import com.allthelucky.share.sdk.ShareListener;
import com.allthelucky.share.sdk.ShareManager;
import com.allthelucky.share.sdk.ShareParams;
import com.allthelucky.share.sdk.ShareWebo;
import com.allthelucky.share.sdk.Utils;

/**
 * 测试程序
 * 
 * @author savant-pan
 * 
 */
public class MainActivity extends Activity implements View.OnClickListener {

	private String sinaKey = "";
	private String sinaSecret = "";
	private String tecentKey = "";
	private String tecentSecret = "";
	private String callbackUrl = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ShareWebo.init(sinaKey, sinaSecret, tecentKey, tecentSecret, callbackUrl);
		ShareErrUtils.init(this);

		findViewById(R.id.sina_post).setOnClickListener(this);
		findViewById(R.id.tecent_post).setOnClickListener(this);
		findViewById(R.id.sina_cancel).setOnClickListener(this);
		findViewById(R.id.tecent_cancel).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.sina_cancel) {// 取消授权
			ShareManager.cancleAuth(MainActivity.this, ShareWebo.SINA);
		} else if (v.getId() == R.id.tecent_cancel) {// 取消授权
			ShareManager.cancleAuth(MainActivity.this, ShareWebo.TECENT);
		} else {
			int weiboType = 0;
			ShareParams shareParams = new ShareParams();
			if (v.getId() == R.id.sina_post) {
				weiboType = ShareWebo.SINA;
				shareParams.put("status", "hello,world");
			} else {
				weiboType = ShareWebo.TECENT;
				shareParams.put("content", "hello,world");
				shareParams.put("format", "json");
			}

			boolean auth = ShareManager.hasAuth(MainActivity.this, ShareAuthActivity.class, weiboType);
			if (!auth) {
				Toast.makeText(MainActivity.this, "未授权", Toast.LENGTH_SHORT).show();
				return;
			}
			shareContent(shareParams, weiboType);

		}
	}

	private void shareContent(final ShareParams shareParams, final int weiboType) {
		ShareManager.addWeibo(MainActivity.this, ShareWebo.SINA, shareParams, new ShareListener() {
			@Override
			public void onStart() {

			}

			@Override
			public void onResult(int code, String result) {
				JSONObject object = Utils.stringToJSONObject(result);
				if (object != null) {
					int errorCode = 0;
					if (weiboType == ShareWebo.SINA) {
						errorCode = object.optInt("error_code");
					} else {
						errorCode = object.optInt("errcode");
					}
					if (errorCode != 0) {
						String msg = ShareErrUtils.getMessage(weiboType, object.optInt("error_code"));
						Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(MainActivity.this, "分享微博成功", Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(MainActivity.this, "分享微博失败", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
}
