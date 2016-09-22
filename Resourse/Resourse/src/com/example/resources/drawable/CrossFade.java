package com.example.resources.drawable;

import com.example.resources.R;
import com.example.resources.R.id;
import com.example.resources.R.layout;

import android.app.Activity;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 *@Author Administrator
 *@Time 2016-3-6 下午9:08:40
 */
public class CrossFade extends Activity {
	private ImageView ivLamp;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cross_fade);

		ivLamp = (ImageView) findViewById(R.id.imageview_lamp);
	} 
		
    
	public void onClick_LampOn(View view)
	{
				
		TransitionDrawable drawable = (TransitionDrawable)ivLamp.getDrawable();
		drawable.startTransition(1000);
	}

	public void onClick_LampOff(View view)
	{
		TransitionDrawable drawable = (TransitionDrawable)ivLamp.getDrawable();
		drawable.reverseTransition(1000);
	}
}
