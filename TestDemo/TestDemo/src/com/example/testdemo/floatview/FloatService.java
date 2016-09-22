package com.example.testdemo.floatview;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.testdemo.R;

/**
 *@Author Administrator
 *@Time 2016-2-29 上午12:07:09
 */
public class FloatService extends Service {

	LinearLayout mLayout;	//悬浮窗口布局
	
	WindowManager.LayoutParams mParams;
	WindowManager mWManager;
	
	private Button mBtnFloat;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		LOG("onCreate");
		creatFloatView();
	}

	private void creatFloatView() {
		mParams = new WindowManager.LayoutParams();
		mWManager = (WindowManager) getApplication().getSystemService(getApplication().WINDOW_SERVICE);
	
//		mParams.type = LayoutParams.TYPE_PHONE;
		mParams.type = LayoutParams.TYPE_TOAST;	//添加这个类型不需要打开系统悬浮权限
		mParams.format = PixelFormat.RGBA_8888;
		//设置浮动窗口不可聚焦（实现操作除浮动窗口外的其他可见窗口的操作）  
		mParams.flags = LayoutParams.FLAG_NOT_FOCUSABLE;
		//调整悬浮窗显示的停靠位置为左侧置顶  
		mParams.gravity = Gravity.LEFT | Gravity.TOP; 
//		mParams.gravity = Gravity.CENTER; 
		
		// 以屏幕左上角为原点，设置x、y初始值，相对于gravity  
		mParams.x = 0;  
		mParams.y = 0;  
  
        //设置悬浮窗口长宽数据    
		mParams.width = WindowManager.LayoutParams.WRAP_CONTENT;  
		mParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
		
        
		 LayoutInflater inflater = LayoutInflater.from(getApplication());  
	        //获取浮动窗口视图所在布局  
	        mLayout = (LinearLayout) inflater.inflate(R.layout.float_win, null);  
	        	        
	        LOG("mLayout.getWidth()--->" + mLayout.getWidth());  
	        LOG("mLayout.getHeight()--->" + mLayout.getHeight());  
	       
	        
	        //添加mFloatLayout  
	        mWManager.addView(mLayout, mParams);  
	        
	        mLayout.measure(
	        		View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), 
	        		View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
	        
	        
	        //浮动窗口按钮  
	        mBtnFloat = (Button)mLayout.findViewById(R.id.btn_float); 
	        
	        LOG("Width/2--->" + mBtnFloat.getMeasuredWidth()/2);  
	        LOG("Height/2--->" + mBtnFloat.getMeasuredHeight()/2);  
	       
	        mBtnFloat.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					 Toast.makeText(FloatService.this, "onClick", Toast.LENGTH_SHORT).show();					
				}
			});
	        mBtnFloat.setOnTouchListener(new OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					switch (event.getAction()) {
					case MotionEvent.ACTION_MOVE:
						LOG("event[X,Y] = "+event.getRawX()+"," + event.getRawY());
						LOG("event[X,Y] = "+mBtnFloat.getMeasuredWidth()+"," + mBtnFloat.getMeasuredWidth());
						//getRawX是触摸位置相对于屏幕的坐标，getX是相对于按钮的坐标  
						mParams.x = (int) event.getRawX() - mBtnFloat.getMeasuredWidth()/2;  
						//减25为状态栏的高度  
						mParams.y = (int) event.getRawY() - mBtnFloat.getMeasuredHeight()/2 - 25;  
						//刷新  
						mWManager.updateViewLayout(mLayout, mParams);  
						return true;  //此处必须返回false，否则OnClickListener获取不到监听
					}
					return false;
				}
			});
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		LOG("onDestroy");
		if (mLayout != null)
		{
			mWManager.removeView(mLayout);
		}
	}

	private void LOG(String string)
	{
		Log.i("FloatServie", string);
	}
}
