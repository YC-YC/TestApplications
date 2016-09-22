/**
 * 
 */
package com.example.resources.drawpic;

import android.app.Activity;
import android.os.Bundle;

/**
 * @author YC
 * @time 2016-3-17 下午2:53:49
 */
public class DrawActivity extends Activity {

	private CustomView customView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		customView = new CustomView(DrawActivity.this);
		setContentView(customView);
	}
}
