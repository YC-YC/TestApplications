package com.example.slidingMenu;

import com.example.testdemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

/*
 * 一、一般的侧划
 * 1、ViewGroup Menu+content
 * 2、onTouchEvent
 * MOVE:ViewGroup的leftMargin
 * UP:根据显示菜单的宽度，决定隐藏或显示（用Scroller或LeftMargin+Thread）
 * 
 * 二、继承HorizontalScrollView
 * 1、布局
 * 
 */
public class SlidingActivity extends Activity {
	
	private SlidingMenu slidingMenu;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.slidermenu_main);
		slidingMenu = (SlidingMenu) findViewById(R.id.slidermenu);
	}
	
	public void toggleMenu(View view)
	{
		slidingMenu.toggleMenu();
	}
}
