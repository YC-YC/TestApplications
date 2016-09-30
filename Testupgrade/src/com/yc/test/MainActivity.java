package com.yc.test;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	private static final String TAG = "Test";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void doClick(View view){
		switch (view.getId()) {
		case R.id.button1:
			Log.i(TAG, "测试出错");
			int i = 1/0;
			break;

		default:
			break;
		}
	}
}
