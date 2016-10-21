package com.example.bigapps.float360;

import com.example.bigapps.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

/**
 * @author Administrator 2016-10-22上午12:47:58
 * TODO：
 */
public class FloatMenuView extends LinearLayout {

	private TranslateAnimation animation;

	public FloatMenuView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View view = View.inflate(context, R.layout.layout_float_menu, null);
		LinearLayout ll = (LinearLayout) view.findViewById(R.id.ll);
		animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
				0, 
				Animation.RELATIVE_TO_SELF, 
				0,
				Animation.RELATIVE_TO_SELF,
				1.0f,
				Animation.RELATIVE_TO_SELF,
				0);
		animation.setDuration(1000);
		animation.setFillAfter(true);
		ll.setAnimation(animation);
		
		view.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				FloatViewManager.getInstances(getContext()).hideFloatMenuView();
				FloatViewManager.getInstances(getContext()).showCircleView();
				return false;
			}
		});
		
		addView(view);
	}

	public FloatMenuView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public FloatMenuView(Context context) {
		this(context, null);
	}
	
	public void startAnimation(){
		animation.start();
	}

}

