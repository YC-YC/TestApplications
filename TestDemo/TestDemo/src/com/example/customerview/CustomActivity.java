package com.example.customerview;

import com.example.testdemo.R;
import com.example.testdemo.Topbar;
import com.example.testdemo.Topbar.topbarClickListener;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

/*
 * 1、定义属性atts.xml
 * 2、自定义类TopBar.java
 * 3、引用自定义View
 * 
 */
public class CustomActivity extends Activity implements topbarClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom_main);
		Topbar topbar = (Topbar) findViewById(R.id.topbar);
		topbar.setOnTopbarClickListener(this);
	}

	@Override
	public void leftClick() {
		Toast.makeText(CustomActivity.this, "点击了左按键", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void rightClick() {
		Toast.makeText(CustomActivity.this, "点击了右按键", Toast.LENGTH_SHORT).show();
		
	}
}
