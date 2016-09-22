/**
 * 
 */
package com.example.resources.scroller;

import com.example.resources.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * @author YC
 * @time 2016-7-24 下午5:51:51
 * TODO:
 */
public class ScrollActivity extends Activity {

	private MyViewGroup myViewGroup;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_scroller);
		myViewGroup = (MyViewGroup) findViewById(R.id.testscrollerview);
		
	}
	
	
	public void scroll(View view){
		myViewGroup.beginScroll();
	}
}
