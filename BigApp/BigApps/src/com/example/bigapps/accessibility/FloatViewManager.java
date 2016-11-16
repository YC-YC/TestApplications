/**
 * 
 */
package com.example.bigapps.accessibility;

import java.lang.reflect.Field;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.Toast;

import com.example.bigapps.R;
import com.example.bigapps.float360.FloatMenuView;

/**
 * 
 * @author Administrator
 *下午9:24:59
 * TODO：浮窗管理
 */
public class FloatViewManager {

	private static final String TAG = "FloatViewManager";
	private Context mContext;
	private WindowManager mManger;
	private FloatMenuView mMenuView;
	private Button virtualBtn;
	private int mBtnWidth = 130,mBtnHeight = 130;
	
	private static FloatViewManager instances;
	private LayoutParams params;
	private FloatViewManager(Context context){
		this.mContext = context;
		mManger = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
		virtualBtn = new Button(mContext);
		virtualBtn.setText("虚拟键");
		virtualBtn.setTextColor(Color.argb(0, 0, 0, 0));
		virtualBtn.setBackgroundResource(R.drawable.back);
		virtualBtn.setOnTouchListener(new OnTouchListener() {
			
			private float startX,startY;
			private float startX1,startY1;

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					startX1 = startX = event.getRawX();
					startY1 = startY = event.getRawY();
					break;
				case MotionEvent.ACTION_MOVE:
					float x = event.getRawX();
					float y = event.getRawY();
					float dx = x - startX;
					float dy = y - startY;
					params.x += dx;
					params.y += dy;
					mManger.updateViewLayout(virtualBtn, params);
					startX = x;
					startY = y;
//					virtualBtn.setDragState(true);
					break;
				case MotionEvent.ACTION_UP:
					float x1 = event.getRawX();
					if (x1 > getScreenWidth()/2){
						params.x = getScreenWidth() - mBtnWidth;
					}
					else{
						params.x = 0;
					}
					mManger.updateViewLayout(virtualBtn, params);
//					virtualBtn.setDragState(false);
					if (Math.abs(x1-startX1) > 6){
						//移动时返回true,销毁触摸事件
						return true;
					}
					break;
				default:
					break;
				}
				return false;
			}
		});
		virtualBtn.setClickable(true);
		virtualBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				Toast.makeText(mContext, "click", Toast.LENGTH_SHORT).show();
//				mManger.removeView(mCircleView);
//				showFloatMenuView();
//				mMenuView.startAnimation();
				performSystemKey((AccessibilityService)mContext, AccessibilityService.GLOBAL_ACTION_BACK);
				
			}
		});
		virtualBtn.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
//				Toast.makeText(mContext, "longclick", Toast.LENGTH_SHORT).show();
				performSystemKey((AccessibilityService)mContext, AccessibilityService.GLOBAL_ACTION_RECENTS);
				return true;
			}
		});
	}
	
	//模拟返回事件
	private void performSystemKey(AccessibilityService service, int action) {
		if (service == null) {
			return;
		}
		Log.e(TAG, "performBack");
		service.performGlobalAction(action);
	}
	
	private int getScreenWidth(){
		return mManger.getDefaultDisplay().getWidth();
	}
	
	private int getScreenHeight(){
		return mManger.getDefaultDisplay().getHeight();
	}
	
	private int getStatusHeight(){
		int height = 0;
		try {
			Class<?> name = Class.forName("com.android.internal.R$dimen");
			Object object = name.newInstance();
			Field field = name.getField("status_bar_height");
			int x = (Integer) field.get(object);
			height = mContext.getResources().getDimensionPixelSize(x);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return height;
	}
	
	public static FloatViewManager getInstances(Context context){
		if (instances == null){
			synchronized (FloatViewManager.class) {
				if (instances == null){
					instances = new FloatViewManager(context);
				}
			}
		}
		return instances;
	}
	
	public void showCircleView(){
		Log.e(TAG, "showCircleView");
		if (params == null){
			params = new LayoutParams();
			params.width = mBtnWidth;
			params.height = mBtnHeight;
			Log.e(TAG, "width = " + virtualBtn.getMeasuredWidth() + ", height = " + virtualBtn.getMeasuredHeight());
			Log.e(TAG, "width = " + params.width + ", height = " + params.height);
			params.gravity = Gravity.TOP|Gravity.LEFT;
			params.x = 0;
			params.y = 0;
			params.type = LayoutParams.TYPE_PHONE;
			//无焦点
			params.flags = LayoutParams.FLAG_NOT_FOCUSABLE|LayoutParams.FLAG_NOT_TOUCH_MODAL|
					LayoutParams.FLAG_TRANSLUCENT_NAVIGATION | LayoutParams.FLAG_TRANSLUCENT_STATUS;
			params.format = PixelFormat.RGBA_8888;
		}
		mManger.addView(virtualBtn, params);
		Log.e(TAG, "showCircleView +++++");
	}
	
	public void hideCircleView(){
		try {
			mManger.removeView(virtualBtn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void showFloatMenuView(){
		LayoutParams params = new LayoutParams();
		params.width = getScreenWidth();
		params.height = getScreenHeight() - getStatusHeight();
		Log.i(TAG, "width = " + params.width + ", height = " + params.height);
		params.gravity = Gravity.BOTTOM|Gravity.LEFT;
		params.x = 0;
		params.y = 0;
		params.type = LayoutParams.TYPE_PHONE;
		//无焦点
		params.flags = LayoutParams.FLAG_NOT_FOCUSABLE|LayoutParams.FLAG_NOT_TOUCH_MODAL;
		params.format = PixelFormat.RGBA_8888;
		mMenuView = new FloatMenuView(mContext);
		mManger.addView(mMenuView, params);
		Log.i(TAG, "showFloatMenuView +++++");
	}
	
	public void hideFloatMenuView(){
		mManger.removeView(mMenuView);
	}
}

