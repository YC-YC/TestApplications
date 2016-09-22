/**
 * 
 */
package com.example.resources.popupwin;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;

import com.example.resources.R;

/**
 * @author YC
 * @time 2016-6-15 下午9:05:27
 */
public class WindowUtils {

	private static final String TAG = "WindowUtils";
	private Context mContext;
	private WindowManager mWindowManager;
	private View mView;
	private boolean isShow;
	
	private static WindowUtils instanse;
	
	public static WindowUtils getInstanse(Context mContext){
		
		if (instanse == null){
			synchronized (WindowUtils.class) {
				if (instanse == null){
					instanse = new WindowUtils(mContext);
				}
			}
		}
		
		return instanse;
	}
	
	public WindowUtils(Context mContext) {
		super();
		this.mContext = mContext.getApplicationContext();
		mWindowManager = (WindowManager) this.mContext.getSystemService(Context.WINDOW_SERVICE);
		
		mView = getView();
	}

	private View getView() {
		View view = LayoutInflater.from(mContext).inflate(R.layout.popupdialog, null);
		
//		点击Back可消除
		view.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				Log.i(TAG, "onKey keyCode = " + keyCode);
				switch (keyCode) {
				case KeyEvent.KEYCODE_BACK:
					hidePopupWindow();
					return true;
				default:
					break;
				}
				return false;
			}
		});
		
		((Button)(view.findViewById(R.id.btn_cancle))).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				hidePopupWindow();
			}
		});
		
		((Button)(view.findViewById(R.id.btn_confirm))).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				hidePopupWindow();
			}
		});
		
		// 点击窗口外部区域可消除
        // 这点的实现主要将悬浮窗设置为全屏大小，外层有个透明背景，中间一部分视为内容区域
        // 所以点击内容区域外部视为点击悬浮窗外部
		final View content = view.findViewById(R.id.layout_content);
		
		view.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int x = (int) event.getX();
				int y = (int) event.getY();
				Rect rect = new Rect();
				content.getGlobalVisibleRect(rect);
				if (!rect.contains(x, y)){
//					hidePopupWindow();
				}
				return false;
			}
		});
		return view;
	}

	/**添加窗口*/
	public void showPopupWindow(){
		if (isShow){
			Log.i(TAG, "Windows had open");
			return;
		}
		
		WindowManager.LayoutParams params = new WindowManager.LayoutParams();
		
		//类型
		params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
		
		//flag
		params.flags = WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM
				| WindowManager.LayoutParams.FLAG_FULLSCREEN;
//				| WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
		// 如果设置了WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE，弹出的View收不到Back键的事件
		
		//设置弹出框的遮盖层为透明，不设置为黑色
		params.format = PixelFormat.TRANSPARENT;
		// FLAG_NOT_TOUCH_MODAL不阻塞事件传递到后面的窗口
        // 设置 FLAG_NOT_FOCUSABLE 悬浮窗口较小时，后面的应用图标由不可长按变为可长按
        // 不设置这个flag的话，home页的划屏会有问题
		
		params.width = LayoutParams.MATCH_PARENT;
		params.height = LayoutParams.MATCH_PARENT;
		
		params.gravity = Gravity.CENTER;
		
		mWindowManager.addView(mView, params);
		Log.i(TAG, "Add View");
		isShow = true;
		
		
	}
	
	public void hidePopupWindow(){
		Log.i(TAG, "hidePopupWindow");
		if (mView != null && isShow){
			mWindowManager.removeView(mView);
			Log.i(TAG, "removeView");
			isShow = false;
		}
	}
}
