package com.example.testdemo.floatview;

import com.example.testdemo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 *@Author Administrator
 *@Time 2016-2-28 下午11:58:33
 *需要添加权限<uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW"/>
 */
public class FloatView extends Activity implements OnClickListener {

	private Button mBtnAdd,mBtnRemove;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.float_main);
		mBtnAdd = (Button) findViewById(R.id.btn_add_float);
		mBtnAdd.setOnClickListener(this);
		mBtnRemove = (Button) findViewById(R.id.btn_remove_float);
		mBtnRemove.setOnClickListener(this);
		
		 LayoutInflater inflater = LayoutInflater.from(getApplication());  
	        //获取浮动窗口视图所在布局  
		 LinearLayout  mLayout = (LinearLayout) inflater.inflate(R.layout.float_win, null);  
	        	
		LOG("mLayout.getWidth()--->" + mLayout.getWidth());  
        LOG("mLayout.getHeight()--->" + mLayout.getHeight()); 
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_add_float:
			startService(new Intent(this, FloatService.class));
			finish();
			break;
		case R.id.btn_remove_float:
			stopService(new Intent(this, FloatService.class));
			break;
		}
	}
	
	private void LOG(String string)
	{
		Log.i("FloatServie", string);
	}
}
