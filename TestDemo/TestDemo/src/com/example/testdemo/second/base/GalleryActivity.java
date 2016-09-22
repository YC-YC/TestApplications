package com.example.testdemo.second.base;

import com.example.testdemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ViewSwitcher.ViewFactory;

public class GalleryActivity extends Activity implements OnItemSelectedListener, ViewFactory{

	private int icons[] = {
			R.drawable.item1,
			R.drawable.item2,
			R.drawable.item3,
			R.drawable.item4,
			R.drawable.item5,
			R.drawable.item6,
			R.drawable.item7,
			R.drawable.item8,
			R.drawable.item9,
			R.drawable.item10,
			R.drawable.item11,
			R.drawable.item12
	};
	
	private Gallery mGallery = null;
	private ImageSwitcher mImageSwitcher = null;
	
	/*
	 * 1、准备数据源
	 * 2、适配器
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gallery);
		
		initView();
	}

	private void initView() {
		mGallery = (Gallery) findViewById(R.id.gallery);
		mGallery.setAdapter(new ImageAdapter(icons, this));
		mGallery.setOnItemSelectedListener(this);
		
		mImageSwitcher = (ImageSwitcher) findViewById(R.id.imgSwitch);
		mImageSwitcher.setFactory(this);
		//设置动画效果
		mImageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
		mImageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		mImageSwitcher.setBackgroundResource(icons[position%icons.length]);
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		
	}

	/*
	 * 视图工厂(non-Javadoc)
	 * @see android.widget.ViewSwitcher.ViewFactory#makeView()
	 */
	@Override
	public View makeView() {
		ImageView imageView = new ImageView(GalleryActivity.this);
		//按比例缩放
		imageView.setScaleType(ScaleType.FIT_CENTER);
		return imageView;
	}
	
	
}
