package com.example.myservicetest;

import com.example.myservicelib.MyManager;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView mContent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContent = (TextView) findViewById(R.id.textView1);
	}
	
	public void doClick(View view){
		switch (view.getId()) {
		case R.id.button1:
			MyManager.getInstance().setVal(100);
			mContent.setText("设置值：" + MyManager.getInstance().getVal());
			break;

		default:
			break;
		}
	}

}
