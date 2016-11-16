/**
 * 
 */
package com.example.bigapps.accessibility;

import java.util.List;

import android.accessibilityservice.AccessibilityService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

/**
 * @author YC
 * @time 2016-11-15 下午3:24:08
 * TODO:
 */
public class MyAccessibilityService extends AccessibilityService {

	private final String TAG = "MyAccessibilityService";

	@Override
	protected void onServiceConnected() {
		//TODO: 系统成功成功绑定到Accessibilityservice服务时会被回掉
		super.onServiceConnected();
		FloatViewManager.getInstances(this).showCircleView();
		Log.e(TAG, "onServiceConnected");
		registerBroadcast();
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		// TODO 关闭这个Accessibility回调
		Log.e(TAG, "onUnbind");
		FloatViewManager.getInstances(this).hideCircleView();
		return super.onUnbind(intent);
	}
	
	@Override
	public void onAccessibilityEvent(AccessibilityEvent event) {
		Log.e(TAG, "onAccessibilityEvent event = " + event.getEventType());
		switch (event.getEventType()) {
		case AccessibilityEvent.TYPE_VIEW_CLICKED:{
//			CharSequence description = event.getContentDescription();
			List<CharSequence> texts = event.getText();
			if (texts != null && texts.size() > 0){
				if ("虚拟键".equals(texts.get(0))){
					performSystemKey(this, AccessibilityService.GLOBAL_ACTION_BACK);
				}
			}
			for (CharSequence text: texts){
				Log.e(TAG, "text list = " + text.toString());
			}
		}break;
		case AccessibilityEvent.TYPE_VIEW_LONG_CLICKED:{
			List<CharSequence> texts = event.getText();
			if (texts != null && texts.size() > 0){
				if ("虚拟键".equals(texts.get(0))){
					performSystemKey(this, AccessibilityService.GLOBAL_ACTION_RECENTS);
				}
			}
		}break;
		case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:{
			List<CharSequence> texts = event.getText();
			if (texts != null && texts.size() > 0){
				if ("虚拟键".equals(texts.get(0))){
					performSystemKey(this, AccessibilityService.GLOBAL_ACTION_BACK);
				}
			}
		}break;
		case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
			/*final String className = event.getClassName().toString();
			Log.e(TAG, "className = " + className);
			if ("com.example.bigapps.slidedel2.LinearLayoutDelDemoActivity".equals(className)){
				performBack(this);
			}*/
			break;

		default:
			break;
		}
	}

	@Override
	public void onInterrupt() {
		Log.e(TAG, "onInterrupt");
	}
	
	@Override
	public void onDestroy() {
		Log.e(TAG, "onInterrupt");
		unregisterReceiver(receiver);
		super.onDestroy();
	}
	
	//模拟返回事件
	private void performSystemKey(AccessibilityService service, int action) {
		if (service == null) {
			return;
		}
		Log.e(TAG, "performBack");
		service.performGlobalAction(action);
	}
	
	
	private void registerBroadcast(){
		IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);//当手机屏幕熄灭时系统发送广播通知用户屏幕已经熄灭
        filter.addAction(Intent.ACTION_SCREEN_OFF);//当手机屏幕亮起时系统发送广播通知用户屏幕已经亮起
        //regist for broadcasts of interest
        registerReceiver(receiver , filter);
	}
	
	private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
        	final String action = intent.getAction();
           if (Intent.ACTION_SCREEN_ON.equals(action)){
        	   Log.e(TAG, "SCREEN_ON");
        	   FloatViewManager.getInstances(context).showCircleView();
           }
           else if (Intent.ACTION_SCREEN_OFF.equals(action)){
        	   Log.e(TAG, "SCREEN_OFF");
        	   FloatViewManager.getInstances(context).hideCircleView();
           } 
        }
    };

}
