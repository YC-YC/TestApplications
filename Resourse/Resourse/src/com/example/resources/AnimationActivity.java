package com.example.resources;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

/**
 * @author YC
 * @time 2016-3-10 下午12:14:03
 */
public class AnimationActivity extends Activity {

	private ImageView mImageView;
	private AnimationDrawable mAnimationDrawable;
	private ImageView mImgPic;
	
	private static final boolean LOAD_FROM_XML = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.anim_main);

		mImageView = (ImageView)findViewById(R.id.img_frame);
		mImageView.setBackgroundResource(R.drawable.loading);
		mAnimationDrawable = (AnimationDrawable) mImageView.getBackground();
		
		mImgPic = (ImageView) findViewById(R.id.img_tween);
		
	}
	
	public void frameDoClick(View view)
	{
		if (mAnimationDrawable.isRunning())
		{
			mAnimationDrawable.stop();
		}
		else
		{
			mAnimationDrawable.start();
		}
	}
	public void alphaDoClick(View view)
	{
		Animation animation = null;
		if (LOAD_FROM_XML)
		{
			animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
		}
		else
		{
			animation = new AlphaAnimation(0.0f, 1.0f);
			animation.setDuration(1000);
		}
		mImgPic.startAnimation(animation);
	}
	
	public void scaleDoClick(View view)
	{
		Animation animation = null;
		if (LOAD_FROM_XML)
		{
			
			animation = AnimationUtils.loadAnimation(this, R.anim.scale);
		}
		else
		{
			animation = new ScaleAnimation(0.0f, 1.4f, 0.0f, 1.4f,
					Animation.RELATIVE_TO_SELF, 
					0.5f,Animation.RELATIVE_TO_SELF, 0.5f);
			animation.setDuration(1000);
		}
		mImgPic.startAnimation(animation);
	}
	
	public void translateDoClick(View view)
	{
		Animation animation = null;
		if (LOAD_FROM_XML)
		{
			animation = AnimationUtils.loadAnimation(this, R.anim.translate);
		}
		else
		{
			animation = new TranslateAnimation(10f, 100f,10f, 100f);
			animation.setDuration(1000);
		}
		mImgPic.startAnimation(animation);
	}
	
	public void rotateDoClick(View view)
	{
		Animation animation = null;
		if (LOAD_FROM_XML)
		{
			animation = AnimationUtils.loadAnimation(this, R.anim.rotate);	
		}
		else
		{
			animation = new RotateAnimation(0f, 350f);
			animation.setDuration(1000);
		}
		mImgPic.startAnimation(animation);
	}
}
