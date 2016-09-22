package com.example.testdemo.second.base;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testdemo.R;
import com.example.testdemo.second.base.MyFragment.FragmentCallBack;

/*
 * 实现Fragment提供的接口实现通讯
 */
public class DynamicFragmentActivity extends Activity implements FragmentCallBack{
	private static final String TAG = "DynamicFragmentActivity";
	
	private TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dynamic_fragment);
		tv = (TextView) findViewById(R.id.dynamic_tv);
	}
	
	public void addFragment(View view)
	{
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
	
		Fragment fragment = new MyFragment();
		/*
		 * 添加fragment
		 */
		beginTransaction.add(R.id.dynamic_layout, fragment);
		//返回到上一个Fragment
		beginTransaction.addToBackStack(null);
		beginTransaction.commit();
		
		//获取绑定的Fragment
//		Log.i(TAG, fragmentManager.findFragmentById(id).);
	}
	
	public void setValueToFragment(View view)
	{
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
	
		Bundle bundle = new Bundle();
		bundle.putString("name", "Activity向Fragment传递数据");
		Fragment fragment = new MyFragment();
		//添加数据
		fragment.setArguments(bundle);
		beginTransaction.add(R.id.dynamic_layout, fragment,"MyFragment");
		//返回到上一个Fragment
		beginTransaction.addToBackStack(null);
		beginTransaction.commit();
	}

	@Override
	public void callback(String code) {
		tv.setText("Activity接收到Fragment的数据为：" + code);
	}
}
