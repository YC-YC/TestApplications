package com.example.resources;


import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.example.customview.CustomTextViewActivity;
import com.example.draghelper.ViewDragHelperActivity;
import com.example.resources.drawpic.DrawActivity;
import com.example.resources.loading.LoadingActivity;
import com.example.resources.popupwin.PopupWindowActivity;
import com.example.resources.popupwin.WindowUtils;
import com.example.resources.receiver.AdminReceiver;
import com.example.resources.scroller.ScrollActivity;
import com.example.resources.text.TextViewLinkActivity;
import com.example.resources.utils.Utils;
import com.example.resources.view.CustomActivity1;
import com.example.resources.view.CustomActivity2;
import com.example.resources.view.CustomActivity3;
import com.example.resources.view.CustomActivity4;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";

	private DevicePolicyManager dpm;
	private ComponentName componentName;
	
	private Button mBtnTest;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dpm = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
		componentName = new ComponentName(this, AdminReceiver.class);
		mBtnTest = (Button) findViewById(R.id.btntest);
		
	}

	public void onClick_Drawable(View view) {
		Intent intent = new Intent(this, ResourceActivity.class);
		startActivity(intent);
	}

	public void onClick_test(View view){
//		Utils.runRuntimeFun();
//		Utils.shuffle();
//		Utils.ReadPlugIn(this, this.getClass().getClassLoader());
//		Utils.getVideoAtFrame("mnt/USB/video/20150101_051237B.mp4", "mnt/sdcard/shoot/20150101_051237B.png", 20*1000);
//		new Button().performClick();
		/*new View(this).post(new Runnable() {
			@Override
			public void run() {
				new Button(MainActivity.this).performClick();
			}
		});*/
		
//		AudioManager aa = (AudioManager) getSystemService(Service.AUDIO_SERVICE);
//		aa.playSoundEffect(SoundEffectConstants.CLICK);
		
		int processors = Runtime.getRuntime().availableProcessors();//为2
		Log.i(TAG, "avaliable processor = " + processors);
		
		Utils.testThreadPool();
		
		Intent it = new Intent("com.zhonghong.ACTION_AMIRRA");
		it.putExtra("quit", "true");
		sendBroadcast(it);
	}
	
	


	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return super.onKeyDown(keyCode, event);
	}
	
	public void onClick_Animtation(View view) {
		Intent intent = new Intent(this, AnimationActivity.class);
		startActivity(intent);
	}
	public void onClick_Reflect(View view) {
		Intent intent = new Intent(this, ReflectActivity.class);
		startActivity(intent);
	}
	
	public void onClick_Main(View view)
	{
		switch (view.getId()) {
		case R.id.btn_contentprovider:
			startActivity(new Intent(this, ContentResolveActivity.class));
			break;
		case R.id.btn_popupwind:
			startActivity(new Intent(this, PopupWindowActivity.class));
			break;
		case R.id.btn_drawpic:
			startActivity(new Intent(this, DrawActivity.class));
			break;
			
		case R.id.btn_customviewgroup1:
			startActivity(new Intent(this, CustomActivity1.class));
			break;
		case R.id.btn_customviewgroup2:
			startActivity(new Intent(this, CustomActivity2.class));
			break;
		case R.id.btn_customviewgroup3:
			startActivity(new Intent(this, CustomActivity3.class));
			break;
		case R.id.btn_customview:
			startActivity(new Intent(this, CustomActivity4.class));
			break;
		case R.id.btn_customtextview:
			startActivity(new Intent(this, CustomTextViewActivity.class));
			break;
		case R.id.btn_expandlistview:
			startActivity(new Intent(this, ExpandListActivity.class));
			break;
		case R.id.btn_askpermission:
			startDeviceManager();
			break;
		case R.id.btn_stoppermission:
			stopDeviceManager();
			break;
		case R.id.btn_lockscreen:
			sysLock();
			break;
		case R.id.btn_windowmanager:
			testWindowManager();
			break;
		case R.id.btn_viewdraghelper:
			startActivity(new Intent(this, ViewDragHelperActivity.class));
			break;
		case R.id.btn_testscroller:
			startActivity(new Intent(this, ScrollActivity.class));
			break;
		case R.id.btn_loading:
			startActivity(new Intent(this, LoadingActivity.class));
			break;
		default:
			break;
		}
	}

	public void onClickTextView(View view){
		switch (view.getId()) {
		case R.id.Testspannablestring:
			startActivity(new Intent(this, TextViewLinkActivity.class));
			break;

		default:
			break;
		}
	}
	
	
	/**
	 * 
	 */
	private void testWindowManager() {
		WindowUtils.getInstanse(this).showPopupWindow();
	}

	/**
	 * 锁屏
	 */
	private void sysLock() {
		if (dpm.isAdminActive(componentName)){
			dpm.lockNow();
		}
	}

	/**
	 * 关闭权限
	 */
	private void stopDeviceManager() {
		if (dpm.isAdminActive(componentName)){
			dpm.removeActiveAdmin(componentName);
		}
	}

	/**
	 * 获取权限
	 */
	private void startDeviceManager() {
		Intent it = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
		
		//需要用到哪些权限，
		it.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
		//附加的说明
		it.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "YC获取权限");
		startActivityForResult(it, 0);
	}
	
	
}
