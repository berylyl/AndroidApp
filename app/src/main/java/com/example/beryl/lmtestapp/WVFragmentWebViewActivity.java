package com.example.beryl.lmtestapp;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.taobao.windvane.fragment.WVWebViewFragment;
import android.taobao.windvane.webview.WVTweakWebCoreHandler;
import android.taobao.windvane.webview.WVWebChromeClient;
import android.taobao.windvane.webview.WVWebViewClient;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

public class WVFragmentWebViewActivity extends FragmentActivity {

	private WVWebViewFragment fragment = null;
	FragmentManager fragmentManager = null;
	FragmentTransaction fragmentTransaction = null;

	

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragementcontainer);

		Intent in = getIntent();
		Bundle bundle = in.getExtras();
//		WVEventService.getInstance().addEventListener(new WVThreadTest());

		fragmentManager = this.getSupportFragmentManager();
		fragmentTransaction = fragmentManager.beginTransaction();
		fragment = new WVWebViewFragment(this);
		//fragment.getWebView().setWebContentsDebuggingEnabled(true);
		fragment.setWebViewClient(new WVWebViewClient(this) {

			private boolean started = false;

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {

				return super.shouldOverrideUrlLoading(view, url);

			}

			@Override
			public void onPageFinished(WebView view, String url) {

				super.onPageFinished(view, url);
				Log.e("zshshr", "onPageFinished" + url);
				/**
				 * 打印domTree renderTree
				 */
				WVTweakWebCoreHandler.tweakWebCoredump(view);
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				// TODO Auto-generated method stub

				Log.e("zshshr", "onPageStarted" + url);
				super.onPageStarted(view, url, favicon);
			}
		});

		fragment.setWebchormeClient(new WVWebChromeClient(this) {

			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				// TODO Auto-generated method stub
				super.onProgressChanged(view, newProgress);
			}

		});
		fragment.setArguments(bundle);
		fragmentTransaction.add(R.id.browser_fragment_layout, fragment);
		// fragmentTransaction.add(paramFragment, paramString)
		fragmentTransaction.commit();

		// WebView wvucWebView =fragment.getWebView();

		// wvucWebView.loadData("<html></html>", "text/html", "UTF-8");
		// String ua = wvucWebView.getSettings().getUserAgentString();
		// ua = ua.replace("AliApp", "testApp");
		// ua =ua.replace("WindVane", "testApp");
		// ua
		// =" Mozilla/5.0 (Linux; U; Android 4.4.2; zh-cn; HIKe 848 Build/KOT49H) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 UCBrowser/1.0.0.100 U3/0.8.0 Mobile Safari/534.30 Tanggula/0.1.0 WebLight/1.0.2";
		// wvucWebView.getSettings().setUserAgentString(ua);
		WebView webview = fragment.getWebView();
		String ua = webview.getSettings().getUserAgentString();
		ua = ua.replace("AliApp", "testApp");
		webview.getSettings().setUserAgentString(ua);
		/**
		 * 立即生效fragment
		 */
		fragmentManager.executePendingTransactions();
		// fragmentTransaction.commitAllowingStateLoss();
		webview.loadUrl("https://h5.m.taobao.com/awp/core/detail.htm?id=522166121586");

		
	}
	@Override
	protected void onDestroy() {
//		this.unbindService(null);
		super.onDestroy();

	}
	@Override
	public void onBackPressed() {
		if (fragment != null) {
			if (!fragment.onBackPressed())
				this.finish();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub

		MenuItem itemrefresh = menu.add("刷新").setIcon(
				R.drawable.tb_icon_actionbar_refresh_48);
		MenuItemCompat.setShowAsAction(itemrefresh,
				MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
		itemrefresh
				.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
					@Override
					public boolean onMenuItemClick(MenuItem menuItem) {

						WVTweakWebCoreHandler.tweakWebCoredump(fragment
								.getWebView());
						if (fragment.getWebView() != null) {
							fragment.getWebView().reload();
						}

						return (true);
					}

				});

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (fragment != null) {
			fragment.onActivityResult(requestCode, resultCode, data);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	private static class SampleTask extends AsyncTask<Void, Void, Void> {

		private long mStartTime;

		@Override
		protected void onPreExecute() {
			mStartTime = System.currentTimeMillis();
		}

		@Override
		protected Void doInBackground(Void... params) {
			doBackgroundWork();
			return null;
		}

		private void doBackgroundWork() {
			long timeNow;
			do {
				timeNow = System.currentTimeMillis();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} while (timeNow - mStartTime < (1 * 60 * 1000));
		}

	}
}
