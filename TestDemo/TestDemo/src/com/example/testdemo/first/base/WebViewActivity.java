package com.example.testdemo.first.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.testdemo.R;

public class WebViewActivity extends Activity {

	private static final String URL = "http://2014.qq.com"; 
	private WebView webView;
	private ProgressDialog mProgressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);
		
		initView();
	}

	private void initView() {

		webView = (WebView) findViewById(R.id.webview);
		webView.loadUrl(URL);
		//覆盖WebView默认通过第三方或者系统浏览器打开网页的行为，使得网页可以在WebView中打开
		webView.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				//返回true时候控制网页在WebView中打开 
				view.loadUrl(url);
				return true;
			}
		});
		
		/* 启用支持javascript
		 */
		WebSettings settings = webView.getSettings();
		settings.setJavaScriptEnabled(true);
		
		/*
		 * 加载页面优先使用缓存加载
		 */
		settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		
		
		/*
		 * 获取加载进度
		 */
		webView.setWebChromeClient(new WebChromeClient(){
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				super.onProgressChanged(view, newProgress);
				if (newProgress == 100)
				{
					//加载完成
					closeDialog();
				}
				else
				{
					openDialog(newProgress);
				}
			}

			private void openDialog(int newProgress) {
				if (mProgressDialog == null)
				{
					mProgressDialog = new ProgressDialog(WebViewActivity.this);
					mProgressDialog.setIcon(R.drawable.ic_launcher);
					mProgressDialog.setTitle("加载进度");
					mProgressDialog.show();
				}
				mProgressDialog.setProgress(newProgress);
				mProgressDialog.setMessage("当前加载进度为：" + newProgress + "%");
			}

			private void closeDialog() {
				if (mProgressDialog != null && mProgressDialog.isShowing())
				{
					mProgressDialog.dismiss();
					mProgressDialog = null;
				}
			}
		});
	}
	
	/*
	 * 修改返回键的处理(non-Javadoc)
	 * @see android.app.Activity#onKeyDown(int, android.view.KeyEvent)
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			Toast.makeText(this, "" + webView.getUrl(), Toast.LENGTH_LONG).show();
			if (webView.canGoBack())
			{
				webView.goBack();
				return true;
			}
			else
			{
				System.exit(0);//退出程度
			}
		}
		return super.onKeyDown(keyCode, event);
	}
}
