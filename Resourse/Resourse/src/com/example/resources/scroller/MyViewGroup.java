/**
 * 
 */
package com.example.resources.scroller;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * @author YC
 * @time 2016-7-24 下午5:55:46
 * TODO:
 */
public class MyViewGroup extends LinearLayout {

	private static final String TAG = "MyViewGroup";
	private Scroller mScroller;
	private boolean flag = false;
	
	public MyViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
		mScroller = new Scroller(context);
	}
	
	@Override
	public void computeScroll() {
		if (mScroller.computeScrollOffset()){//滑动时间内
			Log.i(TAG, "Scrolling x = " + mScroller.getCurrX());
			//不停地更新位置
			scrollTo(mScroller.getCurrX(), 0);
			postInvalidate();
		}
	}
	
	public void beginScroll(){
		if (flag){
			flag = false;
			mScroller.startScroll(0, 0, 200, 0, 2000);
		}
		else{
			flag = true;
			mScroller.startScroll(0, 0, -200, 0, 2000);
		}
		invalidate();
	}

}
