/**
 * 
 */
package com.example.bigapps.float360;

import java.lang.reflect.Field;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Toast;

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
	
	private FloatCircleView mCircleView;
	private FloatMenuView mMenuView;
	
	private static FloatViewManager instances;
	private LayoutParams params;
	private FloatViewManager(Context context){
		this.mContext = context;
		mManger = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
		mCircleView = new FloatCircleView(mContext);
		mCircleView.setOnTouchListener(new OnTouchListener() {
			
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
					mManger.updateViewLayout(mCircleView, params);
					startX = x;
					startY = y;
					mCircleView.setDragState(true);
					break;
				case MotionEvent.ACTION_UP:
					float x1 = event.getRawX();
					if (x1 > getScreenWidth()/2){
						params.x = getScreenWidth() - mCircleView.mWidth;
					}
					else{
						params.x = 0;
					}
					mManger.updateViewLayout(mCircleView, params);
					mCircleView.setDragState(false);
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
		mCircleView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(mContext, "点击了", Toast.LENGTH_SHORT).show();
				mManger.removeView(mCircleView);
				showFloatMenuView();
				mMenuView.startAnimation();
			}
		});
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
		Log.i(TAG, "showCircleView");
		if (params == null){
			params = new LayoutParams();
			params.width = mCircleView.mWidth;
			params.height = mCircleView.mHeight;
			Log.i(TAG, "width = " + params.width + ", height = " + params.height);
			params.gravity = Gravity.TOP|Gravity.LEFT;
			params.x = 0;
			params.y = 0;
			params.type = LayoutParams.TYPE_PHONE;
			//无焦点
			params.flags = LayoutParams.FLAG_NOT_FOCUSABLE|LayoutParams.FLAG_NOT_TOUCH_MODAL;
			params.format = PixelFormat.RGBA_8888;
		}
		mManger.addView(mCircleView, params);
		Log.i(TAG, "showCircleView +++++");
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

