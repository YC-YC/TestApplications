package com.example.recylerview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.example.testdemo.R;

/*
 * 1、不关心Item是否显示在正确的地方--》LayoutManager
 * 2、不关心Item如何分隔--》ItemDecoration
 * 3、不关心Item增加与删除的动画--》ItemAnimator
 * 4、仅仅关注如何回收与利用View
 */
public class RecyclerActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
