package com.example.testdemo.second.base;

import com.example.testdemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

/*
 * 1、可静态加载（xml加载）
 * 2、可动态加载（代码加载）
 */
public class ViewFlipperActivity extends Activity {

	private int[] icons = {R.drawable.pic1,
							R.drawable.pic2,
							R.drawable.pic3,
							R.drawable.pic4};
	
	private float mStartX;
	private ViewFlipper mViewFlipper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewflipper);
		
		initView();
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		switch (event.getAction()) {
		case MotionEvent.ACTION_MOVE:
			
			break;
		case MotionEvent.ACTION_DOWN:
			mStartX = event.getX();
			break;
		case MotionEvent.ACTION_UP:
			if (event.getX() - mStartX > 100)//向右
			{
				//设置进入动画
				mViewFlipper.setInAnimation(this, R.anim.left_in);
				//设置退出动画
				mViewFlipper.setOutAnimation(this, R.anim.left_out);
				//显示前一页
				mViewFlipper.showPrevious();
			}
			
			if (event.getX() - mStartX < -100)//向左
			{
				//设置进入动画
				mViewFlipper.setInAnimation(this, R.anim.right_in);
				//设置退出动画
				mViewFlipper.setOutAnimation(this, R.anim.right_out);
				//显示前一页
				mViewFlipper.showNext();
			}
			break;

		default:
			break;
		}
		return super.onTouchEvent(event);
	}
	
	private void initView() {
		mViewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);
		//动态加载
		for (int iconid : icons) {
			mViewFlipper.addView(getImageView(iconid));
		}
		
		//设置进入动画
		mViewFlipper.setInAnimation(this, R.anim.left_in);
		//设置退出动画
		mViewFlipper.setOutAnimation(this, R.anim.left_out);
		//设置播放时间
		mViewFlipper.setFlipInterval(3000);
		//开始动画
		mViewFlipper.startFlipping();
	}
	private View getImageView(int iconid) {
		ImageView imageView = new ImageView(this);
//		imageView.setImageResource(iconid);
		imageView.setBackgroundResource(iconid);
		return imageView;
	}
}
