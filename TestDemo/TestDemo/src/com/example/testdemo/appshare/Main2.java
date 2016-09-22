package com.example.testdemo.appshare;

import com.example.testdemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 *@Author Administrator
 *@Time 2016-2-24 下午7:39:26
 */
public class Main2 extends Activity {
	private TextView mTextView;
	private EditText mEditText;
	private Button mButton;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_share2);
		
		mTextView = (TextView) findViewById(R.id.textView1);
		mEditText = (EditText) findViewById(R.id.editText1);
		mButton = (Button) findViewById(R.id.button1);
		
		mTextView.setText("共享的数据是：" + getApp().getmShareString());
		mButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getApp().setmShareString(mEditText.getText().toString());
				mTextView.setText("共享的数据是：" + getApp().getmShareString());
			}
		});
		
	}
	
	private App getApp()
	{
		return (App) getApplicationContext();
	}
}
